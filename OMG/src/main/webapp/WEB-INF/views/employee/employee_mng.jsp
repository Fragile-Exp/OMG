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
	      		
	      		
	      		<form action=""></form>
	      		
	      		<!-- Begin Page Content -->
                <div class="container-fluid">
                    <div class="card shadow mb-4">
                        <div class="card-header py-2">
                            <form name="user_frm"  class="form-inline  col-lg-12 col-md-12 text-right" onsubmit="return false">
				    	    	<input type="hidden" name="pageNum" id="pageNum" >  
				    			<div class="form-group">
					    		  	<div class="px-1">
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
						    		</div>
					    		  	<input  type="text" name="searchWord" id="searchWord"  class="form-control  input-sm"  placeholder="검색어"/>
					    		  	<input id="searchBtn" type="button" class="btn btn-info btn-sm" onclick="javascript:doSelectList(1);"  value="조회"  />
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
						<div id="page-selection" class="pagination d-flex justify-content-center"></div>
				    	<!--// pagenation -->
					    
					    <!-- 입력 버튼 -->
                        <div class="col-lg-11 text-right py-4">
                        	
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
					    		  	<c:if test="${deptList.size() >0}">
												<c:forEach var="dept" items="${deptList}" >
													<option value="${dept.deptNo }">${dept.deptNm}</option>
												</c:forEach>
											</c:if>
					    		</select>
							</div>
						</div>
						<div class="row py-2">
							<div class="col-lg-2 text-center">
								<label for="position_no" >직급</label>
							</div>
							<div class="col-lg-9">
								<select class="form-control input-sm" name="position_no_edit"  id="position_no_edit">
									<c:if test="${positionList.size() >0}">
										<c:forEach var="position" items="${positionList}" >
											<option value="${position.positionNo }">${position.positionNm}</option>
										</c:forEach>
									</c:if>
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
						<div class="row py-2">
							<div class="col-lg-2 text-center">
								<label for="auth" >권한</label>
							</div>
							<div class="col-lg-9">
								<select class="form-control input-sm" name="auth"  id="auth">
									<option value="1">일반</option>
									<option value="9">관리자</option>
					    		</select>
							</div>
						</div>
						<input type="hidden" id="img_code" name="img_code" />
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
	$(document).ready(function(){
		$("#setting").attr("class","nav-link");
		$("#setting").attr("aria-expanded","true");
		$("#adminSetting").attr("class","collapse show");
		$("#employee_mng").attr("class","collapse-item active");
		doSelectList(1);
		});

	//검색 Enter Event처리
	$("#searchWord").keypress(function(event) {
		//alert("#searchWord"+key.keyCode)
		if(event.keyCode==13){
			$("#searchBtn").trigger("click");
		}
	});

	//수정
	$("#doUpdate").on("click",function(){
		//alert("doUpdate");
		//비밀번호필수 체크 #다음값 조심하기!_edit 붙이기
		
		if($("#employee_id_edit").val()==false || $("#employee_id_edit").val() ==""){
			alert("아이디(사원번호)를 확인하세요.");
			return ;
		}
		
		//비밀번호필수 체크
		if($("#passwd").val()==false || $("#passwd").val() ==""){
			alert("비밀번호를 확인하세요.");
			return ;
		}

		//dept_no_edit 체크
		if($("#dept_no_edit").val()==false || $("#dept_no_edit").val() ==""){
			alert("부서를 확인하세요.");
			return ;
		}

		//position_no_edit 체크
		if($("#position_no_edit").val()==false || $("#position_no_edit").val() ==""){
			alert("직급을 확인하세요.");
			return ;
		}
		
		//cell_phone_edit 체크
		if($("#cell_phone_edit").val()==false || $("#cell_phone_edit").val() ==""){
			alert("휴대전화 번호를 확인하세요.");
			return ;
		}
		
		//email_edit 체크
		if($("#email_edit").val()==false || $("#email_edit").val() ==""){
			alert("이메일을 확인하세요.");
			return ;
		}

		//address_edit 체크
		if($("#address_edit").val()==false || $("#address_edit").val() ==""){
			alert("주소를 확인하세요.");
			return ;
		}

		//holiday_edit 체크
		if($("#holiday_edit").val()==false || $("#holiday_edit").val() ==""){
			alert("휴가일을 확인하세요.");
			return ;
		}

		//holiday_edit 체크
		if($("#auth").val()==false || $("#auth").val() ==""){
			alert("관리자 권한을 확인하세요.");
			return ;
		}
		if(confirm("수정 하시겠습니까?") ==false)return;

		$("#searchWord").val("");
		//ajax
        $.ajax({
           type:"POST",
           url:"${hContext}/employee/doUpdateMng.do",
           dataType:"html",
           data:{
	           "employee_id":$("#employee_id_edit").val(),
	           "password":$("#password_edit").val(),
	           "name":$("#name_edit").val(),
	           "dept_no":$("#dept_no_edit").val(),
	           "position_no":$("#position_no_edit").val(),
	           "cell_phone":$("#cell_phone_edit").val(),
	           "email":$("#email_edit").val(),
	           "address":$("#address_edit").val(),
	           "hire_date":$("#hire_date_edit").val(),
	           "birth_day":$("#birth_day_edit").val(),
	           "holiday":$("#holiday_edit").val(),
	           "img_code":$("#img_code").val(),
	           "auth":$("#auth").val()
          }, 
        success: function(data){
          var jData = JSON.parse(data);
          if(null != jData && jData.msgId=="1"){
            alert(jData.msgContents);
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
           "employee_id":$("#employee_id_edit").val()
          }, 
        success: function(data){
          var jData = JSON.parse(data);
          if(null != jData && jData.msgId=="1"){
            alert(jData.msgContents);
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
        	$("#auth").val(parseData.auth);
        	$("#img_code").val(parseData.img_code);
          
           //아이디 enable
           $("#employee_id_edit").prop("disabled", true);
           $("#name_edit").prop("disabled", true);
           $("#hire_date_edit").prop("disabled", true);
           $("#birth_day_edit").prop("disabled", true);
			  //버튼제어
           $("#doDelete").prop("disabled", false);
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

	//등록부분 초기화
	function doInit(){
		$("#employee_id_edit").val("");
		$("#name_edit").val("");
		$("#password_edit").val("");
		$("#dept_no_edit").val("");
		$("#position_no_edit").val("");
		$("#cell_phone_edit").val("");
		$("#email_edit").val("");
		$("#address_edit").val("");
		$("#hire_date_edit").val("");
		$("#birth_day_edit").val("");
		$("#holiday_edit").val("");
		$("#img_code").val("");
		$("#auth").val("");
		$("#doDelete").prop("disabled", false);
        $("#doUpdate").prop("disabled", false);
		
	}
	
	
	
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
		      doInit();
		      
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

	$.datepicker.setDefaults({
        dateFormat: 'yy/mm/dd',	//날짜 포맷이다. 보통 yy-mm-dd 를 많이 사용하는것 같다.
        prevText: '이전 달',	// 마우스 오버시 이전달 텍스트
        nextText: '다음 달',	// 마우스 오버시 다음달 텍스트
        closeText: '닫기', // 닫기 버튼 텍스트 변경
        currentText: '오늘', // 오늘 텍스트 변경
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],	//한글 캘린더중 월 표시를 위한 부분
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],	//한글 캘린더 중 월 표시를 위한 부분
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],	//한글 캘린더 요일 표시 부분
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],	//한글 요일 표시 부분
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],	// 한글 요일 표시 부분
        showMonthAfterYear: true,	// true : 년 월  false : 월 년 순으로 보여줌
        yearSuffix: '년',	//
        showButtonPanel: true,	// 오늘로 가는 버튼과 달력 닫기 버튼 보기 옵션
        showMonthAfterYear : true,
		changeMonth : true,
		changeYear : true,
		yearRange: "-100:+0",
//        buttonImageOnly: true,	// input 옆에 조그만한 아이콘으로 캘린더 선택가능하게 하기
//        buttonImage: "images/calendar.gif",	// 조그만한 아이콘 이미지
//        buttonText: "Select date"	// 조그만한 아이콘 툴팁
    });

	$( function() {
	    $( "#hire_date_edit" ).datepicker();
	    $( "#birth_day_edit" ).datepicker();
	  } );

	
	
	
	</script>
</body>
</html>