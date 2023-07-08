import { Button, Result } from 'antd';
const NotFoundPage = () => (
  <Result
    status="404"
    title="404"
    subTitle="Sorry, the page you visited does not exist."
    extra={<Button type="primary" href={"http://localhost:3000"}>Back Home</Button>}
  />
);
export default NotFoundPage;