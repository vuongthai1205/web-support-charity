import { faAngleLeft, faAngleRight } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { img_gioithieu, img_muctieu } from "assets/img";
import HeaderPage from "pages/components/HeaderPage";
import { Col, Container, Row } from "react-bootstrap";

function About() {
  return (
    <div>
      <HeaderPage title={"Giới thiệu"} />
      <section className="py-[60px]">
        <Container>
          <Row>
            <Col lg={12}>
              <div>
                <h2 className="font-['Calistoga'] text-text-color-title text-[45px] text-center mb-[23px]">
                  Giới thiệu
                </h2>
                <hr className="w-[173px] mb-[11px] mx-auto text-color-line"></hr>
                <hr className="w-[116px] mx-auto text-color-line"></hr>
                <h3 className="text-center text-[11px] uppercase my-[38px]">
                WEBSITE HỖ TRỢ CÁC HOẠT ĐỘNG TỪ THIỆN
                </h3>
              </div>
            </Col>
            <Col lg={6}>
              <div>
                <img src={img_gioithieu} alt="" />
              </div>
            </Col>
            <Col lg={6}>
              <div>
                <p className="text-[12px]">Trang web hỗ trợ từ thiện của tôi là một nền tảng trực tuyến được tạo ra với mục tiêu chung là xây dựng một cộng đồng năng động và đoàn kết, nơi mọi người có cơ hội chia sẻ và tham gia vào các hoạt động từ thiện. Vào những năm gần đây, hoạt động từ thiện đã trở nên ngày càng quan trọng và phổ biến trong xã hội. Tuy nhiên, việc kết nối người chủ dự án từ thiện, những người muốn đóng góp, và những người cần sự hỗ trợ vẫn còn gặp nhiều khó khăn. Đó là lý do tại sao trang web hỗ trợ từ thiện của chúng tôi ra đời.</p>
                <p className="text-[12px] mt-[93px]">Trang web này giúp cho việc từ thiện trở nên minh bạch, nhanh chóng, và dễ dàng hơn bao giờ hết. Người dùng có thể tạo và quản lý các dự án từ thiện, đăng bài viết để kể về những khoảnh khắc đáng nhớ và đăng sản phẩm để đấu giá với mục đích từ thiện. Trang web cũng cung cấp các công cụ quản lý cho người chủ dự án và cho phép người dùng đăng ký tham gia vào các dự án từ thiện một cách dễ dàng.</p>
              </div>
            </Col>
          </Row>
        </Container>
      </section>
      <hr></hr>
      <section className="py-[64px]">
        <Container>
          <Row className="px-[15px]">
            <Col className="p-0" lg={12}>
              <div>
                <h3 className="text-center mb-[8px]">THÀNH LẬP TỪ NĂM</h3>
                <h2 className="font-['Calistoga'] text-text-color-title text-[45px] text-center mb-[65px]">
                  MỤC TIÊU CỦA CHÚNG TÔI
                </h2>
              </div>
            </Col>
            <Col className="p-0" lg={6}>
              <div className="relative border-r-[1px] border-color-line-aim">
                <div className="relative inline-block">
                  <img
                    src={img_muctieu}
                    alt=""
                    className="object-cover h-[186px]"
                  />
                  <span className="text-[50px] absolute top-[10%] left-[98%]">
                    <FontAwesomeIcon
                      className="text-bg-color-content"
                      icon={faAngleRight}
                    />
                  </span>
                </div>
                <span className="w-[60px] h-[60px] border-[1px] inline-flex items-center justify-center font-['Calistoga'] text-[32px] border-black rounded-full absolute top-[30%] right-0 translate-y-[-50%] translate-x-[50%] bg-white">
                  01
                </span>
              </div>
            </Col>
            <Col className="p-0" lg={6}></Col>
            <Col className="p-0" lg={6}>
            <div className="relative border-r-[1px] border-color-line-aim h-full">
                
              </div>
            </Col>
            <Col className="p-0" lg={6}>
              <div className="relative flex justify-end">
                <div className="relative">
                  <img
                    src={img_muctieu}
                    alt=""
                    className="object-cover h-[186px]"
                  />
                  <span className="text-[50px] absolute top-[10%] right-[98%]">
                    <FontAwesomeIcon
                      className="text-bg-color-content"
                      icon={faAngleLeft}
                    />
                  </span>
                </div>
                <span className="w-[60px] h-[60px] border-[1px] inline-flex items-center justify-center font-['Calistoga'] text-[32px] border-black rounded-full absolute top-[30%] left-0 translate-y-[-50%] translate-x-[-50%] bg-white">
                  02
                </span>
              </div>
            </Col>
            <Col className="p-0" lg={6}>
              <div className="relative border-r-[1px] border-color-line-aim">
                <div className="relative inline-block">
                  <img
                    src={img_muctieu}
                    alt=""
                    className="object-cover h-[186px]"
                  />
                  <span className="text-[50px] absolute top-[10%] left-[98%]">
                    <FontAwesomeIcon
                      className="text-bg-color-content"
                      icon={faAngleRight}
                    />
                  </span>
                </div>
                <span className="w-[60px] h-[60px] border-[1px] inline-flex items-center justify-center font-['Calistoga'] text-[32px] border-black rounded-full absolute top-[30%] right-0 translate-y-[-50%] translate-x-[50%] bg-white">
                  03
                </span>
              </div>
            </Col>
            <Col className="p-0" lg={6}></Col>
          </Row>
        </Container>
      </section>
    </div>
  );
}

export default About;
