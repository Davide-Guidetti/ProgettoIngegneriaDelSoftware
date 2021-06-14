package it.azudo.model.volontario;

import java.time.LocalDateTime;
import java.util.List;

public class Piano {
	
	long ID;
	private String titolo;
	private String descrizione;
	private String luogo;
	private Comitato comitato;
	private List<Turno> turni;
	private GruppoCompetenza gruppoCompetenzeBase = null;
	private GruppoCompetenza gruppoCompetenzeSpeciale = null;
	
	public Piano(String titolo, String descrizione, String luogo, Comitato comitato, List<Turno> turni,
			GruppoCompetenza gruppoCompetenzeBase, GruppoCompetenza gruppoCompetenzeSpeciale, long iD) {
		super();
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.luogo = luogo;
		this.comitato = comitato;
		this.turni = turni;
		this.gruppoCompetenzeBase = gruppoCompetenzeBase;
		this.gruppoCompetenzeSpeciale = gruppoCompetenzeSpeciale;
		ID = iD;
	}
	
	public long getID() {
		return this.ID;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public String getLuogo() {
		return luogo;
	}
	
	public Comitato getComitato() {
		return comitato;
	}

	public List<Turno> getTurni() {
		return turni;
	}
	
	public void setTurni(List<Turno> turni) {
		this.turni = turni;
	}

	public List<Competenza> getCompetenzeBase() {
		return gruppoCompetenzeBase.getCompetenze();
	}
	
	public List<Competenza> getCompetenzeSpeciale() {
		return (gruppoCompetenzeSpeciale == null) ? null : gruppoCompetenzeSpeciale.getCompetenze();
	}
	
	public int getNumVolontariBase() {
		return gruppoCompetenzeBase.getNumVolontari();
	}
	
	public int getNumVolontariSpeciale() {
		return (gruppoCompetenzeSpeciale == null) ? null : gruppoCompetenzeSpeciale.getNumVolontari();
	}
	
	boolean isScaduto(){
		LocalDateTime maxTime = null;
		for(Turno turno : this.getTurni()) {
			if(maxTime == null || turno.getDataFine().isAfter(maxTime)) {
				maxTime = turno.getDataFine();
			}
		}
		return maxTime.isBefore(LocalDateTime.now());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piano other = (Piano) obj;
		if(ID > 0 && other.ID > 0) {
			return this.ID == other.ID;
		}else {
			if (comitato == null) {
				if (other.comitato != null)
					return false;
			} else if (!comitato.equals(other.comitato))
				return false;
			if (descrizione == null) {
				if (other.descrizione != null)
					return false;
			} else if (!descrizione.equals(other.descrizione))
				return false;
			if (gruppoCompetenzeBase == null) {
				if (other.gruppoCompetenzeBase != null)
					return false;
			} else if (!gruppoCompetenzeBase.equals(other.gruppoCompetenzeBase))
				return false;
			if (gruppoCompetenzeSpeciale == null) {
				if (other.gruppoCompetenzeSpeciale != null)
					return false;
			} else if (!gruppoCompetenzeSpeciale.equals(other.gruppoCompetenzeSpeciale))
				return false;
			if (luogo == null) {
				if (other.luogo != null)
					return false;
			} else if (!luogo.equals(other.luogo))
				return false;
			if (titolo == null) {
				if (other.titolo != null)
					return false;
			} else if (!titolo.equals(other.titolo))
				return false;
			if (turni == null) {
				if (other.turni != null)
					return false;
			} else if (!equalsTurni(turni, other.turni)) //controlliamo che i turni siano uguali senza controllare che i turni riferiscano allo stesso piano
				return false; 
		}
		return true;
	}
	
	private boolean equalsTurni(List<Turno> turni1, List<Turno> turni2) {
		for(Turno t1 : turni1) {
			boolean contains = false;
			for(Turno t2 : turni2) {
				if(t1.myequals(t2, true)) {
					contains = true;
					break;
				}
			}
			if(contains == false) return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Piano [titolo=" + titolo + ", ID=" + ID + ", turni=" + turni + "]";
	}
	
	
}
