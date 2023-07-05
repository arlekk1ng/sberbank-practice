import {Button, Card} from 'antd';
import CartProductEditForm from "./CartProductEditForm";
import {useDispatch} from "react-redux";
import {deleteProduct} from "../../slices/cartProductsSlice";
import {CloseOutlined} from "@ant-design/icons";


const CartProductCard = ({product}) => {
  const dispatch = useDispatch()

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
          onClick={() => dispatch(deleteProduct(product))}
        />,
        <CartProductEditForm product={product}/>,
      ]}
    >

      <div>
        <p><b>{product.name}</b></p>
        <p>{product.price} руб.</p>
        <p>{product.count} шт.</p>
      </div>

    </Card>
  );
};
export default CartProductCard;