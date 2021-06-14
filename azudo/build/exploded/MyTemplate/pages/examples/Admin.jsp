<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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
	final String PASSWORD = "admin";
	final String USER = "admin";
	
	boolean isLogged = false;
	String user = request.getParameter("user");
	String password = request.getParameter("password");
	if(user != null && password != null && user.equals(USER) && password.equals(PASSWORD)){
		session.setAttribute("isLogged", Boolean.valueOf(true));
		isLogged=true;
	}else{
		Boolean b = (Boolean)session.getAttribute("isLogged");
		if(b != null && b.booleanValue()==true) isLogged=true;
	}
%>



<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<script type="text/javascript" src="http://localhost:8080/MyTemplate/scripts/jquery-v3.5.1.min.js"></script>
		<script type="text/javascript" src="http://localhost:8080/MyTemplate/scripts/ajaxScriptJson.js"></script>
		<link rel="stylesheet" href="http://localhost:8080/MyTemplate/styles/default.css" type="text/css"/>
		<style>
			.loginForm form {
				border: 3px solid #f1f1f1;
				font-family: Arial, Helvetica, sans-serif;
			}
			.loginForm input[type=text], .loginForm input[type=password] {
				width: 50%;
				padding: 12px 20px;
				margin: 8px 0;
				display: inline-block;
				border: 1px solid #ccc;
				box-sizing: border-box;
			}
			.loginForm input[type=submit] {
				background-color: #4CAF50;
				color: white;
				padding: 14px 20px;
				margin: 8px 0;
				border: none;
				cursor: pointer;
				width: 50%;
			}
			.loginForm input[type=submit]:hover {
				opacity: 0.8;
			}
			.loginForm .container {
				padding: 16px;
				text-align: center;
			}
	</style>
	</head>
	<body>
		<%if(isLogged){%>
		<script type="text/javascript">
		
			$(document).ready(function(){
				requestJSON();
			})
			
			function parseJSONObj(Obj, Element){
				if(Obj == "Unhauthorized") {location.reload(); return;} //session expired
				// predisposizione variabile stringa da restituire
				risultato = "<table style=\"text-align:center\">"
				risultato += "<tr><td>ID sessione:</td> <td>Data creazione sessione:</td> <td>Data utima attività:</td> <td>Attività recente?:</td> <td>Coppie chiave valore:</td><td></td></tr>";

				// ciclo di decodifica degli elementi e scrittura codice HTML
				for ( var a=0, b=Obj.length;  a<b;  a++ ) {
					risultato += '<tr>';
					risultato += '<td>' + Obj[a].ID +'</td>';
					risultato += '<td>' + Obj[a].creationTime +'</td>';
					risultato += '<td>' + Obj[a].lastActivityTime +'</td>';
					risultato += '<td>' + Obj[a].isRecent +'</td>';
					risultato += '<td style="text-align: left;"><ul>';
					var couples = Obj[a].Values; //matrice: su ogni riga ci sono coppie chiave valore
					for(var i=0; i<couples.length; i++){
						risultato += '<li>' + couples[i][0] +': ' + couples[i][1] +'</li>';
					}
					risultato += '</ul></td>';
					risultato += '<td><button onclick="action(' + Obj[a].ID + ')">Action</button></td>';
					risultato += '</tr>';
				}
				risultato += "</table>";
				
				// restituzione dell'html da aggiungere alla pagina
				return risultato;
				//return null;
			}
		
			function requestJSON(){
				 funzionePostJSON(
						/*url*/'http://localhost:8080/MyTemplate/adminuserssessionsjsonservlet', 
						/*parametri*/ <%--'user='+<%=USER%>+'&password'+<%=PASSWORD%>--%>'',
						/*elemento DOM*/ $('#result')[0],
						/*funzione parsing*/ parseJSONObj
				);
			}
			
			function action(ID){
				console.log("not implemented");
				/*semplice richiesta ajax
					$.ajax({
						url: "http://__MyUrl__", 
						success: function(result){
							//occhio che da qui non si ha accesso a variabili globali
						}
					});
				*/
			}
		
		</script>
		
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
			
			
			
			<div id="result"></div>
			
			<button onclick="requestJSON()">Update</button>
			
		<%}else{ %>
			<form action="" method="post" class="loginForm">
				<div class="container">
					<h2>Admin Login:</h2>
					<label for="uname"><b>Username</b></label><br>
					<input type="text" placeholder="Enter Username" name="user" required><br>
			
					<label for="psw"><b>Password</b></label><br>
					<input type="password" placeholder="Enter Password" name="password" required><br>
			 		
					<input type="submit" value="Login">
				</div>
			</form>
		<%}%>
	</body>
</html>