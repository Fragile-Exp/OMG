<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="hContext" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 부트스트랩 -->
<link href="${hContext}/resources/css/sb-admin-2.min.css" rel="stylesheet">
<link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap core JavaScript-->
<script src="${hContext}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${hContext}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<title>사원/부서 찾기</title>
</head>
<body>
<div class="container">
	<div class="card shadow mb-4" >
		<div class="card-body">
			<div class="row">
				<div>
					<h3>사원 찾기</h3>
				</div>
				<div class="col-lg-11 col-md-11 col-sm-11">
					<input type="hidden" name="pageSize" id="pageSize" value="255"/>
					<input type="hidden" name="searchDiv" id="searchDiv" value="10" />
					<input type="text" class="form-control" name="searchWord" id="searchWord" size="20" placeholder="이름 검색" />
				</div>
				<input type="button" class="btn btn-sm btn-primary" onclick="" name="searchBtn" id="searchBtn" value="검색" />
			</div>
			<div class="py-1"></div>
			<div style="height: 200px; border:1px solid;">
				<div id="empList" class="list-group" >
					
				</div>
			</div>
			<hr />
		</div>
		<div class="px-4 py-2">
			<div>
				<h3>부서</h3>
			</div>
			<div id="mainBody" style="height: 300px">
	
			</div>
		</div>
	</div>
</div>
	<script type="text/javascript">
	$(document).ready(function(){
		drawDeptList();
		});

	function drawDeptList(){

		$.ajax({
            type:"GET",
            url:"${hContext}/org/doSelectListDept.do",
            dataType:"html",
            async: true, 
         success: function(data){
           var parseData = JSON.parse(data);
			//table에 있던 기존 데이터 삭제
			$("#mainBody").empty();
			var html = "";
			var maxLevel = 0;

			//Data가 없으면
           if(parseData.length>0){
               maxLevel = parseData[0].totalCnt;
               var currLevel = 0;
			  $.each(parseData, function(i, value) {
				  if(value.level <= (currLevel -1)){
					  for(var i=0;i<(currLevel-value.level);i++){
					  html += '</div>';
					  html += '</details>';
					  }
					  }
				  if( 0 == value.isNotLeaf){
					  html += '<details class="text-primary">';
					  html += '<summary>';
					  html += '<input type="checkbox" name="org" value="'+value.deptNo+'" /> '+value.deptNm+'';
					  html += '</summary>';
					  html += '<div class="bg-white px-4">';
					  } else{
						  html += '<input type="checkbox" name="org" value="'+value.deptNo+'" /> '+value.deptNm+'<br />';
					  	}
				 
				  	currLevel = value.level;

			  });
			  	for(var i=1; i<currLevel;i++){
					html += '</div>';
					html += '</details>';
					}  
		      }else{
		    	  html += "<span class='label label-default'>";
				  html += "부서 정보가 없습니다.";
				  html += "</span>";
			  }
		                  
			  //mainBody 동적으로 html추가
		      $("#mainBody").append(html);
		      
         },
         error:function(xhr,status,error){
             alert("error:"+error);
         }
        }); 
		
		}

	$("#searchBtn").on("click",function(){
    	pageTotal=$.ajax({
            type:"GET",
            url:"${hContext}/employee/doSelectList.do",
            dataType:"html",
            async: true,
            data:{
            "pageNum":1,
            "pageSize":$("#pageSize").val(),
            "searchDiv":$("#searchDiv").val(),
            "searchWord":$("#searchWord").val()
           }, 
         success: function(data){
           var parseData = JSON.parse(data);
       	  //table에 있던 기존 데이터 삭제
       	  $("#empList").empty();
           var html = "";
           if(parseData.length>0){
               <!-- 문자: 왼쪽, 숫자: 오른쪽, 같은면: 가운데 --> 
				$.each(parseData, function(i, value) {
					html += "<a href='#' class='list-group-item list-group-sm' style='border-bottom:1px;' value='"+value.employee_id+"'>"+value.name;
					html += "</a>";
				});
			}
			//table>tbody 동적으로 html추가
			$("#empList").append(html);
         },
         error:function(xhr,status,error){
             alert("error:"+error);
         }
        }); 
		//--ajax
        })

	</script>
</body>
</html>