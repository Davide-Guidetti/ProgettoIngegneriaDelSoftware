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


public class JSONServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//istanza GSON
		Gson g = new Gson();
		
		//prendi la stringa con un certo valore al suo interno
		String param = request.getParameter("echo");
		
		//nel caso il parametro debba contenere un json, che sara  codificato in Base64 (per evitare problemi con caratteri speciali che vanno nell'url) bisogna decodificarlo in una normale stringa
		//String JSONStr = request.getParameter("JSON");
		//byte[] decodedBytes = Base64.getDecoder().decode(JSONStr);
		//JSONStr = new String(decodedBytes);
		
		//converti la stringa JSON in un oggetto vero e proprio
		//JSONObject JSONObj = g.fromJson(obj, JSONObject.class);
		//String JSONStr = g.fromJson(param, String.class); //converte una stringa in una stringa. di fatto contenuto rimane inalterato
		
		//rispondiamo con un JSON, quindi settiamo il relativo MIME TYPE
		response.setContentType("application/json");
		
		//construiamo l'oggetto da convertire poi in json e inviare al client
		//in questo caso l'oggetto che inviamo  una semplice stringa
		String obj = param;
		
		//scriviamo l'oggetto in uscita
		PrintWriter out = response.getWriter();
		out.write(g.toJson(obj));
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// recupero il tipo di categoria cercata dai parametri della richiesta

		//prendi la stringa JSON dal parametro
		String JSONStr = request.getParameter("JSON");

		//istanza GSON
		Gson g = new Gson();
		
		//converti la stringa JSON in un oggetto vero e proprio
		JSONObject JSONObj = g.fromJson(JSONStr, JSONObject.class);
		
		//System.out.println(JSONObj +"");
		
		//rispondiamo con un JSON, quindi settiamo il relativo MIME TYPE
		response.setContentType("application/json");
		
		//rispondiamo ad esempio con un array di elementi, con un solo elemento all'interno
		PrintWriter out = response.getWriter();
		JSONObject ObjArr[] = new JSONObject[1];
		ObjArr[0] = JSONObj;
		out.write(g.toJson(ObjArr));
	}
	
	
	
	
	/* //test con passaggio stesso json che prendiamo e rimandiamo in post, ma qui lo facciamo con get
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//istanza GSON
		Gson g = new Gson();
		
		//prendi la stringa con un certo valore al suo interno
		String param = request.getParameter("JSON");
		
		//nel caso il parametro debba contenere un json, che sara codificato in Base64 (per evitare problemi con caratteri speciali che vanno nell'url) bisogna decodificarlo in una normale stringa
		String JSONStr = request.getParameter("JSON");
		byte[] decodedBytes = Base64.getDecoder().decode(JSONStr);
		JSONStr = new String(decodedBytes);
		
		//converti la stringa JSON in un oggetto vero e proprio
		//JSONObject JSONObj = g.fromJson(obj, JSONObject.class);
		//String JSONStr = g.fromJson(param, String.class); //converte una stringa in una stringa. di fatto contenuto rimane inalterato
		
		//converti la stringa JSON in un oggetto vero e proprio
		JSONObject JSONObj = g.fromJson(JSONStr, JSONObject.class);
		
		//rispondiamo con un JSON, quindi settiamo il relativo MIME TYPE
		response.setContentType("application/json");
		
		//construiamo l'oggetto da convertire poi in json e inviare al client
		//in questo caso l'oggetto che inviamo � una semplice stringa
		String obj = param;
		
		//rispondiamo ad esempio con un array di elementi, con un solo elemento all'interno
		PrintWriter out = response.getWriter();
		JSONObject ObjArr[] = new JSONObject[1];
		ObjArr[0] = JSONObj;
		out.write(g.toJson(ObjArr));
	}
	*/
	
	
	/*
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		response.setContentType("application/json");
		
		AdminUsersSessionsJSONObject[] result = MySessionCounter.getUserSessions();
		
		out.print(g.toJson(result));

	}*/

}
