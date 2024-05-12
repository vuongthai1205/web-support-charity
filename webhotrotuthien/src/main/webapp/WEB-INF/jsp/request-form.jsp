<%-- 
    Document   : request-form
    Created on : May 10, 2024, 5:51:53 PM
    Author     : vuong
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>



<div class="card shadow mb-4">
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Ma Phieu Yeu Cau</th>
                        <th>Thanh vien</th>
                        <th>So tien nap</th>
                        <th>Xac nhan</th>
                        <th>Loai phieu</th>
                        <th>Ngay tao</th>
                        <th>Ngay cap nhat</th>
                        <th>Hanh dong </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestForms}" var="requestForm">
                        <tr>
                            <td><c:out value="${requestForm.maPhieuYeuCau}"/></td>
                            <td><c:out value="${requestForm.maThanhVien.ten}"/></td>
                            <td><c:out value="${requestForm.soTien}"/></td>

                            <td>
                                <c:choose>
                                    <c:when test="${requestForm.xacNhan == 1}">
                                        Da xac nhan
                                    </c:when>
                                    <c:otherwise>
                                        Chua xac nhan
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td><c:out value="${requestForm.loaiPhieu}"/></td>
                            <td><c:out value="${requestForm.ngayTao}"/></td>


                            <td><c:out value="${requestForm.ngayCapNhat}"/></td>
                            <td><sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_MEMBER')">
                                    <a href="<c:url value="/request-form/${requestForm.maPhieuYeuCau}"/>">Update</a>
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