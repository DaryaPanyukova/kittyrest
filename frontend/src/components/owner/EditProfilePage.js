import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from 'react-router-dom';
import OwnerService from '../../services/OwnerService';

const CreateOwnerPage = () => {
    const {getOwnerById, updateOwner} = OwnerService();
    const navigate = useNavigate();
    const {ownerId} = useParams();

    const [name, setName] = useState('');
    const [birthDate, setBirthDate] = useState('');

    useEffect(() => {
        const fetchOwnerData = async () => {
            try {
                const owner = await getOwnerById(ownerId);
                setName(owner.name);
                setBirthDate(owner.birthDate);
            } catch (error) {
                console.error('Error fetching owner data:', error);
            }
        };

        fetchOwnerData();
    }, [ownerId]);

    const saveOwner = async (e) => {
        e.preventDefault();

        const ownerData = {
            id: ownerId,
            name,
            birthDate
        };

        await updateOwner(ownerData);
        navigate(`/owner/${ownerId}`);
    };

    return (
        <div align={'center'}>
            <h2>Edit Profile</h2>
            <form onSubmit={saveOwner}>
                <div className="form-group">
                    <label>Name:</label>
                    <input type="text" className="form-control" placeholder={name} value={name}
                           onChange={(e) => setName(e.target.value)}/>
                </div>
                <div className="form-group">
                    <label>Birth Date:</label>
                    <input type="date" className="form-control" placeholder={birthDate} value={birthDate}
                           onChange={(e) => setBirthDate(e.target.value)}/>
                </div>
                <button type="submit" className="btn btn-primary" onClick={saveOwner}>Save</button>
                <button type="button" className="btn btn-primary" onClick={() => navigate(`/owner/${ownerId}`)}>Cancel
                </button>
            </form>
        </div>
    );
};

export default CreateOwnerPage;
