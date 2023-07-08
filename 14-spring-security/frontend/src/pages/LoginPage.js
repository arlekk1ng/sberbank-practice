import {LockOutlined, UserOutlined} from '@ant-design/icons';
import {Button, Form, Input} from 'antd';
import authService from "../services/auth.service";
import {useDispatch} from "react-redux";
import {login} from "../slices/authSlice";
import {useNavigate} from "react-router-dom";

const LoginPage = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const onFinish = (values) => {
        authService.login(values).then((user) => {
            dispatch(login(user));
            navigate("/");
        });
    };

    return (
        <Form
            name="normal_login"
            className="login-form"
            onFinish={onFinish}
            style={{padding: '20px 50px'}}
        >
            <Form.Item
                name="username"
                label="Логин"
                rules={[
                    {
                        required: true,
                        message: 'Please input your Username!',
                    },
                ]}
            >
                <Input
                  prefix={<UserOutlined className="site-form-item-icon" />}
                  placeholder="Username"
                />
            </Form.Item>

            <Form.Item
                name="password"
                label="Пароль"
                rules={[
                    {
                        required: true,
                        message: 'Please input your Password!',
                    },
                ]}
            >
                <Input
                    prefix={<LockOutlined className="site-form-item-icon" />}
                    type="password"
                    placeholder="Password"
                />
            </Form.Item>

            <Form.Item>
                <Button type="primary" htmlType="submit" className="login-form-button">
                    Войти
                </Button>
            </Form.Item>
        </Form>
    );
};
export default LoginPage;
