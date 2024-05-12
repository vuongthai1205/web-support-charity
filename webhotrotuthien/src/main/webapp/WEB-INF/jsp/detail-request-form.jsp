<%-- 
    Document   : detail-request-form
    Created on : May 10, 2024, 8:34:49 PM
    Author     : vuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>${requestForm.maPhieuYeuCau}</h1>
<c:url value="/update-request-form" var="action" />

<form:form method="post" action="${action}" modelAttribute="requestForm" enctype="multipart/form-data">
    <form:hidden path="maPhieuYeuCau" />
    <form:hidden path="maThanhVien.maThanhVien" />
    <form:hidden path="soTien" />
    <form:hidden path="loaiPhieu" />
    <form:hidden path="ngayTao" />
    <form:hidden path="ngayCapNhat" />
    <p>Ten thanh vien: ${requestForm.maThanhVien.ten}</p>
    <p>So tien: ${requestForm.soTien}</p>
    <p>Loai phieu: ${requestForm.loaiPhieu}</p>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select"  path="xacNhan">
            <c:choose>
                <c:when test="${requestForm.xacNhan == 1}">
                    <option value="1" selected>Approved</option>
                    <option value="0">Not Approved</option>
                </c:when>
                <c:otherwise>
                    <option value="1" >Approved</option>
                    <option value="0" selected>Not Approved</option>
                </c:otherwise>
            </c:choose>
        </form:select>

        <label for="auctionStatus" class="form-label">Xac nhan</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <button class="btn btn-info" type="submit">
            Cap nhat yeu cau
        </button>
    </div>

</form:form>
<a href="<c:url value="/delete-request-form/${requestForm.maPhieuYeuCau}"/>">
    Xoa yeu cau
</a>
