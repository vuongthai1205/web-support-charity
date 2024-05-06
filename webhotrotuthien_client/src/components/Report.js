import { authApi, endpoints } from "config/apiConfig";
import { useEffect } from "react";
import { useState } from "react";
import { Button, Form, Modal } from "react-bootstrap";

function Report({ id, showPopup, closePopup }) {
  const [formReport, setFormReport] = useState({
    idUser: 0,
    reportReason: "",
  });
  useEffect(() => {
    setFormReport((prev) => ({
      ...prev,
      idUser: id,
    }));
  }, [id]);
  const hanldeReportUser = async () => {
    
    try
    {
      const response = await authApi().post(endpoints["report"], formReport)
      if(response.status ===200){
        alert("Báo cáo thành viên thành công")
        closePopup()
      }
      else{
        alert("Thất bại")
      }
    }catch(ex){
      console.log(ex)
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormReport((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };
  return (
    <>
      <Modal show={showPopup} onHide={closePopup}>
        <Modal.Header closeButton>
          <Modal.Title>Xác nhận báo cáo</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <p>Lý do báo cáo</p>
          <Form.Select
            className="mt-[12px]"
            name="reportReason" 
            onChange={handleInputChange}>
            <option>Lựa chọn lý do</option>
            <option value="Dự án lừa đảo">Dự án lừa đảo</option>
            <option value="Bài viết lừa đảo">Bài viết lừa đảo</option>
            <option value="Quấy rối">Quấy rối</option>
          </Form.Select>
        </Modal.Body>
        <Modal.Footer>
          <Button
            className="bg-color-btn-secondary"
            variant="secondary"
            onClick={closePopup}>
            Đóng
          </Button>
          <Button
            variant="primary"
            className="bg-color-btn-main"
            onClick={() => {
              hanldeReportUser();
            }}>
            Báo cáo
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default Report;
