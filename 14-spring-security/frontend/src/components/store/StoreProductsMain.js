import React, {useEffect} from 'react';
import {Divider} from "antd";
import StoreProductsList from "./StoreProductsList";
import StoreProductCreateForm from "./StoreProductCreateForm";
import {useDispatch, useSelector} from "react-redux";
import productService from "../../services/productService";
import StoreProductsSearch from "./StoreProductsSearch";

const StoreProductsMain = () => {
  const dispatch = useDispatch();

  useEffect(() => {
    productService.getStoreProducts(dispatch);
  }, [])

  return (
    <div>
      <div
        style={{
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'space-between'
        }}
      >
        <StoreProductCreateForm />
        <StoreProductsSearch />
      </div>

      <Divider />
      <StoreProductsList />
    </div>
  );
};

export default StoreProductsMain;