
import apiConfig, { endpoints } from "config/apiConfig";
import {  useEffect, useState } from "react";
import { Card, Col, Placeholder, Row } from "react-bootstrap";
import iconLoading from "../../assets/images/Loading_icon.gif";
import {  useSearchParams } from "react-router-dom";
import ItemProject from "./ItemProject";

function ListProject(props) {
  const [loading, setLoading] = useState(true);
  const [q] = useSearchParams();
  const [projects, setProjects] = useState([]);
  useEffect(() => {
    const renderListProject = () => {
      try {
        let e = endpoints["project"];
        let page = q.get("page");
        let kw = q.get("kw");
        let iduser= q.get("iduser")
        if (page !== null && page !== "") {
          e = `${e}?page=${page}`;
        } else if (kw !== null && kw !== "") {
          e = `${e}?kw=${kw}`;
        }else if (iduser !== null && iduser !== "") {
          e = `${e}?iduser=${iduser}`;
        } else {
          e = `${e}?page=1`;
        }

        apiConfig.get(`${e}`).then((response) => {
          setLoading(false);
          setProjects(response.data);
        });
      } catch (ex) {
        console.log(ex);
      }
    };
    renderListProject();
  }, [props.onCount, q]);
  if (loading === true) {
    return (
      <Row xs={1} className="g-4">
        <Col>
          <Card>
            <Card.Img alt="" src={iconLoading} />
            <Card.Body>
              <Placeholder as={Card.Title} animation="glow">
                <Placeholder xs={6} />
              </Placeholder>
              <Placeholder as={Card.Text} animation="glow">
                <Placeholder xs={7} /> <Placeholder xs={4} />{" "}
                <Placeholder xs={4} /> <Placeholder xs={6} />{" "}
                <Placeholder xs={8} />
              </Placeholder>
              <Placeholder.Button variant="primary" xs={6} />
            </Card.Body>
          </Card>
        </Col>
      </Row>
    );
  }
  return (
    <Row xs={1} className="g-4">
      {projects.map((project, idx) => {
        return (
          <ItemProject
            onProjectUpdate={props.onProjectCreated}
            key={idx}
            project={project}
          />
        );
      })}
    </Row>
  );
}

export default ListProject;