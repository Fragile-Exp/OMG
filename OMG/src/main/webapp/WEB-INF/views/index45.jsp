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
	<%@include file="inc/side_bar.jsp" %>
	<!-- //side_bar -->
		<!-- Content Wrapper -->
    	<div id="content-wrapper" class="d-flex flex-column">

	      	<!-- Main Content -->
	      	<div id="content">
	      		<!-- top_bar -->
	      		<%@include file="inc/top_bar.jsp" %>
	      		<!-- //top_bar -->
	      		
	      		<!-- page Content -->
	      		<div class="container-fluid">

					<!-- Content Row -->
					<div class="row">
					
					  <!-- Earnings (Monthly) Card Example -->
					  <div class="col-xl-3 col-md-6 mb-4">
					    <div class="card border-left-primary shadow h-100 py-2">
					      <div class="card-body">
					        <div class="row no-gutters align-items-center">
					          <div class="col mr-2">
					            <div class="text-sm mb-0 font-weight-bold text-primary text-uppercase mb-1">문서 결재 현황</div>
					            <div class="h6 mb-0 font-weight-bold text-gray-800">결재 받을 문서 : x 건</div>
					            <div class="h6 mb-0 font-weight-bold text-gray-800">결재 해야 할 문서 : x 건</div>
					          </div>
					          <div class="col-auto">
					            <i class="fas fa-calendar fa-2x text-gray-300"></i>
					          </div>
					        </div>
					      </div>
					    </div>
					  </div>
					
					  <!-- Earnings (Monthly) Card Example -->
					  <div class="col-xl-3 col-md-6 mb-4">
					    <div class="card border-left-success shadow h-100 py-2">
					      <div class="card-body">
					        <div class="row no-gutters align-items-center">
					          <div class="col mr-2">
					            <div class="text-sm mb-0 font-weight-bold text-success text-uppercase mb-1">${sessionScope.employee.dept_nm} 출근 현황</div>
					            <div class="h6 mb-0 font-weight-bold text-gray-800">출근 : x건  결근 : x건</div>
					          </div>
					          <div class="col-auto">
					            <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
					          </div>
					        </div>
					      </div>
					    </div>
					  </div>
					
					  <!-- Earnings (Monthly) Card Example -->
					  <div class="col-xl-3 col-md-6 mb-4">
					    <div class="card border-left-info shadow h-100 py-2">
					      <div class="card-body">
					        <div class="row no-gutters align-items-center">
					          <div class="col mr-2">
					            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Tasks</div>
					            <div class="row no-gutters align-items-center">
					              <div class="col-auto">
					                <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50%</div>
					              </div>
					              <div class="col">
					                <div class="progress progress-sm mr-2">
					                  <div class="progress-bar bg-info" role="progressbar" style="width: 50%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
					                </div>
					              </div>
					            </div>
					          </div>
					          <div class="col-auto">
					            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
					          </div>
					        </div>
					      </div>
					    </div>
					  </div>
					
					  <!-- Pending Requests Card Example -->
					  <div class="col-xl-3 col-md-6 mb-4">
					    <div class="card border-left-warning shadow h-100 py-2">
					      <div class="card-body">
					        <div class="row no-gutters align-items-center">
					          <div class="col mr-2">
					            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">Pending Requests</div>
					            <div class="h5 mb-0 font-weight-bold text-gray-800">18</div>
					          </div>
					          <div class="col-auto">
					            <i class="fas fa-comments fa-2x text-gray-300"></i>
					          </div>
					        </div>
					      </div>
					    </div>
					  </div>
					</div>	
					<div id="carousel-example-generic" class="carousel slide text-center" data-ride="carousel" data-interval=5000>
					<!-- Indicators -->
						<ol class="carousel-indicators">
							<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
							<li data-target="#carousel-example-generic" data-slide-to="1"></li>
							<li data-target="#carousel-example-generic" data-slide-to="2"></li>
							<li data-target="#carousel-example-generic" data-slide-to="3"></li>
						</ol>
						
						<!-- Wrapper for slides -->
						<div class="carousel-inner" role="listbox">
							<div class="carousel-item active">
						    	<img src="/cmn/resources/images/coding.jpg" width="1200" height="700" alt="...">
							    <div class="carousel-caption">
							    	<h3>Image 1</h3>
								</div>
							</div>
							<div class="carousel-item">
							    <img src="/cmn/resources/images/laptop.jpg" width="1200" height="700" alt="...">
							    <div class="carousel-caption">
							    <h3>Image 2</h3>
							    </div>
							</div>
							<div class="carousel-item">
							    <img src="/cmn/resources/images/man.jpg" width="1200" height="700" alt="...">
							    <div class="carousel-caption">
							    <h3>Image 3</h3>
							    </div>
							</div>
							<div class="carousel-item">
							    <img src="/cmn/resources/images/office.jpg" width="1200" height="700" alt="...">
							    <div class="carousel-caption">
							    <h3>Image 4</h3>
							    </div>
							</div>
						</div>
						
						<!-- Controls -->
						<a class="left carousel-control-prev" href="#carousel-example-generic" role="button" data-slide="prev">
							<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a>
						<a class="right carousel-control-next" href="#carousel-example-generic" role="button" data-slide="next">
							<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>
					
					<!-- Content Row -->
					<div class="row py-4">
					
						<div class="col-xl-4 col-lg-5">
						   <!-- Illustrations -->
						   <div class="card shadow mb-4">
						     <div class="card-body">
						       <div class="text-center">
						         <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 25rem;" src="${hContext}/resources/images/logo.svg" alt="">
						       </div>
						       <!-- <p>Add some quality, svg illustrations to your project courtesy of <a target="_blank" rel="nofollow" href="https://undraw.co/">unDraw</a>, a constantly updated collection of beautiful svg images that you can use completely free and without attribution!</p>
						       <a target="_blank" rel="nofollow" href="https://undraw.co/">Browse Illustrations on unDraw &rarr;</a> -->
						     </div>
						   </div>
						</div>
						
						<div class="col-xl-2 col-lg-3">
							 <!-- Earnings (Monthly) Card Example -->
							    <div class="card border-left-success shadow h-100 py-2">
							      <div class="card-body">
							        <div class="row no-gutters align-items-center">
							          <div class="col mr-2">
							            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">부서 출근 현황</div>
							            
							            <label class="h5 mb-0 font-weight-bold text-gray-800">전체 : text</label>
							            <label class="h5 mb-0 font-weight-bold text-gray-800">출근 : text</label>
							            <label class="h5 mb-0 font-weight-bold text-gray-800">결근 : text</label>
							            
							          </div>
							          <div class="col-auto">
							            <i class="far fa-calendar-check fa-2x text-gray-300"></i>
							          </div>
							        </div>
							      </div>
							    </div>
						</div>
					</div>	
					<section class="schedule">
						<div class="col-xl-4 col-lg-5">
							<!-- card -->
							<div class="card shadow mb-4" style="font-size: 10px">
								<!-- header-->
							    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
							      <h6 class="m-0 font-weight-bold text-primary">내 일정</h6>
							    </div>
							    <!-- header-->
							    <div class="card-body">
									<!-- table -->
									<table class="table table-striped table-bordered table-hover" id="dateTable" width="100%" cellspacing="0">
										<thead>
											<tr>
												<th>제목</th>
												<th>시작일</th>
												<th>종료일</th>
											</tr>
										</thead>
										<c:forEach items="${scheduleList}" var="schedule">
											<tr>
												<td>
													<a class="move" href="<c:out value='${schedule.schedule_no}'/>">
														<c:out value="${schedule.title}"/>
													</a>
												</td>
												<td><c:out value="${schedule.start_dt}"/></td>
												<td><c:out value="${schedule.end_dt}"/></td>
											</tr>
										</c:forEach>
									</table>
									<!-- /table -->
								</div>
							</div>
							<!-- card -->
						</div>
					</section>
					<!-- 게시판 -->
					<section class="board row">
						<input type="hidden" name="boardSeq"	id="boardSeq" />
						<!--공지사항-->
						<div class="board__notice col-xl-6 col-lg-6" >
						
							<div class="card shadow mb-4">
							    <!-- Card Header - Dropdown -->
							    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
							      <h6 class="m-0 font-weight-bold text-primary">공지사항</h6>
							      <div class="dropdown no-arrow">
							        <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							          <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
							        </a>
							        <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
							          <div class="dropdown-header">Dropdown Header:</div>
							          <a class="dropdown-item" href="#">Action</a>
							          <a class="dropdown-item" href="#">Another action</a>
							          <div class="dropdown-divider"></div>
							          <a class="dropdown-item" href="#">Something else here</a>
							        </div>
							      </div>
							    </div>
							    <!-- // Card Header - Dropdown -->
							    <!-- Card Body -->
							    <div class="card-body">
									<div class="com-sm-12">
										<table class="table table-bordered table-hover dataTable" id="noticeListTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%; font-size: 13px">
											<thead>
												<tr role="row">
													<th class="text-center seq">번호</th>
													<th class="text-left title" >제목</th>
													<th class="text-left regId" >작성자(수정자)</th>
													<th class="text-center regDt" >작성일</th>
													<th class="text-center readCnt" >조회수</th>
													<th class="hidden-lg hidden-sm hidden-xs" style="display:none;" >boardSeq</th>
												</tr>
											</thead>
											<tbody>
												<c:choose>
													<c:when test="${noticeList.size()>0}">
														<c:forEach var="vo" items="${noticeList}">
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
							    </div>
							    <!-- Card Body -->
							    
							</div>
						</div>
						<!-- //공지사항 -->
						
						<!--부서게시판-->
						<div class="board__dept col-xl-6 col-lg-6" >
							<div class="card shadow mb-4">
							    <!-- Card Header - Dropdown -->
							    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
							      <h6 class="m-0 font-weight-bold text-primary">내 부서 게시판</h6>
							      <div class="dropdown no-arrow">
							        <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							          <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
							        </a>
							        <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
							          <div class="dropdown-header">Dropdown Header:</div>
							          <a class="dropdown-item" href="#">Action</a>
							          <a class="dropdown-item" href="#">Another action</a>
							          <div class="dropdown-divider"></div>
							          <a class="dropdown-item" href="#">Something else here</a>
							        </div>
							      </div>
							    </div>
							    <!-- Card Body -->
							    <div class="card-body">
									<div class="com-sm-12">
										<table class="table table-bordered table-hover dataTable" id="deptBoardListTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%; font-size: 13px">
											<thead>
												<tr role="row">
													<th class="text-center seq">번호</th>
													<th class="text-left title" >제목</th>
													<th class="text-left regId" >작성자(수정자)</th>
													<th class="text-center regDt" >작성일</th>
													<th class="text-center readCnt" >조회수</th>
													<th class="hidden-lg hidden-sm hidden-xs" style="display:none;" >boardSeq</th>
												</tr>
											</thead>
											<tbody>
												<c:choose>
													<c:when test="${deptBoardList.size()>0}">
														<c:forEach var="vo" items="${deptBoardList}">
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
							    </div>
							    <!-- Card Body -->
							</div>
						</div>
						<!-- //부서게시판 -->
					</section>
					<!-- //게시판 -->
		        </div>
		        <!-- // page Content -->
	      	
	      	</div>
	      	<!-- //Main Content -->
	      	
	      	<!-- footer -->
	      	<%@include file="inc/footer.jsp" %>
	      	<!-- //footer -->
	      	
		</div>
		<!-- //Content Wrapper -->
</div>
<!-- //wrap -->
	<script type="text/javascript">
	$(document).ready(function()
	{
		console.log("document ready!!");
		
	});

	$("#noticeListTable>tbody").on("click","tr" ,function() {
    	//console.log("#boardListTable>tbody");
    	var trs = $(this);
    	var tds = trs.children();
    	var boardSeq = tds.eq(5).text();
    	
    	console.log("boardSeq:"+boardSeq);
    	//get방식 형태 call
    	window.location.href="${hContext}/board/doSelectOne.do?boardSeq="+boardSeq;

    });

	$("#deptBoardListTable>tbody").on("click","tr" ,function() {
    	//console.log("#boardListTable>tbody");
    	var trs = $(this);
    	var tds = trs.children();
    	var boardSeq = tds.eq(5).text();
    	
    	console.log("boardSeq:"+boardSeq);
    	//get방식 형태 call
    	window.location.href="${hContext}/board/doSelectOne.do?boardSeq="+boardSeq;
  

    });
	
	</script>
	
</body>
</html>