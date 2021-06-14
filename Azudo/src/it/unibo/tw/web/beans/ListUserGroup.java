package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ListUserGroup implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Set<UserGroup> users = new HashSet<UserGroup>();
	
	public ListUserGroup(){
		
	}

	public UserGroup getUser(String name) {
		UserGroup[] usersArr = users.toArray(new UserGroup[0]);
		//Possibilita scrivere 3 volte la password	
		for(UserGroup u : usersArr) {
			if(u.name.equals(name)) {
				return u;
			}
		}
		return null;
	}
	
	public ArrayList<UserGroup> getUserGroup(String group){
		UserGroup[] usersArr = users.toArray(new UserGroup[0]);
		ArrayList<UserGroup> userArrFinal = new ArrayList<UserGroup>();
		//Possibilita scrivere 3 volte la password	
		for(UserGroup u : usersArr) {
			if(u.group.equals(group)) {
				userArrFinal.add(u);
			}
		}
		return userArrFinal;
	}
	
	public boolean isLoginValid(String name, String password) {
		UserGroup u;
		if((u = getUser(name)) != null) {
			return u.checkPassword(password);
		} else return false;
	}
	
	public boolean add(String name, String password, String group) {
		if(getUser(name) == null) {
			users.add(new UserGroup(name, password, group));
			return true;
		}
		return false;
	}

}

