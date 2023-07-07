import axios from "axios";
import {setStoreProducts} from "../slices/storeProductsSlice";
import {API_URL} from "./constants";

const getStoreProducts = (dispatch) => {
  return axios.get(API_URL + "/products")
    .then(
      (response) => {
        dispatch(setStoreProducts(response.data));
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

const addProductInStore = (product, dispatch) => {
  return axios.post(API_URL + "/products", product)
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

const updateStoreProduct = (productId, updProduct, dispatch) => {
  return axios.put(API_URL + `/products/${productId}`, updProduct)
    .then(
      (response) => {
        if (response.data) {
          getStoreProducts(dispatch);
        }
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
  return axios.delete(API_URL + `/products/${productId}`)
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
