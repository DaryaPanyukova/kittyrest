import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Colors } from '../../commons/CatColor';
import useCatService from '../../services/CatService';

const UpdateCatPage = () => {
    const navigate = useNavigate();
    const {getCat, updateCat} = useCatService();
    const {id} = useParams();

    const [cat, setCat] = useState(null);
    const [name, setName] = useState('');
    const [breed, setBreed] = useState('');
    const [color, setColor] = useState('');
    const [birthDate, setBirthDate] = useState('');
    const [image, setImage] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchCat = async () => {
            console.log("Fetching cat with id:", id);
            try {
                const catData = await getCat(id);
                console.log("Fetched cat data:", catData);
                setCat(catData);
                setName(catData.name);
                setBreed(catData.breed);
                setColor(catData.color);
                setBirthDate(catData.birthDate);
            } catch (error) {
                console.error("Failed to fetch cat data:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchCat();
    }, [id]);

    if (loading) {
        return <div>Loading...</div>;
    }

    const handleSubmit = async (event) => {
        event.preventDefault();

        const formData = new FormData();
        formData.append('id', cat.id);
        formData.append('name', name);
        formData.append('breed', breed);
        formData.append('color', color);
        formData.append('birthDate', birthDate);
        formData.append('image', null);

        if (image) {
            console.log("i got a picture");
            const arrayBuffer = await new Response(new Blob([image])).arrayBuffer();
            formData.append('image', new Uint8Array(arrayBuffer));
        }

        await updateCat(formData);
        navigate('/cats');
    };

    return (
        <div align={'center'}>
            <h2>Update Cat</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Name:</label>
                    <input type="text" className="form-control" value={name} onChange={(e) => setName(e.target.value)}/>
                </div>
                <div className="form-group">
                    <label>Breed:</label>
                    <input type="text" className="form-control" value={breed}
                           onChange={(e) => setBreed(e.target.value)}/>
                </div>
                <div className="form-group">
                    <label>Color:</label>
                    <select className="form-control" value={color} onChange={(e) => setColor(e.target.value)}>
                        {Object.entries(Colors).map(([key, value]) => (
                            <option key={value} value={value}>
                                {key}
                            </option>
                        ))}
                    </select>
                </div>
                <div className="form-group">
                    <label>Birth Date:</label>
                    <input type="date" className="form-control" value={birthDate}
                           onChange={(e) => setBirthDate(e.target.value)}/>
                </div>
                <div className="form-group">
                    <label>Photo:</label>
                    <input type="file" className="form-control-file" onChange={(e) => {
                        const file = e.target.files[0];
                        const reader = new FileReader();
                        reader.onloadend = () => {
                            setImage(new Blob([reader.result], {type: file.type}));
                        };
                        reader.readAsArrayBuffer(file);
                    }}/>
                </div>
                <button type="submit" className="btn btn-primary">Save</button>
                <Link to="/cats" className="btn btn-secondary">Cancel</Link>
            </form>
        </div>
    );
};

export default UpdateCatPage;