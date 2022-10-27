package br.mg.vhugo.test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.mg.vhhugo.page.CampoTreinamentoPage;
import br.mg.vhugo.core.BaseTest;
import br.mg.vhugo.core.DriverFactory;

public class TesteCadastro extends BaseTest{
	
//	private WebDriver driver;
	private CampoTreinamentoPage page;
	
	@Before
	public void Inicializa() {
//		driver = new ChromeDriver();
//		driver.manage().window().setSize(new Dimension(1200, 765));
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
//		page = new CampoTreinamentoPage(driver);
		page = new CampoTreinamentoPage();
	}
	

	@Test
	public void deveRealizarCadastroComSucesso() {
		page.setNome("Victor");
		page.setSobrenome("Hugo");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEscolaridade("Mestrado");
		page.setEsporte("Natacao");
		page.cadastrar();
		
		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
//		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Victor"));
		Assert.assertEquals("Victor", page.obterNomeCadastro());
		Assert.assertEquals("Hugo", page.obterSobreNomeCadastro());
		Assert.assertEquals("Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Carne", page.obterComidaCadastro());
		Assert.assertEquals("mestrado", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Natacao", page.obterEsporteCadastro());
		
	}
}
