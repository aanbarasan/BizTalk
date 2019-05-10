<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>User Details List</h1>

<table>
<tr>
	<th>Employee Id</th>
	<th>Employee Name</th>
	<th>Address</th>
</tr>

	<c:forEach items="${userlist}" var="element">    
<tr>	
	<td>${element.empName}</td>
	<td>${element.empId}</td>
	<td>${element.address}</td>
	
</tr>	
	</c:forEach>
<td><a href="${contextPath}/addredirect"> Add</a> </td>	


</table>


</body>
</html>