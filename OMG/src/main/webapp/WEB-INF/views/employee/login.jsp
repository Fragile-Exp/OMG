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

<body class="bg-gradient-primary" onkeydown="javascript:onEnterLogin();">


    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">OMG GroupWare</h1>
                                    </div>
                                    <form class="user">
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-user"
                                                id="employee_id" name="employee_id"
                                                placeholder="아이디(사원번호)">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control form-control-user"
                                                id="password" name="password" placeholder="비밀번호">
                                        </div>
                                        <div class="form-group">
                                            <div class="custom-control custom-checkbox small">
                                                <input type="checkbox" class="custom-control-input" id="customCheck">
                                                <label class="custom-control-label" for="customCheck">Remember
                                                    Me</label>
                                            </div> 
                                        </div>
                                        <a class="btn btn-primary btn-user btn-block" style="color:white;" id="loginBtn" name="loginBtn">
                                            로그인
                                        </a>
                                    </form>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" id="forgot_password" href="${hContext}/employee/forgot_password.do">비밀번호 찾기</a>
                                    </div>
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

    <!-- Core plugin JavaScript-->
    <script type="text/javascript">

    //엔터 로그인 
    function onEnterLogin(){
		var keyCode = window.event.keyCode;
		if (keyCode == 13) { //엔테키 이면
			loginAjax();
		}
	} //onEnterLogin()


	//버튼 로그인
    $("#loginBtn").on("click",function(){
		loginAjax();
        });


    function loginAjax(){
    	//alert("enter");
		if($("#employee_id").val()==false || $("#employee_id").val() ==""){
			alert("아이디(사원번호)를 확인하세요.");
			return ;
		}	
		//password 필수 체크
		if($("#password").val()==false || $("#password").val() ==""){
			alert("비밀번호를 확인하세요.");
			return ;
		}

		let employee_id=$("#employee_id").val().trim();
		let password=$("#password").val();
		//console.log("employeeId:"+employeeId);

		//console.log("#customCheck");
		//var employee_id=$("#employee_id").val().trim();

		//check되면 : 쿠키에 ID저장
        if($("#customCheck").is(":checked")){
           //console.log("check");
            
           if(employee_id !=null){
              setCookie("employee_id",employee_id,7);
           }

        }else{//그렇치 않으면 쿠키에 ID삭제
             deleteCookie("employee_id");
        }


      //ajax
        $.ajax({
           type:"POST",
           url:"${hContext}/employee/doLogin.do",
           dataType:"html",
           data:{
	           "employee_id":$("#employee_id").val(),
	           "password":$("#password").val()
          }, 
        success: function(data){
          var jData = JSON.parse(data);
          if(null != jData && jData.msgId=="1"){
            //console.log(jData.msgContents);
            //다시조회
            //doSelectList(1);
            window.location.href="${hContext}/view/main.do"
          }else{
            alert(jData.msgId+"|"+jData.msgContents);
            alert("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
          }
        },
        error:function(xhr,status,error){
            alert("error:"+error);
        }
       }); 
       //--ajax 
        
    	
        }

	$(document).ready(function(){    
	    var employeeId = getCookie("employee_id");
	     $("#employee_id").val(employeeId);
	
	     if($("#employee_id").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
	          $("#customCheck").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
	      }
	}); 
	
  	//id cookie에 저장
    //cookieValue:j124_146;expires=Mon, 05 Oct 2020 05:39:49 GMT
     function setCookie(cookie_name,value,expire_day){
        //cookie생성
        //Get and set the cookies associated with the current document.
        var expire_date = new Date();//현재 날짜
        //console.log("expire_date.getDate():"+expire_date.getDate());
        expire_date.setDate(expire_date.getDate() +expire_day);
        var cookieValue = this.escape(value) + ((expire_day==null)?"":";expires="+expire_date.toUTCString())
        //j124_146;expires=Mon, 05 Oct 2020 05:39:49 GMT
        //console.log("setCookie cookieValue:"+cookieValue);
       // console.log("setCookie"+cookie_name +"="+value);
        document.cookie = cookie_name +"="+cookieValue;
 
	     }
     


	    //cookie정보 가지고 오기
	     function getCookie(cookie_name){
	        cookie_name = cookie_name +"=";
	        //j124_146;expires=Mon, 05 Oct 2020 05:39:49 GMT
	        var cookie_data = document.cookie;
	        //console.log("cookie_data="+cookie_data);
	        var start = cookie_data.indexOf(cookie_name);
	        //console.log("start="+start);
	        var cookie_value = "";
	        if(start !=-1){
	           start += cookie_name.length;
	           var end = cookie_data.indexOf(";",start);
	           
	           if(end ==-1)end = cookie_data.length;
	           
	           cookie_value = cookie_data.substring(start,end);
	        }
	        
	        return unescape( cookie_value );
	     }
	    
	   function deleteCookie(cookie_name){
	      var expire_date = new Date();//현재 날짜
	      expire_date.setDate(expire_date.getDate()-1);//현재 날짜 -1(전날)
	      //console.log("expire_date.getDate():"+expire_date.getDate());
	      document.cookie  = cookie_name+"="+";expires="+expire_date.toUTCString();
	   }
    
    
    </script>
</body>

</html>
