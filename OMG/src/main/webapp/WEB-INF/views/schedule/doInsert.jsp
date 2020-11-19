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
							
							<!-- /header -->
							<div class="card-header py-3">
								<div class="panel-heading">
									<label class="h4 m-0 font-weight bold text-primary">일정 추가</label>
									
								</div>
							</div>	
			
							<div class="card-body">
								<form role="form" action="${hContext}/schedule/doInsert.do" method="post">
									<input type="hidden" name="category_id" value="${cri.category_id}"/>
									<input type="hidden" name="dept_no" value="${cri.dept_no}"/>
									<input type="hidden" name="employee_id" value="${cri.employee_id}"/>
									
									<div class="form-group">
										<label>제목</label> <input class="form-control" name="title"/>
									</div>
			
									<div class="form-group">
										<label>내용</label>
										<textarea class="form-control" rows="15" name="content"></textarea>
									</div>
									
									<div class="form-group">
										<label>시작일</label>
										<input type="datetime-local" class="form-control" name="start_dt" id="start_dt" value=""/>
									</div>
									
									<div class="form-group">
										<label>종료일</label>
										<input type="datetime-local" class="form-control" name="end_dt" id="end_dt" value=""/>
									</div>

									<button type="submit" id="scheduleInsert" class="btn btn-primary">저장</button>
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
	<script src="${hContext}/resources/js/schedule/schedule.js"></script>
	<script>
		$(document).ready(function() {
			today();
			inputStartDay();

			var date;
			var nowYear;
			var nowMonth;
			var nowDay;
			var year;
			var month;
			var day;
			var hour;
			var minute;

			//날짜계산
			function today() {
			    var date = new Date();
			    nowYear = date.getFullYear();
			    nowMonth = date.getMonth() + 1;
			    nowDay = date.getDate();
			    year = date.getFullYear();
			    month = date.getMonth() + 1;
			    day = date.getDate();
			    hour = date.getHours();
			    minute = date.getMinutes();
			}

			//시작일 및 종료일 입력
			function inputStartDay() {
			    var yyyy = nowYear;
			    var mm;
			    var dd;
			    var hh = hour;
			    var mi = minute;
			    
			    if(month < 10) {
			        mm = "0" + month;
			    } else if(month >= 10) {
			        mm = month;
			    }
			    if(day < 10) {
			        dd = "0" + day;
			    } else if(day >= 10) {
			        dd = day;
			    }
			    if(hh < 10){
			    	hh = "0" + hour;
			    }
			    if(mi < 10) {
			    	mi = "0" + minute;
			    }

			    var todayTime = yyyy + "-" + mm + "-" + dd + "T" + hh + ":" + mi;

			    $("#start_dt").val(todayTime);
			    $("#end_dt").val(todayTime);
			}

			$("#scheduleInsert").on("click", function(e) {
				if($("#start_dt").val() > $("#end_dt").val()) {
					alert("날짜를 확인하세요.");
					return false;
				}
			});
			
		});
	</script>
</body>
</html>