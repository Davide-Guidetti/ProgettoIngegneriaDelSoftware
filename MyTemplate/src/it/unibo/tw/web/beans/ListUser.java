package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ListUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Set<User> users = new HashSet<User>();
	
	public ListUser(){
		
	}

	public User getUser(String name) {
		User[] usersArr = users.toArray(new User[0]);
		//Possibilita scrivere 3 volte la password	
		for(User u : usersArr) {
			if(u.name.equals(name)) {
				return u;
			}
		}
		return null;
	}
	
	public boolean isLoginValid(String name, String password) {
		User u;
		if((u = getUser(name)) != null) {
			return u.checkPassword(password);
		} else return false;
	}
	
	public boolean add(String name, String password) {
		if(getUser(name) == null) {
			users.add(new User(name, password));
			return true;
		}
		return false;
	}

}

