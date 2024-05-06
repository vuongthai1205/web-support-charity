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
                        <th>User</th>
                        <th>User reported</th>
                        <th>Reason</th>
                        <th>Create at</th>
                        <th>Update at</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${reports}" var="report">
                        <tr>
                            <td><c:out value="${report.thanhVien.ten}"/></td>
                            <td><c:out value="${report.thanhVien1.ten}"/></td>
                            <td><c:out value="${report.baoCaoThanhVienPK.lyDoBaoCao}"/></td>
                            <td><c:out value="${report.ngayTao}"/></td>
                            <td><c:out value="${report.ngayCapNhat}"/></td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>