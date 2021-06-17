package it.unibo.tw.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.azudo.model.volontario.Competenza;
import it.azudo.DBObjects.DBConnection;
import it.azudo.model.volontario.VolontarioApprovato;
import it.unibo.tw.web.beans.CompetenzaCheck;

public class ServletController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private List<Competenza> competenze;
	private DBConnection DB;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		/*
		 * Il collegamento al DB avviene in automatico alla creazione della servlet, in
		 * modo da non doverlo rifare a ogni richiesta
		 */
		DB = DBConnection.getDBConnection();
		competenze = DB.getCompetenze();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		competenze = DB.getCompetenze();// Aggiorno le competenze, in caso siano cambiate dall'ultima volta

		// istanza GSON
		Gson g = new Gson();

		// restituzione competenza volontario
		String email = request.getParameter("email");

		if (email != null) {
			String listCompetenze = request.getParameter("listCompetenze");
			if (listCompetenze != null) {
				// salvataggio nel volontario delle competenze checked
				System.out.println(email + listCompetenze);
				String competenzeAggiornate[] = g.fromJson(listCompetenze, String[].class);
				List<it.azudo.model.volontario.Competenza> newCompetenze = new ArrayList<Competenza>();
				for (int i = 0; i < competenzeAggiornate.length; i++) {
					newCompetenze.add(new Competenza(competenzeAggiornate[i]));

				}

				DB.setCompetenzeVolotnario(email, newCompetenze);

			} else {
				List<it.azudo.model.volontario.Competenza> comp = DB.getCompetenzeVolotnario(email);
				List<it.unibo.tw.web.beans.CompetenzaCheck> check = new ArrayList<>();
				int controllo;
				for (Competenza c : competenze) {
					controllo = 0;
					for (Competenza cc : comp) {

						if (c.getNome().equals(cc.getNome())) {

							check.add(new CompetenzaCheck(c.nomeCompetenza(), "true"));
							controllo = 1;
						}
					}
					if (controllo == 0) {
						check.add(new CompetenzaCheck(c.nomeCompetenza(), "false"));

					}
				}

				out.write(g.toJson(check.toArray()));
			}
		} else {
			// prendi la stringa con il comitato al suo interno, se vuota vuol dire che devo
			// restituire le competenze
			String Comitato = request.getParameter("comitato");
			if (Comitato == null && email == null)// restituisco array competenze
			{
				out.write(g.toJson(competenze.toArray()));
			} else {
				String JSONStr = request.getParameter("listCompetenze");
				System.out.println(JSONStr);
				String competStr[] = g.fromJson(JSONStr, String[].class);
				List<Competenza> comp = new ArrayList<Competenza>();
				for (int i = 0; i < competStr.length; i++)
					comp.add(new Competenza(competStr[i]));

				for (Competenza c : comp)
					System.out.println(c.nomeCompetenza());

				List<VolontarioApprovato> volAp = DB.getVolontariComitato(Comitato);
				List<VolontarioApprovato> volontariCompatibili = new ArrayList<>();

				for (int i = 0; i < volAp.size(); i++) // ciclo tutti i volontari del comitato
				{
					if (volAp.get(i).hasCompetences(comp))// caso positivo, il volontario possiede tutte le competenze
															// richieste, lo restituisco
					{
						volontariCompatibili.add(volAp.get(i));
					}
				} // fine controllo

				if (volontariCompatibili.size() != 0) {
					out.write(g.toJson(volontariCompatibili.toArray()));
				}

			}
		}
	}

}
