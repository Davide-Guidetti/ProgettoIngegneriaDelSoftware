package it.unibo.tw.web.examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unibo.tw.web.beans.ListUserGroup;
import it.unibo.tw.web.beans.UserGroup;

public class ServletLoginGroup extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ListUserGroup listUser;
	
	@Override
	public void init() {
		// utile funzione in caso si debba recuperare delle informazioni da delle beans
		
		if (this.getServletContext().getAttribute("ListUser")==null) {
			listUser=new ListUserGroup();
			listUser.add("Giorgio", "Mocci", "1");
			listUser.add("Davide", "Guidetti", "1");
			this.getServletContext().setAttribute("ListUser", listUser);
		}
		
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Hello Wolrd Servlet</title>");
		out.println("</head>");
		out.println("<body>");

		String button = request.getParameter("button");

		if ("loggati".equals(button)) {

			String nome = request.getParameter("nomeL");
			String password = request.getParameter("passwordL");
			String group = request.getParameter("groupL");

			System.out.println("Inserimento pre controllo logg : nome: "+nome + " password: "+ password);
			
			
			listUser=(ListUserGroup)this.getServletContext().getAttribute("ListUser");	
			
			
			if(listUser.isLoginValid(nome, password)==false )  {
				out.println("Errore inserimento dati!!");
				out.println("inserisci i dati per il login  <br> ");
				out.println("<form action='servletlogingroup'>");
				out.println("<label for='textFieldID'>Nome:</label>");
				out.println("<input type='text' id='textFieldID' name='nomeL'><br>");
				out.println("<label for='pswdFieldID'>Password:</label>");
				out.println("<input type='password' id='pswdFieldID' name='passwordL'><br>");
				out.println("<label for='groupFieldID'>Group:</label>");
				out.println("<input type='text' id='groupFieldID' name='groupL'><br>");
				out.println("<input type='submit' name='button' value='loggati'>");
				out.println("</form>");
				out.println("<br/>");
				out.println("</body>");
				out.println("</html>");
			} else {
				// inserimento nome.password nel server
				System.out.println("CORRETTO! \nnome: " + nome + " password: " + password+ " gruppo: "+group);
				
				System.out.println("Gruppo 1 user: ");
				for (UserGroup u:listUser.getUserGroup("1")) {
					System.out.println("nome: " + u.name +" gruppo: "+u.group);
				}
				
				// Eventuale reindirizzamento alla homePage del sito
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/examples/JspAutentificazione.jsp");
				request.getSession().setAttribute("logged", Boolean.valueOf(true));
				dispatcher.forward(request, response);
			}
		} else {

			out.println("<form action='servletlogingroup'>");
			out.println("inserisci i dati per il login  <br>");
			out.println("<label for='textFieldID'>Nome:</label>");
			out.println("<input type='text' id='textFieldID' name='nomeL'><br>");
			out.println("<label for='pswdFieldID'>Password:</label>");
			out.println("<input type='password' id='pswdFieldID' name='passwordL'><br>");
			out.println("<label for='groupFieldID'>Group:</label>");
			out.println("<input type='text' id='groupFieldID' name='groupL'><br>");
			out.println("<input type='submit' name='button' value='loggati'>");
			out.println("</form>");
			out.println("<br/>");
			out.println("</body>");
			out.println("</html>");

		}

	}// service

}