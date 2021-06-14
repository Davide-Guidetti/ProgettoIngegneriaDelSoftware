package it.azudo.model.volontario;

import java.util.List;

public class IscrizionePiano {
	
	private List<Turno> turniSottoscritti;
	private GruppoCompetenza gruppoCompetenza;
	
	public IscrizionePiano(List<Turno> turniSottoscritti, GruppoCompetenza gruppoCompetenza){
		this.turniSottoscritti = turniSottoscritti;
		this.gruppoCompetenza = gruppoCompetenza;
	}
	
	public List<Turno> getTurniSottoscritti() {
		return turniSottoscritti;
	}

	public GruppoCompetenza getGruppoCompetenza() {
		return gruppoCompetenza;
	}
	
	public Piano getPiano() {
		return turniSottoscritti.get(0).getPianoAppartenenza();
	}
	
	public String toString() {
		return "[TitoloPiano=" + getPiano().getTitolo() + ", turni=" + turniSottoscritti + "]";
	}

}
