import {configureStore} from '@reduxjs/toolkit'

import storeProductsReducer from '../slices/storeProductsSlice'
import cartProductsReducer from '../slices/cartProductsSlice'
import authReducer from '../slices/authSlice.js'

export default configureStore({
  reducer: {
    storeProducts: storeProductsReducer,
    cartProducts: cartProductsReducer,
    auth: authReducer,
  }
})