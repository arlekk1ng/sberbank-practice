import {PlusOutlined} from '@ant-design/icons';
import {Button, Card} from 'antd';
import ProductEditForm from "./ProductEditForm";
import {useSelector} from "react-redux";

const ProductCard = ({product}) => {
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
        />,
        <ProductEditForm productId={product.id}/>,
      ]}
    >

      <div>
        <p><b>{product.name}</b></p>
        <p>{product.price} руб.</p>
      </div>

    </Card>
  );
};
export default ProductCard;