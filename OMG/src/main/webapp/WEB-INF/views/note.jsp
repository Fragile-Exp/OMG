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
		<%@include file="inc/side_bar.jsp"%>
		<!-- //side_bar -->
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">
				<!-- top_bar -->
				<%@include file="inc/top_bar.jsp"%>
				<!-- //top_bar -->

				<!-- page Content -->
				<div class="container-fluid">

					<!-- 목록 -->
					<div class="row">
						<div class="col-lg-2">
							<div class="card mb-4 py-3 border-left-primary">
								<div class="card-body">
									<div class="list-group-flush">
										<a href="#" class="list-group-item"> 보낸 쪽지함 </a>
										<a href="#" class="list-group-item"> 받은 쪽지함 </a>
										<a href="#" class="list-group-item"> 휴지통 </a>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-10">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<a href="#" class="btn btn-info btn-icon-split btn-sm">
										<span class="icon text-white-50"> <i class="fas fa-check"></i></span>
										<span class="text">읽음 처리</span>
									</a>
									<a href="#" class="btn btn-info btn-icon-split btn-sm">
										<span class="icon text-white-50"> <i class="fas fa-trash"></i></span>
										<span class="text">삭제</span>
									</a>
									<a href="#" class="btn btn-info btn-icon-split btn-sm">
										<span class="icon text-white-50"> <i class="fas fa-arrow-right"></i></span>
										<span class="text">답장하기</span>
									</a>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<table class="table table-striped table-bordered table-hover table-condensed">
											<thead>
												<tr>
													<th class="text-center">보낸 사람</th>
													<th class="text-center">받는 사람</th>
													<th class="text-center">제목</th>
													<th class="text-center">내용</th>
													<th class="text-center">읽음</th>
													<th class="text-center">보낸 시간</th>
													<th class="text-center">읽은 시간</th>
												</tr>
											</thead>
											<tbody>
										        <!-- 문자: 왼쪽, 숫자: 오른쪽, 같은면: 가운데 -->
										    	<tr>
										    		<td class="text-center" colspan="7" >1</td>
										    	</tr>
										    	<tr>
										    		<td class="text-center" colspan="7">2</td>
										    	</tr>
										    	<tr>
										    		<td class="text-center" colspan="7">3</td>
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
			<%@include file="inc/footer.jsp"%>
			<!-- //footer -->

		</div>
		<!-- //Content Wrapper -->
	</div>
	<!-- //wrap -->
</body>
</html>