<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>OMG</title>
</head>
<body id="page-top">
	<!-- wrap -->
	<div id="wrapper">
		<!-- side_bar -->
		<%@include file="/WEB-INF/views/inc/side_bar.jsp"%>
		<!-- //side_bar -->
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">
				<!-- top_bar -->
				<%@include file="/WEB-INF/views/inc/top_bar.jsp"%>
				<!-- //top_bar -->

				<!-- page Content -->
				<div class="container-fluid">

					<!-- 목록 -->
					<div class="row">
						<div class="col-lg-2">
							<div class="card mb-4 py-3 border-left-primary">
								<div class="card-body">
									<div class="list-group-flush">
										<a href="#" class="list-group-item"> 부서 </a> <a href="#"
											class="list-group-item"> 직급 </a>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-10">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<a href="javascript:history.back();" class="btn btn-info btn-icon-split btn-sm">
										<span class="icon text-white-50"> <i class="fas fa-arrow-left"></i></span>
										<span class="text">취소</span>
									</a>
									<a href="#" class="btn btn-info btn-icon-split btn-sm">
										<span class="icon text-white-50"> <i class="fas fa-check"></i></span>
										<span class="text">등록</span>
									</a>
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col-lg-4"></div>
										<div class="col-lg-1">
											<label for="dept" >상위 부서</label>
										</div>
										<div class="col-lg-3">
											<select class="form-control" id="dept" >
											<option value="1">부서1</option>
											<option value="2">부서2</option>
											<option value="3">부서3</option>
											</select>
										</div>
									</div>
									<div class="row py-4">
										<div class="col-lg-4"></div>
										<div class="col-lg-1">
											<label for="dept_no" >부서 코드</label>
										</div>
										<div class="col-lg-3">
											<input type="text" class="form-control" id="dept_no" />
										</div>
									</div>
									<div class="row">
										<div class="col-lg-4"></div>
										<div class="col-lg-1">
											<label for="dept_no" >부서 명</label>
										</div>
										<div class="col-lg-3">
											<input type="text" class="form-control" id="dept_nm" />
										</div>
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
			<%@include file="/WEB-INF/views/inc/footer.jsp"%>
			<!-- //footer -->

		</div>
		<!-- //Content Wrapper -->
	</div>
	<!-- //wrap -->
	
		<script type="text/javascript">
	$(document).ready(function(){
		$("#setting").attr("class","nav-link");
		$("#setting").attr("aria-expanded","true");
		$("#adminSetting").attr("class","collapse show");
		$("#org").attr("class","collapse-item active");
		});
	
	</script>
	
</body>
</html>