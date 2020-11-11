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
						<h1 class="page-header">Board Read</h1>
					</div>
					<!-- /.col-log-12 -->
				</div>
				<!-- /.row -->
				
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
										<label>번호</label>
										<input class="form-control" name="scheduleNo"
										value="<c:out value="${schedule.scheduleNo}"/>" readonly="readonly"/>				
									</div>
									<div class="form-group">
										<label>제목</label>
										<input class="form-control" name="title" value="<c:out value="${schedule.title}"/>" readonly="readonly">
									</div>
									<div class="form-group">
										<label>내용</label>
										<textarea class="form-control" rows="3" name="content" readonly="readonly"><c:out value="${schedule.content}"/>
										</textarea>
									</div>
				
									<div class="form-group">
										<label>작성자</label>
										<input class="form-control" name="employeeId" value="<c:out value="${schedule.employeeId}"/>" readonly="readonly"/>
									</div>
									
									<div class="form-group">
										<label>카테고리</label>
										<input class="form-control" name="categoryId" value="<c:out value="${schedule.categoryId}"/>" readonly="readonly"/>
									</div>
				
									<div class="form-group">
										<label>부서</label>
										<input class="form-control" name="deptNo" value="<c:out value="${schedule.deptNo}"/>" readonly="readonly"/>
									</div>
									
									<div class="form-group">
										<label>시작일</label>
										<input type="datetime-local" class="form-control" name="startDt" value="<c:out value="${schedule.startDt}"/>" id="startDt" readonly="readonly">
									</div>
									
									<div class="form-group">
										<label>종료일</label>
										<input type="datetime-local" class="form-control" name="endDt" value="<c:out value="${schedule.endDt}"/>" id="endDt" readonly="readonly">
									</div>
									
									<button data-oper="modify" class="btn btn-default">수정하기</button>
									<button data-oper="list" class="btn btn-info">목록</button>
									
									<form id="operForm" action="/schedule/modify.do" method="get">
										<input type="hidden" id="scheduleNo" name="scheduleNo" value="<c:out value="${schedule.scheduleNo}"/>">
										<input type="hidden" name="pageNum" value="<c:out value="${cri.pageNum}"/>">
										<input type="hidden" name="amount" value="<c:out value="${cri.amount}"/>">
										<input type="hidden" name="keyword" value="<c:out value="${cri.keyword}"/>">
										<input type="hidden" name="type" value="<c:out value="${cri.type}"/>">
									</form>
								</div>
								<!-- end panel-body -->
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
	
			$("button[data-oper='modify']").on("click", function(e) {
				operForm.attr("action", "/schedule/update.do").submit();
			});
	
			$("button[data-oper='list']").on("click", function(e) {
				operForm.find("#scheduleNo").remove();
				operForm.attr("action", "/schedule/list.do");
				operForm.submit();
			})
		});
	</script>
</body>
</html>