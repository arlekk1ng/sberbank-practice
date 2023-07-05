import {createSlice} from '@reduxjs/toolkit'

export const storeProductsSlice = createSlice({
  name: 'storeProducts',
  initialState: {
    value: [
      {
        id: 1,
        name: "Яблоко",
        price: 35
      },
      {
        id: 2,
        name: "Груша",
        price: 70
      },
      {
        id: 3,
        name: "Апельсин",
        price: 105
      },
      {
        id: 4,
        name: "Клубника",
        price: 120
      },
      {
        id: 5,
        name: "Банан",
        price: 80
      },
    ],
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
      const product = action.payload;
      product.id = Math.floor(Math.random() * 1_000_000);
      state.value = [product, ...state.value];
    }
  }
})

// Action creators are generated for each case reducer function
export const { updateProduct, addProduct } = storeProductsSlice.actions

export default storeProductsSlice.reducer