import Carousel from "react-bootstrap/Carousel";
import { anh_bia, arrow } from "assets/img";
import { Link } from "react-router-dom";
function Slider() {
  return (
    <Carousel>
      <Carousel.Item>
        <div className="img-slider">
          <img width={"100%"} src={anh_bia} alt="" />
        </div>

        <Carousel.Caption className="flex justify-center flex-column items-center">
          <h3 className="slider_title font-['Calistoga'] text-[24px]">
            Tham gia cùng chúng tôi
          </h3>
          <p className="font-['Calistoga'] text-[45px]">
            LAN TỎA HẠNH PHÚC <br></br> TRAO GỬI YÊU THƯƠNG
          </p>
          <Link to={"/post-auction"} className="inline-flex items-center text-[18px] bg-btn-color px-[18px] py-[12px] mt-[20px]">
            TÌM HIỂU THÊM
            <img src={arrow} alt="" className="ml-[4px]" />
          </Link>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <div className="img-slider">
          <img width={"100%"} src={anh_bia} alt="" />
        </div>
        <Carousel.Caption className="flex justify-center flex-column items-center">
          <h3 className="slider_title font-['Calistoga'] text-[24px]">
            Tham gia cùng chúng tôi
          </h3>
          <p className="font-['Calistoga'] text-[45px]">
            LAN TỎA HẠNH PHÚC <br></br> TRAO GỬI YÊU THƯƠNG
          </p>
          <Link to={"/post-auction"} className="inline-flex items-center text-[18px] bg-btn-color px-[18px] py-[12px] mt-[20px]">
            TÌM HIỂU THÊM
            <img src={arrow} alt="" className="ml-[4px]" />
          </Link>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <div className="img-slider">
          <img width={"100%"} src={anh_bia} alt="" />
        </div>
        <Carousel.Caption className="flex justify-center flex-column items-center">
          <h3 className="slider_title font-['Calistoga'] text-[24px]">
            Tham gia cùng chúng tôi
          </h3>
          <p className="font-['Calistoga'] text-[45px]">
            LAN TỎA HẠNH PHÚC <br></br> TRAO GỬI YÊU THƯƠNG
          </p>
          <Link to={"/post-auction"} className="inline-flex items-center text-[18px] bg-btn-color px-[18px] py-[12px] mt-[20px]">
            TÌM HIỂU THÊM
            <img src={arrow} alt="" className="ml-[4px]" />
          </Link>
        </Carousel.Caption>
      </Carousel.Item>
    </Carousel>
  );
}

export default Slider;
