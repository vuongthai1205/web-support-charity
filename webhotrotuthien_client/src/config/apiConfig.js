import axios from 'axios';
import cookie from 'react-cookies';

const SERVER_CONTEXT = '/webhotrotuthien';
const SERVER = 'http://localhost:8080';

export const endpoints = {
    register: `${SERVER_CONTEXT}/api/user/add/`,
    posts: `${SERVER_CONTEXT}/api/post/`,
    login: `${SERVER_CONTEXT}/api/login/`,
    'current-user': `${SERVER_CONTEXT}/api/current-user/`,
    'like-post': `${SERVER_CONTEXT}/api/post/like/`,
    'get-count-pages': `${SERVER_CONTEXT}/api/post/count-pages/`,
    auction: `${SERVER_CONTEXT}/api/auction/`,
    comment: `${SERVER_CONTEXT}/api/post/comment/`,
    commentProject: `${SERVER_CONTEXT}/api/project/comment/`,
    commentProjectPrivate: `${SERVER_CONTEXT}/api/project/comment-private/`,
    user: `${SERVER_CONTEXT}/api/user/`,
    project: `${SERVER_CONTEXT}/api/charity-project/`,
    joinProject: `${SERVER_CONTEXT}/api/join-project/`,
    report: `${SERVER_CONTEXT}/api/report/`,
};

export const authApi = () => {
    return axios.create({
        baseURL: SERVER,
        headers: {
            "Content-Type": "application/json",
            "Authorization":  cookie.load("token")
        }
    })
}



export default axios.create({
    baseURL: SERVER,
});
