import { MyUserContext } from "App";
import ImagePost from "components/Posts/ImagePost";
import { authApi, endpoints } from "config/apiConfig";
import appFirebase from "config/firebase";
import {
  getDownloadURL,
  getStorage,
  ref,
  uploadBytesResumable,
} from "firebase/storage";
import { formatDateForInput } from "functions";
import { useContext } from "react";
import { useEffect } from "react";
import { useState } from "react";
import { Button, Col, Modal, Row, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

function CreateAndUpdateProject({
  onProjectCreated,
  showPopup,
  closePopup,
  project,
}) {
  
  const [error, setError] = useState("");
  const [user, dispatch] = useContext(MyUserContext);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    nameProject: "",
    purpose: "",
    address: "",
    amountRaised: "",
    images: [],
    startTime: "",
    endTime: "",
  });
  useEffect(() => {
    if (project !== undefined) {
      setFormData((prevData) => ({
        ...prevData,
        nameProject: project.nameProject,
        purpose: project.purpose,
        address: project.address,
        amountRaised: project.amountRaised,
        images: project.images ? project.images : [],
        startTime: project.startTime ? formatDateForInput(project.startTime) : "Chưa cập nhật",
        endTime: project.endTime ? formatDateForInput(project.endTime) : "Chưa cập nhật",
      }));
    } else {
      setFormData((prevData) => ({
        ...prevData,
      }));
    }
  }, [project]);
  const isFormDataValid = (data) => {
    if (data.nameProject.trim() === "" || data.purpose.trim() === "") {
      setError("Vui lòng điền thông tin");
      return true;
    } else if (data.nameProject.trim() === "") {
      setError("Vui lòng điền tên dự án");
      return true;
    } else {
      return false;
    }
  };
  const handleImageChange = async (e) => {
    setFormData((prevData) => ({
      ...prevData,
      images: [],
    }));
    for (let i = 0, f; (f = e.target.files[i]); i++) {
      if (f) {
        const image = f;
        const storageRef = getStorage(appFirebase);
        const imageRef = ref(storageRef, `images/${image.name}`);
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
              setFormData((prevFormData) => ({
                ...prevFormData,
                images: [...prevFormData.images, { link: downloadURL }],
              }));
            });
          }
        );
      }
    }
  };
  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!user) {
      // Người dùng chưa xác thực, chuyển hướng đến trang đăng nhập
      navigate("/login");
      return;
    }

    if (isFormDataValid(formData)) {
      // Do not submit if all fields are empty
      return;
    }

    if (project !== undefined) {
      try {
        const response = await authApi.put(
          `${endpoints["project"]}${project.id}/`,
          formData
        );
        if (response.status === 200) {
          closePopup();
          setFormData({
            nameProject: "",
            purpose: "",
            address: "",
            amountRaised: "",
            images: [],
            startTime: "",
            endTime: "",
          });
          navigate("/project-charity")
          console.log("Project updated successfully");
        } else if (response.status === 500) {
          console.log("Failed to add or update project");
        } else {
          console.log("You can not permission to edit the Project");
        }
      } catch (ex) {
        console.log(ex);
      }
    } else {
      try {
        const response = await authApi.post(endpoints["project"], formData);

        if (response.status === 201) {
          onProjectCreated();
          closePopup();
          setFormData({
            nameProject: "",
            purpose: "",
            address: "",
            amountRaised: "",
            images: [],
            startTime: "",
            endTime: "",
          });
          setError("");
          alert("Cảm ơn bạn đã đăng dự án, xin vui lòng chờ quản trị viên duyệt dự án của bạn")
        } else {
          // Xử lý lỗi khi đăng bài viết
        }
      } catch (error) {
        // Xử lý lỗi từ Axios
      }
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
    <>
      <Modal show={showPopup} onHide={closePopup}>
        <Modal.Header closeButton>
          <Modal.Title>{project ? "Sửa dự án" : "Đăng dự án"}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Row className="mb-5 popup_post">
            <Col>
              <Form onSubmit={handleSubmit}>
                <Form.Group
                  className="mb-3"
                  controlId="exampleForm.ControlInput1">
                  <Form.Label>{error}</Form.Label>
                </Form.Group>
                <Form.Group
                  className="mb-3"
                  controlId="exampleForm.ControlInput1">
                  <Form.Label>Tên dự án</Form.Label>
                  <Form.Control
                    name="nameProject"
                    value={formData.nameProject || ""}
                    type="text"
                    placeholder="Nhập tên dự án..."
                    onChange={handleInputChange}
                    required
                  />
                </Form.Group>
                <Form.Group
                  className="mb-3"
                  controlId="exampleForm.ControlTextarea1">
                  <Form.Label>Mục đích</Form.Label>
                  <Form.Control
                    as="textarea"
                    name="purpose"
                    value={formData.purpose || ""}
                    rows={3}
                    placeholder="Nhập mục đích..."
                    onChange={handleInputChange}
                    required
                  />
                </Form.Group>
                <Form.Group
                  className="mb-3"
                  controlId="exampleForm.ControlTextarea1">
                  <Form.Label>Địa chỉ</Form.Label>
                  <Form.Control
                    as="textarea"
                    name="address"
                    value={formData.address || ""}
                    rows={3}
                    placeholder="Nhập địa chỉ..."
                    onChange={handleInputChange}
                    required
                  />
                </Form.Group>
                <Form.Group
                  className="mb-3"
                  controlId="exampleForm.ControlTextarea1">
                  <Form.Label>Số tiền huy động</Form.Label>
                  <Form.Control
                    name="amountRaised"
                    value={formData.amountRaised || ""}
                    placeholder="Nhập số tiền huy động..."
                    onChange={handleInputChange}
                    required
                  />
                </Form.Group>

                <Form.Group
                  className="mb-3"
                  controlId="exampleForm.ControlTextarea1">
                  <Form.Label>Hình ảnh</Form.Label>
                  <Form.Control
                    onChange={handleImageChange}
                    type="file"
                    multiple="multiple"
                  />
                  {formData.images.length > 0 ? (
                    <ImagePost listImage={formData.images} />
                  ) : (
                    <></>
                  )}
                </Form.Group>
                <Form.Group>
                  <Form.Label>Thời gian bắt đầu</Form.Label>
                  <Form.Control
                    name="startTime"
                    defaultValue={formData.startTime || ""}
                    onChange={handleInputChange}
                    placeholder="Chọn ngày bắt đầu"
                    type="date"
                  />
                  <Form.Label>Thời gian kết thúc</Form.Label>
                  <Form.Control
                    name="endTime"
                    defaultValue={formData.endTime || ""}
                    onChange={handleInputChange}
                    placeholder="Chọn ngày kết thúc"
                    type="date"
                  />
                </Form.Group>

                <Button
                  className="bg-bs-primary bg-color-btn-main mt-[8px]"
                  type="submit"
                  disabled={loading}>
                  {loading === true ? "Đang tải" : "Đăng"}
                </Button>
              </Form>
            </Col>
          </Row>
        </Modal.Body>
      </Modal>
    </>
  );
}

export default CreateAndUpdateProject;
