import React, { useContext, useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";
import Button from "react-bootstrap/Button";
import cookie from "react-cookies";
import Form from "react-bootstrap/Form";
import { MyUserContext } from "../../App";
import apiConfig, { authApi, endpoints } from "../../config/apiConfig";
import { Col, Container, Row, Spinner } from "react-bootstrap";
function Login() {
  const [user, dispatch] = useContext(MyUserContext);
  const [loading, setLoading] = useState(false);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const [validated, setValidated] = useState(false);
  const [error, setError] = useState("");
  const handleLogin = async (e) => {
    e.preventDefault();
    const form = e.currentTarget;
    if (form.checkValidity() === false) {
      // If the form is invalid, don't proceed with API call
      e.stopPropagation();
      setValidated(true); // Set validated to true to display validation messages
      return;
    }
    setLoading(true);
    try {
      const response = await apiConfig.post(endpoints["login"], {
        username,
        password,
      });
      if (response.status === 200) {
        setLoading(false);

        cookie.save("token", response.data.token);
        let { data } = await authApi().get(endpoints["current-user"]);
        cookie.save("user", data);

        dispatch({
          type: "login",
          payload: data,
        });
      } else if (response.status === 400) {
        setError("Sai mật khẩu");
      }
    } catch (error) {
      setLoading(false);
      if (error.response.status === 400) {
        setError("Sai mật khẩu");
      } else {
        setError("Kiểm tra lại thông tin");
      }
    }
  };

  if (user !== null) return <Navigate to="/" />;

  return (
    <div className="my-[20px]">
      <Container>
        <Row>
          <Col>
            <h2 className="font-['Calistoga'] text-[25px] text-center">
              Trang đăng nhập
            </h2>
            <Form noValidate validated={validated} onSubmit={handleLogin}>
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Tên đăng nhập</Form.Label>
                <Form.Control
                  required
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  type="text"
                  placeholder="Nhập tên đăng nhập"
                />
              </Form.Group>

              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Mật khẩu</Form.Label>
                <Form.Control
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  type="password"
                  placeholder="Nhập mật khẩu"
                  required
                />
              </Form.Group>
              <Button
                className="bg-color-btn-main relative"
                type="submit"
                variant="primary"
                disabled={loading}>
                {!loading ? "Đăng nhập" : <Spinner />}
              </Button>
              <span className="text-color-btn-danger ml-[12px]">{error}</span>
            </Form>
          </Col>
        </Row>
      </Container>
    </div>
  );
}

export default Login;
