<%@ taglib prefix="tiles"
           uri="http://tiles.apache.org/tags-tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <link href="<c:url value="/css/sb-admin-2.min.css" />" rel="stylesheet">

        <link href="<c:url value="/vendor/datatables/dataTables.bootstrap4.min.css" /> " rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">

        <title><tiles:insertAttribute name="title" /></title>
    </head>
    <body id="page-top">
        <div id="wrapper">
            <tiles:insertAttribute name="sidebar" />
            <div id="content-wrapper" class="d-flex flex-column">
                <div id="content">
                    <tiles:insertAttribute name="header" />
                    <div class="container-fluid">
                        <tiles:insertAttribute name="content" />
                    </div>

                </div>

                <tiles:insertAttribute name="footer" />
            </div>

        </div>
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Logout Modal-->
        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <a class="btn btn-primary" href="login.html">Logout</a>
                    </div>
                </div>
            </div>
        </div>
        <script src="<c:url value="/vendor/jquery/jquery.min.js" /> "></script>
        <script src="<c:url value="/vendor/bootstrap/js/bootstrap.bundle.min.js" /> "></script>
        <script src="<c:url value="/vendor/jquery-easing/jquery.easing.min.js" /> "></script>

        <!-- Custom scripts for all pages-->
        <script src="<c:url value="/js/sb-admin-2.min.js" /> "></script>

        <!-- Page level plugins -->
        <script src="<c:url value="/vendor/datatables/jquery.dataTables.min.js" />"></script>
        <script src="<c:url value="/vendor/datatables/dataTables.bootstrap4.min.js" /> "></script>

        <!-- Page level custom scripts -->
        <script src="<c:url value="/js/demo/datatables-demo.js" /> "></script>


    </body>
</html>