import {Button, Form, InputNumber, Modal} from 'antd';
import {useState} from 'react';
import {EditOutlined} from "@ant-design/icons";
import {useDispatch} from "react-redux";

const CollectionCreateForm = ({ open, onCreate, onCancel }) => {
  const [form] = Form.useForm();
  return (
    <Modal
      open={open}
      title="Изменение количества продукта"
      okText="Сохранить"
      cancelText="Отменить"
      onCancel={onCancel}
      onOk={() => {
        form
          .validateFields()
          .then((values) => {
            form.resetFields();
            onCreate(values);
          })
          .catch((info) => {
            console.log('Validate Failed:', info);
          });
      }}
    >
      <Form
        form={form}
        layout="vertical"
        name="form_in_modal"
      >
        <Form.Item
          name="count"
          rules={[
            {
              required: true,
              message: 'Введите количество продукта',
            },
          ]}
          initialValue={1}
        >
          <InputNumber min={1} />
        </Form.Item>
      </Form>
    </Modal>
  );
};

const CartProductEditForm = ({cartProduct}) => {
  // const client = useSelector(state => state.client.value);
  const dispatch = useDispatch();

  const [open, setOpen] = useState(false);
  const onCreate = (values) => {
    const newProductCount = {
      ...cartProduct,
      productCount: values.count
    }

    // userService.changeProductCountInCart(client.id, cartProduct.product.id, newProductCount, dispatch);
    setOpen(false);
  };

  return (
    <div>
      <Button
        icon={<EditOutlined />}
        type="text"
        onClick={() => {
          setOpen(true);
        }}
      />
      <CollectionCreateForm
        open={open}
        onCreate={onCreate}
        onCancel={() => {
          setOpen(false);
        }}
      />
    </div>
  );
};
export default CartProductEditForm;