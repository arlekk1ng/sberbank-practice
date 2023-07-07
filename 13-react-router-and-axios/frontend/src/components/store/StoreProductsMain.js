import React, {useEffect} from 'react';
import {Divider} from "antd";
import StoreProductsList from "./StoreProductsList";
import StoreProductCreateForm from "./StoreProductCreateForm";
import {useDispatch} from "react-redux";
import productService from "../../services/productService";

const StoreProductsMain = () => {
  const dispatch = useDispatch();

  useEffect(() => {
    productService.getStoreProducts(dispatch);
  }, [])

  return (
    <div>
      <StoreProductCreateForm />
      <Divider />
      <StoreProductsList />
    </div>
  );
};

export default StoreProductsMain;