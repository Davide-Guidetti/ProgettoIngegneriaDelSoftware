package it.azudo.DBObjects.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.azudo.DBObjects.Competenza;
import it.azudo.DBObjects.DBConnection;
import it.azudo.DBObjects.Possiede;
import it.azudo.DBObjects.Volontario;
import it.azudo.model.volontario.VolontarioApprovato;

class BConnectionTest {
	
	static List<Competenza> competenze;

	@BeforeAll
	public static void setUp() throws Exception {
		competenze = new ArrayList<>();
		competenze.add(new Competenza("Guidatore ambulanza"));
		competenze.add(new Competenza("Sorveglianza pubblica"));
		competenze.add(new Competenza("Vigile del fuoco"));
		DBConnection.getDBConnection().competenze = competenze;
		
		
		List<Volontario> volontari = new ArrayList<>();
		List<Possiede> possiede = new ArrayList<>();
		
		volontari.add(new Volontario("Bosa", "giorgio.mocci@studio.unibo.it", "fdjahsbgdjsa", "Giorgio", "Mocci", "3474516549", false, true));
		possiede.add(new Possiede("Guidatore ambulanza", "giorgio.mocci@studio.unibo.it"));
		possiede.add(new Possiede("Vigile del fuoco", "giorgio.mocci@studio.unibo.it"));
		
		volontari.add(new Volontario("Bosa", "loris.giannatempo@studio.unibo.it", "afdegwerhbr", "Loris", "Giannatempo", "3474324521", false, true));
		possiede.add(new Possiede("Vigile del fuoco", "loris.giannatempo@studio.unibo.it"));
		
		volontari.add(new Volontario("Bosa", "davide.guidetti@studio.unibo.it", "fdasgasdtr", "Davide", "Guidetti", "3475417235", true, true));
		possiede.add(new Possiede("Sorveglianza pubblica", "davide.guidetti@studio.unibo.it"));
		
		volontari.add(new Volontario("Boh", "sconosciuto.boh@libero.it", "ahgeafgasa", "sconosciuto", "boh", "347432125", false, true));
		possiede.add(new Possiede("Vigile del fuoco", "sconosciuto.boh@libero.it"));
		possiede.add(new Possiede("Sorveglianza pubblica", "sconosciuto.boh@libero.it"));
		
		DBConnection.getDBConnection().volontario = volontari;
		DBConnection.getDBConnection().possiede = possiede;
	}
	
	
	
	@Test
	void testGetCompenteze() {
		assertEquals(competenze, DBConnection.getDBConnection().getCompetenze());
	}
	
	@Test
	void testGetVolontari() {
		List<it.azudo.model.volontario.VolontarioApprovato> resList;
		resList = DBConnection.getDBConnection().getVolontariComitato("Bosa");
		assertEquals(3, resList.size());
		assertTrue(containsVolontario("giorgio.mocci@studio.unibo.it", resList));
		assertTrue(containsVolontario("loris.giannatempo@studio.unibo.it", resList));
		assertTrue(containsVolontario("davide.guidetti@studio.unibo.it", resList));
	}
	
	private boolean containsVolontario(String EMail, List<VolontarioApprovato> resList) {
		for(it.azudo.model.volontario.Volontario v : resList) {
			if(v.getEMail().equals(EMail)) return true;
		}
		return false;
	}

}
