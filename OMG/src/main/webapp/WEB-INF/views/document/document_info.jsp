<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OMG</title>
</head>

<style>
	.card { width: 120%;}	
	.btn-box {float:right;}

</style>

<body id="page-top">
<!-- wrap -->
<div id="wrapper">
	<!-- side_bar -->
	<%@include file="/WEB-INF/views/inc/side_bar.jsp" %>
	<!-- //side_bar -->
		<!-- Content Wrapper -->
    	<div id="content-wrapper" class="d-flex flex-column">
				
	      	<!-- Main Content -->
	      	<div id="content">
	      		<!-- top_bar -->
	      		<%@include file="/WEB-INF/views/inc/top_bar.jsp" %>
	      		<!-- //top_bar -->
	      		
	      		<!-- page Content -->
	      		<div class="container-fluid">

					<!-- Page Heading -->
					<div class="d-sm-flex align-items-center justify-content-between mb-4" >
					  <h1 class="h3 mb-0 text-gray-800">문서 수정 페이지</h1>
					</div>
					
				
					<!-- Content Row -->
					
					<div class="row">
					
					  <!-- Area Chart -->
					  <div class="col-xl-15 col-lg-10">
					    <div class="card shadow mb-4">
					      <!-- Card Header - Dropdown -->
					      <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					        <h6 class="m-0 font-weight-bold text-primary">문서 상세</h6>	
					        <div style=" float: right;">
					        	<a  onClick="update()"  class="btn btn-sm btn-primary shadow-sm" style="color:white"><i class="fas fa-file-upload fa-sm text-white-50"></i>수정</a>
					  			<a href="${hContext}/document/document.do" class="btn btn-sm btn-primary shadow-sm" style="color:white"><i class="fas fa-backspace fa-sm text-white-50"></i>취소</a>
					  	    </div>
					       
					      </div>
					      <!-- Card Body -->
					      <div class="card-body">
					        <div class="chart-area" style=" height:100%; ">
					      		<!-- to do : 값들 name/id 부여 -->
					      		<!-- 제목  -->
					      		<form  name="writeFrm" action="" method="post" enctype="multipart/form-data">
					      		<div>
						      		<div class="card-body-label" style="display: inline-block; width:7%; margin-bottom:10px;">
						      			<div class="title-header bg-primary text-white btn-sm" style="text-align:center;"  >
						      				제목
						      			</div>
						      		</div>
						      		<div class="title-body" style="display: inline-block;">
						      			<input id="title" name="title"class="title-input" type="text" style=" width :900px;" value="${SeleteOne.title}">
						      		</div>
					        	</div>
					        	<!-- //제목  -->
					        	<!-- 종류 / 기간   -->
					        	<div>
						        	<div class="card-body-label" style="display: inline-block; width:7%; margin-bottom:10px;">
						      			<div class="kind-header bg-primary text-white btn-sm" style="text-align:center;" >
						      				종류
						      			</div>
						      		</div>
						      		<div class="kind-body" style="display: inline-block;">
						      			<select id="kind" name="kind" class="kind-body-select" style="width:400px;">
							      			<c:choose>
							      				<c:when test="${SeleteOne.kind == 0}"><option value="0">휴가</option></c:when>
							      				<c:when test="${SeleteOne.kind == 1}"><option value="1">실험<option></c:when>
							      				<c:when test="${SeleteOne.kind == 2}"><option value="2">문서<option></c:when>
							      				<c:when test="${SeleteOne.kind == 3}"><option value="3">물풀구매<option></c:when>
							      				<c:when test="${SeleteOne.kind == 4}"><option value="4">프로젝트<option></c:when>
							      				<c:when test="${SeleteOne.kind == 5}"><option value="5">회의록<option></c:when>
							      				<c:when test="${SeleteOne.kind == 6}"><option value="6">행사<option></c:when>
							      				<c:when test="${SeleteOne.kind == 7}"><option value="7">인사<option></c:when>
							      				<c:when test="${SeleteOne.kind == 8}"><option value="8">총무<option></c:when>
							      				<c:when test="${SeleteOne.kind == 9}"><option value="9">사직<option></c:when>
							      			</c:choose>
							      			<opiton>--------------------</opiton>
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
						      		<div class="card-body-label" style="display: inline-block; width:7%; margin-bottom:10px;">
						      			<div class="dDay-header bg-primary text-white btn-sm" style="text-align:center;" >
						      				기간
						      			</div>
						      		</div>
						      		<div class="dDay-body" style="display: inline-block;">
						      			<!-- to do : value값 데이터 가져와서 입력 -->
						      			<input id="dDay" name="dDay" type="date" style="width:400px;" value="${SeleteOne.dDay}" >
						      		</div>
						      	</div>
						      	<!--// 종류 / 기간   -->
						      	<!-- 파일 등록  -->
						      	<div>
						      		<div class="card-body-label" style="display: inline-block; width:7%; margin-bottom:10px;">
						      			<div class="file-header bg-primary text-white btn-sm" style="text-align:center;" >
						      				파일 
						      			</div>
						      		</div>
						      		<div class="file-body" style="display: inline-block;"> 
						      			<input id="file" name="file" class="file-input" multiple="multiple"   type="file" onchange="file_upload(this)"  multiple  style=" width :300px;">
						      		</div>
						      		<div class="card card-body" style="width: 900px;">
										<c:choose>
											<c:when test="${0 ne fileList.size() }">
												<c:forEach var="vo" items="${fileList}" >
													
													<input type="hidden" name="originName"  id="originName" value="${vo.originName}" />
													<input type="hidden" name="saveName" id="saveName" value="${vo.saveName}" />
													<c:set var="originName" value="${vo.originName}" />
													<c:set var="saveName" value="${vo.saveName}"/>
													
													
													<a href="#" onclick='filedown("${originName}","${saveName}"); return false;'>${vo.originName}</a>
													
												</c:forEach>
											</c:when>
										</c:choose>
									</div>
										
					        	</div>
					        	<!--// 파일 등록 -->
					        	<!-- 결재자 -->
					        	<div>
						        	<div class="card-body-label" style="display: inline-block; width:7%; margin-bottom:10px;">
						      			<div class="approval-header bg-primary text-white btn-sm" style="text-align:center;" >
						      				결재자
						      			</div>
						      		</div>
						      		<div class="approval-body" style="display: inline-block;">
						      			<!-- to do : for문 사용해서 사원 입력하기  -->
						      			<select class="approval-dept" name="dept_no" style="width:200px;" disabled>
							      			<c:choose>
							      				<c:when test="${emp.dept_no==10000}"><option selected>omg</option></c:when>
							      				<c:when test="${emp.dept_no==11000}"><option selected>전략기획본부</option></c:when>
							      				<c:when test="${emp.dept_no==12000}"><option selected>경영관리본부</option></c:when>
							      				<c:when test="${emp.dept_no==13000}"><option selected>기술개발본부</option></c:when>
							      				<c:when test="${emp.dept_no==14000}"><option selected>영업본부</option></c:when>
							      				<c:when test="${emp.dept_no==13100}"><option selected>연구소</option></c:when>
							      				<c:when test="${emp.dept_no==13110}"><option selected>제1연구소</option></c:when>
							      				<c:when test="${emp.dept_no==13120}"><option selected>제2연구소</option></c:when>
							      				<c:when test="${emp.dept_no==13200}"><option selected>기술부문</option></c:when>
							      				<c:when test="${emp.dept_no==13210}"><option selected>기술1팀</option></c:when>
							      				<c:when test="${emp.dept_no==13220}"><option selected>기술2팀</option></c:when>
							      				<c:when test="${emp.dept_no==14100}"><option selected>아시아영업부</option></c:when>
							      			</c:choose>
							      		</select>
						      		</div>
						      		<div class="approval-body" style="display: inline-block;">
						      			<!-- to do : for문 사용해서 사원 입력하기  -->
						      			<select class="approval-level" style="width:200px;" disabled>
							      			<c:choose>
							      				<c:when test="${emp.position_no==10000}"><option selected>사장</option></c:when>
							      				<c:when test="${emp.position_no==11000}"><option selected>부사장</option></c:when>
							      				<c:when test="${emp.position_no==11100}"><option selected>전무이사</option></c:when>
							      				<c:when test="${emp.position_no==11200}"><option selected>상무이사</option></c:when>
							      				<c:when test="${emp.position_no==11300}"><option selected>이사</option></c:when>
							      				<c:when test="${emp.position_no==11310}"><option selected>수석</option></c:when>
							      				<c:when test="${emp.position_no==11320}"><option selected>책임</option></c:when>
							      				<c:when test="${emp.position_no==11330}"><option selected>선임</option></c:when>
							      				<c:when test="${emp.position_no==11331}"><option selected>사원</option></c:when>
							      				<c:when test="${emp.position_no==11332}"><option selected>인턴</option></c:when>
							      				<c:when test="${emp.position_no==11333}"><option selected>인턴2</option></c:when>
							      			</c:choose>
							      		</select>
						      		</div>
						      		<div style="display: inline-block;">
						      			<input type="text" value="${emp.name}" disabled>
						      		</div>
						      		
						      	</div>
					        	<div class="card-body-label" style="width:7%; margin-bottom:10px;">
						      			<div class="cont-header bg-primary text-white btn-sm" style="text-align:center; width:100px;" >
						      				문서 내용
						      			</div>
						      	</div>
						    
						      	<input id="cont" name="documentCont" class="cont-body" type="text" style="width:900px; height:100px;" value="${SeleteOne.documentCont}">
					        
					        
					        	<input id="documentId" name="documentId" type="hidden" value="${SeleteOne.documentId}">
					        	<input id="employeeId" name="employeeId" type="hidden" value="${SeleteOne.employeeId}">
					        	<input id="documentSet" name="" type="hidden" value="${SeleteOne.documentSet}">
					        	<input id="okUser" name="documentSet" type="hidden" value="${SeleteOne.okUser}">
					        	<input id="fileCode" name="fileCode" type="hidden" value="${SeleteOne.fileCode}">
					        	
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
	      	<%@include file="/WEB-INF/views/inc/footer.jsp" %>
	      	<!-- //footer -->
	      	
		</div>
		<!-- //Content Wrapper -->
</div>
<!-- //wrap -->


</body>
<script type="text/javascript">



	function filedown(originName, saveName){
		var form = document.createElement("form");
		form.method = 'post';
		form.action = "${hContext}/file/download.do" ;

		var input01 = document.createElement('input');
		var input02 = document.createElement('input');

		input01.setAttribute("type","hidden");
		input01.setAttribute("name","originName");
		input01.setAttribute("value",originName);

		input02.setAttribute("type","hidden");
		input02.setAttribute("name","saveName");
		input02.setAttribute("value",saveName);
		
		form.appendChild(input01);
		form.appendChild(input02);

		document.body.appendChild(form);

		form.submit();
		
		
	}


	function update(){

		var frm = document.writeFrm;
		var formData = new FormData(frm);
		
		$.ajax({
			url:"${hContext}/document/doUpdate.do",
			type:"POST",
			data: formData,
			dataType:"html", 
			enctype : 'multipart/form-data',
			contentType : false,
			processData : false,
		success:function(data){
			
			if(confirm("수정이 성공하였습니다. 목록 페이지로 이동합니다.")==true){
				window.location.href="${hContext}/document/document.do";
			}
		},
		error:function(err){
			alert("수정이 실패 하였습니다.");
			
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
	




	
		
</script>
</html>