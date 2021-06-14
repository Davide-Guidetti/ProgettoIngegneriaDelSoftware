package it.azudo.model.volontario;

import java.util.List;

public class VolontarioNonApprovato extends Volontario{
	
	private Comitato richiestaComitato;

	public Comitato getRichiestaComitato() {
		return richiestaComitato;
	}
	
	public VolontarioNonApprovato(String nome, String congnome, String eMail, String numeroTelefono, List<Competenza> listaCompetenze, Comitato richiestaComitato) {
		super(nome, congnome, eMail, numeroTelefono, listaCompetenze);
		this.richiestaComitato = richiestaComitato;
	}
	
	
}
