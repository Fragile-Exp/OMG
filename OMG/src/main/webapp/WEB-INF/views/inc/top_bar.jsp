<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Topbar -->
<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

  <!-- Sidebar Toggle (Topbar) -->
  <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
    <i class="fa fa-bars"></i>
  </button>

  <!-- Topbar Search -->
  

  <!-- Topbar Navbar -->
  <ul class="navbar-nav ml-auto">

    <!-- Nav Item - Alerts -->
    <li class="nav-item dropdown no-arrow mx-1">
      <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <i class="fas fa-bell fa-fw"></i>
        <!-- Counter - Alerts -->
        <span class="badge badge-danger badge-counter">3+</span>
      </a>
      <!-- Dropdown - Alerts -->
      <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">
        <h6 class="dropdown-header">
          Alerts Center
        </h6>
        <a class="dropdown-item d-flex align-items-center" href="#">
          <div class="mr-3">
            <div class="icon-circle bg-primary">
              <i class="fas fa-file-alt text-white"></i>
            </div>
          </div>
          <div>
            <div class="small text-gray-500">December 12, 2019</div>
            <span class="font-weight-bold">A new monthly report is ready to download!</span>
          </div>
        </a>
        <a class="dropdown-item d-flex align-items-center" href="#">
          <div class="mr-3">
            <div class="icon-circle bg-success">
              <i class="fas fa-donate text-white"></i>
            </div>
          </div>
          <div>
            <div class="small text-gray-500">December 7, 2019</div>
            $290.29 has been deposited into your account!
          </div>
        </a>
        <a class="dropdown-item d-flex align-items-center" href="#">
          <div class="mr-3">
            <div class="icon-circle bg-warning">
              <i class="fas fa-exclamation-triangle text-white"></i>
            </div>
          </div>
          <div>
            <div class="small text-gray-500">December 2, 2019</div>
            Spending Alert: We've noticed unusually high spending for your account.
          </div>
        </a>
        <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
      </div>
    </li>

    <!-- Nav Item - Messages -->
    <li class="nav-item dropdown no-arrow mx-1">
      <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <i class="fas fa-envelope fa-fw"></i>
        <!-- Counter - Messages -->
        <span id="msgCnt" class="badge badge-danger badge-counter"></span>
      </a>
      <!-- Dropdown - Messages -->
      <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="messagesDropdown">
        <h6 class="dropdown-header">
          Message Center
        </h6>
        <div id="messageListArea">
        <!-- message Area -->
        </div>
        <a class="dropdown-item text-center small text-gray-500" href="${hContext}/note/note.do">Read More Messages</a>
      </div>
    </li>

    <div class="topbar-divider d-none d-sm-block"></div>

    <!-- Nav Item - User Information -->
    <li class="nav-item dropdown no-arrow">
      <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <span class="mr-2 d-none d-lg-inline text-gray-600 large">${employee.employee_id }</span>
        <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60">
      </a>
      <!-- Dropdown - User Information -->
      <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
        <a class="dropdown-item" href="${hContext}/employee/mypage.do">
          <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
          마이페이지
        </a>
         <a class="dropdown-item" href="${hContext}/commuting/my_attendence.do">
          <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
          내 출결
        </a>
        <a class="dropdown-item" href="#">
          <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
          Settings
        </a>
        <a class="dropdown-item" href="#">
          <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
          Activity Log
        </a>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="${hContext}/employee/login.do" data-toggle="modal" data-target="#logoutModal">
          <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
          Logout
        </a>
      </div>
    </li>

  </ul>

</nav>
<!-- End of Topbar -->

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">로그아웃 하시겠습니까?</h5>
        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">×</span>
        </button>
      </div>
      <div class="modal-body">로그아웃하시려면 버튼을 눌러주세요.</div>
      <div class="modal-footer">
        <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
        <a class="btn btn-primary" href="login.do">로그아웃</a>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">

	$(document).ready(function(){
		$.ajax({
            type:"GET",
            url:"${hContext}/note/notReadNote.do",
            dataType:"html",
            async: true,
            data:{ "id" : "${sessionScope.employee.employee_id}"
                },
         success: function(data){
           var list = JSON.parse(data);
           if(list.length >0){
        	   $("#messageListArea").empty();
	           $("#msgCnt").text(list.length);
 	           var html = "";
	           for(var i=0;i<4;i++){
	               	html += '<form action="${hContext}/note/note_info.do" method="GET">';
	               	html += '<input type="hidden" name="noteNo" value="'+list[i].noteNo+'" />';
	               	html += '<input type="hidden" name="employeeId" value="'+list[i].employeeId+'" />';
	               	html += '<input type="hidden" name="senderId" value="'+list[i].senderId+'" />';
	               	html += '<input type="hidden" name="read" value="'+list[i].read+'" />';
	               	html += '<input type="hidden" name="noteDiv" value="'+list[i].noteDiv+'" />';
					html += '<a class="dropdown-item d-flex align-items-center" href="#" onclick="this.parentNode.submit(); return false">';
					html += '<div class="dropdown-list-image mr-3">';
					html += '<img class="rounded-circle" src="${hContext}/resources/images/basic.png" alt="">';
					html += '<div class="status-indicator bg-success"></div>';
					html += '</div>';
					html += '<div class="font-weight-bold">';
					html += '<div class="text-truncate">'+list[i].title+'</div>';
					html += '<div class="small text-gray-500">'+list[i].senderId+'</div>';
					html += '</div>';
					html += '</a>';
					html += '</form>';
	               }
	           
           } else{
                html = "";
        	    html += '<a class="dropdown-item d-flex align-items-center" href="#">';
				html += '<div class="font-weight-bold">';
				html += '<div class="text-truncate">새로운 메시지가 없습니다.</div>';
				html += '</div>';
				html += '</a>';

               }
           
           $("#messageListArea").empty();
           $("#messageListArea").append(html);
		},
         error:function(xhr,status,error){
             alert("error:"+error);
         }
        });
		})
		
</script>