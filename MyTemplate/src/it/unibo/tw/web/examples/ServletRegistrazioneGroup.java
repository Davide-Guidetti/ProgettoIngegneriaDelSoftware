package it.unibo.tw.web.examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unibo.tw.web.beans.ListUserGroup;

public class ServletRegistrazioneGroup extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ListUserGroup listUser;

	
	@Override
	public void init(ServletConfig conf) throws ServletException {
	 	super.init(conf);
		listUser=new ListUserGroup();
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
			// System.out.println("CACCAAA" + request.getParameter("ogg1") +
			// ses.getCreationTime());

			String nome = request.getParameter("nome");
			String password = request.getParameter("password");
			String group = request.getParameter("group");
			

			if (nome.equals("") || password.equals("")) {
				out.println("Errore inserimento dati!!");
				out.println("<form action='servletregistrazionegroup'>");
				out.println("inserisci i dati per la registrazione <br> ");
				out.println("<label for='textFieldID'>Nome:</label>");
				out.println("<input type='text' id='textFieldID' name='nome'><br>");
				out.println("<label for='pswdFieldID'>Password:</label>");
				out.println("<input type='password' id='pswdFieldID' name='password'><br>");
				out.println("<label for='groupFieldID'>Group:</label>");
				out.println("<input type='text' id='groupFieldID' name='group'><br>");
				out.println("<input type='submit' name='button' value='registrati'>");
				out.println("</form>");
				out.println("<br/>");
				out.println("</body>");
				out.println("</html>");
			} else {
				//inserimento nome.password nel server 

				listUser=(ListUserGroup)this.getServletContext().getAttribute("ListUser");
				listUser.add(nome, password,group);
				this.getServletContext().setAttribute("ListUser",listUser);
				System.out.println("inserimento nel list user di:");
				System.out.println("nome: "+nome + " password: "+ password+ " group: "+group);
				
				
				// Eventuale inserimento in un bean/Db con scope application
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/servletlogingroup");
				dispatcher.forward(request, response);
			}
		} else {

			out.println("<form action='servletregistrazionegroup'>");
			out.println("inserisci i dati per la registrazione <br>");
			out.println("<label for='textFieldID'>Nome:</label>");
			out.println("<input type='text' id='textFieldID' name='nome'><br>");
			out.println("<label for='pswdFieldID'>Password:</label>");
			out.println("<input type='password' id='pswdFieldID' name='password'><br>");
			out.println("<label for='groupFieldID'>Group:</label>");
			out.println("<input type='text' id='groupFieldID' name='group'><br>");
			out.println("<input type='submit' name='button' value='registrati'>");
			out.println("</form>");
			out.println("<br/>");
			out.println("</body>");
			out.println("</html>");

		}

	}// service

}
