import {PlusOutlined} from '@ant-design/icons';
import {Button, Card} from 'antd';
import StoreProductEditForm from "./StoreProductEditForm";
import {useDispatch, useSelector} from "react-redux";
import clientService from "../../services/clientService";

const StoreProductCard = ({product}) => {
  const client = useSelector(state => state.client.value);
  const dispatch = useDispatch();

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
          onClick={() => clientService.addProductInCart(client.id, product, dispatch)}
        />,
        <StoreProductEditForm productId={product.id}/>,
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
export default StoreProductCard;