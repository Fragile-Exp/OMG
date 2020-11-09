<%@ page language="java" contentType="text/html; charset=UTF-8"
	isErrorPage="true"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error</title>
</head>
<body>
	<h1>error</h1>
	<hr/>
	에러타입 :<%=exception.getClass().getName() %> <br/>
	에러메세지 :<%=exception.getMessage() %> <br/>  
</body>
</html>