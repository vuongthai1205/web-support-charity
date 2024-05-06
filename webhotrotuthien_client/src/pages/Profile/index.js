import React, { useEffect, useState } from "react";
import { MyUserContext } from "../../App";
import { useContext } from "react";
import { Button } from "react-bootstrap";
import CreateAndUpdatePost from "../../components/Posts/CreateAndUpdatePost";
import ListPost from "../../components/Posts/ListPost";
import { useSearchParams } from "react-router-dom";
import { authApi, endpoints } from "../../config/apiConfig";
import Report from "../../components/Report";
import { Suspense } from "react";
function Profile() {
  const [count, setCount] = useState(0);
  const [isCreatingPost, setIsCreatingPost] = useState(false);
  const [q] = useSearchParams();
  const [u, setU] = useState({});
  const [user, dispatch] = useContext(MyUserContext);
  let idUser = q.get("iduser");
  console.log(idUser);
  const handlePostCreated = () => {
    setCount(count + 1);
  };

  useEffect(() => {
    if (idUser !== null && idUser !== "") {
      const handleGetUser = async () => {
        const response = await authApi().get(`${endpoints["user"]}${idUser}/`);
        setU(response.data);
      };
      handleGetUser();
    }
  }, [idUser]);

  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const [showReport, setShowReport] = useState(false);

  const handleCloseReport = () => setShowReport(false);
  const handleShowReport = () => setShowReport(true);
  return (
    <>
      <div>
        <h2 className="font-['Calistoga'] text-[25px] text-center">
          Trang cá nhân
        </h2>
        {user.username !== u.username ? <div className="mb-[20px]">
          <button
            className="bg-color-btn-danger text-white p-[8px] rounded-[12px]"
            onClick={handleShowReport}>
            Báo cáo
          </button>
          <Suspense>
            <Report id={u.id} showPopup={showReport} closePopup={handleCloseReport}/>
          </Suspense>
        </div> : <></>}
        
        <div>
          {u ? (
            <>
              <img height={200} src={u.avatar} alt="User Avatar" />
              <p>
                Họ và tên: {u.lastName ? u.lastName : "Chưa cập nhật"}{" "}
                {u.firstName ? u.firstName : "Chưa cập nhật"}
              </p>
              <p>
                Ngày sinh:{" "}
                {u.dateOfBirth ? u.dateOfBirth : "Chưa cập nhật"}
              </p>
              <p>
                Giới tính:{" "}
                {u.gender
                  ? u.gender === 1
                    ? "Nam"
                    : "Nữ"
                  : "Chưa cập nhật"}
              </p>
              <p>Email: {u.email ? u.email : "Chưa cập nhật"}</p>
              <p>Điện thoại: {u.phone ? u.phone : "Chưa cập nhật"}</p>
            </>
          ) : (
            <>
              <p>Hãy đăng nhập</p>
            </>
          )}
        </div>
      </div>
      <Button
        className="my-4 bg-color-btn-main"
        variant="primary"
        onClick={() => {
          handleShow();
          setIsCreatingPost(true); // Set isEditing to true when the button is clicked
        }}>
        Tạo bài viết
      </Button>

      {isCreatingPost && (
        <CreateAndUpdatePost
          onPostCreated={handlePostCreated}
          showPopup={show}
          closePopup={handleClose}
        />
      )}

      <ListPost onPostCreated={handlePostCreated} onCount={count} />
    </>
  );
}

export default Profile;
