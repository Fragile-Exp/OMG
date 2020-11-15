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
					<h1 class="h3 mb-4 text-gray-800">금일 부서별 출결관리</h1>
					
					<div class="row">

						<div class="col-lg-10">
							
							<div class="card shadow mb-4 column">
								<!-- 관리자 검색, 삭제 -->
								<div class="card-header py-3"> 
									<label for="start">부서</label> 
									<form action="${hContext}/commuting/doSelectDeptList.do" method="get" id="deptFrm">
									
									
											<!-- 삭제를 위한 hidden param -->
											<input type="hidden" id="employeeId" name="employeeId" />
											
											
											<div style="width:30%; display:inline-block;">										
												<select class="form-control" name="deptNo"  >
													<c:forEach var="vo" items="${deptList}">
														<option value="${vo.deptNo}" 
														<c:if test="${deptNo eq vo.deptNo}">selected="selected"</c:if>
														>${vo.deptNm} (${vo.deptNo})</option>
													</c:forEach>
												</select>
											</div>
											<div style="width:10%;   display:inline-block;">
												<button  type="submit" data-oper="search" class="btn btn-info btn-sm">Search</button>
											</div >
											<div style="width:10%;   display:inline-block;">
												<button type="submit" data-oper="remove" class="btn btn-danger btn-sm">삭제</button>
											</div>
											<div style="width:10%;   display:inline-block;">
												<button type="submit" data-oper="init" class="btn btn-danger btn-sm">근태 초기화</button>
											</div>
									</form>
								</div>
								
								<!-- 출결 리스트 -->
								<div class="card-body">
									<div class="table-responsive">
										<!-- table -->
										<table id="myAttendList"
											class="table table-striped table-bordered table-hover table-condensed">
											<thead>
												<tr>
													<th class="text-center" width="15%">근무일</th>
													<th class="text-center" width="8%">사번</th>
													<th class="text-center" width="8%">이름</th>
													<th class="text-center" width="8%">부서번호</th>
													<th class="text-center" width="12%">출근 시간</th>
													<th class="text-center" width="12%">퇴근 시간</th>
													<th class="text-center" width="10%">현재</th>
													<th class="text-center" width="10%">출결 상태</th>
													<th class="text-center" width="10%">선택</th>
												</tr>
											</thead>
											<tbody>
												<c:choose>
													<c:when test="${list.size() > 0 }">
														<c:forEach var="vo" items="${list}">
															<tr>
																<td class="text-center">${vo.seq}</td>
																<td class="text-center">${vo.employeeId}</td>
																<td class="text-center">${vo.name}</td>
																<td class="text-center">${vo.deptNo}</td>
																<td class="text-center">${vo.attendTime}</td>
																<td class="text-center">${vo.leaveTime}</td>
																<td class="text-center">${vo.presentState}</td>
																<td class="text-center">${vo.state}</td>
																<td class="text-center">
																	<input type="radio" name="seq" id="${vo.employeeId}" value="${vo.seq}" form="deptFrm" />
																</td>
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
								<button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
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
			/* Side bar 고정 시키기 */
			$("#setting").attr("class", "nav-link");
			$("#setting").attr("aria-expanded", "true");
			$("#adminSetting").attr("class", "collapse show");
			$("#dept_commuting").attr("class", "collapse-item active");


			/* Modal*/
			var result = '<c:out value="${result}"/>';
			
			checkModal(result);
		
			history.replaceState({},null,null);
		
			function checkModal(result) {
				if(result == '' || history.state){
					return;
				}else {
					$(".modal-body").html(result);
				}
					
		
				$("#myModal").modal("show");
			
			}
			/*// Modal*/
			
		});

		/* Form Controll */
		var formObj = $("form");

		$('button').on("click", function(e) {
			e.preventDefault();

			var operation = $(this).data("oper");
	
			console.log(operation);
	
			if (operation === 'remove') {
				$("#employeeId").val($(":input:radio[name=seq]:checked").attr("id"));
				formObj.attr("action", "${hContext}/commuting/doDelete.do").attr("method","post");
			} else if (operation === 'search') {
				//move to list
				formObj.attr("action", "${hContext}/commuting/doSelectDeptList.do").attr("method", "get");
	
				/* //폼 값 초기화하고 필요한 값만 리스트로 복사
				var pageNumTag = $("input[name='pageNum']")	.clone();
				var amountTag = $("input[name='amount']").clone();
				var type = $("input[name='type']").clone();
				var keyword = $("input[name='keyword']").clone(); */ 
	
				/* formObj.append(pageNumTag);
				formObj.append(amountTag);
				formObj.append(type);
				formObj.append(keyword); */
			} else if (operation === 'init') {
				formObj.attr("action", "${hContext}/commuting/doInit.do").attr("method", "post");
			}
	
			formObj.submit();

			/*// Form Controll */
		});	

	</script>
</body>
</html>