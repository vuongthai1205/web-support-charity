import { faCalendar, faClock } from '@fortawesome/free-regular-svg-icons';
import {
    faAngleDown,
    faAngleRight,
    faLocationCrosshairs,
    faPiggyBank,
    faThumbTack,
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { MyUserContext } from 'App';
import ImagePost from 'components/Posts/ImagePost';
import apiConfig, { authApi, endpoints } from 'config/apiConfig';
import { formatCurrency, formatDate } from 'functions';
import { useEffect } from 'react';
import { useContext } from 'react';
import { useState } from 'react';
import { Button, Form, Spinner } from 'react-bootstrap';
import { Link, useNavigate, useParams } from 'react-router-dom';
import DeleteProject from './DeleteProject';
import CreateAndUpdateProject from './CreateAndUpdateProject';
import RegisterProject from './RegisterProject';
import { lazy } from 'react';
import { Suspense } from 'react';
import { getDownloadURL, getStorage, ref, uploadBytesResumable } from 'firebase/storage';
import appFirebase from 'config/firebase';
import { ACCESS_COMMENT } from 'utils/constant';
import ListComment from './ListComment';
import { toast } from 'react-toastify';
import { EditorState, convertToRaw } from 'draft-js';
import draftToHtml from 'draftjs-to-html';
import { Editor } from 'react-draft-wysiwyg';
import { useWebSocketContext } from 'contexts/useWebSocketContext';

const ListJoinProject = lazy(() => import('./ListJoinProject'));

function DetailProject() {
    const { sendMessage, lastMessage, readyState } = useWebSocketContext();
    const [loading, setLoading] = useState(false);
    const { projectId } = useParams();
    const [project, setProject] = useState({
        id: '',
        nameProject: '',
        purpose: '',
        address: '',
        startTime: '',
        endTime: '',
        amountRaised: '',
        listComment: [],
        user: {},
        images: [],
        createAt: '',
        updateAt: '',
    });

    const [user, dispatch] = useContext(MyUserContext);
    const navigate = useNavigate();
    const [showListJoinProject, setShowListJoinProject] = useState(false);
    const [isRegisterProject, setIsRegisterProject] = useState(false);
    const [showDelete, setShowDelete] = useState(false);
    const [count, setCount] = useState(0);
    const [formComment, setFormComment] = useState({
        content: '',
        images: [],
    });
    const [listCommentPrivate, setListCommentPrivate] = useState([]);
    const [activeTab, setActiveTab] = useState(ACCESS_COMMENT.PUBLIC);
    const [messageError, setMessageError] = useState('');
    const [editorState, setEditorState] = useState(EditorState.createEmpty());
    // Function to handle tab change
    const handleTabChange = async (tab) => {
        setActiveTab(tab);
        if (activeTab === ACCESS_COMMENT.PUBLIC) {
            if (!user) {
                // Người dùng chưa xác thực, chuyển hướng đến trang đăng nhập
                navigate('/login');
                toast.error('Vui lòng đăng nhập để thực hiện tính năng');
                return;
            }
            try {
                const response = await authApi().get(`${endpoints.commentProjectPrivate}${projectId}/`);

                if (response.status === 200) {
                    setListCommentPrivate(response.data);
                } else {
                    console.log(response.error);
                }
            } catch (ex) {
                if (ex.response) {
                    setMessageError('Không thể xem các bình luận khi bạn chưa tham gia');
                } else {
                    setMessageError('Chưa có bình luận');
                }
            }
        }
    };

    const handleCloseDelete = () => {
        setShowDelete(false);
    };

    const handleShowDelete = () => {
        setShowDelete(true);
    };

    const [showRegisterProject, setShowRegisterProject] = useState(false);
    const handleCloseRegisterProject = () => {
        setShowRegisterProject(false);
    };

    const handleShowRegisterProject = () => {
        setShowRegisterProject(true);
    };

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = async (id) => {
        setShow(true);
    };

    const handleContentChange = (e) => {
        const { name, value } = e.target;
        setFormComment((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    useEffect(() => {
        const handleShowPost = async () => {
            try {
                const response = await apiConfig.get(`${endpoints['project']}${projectId}/`);
                const data = response.data;

                if (response.status === 200) {
                    setProject({
                        id: data.id,
                        nameProject: data.nameProject,
                        purpose: data.purpose,
                        address: data.address,
                        startTime: data.startTime,
                        endTime: data.endTime,
                        amountRaised: data.amountRaised,
                        listComment: data.listComment,
                        user: data.user,
                        images: data.images,
                        createAt: data.createAt,
                        updateAt: data.updateAt,
                    });
                } else {
                    console.log('error');
                }
            } catch (ex) {
                console.log(ex);
            }
        };

        handleShowPost();
    }, [count]);
    const handleSubmitComment = async (e) => {
        e.preventDefault();
        if (!user) {
            navigate('/login');
        }
        try {
            
            if (activeTab === ACCESS_COMMENT.PUBLIC) {
                const response = await authApi().post(`${endpoints['commentProject']}${project.id}/`, formComment);
                if (response.status === 201) {
                    setFormComment({ content: '' });
                    sendMessage(
                        JSON.stringify({
                            to: project?.user.username,
                            content: `${user.username} vừa bình luận dự án ${project.nameProject} của bạn`,
                        })
                    );
                    handleProjectUpdate();
                } else {
                    console.log('lỗi rồi ');
                }
            } else {
                const response = await authApi().post(
                    `${endpoints['commentProjectPrivate']}${project.id}/`,
                    formComment
                );
                if (response.status === 201) {
                    sendMessage(
                        JSON.stringify({
                            to: project?.user.username,
                            content: `${user.username} vừa bình luận dự án ${project.nameProject} của bạn`,
                        })
                    );
                    setFormComment({ content: '' });
                    handleProjectUpdate();
                } else {
                    console.log('lỗi rồi ');
                }
            }
        } catch (ex) {
            alert(ex);
        }
    };
    const handleProjectUpdate = () => {
        setCount(count + 1);
    };

    const handleImageChange = async (e) => {
        setFormComment((prevData) => ({
            ...prevData,
            images: [],
        }));
        for (let i = 0, f; (f = e.target.files[i]); i++) {
            if (f) {
                const image = f;
                const storageRef = getStorage(appFirebase);
                const imageRef = ref(storageRef, `images/${image.name}`);
                const uploadTask = uploadBytesResumable(imageRef, image);
                uploadTask.on(
                    'state_changed',
                    (snapshot) => {
                        // Observe state change events such as progress, pause, and resume
                        // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
                        const progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
                        console.log('Upload is ' + progress + '% done');
                        switch (snapshot.state) {
                            case 'paused':
                                console.log('Upload is paused');

                                break;
                            case 'running':
                                setLoading(true);
                                console.log('Upload is running');
                                break;
                        }
                    },
                    (error) => {
                        // Handle unsuccessful uploads
                    },
                    () => {
                        // Handle successful uploads on complete
                        // For instance, get the download URL: https://firebasestorage.googleapis.com/...
                        getDownloadURL(uploadTask.snapshot.ref).then((downloadURL) => {
                            setLoading(false);
                            setFormComment((prevFormData) => ({
                                ...prevFormData,
                                images: [...prevFormData.images, { link: downloadURL }],
                            }));
                        });
                    }
                );
            }
        }
    };

    const onEditorStateChange = (editorState) => {
        setEditorState(editorState);
        setFormComment((prevFormData) => ({
            ...prevFormData,
            content: draftToHtml(convertToRaw(editorState.getCurrentContent())),
        }));
    };

    return (
        <>
            <div className="relative">
                {user !== null && user.username === project.user.username ? (
                    <div className="ml-auto flex absolute top-0 right-0">
                        <span
                            className="mr-[8px] inline-block p-[12px] text-white font-bold cursor-pointer rounded-[12px] bg-color-btn-info"
                            onClick={handleShow}>
                            Sửa
                        </span>
                        <CreateAndUpdateProject showPopup={show} closePopup={handleClose} project={project} />
                        <span
                            className="mr-[8px] inline-block p-[12px] text-white font-bold cursor-pointer rounded-[12px] bg-color-btn-danger"
                            onClick={handleShowDelete}>
                            Xóa
                        </span>
                        <DeleteProject showPopup={showDelete} closePopup={handleCloseDelete} project={project.id} />
                    </div>
                ) : (
                    <></>
                )}

                <h2 className="font-['Calistoga'] text-[40px] mb-[20px]">{project.nameProject}</h2>
                <h3 className="mb-[12px]">
                    <FontAwesomeIcon icon={faCalendar} className="mr-[8px] w-[30px]" />
                    {formatDate(project.createAt)} được tạo bởi{' '}
                    <Link className="text-color-btn-main" to={`/profile?iduser=${project.user.id}`}>
                        {project.user.firstName}
                    </Link>
                </h3>
                <hr className="mb-[12px]" />
                <div className="my-[20px]">
                    <ImagePost listImage={project.images} />
                </div>
                <h3 className="mb-[20px]">
                    <FontAwesomeIcon icon={faThumbTack} className="mr-[8px] w-[30px]" />
                    <strong>Mục đích:</strong> {project.purpose}
                </h3>
                <h3 className="mb-[20px]">
                    <FontAwesomeIcon icon={faLocationCrosshairs} className="mr-[8px] w-[30px]" />
                    <strong>Địa chỉ:</strong> {project.address}
                </h3>
                <h3 className="mb-[20px]">
                    <FontAwesomeIcon icon={faClock} className="mr-[8px] w-[30px]" />
                    <strong>Thời gian bắt đầu:</strong> {project.startTime ? project.startTime : 'Chưa cập nhật'}
                </h3>
                <h3 className="mb-[20px]">
                    <FontAwesomeIcon icon={faClock} className="mr-[8px] w-[30px]" />
                    <strong>Thời gian kết thúc:</strong> {project.endTime ? project.endTime : 'Chưa cập nhật'}
                </h3>
                <h3 className="mb-[20px]">
                    <FontAwesomeIcon icon={faPiggyBank} className="mr-[8px] w-[30px]" />
                    <strong>Số tiền đã huy động:</strong> {formatCurrency(project.amountRaised)}
                </h3>
            </div>
            <div>
                {user !== null && user.username !== project.user.username ? (
                    <>
                        <Button
                            disabled={isRegisterProject}
                            onClick={handleShowRegisterProject}
                            className="mb-[8px] bg-[#308c5a] inline-block p-[12px] text-white font-bold cursor-pointer rounded-[12px] bg-color-btn-info">
                            Đăng ký tham gia
                        </Button>
                        <RegisterProject
                            project={project}
                            showPopup={showRegisterProject}
                            closePopup={handleCloseRegisterProject}
                        />
                    </>
                ) : (
                    <></>
                )}
            </div>
            <div className="bg-bg-color-donate p-[12px] rounded-[20px]">
                <div className="flex items-center justify-between">
                    <label htmlFor="checkShowListJoinProject" className="cursor-pointer font-['Calistoga'] text-[25px]">
                        Danh sách người tham gia
                    </label>
                    {!showListJoinProject ? (
                        <FontAwesomeIcon icon={faAngleRight} className="text-[20px]" />
                    ) : (
                        <FontAwesomeIcon icon={faAngleDown} className="text-[20px]" />
                    )}
                </div>

                <input
                    type="checkbox"
                    id="checkShowListJoinProject"
                    checked={showListJoinProject}
                    onChange={(e) => setShowListJoinProject(e.target.checked)}
                    className="fixed z-[-1] top-0 left-0 opacity-0"
                />
                {showListJoinProject && (
                    <Suspense>
                        <ListJoinProject project={project} />
                    </Suspense>
                )}
            </div>
            <div className="mt-[12px]">
                <h3 className="my-[8px] font-['Calistoga'] text-color-btn-main">
                    Hãy gửi bình luận của bạn về dự án này
                </h3>
                <form onSubmit={handleSubmitComment}>
                    <Editor
                        editorState={editorState}
                        toolbarClassName="toolbarClassName"
                        wrapperClassName="wrapperClassName"
                        editorClassName="editorClassName"
                        onEditorStateChange={onEditorStateChange}
                        placeholder="Nhập nội dung..."
                    />
                    <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                        <Form.Label>Chèn thêm hình ảnh (nếu cần)</Form.Label>
                        <Form.Control onChange={handleImageChange} type="file" multiple="multiple" />
                        {formComment.images?.length > 0 ? <ImagePost listImage={formComment.images} /> : <></>}
                    </Form.Group>
                    <input
                        type="submit"
                        className="bg-color-btn-info px-[12px] py-[8px] mt-[12px] font-bold rounded-[12px]"
                        value={loading === true ? 'Đang tải' : 'Gửi'}
                        disabled={loading}
                    />
                </form>
            </div>
            <div className="mt-[12px]">
                <h2 className="font-['Calistoga'] text-[25px] mb-[20px]">Danh sách bình luận</h2>
                <div>
                    {/* Tab navigation */}
                    <ul className="nav nav-tabs">
                        <li className="nav-item">
                            <a
                                className={`nav-link ${activeTab === ACCESS_COMMENT.PRIVATE ? 'active' : ''}`}
                                role="button"
                                onClick={() => handleTabChange(ACCESS_COMMENT.PRIVATE)}>
                                Trong nhóm
                            </a>
                        </li>
                        <li className="nav-item">
                            <a
                                className={`nav-link ${activeTab === ACCESS_COMMENT.PUBLIC ? 'active' : ''}`}
                                role="button"
                                onClick={() => handleTabChange(ACCESS_COMMENT.PUBLIC)}>
                                Công khai
                            </a>
                        </li>
                    </ul>

                    {/* Tab content */}
                    <div className="tab-content mt-3">
                        <div className={`tab-pane ${activeTab === ACCESS_COMMENT.PRIVATE ? 'active' : 'd-none'}`}>
                            <ListComment
                                listComment={listCommentPrivate}
                                user={user}
                                onUpdateComment={handleProjectUpdate}
                                message={messageError}
                            />
                        </div>
                        <div className={`tab-pane ${activeTab === ACCESS_COMMENT.PUBLIC ? 'active' : 'd-none'}`}>
                            <ListComment
                                listComment={project.listComment}
                                user={user}
                                message={messageError}
                                onUpdateComment={handleProjectUpdate}
                            />
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default DetailProject;
