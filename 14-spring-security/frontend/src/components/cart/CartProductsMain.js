import React, {useEffect} from 'react';
import CartProductsList from "./CartProductsList";
import {useDispatch, useSelector} from "react-redux";
import userService from "../../services/userService";
import {Button, Divider} from "antd";
import StoreProductsSearch from "../store/StoreProductsSearch";

const CartProductsMain = () => {
  const user = useSelector(state => state.auth.user);
  const dispatch = useDispatch();

  useEffect(() => {
    userService.getProductsFromUserCart(user.id, dispatch);
  }, [])

  const cleanCart = () => {
    userService.deleteAllProductInUserCart(user.id, dispatch);
  }
  
  return (
    <div>
      <div>
        <Button onClick={cleanCart}>
          Очистить корзину
        </Button>

      </div>
      <Divider />
      <CartProductsList />
    </div>
  );
};

export default CartProductsMain;