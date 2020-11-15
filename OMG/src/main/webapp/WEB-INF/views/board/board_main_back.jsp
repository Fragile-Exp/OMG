<%@page import="com.omg.code.domain.Code"%>
<%@page import="java.util.List"%>
<%@page import="com.omg.cmn.Search"%>
<%@page import="com.omg.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="hContext" value="${pageContext.request.contextPath }"></c:set> 
<!DOCTYPE html>
<html lang="ko">
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

					<!-- Page Heading -->
					<h1 class="h3 mb-4 text-gray-800" >OO게시판</h1>
					<div class="card shadow mb-4">
						<form action="${hContext}/board/doSelectList.do" name="searchFrm">
							<input type="hidden" name="pageNum" id="pageNum" />
							<div class="card-header py-3">
								<div class="row" align="right">
									<input type="hidden" name="div"	 id="div"	value="${vo.getDiv()}" />
									<div class="col">
										<!-- <h6 class="m-0 font-weight-bold text-primary" >OO게시판</h6> -->
									</div>
									<div class="input-group col-lg-3" style="float: right;">
										<div class="px-1">
										<%
											//검색조건
											List<Code> boardCondition = (List<Code>)request.getAttribute("BOARD_CONDITION");
											Search search = (Search)request.getAttribute("vo");
							    		    out.print(StringUtil.makeSelectBox(boardCondition, "searchDiv", search.getSearchDiv(), true));
										%>
										</div>
										<input id="searchWord" name="searchWord" type="text" class="form-control bg-light small" placeholder="Search for..." 
											aria-label="Search" aria-describedby="basic-addon2" value="${vo.searchWord}">
										<div class="input-group-append">
											<button class="btn btn-primary" type="button" onclick="javascript:doSelectList();">
												<i class="fas fa-search fa-sm"></i>
											</button>
										</div>
										<input type="button" class="btn btn-primary btn-icon-split icon text-white-100"  value="등록"  id="doInsertBtn" style="width: 55px;"/>
									</div>
								</div>
							</div>
						</form>
						<form action="${hContext}/board/doSelectList.do" name="boardList">
							<input type="hidden" name="boardSeq"	id="boardSeq" />
							<input type="hidden" name="pageNum" id="pageNum" />
							<div class="com-sm-12">
								<table class="table table-bordered dataTable" id="boardListTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
									<thead>
										<tr role="row">
											<th class="text-center seq" style="width: 60px;">번호</th>
											<th class="text-left title" >제목</th>
											<th class="text-left regId" style="width: 240px;">작성자(수정자)</th>
											<th class="text-center regDt" style="width: 120px;" >작성일</th>
											<th class="text-center readCnt" style="width: 60px;" >조회수</th>
											<th class="hidden-lg hidden-sm hidden-xs" style="display:none;" >boardSeq</th>
										</tr>
									</thead>
										<c:choose>
											<c:when test="${list.size()>0}">
												<c:forEach var="vo" items="${list}">
													<tr>
														<td class="text-center seq">${vo.num}</td>
														<td class="text-left title">${vo.title}</td>
														<td class="text-left regId">${vo.regId} (${vo.modId} )</td>
														<td class="text-center regDt">${vo.regDt}</td>
														<td class="text-center readCnt">${vo.readCnt}</td>
														<td class="hidden-lg hidden-sm hidden-xs" style="display:none;">${vo.boardSeq}</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td class="text-center" colspan="99">No data found.</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
							<!-- pagenation -->
							<div>
							<%
								int maxNum = 0; //총글수 
								int currPageNo = 1; //현재페이지
								int rowPerPage = 10; //현페이지에 보여질 행수
								int bottomCount = 10; //바닥에 보여질 페이지 수
								String url=""; //호출 url
								String scriptName=""; //호출javascript
								
								maxNum = (Integer)request.getAttribute("totalCnt");
								
								Search searchPage = (Search)request.getAttribute("vo");
								if(null != searchPage)
								{
									currPageNo = searchPage.getPageNum();
									rowPerPage = searchPage.getPageSize();
								}
								
								url = request.getContextPath()+"/board/doSelectList.do";
								//out.print("url : "+url);
								scriptName = "doSearchPage";
								
								out.print(StringUtil.renderPaging(maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName));
							%>
							</div>
							<!--// pagenation -->
						</form>
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
	$(document).ready(function()
	{
		/* $("#Pages").attr("class","nav-link");
		$("#Pages").attr("aria-expanded","true");
		$("#collapsePages").attr("class","collapse show");
		$("#blank").attr("class","collapse-item active"); */
	});

	function doSearchPage(url, num)
	{
		//alert(url+":"+num);

		var frm = document.searchFrm;
		frm.pageNum.value = num;
		frm.action = url;
		frm.submit();
	}

	$("#doInsertBtn").on("click", function() {

    	var frm = document.searchFrm;
    	frm.action = "${hContext}/board/boardInsert.do";
    	frm.submit();
    	
    });

	function doSelectList(){
        //alert('doSelectList');
      var frm = document.searchFrm;
      frm.pageNum.value = 1;
      frm.submit();
	}

	$("#boardListTable>tbody").on("click","tr" ,function() {
    	//console.log("#boardListTable>tbody");
    	var trs = $(this);
    	var tds = trs.children();
    	var boardSeq = tds.eq(5).text();
    	
    	console.log("boardSeq:"+boardSeq);
    	//get방식 형태 call
    	//window.location.href="${hContext}/board/doSelectOne.do?seq="+seq;

    	var frm = document.boardList;
    	frm.boardSeq.value = boardSeq;
    	frm.action = "${hContext}/board/doSelectOne.do";
    	frm.submit();
    });
	
	</script>
</body>
</html>