<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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

				<!-- Page Heading -->
				<h1 class="h3 mb-2 text-gray-800">일정 관리</h1>
				<p class="mb-4">
					DataTables is a third party plugin that is used to generate the demo
					table below. For more information about DataTables, please visit the <a
						target="_blank" href="https://datatables.net">official DataTables
						documentation</a>.
				</p>
				<div class="card shadow mb-4">
					<div class="card-header py-3">
						<h6 class="m-0 font-weight bold text-primary">일정 관리</h6>
						<button id="regBtn" type="button" class="btn btn-xs pull-right">일정 추가</button>
					</div>
				
					<div class="card-body">
						<div class="table-responsive">
							<!-- table -->
							<table class="table table-bordered" id="dateTable" width="100%" cellspacing="0">
								<thead>
									<tr>
										<th>#번호</th>
										<th>제목</th>
										<th>시작일</th>
										<th>종료일</th>
										<th>부서</th>
										<th>작성자</th>
										<th>일정종류</th>
									</tr>
								</thead>
								<c:forEach items="${list}" var="schedule">
									<tr>
										<td><c:out value="${schedule.scheduleNo}"/></td>
										<td>
											<a class="move" href="<c:out value='${schedule.scheduleNo}'/>">
												<c:out value="${schedule.title}"/>
											</a>
										</td>
										<td><c:out value="${schedule.startDt}"/></td>
										<td><c:out value="${schedule.endDt}"/></td>
										<td><c:out value="${schedule.deptNo}"/></td>
										<td><c:out value="${schedule.employeeId}"/></td>
										<td><c:out value="${schedule.categoryId}"/></td>
									</tr>
								</c:forEach>
							</table>
							<!-- /table -->
				
							<!-- criteria -->
							<div class="row">
								<div class="col-lg-12">
									<form id="searchForm" action="/schedule/list.do" method="get">
										<select name="type">
											<option value="" <c:out value="${pageMaker.cri.type == null ? 'selected' : ''}"/>>--</option>
											<option value="T" <c:out value="${pageMaker.cri.type eq 'T' ? 'selected' : ''}"/>>제목</option>
											<option value="C" <c:out value="${pageMaker.cri.type eq 'C' ? 'selected' : ''}"/>>내용</option>
											<option value="E" <c:out value="${pageMaker.cri.type eq 'E' ? 'selected' : ''}"/>>작성자</option>
											<option value="TC" <c:out value="${pageMaker.cri.type eq 'TC' ? 'selected' : ''}"/>>제목+내용</option>
											<option value="TE" <c:out value="${pageMaker.cri.type eq 'TE' ? 'selected' : ''}"/>>제목+작성자</option>
											<option value="TCE" <c:out value="${pageMaker.cri.type eq 'TCE' ? 'selected' : ''}"/>>제목+내용+작성자</option>
										</select>
										
										<input type="text" name="keyword" value="${pageMaker.cri.keyword}">
										<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
										<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
										
										<button class="btn btn-default">Search</button>
									</form>
								</div>
							</div>
							<!-- /criteria -->
			
							<!-- paging -->
							<div class="pull-right">
								<ul class="pagination">
									<c:if test="${pageMaker.prev}">
										<li class="paginate_button previous">
											<a href="${pageMaker.startPage - 1}">Previous</a>
										</li>
									</c:if>
									
									<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
										<li class="paginate_button">
											<a href="${num}">${num}</a>
										</li>
									</c:forEach>
									
									<c:if test="${pageMaker.next}">
										<li class="paginate_button next">
											<a href="${pageMaker.endPage + 1}">Next</a>
										</li>
									</c:if>
								</ul>
							</div>
							<!-- /paging -->
					
							<!-- hidden -->
							<form id="actionForm" action="/schedule/list.do" method="get">
								<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}"/>
								<input type="hidden" name="amount" value="${pageMaker.cri.amount}"/>
								<input type="hidden" name="type" value="${pageMaker.cri.type}"/>
								<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}"/>
							</form>
							
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
					$(".modal-body").html("게시글 " + parseInt(result) + " 번이 등록되었습니다.");
				}
		
				$("#myModal").modal("show");
			}
		
			$("#regBtn").on("click", function(){
				self.location = "/schedule/register.do";
			});
		
			//페이징 이벤트처리
			var actionForm = $("#actionForm");
		
			$(".paginate_button a").on("click", function(e) {
				e.preventDefault();			
				console.log('click');			
				actionForm.find("input[name='pageNum']").val($(this).attr("href"));
				actionForm.submit();
			});
		
			//게시물 조회를 위한 이벤트 처리
			$(".move").on("click", function(e) {
				e.preventDefault();
				actionForm.append("<input type='hidden' name='scheduleNo' value='"+$(this).attr("href")+"'> ");
				actionForm.attr("action", "/schedule/get.do");
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
			
		});
	</script>
</body>
</html>
