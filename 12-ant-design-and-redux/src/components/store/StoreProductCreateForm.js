import {Button, Form, Input, InputNumber, Modal} from 'antd';
import {useState} from 'react';
import {EditOutlined} from "@ant-design/icons";
import {useDispatch} from "react-redux";
import {addProduct} from "../../slices/storeProductsSlice";

const CollectionCreateForm = ({ open, onCreate, onCancel }) => {
  const [form] = Form.useForm();
  return (
    <Modal
      open={open}
      title="Добавление продукта"
      okText="Добавить"
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

const StoreProductCreateForm = () => {
  const dispatch = useDispatch()

  const [open, setOpen] = useState(false);
  const onCreate = (product) => {
    dispatch(addProduct(product));
    setOpen(false);
  };

  return (
    <div>
      <Button
        onClick={() => {
          setOpen(true);
        }}
      >Добавить новый продукт</Button>
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
export default StoreProductCreateForm;