import {AutoComplete, Input} from 'antd';
import {useState} from 'react';
import {useSelector} from "react-redux";

const StoreProductsSearch = () => {
  const storeProducts = useSelector((state) => state.storeProducts.value)
  const [options, setOptions] = useState([]);
  
  const selectHandler = (value) => {
    console.log('onSelect', value);
  };
  
  const searchHandler = (query) => {
    setOptions(query ? searchResult(query) : []);
  };

  const searchResult = (query) => {
    return storeProducts
      .filter(product => product.name.toLowerCase().includes(query.toLowerCase()))
      .map(product => {
        return {
          value: `${product.id}`,
          label: <div key={product.id}>{product.name}</div>
        }
      });
  }

  return (
    <AutoComplete
      popupMatchSelectWidth={252}
      style={{
        width: 300,
      }}
      options={options}
      onSelect={selectHandler}
      onSearch={searchHandler}
    >
      <Input.Search placeholder="поиск отдыхает" enterButton/>
    </AutoComplete>
  );
};
export default StoreProductsSearch;
