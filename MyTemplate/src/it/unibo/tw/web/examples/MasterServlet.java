package it.unibo.tw.web.examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
		// utile funzione in caso si debba recuperare delle informazioni da delle beans
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			System.out.println("sono la servlet master, ora invoco i thread");

			CopyOnWriteArrayList<String> provaArray = new <String>CopyOnWriteArrayList();
			int nSchiavi = 10;
			Slave threads[] = new Slave[nSchiavi];

			for (int i = 0; i < nSchiavi; i++) {

				threads[i] = new Slave(provaArray);
				threads[i].start();
			}
			for (Slave s : threads)
				s.join();
			
			for(String s : provaArray)System.out.println(s);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} // service

	}
}
