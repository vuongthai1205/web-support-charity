import { MyUserContext } from "App";
import { authApi, endpoints } from "config/apiConfig";
import { useEffect } from "react";
import { useContext } from "react";
import { useState } from "react";
import { Button, Col, Form, Modal, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

function UpdateJoinProject({ project, showPopup, closePopup }) {
  const [error, setError] = useState("");
  const [formData, setFormData] = useState({
    idProject: project.project.id,
    idUser: project.user.id,
    contributionAmount: project.contributionAmount,
    contributionOther: project.contributionOther,
    role: project.roleId,
  });
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  const [user, dispatch] = useContext(MyUserContext);
  const handleRegister = async (e) => {
    e.preventDefault();

    if (!user) {
      // Người dùng chưa xác thực, chuyển hướng đến trang đăng nhập
      navigate("/login");
      return;
    }

    try {
      const response = await authApi.put(
        `${endpoints["joinProject"]}`,
        formData
      );
      if (response.status === 200) {
        closePopup();
        navigate(`/project-charity/${project.project.id}`);
        console.log("Project updated successfully");
      } else if (response.status === 500) {
        console.log("Failed to add or update project");
      } else {
        console.log("You can not permission to edit the Project");
      }
    } catch (ex) {
      console.log(ex);
    }
  };
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };
  const [isDisabledInput, setIsDisabledInput] = useState(false);
  const [isDisabledSelect, setIsDisabledSelect] = useState(false);
  useEffect(() => {
    const checkUser = () => {
      if (user.username !== project.project.user.username) {
        // Nếu người dùng có tên hoặc tên người dùng giống với tác giả của dự án, cho phép chỉnh sửa
        setIsDisabledInput(false);
        setIsDisabledSelect(true);
      } else if (user.username !== project.user.username) {
        // Nếu người dùng có tên hoặc tên người dùng giống với tác giả của dự án, cho phép chỉnh sửa
        setIsDisabledInput(true);
        setIsDisabledSelect(false);
      } else {
        // Ngược lại, không cho phép chỉnh sửa
        setIsDisabledInput(false);
        setIsDisabledSelect(false);
      }
    };
    checkUser();
  }, [project.project.user.username,project.user.username,user.username]);

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
                    required
                    disabled={true}
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
                    required
                    disabled={isDisabledInput}
                  />
                </Form.Group>
                <Form.Select
                  name="role" // Đặt tên cho trường lựa chọn để bạn có thể lấy giá trị khi người dùng chọn
                  onChange={handleInputChange} // Khi người dùng chọn vai trò, gọi hàm handleInputChange để cập nhật giá trị trong formData
                  value={formData.role}
                  disabled={isDisabledSelect}>
                  <option value={1} defaultValue={formData.role === 1}>
                    Trưởng dự án
                  </option>
                  <option value={2} defaultValue={formData.role === 2}>
                    Phó dự án
                  </option>
                  <option value={3} defaultValue={formData.role === 3}>
                    Thành Viên
                  </option>
                </Form.Select>

                <Button
                  className="bg-bs-primary bg-color-btn-main mt-[8px]"
                  type="submit"
                  disabled={loading}>
                  {loading === true ? "Đang tải" : "Cập nhật"}
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

export default UpdateJoinProject;
