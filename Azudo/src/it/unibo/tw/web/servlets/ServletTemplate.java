package it.unibo.tw.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTemplate extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/* INIZIALIZZAZIONE E PARAMETRI SERVERLET
	private int n;
	private int N;
	private String param;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		n = Integer.parseInt(config.getInitParameter("n"));
		N = Integer.parseInt(config.getInitParameter("N"));
		param = "";
		Enumeration<String> e = config.getInitParameterNames();
		while(e.hasMoreElements())param += e.nextElement() + " ";
	}*/
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		
		// some delay... (as it usually happens in complex Web apps)
		try {
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//set valid session timeS
		request.getSession().setMaxInactiveInterval(60*10); //seconds
		
		// retrieve former values, if any
		String name = request.getParameter("name");
		if ( name == null ) {
			out.println("parameter error");
			return;
		}
		
		//cookies
		String something = request.getParameter("something");
		if ( something == null ) something = "";
		Cookie cookie = new Cookie("something",something);// store the value as a cookie
		response.addCookie(cookie);
		
		//cycle through cookies
		String something2 = null;
		Cookie[] cookies = request.getCookies();
		if ( cookies != null && cookies.length > 0 ) {
			for ( Cookie cookie2 : cookies ) {
				if ( cookie2.getName().equals("something") ) {
					something2 = cookie2.getValue();
					break;
				}
			}
		}
		
		// store and retrieve the value as a session attribute
		request.getSession().setAttribute("something", something );
		String something3 = (String) request.getSession().getAttribute("something");

		//sample page output
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Hello Wolrd Servlet</title>");
		out.println("<link rel=\"stylesheet\" href=\"styles/default.css\" type=\"text/css\"></link>");
		out.println("</head>");
		out.println("<body>");
		out.println("This is the servlet output!<br/><br/>");
		out.println("<br/>");
		out.println("<hr/>");
		out.println("<br/>");
		out.println("</body>");
		out.println("</html>");
			
		//throw some error
		throw new ServletException("This servlet can only be reached via an HTTP GET REQUEST");

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
	}

}
