package it.unibo.tw.web.examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/*
    Esempio di utilizzo di questa servlet tramite chiamata ajax JS
  	Se voglio mettere un timer nel body : <body onload=\"setInterval(onTimerElapsed,3000);\">
 
	FUNZIONE JS PER CHIAMARE LA SERVLET
	
	<script type="text/javascript\">

  function onTimerElapsed() {
    console.log("timer scaduto!!");
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var tempoAttivita = this.responseText;
        console.log("minuti passati " + tempoAttivita);
      }
    }
    xhttp.open("GET", "controllotemposessione", true);
    xhttp.send();
  }  
  
</script>



 */


public class ControlloTempoSessione extends HttpServlet {

	private static final long serialVersionUID = 1L;

	

	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession ses = request.getSession();
		Date creaz = new Date(ses.getCreationTime());
		Date ora = new Date();
		
		int diffInMillies = (int) Math.abs(ora.getTime() - creaz.getTime());
		int diffInSecond = diffInMillies/1000;
		int diffInMinutes = diffInSecond /60;
		
		System.out.println(diffInSecond);
		
		
		Integer tentativo = (Integer) ses.getAttribute("tentativo");
		
		if (tentativo == null) {
			System.out.println("prima richiesta per questo utente");
			tentativo = 0;
			tentativo++;
			ses.setAttribute("tentativo", tentativo);

		}
		else
		{
			System.out.println("NON e la prima richiesta per questo utente, anzi e al suo "+tentativo+" tentativo");
		}
		
		out.write("La tua sessione e attiva da "+diffInSecond+ " secondi e sei al tuo "+tentativo+" tentativo");
		
		
		
		
		
		
		
	
	}//service

}
