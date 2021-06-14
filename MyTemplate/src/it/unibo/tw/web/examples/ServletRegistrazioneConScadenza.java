package it.unibo.tw.web.examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unibo.tw.web.beans.ListUser;

//servlet che, insieme alla servlet ServletLoginConScadenza gestisce un sistema di autenticaizone basato su Username e Password
//inoltre questo sistema si basa sull'utilizzo di due Beans 
//(uno che gestisce il singolo User mentre il secondo che gestisce la lista di utenti registrati)
//infine, diversamente dalla ServletRegistrazione e ServletLogin quest'ultime gestiscono anche un periodico
//reinserimento di una nuova password


public class ServletRegistrazioneConScadenza extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ListUser listUser;
	
	@Override
	public void init(ServletConfig conf) throws ServletException {
	 	super.init(conf);
		listUser=new ListUser();
		this.getServletContext().setAttribute("ListUser", listUser);
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
		if ("registrati".equals(button)) {
			 System.out.println("ho premuto il bottone in registrazione con scadenza");

			String nome = request.getParameter("nome");
			String password = request.getParameter("password");
			
			
			if (nome.equals("") || password.equals("")) {
				out.println("Errore inserimento dati!!");
				out.println("<form action='servletregistrazioneconscadenza'>");
				out.println("inserisci i dati per la registrazione (con scadenza password) <br> ");
				out.println("<label for='textFieldID'>Nome:</label>");
				out.println("<input type='text' id='textFieldID' name='nome'><br>");
				out.println("<label for='pswdFieldID'>Password:</label>");
				out.println("<input type='password' id='pswdFieldID' name='password'><br>");
				out.println("<input type='submit' name='button' value='registrati'>");
				out.println("</form>");
				out.println("<br/>");
				out.println("</body>");
				out.println("</html>");
			} else {
				
				listUser=(ListUser)this.getServletContext().getAttribute("ListUser");
				listUser.add(nome, password);
				this.getServletContext().setAttribute("ListUser",listUser);
				System.out.println("inserimento nel list user di:");
				System.out.println("nome: "+nome + " password: "+ password);
				
				//inserimento nome.password nel server 
				System.out.println("inserimento nel server con scadenza");
				System.out.println("nome: "+nome + " password: "+ password);
				
				// Eventuale inserimento in un bean/Db con scope application
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/servletloginconscadenza");
				dispatcher.forward(request, response);
			}
		} else {

			out.println("<form action='servletregistrazioneconscadenza'>");
			out.println("inserisci i dati per la registrazione (con scadenza password) <br>");
			out.println("<label for='textFieldID'>Nome:</label>");
			out.println("<input type='text' id='textFieldID' name='nome'><br>");
			out.println("<label for='pswdFieldID'>Password:</label>");
			out.println("<input type='password' id='pswdFieldID' name='password'><br>");
			out.println("<input type='submit' name='button' value='registrati'>");
			out.println("</form>");
			out.println("<br/>");
			out.println("</body>");
			out.println("</html>");

		}

	}// service

}
