<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="hContext" value="${pageContext.request.contextPath }"></c:set> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 수정</title>
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
				<h1 class="h3 mb-4 text-gray-800">게시글 수정</h1>
				<div class="card shadow mb-4" style="width:70%">
					<!-- Page Heading -->
					<div class="card-header py-3 ">
						<div class="row">
							<div class="col-lg-12 text-right">
								<input type="button" class="btn btn-primary text-white-100"  value="목록" id="moveList" />
								<input type="button" class="btn btn-primary text-white-100"  value="삭제" id="doDeleteBtn" />
								<input type="button" class="btn btn-primary text-white-100"  value="수정"  id="doUpdateBtn" />
							</div>
						</div>
					</div>
					<div class="card-doby">
						<form class="form-horizontal" name="mngFrm" action="${hContext}/board/doSelectLsit.do" method="post">
							<input type="hidden" name="boardSeq"     id="boardSeq"  value="${vo.boardSeq}" />
							<input type="hidden" name="div"     id="div"  value="${vo.getDiv()}" />
							<input type="hidden" name="filecode"     id="filecode"  value="${vo.filecode}" />
						<!-- <input type="hidden" name="boardSeq" 	id="boardSeq" /> -->
							<div class="form-group">
								<label for="" class="col-sm-2 control-label">제목</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="title" id="title" placeholder="제목" maxlength="200" value="${vo.title}" />
								</div>
							</div>
							<input type="hidden" class="form-control" name="modId" id="modId" placeholder="수정자" value="${sessionScope.employee.name}" maxlength="20">		
							<div class="form-group">
								<label for="" class="col-sm-2 control-label">기존파일</label>
								<div class="px-4 ">
									<div id="changeFile" class="card card-body" style="width: 84%;">
										<c:choose>
											<c:when test="${0 ne fileList.size() }">
												<c:forEach var="vo" items="${fileList}" >
													<a href="#" onclick="fileDown({vo.originName},${vo.saveName}); return false;">${vo.originName}</a>
												</c:forEach>
											</c:when>
										</c:choose>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-sm-2 control-label">새 파일</label>
								<div class="px-4 ">
									<div id="newFile" class="card card-body" style="width: 84%;"></div>
									<input class="py-2" type="file" onchange="file_upload(this)" id="file" name="file" multiple />
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-sm-2 control-label">내용</label>
								<div class="col-sm-10">
									<textarea  class="form-control" rows="15" cols="40" name="contents" id="contents">${vo.contents}</textarea>
								</div>
							</div>
						</form>
					</div>
					
				</div>
			<!-- // page Content -->
		</div>
		<%-- <div class="container col-lg-12" id="comment_list_div">
			<div class="page-header">
				<!-- h4>댓글</h4> -->
				<label for="" class="col-sm-2 control-label">댓글</label>
			</div>
			<ul class="list-group" id="comment_list">
				<!-- 댓글 영역 -->
			</ul>
			<div class="panel panel-body panel-default">
				<form class="form-horizontal" name="comment_reg_frm" id="comment_reg_frm" >
					<div class="form-group col-lg-10">
						<div>
							<label>${employee.employee_id}</label>
						</div>
						<div>
							<textarea class="form-control" style="resize:none;" id="write_contents" name="write_contents" rows="3" cols="100" placeholder="댓글을 입력하세요."></textarea>
						</div>
						<div style="float: right;">
							<input type="button" id="comment_reg_btn" class="btn btn-primary btn-icon-split icon text-white-100"  value="등록" style="margin: 13px;" />
						</div>
						<!-- <input type="hidden" name="regId" id="regId" value="admin"/> -->
						<input type="hidden" name="regId" id="regId" value="${employee.employee_id}"/>
						<input type="hidden" name="boardSeq" id="boardSeq" value="${vo.getBoardSeq()}" />
						<input type="hidden" name="boardSeq" id="boardSeq" value="${vo.boardSeq}" />
						<input type="hidden" name="upNum" id="upNum" value="0"/>
						<!-- <input type="hidden" name="work_div" id="work_div" /> -->
					</div>
				</form>
			</div>
		</div> --%>
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
		//drawComment();
	});

	function moveToListView()
	{
		window.location.href="${hContext}/board/doSelectList.do?div="+$("#div").val();
	}

	$("#moveList").on("click",function()
			{
		//console.log("moveList");
		moveToListView();
	});

	//삭제
	$("#doDeleteBtn").on("click", function() 
	{
		alert("#doDeleteBtn");
		
		var boardSeq = $("#boardSeq").val();
		
		if(false==confirm("삭제 하시겠습니까?"))return;

		$.ajax({
			type:"POST",
			url:"${hContext}/board/doDelete.do",
			dataType:"html", 
			data:{
					"boardSeq" :$("#boardSeq").val(),
			},
			success:function(data)
			{//성공
			//console.log("data="+data);
			//alert("data:"+data);
			
			//json 분리해서 변수
			var jsonObj = JSON.parse(data);
			console.log("msgId="+jsonObj.msgId);
			console.log("msgContents="+jsonObj.msgContents);

				if(null !=jsonObj && jsonObj.msgId=="1")
				{
					alert(jsonObj.msgContents);
					//board_list.jsp로 이동
					moveToListView();
				}
			},
			error:function(xhr,status,error)
			{//실패
			 alert("error:"+error);
			},
			complete:function(data){
			
			}
		  
	});//--ajax
		
	});

	$("#doUpdateBtn").on("click", function() 
	{
			console.log("#doUpdateBtn");
			
			var title = $("#title").val();
			console.log("title:"+title);
			if(null == title || title.trim().length==0){
				$("#title").focus();
				alert("제목을 입력하세요.");
				return;
			}
			
			var contents = $("#contents").val();
			console.log("contents:"+contents);
			if(null == contents || contents.trim().length==0){
				$("#contents").focus();
				alert("내용을 입력하세요.");
				return;
			}	

			if(false==confirm("수정 하시겠습니까?"))return;

			var frm = document.mngFrm;
			var formData = new FormData(frm);

			$.ajax({
				type:"POST",
				url:"${hContext}/board/doUpdate.do",
				dataType:"html", 
				enctype : 'multipart/form-data',
				contentType : false,
				processData : false,
				data: formData,
				success:function(data)
				{//성공
				//console.log("data="+data);
				//alert("data:"+data);
				
				//json 분리해서 변수
				var jsonObj = JSON.parse(data);
				console.log("msgId="+jsonObj.msgId);
				console.log("msgContents="+jsonObj.msgContents);

					if(null !=jsonObj && jsonObj.msgId=="1")
					{
						alert(jsonObj.msgContents);
						moveToListView();
					}
				},
				error:function(xhr,status,error)
				{
				 alert("error:"+error);
				},
				complete:function(data){
				
				}

		});//--ajax


			
	});

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
		
		$("#newFile").empty();
		$("#newFile").append(html);
	}
	
	
	</script>
</body>
</html>