package it.azudo.DBObjects;

import java.util.ArrayList;
import java.util.List;

import it.azudo.model.volontario.Comitato;
import it.azudo.model.volontario.Competenza;
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
		
		volontario.add(new Volontario("Bologna", "giorgio.mocci@studio.unibo.it", "fdjahsbgdjsa", "Giorgio", "Mocci", "3474516549", true, true));
		possiede.add(new Possiede("Guidatore ambulanza", "giorgio.mocci@studio.unibo.it"));
		possiede.add(new Possiede("Vigile del fuoco", "giorgio.mocci@studio.unibo.it"));
		
		volontario.add(new Volontario("Bologna", "loris.giannatempo@studio.unibo.it", "afdegwerhbr", "Loris", "Giannatempo", "3474324521", false, true));
		possiede.add(new Possiede("Vigile del fuoco", "loris.giannatempo@studio.unibo.it"));
		
		volontario.add(new Volontario("Bologna", "davide.guidetti@studio.unibo.it", "fdasgasdtr", "Davide", "Guidetti", "3475417235", false, true));
		possiede.add(new Possiede("Sorveglianza pubblica", "davide.guidetti@studio.unibo.it"));
		
		volontario.add(new Volontario("Bologna", "sconosciuto.boh@libero.it", "ahgeafgasa", "sconosciuto", "boh", "347432125", false, false));
		possiede.add(new Possiede("Vigile del fuoco", "sconosciuto.boh@libero.it"));
		possiede.add(new Possiede("Sorveglianza pubblica", "sconosciuto.boh@libero.it"));
	}
	
	//return the system registered competences
	public List<it.azudo.model.volontario.Competenza> getCompetenze() {
		List<it.azudo.model.volontario.Competenza> res = new ArrayList<>();
		for (Competenza c : competenze) { 
			res.add(new it.azudo.model.volontario.Competenza(c.getNome())); 
		}
		return res;
	}
	
	public Volontario getVolontario(String email) {
		for (Volontario v : volontario) { 
			if (v.getEmail().equals(email)) {
				return v;
			}
		}
		return null;
	}
	
	//restituisce i volotari approvati (con anche le competenze possedute) 
	public List<VolontarioApprovato> getVolontariApprovatiComitato(String comitato) {
		List<VolontarioApprovato> volontari = new ArrayList<>();
		for(Volontario v : volontario) {
			if(v.IsApprovato && v.Comitato.equals(comitato)) {
				volontari.add(new VolontarioApprovato(v.Nome, v.Cognome, v.EMail, v.NumeroTelefono, getCompetenzeVolotnario(v.EMail), new Comitato(v.Comitato), v.IsCoordinatore));
			}
		}
		return volontari;
	}
	
	//restituisce i volontari approvati o in attesa di approvazione di un comitato non coordinatori
	public List<Volontario> getVolontariComitato(String comitato) {
		List<Volontario> volontari = new ArrayList<>();
		for(Volontario v : volontario) {
			if(v.Comitato.equals(comitato)  && !v.IsCoordinatore) {
				volontari.add(v);
			}
		}
		return volontari;
	}
	
	public List<it.azudo.model.volontario.Competenza> getCompetenzeVolotnario(String EMail) {
		List<it.azudo.model.volontario.Competenza> competenze = new ArrayList<>();
		for(Possiede rowPossiede: possiede) {
			if(rowPossiede.EMail.equals(EMail)) {
				
				competenze.add(new it.azudo.model.volontario.Competenza(rowPossiede.nomeCompetenza));
			}
		}
		
		return competenze;
	}
	
	public void removePossiede(String[] competenzeSistema ) {
		List<String> competenzeEliminate=new ArrayList<>();
		int controllo;
		for (Competenza c: competenze) {
			controllo=0;
			for (String s: competenzeSistema) {
				if (c.getNome().equalsIgnoreCase(s)) {
					controllo=1;
				}
			}
			if (controllo==0) {
				competenzeEliminate.add(c.getNome());
			}
		}
		
		//
		System.out.println(competenzeEliminate);
		
		List<Possiede> newPossiede=new ArrayList<>();
		for(Possiede rowPossiede: possiede) {
			controllo=0;
			for (String s: competenzeEliminate) {
				if(rowPossiede.nomeCompetenza.equals(s)) {
					controllo=1;
				}
			}
			if (controllo==0) {
				newPossiede.add(rowPossiede);
			}
		}
		possiede=newPossiede;
		
	    List<Competenza> newCompetenze=new ArrayList<>();
	    
	    for (String s: competenzeSistema) {
	    	newCompetenze.add(new Competenza(s));
	    }
	    competenze=newCompetenze;
	    
	    
	}
	
	//rimpiazza le vecchie competenze del volontario con quelle nuove
	public void setCompetenzeVolotnario(String EMail, List<it.azudo.model.volontario.Competenza> newCompetenze) {
		//Elimina le comptenteze del volontario
		List<Possiede> possiedeTmp = new ArrayList<>(possiede);
		for(Possiede rowPossiede : possiedeTmp) {
			
			if(rowPossiede.EMail.equals(EMail)) {				
				possiede.remove(rowPossiede);
			}
		}
		//poi aggiungi tutte quelle nuove
		for(it.azudo.model.volontario.Competenza competenza : newCompetenze) {
			
			possiede.add(new Possiede(competenza.nomeCompetenza(), EMail));
		}
	}
}
