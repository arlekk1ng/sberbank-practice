import {Button, Form, Input, InputNumber, Modal} from 'antd';
import {useState} from 'react';
import {EditOutlined} from "@ant-design/icons";
import {useDispatch} from "react-redux";
import {updateProduct} from "../slices/ProductsSlice";

const CollectionCreateForm = ({ open, onCreate, onCancel }) => {
  const [form] = Form.useForm();
  return (
    <Modal
      open={open}
      title="Изменение продукта"
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
          name="name"
          label="Наименование"
          rules={[
            {
              required: true,
              message: 'Введите наименование продукта',
            },
          ]}
        >
          <Input />
        </Form.Item>

        <Form.Item
          name="price"
          label="Цена"
          rules={[
            {
              required: true,
              message: 'Введите цену продукта',
            },
          ]}
        >
          <InputNumber min={0} defaultValue={0} />
        </Form.Item>
      </Form>
    </Modal>
  );
};
const ProductEditForm = ({productId}) => {
  const dispatch = useDispatch()

  const [open, setOpen] = useState(false);
  const onCreate = (product) => {
    product.id = productId;
    dispatch(updateProduct(product));
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
export default ProductEditForm;