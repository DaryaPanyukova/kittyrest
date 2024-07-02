import React, {useEffect, useState} from 'react';
import {Link, useParams} from 'react-router-dom';
import useOwnerService from "../../services/OwnerService";

const OwnerPage = () => {
    const {ownerId} = useParams();
    const [owner, setOwner] = useState(null);
    const [loading, setLoading] = useState(true); // Добавляем состояние для отслеживания загрузки данных
    const {getOwnerById} = useOwnerService();

    useEffect(() => {
        const loadUserData = async () => {
            try {
                const ownerData = await getOwnerById(ownerId);
                setOwner(ownerData);
            } catch (error) {
                console.error('Error loading user data:', error);
            } finally {
                setLoading(false); // Устанавливаем состояние загрузки в false после завершения запроса
            }
        };

        loadUserData();
    }, [ownerId]);

    return (
        <div align='center'>
            {loading ? ( // Проверяем состояние загрузки перед рендерингом
                <p>Loading...</p>
            ) : owner ? (
                <>
                    <h2>Owner Profile</h2>
                    <p><strong>Name:</strong> {owner.name || 'Unknown'}</p>
                    <p><strong>Birth Date:</strong> {owner.birthDate || 'Unknown'}</p>
                    <Link to={`/editProfile/${ownerId}`}>Edit Profile</Link>
                </>
            ) : (
                <p>Owner not found</p>
            )}
        </div>
    );
};

export default OwnerPage;
