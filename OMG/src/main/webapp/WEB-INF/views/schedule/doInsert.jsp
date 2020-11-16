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
			
							<div class="panel-heading">일정 추가</div>
							<!-- /.panel-heading -->
			
							<div class="panel-body">
								<form role="form" action="/schedule/doInsert.do" method="post">
									<input type="hidden" name="category_id" value="${cri.category_id}"/>
									<input type="hidden" name="dept_no" value="${cri.dept_no}"/>
									<input type="hidden" name="employee_id" value="${cri.employee_id}"/>
									
									<div class="form-group">
										<label>제목</label> <input class="form-control" name="title"/>
									</div>
			
									<div class="form-group">
										<label>내용</label>
										<textarea class="form-control" row="5" name="content"></textarea>
									</div>
									
									<div class="form-group">
										<label>시작일</label>
										<input type="datetime-local" class="form-control" name="start_dt" id="start_dt"/>
									</div>
									
									<div class="form-group">
										<label>종료일</label>
										<input type="datetime-local" class="form-control" name="end_dt" id="end_dt"/>
									</div>

									<button type="submit" class="btn btn-primary">저장</button>
									<button type="reset" class="btn btn-danger">리셋</button>

									
								</form>
							</div>
							<!-- end panel-body -->
						</div>
						<!-- end panel-body -->
					</div>
					<!-- end panel -->
				</div>
				<!-- /.row -->
			</div>
			<!-- footer -->
			<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
			<!-- //footer -->
		</div>
	</div>
	<script src="../../../resources/js/schedule/schedule.js"></script>
	<script>
		$(document).ready(function() {
			today();
			inputStartDay();
			inputHourAndMinute();
			//addValue();

			var para = document.location.href.split("?");
			console.log(para);
		});
	</script>
</body>
</html>