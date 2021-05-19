public class TestVolontario {
    
	private VolontarioApprovato volontario;
	private VolontarioApprovato volontario2;
	private VolontarioApprovato volontario3;
	private Piano piano;
	private Turno turni[];
	private Turno turni2[];
	private Comitetato comitatoTest;
	IscrizionePiano iscrizioni[]

    @Before
    public void setup() {
		piano = new Piano();
        volontario = new Volontario();
		
		Competenza competenze[] = new Competenza[3];
        competenze[0] = new Competenza("Autista");
        competenze[1] = new Competenza("BLSD");
        competenze[2] = new Competenza("OPSA");        
		
		turni[] = new Turno[4];
		turni[0]=Turno(piano, LocalDateTime.of(2015, Month.JULY, 29, 10, 30), LocalDateTime.of(2015, Month.JULY, 29, 11, 30)); //yy, mm, gg, hh, mm
		turni[1]=Turno(piano, LocalDateTime.of(2015, Month.JULY, 29, 19, 30), LocalDateTime.of(2015, Month.JULY, 29, 20, 30)); //yy, mm, gg, hh, mm
		turni[2]=Turno(piano, LocalDateTime.of(2015, Month.JULY, 30, 10, 30), LocalDateTime.of(2015, Month.JULY, 30, 11, 30)); //yy, mm, gg, hh, mm
		turni[3]=Turno(piano, LocalDateTime.of(2015, Month.JULY, 30, 19, 30), LocalDateTime.of(2015, Month.JULY, 30, 20, 30)); //yy, mm, gg, hh, mm
		
		comitatoTest = new Comitato("Comitato1");
		
		iscrizioni[] = new IscrizionePiano[1];
		iscrizioni[0] = new IscrizionePiano(turni, TipoGruppo.SPECIALE)
		volontario2 = new Volontario("Giorgio","Mocci","Giorgio.Mocci@gmail.com","123 456 789",comitatoTest,competenze,false,iscrizioni);
		
		iscrizioni1[] = new IscrizionePiano[1];
		iscrizioni1[0] = new IscrizionePiano(turni, TipoGruppo.SPECIALE)
		volontario3 = new Volontario("Giorgio","Mocci","Giorgio.Mocci@gmail.com","123 456 789",comitatoTest,competenze,false,iscrizioni);
		
		turni2[] = new Turno[4];
		turni[0]=Turno(piano, LocalDateTime.of(2015, Month.JULY, 29, 10, 50), LocalDateTime.of(2015, Month.JULY, 29, 11, 30)); //yy, mm, gg, hh, mm
		gruppoCompetenzaBase = new GruppoCompetenza(10, TipoGruppo.BASE, competenze);
		piano2 = new Piano("Piano2",comitatoTest,turni2,gruppoCompetenzaBase,null,"Luogo Test","Descrizione Test");
		
    }

    @Test
    public void TestCostruttore() {
        Assert.assertNull(volontario.getNome());
        Assert.assertNull(volontario.getCognome());
        Assert.assertNull(volontario.getMail());
        Assert.assertNull(volontario.getNumero());
        Assert.assertNull(volontario.getComitato());
        Assert.assertNull(volontario.getCompetenze());
        Assert.assertFalse(volontario.isCoordinatore());
    }

    @Test
    public void TestGetterProprieta() {
        Assert.assertEquals(volontario2.getNome(),"Giorgio");
        Assert.assertEquals(volontario2.getCognome(),"Mocci");
        Assert.assertEquals(volontario2.getMail(),"Giorgio.Mocci@gmail.com");
        Assert.assertEquals(volontario2.getNumero(),"123 456 789");
        Assert.assertEquals(volontario2.getComitato(),"ComitatoA");
		Assert.assertArrayEquals(Volontatio2.getTurniSottoscritti(), turni);
        Assert.assertArrayEquals(volontario.getCompetenze(),competenze);
        Assert.assertFalse(volontario.isCoordinatore());
    }

    @Test
    public void TestIscrizione() {
		Turno turno = new Turno(piano, LocalDateTime.of(2015, Month.JULY, 30, 19, 31), LocalDateTime.of(2015, Month.JULY, 30, 20, 31));
		AssertTrue(volontario3.partecipa(TipoGruppo.SPECIALE, turno));
		AssertTrue(new ArrayList<Turno>.add(volontario.getTurniSottoscritti()).contains(turno));
    }
	
	@Test
    public void TestDisiscrizione() {
		Turno turno = new Turno(piano, LocalDateTime.of(2015, Month.JULY, 30, 19, 31), LocalDateTime.of(2015, Month.JULY, 30, 20, 31));
		AssertTrue(volontario2.disicrivi(turni[1]));
		AssertFalse(new ArrayList<Turno>.add(volontario.getTurniSottoscritti()).contains(turno));
    }
	
	@Test
    public void TestCompatibile1() {
		AssertFalse(volontario2.isPianoCompatibileBase(piano2));
    }
	
	@Test
    public void TestCompatibile2() {
		AssertFalse(volontario2.isPianoCompatibileSpeciale(piano2));
    }

}