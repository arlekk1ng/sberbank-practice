import {Tabs} from 'antd';
import StoreProductsMain from "./store/StoreProductsMain";
import CartProductsMain from "./cart/CartProductsMain";
import {useDispatch, useSelector} from "react-redux";
import {useEffect} from "react";
import productService from "../services/productService";
import clientService from "../services/clientService";

const ProductsCartTabs = () => {
  const dispatch = useDispatch();
  const client = useSelector(state => state.client.value)

  useEffect(() => {
    productService.getProducts(dispatch);

    if (client.id === undefined) {
      clientService.addClient({
        name: "Леонардо Ди Каприо",
        email: "d1Cap@hollywood.com",
        login: "sup3r_5t4r",
        password: "1223334444",
      }, dispatch);
    }

    clientService.getCartProducts(client.id, dispatch);
  }, []);

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