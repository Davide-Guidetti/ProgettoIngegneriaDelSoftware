package it.azudo.model.test.volontario;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.azudo.model.volontario.Comitato;
import it.azudo.model.volontario.Competenza;
import it.azudo.model.volontario.GruppoCompetenza;
import it.azudo.model.volontario.InputCreaTurni;
import it.azudo.model.volontario.IscrizionePiano;
import it.azudo.model.volontario.Piano;
import it.azudo.model.volontario.TipoGruppo;
import it.azudo.model.volontario.Turno;
import it.azudo.model.volontario.VolontarioApprovato;

class TurnoTest {
	
	static Turno turno1, turno2, turno3, turno4, turno5, turno6, turno7, turno8;
	static VolontarioApprovato volontario1, volontario2;
	
	@BeforeAll
	public static void setUp() throws Exception {
		// ---------------------------------------------------------------- PIANO 1 -------------------------------------------------------------------------------------
		List<Competenza> competenzeBase = new ArrayList<Competenza>();
		competenzeBase.add(new Competenza("competenza1"));
		competenzeBase.add(new Competenza("competenza2"));
		competenzeBase.add(new Competenza("competenza3"));
		GruppoCompetenza competenzeBase1 = new GruppoCompetenza(5,TipoGruppo.BASE ,competenzeBase);
		Piano piano1 = new Piano("TitoloPiano1", "descrizionePiano", "luogoPiano", new Comitato("nomeComitato"), null, competenzeBase1, null, -1);
		
		turno1 = new Turno("turno1", LocalDateTime.of(2012, 6, 29, 12, 00), LocalDateTime.of(2012, 6, 29, 13, 00), piano1, 1);
		turno2 = new Turno("turno2", LocalDateTime.of(2012, 6, 29, 12, 59), LocalDateTime.of(2012, 6, 29, 16, 00), piano1, 2);
		turno3 = new Turno("turno3", LocalDateTime.of(2012, 6, 29, 13, 00), LocalDateTime.of(2012, 6, 29, 14, 00), piano1, 3);
		turno4 = new Turno("turno4", LocalDateTime.of(2012, 6, 28, 12, 00), LocalDateTime.of(2012, 6, 30, 13, 00), piano1, 4);
		
		List<Turno> turni = new ArrayList<Turno>();
		turni.add(turno1);
		turni.add(turno3);
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
		Piano piano2 = new Piano("TitoloPiano2", "descrizionePiano", "luogoPiano", new Comitato("nomeComitato"), null, competenzeBase2, competenzeSpeciali2, -1);
		
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
		
	}


	@Test
	void testOverlap() {
		assertTrue(turno1.isOverlapped(turno2));
		assertFalse(turno1.isOverlapped(turno3));
		assertTrue(turno1.isOverlapped(turno4));
		assertTrue(turno4.isOverlapped(turno1));
	}

	@Test
	void testGetNumIscrizioniBase() {
		List<VolontarioApprovato> volontariComitato = new ArrayList<VolontarioApprovato>();
		volontariComitato.add(volontario1);
		volontariComitato.add(volontario2);
		assertEquals(2, turno1.getNumIscrizioniBase(volontariComitato));
		assertEquals(1, turno7.getNumIscrizioniBase(volontariComitato));
		assertEquals(0, turno5.getNumIscrizioniBase(volontariComitato));
		assertEquals(0, turno8.getNumIscrizioniBase(volontariComitato));
		assertEquals(1, turno6.getNumIscrizioniBase(volontariComitato));
	}
	
	@Test
	void testGetNumIscrizioniSpeciali() {
		List<VolontarioApprovato> volontariComitato = new ArrayList<VolontarioApprovato>();
		volontariComitato.add(volontario1);
		volontariComitato.add(volontario2);
		assertEquals(0, turno1.getNumIscrizioniSpeciali(volontariComitato));
		assertEquals(1, turno6.getNumIscrizioniSpeciali(volontariComitato));
		assertEquals(0, turno7.getNumIscrizioniSpeciali(volontariComitato));
		assertEquals(1, turno8.getNumIscrizioniSpeciali(volontariComitato));
	}
	
	@Test
	void testGeneraTurni() {
		List<Competenza> competenzeBase = new ArrayList<Competenza>();
		competenzeBase.add(new Competenza("competenza1"));
		GruppoCompetenza competenzeBase1 = new GruppoCompetenza(5,TipoGruppo.BASE ,competenzeBase);
		Piano piano = new Piano("TitoloPiano", "descrizionePiano", "luogoPiano", new Comitato("nomeComitato"), null, competenzeBase1, null, -1);
		
		InputCreaTurni in[] = new InputCreaTurni[2];
		in[0] = new InputCreaTurni("PrimaParte", LocalTime.of(06, 20), LocalTime.of(07, 45));
		in[1] = new InputCreaTurni("SecondaParte", LocalTime.of(16, 30), LocalTime.of(17, 00));
		List<Turno> turni = Turno.generaTurni(LocalDate.of(2021, 06, 29), LocalDate.of(2021, 07, 01), in, piano);
		
		piano.setTurni(turni);
		
		turno1 = new Turno("PrimaParte", LocalDateTime.of(2021, 6, 29, 6, 20), LocalDateTime.of(2021, 6, 29, 7, 45), piano, -1);
		turno2 = new Turno("SecondaParte", LocalDateTime.of(2021, 6, 29, 16, 30), LocalDateTime.of(2021, 6, 29, 17, 00), piano, -1);
		turno3 = new Turno("PrimaParte", LocalDateTime.of(2021, 6, 30, 6, 20), LocalDateTime.of(2021, 6, 30, 7, 45), piano, -1);
		turno4 = new Turno("SecondaParte", LocalDateTime.of(2021, 6, 30, 16, 30), LocalDateTime.of(2021, 6, 30, 17, 00), piano, -1);
		turno5 = new Turno("PrimaParte", LocalDateTime.of(2021, 7, 1, 6, 20), LocalDateTime.of(2021, 7, 1, 7, 45), piano, -1);
		turno6 = new Turno("SecondaParte", LocalDateTime.of(2021, 7, 1, 16, 30), LocalDateTime.of(2021, 7, 1, 17, 00), piano, -1);
		
		assertEquals(Arrays.asList(turno1, turno2, turno3, turno4, turno5, turno6), piano.getTurni());
	}
	
}
