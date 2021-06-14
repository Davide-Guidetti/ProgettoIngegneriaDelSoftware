package it.unibo.tw.web.examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	PrintWriter out = response.getWriter();
    	
    	HttpSession session=request.getSession();
    	session.setMaxInactiveInterval(60*10); //seconds 
    	
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
    	
    	out.println("<!DOCTYPE html>\r\n"
    			+ "<html>\r\n"
    			+ "	<head>\r\n"
    			+ "		<meta charset=\"ISO-8859-1\">\r\n"
    			+ "		<title>Insert title here</title>\r\n"
    			+ "		<script type=\"text/javascript\" src=\"http://localhost:8080/MyTemplate/scripts/jquery-v3.5.1.min.js\"></script>\r\n"
    			+ "		<script type=\"text/javascript\" src=\"http://localhost:8080/MyTemplate/scripts/ajaxScriptJson.js\"></script>\r\n"
    			+ "		<link rel=\"stylesheet\" href=\"http://localhost:8080/MyTemplate/styles/default.css\" type=\"text/css\"/>\r\n"
    			+ "		<style>\r\n"
    			+ "			.loginForm form {\r\n"
    			+ "				border: 3px solid #f1f1f1;\r\n"
    			+ "				font-family: Arial, Helvetica, sans-serif;\r\n"
    			+ "			}\r\n"
    			+ "			.loginForm input[type=text], .loginForm input[type=password] {\r\n"
    			+ "				width: 50%;\r\n"
    			+ "				padding: 12px 20px;\r\n"
    			+ "				margin: 8px 0;\r\n"
    			+ "				display: inline-block;\r\n"
    			+ "				border: 1px solid #ccc;\r\n"
    			+ "				box-sizing: border-box;\r\n"
    			+ "			}\r\n"
    			+ "			.loginForm input[type=submit] {\r\n"
    			+ "				background-color: #4CAF50;\r\n"
    			+ "				color: white;\r\n"
    			+ "				padding: 14px 20px;\r\n"
    			+ "				margin: 8px 0;\r\n"
    			+ "				border: none;\r\n"
    			+ "				cursor: pointer;\r\n"
    			+ "				width: 50%;\r\n"
    			+ "			}\r\n"
    			+ "			.loginForm input[type=submit]:hover {\r\n"
    			+ "				opacity: 0.8;\r\n"
    			+ "			}\r\n"
    			+ "			.loginForm .container {\r\n"
    			+ "				padding: 16px;\r\n"
    			+ "				text-align: center;\r\n"
    			+ "			}\r\n"
    			+ "	</style>\r\n"
    			+ "	</head>\r\n"
    			+ "	<body>");
    	if(isLogged) {
    		out.println("<script type=\"text/javascript\">\r\n"
    				+ "		\r\n"
    				+ "			$(document).ready(function(){\r\n"
    				+ "				requestJSON();\r\n"
    				+ "			})\r\n"
    				+ "			\r\n"
    				+ "			function parseJSONObj(Obj, Element){\r\n"
    				+ "				if(Obj == \"Unhauthorized\") {location.reload(); return;} //session expired\r\n"
    				+ "				// predisposizione variabile stringa da restituire\r\n"
    				+ "				risultato = \"<table style=\\\"text-align:center\\\">\"\r\n"
    				+ "				risultato += \"<tr><td>ID sessione:</td> <td>Data creazione sessione:</td> <td>Data utima attivit�:</td> <td>Attivit� recente?:</td> <td>Coppie chiave valore:</td><td></td></tr>\";\r\n"
    				+ "\r\n"
    				+ "				// ciclo di decodifica degli elementi e scrittura codice HTML\r\n"
    				+ "				for ( var a=0, b=Obj.length;  a<b;  a++ ) {\r\n"
    				+ "					risultato += '<tr>';\r\n"
    				+ "					risultato += '<td>' + Obj[a].ID +'</td>';\r\n"
    				+ "					risultato += '<td>' + Obj[a].creationTime +'</td>';\r\n"
    				+ "					risultato += '<td>' + Obj[a].lastActivityTime +'</td>';\r\n"
    				+ "					risultato += '<td>' + Obj[a].isRecent +'</td>';\r\n"
    				+ "					risultato += '<td style=\"text-align: left;\"><ul>';\r\n"
    				+ "					var couples = Obj[a].Values; //matrice: su ogni riga ci sono coppie chiave valore\r\n"
    				+ "					for(var i=0; i<couples.length; i++){\r\n"
    				+ "						risultato += '<li>' + couples[i][0] +': ' + couples[i][1] +'</li>';\r\n"
    				+ "					}\r\n"
    				+ "					risultato += '</ul></td>';\r\n"
    				+ "					risultato += '<td><button onclick=\"action(' + Obj[a].ID + ')\">Action</button></td>';\r\n"
    				+ "					risultato += '</tr>';\r\n"
    				+ "				}\r\n"
    				+ "				risultato += \"</table>\";\r\n"
    				+ "				\r\n"
    				+ "				// restituzione dell'html da aggiungere alla pagina\r\n"
    				+ "				return risultato;\r\n"
    				+ "				//return null;\r\n"
    				+ "			}\r\n"
    				+ "		\r\n");
    		
    			
    		out.println("   function requestJSON(){\r\n"
    				+ "				 funzionePostJSON(\r\n"
    				+ "						/*url*/'http://localhost:8080/MyTemplate/adminuserssessionsjsonservlet', \r\n"
    				+ "						/*parametri*/ 'user=admin&password=admin',\r\n"
    				+ "						/*elemento DOM*/ $('#result')[0],\r\n"
    				+ "						/*funzione parsing*/ parseJSONObj\r\n"
    				+ "				);\r\n"
    				+ "			}");
    			
    				out.println(""
    				+ "			function action(ID){\r\n"
    				+ "				console.log(\"not implemented\");\r\n"
    				+ "				/*semplice richiesta ajax\r\n"
    				+ "					$.ajax({\r\n"
    				+ "						url: \"http://__MyUrl__\", \r\n"
    				+ "						success: function(result){\r\n"
    				+ "							//occhio che da qui non si ha accesso a variabili globali\r\n"
    				+ "						}\r\n"
    				+ "					});\r\n"
    				+ "				*/\r\n"
    				+ "			}\r\n"
    				+ "		\r\n"
    				+ "		</script>");
    		out.println("		\r\n"
    				+ "			<div id=\"result\"></div>\r\n"
    				+ "			\r\n"
    				+ "			<button onclick=\"requestJSON()\">Update</button>");
    		
    	}else {
    		out.println("<form action=\"\" method=\"post\" class=\"loginForm\">\r\n"
    				+ "				<div class=\"container\">\r\n"
    				+ "					<h2>Admin Login:</h2>\r\n"
    				+ "					<label for=\"uname\"><b>Username</b></label><br>\r\n"
    				+ "					<input type=\"text\" placeholder=\"Enter Username\" name=\"user\" required><br>\r\n"
    				+ "			\r\n"
    				+ "					<label for=\"psw\"><b>Password</b></label><br>\r\n"
    				+ "					<input type=\"password\" placeholder=\"Enter Password\" name=\"password\" required><br>\r\n"
    				+ "			 		\r\n"
    				+ "					<input type=\"submit\" value=\"Login\">\r\n"
    				+ "				</div>\r\n"
    				+ "			</form>");
    	}
    	
    	out.println("</body>\r\n"
    			+ "</html>");

    }

}
