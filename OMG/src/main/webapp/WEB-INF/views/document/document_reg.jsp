<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OMG</title>
</head>

<style>
.card {
	width: 120%;
}

.btn-box {
	float: right;
}
</style>

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
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">문서 등록 페이지</h1>

					</div>


					<!-- Content Row -->


					<div class="row">

						<!-- Area Chart -->
						<div class="col-xl-15 col-lg-10">
							<div class="card shadow mb-4">
								<!-- Card Header - Dropdown -->
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">문서 등록</h6>
									<div style="float: right;">
										<a id="Insert" onClick="insertDcoument()" class="btn btn-sm btn-primary shadow-sm" style="color: white"><i class="fas fa-file-upload fa-sm text-white-50"></i>상신</a> 
										<a href="${hContext}/document/document.do" class="btn btn-sm btn-primary shadow-sm" style="color: white"><i class="fas fa-backspace fa-sm text-white-50"></i>취소</a>
									</div>
								</div>
								<!-- Card Body -->
								<div class="card-body">
									<div class="chart-area" style="height: 100%;">
										<!-- to do : 값들 name/id 부여 -->
										<!-- 제목  -->
										<form name="writeFrm" action="" method="post" enctype="multipart/form-data">
											<div>
												<div class="card-body-label" style="display: inline-block; width: 7%; margin-bottom: 10px;">
													<div class="title-header bg-primary text-white btn-sm" style="text-align: center;">제목</div>
												</div>
												<div class="title-body" style="display: inline-block;">
													<input id="title" name="title" class="title-input" type="text" style="width: 900px;">
												</div>
											</div>
											<!-- //제목  -->
											<!-- 종류 / 기간   -->
											<div>
												<div class="card-body-label" style="display: inline-block; width: 7%; margin-bottom: 10px;">
													<div class="kind-header bg-primary text-white btn-sm" style="text-align: center;">종류</div>
												</div>
												<div class="kind-body" style="display: inline-block;">
													<!-- to do : for문 사용해서 문서 종류 데이터 입력   -->
													<select id="kind" name="kind" class="kind-body-select" style="width: 400px;">
														<option value="10">----------</option>
														<option value="0">휴가</option>
														<option value="1">실험</option>
														<option value="2">문서</option>
														<option value="3">물품구매</option>
														<option value="4">프로젝트</option>
														<option value="5">회의록</option>
														<option value="6">행사</option>
														<option value="7">인사</option>
														<option value="8">총무</option>
														<option value="9">사직</option>

													</select>
												</div>
												<div class="card-body-label" style="display: inline-block; width: 7%; margin-bottom: 10px;">
													<div class="dDay-header bg-primary text-white btn-sm" style="text-align: center;">
														기간
													</div>
												</div>
												<div class="dDay-body" style="display: inline-block;">
													<input id="dDay" name="dDay" type="date" style="width: 400px;">
												</div>
											</div>
											<!--// 종류 / 기간   -->
											<!-- 파일 등록  -->
											<div>
												<div class="card-body-label" style="display: inline-block; width: 7%; margin-bottom: 10px;">
													<div class="file-header bg-primary text-white btn-sm" style="text-align: center;">
														파일
													</div>
												</div>
												<!-- to do : 파일 등록 모양 만들기  -->
												<div class="file-body" style="display: inline-block;">
													<input id="file" name="file" class="file-input" multiple="multiple" type="file" onchange="file_upload(this)" multiple style="width: 300px;">
												</div>
												<table class="table table-striped table-bordered" id="fileListTable">
													<thead>
														<tr>
															<th>첨부 파일명</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>

											</div>
											<!--// 파일 등록 -->
											<!-- 결재자 -->
											<div>
												<div class="card-body-label" style="display: inline-block; width: 7%; margin-bottom: 10px;">
													<div class="approval-header bg-primary text-white btn-sm" style="text-align: center;">결재자</div>
												</div>
												<div class="approval-body" style="display: inline-block;">
													<!-- to do : for문 사용해서 사원 입력하기  -->
													<select id="approval-dept" name="dept_no" class="approval-body-select" style="width: 200px;" onchange="empNameget()" >
														<c:if test="${deptList.size() >0}">
															<c:forEach var="dept" items="${deptList}" >
																<option value="${dept.deptNo }">${dept.deptNm}</option>
															</c:forEach>
														</c:if>
														<!-- <option>부서</option>
														<option value="10000">omg</option>
														<option value="11000">전략기획본부</option>
														<option value="12000">경영관리본부</option>
														<option value="13000">기술개발본부</option>
														<option value="14000">영업본부</option>
														<option value="13100">연구소</option>
														<option value="13110">제1연구소</option>
														<option value="13120">제2연구소</option>
														<option value="13200">기술부문</option>
														<option value="13210">기술1팀</option>
														<option value="13220">기술2팀</option>
														<option value="14100">아시아영업부</option> -->
													</select>
												</div>
												<input id="emplist" type="hidden" value="">
												<div id="approval-lever" class="approval-body" style="display: inline-block;">
													<select id="approval-name" name="okUser" class="approval-body-select" style="width: 200px;">
													<!-- 부서 사원  -->
														<option value="">---------</option>
													</select>
													

												</div>

												<!-- <div style="display: inline-block;">
													<input id="approval-button" type="button" style="width: 100px;" onclick="selectClick()" value="확인">
												</div> -->
											</div>

											<div class="card-body-label" style="width: 7%; margin-bottom: 10px;">
												<div class="cont-header bg-primary text-white btn-sm" style="text-align: center; width: 100px;">문서 내용</div>
											</div>
											<input id="cont" name="documentCont" class="cont-body" type="text" style="width: 900px; height: 100px;">
										</form>
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
</body>

<script type="text/javascript">
	
	
		function empNameget(){
			$.ajax({
				url : "${hContext}/document/doempNameget.do",
				type : "GET",
				data:{
					"dept_no" : $("#approval-dept").val()
					},
			dataType:"html",
			async: true,
			success:function(data){
				//alert("검색 성공");
				$("#approval-name").empty();
				
				var list = JSON.parse(data);
				console.log(list);

				var html ="";
				for(var i=0; i<list.length; i++){

					html += "<option value='"+list[i].employee_id+"'>";
					html +=list[i].name;
					html +="</option>";
				}
	
				$("#approval-name").append(html);
				

			},
			error:function(data){
					
			}
				
			});
		}

	function file_upload(e)	
		{
			var files = e.files;
		    var fileArr = Array.prototype.slice.call(files);
	 		html = "";
	 		if(fileArr.length != 0){
	 			for(var file of fileArr){
	 				html += '<tr>';
					html += '<td>';
					html += file.name;
					html += '</td>';
					html += '</tr>';
	 			}
	 	 	} else{
	 	 		html += '<tr>';
				html += '<td class="text-center">';
				html += '등록된 데이터가 없습니다.';
				html += '</td>';
				html += '</tr>';
	 	 	 	}
			
			$("#fileListTable>tbody").empty();
			$("#fileListTable>tbody").append(html);
		}



		
/* 	var Id;
	function selectClick(){
		
		
		$.ajax({
			url:"${hContext}/document/doempName.do",
			type:"GET",
			data:{
				  "dept_no": $('#approval-dept').val(),
				  "name" : $("#approval-lever>select").val()
				  },
			dataType:"json",
		success:function(data){
		 	alert("존재합니다.");
		 	alert(data);
		 	Id = data;

		 	
		},
		error:function(err){
			alert("존재하지 않습니다.");
		}
	
		});					
			
	} */

		
	

	//title : 제목 ,  kind : 종류 ,dDay :기간 , approval : 결재자, cont : 내용
	function insertDcoument (){
		//널체크 필수값 체크
		if(null == $("#title").val() || $("#title").val().length ==0){
			alert("제목을 입력하세요.");
			return;
		}

		if(null == $("#kind").val() || $("#kind").val()> 9){
			alert("문서의 종류를 선택하세요.");
			return;
		}

		if(null == $("#dDay").val() || $("#dDay").val() ==0){
			alert("날짜를 선택하세요.");
			return;
		}
		if(null == $("#approval-name").val() || $("#approval-name").val() ==0){
			alert("결재자를 선택하세요.");
			return;
		}
		
		var frm = document.writeFrm;
		var formData = new FormData(frm);
		//formData.append("okUser",Id);
		
		//alert(formData );
		$.ajax({
			url:"${hContext}/document/doInsert.do",
			type:"POST",
			data: formData,
			dataType:"html", 
			enctype : 'multipart/form-data',
			contentType : false,
			processData : false,
		success:function(data){
			alert("등록이 성공 하였습니다.")
			window.location.href="${hContext}/document/document.do";
		},
		error:function(err){
			alert("등록이 실패 하였습니다.")
			
		}

		});					

		
		
	}



	
</script>


</html>
</html>