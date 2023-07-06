import axios from "axios";
import {set} from "../slices/cartProductsSlice";
import {setClient} from "../slices/clientSlice";

const API_URL = "http://localhost:8080/clients";

const addClient = (client, dispatch) => {
  return axios.post(API_URL, client)
    .then(
      (response) => {
        getClient(1, dispatch);
      },
      (error) => {
        const _content = (error.response && error.response.data) ||
          error.message ||
          error.toString();

        console.error(_content)
      }
    );
};

const getClient = (clientId, dispatch) => {
  return axios.get(API_URL + `/${clientId}`)
    .then(
      (response) => {
        dispatch(setClient(response.data));
      },
      (error) => {
        const _content = (error.response && error.response.data) ||
          error.message ||
          error.toString();

        console.error(_content)

        dispatch(setClient({}));
      }
    );
}


const getCartProducts = (clientId, dispatch) => {
  return axios.get(API_URL + `/${clientId}/cart/products`)
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

const addProductInCart = (clientId, product, dispatch) => {
  return axios.post(API_URL + `/${clientId}/cart/products`, product)
    .then(
      (response) => {
        getCartProducts(clientId, dispatch);
      },
      (error) => {
        const _content = (error.response && error.response.data) ||
          error.message ||
          error.toString();

        console.error(_content)
      }
    );
};

const changeProductCountInCart = (clientId, productId, cartProduct, dispatch) => {
  return axios.put(API_URL + `/${clientId}/cart/products/${productId}`, cartProduct)
    .then(
      (response) => {
        getCartProducts(clientId, dispatch);
      },
      (error) => {
        const _content = (error.response && error.response.data) ||
          error.message ||
          error.toString();

        console.error(_content)
      }
    );
};

const deleteProductInCart = (clientId, productId, dispatch) => {
  return axios.delete(API_URL + `/${clientId}/cart/products/${productId}`)
    .then(
      (response) => {
        getCartProducts(clientId, dispatch);
      },
      (error) => {
        const _content = (error.response && error.response.data) ||
          error.message ||
          error.toString();

        console.error(_content)
      }
    );
};

const clientService = {
  addClient,
  getClient,
  getCartProducts,
  addProductInCart,
  changeProductCountInCart,
  deleteProductInCart,
};

export default clientService;
