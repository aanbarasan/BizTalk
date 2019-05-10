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
<link href="${contextPath}/css/loginstyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">       
 <form method="POST" action="${contextPath}/login">

<label for="welcome">Welcome!</label><br><br>
<label for="headline">Please enter your username and password to continue.</label><br><br>
<input type="text" id="username" name="username" placeholder="Username(TPX@tesco.com ID)" required><br>
<input type="password" id="Password" name="Password"  placeholder="Password" required><br>
<input type="checkbox"><label for="checkbox">Keep me logged in</label><br><br>
<div id="lower">


<button class="login" type="submit"> 
    Login   
</button>

<a>Forgot Password?</a>
</div>
</form>
</div>
</body>
</html>