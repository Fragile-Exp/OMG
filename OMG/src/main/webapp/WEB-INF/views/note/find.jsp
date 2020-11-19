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
		<div class="card-body py-2">
		<input type="hidden" id="id" value="" />
		<input type="hidden" id="div" value="" />
		<input type="hidden" id="nm" value="" />
			<div class="row">
				<div class=" px-4 ">
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
			<div style="height: 200px; border:1px solid; overflow: auto;">
				<ul id="empList" class="list-group" >

				</ul>
			</div>
			<div class="py-2" align="right">
				<input type="button" class="btn btn-sm btn-primary" id="empRecBtn" value="받는 사람에 추가" />
				<input type="button" class="btn btn-sm btn-primary" id="empRefBtn" value="참조에 추가" />
			</div>
			<hr />
		
			<div class="px-4 py-2">
				<div>
					<h3>부서</h3>
				</div>
				<div id="mainBody" style="height: 400px">
		
				</div>
				<div align="right">
					<input type="button" class="btn btn-sm btn-primary" id="deptRecBtn" value="받는 사람에 추가" />
					<input type="button" class="btn btn-sm btn-primary" id="deptRefBtn" value="참조에 추가" />
				</div>
				<hr />
			</div>
		</div>
	</div>
</div>
	<script type="text/javascript">
	$(document).ready(function(){
		drawDeptList();
		});

	// 사용자 받는 사람 추가
	$("#empRecBtn").on("click",function(){
		opener.document.getElementById("receiveDiv").value = 1;
		opener.document.getElementById("receiveId").value = $("#id").val();
		opener.document.getElementById("receiveNm").value = $("#nm").val();
		window.close();
		
		});
	
	// 사용자 참조 추가
	$("#empRefBtn").on("click",function(){
		opener.document.getElementById("receiveRefDiv").value = 1;
		opener.document.getElementById("receiveRef").value = $("#id").val();
		opener.document.getElementById("receiveRefNm").value = $("#nm").val();
		window.close();
		
		});

	// 부서 받는 사람 추가
	$("#deptRecBtn").on("click",function(){
		var size = $("input:checkbox[name=dept]:checked").length;
		if(size > 1) {
			alert("하나만 선택해 주세요.");
			return;
		}
		opener.document.getElementById("receiveDiv").value = 2;
		opener.document.getElementById("receiveId").value = $("input:checkbox[name=dept]:checked").val();
		opener.document.getElementById("receiveNm").value = $("input:checkbox[name=dept]:checked").attr("id");
		window.close();
		
		});

	// 부서 참조 추가
	$("#deptRefBtn").on("click",function(){
		var size = $("input:checkbox[name=dept]:checked").length;
		if(size > 1) {
			alert("하나만 선택해 주세요.");
			return;
		}
		opener.document.getElementById("receiveRefDiv").value = 2;                                               
		opener.document.getElementById("receiveRef").value = $("input:checkbox[name=dept]:checked").val();     
		opener.document.getElementById("receiveRefNm").value = $("input:checkbox[name=dept]:checked").attr("id");
		window.close();
		
		});

	//검색 Enter Event처리
	$("#searchWord").keypress(function(event) {
		//alert("#searchWord"+key.keyCode)
		if(event.keyCode==13){
			$("#searchBtn").trigger("click");
		}
	});

	$(document).on("click","#empList>a",function(){
		console.log($(this).attr("value"));
		console.log($(this).text());
		$("#id").val($(this).attr("value"));
		$("#div").val("1");
		$("#nm").val($(this).text());
		})

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
					  html += '<input type="checkbox" name="dept" id="'+value.deptNm+'" value="'+value.deptNo+'" /> '+value.deptNm+'';
					  html += '</summary>';
					  html += '<div class="bg-white px-4">';
					  } else{
						  html += '<input type="checkbox" name="dept" id="'+value.deptNm+'" value="'+value.deptNo+'" /> '+value.deptNm+'<br />';
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