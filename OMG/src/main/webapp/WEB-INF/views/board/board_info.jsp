<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="hContext" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판</title>
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
					<div class="card shadow mb-4" style="width:70%">
						<!-- Page Heading -->
						<div class="card-header py-3 ">
							<div class="row">
								<div class="col-lg-6 text-left">
									<input type="button" class="btn btn-primary text-white-100" value="목록" id="moveList" />
									</div>
								
								<div class="col-lg-6 text-right">
									<c:if test="${vo.regId eq sessionScope.employee.employee_id}">
										<input type="button" class="btn btn-primary text-white-100" value="삭제" id="doDeleteBtn" />
										<input type="button" class="btn btn-primary text-white-100" value="수정" id="doUpdateBtn" />
									</c:if>
								</div>
							</div>
						</div>
						<div class="card-body">
							<h1 class="h3 mb-4 text-gray-800" style="padding-top: 20px; padding-left: 20px; width: 320px;">제목 : ${vo.title}</h1>
							<%-- <form class="form-horizontal" name="mngFrm" action="${hContext}/board/doSelectList.do" method="post"> --%>
							<input type="hidden" name="boardSeq" id="boardSeq" value="${vo.boardSeq}" />
							<input type="hidden" name="div" id="div" value="${vo.getDiv()}" />
							<input type="hidden" class="form-control" name="modId" id="modId" placeholder="수정자" maxlength="20">
							<div class="form-group">
								<label for="" class="col-sm-2 control-label">첨부파일</label>
								<div class="px-4 card card-body">
									<div>
										<c:choose>
											<c:when test="${0 ne fileList.size() }">
												<c:forEach var="vo" items="${fileList}">
													<form method="post" action="${hContext}/file/download.do">
														<input type="hidden" name="originName" value="${vo.originName}" />
														<input type="hidden" name="saveName" value="${vo.saveName}" />
														<a href="#" onclick="this.parentNode.submit(); return false;">${vo.originName}</a>
													</form>
												</c:forEach>
											</c:when>
										</c:choose>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-sm-2 control-label">내용</label>
								<div>
									<textarea class="form-control" rows="15" cols="40"
										name="contents" id="contents" readonly="readonly">${vo.contents}</textarea>
								</div>
							</div>
							<!-- </form> -->
							<!-- // page Content -->
							<hr/><hr/>
							<div id="comment_list_div">
								<div class="page-header">
									<!-- h4>댓글</h4> -->
									<label for="" class="col-sm-2 control-label">댓글</label>
								</div>
								<ul class="list-group" id="comment_list">
									<!-- 댓글 영역 -->
								</ul>
								<div class="panel panel-body panel-default">
									<form class="form-horizontal" name="comment_reg_frm" id="comment_reg_frm">
										<div class="form-group">
											<div class="py-4">
												<textarea class="form-control" style="resize: none;"
													id="write_contents" name="write_contents" rows="3" cols="100"
													placeholder="댓글을 입력하세요."></textarea>
											</div>
											<div style="float: right;">
												<input type="button" id="comment_reg_btn"
													class="btn btn-primary text-white-100"
													value="등록" style="margin: 13px;" />
											</div>
											<!-- <input type="hidden" name="regId" id="regId" value="admin"/> -->
											<input type="hidden" name="regId" id="regId"
												value="${employee.employee_id}" />
											<%-- <input type="hidden" name="boardSeq" id="boardSeq" value="${vo.getBoardSeq()}" /> --%>
											<input type="hidden" name="boardSeq" id="boardSeq"
												value="${vo.boardSeq}" /> <input type="hidden" name="upNum"
												id="upNum" value="0" />
											<!-- <input type="hidden" name="work_div" id="work_div" /> -->
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
			
			<!-- //Main Content -->
			</div>
			</div>	
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
		
		drawComment();
	});

	function moveToListView()
	{
		window.location.href="${hContext}/board/doSelectList.do?div="+$("#div").val();
	}

	function moveToBoardMng()
	{
		window.location.href="${hContext}/board/doSelectOneMng.do?boardSeq="+$("#boardSeq").val();
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

			moveToBoardMng()
			
	});

	function drawComment()
	{
		commentList = null;
		var boardSeq = "${vo.boardSeq}";
		console.log("게시글 번호 : "+boardSeq);

		$.ajax({
			type : "GET",
			url : "${hContext}/comments/doSelectList.do",
			dataType : "html",
			data : {
				"boardSeq" : boardSeq
			},
			success : function(data) 
			{//성공
				//console.log("data=" + data);
				commentList = JSON.parse(data);
				
				var html ="";
				var index = 0;
				if(null != commentList && commentList.length>0){
					for(var vo of commentList)
					{
						var col = 10;
						html += "<li class='list-group-item' style='border-left-width: 1px;left: 10px;'>";
						html += "<div class='row'>";
						html += "<input type='hidden' name='commentNum' id ='commentNum' value='"+vo.commentNum+"'/>";
						html += "<input type='hidden' name='upNum' id ='upNum' value='"+vo.upNum+"'/>";
						if( vo.upNum != 0)
						{
							html += "<div class='col-lg-1'></div>";
							col--;
						}
						/* html += "<a href='#' class='col-lg-1'>";
						// 이미지 가져오면 수정
						html += "<img src='/N1/images/profile/man.png' alt='프로필 사진' width='72' height='72'/>";
						html += "</a>"; */
						html += "<div id='comment_area' class='col-lg-"+col+"'>";
						html += "<div><label id='modId'>"+vo.modId+"</label></div>";
						/* html += "<div><label id='modId'>"${employee.employee_id}"</label></div>"; */
						html += "<div><p id='comment_contents'>"+vo.contents+"</p></div>";
						html += "<div>"+vo.modDt+"<a href='#' onclick='reply(this); return false;' class='btn btn-link'>답글쓰기</a></div>";
						html += "</div>";
						// 세션 ID로 변경
						/* if(vo.modId=="admin") */
						if(vo.modId=="${employee.employee_id}")
						{
						html += "<div class='dropdown col-lg-1 text-right'>";
						html += "<button class='btn btn-default btn-xs dropdown-toggle' data-toggle='dropdown'>";
						html += "...<span class='caret'></span>";
						html += "</button>"; 
						html += "<ul class='dropdown-menu'>";	
						html += "<li><a href='#' onclick='comment_modify(this); return false;'>수정</a></li>";	
						html += "<li><a href='#' onclick='comment_del(this); return false;'>삭제</a></li>";	
						html += "</ul>";
						html += "</div>";
						}
						html += "</div>";
						html += "</li>";
						
					}
				} 
				else 
				{
					html += "<div class='text-center'><label>등록된 댓글이 없습니다.</label></div>";
				}
				$("#comment_list").empty();
				$("#comment_list").append(html);
				console.log('1');
				$("#write_contents").val('');
				console.log('2');
			},
			error : function(xhr, status, error) {
				alert("error:" + error);
			}
		});//--ajax
		
	}; //-- drawComment

	function drawComment_write()
	{
		var html = '<div class="panel panel-body panel-default" id="reply_div">';
		html += '<div class="panel panel-body panel-default" id="comm_div">';
		html += '<div class="col-lg-1"></div>';
		html += '<form class="form-horizontal" name="reply_reg_frm" id="reply_reg_frm" >';
		html += '<div class="form-group col-lg-10">';
		/* html += '<div><label>현재 로그인한 사용자 ID</label></div>'; */
		html += '<div><label>${employee.employee_id}</label></div>';
		html += '<div><textarea class="form-control" style="resize:none;" id="write_contents" name="write_contents" rows="6" cols="100" placeholder="댓글을 입력하세요."></textarea></div>';
		html += '<input type="hidden" name="commentNum" id="commentNum" value="" />';
		html += '<input type="hidden" name="upNum" id="upNum" value="" />';
		/* html += '<input type="hidden" name="regId" id="regId" value="admin"/>'; */
		html += '<input type="hidden" name="regId" id="regId" value="${employee.employee_id}"/>';
		html += '<input type="hidden" name="boardSeq" id="boardSeq" value="${vo.getBoardSeq()}" />';
		html += '</div>';
		html += '</form>';
		html += '<div class="text-right col-lg-11">';
		html += '<input type="button" id="reply_cancel_btn" onclick="reply_remove();" class="btn btn-primary" value="취소" />';
		html += '<input type="button" id="reply_reg_btn" onclick="reply_insert();" class="btn btn-primary" value="등록" />';
		html += '</div>';
		html += '</div>';
		return html;
	}

	// 답글쓰기 클릭
	function reply(r)
	{
		var parent = $(r).parents("div.row");
		var replyDiv = $(r).parents("li.list-group-item");
		var upNum = parent.children('input#commentNum').val()
		console.log(upNum);
		var html = drawComment_write();
		$("#reply_div").remove();
		replyDiv.append(html);
		var frm = document.reply_reg_frm;
		frm.upNum.value = upNum;
		$(frm.write_contents).focus();
		
	}

	// 답글달기 취소
	function reply_remove()
	{
		$("#reply_div").remove();
	}
	
	// 답글달기 등록
	function reply_insert()
	{
		var reply_frm = document.reply_reg_frm;
		var comment_frm = document.comment_reg_frm;
		console.log(reply_frm.upNum.value);
		// 댓글 널 체크
		var cotents = reply_frm.write_contents.value;
		if (null == cotents || cotents.trim().length == 0) 
		{
			$(frm.write_contents).focus();
			alert("댓글을 입력하세요!");
			return;
		}
		
		comment_frm.upNum.value = reply_frm.upNum.value;
		comment_frm.write_contents.value = reply_frm.write_contents.value;
		$("#comment_reg_btn").trigger('click');
		comment_frm.upNum.value = 0;

	}

	// 댓글 등록
	$("#comment_reg_btn").on('click',function(){
		var frm = document.comment_reg_frm;
		var cotents = frm.write_contents.value;
		/* var modId = ${employee.employee_id}; */
		/* var upNum = $("#upNum").val(); */
		if (null == cotents || cotents.trim().length == 0) {
			$("#write_contents").focus();
			alert("댓글을 입력하세요!");
			return;
		}
		// 댓글 쓰기 부분 form 으로 변경
		// hidden 값 추가해서 답글 쓰기 부분 누르면 댓글 번호 추가 하면서 textarea에 해당 댓글 사용자 ID 출력(selectOne)
		// id를 버튼처럼 해서 누르면 사라지고 upnum도 초기화
		// 댓글 등록
		var formData = $("#comment_reg_frm").serialize();
		$.ajax({
			type : "POST",
			url : "${hContext}/comments/doInsert.do",
			dataType : "html",
			data : formData,
			success : function(data) { //성공
				console.log("data=" + data);
				var msg = JSON.parse(data);
				alert(msg.msgContents);
				drawComment();
			},
			error : function(xhr, status, error) {
				alert("error:" + error);
			}
		});//--ajax

		$("#write_contents").val('');
		
		
	}); // 댓글 쓰기

	// 댓글 수정 클릭
	function comment_modify(e){
		var parent = $(e).parents("div.row");
		var replyDiv = $(e).parents("li.list-group-item");
		var upNum = parent.children('input#upNum').val();
		var commentNum = parent.children('input#commentNum').val();
		var contents = parent.find('p#comment_contents').text();
		console.log(upNum);
		console.log(commentNum);
		//var parent = e.parentNode.parentNode.parentNode.parentNode;
		//var replyDiv = $(parent.parentNode);
		//var vo = commentList[parent.firstChild.value];
		var html = drawComment_write();
		$("#reply_div").remove();
		replyDiv.append(html);
		
		var frm = document.reply_reg_frm;
		frm.upNum.value = upNum;
		frm.commentNum.value = commentNum; 
		$(frm.write_contents).val(contents);
		$(frm.write_contents).focus();
		$("#reply_reg_btn").val("수정");
		$("#reply_reg_btn").attr("onclick","reply_update();");
	}
	
	// 수정 버튼 클릭
	function reply_update()
	{
		
		var frm = $("#reply_reg_frm").serialize();
		if (false == confirm("수정 하시겠습니까?")) return;
		
		$.ajax({
			type : "POST",
			url : "${hContext}/comments/doUpdate.do",
			dataType : "html",
			data : frm,
			success : function(data) 
			{ //성공
				console.log("data=" + data);
				var msg = JSON.parse(data);
				alert(msg.msgContents);
				drawComment();
			},
			error : function(xhr, status, error) 
			{
				alert("error:" + error);
			}
		});//--ajax
	}

	//댓글삭제
	function comment_del(e){
		var parent = $(e).parents("div.row");
		var commentNum = parent.children('input#commentNum').val();
		
		if (false == confirm("정말로 하시겠습니까? 삭제 시 연결된 답글들도 전부 삭제 됩니다.")) return;
		
		console.log("삭제");
		// 삭제시 id는 연결된 session에 로그인된 아이디값
		$.ajax({
			type : "POST",
			url : "${hContext}/comments/doDelete.do",
			dataType : "html",
			data : {
				"regId" : $("#regId").val(),
				"commentNum" : commentNum
			},
			success : function(data) { //성공
				console.log("data=" + data);
				var msg = JSON.parse(data);
				alert(msg.msgContents);
				drawComment();
				$("#upNum").val('0');
			},
			error : function(xhr, status, error) {
				alert("error:" + error);
			}
		});//--ajax
	}
	
	</script>
</body>
</html>