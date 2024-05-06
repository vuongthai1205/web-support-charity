import { Suspense, lazy, useState } from "react";
import { Link } from "react-router-dom";
import { authApi, endpoints } from "config/apiConfig";
const EditCommentProject = lazy(() => import('./EditCommentProject'));
function ListComment({listComment, user, onUpdateComment, message}) {
    const [showEditCmt, setShowEditCmt] = useState({});
    const handleCloseEditCmt = (index) => {
        // Đóng popup chỉ cho mục có index tương ứng
        setShowEditCmt({ ...showEditCmt, [index]: false });
    };
    const handleShowEditCmt = (index) => {
        // Hiển thị popup chỉ cho mục có index tương ứng
        setShowEditCmt({ ...showEditCmt, [index]: true });
    };
    const handleDeleteComment = async (id) => {
        try {
            const response = await authApi().delete(`${endpoints['commentProject']}${id}/`);
            if (response.status === 200) {
                onUpdateComment();
            } else {
                console.log('lỗi rồi ');
            }
        } catch (ex) {
            alert(ex);
        }
    };
    return (
        <ul>
            {listComment !== null && listComment.length > 0 ? (
                listComment.map((item, id) => {
                    return (
                        <li key={item.id} className="border-b-[1px] border-black mb-[12px]">
                            <div className="flex items-center">
                                <img src={item.image} alt="" width={100} height={100} />
                                <div className="flex flex-column ml-[12px]">
                                    <Link className="text-color-btn-main" to={`/profile?iduser=${item.idUser}`}>
                                        <h3 className="comment-user-name">{item.username}</h3>
                                    </Link>
                                    <h4 className="comment-content">{item.content}</h4>
                                    {item.images.map((e, i) => {
                                        return <img key={i} src={e.link} width={100} />;
                                    })}
                                </div>

                                {user !== null && user.username === item.username ? (
                                    <div className="ml-auto flex flex-column">
                                        <button
                                            className="mb-[8px] inline-block p-[12px] text-white font-bold cursor-pointer rounded-[12px] bg-color-btn-info"
                                            onClick={() => handleShowEditCmt(id)}>
                                            Sửa
                                        </button>
                                        {showEditCmt && (
                                            <Suspense>
                                                <EditCommentProject
                                                    comment={item}
                                                    showPopup={showEditCmt[id] || false}
                                                    onUpdateProject={onUpdateComment}
                                                    closePopup={() => handleCloseEditCmt(id)}
                                                />
                                            </Suspense>
                                        )}
                                        <button
                                            onClick={() => {
                                                if (window.confirm('Bạn có chắc muốn xóa bình luận này ?')) {
                                                    handleDeleteComment(item.id);
                                                }
                                            }}
                                            className="mb-[8px] inline-block p-[12px] text-white font-bold cursor-pointer rounded-[12px] bg-color-btn-danger">
                                            Xóa
                                        </button>
                                    </div>
                                ) : (
                                    <></>
                                )}
                            </div>
                        </li>
                    );
                })
            ) : (
                <li key={'hi1'}>{message}</li>
            )}
        </ul>
    );
}

export default ListComment;
