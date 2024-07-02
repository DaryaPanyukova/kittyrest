import {useContext} from 'react';
import axios from 'axios';
import {AuthContext} from "../utils/AuthContext";

const OWNER_API_BASE_URL = "http://localhost:8080/api/owners";

const OwnerService = () => {
    const {token} = useContext(AuthContext);

    const getOwners = async () => {
        const res = await axios.get(OWNER_API_BASE_URL, {
            headers: {
                "Cache-Control": "no-cache",
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token,
            },
            withCredentials: true
        });
        console.log(res.data);
        return res.data;
    };

    const createOwner = async (owner) => {
        console.log(JSON.stringify(owner));
        const res = await axios.post(OWNER_API_BASE_URL, owner, {
            headers: {
                "Cache-Control": "no-cache",
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token,
            },
            withCredentials: true
        });
    };

    const updateOwner = async (owner) => {
        console.log(JSON.stringify(owner));
        const res = await axios.put(OWNER_API_BASE_URL + "/" + owner.id, owner, {
            headers: {
                "Cache-Control": "no-cache",
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token,
            },
            withCredentials: true
        });
    };

    const deleteOwner = async (ownerId) => {
        const res = await axios.delete(OWNER_API_BASE_URL + '/' + ownerId, {
            headers: {
                "Cache-Control": "no-cache",
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token,
            },
            withCredentials: true
        });
    };

    const getOwnerById = async (ownerId) => {
        const response = await axios.get(OWNER_API_BASE_URL + '/' + ownerId, {
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


    return {getOwners, createOwner, deleteOwner, getOwnerById, updateOwner};
};

export default OwnerService;
