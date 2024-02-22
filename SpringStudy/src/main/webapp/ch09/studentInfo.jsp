<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>학생정보</h2>[<a href="/studentControl">새로고침</a>]
	<table>
		<tr>
			<th>이름</th>
			<th>대학</th>
			<th>생일</th>
			<th>이메일</th>
		</tr>
		<c:forEach var="s" items="${students}">
			<tr>
				<td>${s.username }</td>
				<td>${s.univ }</td>
				<td>${s.birth }</td>
				<td>${s.email }</td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<form method="post" action="/studentControl?action=insert" accept-charset="UTF-8">
		<label>이름</label>
		<input type="text" name="username"><br>
		<label>대학</label>
		<input type="text" name="univ"><br>
		<label>생일</label>
		<input type="date" name="birth"><br>
		<label>이메일</label>
		<input type="text" name="email"><br>
		<button type="submit">등록</button>
	</form>
</body>
</html>