import { configureStore } from '@reduxjs/toolkit'
import productReducer from '../slices/ProductSlice.js'

export default configureStore({
  reducer: {
    product: productReducer
  }
})