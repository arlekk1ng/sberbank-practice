import axios from "axios";
import {setCartProducts} from "../slices/cartProductsSlice";
import {API_URL} from "./constants";
import authHeader from "./auth-header";

const getProductsFromUserCart = (userId, dispatch) => {
  return axios.get(API_URL + `/users/${userId}/cart/products`,  {headers: authHeader()})
    .then(
      (response) => {
        dispatch(setCartProducts(response.data));
      },
      (error) => {
        const _content = (error.response && error.response.data) ||
          error.message ||
          error.toString();

        console.error(_content)

        dispatch(setCartProducts([]));
      }
    );
};

const addProductInUserCart = (userId, product, dispatch) => {
  return axios.post(API_URL + `/users/${userId}/cart/products`, product,  {headers: authHeader()})
    .then(
      (response) => {
        getProductsFromUserCart(userId, dispatch);
      },
      (error) => {
        const _content = (error.response && error.response.data) ||
          error.message ||
          error.toString();

        console.error(_content);
      }
    );
};

const changeProductCountInCart = (userId, productId, cartProduct, dispatch, cartProducts) => {
  return axios.put(API_URL + `/users/${userId}/cart/products/${productId}`, cartProduct,  {headers: authHeader()})
    .then(
      (response) => {
        const newCartProducts = cartProducts.map(value => {
          if (value.product.id === productId) {
            return {
              ...value,
              productCountInCart: cartProduct.productCountInCart,
            }
          }

          return value;
        });

        dispatch(setCartProducts(newCartProducts));
      },
      (error) => {
        const _content = (error.response && error.response.data) ||
          error.message ||
          error.toString();

        console.error(_content);
      }
    );
};

const deleteProductInUserCart = (userId, productId, dispatch) => {
  return axios.delete(API_URL + `/users/${userId}/cart/products/${productId}`,  {headers: authHeader()})
    .then(
      (response) => {
        getProductsFromUserCart(userId, dispatch);
      },
      (error) => {
        const _content = (error.response && error.response.data) ||
          error.message ||
          error.toString();

        console.error(_content);
      }
    );
};

const userService = {
  getProductsFromUserCart,
  addProductInUserCart,
  changeProductCountInCart,
  deleteProductInUserCart,
};

export default userService;
