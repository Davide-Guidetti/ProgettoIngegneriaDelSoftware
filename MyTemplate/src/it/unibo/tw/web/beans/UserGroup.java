package it.unibo.tw.web.beans;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class UserGroup {
	
	private String password;
	public LocalDateTime lastPasswordChange;
	public String name;
	public String group;
	
	public UserGroup(String name, String password, String group) {
		super();
		this.password = String.valueOf(password);
		this.name = name;
		this.group = group;
		this.lastPasswordChange = LocalDateTime.now();
	}
	
	public boolean isPasswordExpired() {
		long diff = lastPasswordChange.until(LocalDateTime.now(), ChronoUnit.DAYS);
	    return diff > 60; //settaggio giorni prima di cambio password pari a 60 giorni
	}
	
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	public boolean editPassword(String newPassword) {
		password = String.valueOf(newPassword);
		lastPasswordChange = LocalDateTime.now();
		return true;
	}

}
