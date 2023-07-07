import {createSlice} from '@reduxjs/toolkit'

export const userSlice = createSlice({
  name: 'client',
  initialState: {
    value: {
      id: 1,
      name: "Леонардо",
      email: "d1ck@prio.hollywood",
    },
  },
  reducers: {
    setCurrentUser: (state, action) => {
      state.value = action.payload;
    },
  }
});

// Action creators are generated for each case reducer function
export const { setCurrentUser } = userSlice.actions

export default userSlice.reducer