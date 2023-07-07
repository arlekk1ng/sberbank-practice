import {Button, Form, Input, InputNumber, Modal} from 'antd';
import {useState} from 'react';
import {EditOutlined} from "@ant-design/icons";
import {useDispatch} from "react-redux";
import productService from "../../services/productService";

const CollectionCreateForm = ({ open, onCreate, onCancel, product }) => {
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
          initialValue={product.name}
        >
          <Input allowClear />
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
          initialValue={product.price}
        >
          <InputNumber min={0} />
        </Form.Item>

        <Form.Item
          name="countInStore"
          label="Количестсво"
          rules={[
            {
              required: true,
              message: 'Введите количество продукта',
            },
          ]}
          initialValue={product.countInStore}
        >
          <InputNumber min={0} />
        </Form.Item>
      </Form>
    </Modal>
  );
};

const StoreProductEditForm = ({product}) => {
  const dispatch = useDispatch();

  const [open, setOpen] = useState(false);

  const onCreate = (updProduct) => {
    productService.updateStoreProduct(product.id, updProduct, dispatch);
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

        product={product}
      />
    </div>
  );
};
export default StoreProductEditForm;