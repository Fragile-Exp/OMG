<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OMG</title>
</head>

<style>
	.card { width: 120%;}	
	.card-table-thead > th {border-bottom : 3px solid #444444; padding: 10px;}
	.card-table-tbody > td {border-bottom : 1px solid #444444; padding:10px;}
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
					  <h1 class="h3 mb-0 text-gray-800">문서 등록 목록</h1>
					  <div class="btn-box">
					  </div>
					</div>
					
				
					<!-- Content Row -->
					
					<div class="row">
					
					  <!-- Area Chart -->
					  <div class="col-xl-15 col-lg-10">
					    <div class="card shadow mb-4">
					      <!-- Card Header - Dropdown -->
					      <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between" ">
					        <h6 class="m-0 font-weight-bold text-primary">문서 등록 목록</h6>
					    		<div style=" float: right;">
					    			<a href="${hContext}/document/document_reg.do" class="btn btn-sm btn-primary shadow-sm" style="color:white; "><i class="fas fa-file-invoice fa-sm text-white-50"></i>등록 페이지</a>
					  				<a id="delete" onClick="deleteDcoument()" class="btn btn-sm btn-primary shadow-sm" style="color:white; "><i class="fas fa-trash fa-sm text-white-50"></i> 삭제</a>
					  			</div>
					  		</div>
					      
					      
					      
					      <!-- Card Body -->
					      <div class="card-body">
					        <div class="chart-area"  style=" height:100%; ">
					        	<table id="table"class="card-table" >
					        		<thead>
						        		<tr class="card-table-thead">
						        			<th width="15%" class="text-center" >문서 제목</th>	
						        			<th width="10%">문서 종류</th>
						        			<th width="10%">문서번호 </th>
						        			<th width="10%">처리기간</th>
						        			<th width="3%">상태</th>
						        			<th width="3%">선택</th>
						        		</tr>
					        		</thead>
					        		
					        		<tbody>
					        	
					        		
					        			<c:if test="${flag == 0 }">
					        				<tr id="tbody"class="card-table-tbody">
												<td colspan="6" style="text-align: center;">등록되어진 문서가 없습니다.</td>	
											</tr>
					        			</c:if>
					        		
					        			<c:if test="${flag != 0 }">
							        		<c:forEach var="i" begin="0" end="${IdSeleteSize-1}" step="1">
							        			<c:set var="VO" value="${IdSeleteList[i]}"  />
							        			<a id="tbodyA" herf="">
							        			<tr class="card-table-tbody">
													<td>${VO.title}</td>
													<c:choose>
							      						<c:when test="${VO.kind == 0}"><td value="0">휴가</td></c:when>
							      						<c:when test="${VO.kind == 1}"><td value="1">실험</td></c:when>
							      						<c:when test="${VO.kind == 2}"><td value="2">문서</td></c:when>
							      						<c:when test="${VO.kind == 3}"><td value="3">물풀구매</td></c:when>
							      						<c:when test="${VO.kind == 4}"><td value="4">프로젝트<v></c:when>
							      						<c:when test="${VO.kind == 5}"><td value="5">회의록</td></c:when>
							      						<c:when test="${VO.kind == 6}"><td value="6">행사</td></c:when>
							      						<c:when test="${VO.kind == 7}"><td value="7">인사</td></c:when>
							      						<c:when test="${VO.kind == 8}"><td value="8">총무</td></c:when>
							      						<c:when test="${VO.kind == 9}"><td value="9">사직</td></c:when>
							      					</c:choose>
													<td>${VO.documentId}</td>
													<td>${VO.dDay}</td>
													<c:set  var="set" value="${VO.documentSet }"/>
													<c:choose>
														<c:when test="${set == 1 }"><td>승인</td></c:when>	
														<c:when test="${set == 2 }"><td>반려</td></c:when>	
														<c:otherwise><td>대기</td></c:otherwise>	
													</c:choose>
													<td>
														<a href="#"  target="${VO.documentSet }" onClick="infoDocument(this)"  id="${VO.documentId}" class="info btn btn-sm btn-primary shadow-sm" >
															<i class="fas fa-arrow-right fa-sm text-white-50"></i>
															<input name="set" type="hidden" value="${VO.documentSet }">
														</a>
														
													   	<input name="check"  type="checkbox" value="${VO.documentId}">
													</td>
												</tr>
												</a>
								        		
													
								        	</c:forEach>
						        		</c:if>
						        	</tbody>	
								</table>
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
	//let param1 = $("#tag name").val(); 
	//var list = '<c:out value="${IdSeleteList}"/>' ;
	
	// button
	// delete : 삭제 , check : 체크박스
	
	var list = [];

	
	function deleteDcoument(){
		if($('input[name="check"]').is(":checked")==true){
			if(confirm("삭제 하시겠습니까?")==true){

				$("input:checkbox[name=check]:checked").each(function(){
					list.push($(this).val());
				});			
				

				for(var i=$("#table input:checkbox:checked").length-1; i>-1; i--){
					$.ajax({
						url:"${hContext}/document/doDelete.do",
						type:"GET",
						data:{"documentId" : list[i]},
						dataType:"json",
					success:function(data){
					 	alert("삭제가 성공 하였습니다.");
						$("input:checkbox:checked").closest("tr").remove();
					},
					error:function(err){
						alert("삭제가 실패 하였습니다.");
						
					}

					});					

				}

			}else{
				alert("삭제 취소 되었습니다.");
				
				}
			

		}
	}


	function infoDocument(e){ 

		var avalue = $(e).attr('target'); 
		
		if( avalue == 1){
			alert("승인 되어진 문서 입니다.");
			return;
		}else{
			
			window.location.href="${hContext}/document/document_info.do?documentId="+$(e).attr("id");
				
		}
		
	}



	
</script>

</html>