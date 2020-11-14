<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>OMG</title>
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

					<!-- 목록 -->
					<div class="row">
						<div class="col-lg-2">
							<div class="card shadow mb-4 py-3 border-left-primary">
								<div class="card-body">
									<form action="${hContext}/note/note.do" name="move_frm" method="get">
										<div class="list-group-flush">
											<input type="hidden" id="noteDiv" name="noteDiv" />
											<a href="#" onclick="javascript:moveNote(1); return false;" class="list-group-item"> 보낸 쪽지함 </a>
											<a href="#" onclick="javascript:moveNote(2); return false;" class="list-group-item"> 받은 쪽지함 </a>
											<a href="#" onclick="javascript:moveNote(3); return false;" class="list-group-item"> 휴지통 </a>
										</div>
									</form>
								</div>
							</div>
						</div>
						<div class="col-lg-10">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<a id="backBtn" href="#" class="btn btn-info btn-icon-split btn-sm">
										<span class="icon text-white-50"> <i class="fas fa-arrow-left"></i></span>
										<span class="text">취소</span>
									</a>
									<a href="#" class="btn btn-info btn-icon-split btn-sm">
										<span class="icon text-white-50"> <i class="fas fa-trash"></i></span>
										<span class="text">삭제</span>
									</a>
									<a href="#" id="insertBtn" class="btn btn-info btn-icon-split btn-sm">
										<span class="icon text-white-50"> <i class="fas fa-arrow-right"></i></span>
										<span class="text">전송</span>
									</a>
								</div>
								<div class="card-body">
									<form action="${hContext}/note/doInsert.do" method="post" name="insert_frm">
										<div class="row">
											<div class="col-lg-2 text-center">
												<label for="receive_id" >받는 사람</label>
											</div>
											<div class="col-lg-9">
												<input type="hidden" id="receiveDiv" name="receiveDiv" 
												<c:if test="${null ne noteVO }">value="1"</c:if> />
												<input type="text" class="form-control" id="receiveNm" name="receiveNm" readonly="readonly" placeholder="받는 사람 (검색 사용)" 
												<c:if test="${null ne noteVO }">value="${noteVO.senderNm}"</c:if>/>
												<input type="hidden" id="receiveId" name="receiveId" 
												<c:if test="${null ne noteVO }">value="${noteVO.senderId}"</c:if>/>
											</div>
											<input type="button" onclick="window.open('${hContext}/note/find.do','사원/부서 찾기','width=1000, height=900');" class="btn btn-info btn-sm" value="검색" id="search">
										</div>
										<div class="row py-2">
											<div class="col-lg-2 text-center">
												<label for="receive_ref" >참조</label>
											</div>
											<div class="col-lg-9">
												<input type="hidden" id="receiveRefDiv" name="receiveRefDiv" value="0" />
												<input type="text" class="form-control" id="receiveRefNm" name="receiveRefNm" readonly="readonly" placeholder="참조 (검색 사용)" />
												<input type="hidden" id="receiveRef" name="receiveRef" value="" />
											</div>
										</div>
										<div class="row">
											<div class="col-lg-2 text-center">
												<label for="title" >제목</label>
											</div>
											<div class="col-lg-10">
												<input type="text" class="form-control" id="title" name="title" placeholder="제목"
												<c:if test="${null ne noteVO }">value="RE : ${noteVO.title}"</c:if> />
											</div>
										</div>
										<div class="row py-2">
											<div class="col-lg-2"></div>
											<div class="col-lg-10">
												<textarea id="contents" name="contents" class="form-control" rows="20"><c:if test="${null ne noteVO }">${noteVO.contents}

=================================================================</c:if></textarea>
												
											</div>
										</div>
									</form>
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
	<script type="text/javascript">
	
	function moveNote(div){
		var frm = document.move_frm;
		frm.noteDiv.value = div;
		frm.submit();
	}

	$("#backBtn").on("click",function(){
		moveNote("${noteVO.noteDiv}");
	});

	$("#insertBtn").on("click",function(){
		console.log("전송");
		var receiveId = $("#receiveId").val(); 
		if( null == receiveId || receiveId.length == 0 ){
			alert("받는 사람(부서) 를 지정해 주세요.");
			return;
			}
		var title = $("#title").val();
		if( null == title || title.length == 0 ){
			alert("제목을 입력 해 주세요.");
			return;
			}
		var receiveRef = $("#receiveRef").val();
		if( receiveId==receiveRef ){
			alert("받는 사람(부서) 와 참조를 다르게 지정해 주세요");
			return;
			}
		
		$.ajax({
            type:"POST",
            url:"${hContext}/note/doInsert.do",
            dataType:"html",
            async: true,
            data:{
                "senderId" : "${sessionScope.employee.employee_id}",
                "senderNm" : "${sessionScope.employee.name}",
                "receiveDiv" : $("#receiveDiv").val(),
				"receiveId" : $("#receiveId").val(),
				"receiveNm" : $("#receiveNm").val(),
				"receiveRefDiv" : $("#receiveRefDiv").val(),
				"receiveRef" : $("#receiveRef").val(),
				"receiveRefNm" : $("#receiveRefNm").val(),
				"title" : $("#title").val(),
				"contents" : $("#contents").val(),
				"upNote" : "0",
                "noteDiv" : "1"
                },
         success: function(data){
           var parseData = JSON.parse(data);
           alert(parseData.msgContents);
           if(parseData.msgId>=1){
        	   moveNote(1);
           }
		},
         error:function(xhr,status,error){
             alert("error:"+error);
         }
        }); 

	})
		
	</script>
</body>
</html>