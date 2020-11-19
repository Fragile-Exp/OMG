<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>OMG</title>
<style>
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
				
				<div class="card shadow mb-4">
					<!-- header -->
					<div class="card-header py-3">
						<h2>
							<!-- 일정구분번호 -->
							<c:choose>
								<c:when test="${pageMaker.cri.category_id == 1}">사내 일정</c:when>
								<c:when test="${pageMaker.cri.category_id == 2}">부서 일정</c:when>
								<c:when test="${pageMaker.cri.category_id == 3}">개인 일정</c:when>
							</c:choose>
						</h2>
					</div>
					<!-- /header -->
					<div class="card-header py-3">
						<div class="panel-heading">
							
							<!-- 부서목록 -->					
							<c:choose>
								<c:when test="${pageMaker.cri.category_id == 2}">
									<div class="btn-group">
										<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
											부서선택<span class="caret"></span>
										</button>
										<ul class="dropdown-menu" id="deptmenu" role="menu">
											<c:forEach items="${deptDiv}" var="dept">
												<li><a href="${dept.deptNo}">${dept.deptNm}</a></li>
											</c:forEach>
										</ul>
									</div>
								</c:when>
							</c:choose>
							
							<button id="regBtn" type="button" class="btn btn-primary pull-right" style="float: right;">일정 추가</button>
						</div>
					</div>				
				
					<div class="card-body">
						
						<!-- table -->
						<table class="table table-striped table-bordered table-hover" id="dateTable" width="100%" cellspacing="0">
							<thead>
								<tr>
									<th>제목</th>
									<th>시작일</th>
									<th>종료일</th>
									<th>부서</th>
									<th>작성자</th>
								</tr>
							</thead>
							<c:forEach items="${list}" var="schedule">
								<tr>
									<td>
										<a class="move" href="<c:out value='${schedule.schedule_no}'/>">
											<c:out value="${schedule.title}"/>
										</a>
									</td>
									<td><c:out value="${schedule.start_dt}"/></td>
									<td><c:out value="${schedule.end_dt}"/></td>
									<td><c:out value="${schedule.dept_nm}"/></td>
									<td><c:out value="${schedule.name}"/></td>
								</tr>
							</c:forEach>
						</table>
						<!-- /table -->
			
						<!-- criteria -->
						<div class="row">
							<div class="col-lg-12 col-md-7">
								<form id="searchForm" action="${hContext}/schedule/doSelectList.do" method="get">
									<select name="type">
										<option value="" <c:out value="${pageMaker.cri.type == null ? 'selected' : ''}"/>>--</option>
										<option value="T" <c:out value="${pageMaker.cri.type eq 'T' ? 'selected' : ''}"/>>제목</option>
										<option value="C" <c:out value="${pageMaker.cri.type eq 'C' ? 'selected' : ''}"/>>내용</option>
										<c:choose>
											<c:when test="${pageMaker.cri.category_id != 3}">
												<option value="E" <c:out value="${pageMaker.cri.type eq 'E' ? 'selected' : ''}"/>>작성자</option>
											</c:when>
										</c:choose>
										
										<option value="TC" <c:out value="${pageMaker.cri.type eq 'TC' ? 'selected' : ''}"/>>제목+내용</option>
										<c:choose>
											<c:when test="${pageMaker.cri.category_id != 3}">
												<option value="TE" <c:out value="${pageMaker.cri.type eq 'TE' ? 'selected' : ''}"/>>제목+작성자</option>
												<option value="TCE" <c:out value="${pageMaker.cri.type eq 'TCE' ? 'selected' : ''}"/>>제목+내용+작성자</option>
											</c:when>
										</c:choose>
										
									</select>
									
									<input type="text" name="keyword" value="${pageMaker.cri.keyword}">
									<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
									<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
									<input type="hidden" name="category_id" value="${pageMaker.cri.category_id}">
									
									<button class="btn btn-primary">검색</button>

									<!-- paging -->
									<div class="dataTables_paginate paging_simple_numbers" style="float: right;" id="dataTable_paginate">
										<ul class="pagination" style="display: flex;">
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
									<!-- /paging -->
								</form>
							</div>
						</div>
						<!-- /criteria -->
						
						<!-- hidden -->
						<form id="actionForm" action="${hContext}/schedule/doSelectList.do" method="get">
							<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}"/>
							<input type="hidden" name="amount" value="${pageMaker.cri.amount}"/>
							<input type="hidden" name="type" value="${pageMaker.cri.type}"/>
							<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}"/>
							<input type="hidden" name="category_id" value="${pageMaker.cri.category_id}"/>
							<input type="hidden" name="dept_no" value="${pageMaker.cri.dept_no}"/>
						</form>
						
						<!-- Modal추가 -->
						<div class="modal fade" id="scheduleModal" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
								
									<div class="modal-header">
										<h4 class="modal-title" id="myModalLabel">알림</h4>
										<button type="button" class="close" data-dismiss="modal" arai-hidden="true">&times;</button>
									</div>
									 
									<div class="modal-body">처리가 완료되었습니다.</div>
									
									<div class="modal-footer">
										<button type="button" class="btn btn-primary" data-dismiss="modal">닫기</button>
										
									</div>
								</div>
							</div>
						</div>
						<!-- /modal -->
					
					</div>
				</div>
			</div>
			<!-- footer -->
			<%@include file="/WEB-INF/views/inc/footer.jsp"%>
			<!-- //footer -->
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			var result = '<c:out value="${result}"/>';
		
			//모달 처리
			checkModal(result);
		
			history.replaceState({},null,null);
		
			function checkModal(result) {
				if(result == '' || history.state){
					return;
				}
		
				if(parseInt(result) > 0) {
					$(".modal-body").html("게시글이 등록되었습니다.");
				}
		
				$("#scheduleModal").modal("show");
			}
		
			$("#regBtn").on("click", function(){
				self.location = "${hContext}/schedule/doInsert.do?category_id=" + actionForm.find("input[name='category_id']").val();
			});
		
			//페이징 이벤트처리
			var actionForm = $("#actionForm");
		
			$(".paginate_button a").on("click", function(e) {
				e.preventDefault();			
				console.log('click');			
				actionForm.find("input[name='pageNum']").val($(this).attr("href"));
				actionForm.submit();
			});

			//부서변경 이벤트
			var actionForm = $("#actionForm");
		
			$("#deptmenu a").on("click", function(e) {
				e.preventDefault();			
				console.log('click');			
				actionForm.find("input[name='dept_no']").val($(this).attr("href"));
				actionForm.submit();
			});
		
			//게시물 조회를 위한 이벤트 처리
			$(".move").on("click", function(e) {
				e.preventDefault();
				actionForm.append("<input type='hidden' name='schedule_no' value='"+$(this).attr("href")+"'> ");
				actionForm.attr("action", "${hContext}/schedule/doSelectOne.do");
				actionForm.submit();
			});
		
			//검색버튼의 이벤트 처리
			var searchForm = $("#searchForm");
		
			$("#searchForm button").on("click", function(e) {
				if(!searchForm.find("option:selected").val()) {
					alert("검색종류를 선택하세요");
					return false;
				}
		
				if(!searchForm.find("input[name='keyword']").val()) {
					alert("키워드를 입력하세요");
					return false;
				}
		
				//페이지 번호 1로 처리
				searchForm.find("input[name='pageNum']").val("1");
				e.preventDefault();
		
				searchForm.submit();
			});

			$("#setting").attr("class","nav-link");
			$("#setting").attr("aria-expanded","true");
			$("#scheduler").attr("class","collapse show");

			//사이드바 컬랩스
			var categoryId = $("input[name='category_id']").val();

			if(categoryId == 1) {
				$("#schedule_all").attr("class","collapse-item active");
			} else if(categoryId == 2) {
				$("#schedule_dept").attr("class","collapse-item active");
			} else if(categoryId == 3) {
				$("#schedule_private").attr("class","collapse-item active");
			}

		});
	</script>
</body>
</html>
