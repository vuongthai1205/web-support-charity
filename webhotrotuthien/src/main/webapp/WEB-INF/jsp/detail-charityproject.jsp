<%-- 
    Document   : detail-charityproject
    Created on : Oct 4, 2023, 5:50:04 PM
    Author     : vuongthai1205
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>${charityproject.maDuAn}</h1>
<c:url value="/update-charityproject" var="action" />
<form:form method="post" action="${action}" modelAttribute="charityproject" enctype="multipart/form-data">
    <form:hidden path="maDuAn" />
    <form:hidden path="maThanhVienTaoDA.maThanhVien" />
    <form:hidden path="thoiGianBatDau" />
    <form:hidden path="thoiGianKetThuc" />

    <form:hidden path="ngayTao" />
    <form:hidden path="ngayCapNhat" />
    <div class="form-floating mb-3 mt-3">
        <label for="name">Name Project</label>
        <form:input type="text" class="form-control" 
                    path="tenDuAn" id="nameProject" placeholder="Name Project..." />
    </div>
    <div class="form-floating">
        <label for="des">Purpose</label>
        <form:textarea class="form-control" id="purpose"  
                       path="mucDich" placeholder="Purpose"></form:textarea>
        </div>
        <div class="form-floating">
            <label for="des">Address</label>
        <form:input class="form-control"
                    path="diaDiem" placeholder="Address..."></form:input>
        </div>
        <div class="form-floating">
            <label for="des">Amount Money Raised</label>
        <form:input class="form-control"
                    path="soTienHuyDong" placeholder="Amount Money Raised"></form:input>
        </div>
        <div class="form-floating mb-3 mt-3">
            <label for="file">Images</label>
        <form:input type="file" class="form-control" 
                    path="file" id="file" multiple="multiple" />
        <c:forEach items="${charityproject.hinhAnhDuAnList}" var="hinh">
            <img src="${hinh.duongDanHinh}" width="120" />
        </c:forEach>

    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select"  path="daDuyet">
            <c:choose>
                <c:when test="${charityproject.daDuyet == 1}">
                    <option value="1" selected>Approved</option>
                    <option value="0">Not Approved</option>
                </c:when>
                <c:otherwise>
                    <option value="1" >Approved</option>
                    <option value="0" selected>Not Approved</option>
                </c:otherwise>
            </c:choose>
        </form:select>

        <label for="auctionStatus" class="form-label">Browsing Status</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <button class="btn btn-info" type="submit">
            Update Project
        </button>
    </div>

</form:form>
<a href="<c:url value="/delete-charityproject/${charityproject.maDuAn}"/>">
    Delete Project
</a>