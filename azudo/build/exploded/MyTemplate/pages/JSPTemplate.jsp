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

<%-- <%@ page import="it.unibo.tw.web.beans.__beanClass__"%> --%>
<%-- creazione Java Bean con scope di applicazione --%>
<%--	<jsp:useBean id="beanId" class="it.unibo.tw.web.beans.__beanClass__" scope="application" >
		<% 
			//executed once at bean initialization
			beanId.add(new Item(1));
			beanId.add(new Item(2));
		%>
		</jsp:useBean>
--%>

<%-- creazione Java Bean con scope di sessione --%>
<%--	<jsp:useBean id="beanId" class="it.unibo.tw.web.beans.__beanClass__" scope="session" >
		<% 
			//executed once at bean initialization
			beanId.add(new Item(1));
			beanId.add(new Item(2));
		%>
		</jsp:useBean>
--%>

<%-- invalidate session after a given amount of time --%>
<% session.setMaxInactiveInterval(60*10); //seconds %>

<%
	//working with url parameters
	if(request.getParameter("A") != null){
		long A = Long.parseLong(request.getParameter("A"));
		int B = Integer.parseInt(request.getParameter("B"));
		
	}
	
%>

<%-- set a paticular MIME TYPE --%>
<% response.setContentType("text/plain"); %>

<%-- forward to another active page, like a servlet or another jsp. NOTE: path relative to current JSP file! --%>
<jsp:forward page="../page" />


<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		ciao, <%= new java.util.Date() %>
		
		<%-- PRINT A TABLE WITH SOME DATA CONTAINED IN A BEAN --%>
		<%-- <table>
				<tr>
					<td><p>id</p></td>
					<td><p>nome</p></td>
					<td><p>cognome</p></td>
				</tr>
				<%
				User[] usersArr = users.toArray(new PersonalizedItem[0]);
				for( User u : usersArr ){
				%> 
				<tr>
					<td><%= u.getID() %></td>
					<td><%= u.getName() %></td>
					<td><%= u.getSurname() %></td>
				</tr>
			<% } %>
			</table> --%>
		
	</body>
</html>