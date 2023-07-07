import {UserOutlined} from '@ant-design/icons';
import {Avatar, Breadcrumb, Button, Divider, Layout, Menu, Space, theme} from 'antd';
import {Route, Routes} from "react-router-dom";
import NotFoundPage from "../pages/NotFoundPage";
import React from "react";
import {useSelector} from "react-redux";
import StoreProductsMain from "./store/StoreProductsMain";
import CartProductsMain from "./cart/CartProductsMain";

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
  const user = useSelector(state => state.user.value);

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
          <Space style={{paddingLeft: "15px"}}>
            <Button
              href={"http://localhost:3000/products"}
              // shape={"round"}
              // type={"link"}
            >
              Продукты
            </Button>

            <Button
              href={"http://localhost:3000/users/" + user.id + "/cart/products"}
              // shape={"round"}
              // type={"link"}
            >
              Корзина
            </Button>
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
            <Breadcrumb.Item>{user.name}</Breadcrumb.Item>
          </Breadcrumb>

          <div
            style={{
              padding: 24,
              minHeight: 360,
              background: colorBgContainer,
            }}
          >

            <Routes>
              <Route path={"/products"} element={<StoreProductsMain />}/>
              <Route path={`/users/${user.id}/cart/products`} element={<CartProductsMain />}/>
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