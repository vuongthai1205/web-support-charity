import { email_icon, email_icon_1, face_icon_1, logofooter, map_pin, phone_icon, phone_icon_1, tiktok_icon_1, youtube_icon_1 } from "assets/img";
import { Col, Container, Row } from "react-bootstrap";
import { Link } from "react-router-dom";

function Footer() {
  return ( 
    <section className="footer bg-footer-color py-[27px] text-white mt-[12px]">
      <Container>
        <Row>
          <Col lg={4}>
            <div className="item_footer">
              <div className="wrapper_logo_footer flex items-center">
                <img src={logofooter} alt="" width={50} height={50}/>
                <h5 className="text_logo text-[14px] text-center ml-[8px]">WEBSITE HỖ TRỢ TỪ THIỆN <br/> TRƯỜNG ĐẠI HỌC MỞ TPHCM</h5>
              </div>
              <h5 className="text_more_logo_footer mt-[20px]">ABC</h5>
            </div>
          </Col>
          <Col lg={4}>
            <div className="item_footer">
              <h5 className="title_item_footer text-[18px] py-[4px] mb-[19px] border-b-[1px]">
              Liên hệ
              </h5>
              <ul>
                <li className="flex items-center  mb-[25px]">
                  <div className="w-[24px] h-[24px] mr-[4px] flex items-center justify-center">
                  <img src={map_pin} alt="" className="object-contain"/>
                  </div>
                  
                  <span>Địa chỉ</span>
                </li> 
                <li className="flex items-center mb-[25px]">
                <div className="w-[24px] h-[24px] mr-[4px] flex items-center justify-center">
                  <img src={phone_icon_1} alt="" className="object-contain"/>
                  </div>
                  <span>0931185459</span>
                </li> 
                <li className="flex items-center  mb-[25px]">
                <div className="w-[24px] h-[24px] mr-[4px] flex items-center justify-center">
                  <img src={email_icon_1} alt="" className="object-contain"/>
                  </div>
                  <span>giavuong.1205@gmail.com</span>
                </li> 
              </ul>
            </div>
          </Col>
          <Col lg={4}>
            <div>
              <h5 className="text-[18px] py-[4px] mb-[19px] border-b-[1px]">Kết nối ngay</h5>
              <ul className="flex gap-[16px] mb-[26px]">
                <li>
                  <Link to={"#"}>
                    <img src={face_icon_1} alt=""/>
                  </Link>
                </li>
                <li>
                  <Link to={"#"}>
                    <img src={tiktok_icon_1} alt=""/>
                  </Link>
                </li>
                <li>
                  <Link to={"#"}>
                    <img src={youtube_icon_1} alt=""/>
                  </Link>
                </li>
              </ul>
              <h5 className="text-[18px] py-[4px] mb-[19px] border-b-[1px]">Bài viết mới</h5>
              <ul>
                <li>
                  <Link to={"#"}>ABC</Link>
                </li>
                <li>
                  <Link to={"#"}>ABC</Link>
                </li>
              </ul>
            </div>
          </Col>
        </Row>
      </Container>

    </section>
  );
}

export default Footer;