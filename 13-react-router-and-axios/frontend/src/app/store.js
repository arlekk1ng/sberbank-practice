import { configureStore } from '@reduxjs/toolkit'

import storeProductsReducer from '../slices/storeProductsSlice'
import cartProductsReducer from '../slices/cartProductsSlice'
import userReducer from '../slices/userSlice.js'
import authReducer from '../slices/authSlice.js'

export default configureStore({
  reducer: {
    storeProducts: storeProductsReducer,
    cartProducts: cartProductsReducer,
    // user: userReducer,
    auth: authReducer,
  }
})