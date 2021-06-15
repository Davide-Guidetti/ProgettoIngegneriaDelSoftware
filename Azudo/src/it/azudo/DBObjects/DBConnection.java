package it.azudo.DBObjects;

import java.util.ArrayList;
import java.util.List;

import it.azudo.model.volontario.Comitato;
import it.azudo.model.volontario.VolontarioApprovato;

public class DBConnection {
	
	private static DBConnection DBConnectionInstance = null;
	
	public static DBConnection getDBConnection() {
		if(DBConnectionInstance == null) DBConnectionInstance = new DBConnection();
		return DBConnectionInstance;
	}
	
	protected DBConnection() {
		initDB();
	}
	
	public List<Competenza> competenze;
	public List<Volontario> volontario;
	public List<Possiede> possiede; //associazione volontario-competenza
	
	private void initDB() {
		competenze = new ArrayList<>();
		volontario = new ArrayList<>();
		possiede = new ArrayList<>();
		
		competenze.add(new Competenza("Guidatore ambulanza"));
		competenze.add(new Competenza("Sorveglianza pubblica"));
		competenze.add(new Competenza("Vigile del fuoco"));
		
		volontario.add(new Volontario("Bosa", "giorgio.mocci@studio.unibo.it", "fdjahsbgdjsa", "Giorgio", "Mocci", "3474516549", false, true));
		possiede.add(new Possiede("Guidatore ambulanza", "giorgio.mocci@studio.unibo.it"));
		possiede.add(new Possiede("Vigile del fuoco", "giorgio.mocci@studio.unibo.it"));
		
		volontario.add(new Volontario("Bosa", "loris.giannatempo@studio.unibo.it", "afdegwerhbr", "Loris", "Giannatempo", "3474324521", false, true));
		possiede.add(new Possiede("Vigile del fuoco", "loris.giannatempo@studio.unibo.it"));
		
		volontario.add(new Volontario("Bosa", "davide.guidetti@studio.unibo.it", "fdasgasdtr", "Davide", "Guidetti", "3475417235", true, true));
		possiede.add(new Possiede("Sorveglianza pubblica", "davide.guidetti@studio.unibo.it"));
		
		volontario.add(new Volontario("Boh", "sconosciuto.boh@libero.it", "ahgeafgasa", "sconosciuto", "boh", "347432125", false, true));
		possiede.add(new Possiede("Vigile del fuoco", "sconosciuto.boh@libero.it"));
		possiede.add(new Possiede("Sorveglianza pubblica", "sconosciuto.boh@libero.it"));
	}
	
	//retrun the system registered competences
	public List<Competenza> getCompetenze() {
		return competenze;
	}
	
	//restituisce i volotari approvati (con anche le competenze possedute)
	public List<VolontarioApprovato> getVolontariComitato(String comitato) {
		List<VolontarioApprovato> volontari = new ArrayList<>();
		for(Volontario v : volontario) {
			if(v.IsApprovato && v.Comitato.equals(comitato)) {
				volontari.add(new VolontarioApprovato(v.Nome, v.Cognome, v.EMail, v.NumeroTelefono, getCompetenzeVolotnario(v.EMail), new Comitato(v.Comitato), v.IsCoordinatore));
			}
		}
		return volontari;
	}
	
	private List<it.azudo.model.volontario.Competenza> getCompetenzeVolotnario(String EMail) {
		List<it.azudo.model.volontario.Competenza> competenze = new ArrayList<>();
		for(Possiede rowPossiede: possiede) {
			if(rowPossiede.EMail.equals(EMail)) {
				competenze.add(new it.azudo.model.volontario.Competenza(rowPossiede.nomeCompetenza));
			}
		}
		return competenze;
	}
}
