import { arrow_black, calendar, news_img, news_img1 } from "assets/img";
import { Col, Container, Row } from "react-bootstrap";
import { Link } from "react-router-dom";

function News() {
  return (
    <section className="news py-[36px]">
      <Container>
        <Row>
          <Col lg={12}>
            <div>
              <h2 className="font-['Calistoga'] text-[45px] text-text-color-title text-center mb-[24px]">
                TIN TỨC
              </h2>
            </div>
          </Col>
          <Col lg={6}>
            <div className="flex justify-end">
              <div className="flex bg-color-item-news flex-column w-[70%]">
                <img src={news_img1} alt=""/>
                <div className="p-[10px] flex flex-column w-full">
                  <h2 className="font-['Calistoga'] text-[26px] mb-[12px]">
                    Tiêu đề
                  </h2>
                  <p className="text-[15px]">Nội dung</p>
                  <Link className="flex items-center text-[15px] mt-auto">
                    Xem thêm{" "}
                    <img className="ml-[4px]" src={arrow_black} alt="" />
                  </Link>
                  <hr className="mt-[14px]"></hr>
                  <p className="flex items-center mt-[10px]">

                  <img className="mr-[4px]" src={calendar} alt=""/>
                  <span>21/09/2023</span>
                  </p>
                </div>
              </div>
            </div>
          </Col>
          <Col lg={6}>
            <div className="">
              <div>
                <div className="flex mb-[30px] bg-color-item-news">
                  <img src={news_img} width={125} alt="" />
                  <div className="p-[10px] flex flex-column w-full">
                    <h2 className="font-['Calistoga'] text-[26px] mb-[12px]">
                      Tiêu đề
                    </h2>
                    <p className="text-[15px]">Nội dung</p>
                    <Link className="flex items-center text-[15px] mt-auto">
                      Xem thêm{" "}
                      <img className="ml-[4px]" src={arrow_black} alt="" />
                    </Link>
                  </div>
                </div>
                <div className="flex mb-[30px] bg-color-item-news">
                  <img src={news_img} width={125} alt="" />
                  <div className="p-[10px] flex flex-column w-full">
                    <h2 className="font-['Calistoga'] text-[26px] mb-[12px]">
                      Tiêu đề
                    </h2>
                    <p className="text-[15px]">Nội dung</p>
                    <Link className="flex items-center text-[15px] mt-auto">
                      Xem thêm{" "}
                      <img className="ml-[4px]" src={arrow_black} alt="" />
                    </Link>
                  </div>
                </div>
                <div className="flex mb-[30px] bg-color-item-news">
                  <img src={news_img} width={125} alt="" />
                  <div className="p-[10px] flex flex-column w-full">
                    <h2 className="font-['Calistoga'] text-[26px] mb-[12px]">
                      Tiêu đề
                    </h2>
                    <p className="text-[15px]">Nội dung</p>
                    <Link className="flex items-center text-[15px] mt-auto">
                      Xem thêm{" "}
                      <img className="ml-[4px]" src={arrow_black} alt="" />
                    </Link>
                  </div>
                </div>
                <div className="flex mb-[30px] bg-color-item-news">
                  <img src={news_img} width={125} alt="" />
                  <div className="p-[10px] flex flex-column w-full">
                    <h2 className="font-['Calistoga'] text-[26px] mb-[12px]">
                      Tiêu đề
                    </h2>
                    <p className="text-[15px]">Nội dung</p>
                    <Link className="flex items-center text-[15px] mt-auto">
                      Xem thêm{" "}
                      <img className="ml-[4px]" src={arrow_black} alt="" />
                    </Link>
                  </div>
                </div>
              </div>
            </div>
          </Col>
        </Row>
      </Container>
    </section>
  );
}

export default News;
