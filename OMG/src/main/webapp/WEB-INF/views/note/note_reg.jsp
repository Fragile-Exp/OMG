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
										<span class="text">취소</span>
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
										<div class="col-lg-2 text-center">
											<label for="receive_id" >받는 사람</label>
										</div>
										<div class="col-lg-9">
											<input type="text" class="form-control" id="receive_id" placeholder="받는 사람" />
										</div>
										<input type="button" class="btn btn-info btn-sm" value="찾기" id="search">
									</div>
									<div class="row py-2">
										<div class="col-lg-2 text-center">
											<label for="receive_ref" >참조</label>
										</div>
										<div class="col-lg-9">
											<input type="text" class="form-control" id="receive_ref" placeholder="참조" />
										</div>
										<input type="button" class="btn btn-info btn-sm" value="찾기" id="search">
									</div>
									<div class="row">
										<div class="col-lg-2 text-center">
											<label for="receive_ref" >제목</label>
										</div>
										<div class="col-lg-10">
											<input type="text" class="form-control" id="receive_ref" placeholder="제목" />
										</div>
									</div>
									<div class="row py-2">
										<div class="col-lg-2"></div>
										<div class="col-lg-10">
											<textarea class="form-control" rows="20"></textarea>
										</div>
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