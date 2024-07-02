import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import UserService from "../../services/UserService";

const SignUpPage = () => {
    const {signup} = UserService();
    const navigate = useNavigate();

    const [signUpData, setSignUpData] = useState({
        username: '',
        password: '',
        confirmPassword: ''
    });

    const [error, setError] = useState('');

    const handleSignUpChange = (e) => {
        const {name, value} = e.target;
        setSignUpData({...signUpData, [name]: value});
    };

    const handleSignUpSubmit = async (e) => {
        e.preventDefault();
        if (signUpData.password !== signUpData.confirmPassword) {
            setError('Passwords do not match. Please try again.');
            return;
        }

        try {
            const response = await signup({
                username: signUpData.username,
                password: signUpData.password,
                roles: "ROLE_USER"
            });

            navigate('/login');
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div align='center'>
            <h2>Sign Up</h2>
            {error && <p style={{color: 'red'}}>{error}</p>}
            <form onSubmit={handleSignUpSubmit}>
                <input type="text" name="username" placeholder="Username" value={signUpData.username}
                       onChange={handleSignUpChange} required/>
                <input type="password" name="password" placeholder="Password" value={signUpData.password}
                       onChange={handleSignUpChange} required/>
                <input type="password" name="confirmPassword" placeholder="Confirm Password"
                       value={signUpData.confirmPassword} onChange={handleSignUpChange} required/>
                <button type="submit">Sign Up</button>
            </form>
        </div>
    );
};

export default SignUpPage;
