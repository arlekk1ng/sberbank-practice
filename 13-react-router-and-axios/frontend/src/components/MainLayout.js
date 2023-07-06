import {UserOutlined} from '@ant-design/icons';
import {Avatar, Breadcrumb, Layout, Menu, theme} from 'antd';
import MainPage from "../pages/MainPage";
import {Route, Routes} from "react-router-dom";
import NotFoundPage from "../pages/NotFoundPage";
import React, {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import clientService from "../services/clientService";

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
  const client = useSelector(state => state.client.value)

  const items = [
    getItem('Пользователь', 'sub1', <UserOutlined />, [
      getItem(client.name, 'user1'),
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

        <Menu theme="dark" defaultSelectedKeys={['user1']} mode="inline" items={items} />
      </Sider>
      <Layout>
        <Header
          style={{
            padding: 0,
            background: colorBgContainer,
          }}
        />

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
            <Breadcrumb.Item>{client.name}</Breadcrumb.Item>
          </Breadcrumb>

          <div
            style={{
              padding: 24,
              minHeight: 360,
              background: colorBgContainer,
            }}
          >

            <Routes>
              <Route index element={<MainPage />}/>
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