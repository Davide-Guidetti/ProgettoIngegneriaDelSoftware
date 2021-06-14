package it.azudo.model.volontario;

import java.util.List;

public abstract class Volontario {
	
	protected String nome;
	protected String cognome;
	protected String EMail;
	protected String numeroTelefono;
	protected List<Competenza> listaCompetenze;
	
	public Volontario(String nome, String cognome, String eMail, String numeroTelefono, List<Competenza> listaCompetenze) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.EMail = eMail;
		this.numeroTelefono = numeroTelefono;
		this.listaCompetenze = listaCompetenze;
	}
	
	public String getNome() {
		return nome;
	}
	public String getCongnome() {
		return cognome;
	}
	public String getEMail() {
		return EMail;
	}
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	public List<Competenza> getListaCompetenze() {
		return listaCompetenze;
	}
	
	public String toString() {
		return EMail + " (" + nome + " " + cognome + ")";
	}
	
}
