import {createSlice} from '@reduxjs/toolkit'

export const storeProductsSlice = createSlice({
  name: 'storeProducts',
  initialState: {
    value: []
  },
  reducers: {
    setStoreProducts: (state, action) => {
      state.value = action.payload;
    },
  }
});

// Action creators are generated for each case reducer function
export const { setStoreProducts } = storeProductsSlice.actions

export default storeProductsSlice.reducer