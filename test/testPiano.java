import staticorg.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;


public class TestPiano{
	
	private Piano piano;

	String comitati [] = new String[3];
	comitati[0]="Comitato1";
	comitati[1]="Comitato2";
	comitati[2]="Comitato3";
	String competenzeBase [] = new String[3];
	competenze[0]="Autista";
	competenze[1]="BLSD";
	competenze[2]="OPSA";
	String competenzeSpeciali [] = new String[3];
	competenze[0]="Autista camion";
	competenze[1]="Pompiere";
	competenze[2]="operatore AED";
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
        Assert.assertEquals(piano.getTurni(),turni);
        Assert.assertEquals(piano.getCompetenzeBase(),competenzeBase);
        Assert.assertEquals(piano.getCompetenzeSpeciali(),competenzeSpeciali);
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
        Assert.assertEquals(piano.getTurni(),turni);
        piano.setCompetenzeBase(competenzeBase);
        Assert.assertEquals(piano.getCompetenzeBase(),competenzeBase);
        piano.setCompetenzeSpeciali(competenzeSpeciali);
        Assert.assertEquals(piano.getCompetenzeSpeciali(),competenzeSpeciali);
        piano.setLuogo("Luogo Test");
        Assert.assertEquals(piano.getLuogo(),"Luogo Test");
        piano.setDescrizione("Descrizione Test");
        Assert.assertEquals(piano.getDescrizione(),"Descrizione Test");
    }

}