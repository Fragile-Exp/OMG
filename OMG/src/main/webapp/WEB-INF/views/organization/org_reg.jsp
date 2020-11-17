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
							<div class="card mb-4 py-3 border-left-primary">
								<div class="card-body">
									<form action="${hContext}/org/org.do" name="move_frm" method="get">
										<div class="list-group-flush">
											<input type="hidden" id="moveDiv" name="moveDiv" />
											<a href="#" onclick="javascript:changeOrg('dept'); return false;" class="list-group-item"> 부서 </a>
											<a href="#" onclick="javascript:changeOrg('position'); return false;" class="list-group-item"> 직급 </a>
										</div>
									</form>
								</div>
							</div>
						</div>
						<div class="col-lg-10" >
							<div class="card shadow mb-4" style="width:1000px;">
								<div class="card-header py-3">
									<a href="javascript:history.back();" class="btn btn-info btn-icon-split btn-sm">
										<span class="icon text-white-50"> <i class="fas fa-arrow-left"></i></span>
										<span class="text">취소</span>
									</a>
									<a id="saveBtn" href="#" class="btn btn-info btn-icon-split btn-sm">
										<span class="icon text-white-50"> <i class="fas fa-check"></i></span>
										<span class="text">등록</span>
									</a>
								</div>
								<div class="card-body " >
									<%-- <form action="${hContext}/org/doInsert.do" name="save_frm" method="post"> --%>
									<input type="hidden" id="orgDiv" name="orgDiv" value="${orgDiv}" />
										<c:choose>
											<c:when test="${orgDiv eq 'dept' }">
												<c:set var="orgName" value="부서" />
											</c:when>
											<c:when test="${orgDiv eq 'position' }" >
												<c:set var="orgName" value="직급" />
											</c:when>
										</c:choose>
										<h3>${orgName} 등록</h3>
										<hr/>
										<div class="row">
											<div class="col-lg-3"></div>
											<div class="col-lg-2 text-right">
												<label>상위 ${orgName}</label>
											</div>
											<div class="col-lg-3">
												<select class="form-control" id="upOrg" >
												<c:forEach var="vo" items="${orgList}">
													<c:if test="${orgDiv eq 'dept' }">
														<option value="${vo.deptNo }">${vo.deptNm} (${vo.deptNo})</option>
													</c:if>
													<c:if test="${orgDiv eq 'position' }">
														<option value="${vo.positionNo }">${vo.positionNm} (${vo.positionNo})</option>
													</c:if>
												</c:forEach>
												</select>
											</div>
										</div>
										<div class="row py-4">
											<div class="col-lg-3"></div>
											<div class="col-lg-2 text-right">
												<label>${orgName} 코드</label>
											</div>
											<div class="col-lg-3">
												<input type="text" class="form-control" id="orgNo" />
											</div>
										</div>
										<div class="row">
											<div class="col-lg-3"></div>
											<div class="col-lg-2 text-right">
												<label>${orgName} 명</label>
											</div>
											<div class="col-lg-3">
												<input type="text" class="form-control" id="orgNm" />
											</div>
										</div>
									<!-- </form> -->
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
	$(document).ready(function(){
		$("#setting").attr("class","nav-link");
		$("#setting").attr("aria-expanded","true");
		$("#adminSetting").attr("class","collapse show");
		$("#org").attr("class","collapse-item active");
		});

	function changeOrg(div){
		var frm = document.move_frm;
		frm.moveDiv.value = div;
		frm.submit();
	}

	$("#saveBtn").on("click",function(){
		console.log("saveBtn");
		var orgDiv = "${orgDiv}";
		
		if(null == $("#orgNo").val() || $("#orgNo").val() == "" ){
			alert("코드번호를 확인하세요.");
			return;
			}

		if(null == $("#orgNm").val() || $("#orgNm").val() == "" ){
			alert("명칭을 확인하세요.");
			return;
			}

		if(false == confirm("등록 하시겠습니까?")) return;

/* 		var frm = document.save_frm;
		frm.submit(); */

		$.ajax({
            type:"POST",
            url:"${hContext}/org/doInsert.do",
            dataType:"html",
            async: true,
            data:{
                "orgDiv" : orgDiv,
                "upOrg" : $("#upOrg").val(),
                "orgNo" : $("#orgNo").val(),
                "orgNm" : $("#orgNm").val()
                },
         success: function(data){
           var parseData = JSON.parse(data);
           alert(parseData.msgContents);
           changeOrg(orgDiv);
		},
         error:function(xhr,status,error){
             alert("error:"+error);
         }
        }); 

		});
	
	
	</script>
	
</body>
</html>