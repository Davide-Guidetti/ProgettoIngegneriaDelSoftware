package it.azudo.model.volontario;

import java.time.LocalTime;

public class InputCreaTurni {
	String nomeTurno;
	LocalTime da, a;
	
	public InputCreaTurni(String nomeTurno, LocalTime da, LocalTime a) {
		super();
		this.nomeTurno = nomeTurno;
		this.da = da;
		this.a = a;
	}
	
	
}