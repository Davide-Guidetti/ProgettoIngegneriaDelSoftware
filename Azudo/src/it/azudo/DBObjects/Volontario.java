package it.azudo.DBObjects;

public class Volontario {
	String Comitato;
	String EMail;
	String Hash_Password;
	String Nome;
	String Cognome;
	String NumeroTelefono;
	boolean IsCoordinatore;
	boolean IsApprovato;
	
	
	public Volontario(String comitato, String eMail, String hash_Password, String nome, String cognome,
			String numeroTelefono, boolean isCoordinatore, boolean isApprovato) {
		super();
		Comitato = comitato;
		EMail = eMail;
		Hash_Password = hash_Password;
		Nome = nome;
		Cognome = cognome;
		NumeroTelefono = numeroTelefono;
		IsCoordinatore = isCoordinatore;
		IsApprovato = isApprovato;
	}
	
	public String getEmail() {
		return this.EMail;		
	}
	
	public void setNumeroTelefono(String numeroTelefono) {
		this.NumeroTelefono=numeroTelefono;
	}
	
	public String getNumeroTelefono() {
		return this.NumeroTelefono;		
	}
	
	public boolean controlloComitato(String comitato) {
		if (this.Comitato.equals(comitato) && IsApprovato==true) {
			return true;
		}
		if (!this.Comitato.equals(comitato)) {
			this.Comitato=comitato;
			IsApprovato=false;
		}
		return false;
	}
}
