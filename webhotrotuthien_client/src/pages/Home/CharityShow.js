import { img_show } from "assets/img";
import { Col, Container, Row } from "react-bootstrap";

function CharityShow() {
  return ( <section className="charity_show bg-bg-color-content pt-[38px] pb-[81px]">

    <Container>
      <Row>
        <Col lg={4}>
          <div>
            <h2 className="text-white text-[45px] font-['Calistoga'] mt-[36px] mb-[52px]">Chiến dịch</h2>
            <span className="text-[24px] text-white">Chiến dịch từ thiện là một nỗ lực tổ chức hoặc cá nhân thực hiện để hỗ trợ các vấn đề xã hội hoặc những tình huống khó khăn. Thông qua chiến dịch từ thiện, người tham gia thường cố gắng gây quỹ, quyên góp, hoặc tham gia vào các hoạt động nhằm giúp đỡ những người cần sự hỗ trợ. Chiến dịch từ thiện có thể xoay quanh nhiều mục tiêu, bao gồm việc cung cấp thực phẩm, quần áo, chăm sóc y tế, giáo dục, hoặc hỗ trợ trong tình huống khẩn cấp như thảm họa tự nhiên.</span>
          </div>
        </Col>
        <Col lg={4}>
          <div className="flex justify-center">
            <img src={img_show} alt="" width={300}/>
          </div>
        </Col>
        <Col lg={4}>
        <div className="flex justify-center">
            <img src={img_show} alt="" width={300}/>
          </div>
        </Col>
      </Row>
    </Container>
  </section> );
}

export default CharityShow;