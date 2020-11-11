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
				
				<form class="form-horizontal" name="writeFrm" action="${hContext}/board/doSelectLsit.do" method="post">
					<input type="button" class="btn btn-primary btn-icon-split icon text-white-100"  value="등록"  id="doInsertBtn" style="float: right;  margin: 13px;" />
					<input type="button" class="btn btn-primary btn-icon-split icon text-white-100"  value="목록" id="moveList"  style="float: right;  margin: 13px;" />
					<input type="button" class="btn btn-primary btn-icon-split icon text-white-100"  value="초기화" id="doClearBtn"  style="float: right;  margin: 13px;" />
					<input type="hidden" name="div"     id="div"  value="${boardDiv}" />
					<!-- <input type="hidden" name="boardSeq" 	id="boardSeq" /> -->
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">제목</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="title" id="title" placeholder="제목" maxlength="200">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">작성자</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="regId" id="regId" placeholder="작성자" maxlength="20">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">내용</label>
						<div class="col-sm-10">
							<textarea  class="form-control" rows="15" cols="40" name="contents" id="contents"></textarea>
						</div>
					</div>
				</form>
		
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
	<script type="text/javascript">
	$(document).ready(function()
	{
		$("#Pages").attr("class","nav-link");
		$("#Pages").attr("aria-expanded","true");
		$("#collapsePages").attr("class","collapse show");
		$("#blank").attr("class","collapse-item active");
	});

	function moveToListView()
	{
		window.location.href="${hContext}/board/doSelectList.do?div="+$("#div").val();
	}

	$("#moveList").on("click",function(){
		console.log("moveList");
		moveToListView();
	});

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
		
		$.ajax({
			type:"POST",
			url:"${hContext}/board/doInsert.do",
			dataType:"html", 
			data:{
					"div" :$("#div").val(),
					"title":$("#title").val(),
					"contents":$("#contents").val(),
					"regId":$("#regId").val()
				},
			success:function(data)
			{ //성공
			var jsonObj = JSON.parse(data);
			console.log("msgId="+jsonObj.msgId);
			console.log("msgContents="+jsonObj.msgContents);

				if(null !=jsonObj && jsonObj.msgId=="1")
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
	
	</script>
</body>
</html>