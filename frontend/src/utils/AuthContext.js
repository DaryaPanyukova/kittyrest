import React, {createContext, useState} from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({children}) => {
    const [userRole, setUserRole] = useState('guest');
    const [ownerId, setOwnerId] = useState(-1);
    const [token, setToken] = useState('');

    const set = (newRole, newToken, newOwnerId)=> {
        setUserRole(newRole);
        setToken(newToken);
        setOwnerId(newOwnerId);
        console.log("set in AuthContext " + userRole + " " + ownerId);
    }

    const reset = async() => {
        setUserRole('guest');
        setToken('');
    }

    return (
        <AuthContext.Provider value={{
            userRole,
            token,
            ownerId,
            set,
            reset
        }}>
            {children}
        </AuthContext.Provider>
    );
};
