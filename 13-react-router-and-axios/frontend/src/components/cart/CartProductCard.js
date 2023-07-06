import {Button, Card} from 'antd';
import CartProductEditForm from "./CartProductEditForm";
import {useDispatch, useSelector} from "react-redux";
import clientService from "../../services/clientService";
import {CloseOutlined} from "@ant-design/icons";


const CartProductCard = ({cartProduct}) => {
  const client = useSelector(state => state.client.value);
  const dispatch = useDispatch();

  const deleteProductInCart = () => {
    clientService.deleteProductInCart(client.id, cartProduct.product.id, dispatch)
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
          icon={<CloseOutlined />}
          type="text"
          onClick={deleteProductInCart}
        />,
        <CartProductEditForm cartProduct={cartProduct}/>,
      ]}
    >

      <div>
        <p><b>{cartProduct.product.name}</b></p>
        <p>{cartProduct.product.price} руб.</p>
        <p>{cartProduct.productCount} шт.</p>
      </div>

    </Card>
  );
};
export default CartProductCard;