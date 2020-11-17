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

						<div class="col-lg-50">
							
							<div class="card shadow mb-4 column">
								<!-- 관리자 검색, 삭제 -->
								<div class="card-header"> 
									<label for="start">부서</label> 
									
									<!-- hidden -->
									<form name="actionForm" id="actionForm" action="${hContext}/commuting/doSelectDeptList.do" method="get">
										<input type="hidden" name="deptNo" id="dept_no"/>
										<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}"/>
										<input type="hidden" name="amount" value="${pageMaker.cri.amount}"/>
										<input type="hidden" name="type" value="${pageMaker.cri.type}"/>
										<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}"/>
										<input type="hidden" id="employeeId" name="employeeId" />
										<input type="hidden" id="seq" name="seq" />
									</form>
											
									<div style="width:30%; display:inline-block;">										
										<select class="form-control" id="deptNo" >
											<c:forEach var="vo" items="${deptList}">
												<option value="${vo.deptNo}" 
												<c:if test="${pageMaker.cri.dept_no eq vo.deptNo}">selected="selected"</c:if>
												>${vo.deptNm}</option>
										</c:forEach>
										</select>
									</div>
									<!-- button : search -->
									<div class="btn-group btn-group-justified  btn-group-sm" role="group" >
										 <button  type="submit" data-oper="search" class="btn btn-info">Search</button>
									</div>
									
									<!-- button : remove -->
									<div class="btn-group btn-group-justified  btn-group-sm" role="group" >
										 <button type="submit" data-oper="remove" class="btn btn-danger">삭제</button>
									</div>
									<!-- button : init -->
									<div class="btn-group btn-group-justified  btn-group-sm" role="group" >
										 <button type="submit" data-oper="init" id="init" class="btn btn-primary">근태 초기화</button>
									</div>
											
								</div>
								
								<div class="card-body">
									<div class="table-responsive">
										<!-- table -->
										<table id="myAttendList"
											class="table table-striped table-bordered table-hover table-condensed">
											<thead>
												<tr>
													<th class="text-center" width="20%">근무일</th>
													<th class="text-center" width="15%">사번</th>
													<th class="text-center" width="15%">이름</th>
													<th class="text-center" width="15%">부서번호</th>
													<th class="text-center" width="15%">출근시간</th>
													<th class="text-center" width="15%">퇴근시간</th>
													<th class="text-center" width="15%">현재</th>
													<th class="text-center" width="15%">출결</th>
													<th class="text-center" width="8%">선택</th>
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
																	<input type="radio" name="deleteParam" id="${vo.employeeId}" value="${vo.seq}" form="deptFrm" />
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
										
										<!-- paging -->
										<div class="dataTables_paginate paging_simple_numbers pull-right" style="float: right;" id="dataTable_paginate">
											<ul class="pagination">
												<c:if test="${pageMaker.prev}">
													<li class="paginate_button page-item previous" id="dataTable_previous">
														<a href="${pageMaker.startPage - 1}" aria-control="dataTable" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
													</li>
												</c:if>
												
												<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
													<li class="paginate_button page-item ${pageMaker.cri.pageNum == num ? 'active' : ''}">
														<a href="${num}" aria-control="dataTable" data-dt-idx="${num}" tabindex="0" class="page-link">${num}</a>
													</li>
												</c:forEach>
												
												<c:if test="${pageMaker.next}">
													<li class="paginate_button page-item next">
														<a href="${pageMaker.endPage + 1}" aria-control="dataTable" data-dt-idx="0" tabindex="0" class="page-link">Next</a>
													</li>
												</c:if>
											</ul>
										</div>
										
										
										
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

			//페이징 이벤트처리
			var actionForm = $("#actionForm");
		
			$(".paginate_button a").on("click", function(e) {
				e.preventDefault();			
				actionForm.find("input[name='deptNo']").val($("#deptNo").val());			
				actionForm.find("input[name='pageNum']").val($(this).attr("href"));
				actionForm.submit();
			});
			
		});

		/* Form Controll */
		var formObj = $('form');

		$('button').on("click", function(e) {
			
			e.preventDefault();
			
			var operation = $(this).data("oper");
	
			console.log(operation);
	
			if (operation === 'remove') {
				$("#employeeId").val($(":input:radio[name=deleteParam]:checked").attr("id"));
				$("#seq").val($(":input:radio[name=deleteParam]:checked").val());
				formObj.attr("action", "${hContext}/commuting/doDelete.do").attr("method","post");
			} else if (operation === 'search') {
				//move to list
				$("#dept_no").val($("#deptNo").val());
				formObj.attr("action", "${hContext}/commuting/doSelectDeptList.do").attr("method","get");
			} else if (operation === 'init') {
				formObj.attr("action", "${hContext}/commuting/doInit.do").attr("method", "post");
			}
			
			formObj.submit();

			/*// Form Controll */
		});	

	</script>
</body>
</html>