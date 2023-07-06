import {createSlice} from '@reduxjs/toolkit'

export const storeProductsSlice = createSlice({
  name: 'storeProducts',
  initialState: {
    value: []
  },
  reducers: {
    set: (state, action) => {
      state.value = action.payload;
    },
  }
});

// Action creators are generated for each case reducer function
export const { set } = storeProductsSlice.actions

export default storeProductsSlice.reducer