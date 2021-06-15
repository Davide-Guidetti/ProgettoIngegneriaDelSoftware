package it.azudo.model.test.volontario;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.azudo.model.volontario.Comitato;
import it.azudo.model.volontario.Competenza;
import it.azudo.model.volontario.GruppoCompetenza;
import it.azudo.model.volontario.IscrizionePiano;
import it.azudo.model.volontario.Piano;
import it.azudo.model.volontario.TipoGruppo;
import it.azudo.model.volontario.Turno;
import it.azudo.model.volontario.VolontarioApprovato;

class VolontarioApprovatoTest {

	static Turno turno1, turno2, turno3, turno4, turno5, turno6, turno7, turno8, turno9;
	static VolontarioApprovato volontario1, volontario2, volontario3;
	static Piano piano1, piano2, piano3;
	
	@BeforeAll
	public static void setUp() throws Exception {
		// ---------------------------------------------------------------- PIANO 1 -------------------------------------------------------------------------------------
		List<Competenza> competenzeBase = new ArrayList<Competenza>();
		competenzeBase.add(new Competenza("competenza1"));
		competenzeBase.add(new Competenza("competenza2"));
		competenzeBase.add(new Competenza("competenza3"));
		GruppoCompetenza competenzeBase1 = new GruppoCompetenza(5,TipoGruppo.BASE ,competenzeBase);
		piano1 = new Piano("TitoloPiano1", "descrizionePiano", "luogoPiano", new Comitato("nomeComitato"), null, competenzeBase1, null, -1);
		
		turno1 = new Turno("turno1", LocalDateTime.of(2012, 6, 29, 12, 00), LocalDateTime.of(2012, 6, 29, 13, 00), piano1, 1);
		turno2 = new Turno("turno2", LocalDateTime.of(2012, 6, 29, 13, 00), LocalDateTime.of(2012, 6, 29, 17, 00), piano1, 2);
		turno3 = new Turno("turno3", LocalDateTime.of(2012, 6, 30, 12, 00), LocalDateTime.of(2012, 6, 30, 13, 00), piano1, 3);
		turno4 = new Turno("turno4", LocalDateTime.of(2012, 6, 30, 13, 00), LocalDateTime.of(2012, 6, 30, 17, 00), piano1, 4);
		
		List<Turno> turni = new ArrayList<Turno>();
		turni.add(turno1);
		turni.add(turno2);
		turni.add(turno3);
		turni.add(turno4);
		piano1.setTurni(turni);
		// -----------------------------------------------------------------------------------------------------------------------------------------------------------
		
		// ---------------------------------------------------------------- PIANO 2 -------------------------------------------------------------------------------------
		competenzeBase = new ArrayList<Competenza>();
		competenzeBase.add(new Competenza("competenza4"));
		competenzeBase.add(new Competenza("competenza2"));
		competenzeBase.add(new Competenza("competenza5"));
		List<Competenza> competenzeSpeciali = new ArrayList<Competenza>();
		competenzeSpeciali.add(new Competenza("competenza1"));
		GruppoCompetenza competenzeBase2 = new GruppoCompetenza(10,TipoGruppo.BASE ,competenzeBase);
		GruppoCompetenza competenzeSpeciali2 = new GruppoCompetenza(5,TipoGruppo.SPECIALE, competenzeSpeciali);
		piano2 = new Piano("TitoloPiano2", "descrizionePiano", "luogoPiano", new Comitato("nomeComitato"), null, competenzeBase2, competenzeSpeciali2, -1);
		
		turno5 = new Turno("turno5", LocalDateTime.of(2012, 6, 29, 13, 00), LocalDateTime.of(2012, 6, 29, 14, 00), piano2, 5);
		turno6 = new Turno("turno6", LocalDateTime.of(2012, 6, 29, 16, 00), LocalDateTime.of(2012, 6, 29, 17, 00), piano2, 6);
		turno7 = new Turno("turno7", LocalDateTime.of(2012, 6, 30, 13, 00), LocalDateTime.of(2012, 6, 30, 14, 00), piano2, 7);
		turno8 = new Turno("turno8", LocalDateTime.of(2012, 6, 30, 16, 00), LocalDateTime.of(2012, 6, 30, 17, 00), piano2, 8);
		
		turni = new ArrayList<Turno>();
		turni.add(turno5);
		turni.add(turno6);
		turni.add(turno7);
		turni.add(turno8);
		piano2.setTurni(turni);
		// -----------------------------------------------------------------------------------------------------------------------------------------------------------
		
		// ---------------------------------------------------------------- PIANO 3 -------------------------------------------------------------------------------------
		competenzeBase = new ArrayList<Competenza>();
		competenzeBase.add(new Competenza("competenza4"));
		competenzeSpeciali = new ArrayList<Competenza>();
		competenzeSpeciali.add(new Competenza("competenza1"));
		competenzeBase2 = new GruppoCompetenza(10,TipoGruppo.BASE ,competenzeBase);
		competenzeSpeciali2 = new GruppoCompetenza(5,TipoGruppo.SPECIALE, competenzeSpeciali);
		piano3 = new Piano("TitoloPiano2", "descrizionePiano", "luogoPiano", new Comitato("nomeComitato"), null, competenzeBase2, competenzeSpeciali2, -1);
		
		turno9 = new Turno("turno9", LocalDateTime.of(2012, 6, 30, 13, 00), LocalDateTime.of(2012, 6, 30, 15, 00), piano3, 9);
		
		turni = new ArrayList<Turno>();
		turni.add(turno9);
		piano3.setTurni(turni);
		// -----------------------------------------------------------------------------------------------------------------------------------------------------------

		
		// --------------------------------------------------------------------- VOLONTARIO 1 ---------------------------------------------------------------------------
		List<Competenza> listaCompetenze = new ArrayList<Competenza>();
		listaCompetenze.add(new Competenza("competenza1"));
		listaCompetenze.add(new Competenza("competenza2"));
		listaCompetenze.add(new Competenza("competenza3"));
		listaCompetenze.add(new Competenza("competenza4"));
		listaCompetenze.add(new Competenza("competenza5"));
		List<IscrizionePiano> partipaAPiani = new ArrayList<>();
		turni = new ArrayList<Turno>();
		turni.add(turno1);
		partipaAPiani.add(new IscrizionePiano(turni, competenzeBase1));
		turni = new ArrayList<Turno>();
		turni.add(turno6);
		turni.add(turno7);
		partipaAPiani.add(new IscrizionePiano(turni, competenzeBase2));
		volontario1 = new VolontarioApprovato("volontario1", "congnome", "eMail1", "+394562587001", listaCompetenze, new Comitato("nomeComitato"), false, partipaAPiani);
		// -----------------------------------------------------------------------------------------------------------------------------------------------------------
		
		// --------------------------------------------------------------------- VOLONTARIO 2 ---------------------------------------------------------------------------
		listaCompetenze = new ArrayList<Competenza>();
		listaCompetenze.add(new Competenza("competenza1"));
		listaCompetenze.add(new Competenza("competenza2"));
		listaCompetenze.add(new Competenza("competenza3"));
		partipaAPiani = new ArrayList<>();
		turni = new ArrayList<Turno>();
		turni.add(turno1);
		partipaAPiani.add(new IscrizionePiano(turni, competenzeBase1));
		turni = new ArrayList<Turno>();
		turni.add(turno6);
		turni.add(turno8);
		partipaAPiani.add(new IscrizionePiano(turni, competenzeSpeciali2));
		volontario2 = new VolontarioApprovato("volontario2", "congnome", "eMail2", "+394562587002", listaCompetenze, new Comitato("nomeComitato"), false, partipaAPiani);
		// -----------------------------------------------------------------------------------------------------------------------------------------------------------
		
		// --------------------------------------------------------------------- VOLONTARIO 3 ---------------------------------------------------------------------------
		listaCompetenze = new ArrayList<Competenza>();
		listaCompetenze.add(new Competenza("competenza2"));
		listaCompetenze.add(new Competenza("competenza3"));
		listaCompetenze.add(new Competenza("competenza4"));
		listaCompetenze.add(new Competenza("competenza5"));
		partipaAPiani = new ArrayList<>();
		volontario3 = new VolontarioApprovato("volontario2", "congnome", "eMail2", "+394562587002", listaCompetenze, new Comitato("nomeComitato"), false, partipaAPiani);
		// -----------------------------------------------------------------------------------------------------------------------------------------------------------	
	}
	
	
	@Test
	void testIsPianoCompatibileBase() {
		assertTrue(volontario1.isPianoCompatibileBase(piano1));
		assertTrue(volontario1.isPianoCompatibileBase(piano2));
		assertTrue(volontario2.isPianoCompatibileBase(piano1));
		assertFalse(volontario2.isPianoCompatibileBase(piano2));
		assertFalse(volontario3.isPianoCompatibileBase(piano1));
		assertTrue(volontario3.isPianoCompatibileBase(piano2));
	}
	
	@Test
	void testIsPianoCompatibileSpeciale() {
		assertFalse(volontario1.isPianoCompatibileSpeciale(piano1));
		assertFalse(volontario2.isPianoCompatibileSpeciale(piano1));
		assertFalse(volontario3.isPianoCompatibileSpeciale(piano1));
		
		assertTrue(volontario1.isPianoCompatibileSpeciale(piano2));
		assertTrue(volontario2.isPianoCompatibileSpeciale(piano2));
		assertFalse(volontario3.isPianoCompatibileSpeciale(piano2));
	}
	
	@Test
	void testGetTurniSottoscritti() {
		assertEquals(Arrays.asList(turno1, turno6, turno7), volontario1.getTurniSottoscritti());
		assertEquals(Arrays.asList(turno1, turno6, turno8), volontario2.getTurniSottoscritti());
		assertEquals(Arrays.asList(), volontario3.getTurniSottoscritti());
	}
	
	@Test
	void testGetTurniCompatibili() {
		assertEquals(Arrays.asList(turno1, turno3), volontario1.getTurniCompatibili(piano1));
		assertEquals(Arrays.asList(turno5, turno6, turno7, turno8), volontario1.getTurniCompatibili(piano2));
		assertEquals(Arrays.asList(turno1, turno3), volontario2.getTurniCompatibili(piano1));
		assertEquals(Arrays.asList(turno5, turno6, turno7, turno8), volontario2.getTurniCompatibili(piano2));
		assertEquals(piano1.getTurni(), volontario3.getTurniCompatibili(piano1));
		assertEquals(piano2.getTurni(), volontario3.getTurniCompatibili(piano2));
	}
	
	@Test
	void testGetPianiCompatibili() {
		assertEquals(Arrays.asList(piano1, piano2), volontario1.getPianiCompatibili(Arrays.asList(piano1, piano2)));
		assertEquals(Arrays.asList(piano1, piano2), volontario2.getPianiCompatibili(Arrays.asList(piano1, piano2)));
		assertEquals(Arrays.asList(piano2), volontario3.getPianiCompatibili(Arrays.asList(piano1, piano2)));
		assertEquals(Arrays.asList(), volontario1.getPianiCompatibili(Arrays.asList(piano3)));
		assertEquals(Arrays.asList(piano3), volontario2.getPianiCompatibili(Arrays.asList(piano3)));
	}

}
