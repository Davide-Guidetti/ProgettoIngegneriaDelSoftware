package it.unibo.tw.web.examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unibo.tw.web.beans.ListUser;
import it.unibo.tw.web.beans.ListUserGroup;

//Medesima struttura di ServletLogin con aggiunta sistema di scadenza password

public class ServletLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ListUser listUser;
	
	@Override
	public void init() {
		// utile funzione in caso si debba recuperare delle informazioni da delle beans
		
		if (this.getServletContext().getAttribute("ListUser")==null) {
			listUser=new ListUser();
			listUser.add("Giorgio", "Mocci");
			listUser.add("Davide", "Guidetti");
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

			System.out.println("Inserimento pre controllo logg : nome: "+nome + " password: "+ password);
			
			
			//Oss. ListUser e User presentano metodi per la gestione di Scadenze temporali della password!!
			
			
			listUser=(ListUser)this.getServletContext().getAttribute("ListUser");
			
			
			
			if(listUser.isLoginValid(nome, password)==false )  {
				out.println("Errore inserimento dati!!");
				out.println("inserisci i dati per il login  <br> ");
				out.println("<form action='servletlogin'>");
				out.println("<label for='textFieldID'>Nome:</label>");
				out.println("<input type='text' id='textFieldID' name='nomeL'><br>");
				out.println("<label for='pswdFieldID'>Password:</label>");
				out.println("<input type='password' id='pswdFieldID' name='passwordL'><br>");
				out.println("<input type='submit' name='button' value='loggati'>");
				out.println("</form>");
				out.println("<br/>");
				out.println("</body>");
				out.println("</html>");
			} else {
				// inserimento nome.password nel server
				System.out.println("CORRETTO! \nnome: " + nome + " password: " + password);
							
				// Eventuale reindirizzamento alla homePage del sito
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/examples/JspAutentificazione.jsp");
				request.getSession().setAttribute("logged", Boolean.valueOf(true));
				dispatcher.forward(request, response);
			}
		} else {

			out.println("<form action='servletlogin'>");
			out.println("inserisci i dati per il login  <br>");
			out.println("<label for='textFieldID'>Nome:</label>");
			out.println("<input type='text' id='textFieldID' name='nomeL'><br>");
			out.println("<label for='pswdFieldID'>Password:</label>");
			out.println("<input type='password' id='pswdFieldID' name='passwordL'><br>");
			out.println("<input type='submit' name='button' value='loggati'>");
			out.println("</form>");
			out.println("<br/>");
			out.println("</body>");
			out.println("</html>");

		}

	}// service

}