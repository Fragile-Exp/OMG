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
	      		<div class="container-fluid">
		      		<div class="col-lg-10">
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
										<input type="text" class="form-control" id="password" placeholder="비밀번호" />
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="dept_no" >부서명</label>
									</div>
									<div class="col-lg-9">
										<select class="form-control input-sm" name="dept_no"  id="dept_no">
							    		  	<option value="11000">전략기획본부</option>
							    		  	<option value="12000">경영관리본부</option>
							    		  	<option value="13000">기술개발본부</option>
							    		  	<option value="14000">영업본부</option>
							    		</select>
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="position_no" >직급</label>
									</div>
									<div class="col-lg-9">
										<select class="form-control input-sm" name="position_no"  id="position_no">
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
		$("#Pages").attr("class","nav-link");
		$("#Pages").attr("aria-expanded","true");
		$("#collapsePages").attr("class","collapse show");
		$("#blank").attr("class","collapse-item active");
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
             // doSelectList(1);
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
		alert("#employeeAdd");

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
	           "img_code":1
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
	

	
	
	</script>
</body>
</html>