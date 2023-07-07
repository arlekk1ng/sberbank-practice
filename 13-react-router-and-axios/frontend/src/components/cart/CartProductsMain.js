import React, {useEffect} from 'react';
import CartProductsList from "./CartProductsList";
import {useDispatch, useSelector} from "react-redux";
import userService from "../../services/userService";

const CartProductsMain = () => {
  const user = useSelector(state => state.auth.user);
  const dispatch = useDispatch();

  useEffect(() => {
    userService.getProductsFromUserCart(user.id, dispatch);
  }, [])
  
  return (
    <div>
      <CartProductsList />
    </div>
  );
};

export default CartProductsMain;