import './App.css';
import {useDispatch, useSelector} from "react-redux";
import React, {useEffect} from "react";
import productService from "./services/productService";
import userService from "./services/userService";
import MainLayout from "./components/MainLayout";

function App() {
  const user = useSelector(state => state.user.value);

  const dispatch = useDispatch();

  useEffect(() => {
    productService.getStoreProducts(dispatch);
    // userService.getProductsFromUserCart(user.id, dispatch);
  }, [])

  return (
    <div>
      <MainLayout />
    </div>
  );
}

export default App;
