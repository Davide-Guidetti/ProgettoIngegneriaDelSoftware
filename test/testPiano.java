import staticorg.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;


public class TestPiano{
	
	// vedere se in progettazione le classi  CompetenzaBase CompetenzaSpeciali sono in realtà la stessa classe
	
	private Piano piano;

	Comitato comitati [] = new String[3];
	comitati[0] = new Comitato("Comitato1");
	comitati[1] = new Comitato("Comitato2");
	comitati[2] = new Comitato("Comitato3");
	Competenza competenzeBaseArr [] = new String[3];
	competenzeBaseArr[0] = new Competenza("Autista");
	competenzeBaseArr[1] = new Competenza("BLSD");
	competenzeBaseArr[2] = new Competenza("OPSA");
	CompetenzaBase competenzaBase = new CompetenzaBase(competenzeBaseArr, 10);
	Competenza competenzeSpecialiArr [] = new Competenza[3];
	competenzeSpecialiArr[0] = new Competenza("Autista camion");
	competenzeSpecialiArr[1] = new Competenza("Pompiere");
	competenzeSpecialiArr[2] = new Competenza("operatore AED");
	CompetenzaSpeciali CompetenzaSpeciali = new CompetenzaSpeciali(competenzeSpecialiArr, 5);
	Turno turni [] = new Turno[4];
	turni[0]=Turno(LocalDateTime.of(2015, Month.JULY, 29, 10, 30), LocalDateTime.of(2015, Month.JULY, 29, 11, 30)); //yy, mm, gg, hh, mm
	turni[1]=Turno(LocalDateTime.of(2015, Month.JULY, 29, 19, 30), LocalDateTime.of(2015, Month.JULY, 29, 20, 30)); //yy, mm, gg, hh, mm
	turni[2]=Turno(LocalDateTime.of(2015, Month.JULY, 30, 10, 30), LocalDateTime.of(2015, Month.JULY, 30, 11, 30)); //yy, mm, gg, hh, mm
	turni[3]=Turno(LocalDateTime.of(2015, Month.JULY, 30, 19, 30), LocalDateTime.of(2015, Month.JULY, 30, 20, 30)); //yy, mm, gg, hh, mm

	@Before
	public void setup(){
		this.piano = new Piano();
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
        piano = new Piano("Piano1","ComitatoA",turni,competenzeBase,competenzeSpeciali,"Luogo Test","Descrizione Test");
        Assert.assertEquals(piano.getTitolo(),"Piano1");
        Assert.assertEquals(piano.getComitato(),"ComitatoA");
        Assert.assertArrayEquals(piano.getTurni(),turni);
        Assert.assertArrayEquals(piano.getCompetenzeBase().getArrCompetenzeBase(),competenzeBase);
		Assert.assertArrayEquals(piano.getCompetenzeBase().getNumVolontari(),10);
        Assert.assertArrayEquals(piano.getCompetenzeSpeciali().getArrCompetenzeSpecali(),competenzeSpeciali);
		Assert.assertArrayEquals(piano.getCompetenzeSpeciali().getNumVolontari(),5);
        Assert.assertEquals(piano.getLuogo(),"Luogo Test");
		Assert.assertEquals(piano.getDescrizione(),"Descrizione Test");
    }

    @Test
    public void TestSettersProprieta() {
        piano.setTitolo("Piano1");
        Assert.assertEquals(piano.getTitolo(),"Piano1");
        piano.setComitato("ComitatoA");
        Assert.assertEquals(piano.getComitato(),"ComitatoA");
        piano.setTurni(turni);
        Assert.assertArrayEquals(piano.getTurni(),turni);
        piano.setCompetenzeBase(competenzeBase);
        Assert.assertArrayEquals(piano.getCompetenzeBase().getNumVolontari(),competenzeBase);
		Assert.assertArrayEquals(piano.getCompetenzeBase().getNumVolontari(),10);
        piano.setCompetenzeSpeciali(competenzeSpeciali);
        Assert.assertArrayEquals(piano.getCompetenzeSpeciali().getNumVolontari(),competenzeSpeciali);
		Assert.assertArrayEquals(piano.getCompetenzeSpeciali().getNumVolontari(),5);
        piano.setLuogo("Luogo Test");
        Assert.assertEquals(piano.getLuogo(),"Luogo Test");
        piano.setDescrizione("Descrizione Test");
        Assert.assertEquals(piano.getDescrizione(),"Descrizione Test");
    }

}