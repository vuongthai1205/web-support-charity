import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Form, Button, Container, Row, Col } from "react-bootstrap";
import appFirebase from "../../config/firebase";
import Image from "react-bootstrap/Image";
import {
  getStorage,
  ref,
  uploadBytesResumable,
  getDownloadURL,
} from "firebase/storage";
import { endpoints } from "../../config/apiConfig";
import apiConfig from "../../config/apiConfig";
function Register() {
  const [isLoading, setLoading] = useState(false);
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    tenDangNhap: "",
    soDienThoai: "",
    email: "",
    matKhau: "",
    ten: "",
    ho: "",
    anhDaiDien: "",
  });

  const handleImageChange = async (e) => {
    if (e.target.files[0]) {
      const image = e.target.files[0];
      const storageRef = getStorage(appFirebase);
      const imageRef = ref(storageRef, `avatars/${image.name}`);
      const uploadTask = uploadBytesResumable(imageRef, image);
      uploadTask.on(
        "state_changed",
        (snapshot) => {
          // Observe state change events such as progress, pause, and resume
          // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
          const progress =
            (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
          console.log("Upload is " + progress + "% done");
          switch (snapshot.state) {
            case "paused":
              console.log("Upload is paused");
              break;
            case "running":
              setLoading(true);
              console.log("Upload is running");
              break;
          }
        },
        (error) => {
          // Handle unsuccessful uploads
        },
        () => {
          // Handle successful uploads on complete
          // For instance, get the download URL: https://firebasestorage.googleapis.com/...
          getDownloadURL(uploadTask.snapshot.ref).then((downloadURL) => {
            setLoading(false);
            setFormData((prevData) => ({
              ...prevData,
              anhDaiDien: downloadURL,
            }));
          });
        }
      );
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formDataToSend = new FormData();
    formDataToSend.append("tenDangNhap", formData.tenDangNhap);
    formDataToSend.append("soDienThoai", formData.soDienThoai);
    formDataToSend.append("email", formData.email);
    formDataToSend.append("matKhau", formData.matKhau);
    formDataToSend.append("ten", formData.ten);
    formDataToSend.append("ho", formData.ho);
    formDataToSend.append("anhDaiDien", formData.anhDaiDien);
    try {
      const response = await apiConfig.post(
        endpoints["register"],
        formDataToSend,
        {
          headers: {
            "Content-Type": "application/json", // Set the content type to JSON
          },
        }
      );

      if (response.status === 201) {
        setLoading(true);
        // Handle successful registration
        navigate("/login");
      } else {
        // Handle registration error
      }
    } catch (error) {
      // Handle Axios error
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  return (
    <div className="container mt-4 mb-[20px]">
      <Container>
        <Row>
          <Col>
            <h2 className="font-['Calistoga'] text-[25px] text-center">Trang đăng ký</h2>
            <Form onSubmit={handleSubmit}>
              <Form.Group controlId="tenDangNhap">
                <Form.Label>Tên đăng nhập</Form.Label>
                <Form.Control
                  type="text"
                  name="tenDangNhap"
                  value={formData.tenDangNhap}
                  onChange={handleInputChange}
                  required
                />
              </Form.Group>
              <Form.Group controlId="matKhau">
                <Form.Label>Mật khẩu</Form.Label>
                <Form.Control
                  type="password"
                  name="matKhau"
                  value={formData.matKhau}
                  onChange={handleInputChange}
                  required
                />
              </Form.Group>
              <Form.Group controlId="ten">
                <Form.Label>Tên</Form.Label>
                <Form.Control
                  type="text"
                  name="ten"
                  value={formData.ten}
                  onChange={handleInputChange}
                  required
                />
              </Form.Group>
              <Form.Group controlId="ho">
                <Form.Label>Họ</Form.Label>
                <Form.Control
                  type="text"
                  name="ho"
                  value={formData.ho}
                  onChange={handleInputChange}
                  required
                />
              </Form.Group>
              <Form.Group controlId="soDienThoai">
                <Form.Label>Số điện thoại</Form.Label>
                <Form.Control
                  type="soDienThoai"
                  name="soDienThoai"
                  value={formData.soDienThoai}
                  onChange={handleInputChange}
                  required
                />
              </Form.Group>
              <Form.Group controlId="email">
                <Form.Label>Email</Form.Label>
                <Form.Control
                  type="email"
                  name="email"
                  value={formData.email}
                  onChange={handleInputChange}
                  required
                />
              </Form.Group>
              <Form.Group controlId="anhDaiDien">
                <Form.Label>Ảnh đại diện</Form.Label>
                <Form.Control
                  type="file"
                  onChange={handleImageChange}
                  required
                />
              </Form.Group>
              <Form.Group>
                <Image height={200} src={formData.anhDaiDien} />
              </Form.Group>

              <Button
                disabled={isLoading}
                className="mt-2 bg-color-btn-main"
                variant="primary"
                type="submit">
                {isLoading ? "Đang tải" : "Đăng ký"}
              </Button>
              <p>
                Nếu bạn đã có tài khoản <Link className="text-color-btn-danger" to="/login">hãy đăng nhập</Link>
              </p>
            </Form>
          </Col>
        </Row>
      </Container>
    </div>
  );
}

export default Register;
