import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import useOwnerService from '../../services/OwnerService';

const OwnersListPage = () => {
    console.log('OwnerListComponent rendered');
    const { getOwners, deleteOwner } = useOwnerService();
    const [owners, setOwners] = useState([]);

    useEffect(() => {
        const fetchOwners = async () => {
            console.log("fetchOwners")
            const data = await getOwners();
            setOwners(data);
        };
        fetchOwners();
    }, []);

    const handleDeleteOwner = async (ownerId) => {
        await deleteOwner(ownerId);
        // После успешного удаления, обновляем список владельцев
        const updatedOwners = await getOwners();
        setOwners(updatedOwners);
    };

    return (
        <div align={'center'}>
            <h2>Owners</h2>
            <Link to="/add-owner" className="btn btn-primary">Add Owner</Link>
            <br/>
            <br/>
            {owners.length === 0 && <p>No owners found.</p>}
            <table className="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Birth Date</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {owners.map(owner => (
                    <tr key={owner.id}>
                        <td>{owner.id}</td>
                        <td>{owner.name}</td>
                        <td>{owner.birthDate}</td>
                        <td>
                            <button
                                className="btn btn-danger"
                                onClick={() => handleDeleteOwner(owner.id)}
                            >
                                Delete
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default OwnersListPage;
