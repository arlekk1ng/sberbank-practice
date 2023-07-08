import {createSlice} from '@reduxjs/toolkit'

const user = JSON.parse(localStorage.getItem("user"));

const defaultUser = {
    id: 0,
    username: "DEFAULT",
}

const initialState = user
    ? {isLoggedIn: true, user: user}
    : {isLoggedIn: false, user: defaultUser};

export const authSlice = createSlice({
    name: 'auth',
    initialState: initialState,
    reducers: {
        login: (state, action) => {
            state.isLoggedIn = true;
            state.user = action.payload;
        },
        logout: (state, action) => {
            state.isLoggedIn = false;
            state.user = defaultUser;
        }
    },
})

// Action creators are generated for each case reducer function
export const {login, logout} = authSlice.actions

export default authSlice.reducer
