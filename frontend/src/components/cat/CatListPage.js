import React, {useEffect, useState} from 'react';
import {Link} from 'react-router-dom';
import CatService from '../../services/CatService';

const CatListPage = () => {
    console.log('CatList rendered');

    const { getCatList, deleteCat} = CatService();
    const [cats, setCats] = useState([]);

    useEffect(() => {
        const fetchOwners = async () => {
            console.log("fetchOwners")
            const data = await getCatList();
            setCats(data);
        };
        fetchOwners();
    }, []);

    const handleDeleteCat= async (catId) => {
        await deleteCat(catId);
        const updatedOwners = await getCatList();
        setCats(updatedOwners);
    };

    const getImageUrl = (img) => {
        if (!img) {
            return null;
        }
        return `data:image/png;base64,${img}`;
    };

    return (
        <div align='center'>
            <h2>Cats</h2>
            <Link to="/add-cat" className="btn btn-primary">Add Cat</Link>
            <br/>
            <br/>
            {cats.length === 0 && <p>No cats found.</p>}
            <table className="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Breed</th>
                    <th>Color</th>
                    <th>Birth Date</th>
                    <th>Photo</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {cats.map(cat => (
                    <tr key={cat.id}>
                        <td>{cat.id}</td>
                        <td>{cat.name}</td>
                        <td>{cat.breed}</td>
                        <td>{cat.color}</td>
                        <td>{cat.birthDate}</td>
                        <td><img src={getImageUrl(cat.image)} alt="Cat" width="100"/></td>
                        <td>
                            <button
                                className="btn btn-danger"
                                onClick={() => {
                                    handleDeleteCat(cat.id)}
                                }
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

export default CatListPage;
