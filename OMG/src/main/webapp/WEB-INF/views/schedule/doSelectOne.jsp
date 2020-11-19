<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- wrap -->
	<div id="wrapper">
		<!-- side_bar -->
		<%@ include file="/WEB-INF/views/inc/side_bar.jsp"%>
		<!-- //side_bar -->
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">
				<!-- top_bar -->
				<%@ include file="/WEB-INF/views/inc/top_bar.jsp"%>
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<div class="m-0 font-weight-bold text-primary">일정</div>
									<!-- /.panel-heading -->
								</div>
								
								<div class="card-body">
									<div class="form-group">
										<label>제목</label>
										<input class="form-control" name="title" value="<c:out value="${schedule.title}"/>" readonly="readonly">
									</div>
									<div class="form-group">
										<label>내용</label>
										<textarea class="form-control" rows="15" name="content" readonly="readonly"><c:out value="${schedule.content}"/>
										</textarea>
									</div>
				
									<div class="form-group">
										<label>작성자</label>
										<input class="form-control" name="name" value="<c:out value="${schedule.name}"/>" readonly="readonly"/>
									</div>
									
									<div class="form-group">
										<label>부서</label>
										<input class="form-control" name="dept_nm" value="<c:out value="${schedule.dept_nm}"/>" readonly="readonly"/>
									</div>
									
									<div class="form-group">
										<label>시작일</label>
										<input type="datetime-local" class="form-control" name="start_dt" value="<c:out value="${schedule.start_dt}"/>" id="startDt" readonly="readonly">
									</div>
									
									<div class="form-group">
										<label>종료일</label>
										<input type="datetime-local" class="form-control" name="end_dt" value="<c:out value="${schedule.end_dt}"/>" id="end_dt" readonly="readonly">
									</div>
									
									<c:choose>
										<c:when test="${schedule.employee_id == cri.employee_id}">
											<button data-oper="update" class="btn btn-primary">수정</button>
										</c:when>
									</c:choose>
									
									<button data-oper="list" class="btn btn-info">목록</button>
									
									<form id="operForm" action="/schedule/doUpdate.do" method="get">
										<input type="hidden" id="schedule_no" name="schedule_no" value="<c:out value="${schedule.schedule_no}"/>">
										<input type="hidden" name="pageNum" value="<c:out value="${cri.pageNum}"/>">
										<input type="hidden" name="amount" value="<c:out value="${cri.amount}"/>">
										<input type="hidden" name="keyword" value="<c:out value="${cri.keyword}"/>">
										<input type="hidden" name="type" value="<c:out value="${cri.type}"/>">
										<input type="hidden" name="category_id" value="<c:out value="${cri.category_id}"/>"/>
									</form>
								</div>
								<!-- end panel-body -->
							</div>
							</div>
							
						</div>
						<!-- end panel-body -->
					</div>
					<!-- end panel -->
				</div>
				<!-- /.row -->
			<!-- footer -->
			<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
			<!-- //footer -->
		</div>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var operForm = $("#operForm");
	
			$("button[data-oper='update']").on("click", function(e) {
				operForm.attr("action", "${hContext}/schedule/doUpdate.do").submit();
			});
	
			$("button[data-oper='list']").on("click", function(e) {
				//move to list
				operForm.find("#schedule_no").remove();
				operForm.attr("action", "${hContext}/schedule/doSelectList.do");

				operForm.submit();
			});

		});
	</script>
</body>
</html>