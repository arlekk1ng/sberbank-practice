import {PlusOutlined} from '@ant-design/icons';
import {Button, Card} from 'antd';
import StoreProductEditForm from "./StoreProductEditForm";
import {useDispatch} from "react-redux";
import {addProduct} from "../../slices/cartProductsSlice";

const StoreProductCard = ({product}) => {
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
          icon={<PlusOutlined />}
          type="text"
          onClick={() => dispatch(addProduct(product))}
        />,
        <StoreProductEditForm productId={product.id}/>,
      ]}
    >

      <div>
        <p><b>{product.name}</b></p>
        <p>{product.price} руб.</p>
      </div>

    </Card>
  );
};
export default StoreProductCard;