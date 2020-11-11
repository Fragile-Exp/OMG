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
									<div class="list-group-flush">
										<a id="note1" href="#" onclick="javascript:drawNote(1); return false;" class="list-group-item"> 보낸 쪽지함 </a>
										<a id="note2" href="#" onclick="javascript:drawNote(2); return false;" class="list-group-item"> 받은 쪽지함 </a>
										<a id="note3" href="#" onclick="javascript:drawNote(3); return false;" class="list-group-item"> 휴지통 </a>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-10">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<div class="row">
										<div class="col-lg-8">
										<a href="${hContext}/note/note_reg.do" class="btn btn-secondary btn-sm">
											<span class="text">쪽지쓰기</span>
										</a>
										<a href="#" class="btn btn-info btn-icon-split btn-sm">
											<span class="icon text-white-50"> <i class="fas fa-check"></i></span>
											<span class="text">읽음 처리</span>
										</a>
										<a id="deleteBtn" href="#" class="btn btn-info btn-icon-split btn-sm">
											<span class="icon text-white-50"> <i class="fas fa-trash"></i></span>
											<span class="text">삭제</span>
										</a>
										<a href="${hContext}/note/note_reply.do" class="btn btn-info btn-icon-split btn-sm">
											<span class="icon text-white-50"> <i class="fas fa-arrow-right"></i></span>
											<span class="text">답장하기</span>
										</a>
										</div>
										<div class="col-lg-4" align="right">
											<form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
											    <div class="input-group">
											      <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
											      <div class="input-group-append">
											        <button class="btn btn-primary" type="button">
											          <i class="fas fa-search fa-sm"></i>
											        </button>
											      </div>
											    </div>
											</form>
										</div>
										
									</div>
								</div>
								<div class="card-body">
									<form action="${hContext}/note/note_info.do" name="noteInfo_frm">
										<input type="hidden" id="employeeId" name="employeeId" />
										<input type="hidden" id="noteDiv" name="noteDiv" />
										<input type="hidden" id="noteNo" name="noteNo" />
										<input type="hidden" id="senderId" name="senderId" />
										<input type="hidden" id="read" name="read" />
									</form>
									<div class="table-responsive">
										<table id="noteList" class="table table-striped table-bordered table-hover table-condensed">
											<thead>
												<tr>
													<th class="text-center" width="2%"><input type="checkbox" class="form-controll" id="selectAll" /></th>
													<th class="text-center" width="12%">보낸 사람</th>
													<th class="text-center" width="12%">받는 사람</th>
													<th class="text-center" width="36%">제목</th>
													<th class="text-center" width="10%">읽음</th>
													<th class="text-center" width="15%">보낸 시간</th>
													<th class="text-center" width="15%">읽은 시간</th>
													<th style="display:none;">쪽지번호</th>
													<th style="display:none;">사원번호</th>
												</tr>
											</thead>
											<tbody>

										    </tbody>
										</table>
										<div class="text-center">
											<div id="page-selection" class="text-center page"></div>
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
	$(document).ready(function(){
		drawNote("${noteDiv}");
		})
		
	$("#deleteBtn").on("click",function(){
		var size = $("input:checkbox[name=selectNote]:checked").length;
		if(size < 1) {
			alert("하나이상 선택해 주세요.");
			return;
		}
		var noteNolist = [];
		var employeelist = [];
		$("input:checkbox[name=selectNote]:checked").each(function(i){
			var args = [];
			args = $(this).val().split(',');
			noteNolist.push(args[0]);
			employeelist.push(args[1]);
			});

		if(false == confirm("쪽지를 삭제하시겠습니까?")) return;
		
		jQuery.ajaxSettings.traditional = true;
		$.ajax({
            type:"GET",
            url:"${hContext}/note/doDelete.do",
            dataType:"html",
            async: true,
            data:{
                "noteDiv" : $("#noteDiv").val(),
                "noteNo" : noteNolist,
                "employee" : employeelist
                },
         success: function(data){
           var parseData = JSON.parse(data);
           alert(parseData.msgContents);
           drawNote($("#noteDiv").val());
		},
         error:function(xhr,status,error){
             alert("error:"+error);
         }
        }); 
	})
		
	
	$("#noteList>tbody").on("click","tr",function(){
		var tds = $(this).children();
		var noteNo = tds.eq(7).text();
		var employeeId = tds.eq(8).text();
		var senderId = tds.eq(1).text();
		var read = tds.eq(9).text();
	
		var frm = document.noteInfo_frm;
		frm.noteNo.value = noteNo;
		frm.employeeId.value = employeeId;
		frm.senderId.value = senderId;
		frm.read.value = read;
		frm.noteDiv.value = $("#noteDiv").val();
		frm.submit();
		//window.location.href="${hContext}/note/note_info.do"
		})
			
	function drawNote(noteDiv){
		$("#noteDiv").val(noteDiv);
		var activeNote = "note"+noteDiv;
		$(".active").removeClass("active");
		document.getElementById(activeNote).classList.add("active");
		
		$.ajax({
            type:"GET",
            url:"${hContext}/org/doSelectList.do",
            dataType:"html",
            async: true, 
            data : {
                "noteDiv" : noteDiv
                },
         success: function(data){
           var parseData = JSON.parse(data);
			//table에 있던 기존 데이터 삭제
			$("#noteList>tbody").empty();
			var html = "";
			var read = "";
			//Data가 없으면
           if(parseData.length>0){
               var totalCnt = parseData[0].totalCnt;
        	   $.each(parseData, function(i, value) {
            	    html += '<tr>';
    		   		html += '<td class="text-center" onclick="event.cancelBubble=true">';
    		   		html += '<input type="checkbox" class="form-controll" name="selectNote" value="'+value.noteNo+','+value.employeeId+'" />';
    		   		html += '</td>';
					html += '<td class="text-center">'+value.senderId+'</td>';
					html += '<td class="text-center">'+value.receiveId+'</td>';
					html += '<td class="text-left">'+value.title+'</td>';
					if(value.read==1){
						read="읽음";
					} else {
						read="읽지 않음";	
					}
					html += '<td class="text-center">'+read+'</td>';
					html += '<td class="text-center">'+value.sendDt+'</td>';
					html += '<td class="text-center">'+value.readDt+'</td>';
					html += '<td style="display:none;">'+value.noteNo+'</td>';
					html += '<td style="display:none;">'+value.employeeId+'</td>';
					html += '<td style="display:none;">'+value.read+'</td>';
					html += '</tr>';
        	   });
		      }else{
		    	 	html += '<th class="text-center" colspan="7">쪽지가 없습니다.</th>';
			  }
			  //var pageTotal = totalCnt/pageSize;
			  //mainBody 동적으로 html추가
		      $("#noteList>tbody").append(html);
		      
         },
         error:function(xhr,status,error){
             alert("error:"+error);
         }
        }); 
        // -- ajax
	}

	/*
	paging처리 
	*/
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