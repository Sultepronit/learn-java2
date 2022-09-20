<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>title</title>
</head>
<body>
Hello, JSP!

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