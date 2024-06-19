<%-- 
    Document   : sidebar
    Created on : Aug 3, 2023, 10:10:20 PM
    Author     : vuongthai1205
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<c:url value="/"/>">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Charity Support Website</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    
    <!-- Nav Item - Tables -->
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/stats"/>">
            <i class="fas fa-fw fa-table"></i>
            <span>Stats Post</span></a>
    </li>
    
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/stats-project"/>">
            <i class="fas fa-fw fa-table"></i>
            <span>Stats Project</span></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/post"/>">
            <i class="fas fa-fw fa-table"></i>
            <span>Post Manager</span></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/user-manager"/>">
            <i class="fas fa-fw fa-table"></i>
            <span>User manager</span></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/charityproject/"/>">
            <i class="fas fa-fw fa-table"></i>
            <span>Manager Charity Project</span></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/report/"/>">
            <i class="fas fa-fw fa-table"></i>
            <span>Manager Report</span></a>
    </li>
    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>