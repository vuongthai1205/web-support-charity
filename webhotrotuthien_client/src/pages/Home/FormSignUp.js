import { useRef } from "react";
import { Col, Container, Row } from "react-bootstrap";

function FormSignUp() {
  const handleSubmit = (e) => {
    e.preventDefault();
  };

  const dateInputRef = useRef(null);

  return (
    <section className="form-sign-up bg-bg-color-content py-[60px]">
      <Container>
        <Row>
          <Col lg={6}>
            <div className="h-full flex flex-column justify-center">
              <h2 className="text-[45px] font-['Calistoga'] text-white mb-[41px]">
                TÌNH NGUYỆN VIÊN
              </h2>
              <span className="text-[24px] text-white">Tình nguyện viên là những người tự nguyện đóng góp thời gian, năng lực, và tình cảm của họ để làm việc cho các tổ chức từ thiện, hoạt động xã hội, hoặc các mục tiêu có ý nghĩa xã hội.</span>
            </div>
          </Col>
          <Col lg={6}>
            <div className="bg-color-bg-form py-[17px] px-[27px]">
              <h2 className="text-[39px] text-white text-center font-['Calistoga']">
                Đăng ký tình nguyện viên
              </h2>
              <span className="text-[20px] mb-[16px] mt-[12px] text-center block text-white">
                Thông tin đăng ký
              </span>
              <form
                method="post"
                className="flex flex-column items-center"
                onSubmit={handleSubmit}>
                <input
                  className="bg-bg-color-content w-full px-[12px] py-[7px] mb-[12px]"
                  type="text"
                  name="hovaten"
                  placeholder="Họ và tên"
                />
                <div className="flex w-full">
                  <input
                    className="bg-bg-color-content w-[50%] px-[12px] py-[7px] mb-[12px] mr-[12px]"
                    type="date"
                    name="ngaysinh"
                    placeholder="Ngày sinh"
                  />

                  <input
                    className="bg-bg-color-content w-[50%] px-[12px] py-[7px] mb-[12px]"
                    type="text"
                    name="email"
                    placeholder="Email"
                  />
                </div>
                <input
                  className="bg-bg-color-content w-full px-[12px] py-[7px] mb-[12px]"
                  type="text"
                  name="sodienthoai"
                  placeholder="Số điện thoại"
                />
                <input
                  className="bg-bg-color-content w-full px-[12px] py-[7px] mb-[12px]"
                  type="text"
                  name="ban"
                  placeholder="Ban bạn muốn đăng ký"
                />
                <input
                  className="bg-bg-color-content w-full px-[12px] py-[7px] mb-[12px]"
                  type="text"
                  name="thoigiandangky"
                  placeholder="Thời gian rảnh"
                />
                <input
                  type="submit"
                  className="px-[24px] pt-[15px] pb-[12px] text-color-btn-submit bg-btn-color font-bold "
                  value={"GỬI ĐĂNG KÝ"}
                />
              </form>
            </div>
          </Col>
        </Row>
      </Container>
    </section>
  );
}

export default FormSignUp;
