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
        <h6 class="m-0 font-weight-bold text-primary">Manager Charity Project</h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>User Create</th>
                        <th>Name Project</th>
                        <th>Purpose</th>
                        <th>Image</th>
                        <th>Start Time</th>
                        <th>End Time</th>
                        <th>Address</th>
                        <th>Amount Raised</th>
                        <th>Browsing Status</th>
                        <th>Create Date</th>
                        <th>Update Date</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${charityprojects}" var="charityproject">
                        <tr>
                            <td><c:out value="${charityproject.maDuAn}"/></td>
                            <td><c:out value="${charityproject.maThanhVienTaoDA.ten}"/></td>
                            <td><c:out value="${charityproject.tenDuAn}"/></td>
                            <td><c:out value="${charityproject.mucDich}"/></td>
                            <td>
                                <c:forEach items="${charityproject.hinhAnhDuAnList}" var="hinh">
                                    <img class="mt-2" width="120" alt="${charityproject.tenDuAn}" src="<c:out value="${hinh.duongDanHinh}"/>"/>
                                </c:forEach>
                            </td>
                            <td><c:out value="${charityproject.thoiGianBatDau}"/></td>
                            <td><c:out value="${charityproject.thoiGianKetThuc}"/></td>
                            <td><c:out value="${charityproject.diaDiem}"/></td>
                            <td><c:out value="${charityproject.soTienHuyDong}"/>
                            <td>
                            <c:choose>
                                    
                                    <c:when test="${charityproject.daDuyet == null}">
                                        None
                                    </c:when>
                                    <c:when test="${charityproject.daDuyet == 1}">
                                        Approved
                                    </c:when>
                                    <c:otherwise>
                                        Not approved yet
                                    </c:otherwise>
                                </c:choose>
                                        </td>
                            <td><c:out value="${charityproject.ngayTao}"/></td>
                            <td><c:out value="${charityproject.ngayCapNhat}"/></td>
                            <td>
                                <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_MEMBER')">
                                    
                                    <a href="<c:url value="/detail-charityproject/${charityproject.maDuAn}"/>">Update</a>
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
