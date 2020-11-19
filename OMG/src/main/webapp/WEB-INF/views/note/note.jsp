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
						<!-- 메뉴 -->
						<div class="col-lg-2">
							<div class="card shadow mb-4 py-3 border-left-primary">
								<div class="card-body">
									<div class="list-group-flush">
										<a id="note1" href="#" onclick="changeNoteDiv(1,1); return false;" class="list-group-item"> 보낸 쪽지함 </a>
										<a id="note2" href="#" onclick="changeNoteDiv(2,1); return false;" class="list-group-item"> 받은 쪽지함 </a>
										<a id="note3" href="#" onclick="changeNoteDiv(3,1); return false;" class="list-group-item"> 휴지통 </a>
									</div>
								</div>
							</div>
						</div>
						<!-- // 메뉴 -->
						<!-- 메인 -->
						<div class="col-lg-10">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<div class="row">
										<div class="col-lg-6">
											<a href="${hContext}/note/note_reg.do" class="btn btn-secondary btn-sm">
												<span class="text">쪽지쓰기</span>
											</a>
											<a id="readBtn" href="#" class="btn btn-info btn-icon-split btn-sm">
												<span class="icon text-white-50"> <i class="fas fa-check"></i></span>
												<span class="text">읽음 처리</span>
											</a>
											<a id="deleteBtn" href="#" class="btn btn-info btn-icon-split btn-sm">
												<span class="icon text-white-50"> <i class="fas fa-trash"></i></span>
												<span class="text">삭제</span>
											</a>
												<input type="checkbox" id="onlyNotRead" onclick="drawNote($('#noteDiv').val(),1);"/> 읽지 않음
										</div>
										<div class="col-lg-6" align="right">
										
											<form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search" onsubmit="return false">
												<input type="hidden" name="pageNum" id="pageNum" value="1" />
											    <div class="input-group">
											    	<div class="px-1">
											    		<select class="form-control input-sm" name="pageSize"  id="pageSize">
												    		<c:forEach var="pageSzie" items="${pageSizeList}">
												    			<option value="${pageSzie.detCode}">${pageSzie.detNm}</option>
												    		</c:forEach>
											    		</select>
											    		<select class="form-control input-sm" name="searchDiv" id="searchDiv">
											    		  	<c:forEach var="noteCondition" items="${noteConditionList}">
												    			<option value="${noteCondition.detCode}">${noteCondition.detNm}</option>
												    		</c:forEach>
											    		</select> 
										    		</div>
													<input id="searchWord" name="searchWord" type="text" class="form-control bg-light small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
													<div class="input-group-append">
														<button class="btn btn-primary" type="button" id="searchBtn">
															<i class="fas fa-search fa-sm"></i>
												        </button>
													</div>
											    </div>
											</form>
										</div>
										
									</div>
								</div>
								<div class="card-body">
									<!-- 전송 폼 -->
									<form action="${hContext}/note/note_info.do" name="noteInfo_frm">
										<input type="hidden" id="employeeId" name="employeeId" />
										<input type="hidden" id="noteDiv" name="noteDiv" />
										<input type="hidden" id="noteNo" name="noteNo" />
										<input type="hidden" id="senderId" name="senderId" />
										<input type="hidden" id="read" name="read" />
									</form>
									<!-- // 전송 폼 -->
									<!-- 쪽지 테이블 -->
									<div class="table-responsive">
										<table id="noteList" class="table table-striped table-bordered table-hover table-condensed">
											<thead>
												<tr>
													<th class="text-center" width="2%"><input type="checkbox" onclick="checkToggle();" id="checkAll" /></th>
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
										
									</div>
									<!-- // 쪽지 테이블 -->
									<!-- 페이지네이션 -->
										<div id="page-selection" class="pagination d-flex justify-content-center" ></div>
										<!-- // 페이지네이션 -->
								</div>
							</div>
						</div>
						<!-- // 메인 -->
					</div>
					<!-- // 메뉴 -->

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
		drawNote("${noteDiv}",1);
		})
		
		
	function changeNoteDiv(noteDiv,pageNum){
		$('#searchWord').val('');
		$("#checkAll").prop("checked", false);
		$("#onlyNotRead").prop("checked", false);
		drawNote(noteDiv,pageNum);
		}
		
	/* 검색 버튼 */
	$("#searchBtn").on("click",function(){
		drawNote($("#noteDiv").val(),1);
		$("#checkAll").prop("checked", false);
	});

	//검색 Enter Event처리
	$("#searchWord").keypress(function(event) {
		//alert("#searchWord"+key.keyCode)
		if(event.keyCode==13){
			$("#searchBtn").trigger("click");
		}
	});

	// pageSize 변경시 자동 검색
	$("#pageSize").on("change",function(){
		$("#searchBtn").trigger("click");
		})
	
	function checkToggle(){
      if( $("#checkAll").is(':checked') ){
        $("input:checkbox[name=selectNote]").prop("checked", true);
      }else{
        $("input:checkbox[name=selectNote]").prop("checked", false);
      }
	}

	
	/* 삭제 버튼 */	
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
           $("#checkAll").prop("checked", false);
           drawNote($("#noteDiv").val(),1);
		},
         error:function(xhr,status,error){
             alert("error:"+error);
         }
        }); 
	})
	
	/* 읽음 처리 */
	$("#readBtn").on("click",function(){
		var noteNolist = [];
		var employeelist = [];
		var senderlist = [];
		var readlist = [];
		$("input:checkbox[name=selectNote]:checked").each(function(i){
			var args = [];
			args = $(this).val().split(',');
			noteNolist.push(args[0]);
			employeelist.push(args[1]);
			senderlist.push(args[2]);
			readlist.push(args[3]);
			});
		
		jQuery.ajaxSettings.traditional = true;
		$.ajax({
            type:"GET",
            url:"${hContext}/note/changeRead.do",
            dataType:"html",
            async: true,
            data:{
                "noteDiv" : $("#noteDiv").val(),
                "noteNo" : noteNolist,
                "employee" : employeelist,
                "sender" : senderlist,
                "read" : readlist
                },
         success: function(data){
           var parseData = JSON.parse(data);

           if(null != parseData ){
        	   alert(parseData.msgContents);
               }
           
           $("#checkAll").prop("checked", false);
           drawNote($("#noteDiv").val(),1);
		},
         error:function(xhr,status,error){
             alert("error:"+error);
         }
        });

		})
	
	/* 쪽지 확인 */
	$("#noteList>tbody").on("click","tr",function(){
		var tds = $(this).children();
		var noteNo = tds.eq(8).text();
		var employeeId = tds.eq(9).text();
		var senderId = tds.eq(2).text();
		var read = tds.eq(10).text();
	
		var frm = document.noteInfo_frm;
		frm.noteNo.value = noteNo;
		frm.employeeId.value = employeeId;
		frm.senderId.value = senderId;
		frm.read.value = read;
		frm.noteDiv.value = $("#noteDiv").val();
		frm.submit();
		//window.location.href="${hContext}/note/note_info.do"
		})
	
	/* 테이블 그리기 */
	function drawNote(noteDiv,page){
		
		$("#noteDiv").val(noteDiv);
		var activeNote = "note"+noteDiv;
		$(".active").removeClass("active");
		document.getElementById(activeNote).classList.add("active");
		$.ajax({
            type:"GET",
            url:"${hContext}/note/doSelectList.do",
            dataType:"html",
            async: true, 
            data : {
                "div" : $("#onlyNotRead:checked").length,
                "noteDiv" : noteDiv,
                "pageSize" : $("#pageSize").val(),
                "pageNum" : page,
                "searchWord" : $("#searchWord").val(),
                "searchDiv" : $("#searchDiv").val()
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
               var totalCount = 0;
        	   $.each(parseData, function(i, value) {
        		   totalCount = totalCount +1;
        		   /* if( $("#onlyNotRead").is(':checked') && value.read==1 ){
            		   totalCnt = totalCount;
            		   return true;
        		   } */
        		   
            	    html += '<tr>';
    		   		html += '<td class="text-center" onclick="event.cancelBubble=true">';
    		   		html += '<input type="checkbox" name="selectNote" value="'+value.noteNo+','+value.employeeId+','+value.senderId+','+value.read+'" />';
    		   		html += '</td>';
					html += '<td class="text-center">'+value.senderNm+'('+value.senderId+')</td>';
					html += '<td style="display:none;">'+value.senderId+'</td>';
					html += '<td class="text-center">'+value.receiveNm+'('+value.receiveId+')</td>';
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
			  var pageTotal = Math.ceil(totalCnt/$("#pageSize").val());
			  //테이블 동적으로 html추가
		      $("#noteList>tbody").append(html);
		      console.log(totalCnt);
		      console.log(totalCount);
		      renderingPage(pageTotal,page);
		      
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
            $("#pageNum").val(num);
            drawNote($("#noteDiv").val(),num);
        }); 
	}
			
	</script>
</body>
</html>