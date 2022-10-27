package br.mg.vhugo.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.mg.vhhugo.page.CampoTreinamentoPage;
import br.mg.vhugo.core.*;

public class TesteRegraDeNegocioNova {
	
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void Inicializa() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(DriverFactory.getDriver());
		page = new CampoTreinamentoPage();
	}
	
	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}

	@Test
	public void deveChecarRegraNegocio() {
		
		//Cadastrar
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
		
		//Nome + Cadatrar
		page.setNome("Teste");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
		
		//Nome + Sobrenome + Cadatrar
		page.setSobrenome("Teste1");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
		
		//Nome +  Sobrenome + Sexo + comida(carne + vegetariano) + Cadastrar
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
		page.setComidaVegetariano();
		
		//Todas as marcações acima + escolaridade
		page.setEscolaridade("Mestrado");
		Assert.assertEquals("mestrado", dsl.obterValorCampo("elementosForm:escolaridade"));
		
		//Todas as marcações do caso acima mais a seleção de 2 esportes
		page.setEsporte("Natacao", "O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
		
		//Todas as marcações do caso acima, seleção de 1 esportes + Cadastro
		dsl.deselecionarCombo("elementosForm:esportes", "O que eh esporte?");
		dsl.escrever(By.tagName("textarea"), "Teste1");
		dsl.clicarBotao("elementosForm:cadastrar");
		
		//Teste cadastro completo
		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		Assert.assertEquals("Teste", page.obterNomeCadastro());
		Assert.assertEquals("Teste1", page.obterSobreNomeCadastro());
		Assert.assertEquals("Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Carne", page.obterComidaCadastro());
		Assert.assertEquals("mestrado", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Natacao", page.obterEsporteCadastro());
		Assert.assertEquals("Teste1", page.obterSugestoesCadastro());

	}
}
