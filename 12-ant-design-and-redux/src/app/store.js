import { configureStore } from '@reduxjs/toolkit'

import productReducer from '../slices/productSlice.js'
import storeProductsReducer from '../slices/storeProductsSlice.js'
import cartProductsReducer from '../slices/cartProductsSlice.js'

export default configureStore({
  reducer: {
    product: productReducer,
    storeProducts: storeProductsReducer,
    cartProducts: cartProductsReducer
  }
})