import {UserOutlined} from '@ant-design/icons';
import {Avatar, Breadcrumb, Button, Divider, Layout, Menu, Space, theme} from 'antd';
import {Route, Routes} from "react-router-dom";
import NotFoundPage from "../pages/NotFoundPage";
import React from "react";
import {useDispatch, useSelector} from "react-redux";
import StoreProductsMain from "./store/StoreProductsMain";
import CartProductsMain from "./cart/CartProductsMain";
import RegistrationPage from "../pages/RegistrationPage";
import LoginPage from "../pages/LoginPage";
import authService from "../services/auth.service";
import productService from "../services/productService";
import userService from "../services/userService";

const { Header, Content, Footer, Sider } = Layout;

function getItem(label, key, icon, children) {
  return {
    key,
    icon,
    children,
    label,
  };
}

const MainLayout = () => {
  const stateAuth = useSelector(state => state.auth);
  const dispatch = useDispatch();

  const items = [
    getItem('Профиль', 'profile', <UserOutlined />, [
      getItem("Изменить", 'change'),
    ]),
  ];

  const {
    token: { colorBgContainer },
  } = theme.useToken();

  return (
    <Layout
      style={{
        minHeight: '100vh',
      }}
    >
      <Sider>
        <div className="demo-logo-vertical"/>

        <div style={{
          display: "flex",
          margin: "15px"
        }}>
          <Avatar
            size={{
              xs: 24,
              sm: 32,
              md: 40,
              lg: 64,
              xl: 80,
              xxl: 100,
            }}
            src={"https://lamcdn.net/wonderzine.com/post-cover/fUfcc4c04ivrhf2WTGccaA-default.gif"}
            style={{margin: "auto"}}
          />
        </div>

        <Menu theme="dark" defaultSelectedKeys={['profile']} mode="inline" items={items} />
      </Sider>

      <Layout>
        <Header
          style={{
            padding: 0,
            background: colorBgContainer,
          }}
        >
          <Space style={{padding: "0 15px"}}>
            <Button
                href={"http://localhost:3000/products"}
                onClick={() => productService.getStoreProducts(dispatch)}
            >
              Продукты
            </Button>

            <Button
                disabled={!stateAuth.isLoggedIn}
                href={"http://localhost:3000/users/" + stateAuth.user.id + "/cart/products"}
                onClick={() => userService.getProductsFromUserCart(stateAuth.user.id, dispatch)}
            >
              Корзина
            </Button>

            <Space>
              <Button
                  href={"http://localhost:3000/api/auth/signin"}
              >
                Войти
              </Button>

              <Button
                  href={"http://localhost:3000/api/auth/signup"}
              >
                Зарегистрироваться
              </Button>

              <Button
                  disabled={!stateAuth.isLoggedIn}
                  href={"http://localhost:3000/api/auth/signin"}
                  onClick={authService.logout}
              >
                Выйти
              </Button>
            </Space>

          </Space>

        </Header>

        <Content
          style={{
            margin: '0 16px',
          }}
        >
          <Breadcrumb
            style={{
              margin: '16px 0',
            }}
          >
            <Breadcrumb.Item>Пользователь</Breadcrumb.Item>
            <Breadcrumb.Item>{stateAuth.user.username}</Breadcrumb.Item>
          </Breadcrumb>

          <div
            style={{
              padding: 24,
              minHeight: 360,
              background: colorBgContainer,
            }}
          >

            <Routes>
              <Route path={"/api/auth/signin"} element={<LoginPage />}/>
              <Route path={"/api/auth/signup"} element={<RegistrationPage />}/>
              <Route index />

              <Route path={"/products"} element={<StoreProductsMain />}/>
              <Route path={`/users/${stateAuth.user.id}/cart/products`} element={<CartProductsMain />}/>
              <Route path={"*"} element={<NotFoundPage />}/>
            </Routes>

          </div>
        </Content>

        <Footer
          style={{
            textAlign: 'center',
          }}
        >
          Store ©2023 Created by Arlekk1ng
        </Footer>
      </Layout>
    </Layout>
  );
};
export default MainLayout;