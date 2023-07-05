import { configureStore } from '@reduxjs/toolkit'

import productReducer from '../slices/ProductSlice.js'
import productsReducer from '../slices/ProductsSlice.js'

export default configureStore({
  reducer: {
    product: productReducer,
    products: productsReducer
  }
})