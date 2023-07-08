import axios from "axios";
import {setStoreProducts} from "../slices/storeProductsSlice";
import {API_URL} from "./constants";
import authHeader from "./auth-header";

const getStoreProducts = (dispatch) => {
  return axios.get(API_URL + "/products")
    .then(
      (response) => {
        const products = response.data;
        // костыль
        dispatch(setStoreProducts(products.reverse()));
      },
      (error) => {
        const _content = (error.response && error.response.data) ||
          error.message ||
          error.toString();

        console.error(_content)

        dispatch(setStoreProducts([]));
      }
    );
};


// --- Новые объекты вставляются в конец!

const addProductInStore = (product, dispatch) => {
  return axios.post(API_URL + "/products", product,  {headers: authHeader()})
    .then(
      (response) => {
        getStoreProducts(dispatch);

        // const newProducts = [products.pop(), ...products]
        // dispatch(setStoreProducts(newProducts));
      },
      (error) => {
        const _content = (error.response && error.response.data) ||
          error.message ||
          error.toString();

        console.error(_content)
      }
    );
};

const updateStoreProduct = (productId, updProduct, dispatch) => {
  return axios.put(API_URL + `/products/${productId}`, updProduct,  {headers: authHeader()})
    .then(
      (response) => {
        getStoreProducts(dispatch);

        // if (response.data) {
        //   const newProduct = {
        //     ...updProduct,
        //     id: productId
        //   }
        //
        //   const index = products.findIndex(product => product.id === productId);
        //   products[index] = newProduct;
        // }
      },
      (error) => {
        const _content = (error.response && error.response.data) ||
          error.message ||
          error.toString();

        console.error(_content)
      }
    );
};

const deleteStoreProduct = (productId, dispatch) => {
  return axios.delete(API_URL + `/products/${productId}`,  {headers: authHeader()})
    .then(
      (response) => {
        getStoreProducts(dispatch);
      },
      (error) => {
        const _content = (error.response && error.response.data) ||
          error.message ||
          error.toString();

        console.error(_content)
      }
    );
};

const productService = {
  getStoreProducts,
  addProductInStore,
  updateStoreProduct,
  deleteStoreProduct,
};

export default productService;
