<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 루트 경로 -->
<c:set var="hContext" value="${pageContext.request.contextPath }"></c:set>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<!-- Custom fonts for this template-->
<link href="${hContext}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

<!-- Custom styles for this template-->
<link href="${hContext}/resources/css/sb-admin-2.min.css" rel="stylesheet">
<link href="${hContext}/resources/css/jquery-ui.min.css" rel="stylesheet">

	<!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${hContext}/view/main.do">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">OMG</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
        <a class="nav-link" href="${hContext}/view/main.do">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Dashboard</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">
	 
	  <!-- 사원 검색 Heading -->
      <div class="sidebar-heading">
        사원 검색
      </div>
      <!-- Nav Item - Tables -->
    <li class="nav-item">
      <a class="nav-link" href="${hContext}/employee/employee_list.do">
        <i class="fas fa-fw fa-search"></i>
        <span>사원 검색</span></a>
    </li>
    
	<div class="sidebar-heading">board</div>
	<li class="nav-item">
		<a id="boardMemu" class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#boardCategory" aria-expanded="true" aria-controls="boardCategory">
			<i class="fas fa-fw fa-table"></i>
			<span>게시판</span>
		</a>
		<div id="boardCategory" class="collapse" aria-labelledby="headingSetting" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<a id="board_10" class="collapse-item" href="${hContext}/board/doSelectList.do?div=10">공지사항</a>
				<a id="board_20" class="collapse-item" href="${hContext}/board/doSelectList.do?div=20">자유게시판</a>
				<a id="board_20" class="collapse-item" href="${hContext}/board/doSelectList.do?div=11000">전략기획게시판</a>
				<a id="board_20" class="collapse-item" href="${hContext}/board/doSelectList.do?div=12000">경영지원게시판</a>
				<a id="board_20" class="collapse-item" href="${hContext}/board/doSelectList.do?div=13000">기술영업게시판</a>
				<a id="board_20" class="collapse-item" href="${hContext}/board/doSelectList.do?div=14000">영업게시판</a>
			<div class="collapse-divider"></div>
			</div>
		</div>
	</li>
	<!-- Nav Item - Charts -->
	<li id="chattingPlace" class="nav-item">
		<a class="nav-link" href="${hContext}/chat/room.do">
		<i class="fas fa-fw fa-comments"></i>
		<span>채팅</span></a>
	</li>
      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        Approval
      </div>

      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-fw fa-folder"></i>
          <span>문서 메뉴</span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Custom Utilities:</h6>
            <a class="collapse-item" href="${hContext}/document/document.do">등록</a>
            <a class="collapse-item" href="${hContext}/document/document_manager.do">결재</a>
          </div>
        </div>
      </li>


      <!-- Nav Item - Pages Collapse Menu -->
      <%-- <li class="nav-item">
        <a id="Pages" class="nav-link" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
          <i class="fas fa-fw fa-folder"></i>
          <span>Pages</span>
        </a>
        <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Login Screens:</h6>
            <a class="collapse-item" href="login.html">Login</a>
            <a class="collapse-item" href="register.html">Register</a>
            <a class="collapse-item" href="forgot-password.html">Forgot Password</a>
            <div class="collapse-divider"></div>
            <h6 class="collapse-header">Other Pages:</h6>
			<a class="collapse-item" href="404.html">404 Page</a>
			<a id="blank" class="collapse-item" href="${hContext}/view/blank.do">Blank Page</a>
         </div>
       </div>
     </li> --%>
	<!-- Heading -->
	<div class="sidebar-heading">
		Schedule
	</div>
     <!-- 일정 -->
		<li class="nav-item">
		  	<a id="setting" class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#scheduler" aria-expanded="true" aria-controls="scheduler">
		    	<i class="fas fa-fw fa-calendar-check"></i>
		    	<span>일정관리</span>
			</a>
			<div id="scheduler" class="collapse" aria-labelledby="headingSetting" data-parent="#accordionSidebar">
		      	<div class="bg-white py-2 collapse-inner rounded">
					<a id="schedule_all" class="collapse-item" href="${hContext}/schedule/doSelectList.do?category_id=1">사내 일정</a>
					<a id="schedule_dept" class="collapse-item" href="${hContext}/schedule/doSelectList.do?category_id=2">부서 일정</a>
					<a id="schedule_private" class="collapse-item" href="${hContext}/schedule/doSelectList.do?category_id=3">개인 일정</a>
					<div class="collapse-divider"></div>
		     	</div>
		   </div>
		</li>
		
		<!-- Divider -->
		<hr class="sidebar-divider d-none d-md-block">
	<c:if test="${sessionScope.employee.auth==9 }">
      <!-- Heading -->
      <div class="sidebar-heading">
        관리자 메뉴
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a id="setting" class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#adminSetting" aria-expanded="true" aria-controls="adminSetting">
          <i class="fas fa-fw fa-wrench"></i>
          <span>관리</span>
        </a>
        <div id="adminSetting" class="collapse" aria-labelledby="headingSetting" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
			<h6 class="collapse-header">조직 관리:</h6>
			<a id="org" class="collapse-item" href="${hContext}/org/org.do">부서(직급) 관리</a>
			<div class="collapse-divider"></div>
			<h6 class="collapse-header">사원 관리:</h6>
			<a id="employee_reg" class="collapse-item" href="${hContext}/employee/employee_reg.do">사원 추가</a>
			<a id="employee_mng" class="collapse-item" href="${hContext}/employee/employee_mng.do">사원 정보 수정</a>
			<div class="collapse-divider"></div>
			<h6 class="collapse-header">근태 관리:</h6>
			<a id="dept_commuting" class="collapse-item" href="${hContext}/commuting/doSelectDeptList.do">부서 근태 관리</a>
			<div class="collapse-divider"></div>
         </div>
       </div>
     </li>
     </c:if>

    

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
      <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

  </ul>
  <!-- End of Sidebar -->

<!-- Bootstrap core JavaScript-->
<script src="${hContext}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${hContext}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="${hContext}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="${hContext}/resources/js/sb-admin-2.min.js"></script>
<script src="${hContext}/resources/js/jquery.bootpag.min.js"></script>
<script src="${hContext}/resources/js/jquery-ui.min.js"></script>
<script src="${hContext}/resources/js/jquery.validate.js"></script>