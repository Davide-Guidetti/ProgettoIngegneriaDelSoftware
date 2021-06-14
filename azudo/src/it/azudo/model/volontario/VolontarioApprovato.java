package it.azudo.model.volontario;

import java.util.ArrayList;
import java.util.List;

public class VolontarioApprovato extends Volontario {
	
	boolean isCoordinatore;
	List<IscrizionePiano> partecipaAPiani = null;

	//costruttore minimo per un volotario approvato
	public VolontarioApprovato(String nome, String congnome, String eMail, String numeroTelefono, List<Competenza> listaCompetenze, Comitato comitatoAppartenenza, boolean isCoordinatore) {
		super(nome, congnome, eMail, numeroTelefono, listaCompetenze);
		this.isCoordinatore = isCoordinatore;
	}
	
	//costruttore per un volotario approvato con associate le iscrizioni
	public VolontarioApprovato(String nome, String congnome, String eMail, String numeroTelefono, List<Competenza> listaCompetenze, Comitato comitatoAppartenenza, boolean isCoordinatore, List<IscrizionePiano> partecipaAPiani) {
		super(nome, congnome, eMail, numeroTelefono, listaCompetenze);
		this.isCoordinatore = isCoordinatore;
		this.partecipaAPiani = partecipaAPiani;
	}
	
	public List<IscrizionePiano> getIscrizioniPiani() {
		return partecipaAPiani;
	}
	
	public List<Turno> getTurniSottoscritti() {
		List<Turno> turniSottoscritti = new ArrayList<Turno>();
		for(IscrizionePiano iscrizionePiano : partecipaAPiani) {
			turniSottoscritti.addAll(iscrizionePiano.getTurniSottoscritti());
		}
		return turniSottoscritti;
	}
	
	//si suppone che il piano sia compatibile dal punto di vista delle competenze
	//restituisce sia quelli che non sono in conflitto con turni sottoscritti, sia i turni sottoscritti stessi
	public List<Turno> getTurniCompatibili(Piano p) {
		List<Turno> turniCompatibili = new ArrayList<>();
		List<Turno> turniVolontario = this.getTurniSottoscritti();
		for(Turno turnoPiano : p.getTurni()) {
			boolean compatible = true;
			if(!turniVolontario.contains(turnoPiano)) { //se non è un piano che abbiamo esplicitamente sottoscritto, verificiamo che sia compatibile
				for(Turno turnoSottoscritto : turniVolontario) {
					if(turnoSottoscritto.isOverlapped(turnoPiano)) {
						compatible = false;
						break;
					}
				}
			}
			if(compatible) turniCompatibili.add(turnoPiano);
		}
		return turniCompatibili;
	}
	
	//compatibile o con il piano base o con quello speciale.
	//in più deve avere almeno un turno compatibile (ossia non sovrapposto con altri turni sottoscritti)
	public List<Piano> getPianiCompatibili(List<Piano> listaPiani) {
		List<Piano> pianiCompatibili = new ArrayList<>();
		for(Piano piano: listaPiani) {
			if(
					(isPianoCompatibileBase(piano) || isPianoCompatibileSpeciale(piano)) &&
					!getTurniCompatibili(piano).isEmpty()
			) {
				pianiCompatibili.add(piano);
			}
		}
		return pianiCompatibili;
	}
	
	//compatibile se possiede competenze, non controlla sovrapposizione turni
	public boolean isPianoCompatibileBase(Piano p) {
		for(Competenza competenzaRichiesta : p.getCompetenzeBase()) {
			if(!this.getListaCompetenze().contains(competenzaRichiesta)) return false;
		}
		return true;
	}
	
	//compatibile se possiede competenze, non controlla sovrapposizione turni
	//non compatibile se non ci sono competenze speciali
	public boolean isPianoCompatibileSpeciale(Piano p) {
		List<Competenza> competenzeRichieste = p.getCompetenzeSpeciale();
		if(competenzeRichieste == null) return false;
		for(Competenza competenzaRichiesta : competenzeRichieste) {
			if(!this.getListaCompetenze().contains(competenzaRichiesta)) return false;
		}
		return true;
	}
	
	public boolean isCoordinatore() {
		return isCoordinatore;
	}
	
	
	
}
