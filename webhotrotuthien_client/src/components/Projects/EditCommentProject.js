import { authApi, endpoints } from "config/apiConfig";
import { useState } from "react";
import { Button, Col, Form, Modal, Row, Spinner } from "react-bootstrap";

function EditCommentProject({
  onUpdateProject,
  comment,
  showPopup,
  closePopup,
}) {
  const [formComment, setFormComment] = useState({
    content: comment.content,
  });
  const [isLoading, setIsLoading] = useState(false);
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormComment((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };
  const handleUpdateComent = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      const response = await authApi().put(
        `${endpoints["commentProject"]}${comment.id}/`,
        formComment
      );
      if (response.status === 200) {
        setFormComment({ content: "" });
        onUpdateProject();
        closePopup();
      } else {
        console.log("lỗi rồi ");
      }
      setIsLoading(false);
    } catch (ex) {
      alert(ex);
    }
  };
  return (
    <>
      <Modal show={showPopup} onHide={closePopup}>
        <Modal.Header closeButton>
          <Modal.Title>Cập nhật bình luận</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Row className="mb-5 popup_post">
            <Col>
              <Form onSubmit={handleUpdateComent}>
                <Form.Group
                  className="mb-3"
                  controlId="exampleForm.ControlTextarea1">
                  <Form.Label>Nội dung</Form.Label>
                  <Form.Control
                    as="textarea"
                    name="content"
                    value={formComment.content || ""}
                    rows={3}
                    placeholder="Nhập nội dung..."
                    onChange={handleInputChange}
                    required
                  />
                </Form.Group>
                <Button
                  className="bg-bs-primary bg-color-btn-main mt-[8px]"
                  type="submit"
                  disabled={isLoading}>
                  {isLoading ? (
                    <Spinner animation="border" size="sm" /> // Display a loading spinner
                  ) : (
                    "Cập nhật"
                  )}
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

export default EditCommentProject;
