import '../../styles.css';
import React, {useContext} from 'react';
import {Link, useNavigate} from 'react-router-dom';
import {AuthContext} from '../../utils/AuthContext';

function Menu() {
    const {reset, userRole, ownerId} = useContext(AuthContext);
    const navigate = useNavigate();

    const handleLogout = () => {
        reset();
        navigate('/');
    };

    return (
        <nav className="sidebar">
            <ul>
                <li><Link to="/">Kittyrest</Link></li>
                {userRole === 'admin' && (
                    <>
                        <li><Link to={`/owner/${ownerId}`}>My page</Link></li>
                        <li><Link to="/cats">Cats</Link></li>
                        <li><Link to="/owners">Owners</Link></li>
                    </>
                )}
                {(userRole === 'user') && (
                    <>
                        <li><Link to={`/owner/${ownerId}`}>My page</Link></li>
                        <li><Link to="/cats">My Cats</Link></li>
                    </>
                )}
                {userRole === 'guest' && (
                    <>
                        <li><Link to="/login">Login</Link></li>
                        <li><Link to="/signUp">Sign Up</Link></li>
                    </>
                )}
            </ul>
            {(userRole === 'admin' || userRole === 'user') && (
                <button onClick={handleLogout} className="logout-button">
                    Logout
                </button>
            )}
        </nav>
    );
}

export default Menu;
