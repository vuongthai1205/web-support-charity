import { MyUserContext } from "App";
import { authApi, endpoints } from "config/apiConfig";
import { formatCurrency } from "functions";
import { useEffect } from "react";
import { useContext } from "react";
import { useState } from "react";
import { Button, Col, Form, Modal, Row, Spinner } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import cookie from "react-cookies";
function RegisterProject({ project, showPopup, closePopup }) {
  const [error, setError] = useState("");
  const [formData, setFormData] = useState({
    idProject: 0,
    contributionAmount: 0,
    contributionOther: "",
  });
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();
  const [user, dispatch] = useContext(MyUserContext);
  const handleRegister = async (e) => {
    e.preventDefault();

    if (!user) {
      // Người dùng chưa xác thực, chuyển hướng đến trang đăng nhập
      navigate("/login");
      return;
    }

    setIsLoading(true);

    try {
      const response = await authApi.post(
        `${endpoints["joinProject"]}`,
        formData
      );
      if (response.status === 201) {
        closePopup();
        let { data } = await authApi.get(endpoints["current-user"]);
        cookie.save("user", data);

        dispatch({
          type: "login",
          payload: data,
        });
        navigate("/project-charity");
        console.log("Project updated successfully");
      } else if (response.status === 500) {
        alert(
          "Xin lỗi bạn đã đăng ký dự án này, Vui lòng tham khảo dự án khác"
        );
      } else {
        console.log("You can not permission to edit the Project");
      }

      setIsLoading(false);
    } catch (ex) {
      if (ex.response.status === 500) {
        alert(
          "Xin lỗi bạn đã đăng ký dự án này, Vui lòng tham khảo dự án khác"
        );
      } else if(ex.response.status === 409) {
        alert(`Số tiền đóng góp đang cao hơn số tiền bạn hiện có. \nSố tiền hiện có của bạn là: ${ formatCurrency(user.tongTien) }`);
      }
      setIsLoading(false);
    }
  };
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  useEffect(() => {
    setFormData((prev) => ({
      ...prev,
      idProject: project,
    }));
  }, [project]);
  return (
    <>
      <Modal show={showPopup} onHide={closePopup}>
        <Modal.Header closeButton>
          <Modal.Title>Điền thông tin tham gia dự án</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Row className="mb-5 popup_post">
            <Col>
              <Form onSubmit={handleRegister}>
                <Form.Group
                  className="mb-3"
                  controlId="exampleForm.ControlInput1">
                  <Form.Label>{error}</Form.Label>
                </Form.Group>
                <Form.Group
                  className="mb-3"
                  controlId="exampleForm.ControlInput1">
                  <Form.Label>Số tiền đóng góp</Form.Label>
                  <Form.Control
                    name="contributionAmount"
                    value={formData.contributionAmount || ""}
                    type="text"
                    placeholder="Nhập số tiền đóng góp..."
                    onChange={handleInputChange}
                  />
                </Form.Group>
                <Form.Group
                  className="mb-3"
                  controlId="exampleForm.ControlTextarea1">
                  <Form.Label>Các đóng góp khác</Form.Label>
                  <Form.Control
                    as="textarea"
                    name="contributionOther"
                    value={formData.contributionOther || ""}
                    rows={3}
                    placeholder="Nhập các đóng góp khác..."
                    onChange={handleInputChange}
                  />
                </Form.Group>
                <Button
                  className="bg-bs-primary bg-color-btn-main mt-[8px]"
                  type="submit"
                  disabled={isLoading}>
                  {isLoading ? (
                    <Spinner animation="border" size="sm" /> // Display a loading spinner
                  ) : "Đăng ký"}
                </Button>
              </Form>
            </Col>
          </Row>
        </Modal.Body>
        <Modal.Footer>
          <Button
            className="bg-color-btn-secondary"
            variant="secondary"
            onClick={closePopup}>
            Đóng
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default RegisterProject;
