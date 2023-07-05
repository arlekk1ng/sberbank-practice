import {Tabs} from 'antd';
import StoreProductsMain from "./store/StoreProductsMain";
import CartProductsMain from "./cart/CartProductsMain";

const ProductsCartTabs = () => {
  return (
    <Tabs
      type="card"
      items={
        [
          {
            label: `Продукты`,
            key: 1,
            children: <StoreProductsMain />,
          },
          {
            label: `Корзина`,
            key: 2,
            children: <CartProductsMain />,
          }
        ]
      }
    />
  );
};
export default ProductsCartTabs;