<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>title</title>
</head>
<body>
<c:out value="Hello, JSTL" />

<% 
System.out.println("Boo!");
%>

<p>
<strong>
<%= new java.util.Date() %>
</strong>
</p>

<p>
<strong>
${pageContext.servletContext.contextPath}
</strong>
</p>

</body>
</html>