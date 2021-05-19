import staticorg.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;


public class TestPiano{
	
	// vedere se in progettazione le classi  CompetenzaBase CompetenzaSpeciali sono in realtà la stessa classe
	
	private Piano piano;
	private Comitato comitati;
	private Competenza competenzeBaseArr [];
	private GruppoCompetenza gruppoCompetenzaBase;
	private Competenza competenzeSpecialiArr [];
	private GruppoCompetenza gruppoCompetenzaSpeciali;
	private Turno turni [];
	private Turno turni2 [];
	private Comitato comitatoTest;
	private Piano piano2;
	private Piano piano3;

	@Before
	public void setup(){
		this.piano = new Piano();
		comitati [] = new String[3];
		comitati[0] = new Comitato("Comitato1");
		comitati[1] = new Comitato("Comitato2");
		comitati[2] = new Comitato("Comitato3");
		
		competenzeBaseArr [] = new Competenza[3];
		competenzeBaseArr[0] = new Competenza("Autista");
		competenzeBaseArr[1] = new Competenza("BLSD");
		competenzeBaseArr[2] = new Competenza("OPSA");
		gruppoCompetenzaBase = new GruppoCompetenza(10, TipoGruppo.BASE, competenzeBaseArr);
		
		competenzeSpecialiArr [] = new Competenza[3];
		competenzeSpecialiArr[0] = new Competenza("Autista camion");
		competenzeSpecialiArr[1] = new Competenza("Pompiere");
		competenzeSpecialiArr[2] = new Competenza("operatore AED");
		gruppoCompetenzaSpeciali = new GruppoCompetenza(10, TipoGruppo.SPECIALE, competenzeSpecialiArr);
		
		turni [] = new Turno[4];
		turni[0]=Turno(piano, LocalDateTime.of(2015, Month.JULY, 29, 10, 30), LocalDateTime.of(2015, Month.JULY, 29, 11, 30)); //yy, mm, gg, hh, mm
		turni[1]=Turno(piano, LocalDateTime.of(2015, Month.JULY, 29, 19, 30), LocalDateTime.of(2015, Month.JULY, 29, 20, 30)); //yy, mm, gg, hh, mm
		turni[2]=Turno(piano, LocalDateTime.of(2015, Month.JULY, 30, 10, 30), LocalDateTime.of(2015, Month.JULY, 30, 11, 30)); //yy, mm, gg, hh, mm
		turni[3]=Turno(piano, LocalDateTime.of(2015, Month.JULY, 30, 19, 30), LocalDateTime.of(2015, Month.JULY, 30, 20, 30)); //yy, mm, gg, hh, mm
		
		turni2 [] = new Turno[0];
		turni2[0]=Turno(piano, LocalDateTime.of(3000, Month.JULY, 29, 10, 30), LocalDateTime.of(3000, Month.JULY, 29, 11, 30)); //yy, mm, gg, hh, mm
		
		comitatoTest = new Comitato("nomeComitato", null, null, null); //nomeComitato, piani[], volontariApprovati[], volontariNonApprovati[]
		
		piano2 = new Piano("Piano2",comitatoTest,turni,gruppoCompetenzaBase,gruppoCompetenzaSpeciali,"Luogo Test","Descrizione Test");
		piano3 = new Piano("Piano3",comitatoTest,turni2,gruppoCompetenzaBase,gruppoCompetenzaSpeciali,"Luogo Test","Descrizione Test");
	}

	@Test
	public testCostruttore(){
		Assert.assertNull(piano.getTitolo());
		Assert.assertNull(piano.getComitato());
		Assert.assertNull(piano.getTurni());
		Assert.assertNull(piano.getCompetenzeBase());
		Assert.assertNull(piano.getCompetenzeSpeciali());
		Assert.assertNull(piano.getLuogo());
		Assert.assertNull(piano.getDescrizione());
	}

    @Test
    public void TestGetterProprieta() {
        Assert.assertEquals(piano2.getTitolo(),"Piano1");
        Assert.assertEquals(piano2.getComitato(),comitatoTest);
        Assert.assertArrayEquals(piano2.getTurni(),turni);
        Assert.assertEquals(piano2.getCompetenzeBase(),gruppoCompetenzaBase);
		Assert.assertEquals(piano2.getCompetenzeBase().getNumVolontari(),10);
        Assert.assertEquals(piano2.getCompetenzeSpeciali(),gruppoCompetenzaSpeciali);
		Assert.assertEquals(piano2.getCompetenzeSpeciali().getNumVolontari(),5);
        Assert.assertEquals(piano2.getLuogo(),"Luogo Test");
		Assert.assertEquals(piano2.getDescrizione(),"Descrizione Test");
    }

    @Test
    public void TestScadenza() {
        Assert.AssertTrue(piano2.isScaduto());
		Assert.AssertFalse(piano3.isScaduto());
    }

}