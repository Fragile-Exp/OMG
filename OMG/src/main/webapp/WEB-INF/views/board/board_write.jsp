<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="hContext" value="${pageContext.request.contextPath }"></c:set> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 등록</title>
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
					<h1 class="h3 mb-4 text-gray-800">게시글 등록</h1>
						<div class="card shadow mb-4" style="width:70%">
							<!-- Page Heading -->
							<div class="card-header py-3 ">
								<div class="row">
									<div class="col-lg-6">
										<input type="button" class="btn btn-primary text-white-100"  value="목록" id="moveList" />
										
									</div>
									<div class="col-lg-6 text-right">
										<input type="button" class="btn btn-primary text-white-100"  value="등록"  id="doInsertBtn"/>
										<input type="button" class="btn btn-primary text-white-100"  value="초기화" id="doClearBtn"  />
									</div>
								</div>
							</div>
							<div class="card-body">
								<form class="form-horizontal" name="writeFrm" action="" method="post" enctype="multipart/form-data">
									<input type="hidden" name="div"     id="div"  value="${boardDiv}" />
									<!-- <input type="hidden" name="boardSeq" 	id="boardSeq" /> -->
									<div class="form-group">
										<label for="" class="col-sm-2 control-label">제목</label>
										<div>
											<input type="text" class="form-control" name="title" id="title" placeholder="제목" maxlength="200">
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-sm-2 control-label">작성자</label>
										<div>
											<input type="text" class="form-control" name="regId" id="regId" placeholder="작성자" maxlength="20" value="${sessionScope.employee.employee_id}" readonly="readonly">
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-sm-2 control-label">내용</label>
										<div >
											<textarea  class="form-control" rows="15" cols="40" name="contents" id="contents"></textarea>
										</div>
									</div>
									<div class="form-group">
										<div>
											<input type="file" onchange="file_upload(this)" id="file" name="file" multiple />
										</div>
									</div>
								</form>
							</div>
						
							<ul class="list-group" >
								<li class="list-group-item  col-lg-6" style="border-width: 0px;left: 5px;">
									<table class="table table-striped table-bordered" id="fileListTable">
										<thead>
											<tr>
												<th>첨부 파일명</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</li>
							</ul>
						</div>	
					</div>
				</div>
				<!-- // page Content -->
				
			
			<!-- //Main Content -->

			<!-- footer -->
			<%@include file="/WEB-INF/views/inc/footer.jsp"%>
			<!-- //footer -->
		</div>
		<!-- //Content Wrapper -->
</div>
<!-- //wrap -->
	<script type="text/javascript">
	$(document).ready(function()
	{
		/* $("#Pages").attr("class","nav-link");
		$("#Pages").attr("aria-expanded","true");
		$("#collapsePages").attr("class","collapse show");
		$("#blank").attr("class","collapse-item active"); */
	});

	//게시판 초기화버튼
	$("#doClearBtn").on("click",function()
	{
		$("#title").val('');
		$("#contents").val('');
		$("#title").focus();
	});

	//게시판 목록 이동
	function moveToListView()
	{
		window.location.href="${hContext}/board/doSelectList.do?div="+$("#div").val();
	}

	//게시판 목록버튼
	$("#moveList").on("click",function(){
		console.log("moveList");
		moveToListView();
	});

	//게시판 등록버튼
	$("#doInsertBtn").on("click",function()
	{
		console.log("#doInsertBtn");

		var title = $("#title").val();
		console.log("title:"+title);
		if(null == title || title.trim().length==0)
		{
			$("#title").focus();
			alert("제목을 입력하세요.");
			return;
		}
		
		var regId = $("#regId").val();
		console.log("regId:"+regId);
		if(null == regId || regId.trim().length==0)
		{
			$("#regId").focus();
			alert("등록자를 입력하세요.");
			return;
		}
		
		var contents = $("#contents").val();
		console.log("contents:"+contents);
		if(null == contents || contents.trim().length==0)
		{
			$("#contents").focus();
			alert("내용을 입력하세요.");
			return;
		}			
		
		//confirm : 확인
		if( false==confirm("저장 하시겠습니까?"))return;
		
		var frm = document.writeFrm;
		var formData = new FormData(frm);
		
		$.ajax({
			type:"POST",
			url:"${hContext}/board/doInsert.do",
			dataType:"html", 
			enctype : 'multipart/form-data',
			contentType : false,
			processData : false,
			data: formData,
		success:function(data)
			{ //성공
			var jsonObj = JSON.parse(data);
			console.log("msgId="+jsonObj.msgId);
			console.log("msgContents="+jsonObj.msgContents);

				// msgId (fileCOde) 가 1보다 크거나 같으면
				if(null !=jsonObj && jsonObj.msgId >=1)
				{
					alert(jsonObj.msgContents);
					//board_list.jsp로 이동 
					//window.location.href="/EJDBC/board/board.do?work_div=doSelectList";
					moveToListView();
				}
			},
			error:function(xhr,status,error)
			{//실패
			 alert("error:"+error);
			},
			complete:function(data)
			{
				
			}

		});//--ajax

		
	});

	function fileSave(fileCode){


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
</body>
</html>