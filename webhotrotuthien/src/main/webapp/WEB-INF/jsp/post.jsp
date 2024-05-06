<%--
    Document   : post
    Created on : Aug 3, 2023, 10:47:49 PM
    Author     : vuongthai1205
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Quản lý bài viết</h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Ma bai viet</th>
                        <th>Nguoi dang</th>
                        <th>Tieu de</th>
                        <th>Noi dung</th>
                        <th>Hinh anh</th>
                        <th>Trang thai dau gia</th>
                        <th>Ngay tao</th>
                        <th>Ngay cap nhat</th>
                        <th>Hanh dong</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${posts}" var="post">
                        <tr>
                            <td><c:out value="${post.maBaiViet}"/></td>
                            <td><c:out value="${post.maThanhVien.ten}"/></td>
                            <td><c:out value="${post.tieuDe}"/></td>
                            <td><c:out value="${post.noiDung}"/></td>
                            <td>
                                <c:forEach items="${post.hinhAnhBaiViets}" var="hinh">
                                    <img class="mt-2" width="120" alt="${post.tieuDe}" src="<c:out value="${hinh.duongDanHinh}"/>"/>
                                </c:forEach>
                                  </td>
                            <td><c:out value="${post.trangThaiDauGia.tenTrangThai}"/></td>
                            <td><c:out value="${post.ngayTao}"/></td>
                            <td><c:out value="${post.ngayCapNhat}"/></td>
                            <td>
                                <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_MEMBER')">
                                    <a href="<c:url value="/detail-post/${post.maBaiViet}"/>">Update</a>
                                </sec:authorize> 
                                <sec:authorize access="hasRole('ROLE_CUSTOMER')">
                                    Ban khong co quyen sua hoac xoa
                                </sec:authorize> 
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="<c:url value="/js/main.js" />"></script>
