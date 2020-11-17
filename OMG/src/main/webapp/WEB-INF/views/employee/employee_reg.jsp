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
	      		<div class="container-fluid">
		      		<div class="col-lg-8">
						<div class="card shadow mb-4">
							<div class="card-header py-3">
								<input type="button" class="btn btn-info btn-sm" value="취소" id="cancel">
								<input type="button" class="btn btn-info btn-sm" value="사원 추가" id="employeeAdd">
							</div>
							<div class="card-body">
								<div class="row">
									<div class="col-lg-2 text-center">
										<label for="employee_id" >아이디(사원번호)</label>
									</div>
									<div class="col-lg-8">
										<input type="text" class="form-control" id="employee_id" placeholder="아이디(사원번호)" />
									</div>
									<button type="button" class="btn btn-info btn-sm" value="idConfirm" id="idConfirm">아이디 중복 확인</button>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="name" >이름</label>
									</div>
									<div class="col-lg-9">
										<input type="text" class="form-control" id="name" placeholder="이름" />
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="password" >비밀번호</label>
									</div>
									<div class="col-lg-9">
										<input type="password" class="form-control" id="password" placeholder="비밀번호" />
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="dept_no" >부서명</label>
									</div>
									<div class="col-lg-9">
										<select class="form-control input-sm" name="dept_no"  id="dept_no">
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
										<select class="form-control input-sm" name="position_no"  id="position_no">
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
										<input type="text" class="form-control" id="cell_phone" placeholder="핸드폰 EX)01012341234" />
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="email" >이메일</label>
									</div>
									<div class="col-lg-9">
										<input type="text" class="form-control" id="email" placeholder="이메일 EX)omg@omg.com" />
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="address" >주소</label>
									</div>
									<div class="col-lg-9">
										<input type="text" class="form-control" id="address" placeholder="주소" />
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="hire_date" >입사일 EX)20/11/19</label>
									</div>
									<div class="col-lg-9">
										<input type="text" class="form-control" id="hire_date" placeholder="입사일 EX)20/11/19" />
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="birth_day" >생년월일 EX)20/11/19</label>
									</div>
									<div class="col-lg-9">
										<input type="text" class="form-control" id="birth_day" placeholder="생년월일 EX)20/11/19" />
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="holiday" >휴가일</label>
									</div>
									<div class="col-lg-9">
										<input type="text" class="form-control" id="holiday" placeholder="휴가일" />
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
		$("#employee_reg").attr("class","collapse-item active");
		});

	//아이디 존재 여부 확인
	$("#idConfirm").one("click", function(){
		//alert("#idConfirm");
		if($("#employee_id").val()==false || $("#employee_id").val() ==""){
			alert("아이디를 확인하세요.");
			return ;
		}
		
		$.ajax({
            type:"GET",
            url:"${hContext}/employee/idConfirm.do",
            dataType:"html",
            data:{
            "employee_id":$("#employee_id").val().trim()
           }, 
         success: function(data){
           var jData = JSON.parse(data);
           if(null != jData && jData.msgId=="1"){
             alert(jData.msgContents);
             //다시조회
           	 //존재하는 아이디이므로 초기화
             $("#employee_id").val("");
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

	//사원 추가
	$("#employeeAdd").on("click", function(){
		//alert("#employeeAdd");

/* 		EMPLOYEE_ID
		PASSWORD
		NAME
		CELL_PHONE
		EMAIL
		ADDRESS
		HIRE_DATE
		BIRTH_DAY
		HOLIDAY
		IMG_CODE */


		//아이디(사원번호) 필수 체크
		if($("#employee_id").val()==false || $("#employee_id").val() ==""){
			alert("아이디(사원번호)를 확인하세요.");
			return ;
		}		
		//name 필수 체크
		if($("#name").val()==false || $("#name").val() ==""){
			alert("이름을 확인하세요.");
			return ;
		}
		//password 필수 체크
		if($("#password").val()==false || $("#password").val() ==""){
			alert("비밀번호를 확인하세요.");
			return ;
		}
		//cell_phone 필수 체크
		if($("#cell_phone").val()==false || $("#cell_phone").val() ==""){
			alert("핸드폰을 확인하세요.");
			return ;
		}
		//email 필수 체크
		if($("#email").val()==false || $("#email").val() ==""){
			alert("이메일을 확인하세요.");
			return ;
		}
		//address 필수 체크
		if($("#address").val()==false || $("#address").val() ==""){
			alert("주소를 확인하세요.");
			return ;
		}
		//hire_date 필수 체크
		if($("#hire_date").val()==false || $("#hire_date").val() ==""){
			alert("입사일을 확인하세요.");
			return ;
		}//birth_day 필수 체크
		if($("#birth_day").val()==false || $("#birth_day").val() ==""){
			alert("생년월일을 확인하세요.");
			return ;
		}//holiday 필수 체크
		if($("#holiday").val()==false || $("#holiday").val() ==""){
			alert("휴가일을 확인하세요.");
			return ;
		}

		if($("#auth").val()==false || $("#auth").val() ==""){
			alert("관리자 권한 확인하세요.");
			return ;
		}
		//ajax
        $.ajax({
           type:"POST",
           url:"${hContext}/employee/doInsert.do",
           dataType:"html",
           data:{
	           "employee_id":$("#employee_id").val(),
	           "password":$("#password").val(),
	           "name":$("#name").val(),
	           "dept_no":$("#dept_no").val(),
	           "position_no":$("#position_no").val(),
	           "cell_phone":$("#cell_phone").val(),
	           "email":$("#email").val(),
	           "address":$("#address").val(),
	           "hire_date":$("#hire_date").val(),
	           "birth_day":$("#birth_day").val(),
	           "holiday":$("#holiday").val(),
	           "img_code":1,
	           "auth":$("#auth").val()
          }, 
        success: function(data){
          var jData = JSON.parse(data);
          if(null != jData && jData.msgId=="1"){
            alert(jData.msgContents);
            window.location.reload();
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
	    $( "#hire_date" ).datepicker();
	    $( "#birth_day" ).datepicker();
	  } );

	
	
	</script>
</body>
</html>