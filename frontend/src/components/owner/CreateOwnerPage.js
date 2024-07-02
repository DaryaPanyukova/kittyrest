import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import useOwnerService from '../../services/OwnerService';

const CreateOwnerPage = () => {
    const { createOwner } = useOwnerService();
    const navigate = useNavigate();

    const [name, setName] = useState('');
    const [birthDate, setBirthDate] = useState('');

    const saveOwner = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append('name', name);
        formData.append('birthDate', birthDate);

        await createOwner(formData);
        navigate('/add-owner');
    };

    return (
        <div align={'center'}>
            <h2>Add Owner</h2>
            <form onSubmit={saveOwner}>
                <div className="form-group">
                    <label>Name:</label>
                    <input type="text" className="form-control" value={name} onChange={(e) => setName(e.target.value)} />
                </div>
                <div className="form-group">
                    <label>Birth Date:</label>
                    <input type="date" className="form-control" value={birthDate} onChange={(e) => setBirthDate(e.target.value)} />
                </div>
                <button type="submit" className="btn btn-primary">Save</button>
                <Link to="/owners" className="btn btn-secondary">Cancel</Link>
            </form>
        </div>
    );
};

export default CreateOwnerPage;
