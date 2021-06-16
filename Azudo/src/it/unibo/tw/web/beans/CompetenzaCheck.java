package it.unibo.tw.web.beans;

import java.io.Serializable;

public class CompetenzaCheck implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String nomeCompetenza;
	String checked;
	
	public CompetenzaCheck(String nomeCompetenza,String checked) {
		this.nomeCompetenza=nomeCompetenza;
		this.checked=checked;
	}

}
