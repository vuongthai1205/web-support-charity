import { authApi, endpoints } from 'config/apiConfig';
import { useEffect } from 'react';
import { useState } from 'react';
import { Button, Form, Modal } from 'react-bootstrap';

function RequestFormWithdraw({ id, showPopup, closePopup }) {
    const [requestFormWithdraw, setRequestFormWithdraw] = useState({
        moneyMount: 0,
        typeRequest: 'rut_tien',
    });
    const [message, setMessage] = useState('');

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setRequestFormWithdraw((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };
    const handleSubmit = async () => {
        if (requestFormWithdraw.moneyMount > 0) {
            try {
                const response = await authApi().post(endpoints['request-form'], requestFormWithdraw);
                if (response.status === 201) {
                    alert('Gửi phiếu yêu cầu rút tiền thành công, vui lòng đợi phản hồi từ admin');
                    closePopup();
                } else if(response.status === 204) {
                    alert('Số tiền hiện tại không thể rút');
                }
                else{
                    alert("Thất bại")
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
                    <Modal.Title>Xác nhận rút tiền</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label className="font-bold">Số tiền</Form.Label>
                        <Form.Control
                            name="moneyMount"
                            value={requestFormWithdraw.moneyMount || ''}
                            type="text"
                            placeholder="Nhập số tiền muốn rút..."
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

export default RequestFormWithdraw;
