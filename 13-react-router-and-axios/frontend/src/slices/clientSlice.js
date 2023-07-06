import {createSlice} from '@reduxjs/toolkit'

export const clientSlice = createSlice({
  name: 'client',
  initialState: {
    value: {

    },
  },
  reducers: {
    setClient: (state, action) => {
      state.value = action.payload;

      console.log("clientSlice, setClient, payload", action.payload);
      console.log("clientSlice, setClient, value", state.value);
    },
  }
});

// Action creators are generated for each case reducer function
export const { setClient } = clientSlice.actions

export default clientSlice.reducer