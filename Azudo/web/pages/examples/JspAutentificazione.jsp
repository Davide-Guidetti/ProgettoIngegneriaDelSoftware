<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%-- pagina per la gestione di errori --%>
<%@ page errorPage="../errors/failure.jsp"%>

<%-- accesso alla sessione --%>
<%@ page session="true"%>

<%-- import di classi Java --%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.google.gson.Gson"%>


<%
if(request.getParameter("logout") != null){
	session.setAttribute("login", Boolean.valueOf(false));
}

boolean isLoggedIn = false;
Boolean isLoggedInB = (Boolean)(session.getAttribute("logged")); 
if(isLoggedInB != null) isLoggedIn = isLoggedInB.booleanValue();
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		
		<% if(isLoggedIn){%>
		ciao, sei dentro
		
		
		
		
		<%}else{ %>
		
		
		non sei autenticato
		<%} %>
		
	</body>
</html>