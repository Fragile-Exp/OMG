<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="hContext" value="${pageContext.request.contextPath }" ></c:set>   
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>OMG</title>

    <!-- Custom fonts for this template-->
    <link href="../resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../resources/css/sb-admin-2.min.css" rel="stylesheet">

</head>
<body id="page-top">
 <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-password-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-2">비밀번호를 잊으셨나요?</h1>
                                        <p class="mb-4">당신의 사원번호를 입력하시면<br>조회 후 메일로 비밀번호를 보내드리겠습니다.</p>
                                    </div>
                                    <form class="user">
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-user"
                                                id="employee_id" name="employee_id"
                                                placeholder="사원번호를 입력하세요">
                                        </div>
                                        <a id="mailSenderBtn" style="color:white;" class="btn btn-primary btn-user btn-block">
                                            메일 보내기
                                        </a>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>
    <!-- Bootstrap core JavaScript-->
    <script src="../resources/vendor/jquery/jquery.min.js"></script>
    <script src="../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<script type="text/javascript">
	$("#mailSenderBtn").one("click",function(){
		//alert("#mailSenderBtn");
		if($("#employee_id").val()==false || $("#employee_id").val() ==""){
			alert("아이디(사원번호)를 확인하세요.");
			return ;
		}	
		//ajax
        $.ajax({
           type:"GET",
           url:"${hContext}/employee/idConfirm.do",
           dataType:"html",
           data:{
	           "employee_id":$("#employee_id").val()
          }, 
        success: function(data){
          var jData = JSON.parse(data);
          if(null != jData && jData.msgId=="1"){
            //console.log(jData.msgContents);
            alert("존재하는 사원번호입니다.\n등록되어있는 메일로 비밀번호를 보내드렸습니다.")
            mailSender();
            //window.location.href="${hContext}/view/main.do"
          }else{
            alert("존재하지 않는 사원번호입니다.\n 사원번호를 확인하세요.");
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

	function mailSender(){
		//ajax
        $.ajax({
           type:"GET",
           url:"${hContext}/employee/sendMail.do",
           dataType:"html",
           data:{
	           "employee_id":$("#employee_id").val()
          }, 
        success: function(data){
            console.log("mail send success");
            window.location.href="${hContext}/employee/login.do"
        },
        complete:function(data){
         
        },
        error:function(xhr,status,error){
            alert("error:"+error);
        }
       }); 
       //--ajax 


		}

	

	
	
	</script>
</body>
</html>