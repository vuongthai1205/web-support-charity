import { Col, Container, Row } from "react-bootstrap";
import { phone_icon, face_icon, email_icon, youtube_icon, down_icon, tiktok_icon } from "assets/img";
function Topbar() {
  return (
    <section className="topbar">
      <Container>
        <Row>
          <Col lg={6}>
            <Row>
              <Col lg={4}>
                <div className="wrapper-item-topbar">
                  <img src={phone_icon} alt="" className="item-topbar-img" />
                  <span className="item-topbar-text">
                    Liên hệ:{" "}
                    <a href="tel:+84931185459" className="item-top-link">
                      (+84)931185459
                    </a>{" "}
                  </span>
                </div>
              </Col>
              <Col lg={8}>
                <div className="wrapper-item-topbar">
                  <img src={email_icon} alt="" className="item-topbar-img" />
                  <span className="item-topbar-text">
                    Email:{" "}
                    <a
                      href="mailto:giavuong.1205@gmail.com"
                      className="item-top-link">
                      giavuong.1205@gmail.com
                    </a>{" "}
                  </span>
                </div>
              </Col>
            </Row>
          </Col>
          <Col lg={6}>
            <div className="wrapper-item-topbar right">
              <ul className="list-icon-topbar">
                <li className="item-icon-topbar">
                  <a href="#">
                    <img src={face_icon} alt=""/>
                  </a>
                </li>
                <li className="item-icon-topbar">
                  <a href="#">
                    <img src={tiktok_icon} alt=""/>
                  </a>
                </li>
                <li className="item-icon-topbar">
                  <a href="#">
                    <img src={youtube_icon} alt=""/>
                  </a>
                </li>
              </ul>
            </div>
          </Col>
        </Row>
      </Container>
    </section>
  );
}

export default Topbar;
