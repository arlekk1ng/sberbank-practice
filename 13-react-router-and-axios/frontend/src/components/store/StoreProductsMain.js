import React from 'react';
import {Divider} from "antd";
import StoreProductsList from "./StoreProductsList";
import StoreProductCreateForm from "./StoreProductCreateForm";

const StoreProductsMain = () => {

  return (
    <div>
      <StoreProductCreateForm />
      <Divider />
      <StoreProductsList />
    </div>
  );
};

export default StoreProductsMain;