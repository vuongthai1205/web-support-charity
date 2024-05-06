<%-- 
    Document   : login
    Created on : Aug 6, 2023, 2:08:42 PM
    Author     : vuongthai1205
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

        <div class="col-xl-10 col-lg-12 col-md-9">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                                </div>
                                <c:if test="${param.error != null}">
                                    <h1 style="font-size: 12px; color: red">Please check your account again</h1>
                                </c:if>
                                <c:url value="/login" var="action" />
                                <form class="user" method="post" action="${action}">
                                    <div class="form-group">
                                        <input type="text"name="username" class="form-control form-control-user"
                                               id="exampleInputEmail" 
                                               placeholder="Enter Username...">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="password" class="form-control form-control-user"
                                               id="exampleInputPassword" placeholder="Password">
                                    </div>
                                    <input type="submit" class="btn btn-primary btn-user btn-block" value="Login" />
                                        
                                </form>
                                <hr>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>
