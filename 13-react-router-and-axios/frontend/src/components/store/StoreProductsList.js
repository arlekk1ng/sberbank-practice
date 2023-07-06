import {List} from 'antd';
import {useSelector} from "react-redux";
import StoreProductCard from "./StoreProductCard";

const StoreProductsList = () => {
  const storeProducts = useSelector(state => state.storeProducts.value)

  return (
    <List
      grid={{
        gutter: 16,
        column: 3,
      }}
      dataSource={storeProducts}
      renderItem={(item) => {
        return (
          <List.Item>
            <StoreProductCard product={item}/>
          </List.Item>
        )
      }}
    />
  );
};
export default StoreProductsList;