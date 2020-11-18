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
				
				<input type="hidden" id="sessionId" value="" />
				<input type="hidden" id="roomNo" value="${roomVO.roomNo}" />
				<div align="center">
					<!-- Page Heading -->
					<h1 class="h3 mb-4 text-gray-800">${roomVO.roomNm}</h1>
					<div class="row d-flex justify-content-center">
						<div id="chating" class="jumbotron" style="width: 1000px; height: 800px; overflow:auto;" ></div>
						<div>
							<div class="card" style="width:200px; height: 800px; overflow:auto;">
								<div id="userList" class="card-body">
									
								</div>
							</div>
						</div>
					</div>
					<input type="hidden" name="userName" id="userName" value="${sessionScope.employee.name} ${sessionScope.employee.position_nm}" />

					<div id="yourMsg">
						<table class="inputTable">
							<tr>
								<th>메시지</th>
								<th><input type="text" id="chatting" size="30" placeholder="보내실 메시지를 입력하세요." /></th>
								<th><input type="button" onclick="send()" id="sendBtn" value="보내기" /></th>
								<th><input type="button" onclick="exit()" id="sendBtn" value="나가기" /></th>
							</tr>
						</table>
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

	<script type="text/javascript">
	$(document).ready(function(){
		$("#chattingPlace").addClass("active");
		wsOpen();
		});

	var ws;

	$(window).bind("beforeunload", function (e){
		exit();
	});

	function wsOpen(){
		ws = new WebSocket("ws://"+location.host+"/cmn/chatting/${roomVO.roomNo}.do");
		wsEvt();
	}

	function wsEvt() {
		ws.onopen = function(data){
			//소켓이 열리면 초기화 세팅하기
			$("#chating")
		}
		
		ws.onmessage = function(data) {
			// 메시지를 받았을 시
			var msg = data.data;
			if(msg != null && msg.trim() != ''){
				var d = JSON.parse(msg);
				// 아이디 구분
				if(d.type == "getId"){ // 입장 시
					var si = d.sessionId != null ? d.sessionId : "";
					if(si != ""){
						$("#sessionId").val(si);
						enter();
					}
				} else if(d.type == "message"){
					if(d.sessionId == $("#sessionId").val()){
						$("#chating").append("<p class='text-right''>나 :" + d.msg + "</p>");	
					}else{
						$("#chating").append("<p class='text-left''>" + d.userName + " :" + d.msg + "</p>");
					}
				} else if(d.type == "enter"){
					$("#chating").append("<p class='text-center font-weight-bold''>" +d.userName + d.msg + "</p>");
					// 채팅방 참여 인원수
					//$("#enterCnt").text(Number($("#enterCnt").text())+1);
				} else if(d.type == "exit"){
					$("#chating").append("<p class='text-center font-weight-bold''>" +d.userName + d.msg + "</p>");
				} else{
					console.warn("unknown type!");
				}
				//alert(d.userList[0]);
				
					$("#userList").empty();
					$.each(d.userList, function(i, value) {
						$("#userList").append("<p calss='h6'>"+value+"</p>");
					});
					


				
				$("#chating").scrollTop($("#chating")[0].scrollHeight);
			}
		}

		document.addEventListener("keypress", function(e){
			if(e.keyCode == 13){ //enter press
				send();
			}
		});
	}

	function exit(){
		var option = {
				type : "exit",
				roomNo : $("#roomNo").val(),
				sessionId : $("#sessionId").val(),
				userName : $("#userName").val(),
				msg : "님이 퇴장 했습니다."
			}
		ws.send(JSON.stringify(option));
		$('#chatting').val("");
		$('#chatting').focus();
		ws.close();
		window.location.href="${hContext}/chat/room.do";
		}
	
	

	function enter(){
		var option = {
				type : "enter",
				roomNo : $("#roomNo").val(),
				sessionId : $("#sessionId").val(),
				userName : $("#userName").val(),
				msg : "님이 입장 했습니다."
			}
		ws.send(JSON.stringify(option));
		$('#chatting').val("");
		$('#chatting').focus();
		}


	function send() {
		var option = {
				type : "message",
				roomNo : $("#roomNo").val(),
				sessionId : $("#sessionId").val(),
				userName : $("#userName").val(),
				msg : $("#chatting").val()
			}
		ws.send(JSON.stringify(option));
		$('#chatting').val("");
		$('#chatting').focus();
	}
	
	</script>
</body>
</html> 