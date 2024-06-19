<%-- 
    Document   : user-manager
    Created on : Aug 7, 2023, 7:56:12 AM
    Author     : vuongthai1205
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>



<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">User manager</h6>
        <a href="<c:url value="/add-user"/>">Add user</a>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Id User</th>
                        <th>User name</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Date Of birth</th>
                        <th>Avatar</th>
                        <th>Gender</th>
                        <th>Address</th>
                        <th>Create at</th>
                        <th>Update at</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td><c:out value="${user.maThanhVien}"/></td>
                            <td><c:out value="${user.tenDangNhap}"/></td>
                            <td><c:out value="${user.ten}"/></td>
                            <td><c:out value="${user.ho}"/></td>
                            <td><c:out value="${user.ngaySinh}"/></td>
                            <td><img width="200" alt="${user.tenDangNhap}" src="<c:out value="${user.anhDaiDien}"/>"/>  </td>
                            <td>
                                <c:choose>

                                    <c:when test="${user.gioiTinh == null}">
                                        None
                                    </c:when>
                                    <c:when test="${user.gioiTinh == 1}">
                                        Male
                                    </c:when>
                                    <c:otherwise>
                                        Female
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td><c:out value="${user.diaChi}"/></td>
                            <td><c:out value="${user.ngayTao}"/></td>
                            <td><c:out value="${user.ngayCapNhat}"/></td>
                            <td>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <c:url value="/api/user/${user.maThanhVien}" var="apiDel"/>
                                    <a href="<c:url value="/detail-user/${user.maThanhVien}"/>">Update</a>
                                </sec:authorize> 
                                <sec:authorize access="hasRole('ROLE_MEMBER')">
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