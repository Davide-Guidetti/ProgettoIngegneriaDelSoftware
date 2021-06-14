package it.unibo.tw.web.examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unibo.tw.web.examples.AdminUsersSessionsJSONObject;
import it.unibo.tw.web.examples.MySessionCounter;


public class AdminUsersSessionsJSONServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		
		boolean isLogged = false;
		Boolean b = (Boolean)request.getSession().getAttribute("isLogged");
		if(b != null && b.booleanValue()==true) isLogged=true;
		if(!isLogged) {
			out.print(g.toJson("Unhauthorized"));
			//response.setStatus(401);
			return;
		}
		
		response.setContentType("application/json");
		
		AdminUsersSessionsJSONObject[] result = MySessionCounter.getUserSessions();
		
		out.print(g.toJson(result));
	}
	



}
