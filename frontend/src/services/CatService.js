import {useContext} from 'react';
import axios from 'axios';
import {AuthContext} from "../utils/AuthContext";

const CAT_API_BASE_URL = "http://localhost:8080/api/cats";

const CatService = () => {
    const {userRole, token, ownerId} = useContext(AuthContext);

    const getCatById = async (catId) => {
        const response = await axios.get(CAT_API_BASE_URL + '/' + catId, {
            headers: {
                "Cache-Control": "no-cache",
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token,
            },
            withCredentials: true
        });
        return response.data;
    };

    const getCatList = async () => {

        if (userRole === "admin") {
            const response = await axios.get(CAT_API_BASE_URL, {
                headers: {
                    "Cache-Control": "no-cache",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + token,
                },
                withCredentials: true
            });
            return response.data;
        } else if (userRole === "user") {
            const response = await axios.get(CAT_API_BASE_URL + "/owner/" + ownerId, {
                headers: {
                    "Cache-Control": "no-cache",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + token,
                },
                withCredentials: true
            });
            return response.data;
        }

        return null;
    }

    const createCat = async (cat) => {
        const response = await axios.post(CAT_API_BASE_URL, cat, {
            headers: {
                "Cache-Control": "no-cache",
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token,
            },
            withCredentials: true
        });
        return response.data;
    };
// todo: status 403
    const updateCat = async (cat) => {
        const response = await axios.put(CAT_API_BASE_URL + "/" + cat.id, cat, {
            headers: {
                "Cache-Control": "no-cache",
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token,
            },
            withCredentials: true
        });
        return response.data;
    };

    const deleteCat = async (catId) => {
        const response = await axios.delete(CAT_API_BASE_URL + '/' + catId, {
            headers: {
                "Cache-Control": "no-cache",
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token,
            },
            withCredentials: true
        });
        return response.data;
    };

    return {getCatById, getCatList, createCat, updateCat, deleteCat};
};


export default CatService;
