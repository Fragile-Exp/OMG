var date;
var nowYear;
var nowMonth;
var nowDay;
var year;
var month;
var day;
var hour;
var minute;

//날짜계산
function today() {
    var date = new Date();
    nowYear = date.getFullYear();
    nowMonth = date.getMonth() + 1;
    nowDay = date.getDate();
    year = date.getFullYear();
    month = date.getMonth() + 1;
    day = date.getDate();
    hour = date.getHours();
    minute = date.getMinutes();
}

//시작일 및 종료일 입력
function inputStartDay() {
    var yyyy = nowYear;
    var mm;
    var dd;
    
    if(month < 10) {
        mm = "0" + month;
    } else if(month >= 10) {
        mm = month;
    }
    if(day < 10) {
        dd = "0" + day;
    } else if(day >= 10) {
        dd = day;
    }
    var today = yyyy + "-" + mm + "-" + dd;

    $("#startDt").val(today);
    $("#endDt").val(today);
}

//일정추가 시간 및 분 구하기
function inputHourAndMinute() {
    var str = "";
    for(var i = 0; i <= 24; i++) {
        if(i < 10) {
            str = str + "<option value='0" + i +"'>0" + i + "</option>";
        } else if(i >= 10) {
            str = str + "<option value='" + i +"'>" + i + "</option>";
        }
        $("#startHour").html(str);
        $("#endHour").html(str);
    }

    for(var i = 0; i <= 60; i++) {
        if(i < 10) {
            str = str + "<option value='0" + i +"'>0" + i + "</option>";
        } else if(i >= 10) {
            str = str + "<option value='" + i +"'>" + i + "</option>";
        }
        $("#startMinute").html(str);
        $("#endMinute").html(str);
    }
}

//달력 년도, 월 구하기
function getYearMonth() {
    var str;

    str = year + "년 " + month + "월";

    $("#yearMonth").text(str);
}

//월 감소 버튼
function clickPrev() {
    if(month > 1){
        month = month - 1;
    } else if(month == 1) {
        month = 12;
        year = year - 1;
    }
    
    getYearMonth();
}

//월 증가버튼
function clickPrev() {
    if(month > 1){
        month = month - 1;
    } else if(month == 1) {
        month = 12;
        year = year - 1;
    }
    
    getYearMonth();
}

//월 감소버튼
function clickNext() {
    if(month < 12){
        month = month + 1;
    } else if(month == 12) {
        month = 1;
        year = year + 1;
    }
    
    getYearMonth();
}

//현재날짜로 가는 버튼
function clickToday() {
    month = nowMonth;
    year = nowYear;
    day = nowDay;

    getYearMonth();
}