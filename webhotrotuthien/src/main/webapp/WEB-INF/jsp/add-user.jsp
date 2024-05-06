<%-- 
    Document   : add-user
    Created on : Aug 7, 2023, 10:11:48 PM
    Author     : vuongthai1205
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/add-user" var="action" />
<form:form method="post" action="${action}" modelAttribute="user" enctype="multipart/form-data">
    <h1 style="font-size: 16px; color: red">${error}</h1>
    <form:errors path="*" element="div" /> 
    <div class="form-floating mb-3 mt-3">
        <label for="name">User Name</label>
        <form:input type="text" class="form-control" 
                    path="tenDangNhap" placeholder="Enter User name" />
        
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
        <label for="des">Pass word</label>
        <form:input type="password" class="form-control" 
                    path="matKhau" placeholder="Enter password" />
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
        <label for="des">Date of birth</label>
        <form:input type="date" class="form-control" 
                    path="dateString" placeholder="Enter date of birth" />
    </div>
    <div class="form-floating">
        <label for="des">Gender</label>
        <form:select class="form-select" path="gioiTinh">
            <option value="1">Male</option>
            <option value="0">Female</option>
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
                path="file" id="file" multiple="multiple" />
        
       
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" name="auctionStatus" path="role">
            <c:forEach items="${roles}" var="a">
                        <option value="${a.maQuyen}">${a.tenQuyen}</option>
                
                
                    
            </c:forEach>
        </form:select>

        <label for="auctionStatus" class="form-label">User Role</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <button class="btn btn-info" type="submit">
            Add User
        </button>
    </div>

</form:form>