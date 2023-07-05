import {AntDesignOutlined, UserOutlined} from '@ant-design/icons';
import {Avatar, Breadcrumb, Layout, Menu, theme} from 'antd';
import ProductsCartTabs from "./ProductsCartTabs";

const { Header, Content, Footer, Sider } = Layout;

function getItem(label, key, icon, children) {
  return {
    key,
    icon,
    children,
    label,
  };
}

const items = [
  getItem('Пользователь', 'sub1', <UserOutlined />, [
    getItem('Джейсон', 'user1'),
  ]),
];

const MainLayout = () => {
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
            <Breadcrumb.Item>Джейсон</Breadcrumb.Item>
          </Breadcrumb>
          <div
            style={{
              padding: 24,
              minHeight: 360,
              background: colorBgContainer,
            }}
          >
            <ProductsCartTabs />
          </div>
        </Content>
        <Footer
          style={{
            textAlign: 'center',
          }}
        >
          Ant Design ©2023 Created by Arlekk1ng
        </Footer>
      </Layout>
    </Layout>
  );
};
export default MainLayout;