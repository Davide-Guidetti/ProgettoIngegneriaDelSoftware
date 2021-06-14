package it.azudo.model.volontario;

import java.util.List;

public class Comitato {
	private String nomeComitato;
	private List<Piano> pianiComitato = null;
	private List<VolontarioApprovato> volontariApprovati = null;
	private List<VolontarioNonApprovato> volontariNonApprovati = null;
	
	public Comitato(String nomeComitato) {
		this.nomeComitato = nomeComitato;
	}
	
	public Comitato(String nomeComitato, List<Piano> pianiComitato, List<VolontarioApprovato> volontariApprovato, List<VolontarioNonApprovato> volontariNonApprovato) {
		this.nomeComitato = nomeComitato;
		this.pianiComitato = pianiComitato;
		this.volontariApprovati = volontariApprovato;
		this.volontariNonApprovati = volontariNonApprovato;
	}
	
	public List<VolontarioApprovato> listaVolontari() {
		return volontariApprovati;
	}
	
	public List<VolontarioNonApprovato> listaVolontariNonApprovati() {
		return volontariNonApprovati;
	}

	public String getNomeComitato() {
		return nomeComitato;
	}

	public List<Piano> getPianiComitato() {
		return pianiComitato;
	}

	public void setPianiComitato(List<Piano> pianiComitato) {
		this.pianiComitato = pianiComitato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeComitato == null) ? 0 : nomeComitato.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comitato other = (Comitato) obj;
		if (nomeComitato == null) {
			if (other.nomeComitato != null)
				return false;
		} else if (!nomeComitato.equals(other.nomeComitato))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nomeComitato;
	}
	
	
	
}
