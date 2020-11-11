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
	      		
	      		
	      		<form action=""></form>
	      		
	      		<!-- Begin Page Content -->
                <div class="container-fluid">
                    <div class="card shadow mb-4">
                        <div class="card-header py-2">
                            <form action="" method="get" name="user_frm"  class="form-inline  col-lg-12 col-md-12 text-right">
				    	    	<input type="hidden" name="pageNum" id="pageNum" >  
				    			<div class="form-group">
					    		  	<select class="form-control input-sm" name="pageSize"  id="pageSize">
						    		  	<option value="10">10</option>
						    		  	<option value="20">20</option>
						    		  	<option value="30">30</option>
						    		  	<option value="50">50</option>
						    		  	<option value="100">100</option>
					    		 	</select>	    		
					    		  	<select class="form-control input-sm" name="searchDiv" id="searchDiv">
						    		  	<option value="10">이름</option>
						    		  	<option value="20">부서</option>
					    		  	</select>  
					    		  	<input  type="text" name="searchWord" id="searchWord"  class="form-control  input-sm"  placeholder="검색어"/>
					    		  	<input type="button" class="btn btn-info btn-sm" onclick="javascript:doSelectList(1);"  value="조회"  />
				    			</div>
				    		</form>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="" method="get" name="employeeFrm"  >
	                                <table class="table table-bordered" id="employeeTable" width="100%"  cellspacing="0">
	                                    <thead >
	                                        <tr>
	                                        	<th>사원번호</th>
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
                                </form>
                            </div>
                        </div>
                        <!-- pagenation -->
						<div  class="text-center">
							<div id="page-selection" class="text-center page"></div>
						</div>
					    <!--// pagenation -->
					    
					    <!-- 입력 버튼 -->
                        <div class="card-body">
                        	
			    		  	<input type="button" class="btn btn-info btn-sm" id="doUpdate" value="수정"  />
			    		  	<input type="button" class="btn btn-info btn-sm" id="doDelete" value="삭제"  />
				    	</div>
				        <!-- 입력 버튼 -->
		    			<div class="row">
							<div class="col-lg-2 text-center">
								<label for="employee_id" >아이디(사원번호)</label>
							</div>
							<div class="col-lg-9">
								<input type="text" class="form-control" id="employee_id_edit" placeholder="아이디(사원번호)" />
							</div>
						</div>
						<div class="row py-2">
							<div class="col-lg-2 text-center">
								<label for="name" >이름</label>
							</div>
							<div class="col-lg-9">
								<input type="text" class="form-control" id="name_edit" placeholder="이름" />
							</div>
						</div>
						<div class="row py-2">
							<div class="col-lg-2 text-center">
								<label for="password" >비밀번호</label>
							</div>
							<div class="col-lg-9">
								<input type="text" class="form-control" id="password_edit" placeholder="비밀번호" />
							</div>
						</div>
						<div class="row py-2">
							<div class="col-lg-2 text-center">
								<label for="dept_no" >부서명</label>
							</div>
							<div class="col-lg-9">
								<select class="form-control input-sm" name="dept_no_edit"  id="dept_no_edit">
					    		  	<option value="10000">OMG</option>
					    		  	<option value="11000">전략기획본부</option>
					    		  	<option value="12000">경영관리본부</option>
					    		  	<option value="13000">기술개발본부</option>
					    		  	<option value="13100">연구소</option>
					    		  	<option value="13110">제1연구소</option>
					    		  	<option value="13120">제2연구소</option>
					    		  	<option value="13200">기술부문</option>
					    		  	<option value="13210">기술 1팀</option>
					    		  	<option value="13210">기술 1팀</option>
					    		  	<option value="13220">기술 2팀</option>
					    		  	<option value="14000">영업본부</option>
					    		  	<option value="14100">아시아영업부</option>
					    		</select>
							</div>
						</div>
						<div class="row py-2">
							<div class="col-lg-2 text-center">
								<label for="position_no" >직급</label>
							</div>
							<div class="col-lg-9">
								<select class="form-control input-sm" name="position_no_edit"  id="position_no_edit">
									<option value="">선택하세요</option>
									<option value="10000">사장</option>
									<option value="11000">부사장</option>
									<option value="11100">전무이사</option>
									<option value="11200">상무이사</option>
									<option value="11300">이사</option>
					    		  	<option value="11310">수석</option>
					    		  	<option value="11320">책임</option>
					    		  	<option value="11330">선임</option>
					    		  	<option value="11331">사원</option>
					    		  	<option value="11332">인턴</option>
					    		</select>
							</div>
						</div>
						<div class="row py-2">
							<div class="col-lg-2 text-center">
								<label for="cell_phone" >핸드폰</label>
							</div>
							<div class="col-lg-9">
								<input type="text" class="form-control" id="cell_phone_edit" placeholder="핸드폰 EX)01012341234" />
							</div>
						</div>
						<div class="row py-2">
							<div class="col-lg-2 text-center">
								<label for="email" >이메일</label>
							</div>
							<div class="col-lg-9">
								<input type="text" class="form-control" id="email_edit" placeholder="이메일 EX)omg@omg.com" />
							</div>
						</div>
						<div class="row py-2">
							<div class="col-lg-2 text-center">
								<label for="address" >주소</label>
							</div>
							<div class="col-lg-9">
								<input type="text" class="form-control" id="address_edit" placeholder="주소" />
							</div>
						</div>
						<div class="row py-2">
							<div class="col-lg-2 text-center">
								<label for="hire_date" >입사일 EX)20/11/19</label>
							</div>
							<div class="col-lg-9">
								<input type="text" class="form-control" id="hire_date_edit" placeholder="입사일 EX)20/11/19" />
							</div>
						</div>
						<div class="row py-2">
							<div class="col-lg-2 text-center">
								<label for="birth_day" >생년월일 EX)20/11/19</label>
							</div>
							<div class="col-lg-9">
								<input type="text" class="form-control" id="birth_day_edit" placeholder="생년월일 EX)20/11/19" />
							</div>
						</div>
						<div class="row py-2">
							<div class="col-lg-2 text-center">
								<label for="holiday" >휴가일</label>
							</div>
							<div class="col-lg-9">
								<input type="text" class="form-control" id="holiday_edit" placeholder="휴가일" />
							</div>
						</div>
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
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="${hContext}/resources/js/bootstrap.min.js"></script>
    <!-- jquery bootpag -->
    <script src="${hContext}/resources/js/jquery.bootpag.min.js"></script>
    
    <script type="text/javascript">
	$(document).ready(function(){
		$("#Pages").attr("class","nav-link");
		$("#Pages").attr("aria-expanded","true");
		$("#collapsePages").attr("class","collapse show");
		$("#blank").attr("class","collapse-item active");
		doSelectList(1);
		});

	//수정
	$("#doUpdate").on("click",function(){
		//alert("doUpdate");
		//비밀번호필수 체크 #다음값 조심하기!_edit 붙이기
		
		
		
		});
	
	//삭제
	//employeeDelete
	$("#doDelete").on("click",function(){
		//alert("doDelete");
		if($("#employee_id_edit").val()==false || $("#employee_id_edit").val() ==""){
			alert("아이디(사원번호)를 확인하세요.");
			return ;
		}
		if(confirm("삭제 하시겠습니까?") ==false)return;

		//ajax
        $.ajax({
           type:"GET",
           url:"${hContext}/employee/doDelete.do",
           dataType:"html",
           data:{
           "employee_id":$("#employee_id_edit").val().trim()
          }, 
        success: function(data){
          var jData = JSON.parse(data);
          if(null != jData && jData.msgId=="1"){
            //alert(jData.msgContents);
            //다시조회
            doSelectList(1);
          }else{
            alert(jData.msgId+"|"+jData.msgContents);
          }
        },
        complete:function(data){
         
        },
        error:function(xhr,status,error){
            alert("error:"+error);
        }
       }); 
       //--ajax 
		
		});	

	//사원 grid 선택
	$("#employeeTable>tbody").on("click","tr",function(e){
		var tds=$(this).children();
		var employeeId=tds.eq(0).text();
		console.log("선택한 열의 아이디:"+employeeId);


		$.ajax({
            type:"GET",
            url:"${hContext}/employee/doSelectOne.do",
            dataType:"html",
            data:{
            "employee_id":employeeId
           }, 
         success: function(data){
	        console.log("data:"+data);    
	          
			var parseData = JSON.parse(data);
			console.log("parseData:"+parseData.length);
          	//data:{"employee_id":"success","password":"ddd","name":"ddd","dept_no":11000,"position_no":10000,"cell_phone":10,"email":"casd","address":"123","hire_date":"20/11/19 ","birth_day":"20/11/19","holiday":1,"img_code":"1","num":0,"totalCnt":0}
          	
           	$("#employee_id_edit").val(parseData.employee_id);
           	$("#name_edit").val(parseData.name);
           	$("#password_edit").val(parseData.password);
           	$("#dept_no_edit").val(parseData.dept_no);
           	$("#position_no_edit").val(parseData.position_no);
           	$("#cell_phone_edit").val(parseData.cell_phone);
           	$("#email_edit").val(parseData.email);
           	$("#address_edit").val(parseData.address);
           	$("#hire_date_edit").val(parseData.hire_date);
        	$("#birth_day_edit").val(parseData.birth_day);
        	$("#holiday_edit").val(parseData.holiday);
          
           //아이디 enable
           $("#employee_id_edit").prop("disabled", true);
           $("#name_edit").prop("disabled", true);
           $("#hire_date_edit").prop("disabled", true);
           $("#birth_day_edit").prop("disabled", true);
			  //버튼제어
           //$("#doDelete").prop("disabled", false);
           $("#doUpdate").prop("disabled", false);
           
         },
         complete:function(data){
          
         },
         error:function(xhr,status,error){
             alert("error:"+error);
         }
        }); 
//--ajax  	
		
		});

	//사원목록 조회
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
       	  $("#employeeTable>tbody").empty();
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
					  html += "<td>"+value.employee_id+"</td>";
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
		      $("#employeeTable>tbody").append(html);	
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