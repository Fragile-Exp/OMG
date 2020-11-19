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
									<div class="m-0 font-weight-bold text-primary">일정 수정 페이지</div>
									<!-- /.panel-heading -->
								</div>

								<div class="card-body">
									<form role="form" action="${hContext}/schedule/doUpdate.do" method="post">
										<!-- hidden -->
										<input type="hidden" name="schedule_no" value='<c:out value="${schedule.schedule_no}"/>'>
										<input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum}"/>'>
										<input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'>
										<input type="hidden" name="type" value='<c:out value="${cri.type}"/>'>
										<input type="hidden" name="keyword" value='<c:out value="${cri.keyword}"/>'>
										<input type="hidden" name="category_id" value='<c:out value="${cri.category_id}"/>'/>

										<div class="form-group">
											<label>제목</label>
											<input class="form-control" name="title" value="<c:out value="${schedule.title}"/>"/>
										</div>
										<div class="form-group">
											<label>내용</label>
											<textarea class="form-control" rows="15" name="content"><c:out value="${schedule.content}"/></textarea>
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
											<input type="datetime-local" class="form-control" name="start_dt" value="<c:out value="${schedule.start_dt}"/>">
										</div>
										
										<div class="form-group">
											<label>종료일</label>
											<input type="datetime-local" class="form-control" name="end_dt" value="<c:out value="${schedule.end_dt}"/>" id="endDt">
										</div>

										<button type="submit" data-oper="update" class="btn btn-primary">수정</button>
										<button type="submit" data-oper="remove" class="btn btn-danger">삭제</button>
										<button type="submit" data-oper="list" class="btn btn-info">목록</button>
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
			</div>
			<!-- footer -->
			<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
			<!-- //footer -->
		</div>
	</div>

	<script>
		$(document).ready(function() {
			var formObj = $("form");
	
			$('button').on("click", function(e) {
				e.preventDefault();
	
				var operation = $(this).data("oper");
		
				console.log(operation);
		
				if (operation === 'remove') {
					formObj.attr("action", "${hContext}/schedule/doDelete.do");
				} else if (operation === 'list') {
					//move to list
					formObj.attr("action", "${hContext}/schedule/doSelectList.do").attr("method", "get");
		
					//폼 값 초기화하고 필요한 값만 리스트로 복사
					var pageNumTag = $("input[name='pageNum']")	.clone();
					var amountTag = $("input[name='amount']").clone();
					var type = $("input[name='type']").clone();
					var keyword = $("input[name='keyword']").clone();
		
					formObj.empty();
					formObj.append(pageNumTag);
					formObj.append(amountTag);
					formObj.append(type);
					formObj.append(keyword);
				}
		
				formObj.submit();
			});
		});
	</script>
</body>
</html>