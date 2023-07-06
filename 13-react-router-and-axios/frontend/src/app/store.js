import { configureStore } from '@reduxjs/toolkit'

import storeProductsReducer from '../slices/storeProductsSlice'
import cartProductsReducer from '../slices/cartProductsSlice'
import clientReducer from '../slices/clientSlice.js'

export default configureStore({
  reducer: {
    storeProducts: storeProductsReducer,
    cartProducts: cartProductsReducer,
    client: clientReducer,
  }
})