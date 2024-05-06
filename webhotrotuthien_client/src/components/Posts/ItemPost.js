import { useContext, useEffect, useState } from "react";
import { Button, Card, Col, Form, Row } from "react-bootstrap";
import { MyUserContext } from "../../App";
import CreateAndUpdatePost from "./CreateAndUpdatePost";
import apiConfig, { authApi, endpoints } from "../../config/apiConfig";
import DeletePost from "./DeletePost";
import { Link, useNavigate } from "react-router-dom";
import ListAuction from "../Auctions/ListAuction";
import ImagePost from "./ImagePost";
import parse from "html-react-parser";
import { formatCurrency } from "functions";
function ItemPost({ onPostUpdate, post, xuLyThichBaiViet }) {
  const [user, dispatch] = useContext(MyUserContext);
  const [like, setLike] = useState(false);
  const [action, setAction] = useState(false);
  const [listAuction, setListAuction] = useState([]);
  const [formPrice, setFormPrice] = useState({
    idPost: post.id,
    price: "",
  });
  const [auctioned, setAuctioned] = useState(false);
  const [formComment, setFormComment] = useState({
    content: "",
  });
  const [isEditing, setIsEditing] = useState(false);
  const navigate = useNavigate();
  useEffect(() => {
    const handleLike = () => {
      if (
        user !== null &&
        post.likePost.map((element) => element.username).includes(user.username)
      ) {
        setLike(true);
      } else {
        setLike(false);
      }
    };

    const handleShowAction = () => {
      if (user !== null && post.user.username === user.username) {
        setAction(true);
      } else {
        setAction(false);
      }
    };

    const handleAuctioned = () => {
      if (
        user !== null &&
        listAuction.map((element) => element.username).includes(user.username)
      ) {
        setAuctioned(true);
      } else {
        setAuctioned(false);
      }
    };

    handleShowAction();
    handleLike();
    handleAuctioned();
  }, [user, post, listAuction]);

  const handleLikeClick = () => {
    setLike((prevLike) => !prevLike);
    xuLyThichBaiViet(post.id);
  };

  const [show, setShow] = useState(false);
  const [postItem, setPostItem] = useState({});
  const handleClose = () => setShow(false);

  const handleShow = async (id) => {
    try {
      const response = await apiConfig.get(`${endpoints["posts"]}${id}/`);
      const data = response.data;
      setPostItem(data);
      if (response.status === 200) {
        console.log("getPostById oke");
      } else {
        console.log("error");
      }
    } catch (ex) {
      console.log(ex);
    }
    setShow(true);
  };

  const [showDelete, setShowDelete] = useState(false);
  const handleCloseDelete = () => {
    setShowDelete(false);
  };

  const handleShowDelete = () => {
    setShowDelete(true);
  };

  const [showAuction, setShowAuction] = useState(false);
  const handleCloseAuction = () => {
    setShowAuction(false);
  };

  const handleShowAuction = async (id) => {
    try {
      const response = await authApi().get(`${endpoints["auction"]}${id}/`);
      setListAuction(response.data);
    } catch (ex) {
      console.log(ex);
    }

    setShowAuction(true);
  };

  const handleSubmitStartPrice = async (e) => {
    e.preventDefault();
    try {
      const response = await authApi().post(endpoints["auction"], formPrice);

      if (response.status === 201) {
        setFormPrice({ price: "" });
        alert("Đấu giá thành công");
      } else {
        alert("Không thành công");
      }
    } catch (ex) {
      if (ex.response.status === 409) {
        alert("Bạn đã đấu giá rồi, Vui lòng đợi thông tin của chủ bài viết");
      } else if (ex.response.status === 400) {
        alert("Vui lòng cho giá cao hơn giá khởi điểm");
      } else if (ex.response.status === 500) {
        alert("Lỗi máy chủ");
      } else {
        alert("Không thành công");
      }
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormPrice((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleContentChange = (e) => {
    const { name, value } = e.target;
    setFormComment((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handlSubmitComment = async (e) => {
    e.preventDefault();
    if (!user) {
      navigate("/login");
    } else {
      try {
        const response = await authApi().post(
          `${endpoints["comment"]}${post.id}/`,
          formComment
        );
        if (response.status === 201) {
          setFormComment({ content: "" });
          onPostUpdate();
        } else {
          console.log("lỗi rồi ");
        }
      } catch (ex) {
        alert(ex);
      }
    }

    // Đây bạn có thể sử dụng giá trị của 'content' cho mục đích của bạn

    // Nếu bạn muốn làm gì đó khác với giá trị này, bạn có thể thực hiện ở đây
  };

  const handleDeleteComment = async (id) => {
    try {
      const response = await authApi().delete(`${endpoints["comment"]}${id}/`);
      if (response.status === 200) {
        onPostUpdate();
      } else {
        console.log("lỗi rồi ");
      }
    } catch (ex) {
      alert(ex);
    }
  };

  return (
    <Col>
      <Card>
        {action ? (
          <div className="action_post">
            <Button
              className=" bg-color-btn-success"
              variant="success"
              onClick={() => {
                handleShow(post.id);
                setIsEditing(true); // Set isEditing to true when the button is clicked
              }}>
              Sửa
            </Button>

            {isEditing && (
              <CreateAndUpdatePost
                onPostUpdate={onPostUpdate}
                post={postItem}
                showPopup={show}
                closePopup={handleClose}
              />
            )}

            <Button
              variant="danger"
              className="bg-color-btn-danger"
              onClick={handleShowDelete}>
              Xóa
            </Button>

            <DeletePost
              onPostUpdate={onPostUpdate}
              post={post.id}
              showPopup={showDelete}
              closePopup={handleCloseDelete}
            />
            {(user !== null && post.auctionStatus.id === 2) ||
            (user !== null && post.auctionStatus.id === 3) ? (
              <Button
                className="bg-color-btn-info"
                variant="info"
                onClick={() => {
                  handleShowAuction(post.id);
                }}>
                Danh sách người đã đấu giá
              </Button>
            ) : (
              <></>
            )}

            <ListAuction
              listAuction={listAuction}
              showPopup={showAuction}
              closePopup={handleCloseAuction}
            />
          </div>
        ) : (
          <></>
        )}
        {post.imagesPost.length > 0 ? (
          <ImagePost listImage={post.imagesPost} />
        ) : (
          <></>
        )}

        <Card.Body>
          <Card.Title>
            <Link to={`/post-auction/${post.id}`}>{post.title}</Link>
          </Card.Title>
          <div>{post.content ? parse(post.content) : ""}</div>
          {user !== null && post.auctionStatus.id === 2 ? (
            auctioned === false ? (
              <div className="my-[18px]">
                <Card.Text>
                  <span className="font-bold mr-[8px]">
                    Ngày bắt đầu đấu giá:
                  </span>
                  {post.startAuctionTime || "Chủ bài viết không đề cập tới ngày bắt đầu"}
                </Card.Text>
                <Card.Text>
                  <span className="font-bold mr-[8px]">
                    Ngày kết thúc đấu giá:{" "}
                  </span>
                  { post.endAuctionTime || "Chủ bài viết không đề cập tới ngày kết thúc"}
                </Card.Text>
                <Card.Text>
                  <span className="font-bold mr-[8px]">Giá Khởi điểm: </span>
                  {formatCurrency(post.startPrice) }
                </Card.Text>
                <Form onSubmit={handleSubmitStartPrice} className="my-3">
                  <Form.Group>
                    <Form.Control
                      pattern="[0-9]*"
                      type="text"
                      value={formPrice.price}
                      name="price"
                      onChange={handleInputChange}
                      placeholder="Nhập giá bạn muốn đấu giá (Vui lòng lớn hơn giá khởi điểm)"
                    />
                  </Form.Group>
                  <Button className="mt-2 bg-color-btn-main" type="submit">
                    Gửi
                  </Button>
                </Form>
              </div>
            ) : (
              <h4 className="my-[18px] italic text-color-btn-success">Bạn đã đấu giá bài viết</h4>
            )
          ) : (
            <></>
          )}
          {user !== null && post.auctionStatus.id === 3 ? (
            <h5 className="my-[18px] italic text-color-btn-success">Đã kết thúc đấu giá</h5>
          ) : (
            <></>
          )}

          <Row lg={3}>
            <Col>
              <Button
                onClick={handleLikeClick}
                className="mr-3 bg-color-btn-success"
                variant={like ? "success" : "info"}>
                {like ? "Đã Thích" : "Thích"}
              </Button>
              {post.likePost.map((item, id) => (
                <h5 key={id}>{item.username}</h5>
              ))}
            </Col>
            <Col>
              <Form onSubmit={handlSubmitComment}>
                <Form.Control
                  required
                  className="mb-2"
                  type="text"
                  name="content"
                  value={formComment.content}
                  onChange={handleContentChange}></Form.Control>
                <Button
                  type="submit"
                  variant="secondary"
                  className="bg-color-btn-secondary">
                  Bình luận
                </Button>
              </Form>
            </Col>
          </Row>
          <Row>
            <Col>
              <ul className="comment-list">
                {post.listComment.map((item, id) => {
                  return (
                    <li key={id} className="comment-item">
                      <img
                        width={100}
                        src={item.image}
                        className="comment-avt"
                        alt=""
                      />
                      <div>
                        <Link
                          className="text-color-btn-main"
                          to={`/profile?iduser=${item.idUser}`}>
                          <h3 className="comment-user-name">{item.username}</h3>
                        </Link>

                        <h4 className="comment-content">{item.content}</h4>
                      </div>
                      {user !== null && user.username === item.username ? (
                        <Button
                          onClick={() => {
                            if (
                              window.confirm(
                                "Bạn có chắc muốn xóa bình luận này ?"
                              )
                            ) {
                              handleDeleteComment(item.id);
                            }
                          }}
                          className="btn-delete-cmt bg-color-btn-danger"
                          variant="danger">
                          Xóa
                        </Button>
                      ) : (
                        <></>
                      )}
                    </li>
                  );
                })}
              </ul>
            </Col>
          </Row>
        </Card.Body>
      </Card>
    </Col>
  );
}

export default ItemPost;
