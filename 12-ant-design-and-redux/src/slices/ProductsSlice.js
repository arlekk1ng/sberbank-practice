import { createSlice, current } from '@reduxjs/toolkit'

export const productsSlice = createSlice({
  name: 'products',
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
        price: 80
      },
      {
        id: 3,
        name: "Апельсин",
        price: 130
      },
      {
        id: 4,
        name: "Черешня",
        price: 120
      },
    ],
  },
  reducers: {
    updateProduct: (state, action) => {
      let updState = [];
      for (let i = 0; i < state.value.length; i++) {
        if (state.value[i].id === action.payload.id) {
          updState.push(action.payload);
        } else {
          updState.push(state.value[i]);
        }
      }

      state.value = updState;
    }
  }
})

// Action creators are generated for each case reducer function
export const { updateProduct } = productsSlice.actions

export default productsSlice.reducer