<%-- 
    Document   : index
    Created on : Sep 15, 2023, 2:39:56 PM
    Author     : vuongthai1205
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">Welcome to website 
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            admin
        </sec:authorize> 
        <sec:authorize access="hasRole('ROLE_MEMBER')">
            member
        </sec:authorize> 
    </h1>

</div>
