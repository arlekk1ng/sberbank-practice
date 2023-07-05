import { createSlice } from '@reduxjs/toolkit'

export const productSlice = createSlice({
  name: 'product',
  initialState: {
    value: {
      name: "Яблоко",
      price: 50
    }
  },
  reducers: {
    updateProduct: (state, action) => {
      state.value = action.payload;
    }
  }
})

// Action creators are generated for each case reducer function
export const { updateProduct } = productSlice.actions

export default productSlice.reducer