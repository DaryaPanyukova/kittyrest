import React, {useContext, useState} from 'react';
import {Link, useNavigate} from 'react-router-dom';
import useCatService from '../../services/CatService';
import {Colors} from "../../commons/CatColor";
import {AuthContext} from "../../utils/AuthContext";

const CreateCatPage = () => {
    const {ownerId} = useContext(AuthContext);
    const {createCat} = useCatService();
    const navigate = useNavigate();


    const [name, setName] = useState('');
    const [breed, setBreed] = useState('');
    const [color, setColor] = useState(Object.keys(Colors)[0]);
    const [birthDate, setBirthDate] = useState('');
    const [image, setImage] = useState('');


    const saveCat = async (e) => {
        e.preventDefault();
        const catData = {
            name: name,
            birthDate: birthDate,
            breed: breed,
            color: color,
            image: null,
            owner: {
                id: ownerId,
            }
        };

        if (image) {
            console.log("i got a picture");
            const arrayBuffer = await new Response(new Blob([image])).arrayBuffer();
            catData.append('image', new Uint8Array(arrayBuffer));
        }
        console.log(catData);
        console.log(catData.color);
        await createCat(catData);
        navigate('/cats');
    };



    return (
        <div align={'center'}>
            <h2>Add Cat</h2>
            <form onSubmit={createCat}>
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
                <button type="submit" className="btn btn-primary" onClick={saveCat}>Save</button>
                <button type="button" className="btn btn-primary" onClick={() => navigate(`/owner/${ownerId}`)}>Cancel</button>
            </form>
        </div>
    );
};
export default CreateCatPage;