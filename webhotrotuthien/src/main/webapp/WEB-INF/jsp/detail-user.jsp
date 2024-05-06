<%-- 
    Document   : detail-user
    Created on : Aug 7, 2023, 9:39:48 AM
    Author     : vuongthai1205
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>${user.maThanhVien}</h1>
<c:url value="/detail-user" var="action" />
<form:form method="post" action="${action}" modelAttribute="user" enctype="multipart/form-data">
    <form:hidden path="maThanhVien" />
    <form:hidden path="anhDaiDien" />
    <form:hidden path="matKhau" />
    <form:hidden path="ngaySinh"/>
    <form:hidden path="ngayTao"/>
    <form:errors path="*" element="div" /> 
    <div class="form-floating mb-3 mt-3">
        <label for="name">User Name</label>
        <form:input type="text" class="form-control" 
                    path="tenDangNhap" placeholder="Enter username" />
        <form:errors path="tenDangNhap" cssClass="text-danger" />
    </div>
    <div class="form-floating">
        <label for="des">First Name</label>
        <form:input type="text" class="form-control" 
                    path="ten" placeholder="Enter first name" />
    </div>
    <div class="form-floating">
        <label for="des">Last Name</label>
        <form:input type="text" class="form-control" 
                    path="ho" placeholder="Enter last name" />
    </div>
    <div class="form-floating">
        <label for="des">Phone</label>
        <form:input type="phone" class="form-control" 
                    path="soDienThoai" placeholder="Enter phone" />
    </div>
    <div class="form-floating">
        <label for="des">Email</label>
        <form:input type="email" class="form-control" 
                    path="email" placeholder="Enter email" />
    </div>
    
    <div class="form-floating">
        <label for="des">Password</label>
        <form:input type="password" class="form-control" 
                    path="fakePassword" placeholder="Enter password" />
    </div>
    <div class="form-floating">
        <label for="des">Date of birth ${user.ngaySinh}</label>
        <form:input type="date" class="form-control" 
                    path="dateString" placeholder="Enter date of birth" />
    </div>
    <div class="form-floating">
        <label for="des">Gender</label>
        <form:select class="form-select"  path="gioiTinh">
            <c:choose>
                <c:when test="${user.gioiTinh == 1}">
                    <option value="1" selected>Male</option>
                    <option value="0">Female</option>
                </c:when>
                <c:otherwise>
                    <option value="1" >Male</option>
                    <option value="0" selected>Female</option>
                </c:otherwise>
            </c:choose>
        </form:select>
    </div>
    <div class="form-floating">
        <label for="des">Address</label>
        <form:input type="text" class="form-control" 
                    path="diaChi" placeholder="Enter address" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <label for="file">Avatar</label>
        <form:input type="file" class="form-control" 
                    path="file" id="file"  />

        <c:if test="${user.anhDaiDien != null}">
            <img src="${user.anhDaiDien}" width="120" />
        </c:if>
    </div>
    <div class="form-floating mb-3 mt-3">


        <c:forEach items="${user.quyenSet}" var="a">
            <form:select class="form-select" path="role">
                <c:forEach items="${roles}" var="i">
                    <c:choose>
                        <c:when test="${i.maQuyen == a.maQuyen}">
                            <option value="${i.maQuyen}" selected>${i.tenQuyen}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${i.maQuyen}">${i.tenQuyen}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>


            </form:select>


        </c:forEach>


        <label for="auctionStatus" class="form-label">User Role</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <button class="btn btn-info" type="submit">
            Update User
        </button>
    </div>

</form:form>
<a href="<c:url value="/delete-user/${user.maThanhVien}"/>">
    Delete User
</a>