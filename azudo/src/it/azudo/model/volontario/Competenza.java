package it.azudo.model.volontario;

public class Competenza {
	private String nomeCompetenza;
	
	public String nomeCompetenza() {
		return nomeCompetenza;
	}

	public Competenza(String nomeCompetenza) {
		super();
		this.nomeCompetenza = nomeCompetenza;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeCompetenza == null) ? 0 : nomeCompetenza.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Competenza other = (Competenza) obj;
		if (nomeCompetenza == null) {
			if (other.nomeCompetenza != null) return false;
		} else if (!nomeCompetenza.equals(other.nomeCompetenza))
			return false;
		return true;
	}
	
	
}
