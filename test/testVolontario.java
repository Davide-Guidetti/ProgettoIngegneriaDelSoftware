public class TestVolontario {
    private Volontario volontario;

    @Before
    public void setUp() {
        volontario = new Volontario();
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
        String competenze []=new String[3];
        competenze[0]="Autista";
        competenze[1]="BLSD";
        competenze[2]="OPSA";
        volontario = new Volontario("Giorgio","Mocci","Giorgio.Mocci@gmail.com","123 456 789","ComitatoA",competenze,false);
        Assert.assertEquals(volontario.getNome(),"Giorgio");
        Assert.assertEquals(volontario.getCognome(),"Mocci");
        Assert.assertEquals(volontario.getMail(),"Giorgio.Mocci@gmail.com");
        Assert.assertEquals(volontario.getNumero(),"123 456 789");
        Assert.assertEquals(volontario.getComitato(),"ComitatoA");
        Assert.assertEquals(volontario.getCompetenze(),competenze);
        Assert.assertFalse(volontario.isCoordinatore());
    }

    @Time
    public void TestSettersProprieta() {
        volontario.setNome("Giorgio");
        Assert.assertEquals(volontario.getNome(),"Giorgio");
        volontario.setCognome("Mocci");
        Assert.assertEquals(volontario.getCognome(),"Mocci");
        volontario.setMail("Giorgio.Mocci@gmail.com");
        Assert.assertEquals(volontario.getMail(),"Giorgio.Mocci@gmail.com");
        volontario.setNumero("123 456 789");
        Assert.assertEquals(volontario.getNumero(),"123 456 789");
        volontario.setComitato("ComitatoA");
        Assert.assertEquals(volontario.getComitato(),"ComitatoA");
        String competenze []=new String[3];
        competenze[0]="Autista";
        competenze[1]="BLSD";
        competenze[2]="OPSA";
        volontario.setCompetenze(competenze);
        Assert.assertEquals(volontario.getCompetenze(),competenze);
        volontario.setVolontario(false);
        Assert.assertFalse(volontario.isCoordinatore());
    }

}