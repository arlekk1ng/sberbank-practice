import {createSlice} from '@reduxjs/toolkit'

export const cartProductsSlice = createSlice({
  name: 'cartProducts',
  initialState: {
    value: []
  },
  reducers: {
    set: (state, action) => {
      state.value = action.payload;
    },
  }
})

// Action creators are generated for each case reducer function
export const { set } = cartProductsSlice.actions

export default cartProductsSlice.reducer