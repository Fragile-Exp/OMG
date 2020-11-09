<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <style>
        #menu {
            margin:0;
            background-color: aquamarine; 
            padding: 10px;
            float: left;    
        }
        #schedule {
            float: center;
        }
        #calendar {
            width: 300px;
            
        }
        
    </style>
</head>
<body>
    <div id="wrap">
        <!-- menu -->
        <div id="menu">
            <h3>일정추가</h3>
            <form id="scheduleInsert" action="/schedule/insert.do" method="post">
                <div class="form-group">
                    <label>제목</label>
                    <input type="text" id="scheduleNm" class="form-control" placeholder="제목">
                </div>

                <div class="form-group">
                    <label>시작일</label>
                    <input type="date" id="startDay" class="form-control">
                    <select id="startHour">
                        <option value="00">00</option>
                    </select>시
                    <select id="startMinute">
                        <option value="00">00</option>
                    </select>분
                </div>

                <div>
                    <label>종료일</label>
                    <input type="date" id="endDay" class="form-control">
                    <select id="endHour">
                        <option value="00">00</option>
                    </select>시
                    <select id="endMinute">
                        <option value="00">00</option>
                    </select>분
                </div>

                <div>
                    <textarea id="content" class="form-control" placeholder="내용"></textarea>
                </div>

                <button type="button" id="insertSchedule" class="btn btn-default form-control">저장</button>
            </form>
        </div>

        <!-- schedule -->
        <div id="schedule">
            <div id="scheduleTop" class="form-group">
                <button id="prev" type="btn btn-default" onclick="clickPrev()">prev</button>
                <label id="yearMonth">0000년 00월</label>
                <button id="next" type="btn btn-default" onclick="clickNext()">next</button>
                <button id="gotoToday" type="btn btn-default" onclick="clickToday()">Today</button>
            </div>
            <div id="scheduleBottom">
                <table id="calendar" class="table table-bordered">
                    <colgroup>
                        <col width="14.2%">
                        <col width="14.2%">
                        <col width="14.2%">
                        <col width="14.2%">
                        <col width="14.2%">
                        <col width="14.2%">
                        <col width="14.2%">
                    </colgroup>
                    <thead>
                        <th style="color: red;">일</th>
                        <th>월</th>
                        <th>화</th>
                        <th>수</th>
                        <th>목</th>
                        <th>금</th>
                        <th style="color: blue;">토</th>
                    </thead>
                    <tbody id="tbody">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    ${list.title}
    <script src="/resources/js/schedule/schedule.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script>
        $(document).ready(function() {
            today();
            inputStartDay();
            inputHourAndMinute();
            getYearMonth();
            
        });
    </script>
</body>
</html>
