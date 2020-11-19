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

				<div align="center">
				<!-- Page Heading -->
				<h1 class="h3 mb-4 text-gray-800">채팅방 목록</h1>
					<div id="mainbody" class="card shadow mb-4" style="width: 1000px; height: 680px" >
						<div class="card-header py-3">
							<div class="row">
								<div class="col-lg-6">
								</div>
								<div class="col-lg-6 row" align="right">
									<div class="col-lg-9">
										<input type="text" class="form-control" name="roonNm" id="roomNm" placeholder="채팅방 이름"/>
									</div>
									
									<a id="createRoom" href="#" class="btn btn-info btn-sm col-lg-3">
										<span class="text">채팅방 생성</span>
									</a>
								</div>
							</div>
						</div>
						<div class="card-body" style="height: 600px; overflow: auto;">
							<div class="table-responsive">
								<table id="roomList" class="table table-striped table-bordered table-hover table-condensed">
									<thead>
										<tr>
											<th width="10%">방 번호</th>
											<th width="80%">방 이름</th>
											<th>참여</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${null ne list}">
												<c:forEach var="vo" items="${list}">
													<tr>
														<td>${vo.roomNo}</td>
														<td>${vo.roomNm}</td>
														<td><input type="button" class="btn btn-primary btn-sm" onclick="goRoom('${vo.roomNo}','${vo.roomNm}');" value="참여" />
														<c:if test="${(vo.regId eq sessionScope.employee.employee_id) || (sessionScope.employee.auth == 9) }">
														<input type="button" class="btn btn-primary btn-sm" onclick="deleteRoom('${vo.roomNo}'); return false;" value="삭제" />
														</c:if>
														</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td colspan="3" class="text-center"> 생성된 채팅방이 없습니다. </td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
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

	<script type="text/javascript">
	$(document).ready(function(){
		$("#chattingPlace").addClass("active");
		});

	function goRoom(no,nm){
		window.location.href="${hContext}/chat/moveChat.do?roomNo="+no+"&roomNm="+nm;
		}

	function deleteRoom(no){
		$.ajax({
            type:"GET",
            url:"${hContext}/chat/doDelete.do",
            dataType:"html",
            async: true,
            data:{
                "roomNo" : no
                },
         success: function(data){
           var parseData = JSON.parse(data)
           if(parseData.msgId == "1"){
        	    alert(parseData.msgContents);
				window.location.reload();
           } else{
				alert(parseData.msgContents);
           }
           
		},
         error:function(xhr,status,error){
             alert("error:"+error);
         }
        }); 
		}
	
	$("#createRoom").on("click",function(){
		var roomNm = $("#roomNm").val();

		if(null == roomNm || roomNm.length ==0){
			alert("방 제목을 입력하세요.");
			}

		$.ajax({
            type:"GET",
            url:"${hContext}/chat/doInsert.do",
            dataType:"html",
            async: true,
            data:{
                "roomNm" : roomNm ,
                "regId" : "${sessionScope.employee.employee_id}"
                },
         success: function(data){
           var parseData = JSON.parse(data)
           if(parseData.message.msgId == "1"){
				goRoom(parseData.roomNo);
           } else{
				alert(parseData.message.msgContents);
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