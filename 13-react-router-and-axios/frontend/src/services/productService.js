import axios from "axios";
import {set} from "../slices/storeProductsSlice";

const API_URL = "http://localhost:8080/products";

const getProducts = (dispatch) => {
  return axios.get(API_URL)
    .then(
      (response) => {
        dispatch(set(response.data));
      },
      (error) => {
        const _content = (error.response && error.response.data) ||
          error.message ||
          error.toString();

        console.error(_content)

        dispatch(set([]));
      }
    );
};

const addProduct = (product, dispatch) => {
  return axios.post(API_URL, product)
    .then(
      (response) => {
        getProducts(dispatch);
      },
      (error) => {
        const _content = (error.response && error.response.data) ||
          error.message ||
          error.toString();

        console.error(_content)
      }
    );
};

const updateProduct = (id, product, dispatch) => {
  return axios.put(API_URL + `/${id}`, product)
    .then(
      (response) => {
        getProducts(dispatch);
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
  getProducts,
  addProduct,
  updateProduct,
};

export default productService;
