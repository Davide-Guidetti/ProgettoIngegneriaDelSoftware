package it.azudo.model.volontario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Turno {
	private LocalDateTime dataInizio, dataFine;
	private Piano pianoAppartenenza;
	private long ID;
	private String nome;

	public Turno(String nome, LocalDateTime dataInizio, LocalDateTime dataFine, Piano pianoAppartenenza, int ID) {
		super();
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		if(!dataInizio.isBefore(dataFine)) throw new IllegalArgumentException("Periodo turno non valido");
		this.pianoAppartenenza = pianoAppartenenza;
		this.ID=ID;
		this.nome = nome;
	}
	
	public Piano getPianoAppartenenza() {
		return pianoAppartenenza;
	}
	
	public String getNome() {
		return nome;
	}
	
	public long getID() {
		return this.ID;
	}
	
	public LocalDateTime getDataInizio() {
		return dataInizio;
	}

	public LocalDateTime getDataFine() {
		return dataFine;
	}

	public int getNumIscrizioniBase(List<VolontarioApprovato> volontari) {
		int count=0;
		for(VolontarioApprovato volontario : volontari) {
			for(IscrizionePiano iscrizionePiano : volontario.getIscrizioniPiani()) {
				if(iscrizionePiano.getGruppoCompetenza().getTipoGruppo() == TipoGruppo.BASE) {
					for(Turno turno : iscrizionePiano.getTurniSottoscritti()) {
						if(turno.equals(this)) {
							count++;
							break;
						}
					}
				}
			}
		}
		return count;
	}
	
	public int getNumIscrizioniSpeciali(List<VolontarioApprovato> volontari) {
		int count=0;
		for(VolontarioApprovato volontario : volontari) {
			for(IscrizionePiano iscrizionePiano : volontario.getIscrizioniPiani()) {
				if(iscrizionePiano.getPiano().getCompetenzeSpeciale() != null && iscrizionePiano.getGruppoCompetenza().getTipoGruppo() == TipoGruppo.SPECIALE) {
					for(Turno turno : iscrizionePiano.getTurniSottoscritti()) {
						if(turno.equals(this)) {
							count++;
							break;
						}
					}
				}
			}
		}
		return count;
	}
	
	public boolean isOverlapped(Turno o) {
		//return this.dataInizio.isBefore(o.dataFine) || this.dataFine.isAfter(o.dataInizio);
		return (o.dataInizio.isBefore(this.dataInizio) && o.dataFine.isAfter(this.dataInizio)) || 
				(o.dataInizio.isBefore(this.dataFine) && o.dataFine.isAfter(this.dataFine)) ||
				(o.dataInizio.isAfter(this.dataInizio) && o.dataFine.isBefore(this.dataFine)) ||
				(o.dataInizio.equals(this.dataInizio) && o.dataFine.equals(this.dataFine));
	}

	@Override
	public boolean equals(Object obj) {return myequals(obj, false);}
	
	public boolean myequals(Object obj, Boolean daPiano) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turno other = (Turno) obj;
		if(ID > 0 && other.ID > 0) {
			if (ID != other.ID) return false;
		}else {
			if (dataFine == null) {
				if (other.dataFine != null)
					return false;
			} else if (!dataFine.equals(other.dataFine))
				return false;
			if (dataInizio == null) {
				if (other.dataInizio != null)
					return false;
			} else if (!dataInizio.equals(other.dataInizio))
				return false;
			if (nome == null) {
				if (other.nome != null)
					return false;
			} else if (!nome.equals(other.nome))
				return false;
			if(daPiano==false) { //se la chiamata viene da pianoAppartenenza, non fare questo controllo, che provocherebbe a sua volta una chiamata a questa funzione, creando un loop infinito
				if (pianoAppartenenza == null) {
					if (other.pianoAppartenenza != null)
						return false;
				} else if (!pianoAppartenenza.equals(other.pianoAppartenenza))
					return false;
			}
		}
		return true;
	}
	
	public String toString() {
		return this.nome + " (" + this.dataInizio + " - " + this.dataFine + ")";
	}

	
	//genenra i turni a partire dalle informazioni fornite dal coordinatore dall'interfaccia web
	//attenzione: i turni non hanno un ID valido
	public static List<Turno> generaTurni(LocalDate da, LocalDate a, InputCreaTurni[] inputTurni, Piano piano){
		List<Turno> turni = new ArrayList<Turno>();
		LocalDate dataCorrente = da;
		do{
			for(int i=0; i<inputTurni.length; i++){
				turni.add(new Turno(inputTurni[i].nomeTurno, LocalDateTime.of(dataCorrente, inputTurni[i].da), LocalDateTime.of(dataCorrente, inputTurni[i].a), piano, -1));
			}
			dataCorrente = dataCorrente.plusDays(1);
		}while(!dataCorrente.isAfter(a));
		return turni;
	}
	
}
