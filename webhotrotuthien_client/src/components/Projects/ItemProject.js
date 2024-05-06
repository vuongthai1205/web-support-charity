import { faAngleRight, faArrowRight } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import ImagePost from "components/Posts/ImagePost";
import { formatCurrency } from "functions";
import { Col, Row } from "react-bootstrap";
import { Link } from "react-router-dom";

function ItemProject({ onProjectUpdate, project }) {
  return (
    <>
      <Col lg={12}>
        <Row>
          <Col lg={4}>
            <div className="p-[12px] border-[1px] rounded-l-[12px]">
              <img
                src={project.images[0].link}
                alt=""
                className="w-[100%] img-project h-[200px] object-cover"
              />
            </div>
          </Col>
          <Col lg={8}>
            <div className="p-[12px] border-[1px] rounded-r-[12px] h-full flex flex-column">
              <h2 className="font-['Calistoga'] font-bold text-[18px] mb-[12px]">Tên dự án: <span>{project.nameProject}</span></h2>
              <h3 className="mb-[4px]">Mục đích: <span>{project.purpose}</span></h3>
              <h3 className="mb-[4px]">Địa điểm: <span>{project.address}</span></h3>
              <h3 className="mb-[4px]">Số tiền huy động: <span>{formatCurrency(project.amountRaised)}</span></h3>
              <Link to={`/project-charity/${project.id}`} className="font-bold underline underline-offset-1 text-color-btn-main mt-auto ml-auto">Tìm hiểu thêm <FontAwesomeIcon icon={faArrowRight} /> </Link>
            </div>
          </Col>
        </Row>
      </Col>
    </>
  );
}

export default ItemProject;
