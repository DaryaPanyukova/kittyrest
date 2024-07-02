import React, {useContext, useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import UserService from "../../services/UserService";
import {AuthContext} from "../../utils/AuthContext";

const LoginPage = () => {
    const {login} = UserService();
    const navigate = useNavigate();
    const {userRole, ownerId} = useContext(AuthContext);


    const [loginData, setLoginData] = useState({
        username: '',
        password: ''
    });


    const handleLoginChange = (e) => {
        const {name, value} = e.target;
        setLoginData({...loginData, [name]: value});
    };

    const handleLoginSubmit = async (e) => {
        e.preventDefault();
        try {
            await login(loginData);
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        if (userRole && userRole !== 'guest' && ownerId) {
            console.log('navigating, role:', userRole);
            navigate(`/owner/${ownerId}`);
        }
    }, [userRole, ownerId, navigate]);

    return (
        <div align='center'>
            <h2>Sign In</h2>
            <form onSubmit={handleLoginSubmit}>
                <input type="text" name="username" placeholder="Username" value={loginData.username}
                       onChange={handleLoginChange} required/>
                <input type="password" name="password" placeholder="Password" value={loginData.password}
                       onChange={handleLoginChange} required/>
                <button type="submit">Sign In</button>
            </form>
        </div>
    );
};

export default LoginPage;
