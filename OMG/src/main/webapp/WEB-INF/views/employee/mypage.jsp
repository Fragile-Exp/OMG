<%@page import="com.omg.employee.domain.EmployeeVO"%>
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
	      		<form action="" name="writeFrm" enctype="multipart/form-data">
	      		<div class="container-fluid">
		      		<div align="center">
						<div class="card shadow mb-4" style="width:50%;">
							<div class="card-header py-3 text-left">
								<input type="button" class="btn btn-info btn-sm " value="취소" id="cancel">
								<input type="button" class="btn btn-info btn-sm " value="수정" id="doUpdate">
							</div>
							<div class="card-body">
								<input type="hidden"  name="img_code" id="img_code" value="${sessionScope.employee.img_code }"  />	
								<input type="hidden"  name="auth" id="auth" value="${sessionScope.employee.auth }" />	
								<div class="col-lg-10 text-center py-2" id="img_preview">
									<div><img src="${hContext}/${sessionScope.employee.img_name}" id="img_name" name="img_name" alt="프로필" width=100 height=100 /></div>
									
								</div>	
								<input type="file" onchange="file_upload(this)" id="file" name="file" accept="img/*" />
								<div class="row">
									<div class="col-lg-2 text-center">
										<label for="employee_id" >아이디(사원번호)</label>
									</div>
									<div class="col-lg-9">
										<input readOnly class="form-control" name="employee_id" id="employee_id" value="${sessionScope.employee.employee_id}"/>
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="name" >이름</label>
									</div>
									<div class="col-lg-9">
										<input readOnly type="text" class="form-control" name="name" id="name" value="${sessionScope.employee.name}"/>
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="name" >기존 비밀번호</label>
									</div>
									<div class="col-lg-9">
										<input readOnly type="password" class="form-control" name="password" id="password" value="${sessionScope.employee.password}"/>
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="password" >새 비밀번호</label>
									</div>
									<div class="col-lg-3">
										<input type="password" class="form-control"  name="newPassword" id="newPassword" placeholder="새 비밀번호" />
									</div>
									<div class="col-lg-2 text-center">
										<label for="password" >새 비밀번호 확인</label>
									</div>
									<div class="col-lg-4">
										<input type="password" class="form-control" name="newPasswordConfirm" id="newPasswordConfirm" placeholder="새 비밀번호 확인" />
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="dept_no" >부서명</label>
										<input type="hidden" id="dept_no" name="dept_no" value="${sessionScope.employee.dept_no}"/>
									</div>
									<div class="col-lg-9">
										<input readOnly type="text" class="form-control" name="dept_nm"  id="dept_nm" value="${sessionScope.employee.dept_nm}"/>
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="position_no" >직급</label>
										<input type="hidden" id="position_no" name="position_no" value="${sessionScope.employee.position_no}"/>
									</div>
									<div class="col-lg-9">
										<input readOnly type="text" class="form-control" name="position_nm"  id="position_nm" value="${sessionScope.employee.position_nm}"/>
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="cell_phone" >핸드폰</label>
									</div>
									<div class="col-lg-9">
										<input type="text" class="form-control" name="cell_phone" id="cell_phone" value="${sessionScope.employee.cell_phone}"/>
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="email" >이메일</label>
									</div>
									<div class="col-lg-9">
										<input type="text" class="form-control" name="email" id="email" value="${sessionScope.employee.email}"/>
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="address" >주소</label>
									</div>
									<div class="col-lg-9">
										<input type="text" class="form-control" name="address" id="address" value="${sessionScope.employee.address}"/>
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="hire_date" >입사일 EX)20/11/19</label>
									</div>
									<div class="col-lg-9">
										<input readOnly type="text" class="form-control" name="hire_date"  id="hire_date" value="${sessionScope.employee.hire_date}"/>
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="birth_day" >생년월일 EX)20/11/19</label>
									</div>
									<div class="col-lg-9">
										<input readOnly type="text" class="form-control" name="birth_day" id="birth_day" value="${sessionScope.employee.birth_day}"/>
									</div>
								</div>
								<div class="row py-2">
									<div class="col-lg-2 text-center">
										<label for="holiday" >휴가일</label>
									</div>
									<div class="col-lg-9">
										<input readOnly type="text" class="form-control" name="holiday" id="holiday" value="${sessionScope.employee.holiday}"/>
									</div>
								</div>
							</div>
						</div>	
					</div>		
		        </div>
		        </form>
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
	
	// 이미지 미리보기 
	function file_upload(e){
	    $('#img_preview').empty();
	    var files = e.files;
	    var fileArr = Array.prototype.slice.call(files);
	    fileArr.forEach(function(f){
	    	if(!f.type.match("image/.*")){
	        	alert("이미지 확장자만 업로드 가능합니다.");
	            return;
	        }
	        
	        var reader = new FileReader();
	        
	        reader.onload = function(e){
	        	var img = new Image();
	        	img.src = e.target.result;
	            $("#img_preview").append('<img src="'+e.target.result+'" width="100px" height="100px" style="margin: 5px"/>');
	            
	        }
	        reader.readAsDataURL(f);
	    }) // forEach
	} // img_upload
	
	$("#doUpdate").on("click",function(){
		var frm=document.writeFrm;
		var formData=new FormData(frm);
		//console.log("doUpdate");
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

		var updatePasswd;
		
		
		if($("#newPassword").val() != ""  ){//새로운 비밀번호를 입력함
			
			if($("#newPassword").val() == $("#newPasswordConfirm").val()){//새로운 비밀번호가 동일한 경우
				alert("비밀번호가 동일합니다.");
				updatePasswd=$("#newPassword").val();
				//console.log("updatePasswd:"+updatePasswd);
			}else{
				alert("비밀번호가 동일하지 않습니다.");
				return; //새로운 비밀번호가 동일하지 않은 경우
			}
			
		}else if($("#newPassword").val()==false || $("#newPassword").val() ==""){ //새로운 비밀번호를 입력하지 않음
			alert("새 비밀번호를 입력하지 않아 기존 비밀번호를 유지합니다.");
			updatePasswd=$("#password").val();
		}

		if(confirm("수정 하시겠습니까?") ==false)return;
		
		//ajax
        $.ajax({
           type:"POST",
           url:"${hContext}/employee/doUpdate.do",
           dataType:"html",
           enctype: 'multipart/form-data',
           contentType: false,
           processData: false,
           data:formData, 
        success: function(data){
          var jData = JSON.parse(data);
          if(null != jData && jData.msgId=="1"){
            alert(jData.msgContents);
            //다시조회
            //doSelectList(1);
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

	$("#cancel").on("click",function(){
		$("#newPassword").val("");
		$("#newPasswordConfirm").val("");
		});
	
	
	</script>
</body>
</html>