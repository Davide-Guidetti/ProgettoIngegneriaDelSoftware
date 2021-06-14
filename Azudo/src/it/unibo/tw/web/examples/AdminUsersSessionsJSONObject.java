package it.unibo.tw.web.examples;

public class AdminUsersSessionsJSONObject {
	private String ID;
	String creationTime;
	String lastActivityTime;
	boolean isRecent; 
	private String Values[][];

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String[][] getValues() {
		return Values;
	}

	public void setValues(String[][] values) {
		Values = values;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getLastActivityTime() {
		return lastActivityTime;
	}

	public void setLastActivityTime(String lastActivityTime) {
		this.lastActivityTime = lastActivityTime;
	}

	public boolean isRecent() {
		return isRecent;
	}

	public void setRecent(boolean isRecent) {
		this.isRecent = isRecent;
	}
}