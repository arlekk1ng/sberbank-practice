import {CloseOutlined, PlusOutlined} from '@ant-design/icons';
import {Button, Card} from 'antd';
import StoreProductEditForm from "./StoreProductEditForm";
import userService from "../../services/userService";
import {useDispatch, useSelector} from "react-redux";
import productService from "../../services/productService";

const StoreProductCard = ({product}) => {
  const user = useSelector(state => state.auth.user);

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
        <StoreProductEditForm product={product}/>,
        <Button
          icon={<PlusOutlined />}
          type="text"
          onClick={() => userService.addProductInUserCart(user.id, product, dispatch)}
        />,
      ]}

      extra={
        <Button
          icon={<CloseOutlined />}
          onClick={() => productService.deleteStoreProduct(product.id, dispatch)}
        />
      }
      hoverable={true}
    >

      <div>
        <p><b>{product.name}</b></p>
        <p>{product.price} руб.</p>
        <p>{product.countInStore} шт.</p>
      </div>

    </Card>
  );
};
export default StoreProductCard;