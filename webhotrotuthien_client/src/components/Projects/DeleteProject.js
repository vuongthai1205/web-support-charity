import { Button, Modal } from "react-bootstrap";
import { authApi, endpoints } from "../../config/apiConfig";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function DeleteProject({ project, showPopup, closePopup }) {
  const [error, setError] = useState("")
  const navigate = useNavigate();
  const handleDeleteProject = async (id) => {
    console.log(id)
    try{

      const response = await authApi.delete(`${endpoints["project"]}${id}/`)
      if(response.status === 200){
        navigate("/project-charity");
        closePopup()
      }
      else {
        setError("Có lỗi xảy ra bạn chưa xóa được dự án")
        console.log("delete not oke")
      }
    }
    catch(ex){
      console.log(ex)
    }

  }
  return (
    <>
      <Modal show={showPopup} onHide={closePopup}>
        <Modal.Header closeButton>
          <Modal.Title>Xác nhận xóa dự án</Modal.Title>
        </Modal.Header>
        <Modal.Body> <span>{error}</span>
          Bạn hãy xác nhận lại lần nữa là có chắn chắn xóa dự án không</Modal.Body>
        <Modal.Footer>
          <Button className="bg-color-btn-secondary" variant="secondary" onClick={closePopup}>
            Đóng
          </Button>
          <Button variant="primary" className="bg-color-btn-main" onClick={() =>{handleDeleteProject(project)}}>
            Xóa dự án
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default DeleteProject;
