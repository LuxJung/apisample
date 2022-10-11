<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% String content = 
 	(String)request.getAttribute("content");%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서울시 공공도서관 목록result</title>
<script type="text/javascript" src="./js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="./js/llib.js"></script>
</head>
<body>
	<h1>서울시 공공도서관 목록1</h1>
	<table border="1">
		<thead></thead>
		<tbody></tbody>
	</table>
	
	<ul class="content">
		<li>${content}</li>
	</ul>
	
</body>
</html>