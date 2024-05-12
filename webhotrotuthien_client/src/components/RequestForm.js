import { authApi, endpoints } from 'config/apiConfig';
import { useEffect } from 'react';
import { useState } from 'react';
import { Button, Form, Modal } from 'react-bootstrap';

function RequestForm({ id, showPopup, closePopup }) {
    const [requestForm, setRequestForm] = useState({
        moneyMount: 0,
        typeRequest: 'nap_tien',
    });
    const [message, setMessage] = useState('');

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setRequestForm((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };
    const handleSubmit = async () => {
        if (requestForm.moneyMount > 0) {
            try {
                const response = await authApi.post(endpoints['request-form'], requestForm);
                if (response.status === 201) {
                    alert('Gửi phiếu yêu cầu nạp tiền thành công, vui lòng đợi phản hồi từ admin');
                    closePopup();
                    setMessage('')
                } else {
                    alert('Thất bại');
                }
            } catch (ex) {
                console.log(ex);
            }
        } else {
            setMessage('Vui lòng nhập');
        }
    };
    return (
        <>
            <Modal show={showPopup} onHide={closePopup}>
                <Modal.Header closeButton>
                    <Modal.Title>Xác nhận nạp tiền</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label className="font-bold">Số tiền</Form.Label>
                        <Form.Control
                            name="moneyMount"
                            value={requestForm.moneyMount || ''}
                            type="text"
                            placeholder="Nhập số tiền muốn nạp..."
                            onChange={handleInputChange}
                            required
                        />
                        <span style={{ fontSize: '12px', color: 'red' }}>{message}</span>
                    </Form.Group>
                </Modal.Body>
                <Modal.Footer>
                    <Button className="bg-color-btn-secondary" variant="secondary" onClick={closePopup}>
                        Đóng
                    </Button>
                    <Button
                        variant="primary"
                        className="bg-color-btn-main"
                        onClick={() => {
                            handleSubmit();
                        }}>
                        Nạp tiền
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}

export default RequestForm;
