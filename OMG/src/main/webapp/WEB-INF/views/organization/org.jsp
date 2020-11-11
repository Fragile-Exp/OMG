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
							<div class="card mb-4 py-3 border-left-primary">
								<div class="card-body">
									<form action="${hContext}/org/org.do" name="move_frm" method="get">
										<div class="list-group-flush">
											<input type="hidden" id="moveDiv" name="moveDiv" />
											<a href="#" onclick="javascript:changeOrg('dept'); return false;" class="list-group-item"> 부서 </a>
											<a href="#" onclick="javascript:changeOrg('position'); return false;" class="list-group-item"> 직급 </a>
										</div>
									</form>
								</div>
							</div>
						</div>
						<div class="col-lg-10">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<a id="deleteBtn" href="#" class="btn btn-info btn-icon-split btn-sm">
										<span class="icon text-white-50"> <i class="fas fa-trash"></i></span>
										<span class="text">삭제</span>
									</a>
									<button id="updateBtn" type="button" class="btn btn-info btn-icon-split btn-sm">
										<span class="icon text-white-50"> <i class="fas fa-check"></i></span>
										<span class="text">수정</span>
									</button>
									<form action="${hContext}/org/org_reg.do" name="reg_frm" method="get" style="display:inline" >
									<input type="hidden" id="orgDiv" name="orgDiv" value="dept" />
									<button type="submit" class="btn btn-info btn-icon-split btn-sm">
										<span class="icon text-white-50"> <i class="fas fa-arrow-right"></i></span>
										<span class="text">추가</span>
									</button>
									</form>
								</div>
								<form action="" name="org_frm" method="get" >
								<input type="hidden" id="org_div" name="org_div" value="dept" />
									<div id="mainBody" class="card-body">
										
									</div>
								</form>
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
		$("#org").attr("class","collapse-item active");
		drawCheck("${moveDiv}");
		});

	
	function changeOrg(div){
		var frm = document.move_frm;
		frm.moveDiv.value = div;
		frm.submit();
	}
		

	function drawCheck(div){
		$("#orgDiv").val(div);
		$("#org_div").val(div);
		
		if(div == "dept"){
			drawDeptList();
		} else {
			drawPositionList();
		}
	}

	function drawPositionList(){
		$.ajax({
            type:"GET",
            url:"${hContext}/org/doSelectListPosition.do",
            dataType:"html",
            async: true, 
         success: function(data){
           var parseData = JSON.parse(data);
			//table에 있던 기존 데이터 삭제
			$("#mainBody").empty();
			var html = "";
			var maxLevel = 0;

			//Data가 없으면
           if(parseData.length>0){
               html += '<div class="text-primary">';
        	   $.each(parseData, function(i, value) {
        		   html += '<input type="checkbox" name="org" value="'+value.positionNo+'" /> '+value.positionNm+'';
        		   html += '<br/>';
        	   });
        	   html += '</div>';
		      }else{
		    	  html += "<span class='label label-default'>";
				  html += "직급 정보가 없습니다.";
				  html += "</span>";
			  }        
			  //mainBody 동적으로 html추가
		      $("#mainBody").append(html);
		      
         },
         error:function(xhr,status,error){
             alert("error:"+error);
         }
        }); 
	}

	
	$("#updateBtn").on("click",function(){
		//console.log($("input:checkbox[name=org]:checked").length);
		var size = $("input:checkbox[name=org]:checked").length;
		if(size != 1) {
			alert("하나만 선택해 주세요.");
			return;
		}
		
		var frm = document.org_frm;
		frm.action="${hContext}/org/org_mod.do";
		frm.submit();	
	})
	
	$("#deleteBtn").on("click",function(){
		var size = $("input:checkbox[name=org]:checked").length;
		if(size < 1) {
			alert("하나이상 선택해 주세요.");
			return;
		}
		var lists = [];
		$("input:checkbox[name=org]:checked").each(function(i){
			lists.push($(this).val());
			});
		console.log(lists);

		if(false == confirm("    * 주의 *\n하위 부서(직급)까지 전부 사라질 수 있습니다.\n그래도 삭제 하시겠습니까?")) return;
		
		jQuery.ajaxSettings.traditional = true;
		$.ajax({
            type:"GET",
            url:"${hContext}/org/doDelete.do",
            dataType:"html",
            async: true,
            data:{
                "orgDiv" : $("#orgDiv").val(),
                "orgNo" : lists
                },
         success: function(data){
           var parseData = JSON.parse(data);
           alert(parseData.msgContents);
           changeOrg($("#orgDiv").val());
		},
         error:function(xhr,status,error){
             alert("error:"+error);
         }
        }); 
	})
	
	
	
	function drawDeptList(){

		$.ajax({
            type:"GET",
            url:"${hContext}/org/doSelectListDept.do",
            dataType:"html",
            async: true, 
         success: function(data){
           var parseData = JSON.parse(data);
			//table에 있던 기존 데이터 삭제
			$("#mainBody").empty();
			var html = "";
			var maxLevel = 0;

			//Data가 없으면
           if(parseData.length>0){
               maxLevel = parseData[0].totalCnt;
               var currLevel = 0;
			  $.each(parseData, function(i, value) {
				  if(value.level <= (currLevel -1)){
					  for(var i=0;i<(currLevel-value.level);i++){
					  html += '</div>';
					  html += '</details>';
					  }
					  }
				  if( 0 == value.isNotLeaf){
					  html += '<details class="text-primary">';
					  html += '<summary>';
					  html += '<input type="checkbox" name="org" value="'+value.deptNo+'" /> '+value.deptNm+'';
					  html += '</summary>';
					  html += '<div class="bg-white px-4">';
					  } else{
						  html += '<input type="checkbox" name="org" value="'+value.deptNo+'" /> '+value.deptNm+'<br />';
					  	}
				 
				  	currLevel = value.level;

			  });
			  	for(var i=1; i<currLevel;i++){
					html += '</div>';
					html += '</details>';
					}  
		      }else{
		    	  html += "<span class='label label-default'>";
				  html += "부서 정보가 없습니다.";
				  html += "</span>";
			  }
		                  
			  //mainBody 동적으로 html추가
		      $("#mainBody").append(html);
		      
         },
         error:function(xhr,status,error){
             alert("error:"+error);
         }
        }); 
		
		}
	
	</script>
	
</body>
</html>