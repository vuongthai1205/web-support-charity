import { MyUserContext } from "App";
import apiConfig, { authApi, endpoints } from "config/apiConfig";
import { formatCurrency } from "functions";
import { useContext, useState } from "react";
import { useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import UpdateJoinProject from "./UpdateJoinProject";
import { Spinner } from "react-bootstrap";
import { toast } from "react-toastify";

function ListJoinProject({ project }) {
  const [list, setList] = useState([]);
  const [user, dispatch] = useContext(MyUserContext);
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();
  useEffect(() => {
    const showList = async () => {
      setIsLoading(true);
      if (!user) {
        // Người dùng chưa xác thực, chuyển hướng đến trang đăng nhập
        navigate("/login");
        toast.error("Vui lòng đăng nhập để thực hiện tính năng")
        return;
      }
      try {
        const response = await authApi().get(
          `${endpoints["joinProject"]}${project.id}/`
        );
        console.log("from list join"+response)
        if (response.status === 200) {
          setList(response.data);
        } else {
          console.log("error");
        }
        setIsLoading(false);
      } catch (ex) {
        console.log("from list join"+ex);
        setIsLoading(false);
      }
    };

    showList();
  }, [project.id]);

  const handleDeleteJoinProject = async (idUser, idProject) => {
    try {
      const response = await authApi().delete(
        `${endpoints["joinProject"]}${idProject}/${idUser}/`
      );

      if (response.status === 204) {
        navigate("/project-charity");
      } else {
        console.log("error");
      }
    } catch (ex) {
      console.log(ex);
    }
  };

  const [showEdit, setShowEdit] = useState({}); // Sử dụng một đối tượng để lưu trạng thái cho từng mục

  const handleCloseEdit = (index) => {
    // Đóng popup chỉ cho mục có index tương ứng
    setShowEdit({ ...showEdit, [index]: false });
  };

  const handleShowEdit = (index) => {
    // Hiển thị popup chỉ cho mục có index tương ứng
    setShowEdit({ ...showEdit, [index]: true });
  };
  return (
    <ul className="mt-[20px]">
      {isLoading ? (
        <Spinner animation="border" size="sm"/>
      ) : list.length > 0 ? (
        list.map((item, i) => {
          return (
            <li
              key={i}
              className="p-[12px] bg-bg-color-content rounded-[20px] mb-[12px]">
              <div className="flex items-center">
                <img src={item.user.avatar} alt="" width={100} height={100} />
                <div className="flex flex-column ml-[12px]">
                  <Link
                    className="text-color-btn-main"
                    to={`/profile?iduser=${item.user.id}`}>
                    Tên: {item.user.username}
                  </Link>
                  <span>Vai Trò: {item.role}</span>
                  <span>
                    Số tiền đóng góp: {formatCurrency(item.contributionAmount)}
                  </span>
                  <span>Các đóng góp khác: {item.contributionOther}</span>
                </div>
                {user.username === item.user.username ||
                user.username === project.user.username ? (
                  <div className="ml-auto flex flex-column">
                    <span
                      className="mb-[8px] inline-block p-[12px] text-white font-bold cursor-pointer rounded-[12px] bg-color-btn-info"
                      onClick={() => handleShowEdit(i)}>
                      Sửa
                    </span>
                    <UpdateJoinProject
                      project={item}
                      showPopup={showEdit[i] || false} // Sử dụng trạng thái của mục cụ thể
                      closePopup={() => handleCloseEdit(i)}
                    />

                    <span
                      className="mb-[8px] inline-block p-[12px] text-white font-bold cursor-pointer rounded-[12px] bg-color-btn-danger"
                      onClick={() => {
                        if (
                          window.confirm("Bạn có chắc muốn xóa đăng ký tham của bạn ?")
                        ) {
                          handleDeleteJoinProject(
                            item.user.id,
                            item.project.id
                          );
                        }
                      }}>
                      Xóa
                    </span>
                  </div>
                ) : (
                  <></>
                )}
              </div>
            </li>
          );
        })
      ) : (
        <span>Chưa có người đăng ký</span>
      )}
    </ul>
  );
}

export default ListJoinProject;
