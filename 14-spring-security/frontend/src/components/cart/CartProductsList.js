import {List} from 'antd';
import {useSelector} from "react-redux";
import CartProductCard from "./CartProductCard";

const CartProductsList = () => {
  const cartProducts = useSelector(state => state.cartProducts.value)

  return (
    <List
      grid={{
        gutter: 16,
      }}
      dataSource={cartProducts}
      renderItem={(item) => {
        return (
          <List.Item>
            <CartProductCard cartProduct={item}/>
          </List.Item>
        )
      }}
    />
  );
};
export default CartProductsList;