<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="hContext" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>OMG</title>
</head>
<body id="page-top" class="bg-gray-100">
<!-- wrap -->
<div id="wrapper">
		<!-- Content Wrapper -->
    	<div id="content-wrapper" class="d-flex flex-column">

	      	<!-- Main Content -->
	      	<div id="content">

	      		<!-- page Content -->
	      		<div class="container-fluid">

		           <!-- 404 Error Text -->
		          <div class="text-center">
		            <div class="error mx-auto" data-text="">Error</div>
		            <p class="lead text-gray-800 mb-5">Error. 관리자에게 문의 하세요.</p>
		            <a href="${hContext}/view/main.do">&larr; 메인으로 ..</a>
		          </div>
		
		        </div>
		        <!-- // page Content -->
	      	
	      	</div>
	      	<!-- //Main Content -->
	      	
	      	
		</div>
		<!-- //Content Wrapper -->
</div>
<!-- //wrap -->

</body>
</html>