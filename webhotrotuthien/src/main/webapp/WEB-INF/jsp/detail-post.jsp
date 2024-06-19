<%-- 
    Document   : detail-post
    Created on : Aug 4, 2023, 10:18:27 AM
    Author     : vuongthai1205
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>${post.maBaiViet}</h1>
<c:url value="/detail-post" var="action" />
<form:form method="post" action="${action}" modelAttribute="post" enctype="multipart/form-data">
    <form:hidden path="maBaiViet" />
    <form:hidden path="maThanhVien.maThanhVien" />
    <form:hidden path="thoiGianBatDau" />
    <form:hidden path="thoiGianKetThuc" />
    <form:hidden path="ngayTao" />
    <form:hidden path="ngayCapNhat" />
    <div class="form-floating mb-3 mt-3">
        <label for="name">Tieu de bai viet</label>
        <form:input type="text" class="form-control" 
                    path="tieuDe" id="title" placeholder="Tieu de bai viet" />
    </div>
    <div class="form-floating">
        <label for="des">Noi dung</label>
        <form:textarea class="form-control" id="content_post" name="text" 
                       path="noiDung" placeholder="Noi dung"></form:textarea>
    </div>
    <div class="form-floating mb-3 mt-3">
        <label for="file">Anh bai viet</label>
        <form:input type="file" class="form-control" 
                    path="file" id="file" multiple="multiple" />
        <c:forEach items="${post.hinhAnhBaiViets}" var="hinh">
            <img src="${hinh.duongDanHinh}" width="120" />
        </c:forEach>
        
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="auctionStatus" name="auctionStatus" path="trangThaiDauGia">
            <c:forEach items="${auctionStatuses}" var="a">
                <c:choose>
                    <c:when test="${a.maTrangThaiDauGia == post.trangThaiDauGia.maTrangThaiDauGia}">
                        <option value="${a.maTrangThaiDauGia}" selected>${a.tenTrangThai}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${a.maTrangThaiDauGia}">${a.tenTrangThai}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>

        <label for="auctionStatus" class="form-label">Trang thai dau gia cua bai viet</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <button class="btn btn-info" type="submit">
            Cap nhat bai viet
        </button>
    </div>

</form:form>
<a href="<c:url value="/delete-post/${post.maBaiViet}"/>">
            Delete Post
        </a>
            <h6 class="m-0 font-weight-bold text-primary">List user auctioned</h6>
<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Price</th>
                        <th>Is Winner</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${auctions}" var="auction">
                        <tr>
                            <td><c:out value="${auction.username}"/></td>
                            <td><c:out value="${auction.price}"/></td>
                            <td><c:choose>

                                    <c:when test="${auction.winnerAuctioned == true}">
                                        Winner
                                    </c:when>
                                    <c:otherwise>
                                        Not win
                                    </c:otherwise>
                                </c:choose></td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>