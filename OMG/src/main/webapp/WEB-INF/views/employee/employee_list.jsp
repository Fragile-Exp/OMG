<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="hContext" value="${pageContext.request.contextPath }" ></c:set>     
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>OMG</title>
</head>
<body id="page-top"   >
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
	      		
	      		<!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                   
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <form name="user_frm" class="form-inline  col-lg-12 col-md-12 text-right" onsubmit="return false" >
					    	    <input type="hidden" name="pageNum" id="pageNum" >  
					    		<div class="form-group px-1">
						    		<select class="form-control input-sm" name="pageSize"  id="pageSize">
							    		<c:forEach var="pageSzie" items="${pageSizeList}">
							    			<option value="${pageSzie.detCode}">${pageSzie.detNm}</option>
							    		</c:forEach>
						    		</select>
						    		<select class="form-control input-sm" name="searchDiv" id="searchDiv">
						    		  	<c:forEach var="empCondition" items="${empConditionList}">
							    			<option value="${empCondition.detCode}">${empCondition.detNm}</option>
							    		</c:forEach>
						    		</select>
					    		  <input  type="text" name="searchWord" id="searchWord"  class="form-control  input-sm"  placeholder="검색어"/>
					    		  <input id="searchBtn" type="button" class="btn btn-info btn-sm" id="searchBtn" name="searchBtn"  value="조회"  />
					    		</div>
					    	</form>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead >
                                        <tr>
                                            <th>이름</th>
                                            <th>부서</th>
                                            <th>직급</th>
                                            <th>핸드폰</th>
                                            <th>이메일</th>
                                            <th>주소</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        
					<!-- pagenation -->
					<div id="page-selection" class="pagination d-flex justify-content-center"></div>
			    	<!--// pagenation -->
                    </div>

                </div>
                <!-- /.container-fluid -->
               
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
	
	$(document).ready(function() {
		//alert("javascript");
        //화면이 로딩 되면서 데이터 조회
    	doSelectList(1);
    });

	//검색 Enter Event처리
	$("#searchWord").keypress(function(event) {
		//alert("#searchWord"+key.keyCode)
		if(event.keyCode==13){
			$("#searchBtn").trigger("click");
		}
	});
    


	
	$("#searchBtn").on("click",function(){
		//alert("조회 버튼");
		doSelectList(1);
		});

	
    function doSelectList(page){
    	var pageTotal = 1;
    	pageTotal=$.ajax({
            type:"GET",
            url:"${hContext}/employee/doSelectList.do",
            dataType:"html",
            async: true,
            data:{
            "pageNum":page,
            "pageSize":$("#pageSize").val(),
            "searchDiv":$("#searchDiv").val(),
            "searchWord":$("#searchWord").val()
           }, 
         success: function(data){
           var parseData = JSON.parse(data);
       	  //table에 있던 기존 데이터 삭제
       	  $("#dataTable>tbody").empty();
           var html = "";

           //Data가 없으면   
           
       	  var totalCount = 0;
           if(parseData.length>0){
               totalCount = parseData[0].totalCnt;//33/10->3,3
               pageTotal  = (totalCount/$("#pageSize").val());
               pageTotal  = Math.ceil(pageTotal); //3.3 -> 4
               <!-- 문자: 왼쪽, 숫자: 오른쪽, 같은면: 가운데 --> 
				  $.each(parseData, function(i, value) {
						
					  html += "<tr>";
					  html += "<td>"+value.name+"</td>";
					  html += "<td>"+value.dept_nm+"</td>";
					  html += "<td>"+value.position_nm+"</td>";
					  html += "<td>"+value.cell_phone+"</td>";
					  html += "<td>"+value.email+"</td>";
					  html += "<td>"+value.address+"</td>";
					  html += "</tr>";

				  });
					  
		      }else{
		    	  html += "<tr>";
				  html += "<td class='text-center' colspan='99'>등록된 사원이 없습니다.</td>";
				  html += "</tr>";
			  }
		                  
			  //table>tbody 동적으로 html추가
		      $("#dataTable>tbody").append(html);	
		      //페이징
			  renderingPage(pageTotal,page);  
           	  //등록부분 초기화
		      //doInit();
		      
         },
         complete:function(data){
          
         },
         error:function(xhr,status,error){
             alert("error:"+error);
         }
        }); 
		//--ajax
        }
    
    //pagign 처리
    function renderingPage(pageTotal,page){
		//이전에 연결된 이벤트 핸들러를 요소에서 제거합니다.
		$('#page-selection').unbind('page') ;
		
        $('#page-selection').bootpag({
            total: pageTotal,     <!--totalPage = totalCnt/pageSize(10)  -->
            page: page,                <!--현재 페이지  -->
            maxVisible: 10,         <!-- bottom page --> 
            leaps: true,
            firstLastUse: true,
            first: '←',
            last: '→',
            wrapClass: 'pagination',
            activeClass: 'active',
            disabledClass: 'disabled',
            nextClass: 'next',
            prevClass: 'prev',
            lastClass: 'last',
            firstClass: 'first'
        }).on("page", function(event, num){
        	doSelectList(num);
        }); 
	}
	
	
	</script>
</body>
</html>