package it.azudo.model.volontario;

import java.util.List;

public class GruppoCompetenza {
	
	int numeroVolontari;
	TipoGruppo tipoGruppo;
	List<Competenza> competenze;
	
	public int getNumVolontari() {
		return numeroVolontari;
	}

	public TipoGruppo getTipoGruppo() {
		return tipoGruppo;
	}

	public List<Competenza> getCompetenze() {
		return competenze;
	}

	public GruppoCompetenza(int numeroVolontari, TipoGruppo tipoGruppo, List<Competenza> competenze) {
		super();
		this.numeroVolontari = numeroVolontari;
		this.tipoGruppo = tipoGruppo;
		this.competenze = competenze;
	}

	@Override
	public String toString() {
		return "GruppoCompetenza [tipoGruppo=" + tipoGruppo + ", numeroVolontari=" + numeroVolontari + ", competenze="
				+ competenze + "]";
	}
	
	
}
