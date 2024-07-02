import axios from 'axios';
import {AuthContext} from "../utils/AuthContext";
import {useContext} from "react";

const USER_API_BASE_URL = "http://localhost:8080/api/auth";


const UserService = () => {
    const {set} = useContext(AuthContext);
    const login = async (loginData) => {
        const response = await axios.post(`${USER_API_BASE_URL}/login`, loginData, {
            headers: {
                "Cache-Control": "no-cache",
                "Content-Type": "application/json",
            },
            withCredentials: true
        });

        const role = await getCurrentRole(response.data);
        const user = await getCurrentUser(response.data);
        set(role, response.data, user.owner.id);
        console.log("login in UserService" + user.owner.id);
        return response.data;
    };

    const signup = async (signupData) => {
        const response = await axios.post(`${USER_API_BASE_URL}/signup`, signupData, {
            headers: {
                "Cache-Control": "no-cache",
                "Content-Type": "application/json",
            },
            withCredentials: true
        });

        console.log(response.data);
        return response.data;
    };

    const getCurrentRole = async (token) => {
        const response = await axios.get(`${USER_API_BASE_URL}/current-user-role`, {
            headers: {
                "Cache-Control": "no-cache",
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token,
            },
            withCredentials: true
        });
        console.log("getCurrentRole token: " + token);
        console.log("getCurrentRole role: " + response.data);
        return response.data;
    };

    const getCurrentUser = async (token) => {
        const response = await axios.get(`${USER_API_BASE_URL}/current-user`, {
            headers: {
                "Cache-Control": "no-cache",
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token,
            },
            withCredentials: true
        });
        console.log(response.data);
        return response.data;
    };

    return {login, signup, getCurrentRole, getCurrentUser};
}

export default UserService;