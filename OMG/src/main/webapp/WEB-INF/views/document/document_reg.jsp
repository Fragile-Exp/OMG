<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OMG</title>
</head>

<style>
	.card { width: 120%;}	
	.card-table-thead > th {border-bottom : 3px solid #444444; padding: 10px;}
	.card-table-tbody > td {border-bottom : 1px solid #444444; padding:10px;}
	.btn-box {float:right;}
</style>

<body id="page-top">
<!-- wrap -->
<div id="wrapper">
	<!-- side_bar -->
	<%@include file="/WEB-INF/views/inc/side_bar.jsp" %>
	<!-- //side_bar -->
		<!-- Content Wrapper -->
    	<div id="content-wrapper" class="d-flex flex-column">

	      	<!-- Main Content -->
	      	<div id="content">
	      		<!-- top_bar -->
	      		<%@include file="/WEB-INF/views/inc/top_bar.jsp" %>
	      		<!-- //top_bar -->
	      		
	      		<!-- page Content -->
	      		<div class="container-fluid">

					<!-- Page Heading -->
					<div class="d-sm-flex align-items-center justify-content-between mb-4" >
					  <h1 class="h3 mb-0 text-gray-800">문서 등록</h1>
					  <div class="btn-box">
					  	<a href="#" class="btn btn-sm btn-primary shadow-sm"><i class="fas fa-file-invoice fa-sm text-white-50"></i>등록 페이지</a>
					  	<a href="#" class="btn btn-sm btn-primary shadow-sm"><i class="fas fa-trash fa-sm text-white-50"></i> 삭제</a>
					  </div>
					</div>
					
				
					<!-- Content Row -->
					
					<div class="row">
					
					  <!-- Area Chart -->
					  <div class="col-xl-15 col-lg-10">
					    <div class="card shadow mb-4">
					      <!-- Card Header - Dropdown -->
					      <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					        <h6 class="m-0 font-weight-bold text-primary">등록 결재 목록</h6>
					        <div class="dropdown no-arrow">
					          <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					            <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
					          </a>
					          <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
					            <div class="dropdown-header">Dropdown Header:</div>
					            <a class="dropdown-item" href="#">Action</a>
					            <a class="dropdown-item" href="#">Another action</a>
					            <div class="dropdown-divider"></div>
					            <a class="dropdown-item" href="#">Something else here</a>
					          </div>
					        </div>
					      </div>
					      <!-- Card Body -->
					      <div class="card-body">
					        <div class="chart-area">
					        	<table class="card-table" >
					        		<thead>
						        		<tr class="card-table-thead">
						        			<th width="15%" class="text-center"  >문서 제목</th>	
						        			<th width="10%">문서 종류</th>
						        			<th width="10%">문서번호 </th>
						        			<th width="10%">처리기간</th>
						        			<th width="3%">상태</th>
						        			<th width="3%">선택</th>
						        		</tr>
					        		</thead>
					        		
					        		<tbody>
						        		<!-- to do : for문을 사용해서 여러 데이터 출력-->
						        		<!-- to do : javascript 사용해서 라인 클릭시 페이지 이동-->
						        		<tr class="card-table-tbody">
						        			<td>문서제목 데이터</td>
						        			<td>문서 종류 데이터</td>
						        			<td>문서 번호 데이터</td>
						        			<td>처리기간 데이터</td>
						        			<td>상태 </td>
						        			<td><input type="checkbox"></td>
						        		</tr>
					        		</tbody>	
					        	
					        	</table>
					        </div>
					      </div>
					    </div>
					  </div>
					</div>
					
		        </div>
		        <!-- // page Content -->
	      	
	      	</div>
	      	<!-- //Main Content -->
	      	
	      	<!-- footer -->
	      	<%@include file="/WEB-INF/views/inc/footer.jsp" %>
	      	<!-- //footer -->
	      	
		</div>
		<!-- //Content Wrapper -->
</div>
<!-- //wrap -->
</body>
</html></html>