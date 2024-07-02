/*
import React, {useContext} from 'react';
import {Route, Navigate} from 'react-router-dom';
import {AuthContext} from './AuthContext';

export const AdminOnlyRoute = ({element: Component, ...rest}) => {
    const {userRole} = useContext(AuthContext);

    if (userRole === "guest") {
        return <Navigate to="/login"/>;
    }

    if (userRole === "user") {
        return <Navigate to="/"/>;
    }

    return <Route {...rest} element={Component}/>;
};

export const AuthorisedOnlyRoute = ({element: Component, ...rest}) => {
    const {userRole} = useContext(AuthContext);

    if (userRole === "guest") {
        return <Navigate to="/login"/>;
    }

    return <Route {...rest} element={Component}/>;
};

*/
