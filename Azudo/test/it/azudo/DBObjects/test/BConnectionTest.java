package it.azudo.DBObjects.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.azudo.DBObjects.Comitato;
import it.azudo.DBObjects.Competenza;
import it.azudo.DBObjects.DBConnection;
import it.azudo.DBObjects.Possiede;
import it.azudo.DBObjects.Volontario;
import it.azudo.model.volontario.VolontarioApprovato;

class BConnectionTest {
	
	static List<Competenza> competenze;

	@BeforeEach
	public void setUp() throws Exception {
		List<Comitato> comitato = new ArrayList<>();
		comitato.add(new Comitato("Bosa"));
		comitato.add(new Comitato("Castel san pietro"));
		comitato.add(new Comitato("Castel guelfo"));
		DBConnection.getDBConnection().comitato = comitato;
		
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
		
		volontari.add(new Volontario("Castel san pietro", "sconosciuto.boh@libero.it", "ahgeafgasa", "sconosciuto", "boh", "347432125", false, true));
		possiede.add(new Possiede("Vigile del fuoco", "sconosciuto.boh@libero.it"));
		possiede.add(new Possiede("Sorveglianza pubblica", "sconosciuto.boh@libero.it"));
		
		DBConnection.getDBConnection().volontario = volontari;
		DBConnection.getDBConnection().possiede = possiede;
	}
	
	
	
	@Test
	void testGetCompenteze() {
		List<it.azudo.model.volontario.Competenza> res = DBConnection.getDBConnection().getCompetenze();
		assertEquals(competenze.size(), res.size());
		for(Competenza competenza : competenze) {
			assertTrue(containsCompetenza(res, competenza.getNome()));
		}
	}
	
	private boolean containsCompetenza(List<it.azudo.model.volontario.Competenza> res, String competenza) {
		boolean found = false;
		for(it.azudo.model.volontario.Competenza compRes : res) {
			if(compRes.nomeCompetenza().equals(competenza)) {
				found = true;
				break;
			}
		}
		return found;
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
	
	@Test
	public void setCompetenzeVolotnarioTest() {
		
		List<it.azudo.model.volontario.Competenza> newCompetenze = new ArrayList<>();
		newCompetenze.add(new it.azudo.model.volontario.Competenza("Guidatore ambulanza"));
		newCompetenze.add(new it.azudo.model.volontario.Competenza("Vigile del fuoco"));
		newCompetenze.add(new it.azudo.model.volontario.Competenza("Sorveglianza pubblica"));
		
		DBConnection.getDBConnection().setCompetenzeVolotnario("giorgio.mocci@studio.unibo.it", newCompetenze);
		
		List<it.azudo.model.volontario.Competenza> res = DBConnection.getDBConnection().getCompetenze();
		assertEquals(competenze.size(), newCompetenze.size());
		for(it.azudo.model.volontario.Competenza competenza : newCompetenze) {
			assertTrue(containsCompetenza(res, competenza.nomeCompetenza()));
		}
	}
	
	@Test
	public void getComitatiTest() {
		List<it.azudo.model.volontario.Comitato> resList = new ArrayList<>();
		resList = DBConnection.getDBConnection().getComitati();
		assertEquals(3, resList.size());
		assertTrue(containsComitato("Bosa", resList));
		assertTrue(containsComitato("Castel san pietro", resList));
		assertTrue(containsComitato("Castel guelfo", resList));
	}
	
	private boolean containsComitato(String nome, List<it.azudo.model.volontario.Comitato> resList) {
		for(it.azudo.model.volontario.Comitato v : resList) {
			if(v.getNomeComitato().equals(nome)) return true;
		}
		return false;
	}
	
	@Test
	public void setComitatiTest() {
		List<it.azudo.model.volontario.Comitato> newComitati = new ArrayList<>();
		newComitati.add(new it.azudo.model.volontario.Comitato("Castel san pietro"));
		newComitati.add(new it.azudo.model.volontario.Comitato("Ravenna"));
		DBConnection.getDBConnection().setComitati(newComitati);
		
		List<it.azudo.model.volontario.Comitato> resList = new ArrayList<>();
		resList = DBConnection.getDBConnection().getComitati();
		assertEquals(2, resList.size());
		assertTrue(containsComitato("Castel san pietro", resList));
		assertTrue(containsComitato("Ravenna", resList));
		
		List<it.azudo.model.volontario.VolontarioApprovato> resList2;
		resList2 = DBConnection.getDBConnection().getVolontariComitato("Bosa");
		assertEquals(0, resList2.size());
		resList2 = DBConnection.getDBConnection().getVolontariComitato("Castel san pietro");
		assertEquals(1, resList2.size());
		assertTrue(containsVolontario("sconosciuto.boh@libero.it", resList2));
	}

}
