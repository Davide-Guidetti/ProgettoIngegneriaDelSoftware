package it.unibo.tw.web.examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;



public class EchoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	@Override
	public void init() {
		//utile funzione in caso si debba recuperare delle informazioni da delle beans
	}
	
	
	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

		response.setContentType("text/xml;charset=UTF-8");
		String  arrivo = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"; //la risposta in questo cado deve essere di tipo XML
        arrivo += "<oggettiEcho><![CDATA[";
		 arrivo += request.getParameter("echo");		
		 arrivo +=   "]]></oggettiEcho>";
		PrintWriter out = response.getWriter();
		
		out.write(arrivo);
		
		
		
		
		
		
		
	
	}//service

}
