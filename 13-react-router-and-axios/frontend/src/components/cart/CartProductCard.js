import {CloseOutlined, MinusOutlined, PlusOutlined} from '@ant-design/icons';
import {Button, Card} from 'antd';
import userService from "../../services/userService";
import {useDispatch, useSelector} from "react-redux";

const CartProductCard = ({cartProduct}) => {
  // const user = useSelector(state => state.user.value);
  const user = useSelector(state => state.auth.user);
  const cartProducts = useSelector(state => state.cartProducts.value);

  const dispatch = useDispatch();

  const increaseProductCountByOne = () => {
    const newCartProduct = {
      productCountInCart: cartProduct.productCountInCart + 1
    };
    userService.changeProductCountInCart(
      user.id, cartProduct.product.id, newCartProduct, dispatch, cartProducts);
  };

  const reduceProductCountByOne = () => {
    const newCartProduct = {
      productCountInCart: cartProduct.productCountInCart - 1
    };
    userService.changeProductCountInCart(
      user.id, cartProduct.product.id, newCartProduct, dispatch, cartProducts);
  };

  return (
    <Card
      style={{
        width: 300,
      }}
      cover={
        <img
          alt="example"
          src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
        />
      }
      actions={[
        <Button
          icon={<PlusOutlined />}
          type="text"
          onClick={increaseProductCountByOne}
        />,
        <Button
          icon={<MinusOutlined />}
          type="text"
          onClick={reduceProductCountByOne}
        />,
      ]}

      extra={
        <Button
          icon={<CloseOutlined />}
          onClick={() => userService.deleteProductInUserCart(user.id, cartProduct.product.id, dispatch)}
        />
      }
      hoverable={true}
    >

      <div>
        <p><b>{cartProduct.product.name}</b></p>
        <p>{cartProduct.product.price} руб.</p>
        <p>{cartProduct.productCountInCart} шт.</p>
      </div>

    </Card>
  );
};
export default CartProductCard;