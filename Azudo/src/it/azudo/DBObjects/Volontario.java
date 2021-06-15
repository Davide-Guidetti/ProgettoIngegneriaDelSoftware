package it.azudo.DBObjects;

import it.azudo.model.volontario.Comitato;

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
}
