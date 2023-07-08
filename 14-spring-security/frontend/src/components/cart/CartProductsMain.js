import React, {useEffect} from 'react';
import CartProductsList from "./CartProductsList";
import {useDispatch, useSelector} from "react-redux";
import userService from "../../services/userService";

const CartProductsMain = () => {
  const stateAuth = useSelector(state => state.auth);
  const dispatch = useDispatch();

  useEffect(() => {
    if (stateAuth.isLoggedIn) {
      userService.getProductsFromUserCart(stateAuth.user.id, dispatch);
    }
  }, [])
  
  return (
    <div>
      <CartProductsList />
    </div>
  );
};

export default CartProductsMain;