package it.unibo.tw.web.examples;

import javax.servlet.http.HttpSessionListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

public class MySessionCounter implements HttpSessionListener {

	private static int activeSessions = 0;
	private static HashMap<String, HttpSession> idUser = new HashMap<String, HttpSession>();
	private static final int NEW_IF_YOUNGER_THAN_MS = 1000*60*5; //5 min
	
	public void sessionCreated(HttpSessionEvent se) {
		synchronized (this) {
			activeSessions++;
			idUser.put(se.getSession().getId(), se.getSession());
		}
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		if (activeSessions > 0)
			synchronized (this) {
				activeSessions--;
				idUser.remove(se.getSession().getId());
			}
	}

	public static int getActiveSessions() {
		return activeSessions;
	}

	public static HashMap<String, HttpSession> getMapSessions() {
		return idUser;
	}

	public static AdminUsersSessionsJSONObject[] getUserSessions() {
		Iterator<Entry<String, HttpSession>> it = idUser.entrySet().iterator();
		List<AdminUsersSessionsJSONObject> sessions = new ArrayList<AdminUsersSessionsJSONObject>();
		while (it.hasNext()) {
			HashMap.Entry<String, HttpSession> pair = (HashMap.Entry<String, HttpSession>) it.next();
			String key = pair.getKey();
			HttpSession s = pair.getValue();
			System.out.println(s.getAttribute("password"));

			Map<String, String> sessionPropertiesM = new HashMap<>(); // depositiamo qui temporaneamente tutte le coppie
																		// chiave valore delle varie proproeta
			Enumeration<String> e = s.getAttributeNames();
			while (e.hasMoreElements()) { // ciclo su tutti gli attributi di quella sessione
				String attributeName = e.nextElement();
				String attributeValue = s.getAttribute(attributeName).toString();
				sessionPropertiesM.put(attributeName, attributeValue);
				System.out.println(attributeName);
			}

			// converto la mappa sessionPropertiesM con le chiavi attributi valori in una
			// piu smplice matrice
			String sessionProperties[][] = new String[sessionPropertiesM.size()][2];
			System.out.print(sessionPropertiesM.keySet());
			Iterator<String> I2 = sessionPropertiesM.keySet().iterator();
			int count = 0;
			while (I2.hasNext()) { // ciclo su tutti gli attributi di quella sessione
				String attributeName = I2.next();
				String attributeValue = sessionPropertiesM.get(attributeName);
				sessionProperties[count][0] = attributeName;
				sessionProperties[count][1] = attributeValue;
				count++;
				System.out.println(attributeName);
			}
			// contruiamo l'oggetto che contiene l'id della sessione e i relativi valori
			AdminUsersSessionsJSONObject JO = new AdminUsersSessionsJSONObject();
			JO.setID(key);
			JO.setValues(sessionProperties);
			JO.setCreationTime( (new Date(s.getCreationTime())).toString() );
			JO.setLastActivityTime( (new Date(s.getLastAccessedTime())).toString() );
			JO.setRecent( s.getCreationTime()-(new Date()).getTime() < NEW_IF_YOUNGER_THAN_MS );
			sessions.add(JO); // aggiungiamo alla lista contenente tutti i dati delle sessioni che ci servono
		}
		return sessions.toArray(new AdminUsersSessionsJSONObject[0]);
	}

	public static ArrayList<String> getAttributeSessions() {
		ArrayList<String> listUser = new ArrayList<String>();
		Iterator<Entry<String, HttpSession>> it = idUser.entrySet().iterator();
		while (it.hasNext()) {
			HashMap.Entry<String, HttpSession> pair = (HashMap.Entry<String, HttpSession>) it.next();
			listUser.add((String) pair.getValue().getAttribute("user"));
		}
		return listUser;
	}
	
	public static synchronized void invalidateOldSessions(int minuti) {
		Iterator<Entry<String, HttpSession>> it = idUser.entrySet().iterator();
		int diffInMillies;
		int diffInSecond;
		int diffInMinutes;
		Date creaz;
		Date ora = new Date();
		while (it.hasNext()) {
			HashMap.Entry<String, HttpSession> pair = (HashMap.Entry<String, HttpSession>) it.next();
			creaz = new Date(pair.getValue().getCreationTime()); //date creation time session
			diffInMillies = (int) Math.abs(ora.getTime() - creaz.getTime());
			diffInSecond = diffInMillies/1000;
			diffInMinutes = diffInSecond /60; //duration session in minutes
			if (diffInMinutes>=minuti) { 
				pair.getValue().invalidate(); //session too old
			}
			// System.out.println(pair.getKey() + " = " + pair.getValue());
			it.remove(); // avoids a ConcurrentModificationException
		}
	}

	public static synchronized void invalidateAllSessions() {
		Iterator<Entry<String, HttpSession>> it = idUser.entrySet().iterator();
		while (it.hasNext()) {
			HashMap.Entry<String, HttpSession> pair = (HashMap.Entry<String, HttpSession>) it.next();
			pair.getValue().invalidate();
			// System.out.println(pair.getKey() + " = " + pair.getValue());
			it.remove(); // avoids a ConcurrentModificationException
		}
	}

}