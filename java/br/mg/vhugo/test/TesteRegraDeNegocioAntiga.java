package br.mg.vhugo.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.mg.vhhugo.page.CampoTreinamentoPage;
import br.mg.vhugo.core.*;

public class TesteRegraDeNegocioAntiga {
	
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
//		dsl.escrever("elementosForm:nome", "teste");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
		
//		driver.findElement(By.id("elementosForm:nome")).sendKeys("teste");
//		driver.findElement(By.id("elementosForm:cadastrar")).click();
//		driver.switchTo().alert();
//		Alert alert = driver.switchTo().alert();
//		String msg1 = alert.getText();
//		
//		Assert.assertEquals("Sobrenome eh obrigatorio", msg1);
//		alert.accept();
		
		//Nome + Sobrenome + Cadatrar
		page.setSobrenome("Teste1");
//		dsl.escrever("elementosForm:sobrenome", "teste1");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
		
		
//		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("teste1");
//		driver.findElement(By.id("elementosForm:cadastrar")).click();
//		driver.switchTo().alert();
//		String msg2 = alert.getText();
//		
//		Assert.assertEquals("Sexo eh obrigatorio", msg2);
//		alert.accept();
		
		//Nome +  Sobrenome + Sexo + comida(carne + vegetariano) + Cadastrar
//		dsl.clicarBotao("elementosForm:sexo:0");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setComidaVegetariano();
//		dsl.clicarRadio("elementosForm:comidaFavorita:0");
//		dsl.clicarRadio("elementosForm:comidaFavorita:3");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
		page.setComidaVegetariano();
//		dsl.clicarRadio("elementosForm:comidaFavorita:3");
		
//		driver.findElement(By.id("elementosForm:sexo:0")).click();
//		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
//		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
//		driver.findElement(By.id("elementosForm:cadastrar")).click();
//		driver.switchTo().alert();
//		String msg3 = alert.getText();
//		
//		Assert.assertEquals("Tem certeza que voce eh vegetariano?", msg3);
//		alert.accept();
//		
//		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		
		//Todas as marcações acima + escolaridade
//		dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
		page.setEscolaridade("Mestrado");
		Assert.assertEquals("mestrado", dsl.obterValorCampo("elementosForm:escolaridade"));
		
		//Todas as marcações do caso acima mais a seleção de 2 esportes
//		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		page.setEsporte("Natacao", "O que eh esporte?");
//		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
//		page.setEsporte("O que eh esporte?");
//		dsl.clicarBotao("elementosForm:cadastrar");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
		
//		WebElement seleciona = driver.findElement(By.id("elementosForm:esportes")); 
////		Select selecionaEsporte = new Select((WebElement) seleciona);
//		Select selecionaEsporte = new Select(seleciona);
//		selecionaEsporte.selectByVisibleText("Natacao");
//		selecionaEsporte.selectByVisibleText("O que eh esporte?");
//		driver.findElement(By.id("elementosForm:cadastrar")).click();
//		driver.switchTo().alert();
//		String msg4 = alert.getText();
//		
//		Assert.assertEquals("Voce faz esporte ou nao?", msg4);
//		alert.accept();
		
		//Todas as marcações do caso acima, seleção de 1 esportes + Cadastro
		dsl.deselecionarCombo("elementosForm:esportes", "O que eh esporte?");
		dsl.escrever(By.tagName("textarea"), "Teste1");
		dsl.clicarBotao("elementosForm:cadastrar");
//		Assert.assertTrue(dsl.obterTexto(By.id("resultado")).startsWith("Cadastrado"));
		page.obterResultadoCadastro().startsWith("Cadastrado");
		
//		selecionaEsporte.deselectByVisibleText("O que eh esporte?");
//		
//		driver.findElement(By.tagName("textarea")).sendKeys("Teste1");
//		
//		driver.findElement(By.id("elementosForm:cadastrar")).click();
//		
//		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado"));
	
	}
	
}
