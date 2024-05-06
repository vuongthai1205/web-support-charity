import { gratidute } from "assets/img";
import { Col, Container, Row } from "react-bootstrap";

function Gratitude() {
  return (
    <section className="gratidute pt-[28px] pb-[61px]">
      <Container>
        <Row>
          <Col lg={12}>
            <h2 className="text-[45px] font-['Calistoga'] text-center text-text-color-title">
              Góc tri ân
            </h2>
            <h3 className="text-[18px] text-center mb-[47px] mt-[6px]">Cảm ơn</h3>
          </Col>
          <Col lg={4}>
            <div className="flex items-center justify-end">
              <img src={gratidute} width={300} alt="" />
            </div>
          </Col>
          <Col lg={4} className="flex items-center justify-center">
            <div>
              <img src={gratidute} width={300} alt="" />
            </div>
          </Col>
          <Col lg={4}>
            <div className="flex items-center justify-start">
              <img src={gratidute} width={300} alt="" />
            </div>
          </Col>
        </Row>
      </Container>
    </section>
  );
}

export default Gratitude;
