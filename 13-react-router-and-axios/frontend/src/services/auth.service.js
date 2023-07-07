import axios from "axios";
import {API_URL} from "./constants";

const register = (registration) => {
    const {username, email, password} = registration;
    return axios.post(API_URL + "/api/auth/signup", {
        username,
        email,
        password,
    });
};

const login = (login) => {
    const {username, password} = login;

    return axios
        .post(API_URL + "/api/auth/signin", {
            username,
            password,
        })
        .then((response) => {
            if (response.data.accessToken) {
                localStorage.setItem("user", JSON.stringify(response.data));
            }

            return response.data;
        });
};

const logout = () => {
    localStorage.removeItem("user");

    console.log("logout was successful");
};

const authService = {
    register,
    login,
    logout,
};

export default authService;
