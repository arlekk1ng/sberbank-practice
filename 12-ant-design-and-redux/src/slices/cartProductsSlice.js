import {createSlice} from '@reduxjs/toolkit'
import {productSlice} from "./productSlice";

export const cartProductsSlice = createSlice({
  name: 'cartProducts',
  initialState: {
    value: []
  },
  reducers: {
    updateProduct: (state, action) => {
      const product = action.payload;
      const updState = [];

      for (let i = 0; i < state.value.length; i++) {
        if (state.value[i].id === product.id) {
          updState.push(product);
        } else {
          updState.push(state.value[i]);
        }
      }

      state.value = updState;
    },
    addProduct: (state, action) => {
      let product = action.payload;
      const updState = [];
      let productNotPresent = true;

      for (let i = 0; i < state.value.length; i++) {
        if (state.value[i].id === product.id) {
          productNotPresent = false;

          product = {
            count: state.value[i].count + 1,
            ...product
          }
          updState.push(product);
        } else {
          updState.push(state.value[i]);
        }
      }

      if (productNotPresent) {
        product = {
          count: 1,
          ...product
        };
        state.value = [product, ...state.value];
      } else {
        state.value = updState;
      }
    },
    deleteProduct: (state, action) => {
      state.value = state.value.filter(product => product.id !== action.payload.id);
    }
  }
})

// Action creators are generated for each case reducer function
export const { updateProduct, addProduct, deleteProduct } = cartProductsSlice.actions

export default cartProductsSlice.reducer