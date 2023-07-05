import {Card, List} from 'antd';
import {useSelector} from "react-redux";
import ProductCard from "./ProductCard";

const CardsListGridLayout = () => {
  const products = useSelector(state => state.products.value)

  return (
    <List
      grid={{
        gutter: 16,
        column: 4,
      }}
      dataSource={products}
      renderItem={(item) => {
        return (
          <List.Item>
            <ProductCard product={item}/>
          </List.Item>
        )
      }}
    />
  );
};
export default CardsListGridLayout;