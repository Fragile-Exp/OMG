<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>OMG</title>
<style type="text/css">
	.btn-info {
		
	}
</style>

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

					<!-- Page Heading -->
					<h1 class="h3 mb-4 text-gray-800">내 출결관리</h1>
					
					<div class="row">

						<div class="col-lg-10">
							
							<div class="card shadow mb-4">
							
								<div class="card-header py-3"> 
									
									<label for="start">월 선택</label> 
									<form action="${hContext}/commuting/my_attendence.do" method="get" name="searchFrm">
										<input type="month"  min="2020-11" id="month" name="month" value="${month}" >	
										<input type="submit" class="btn btn-info btn-sm" value="Search"> 
									</form>
									<form action="${hContext}/commuting/doUpdateAttendTime.do" method="get" name="searchFrm">
										<input type="submit" class="btn btn-info btn-sm" value="출근등록"> 
									</form>
									
								</div>
								
								
								<div class="card-body">
									<div class="table-responsive">
										<!-- table -->
										<table id="myAttendList"
											class="table table-striped table-bordered table-hover table-condensed">
											<thead>
												<tr>
													<th class="text-center" width="15%">일자</th>
													<th class="text-center" width="8%">사번</th>
													<th class="text-center" width="8%">이름</th>
													<th class="text-center" width="12%">출근 시간</th>
													<th class="text-center" width="12%">퇴근 시간</th>
													<th class="text-center" width="10%">현재 출결</th>
													<th class="text-center" width="10%">출결 상태</th>
													<th class="text-center" width="12%">근무 시간</th>
													<th class="text-center" width="13%">누적근무시간</th>
												</tr>
											</thead>
											<tbody>
												<!-- 문자: 왼쪽, 숫자: 오른쪽, 같은면: 가운데 -->
												<c:choose>
													<c:when test="${list.size() > 0 }">
														<c:forEach var="vo" items="${list}">
															<tr>
																<td class="text-center">${vo.seq}</td>
																<td class="text-center">${vo.employeeId}</td>
																<td class="text-center">${vo.name}</td>
																<td class="text-center">${vo.attendTime}</td>
																<td class="text-center">${vo.leaveTime}</td>
																<td class="text-center">${vo.presentState}</td>
																<td class="text-center">${vo.state}</td>
																<td class="text-center">${vo.workTime}시간</td>
																<td class="text-right">${vo.totalCnt}시간</td>
															</tr>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<tr>
															<td class="text-center" colspan="99">No data found.</td>
														</tr>
													</c:otherwise>
												</c:choose>
											</tbody>
										</table>
										<!-- //table -->
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
				<!-- // page Content -->
				<!-- Modal추가 -->
							<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
									
										<div class="modal-header">
											<h4 class="modal-title" id="myModalLabel">알림</h4>
											<button type="button" class="close" data-dismiss="modal" arai-hidden="true">&times;</button>
										</div>
										 
										<div class="modal-body">처리가 완료되었습니다.</div>
										
										<div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
											<button type="button" class="btn btn-default">Save changes</button>
										</div>
									</div>
								</div>
							</div>
							<!-- /modal -->
				
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
		$(document).ready(function() {
			$("#Pages").attr("class", "nav-link");
			$("#Pages").attr("aria-expanded", "true");
			$("#collapsePages").attr("class", "collapse show");
			$("#blank").attr("class", "collapse-item active");
		});

	</script>
</body>
</html>