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
	<h2>JSTL 종합 예제</h2>
	<hr>
	<h3>set, out</h3>
	<c:set var="product1" value="<b>애플 아이폰</b>" />
	<c:set var="product2" value="삼성 갤럭시 노트" />
	<%-- 아래 코드는 사용할 경우에만 주석해제, 정상 작동하는 코드임. --%>
	<%--<c:set var="intArray" value="${[1, 2, 3, 4, 5]}" /> --%>
	<p>
		product1(jstl):
		<c:out value="${product1}" default="Not registered" escapeXml="true" />
	</p>
	<p>product1(e1) : ${product1}</p>
	<p>intArray[2] : ${intArray[2]}</p>
	
	<%-- forEach --%>
	<h3>forEach: 배열 출력</h3>
	<ul>
		<c:forEach var="num" varStatus="i" items="${intArray}">
			<li>${i.index} : ${num}</li>
		</c:forEach>
	</ul>
</body>
</html>