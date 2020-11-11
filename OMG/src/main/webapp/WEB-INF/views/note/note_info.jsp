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
				<div class="container-fluid">

					<!-- 목록 -->
					<div class="row">
						<div class="col-lg-2">
							<div class="card shadow mb-4 py-3 border-left-primary">
								<div class="card-body">
									<form action="${hContext}/note/note.do" name="move_frm" method="get">
										<div class="list-group-flush">
											<input type="hidden" id="noteDiv" name="noteDiv" />
											<a href="#" onclick="javascript:moveNote(1); return false;" class="list-group-item"> 보낸 쪽지함 </a>
											<a href="#" onclick="javascript:moveNote(2); return false;" class="list-group-item"> 받은 쪽지함 </a>
											<a href="#" onclick="javascript:moveNote(3); return false;" class="list-group-item"> 휴지통 </a>
										</div>
									</form>
								</div>
							</div>
						</div>
						<div class="col-lg-10">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<a href="javascript:history.back();" class="btn btn-info btn-icon-split btn-sm">
										<span class="icon text-white-50"> <i class="fas fa-arrow-left"></i></span>
										<span class="text">뒤로가기</span>
									</a>
									<a href="#" class="btn btn-info btn-icon-split btn-sm">
										<span class="icon text-white-50"> <i class="fas fa-trash"></i></span>
										<span class="text">삭제</span>
									</a>
									<a href="${hContext}/note/note_reply.do" class="btn btn-info btn-icon-split btn-sm">
										<span class="icon text-white-50"> <i class="fas fa-arrow-right"></i></span>
										<span class="text">답장하기</span>
									</a>
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col-lg-9">
											<h6><span class="label label-default"> 제목 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : ${noteVO.title}</span></h6>
										</div>
										<div class="col-lg-3 text-right">
											<h6><span class="label label-default">보낸 시간 : ${noteVO.sendDt}</span></h6>
										</div>
									</div>
									<div>
										<h6><span class="label label-default">보낸 사람 : ${noteVO.senderId}</span></h6>
									</div>
									<div>
										<h6><span class="label label-default">받는 사람 : ${noteVO.receiveId }</span></h6>
									</div>
									<div>
										<h6><span class="label label-default"> 참조 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : ${noteVO.receiveRef }</span></h6>
									</div>
									
           							<hr class="sidebar-divider">
           							
           							<div class="py-4 px-10">
           							${noteVO.contents}
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
	
	function moveNote(div){
		var frm = document.move_frm;
		frm.noteDiv.value = div;
		frm.submit();
	}
	</script>
</body>
</html>