package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.List;

public class VolontarioCompetenze implements Serializable {
	private static final long serialVersionUID = 1L;

	String comitato;
	String email;
	String competenze;
	String isApprove;
	
	public VolontarioCompetenze(String email, String comitato, String competenze, String isApprove) {
		super();
		this.comitato = comitato;
		this.email = email;
		this.competenze = competenze;
		this.isApprove=isApprove;
	}
	


}