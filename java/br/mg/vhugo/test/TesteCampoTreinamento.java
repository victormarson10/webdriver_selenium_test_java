package br.mg.vhugo.test;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import br.mg.vhugo.core.*;

public class TesteCampoTreinamento {
	
	private DSL dsl;
	
	@Before
	public void Inicializa() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(DriverFactory.getDriver());
	}
	
	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}

	@Test
	public void testeTextField() {
		dsl.escrever("elementosForm:nome", "Teste de escrita");
		
//		DriverFactory.getDriver().findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita"); -> Antes de aplicar DSL
		
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void testeTextFieldDuplo() {
		dsl.escrever("elementosForm:nome", "Victor");
		
		Assert.assertEquals("Victor", dsl.obterValorCampo("elementosForm:nome"));
		
		dsl.escrever("elementosForm:nome", "Hugo");
		
		Assert.assertEquals("Hugo", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveInteragirComTextArea() {
		dsl.escrever("elementosForm:sugestoes", "Teste\nTetse2\nTeste123");
		
		Assert.assertEquals("Teste\nTetse2\nTeste123", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void deveInteragirComRedioButton() {
		dsl.clicarRadio("elementosForm:sexo:0");
		
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}
	
	@Test
	public void deveInteragirComCheckBox() {
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:0"));
	}
	
	@Test
	public void deveInteragirComCombo() {
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		
		Assert.assertEquals("2graucomp", dsl.obterValorCampo("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresCombo() {
		
		dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
		Assert.assertEquals("Mestrado", dsl.obterValorCombo("elementosForm:escolaridade"));
//		assertEquals("mestrado", dsl.obterValorCampo("elementosForm:escolaridade"));
		
//		WebElement element = DriverFactory.getDriver().findElement(By.id("elementosForm:escolaridade"));
//		Select combo = new Select(element);
//		List<WebElement> opcoes = combo.getOptions();
//		
//		Assert.assertEquals(8, opcoes.size());
//		
//		boolean encontrou = false;
//		for(WebElement opcoe: opcoes) {
//			if(opcoe.getText().equals("Mestrado")) {
//				encontrou = true;
//				break;
//			} else {
//				System.out.println("Não encontrado!");
//			}
//		}
//		
//		Assert.assertTrue(encontrou);
	}	
	
	@Test
	public void deveVerificarValoresComboMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		
		Assert.assertEquals(2, opcoesMarcadas.size());
		
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
		
//		WebElement element = DriverFactory.getDriver().findElement(By.id("elementosForm:esportes"));
//		Select combo = new Select(element);
//		
//		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
//		
//		Assert.assertEquals(3, allSelectedOptions.size());
//		
//		combo.deselectByVisibleText("Corrida");
//		allSelectedOptions = combo.getAllSelectedOptions();
//		Assert.assertEquals(2, allSelectedOptions.size());
	}
	
	@Test
	public void deveInteragirComBotoes() {
//		DriverFactory.getDriver().findElement(By.id("buttonSimple")).click();
		dsl.clicarBotao("buttonSimple");
		
//		WebElement botao = DriverFactory.getDriver().findElement(By.id("buttonSimple"));
//		botao.click();
		
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
	}
	
	@Test
//	@Ignore
	public void deveInteragirComLinks() {
//		WebElement link = DriverFactory.getDriver().findElement(By.linkText("Voltar"));
//		link.click();
		
		dsl.clicarLink("Voltar");
		
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}	
	
	@Test
	public void deveBuscarTextosNaPagina() {
//		Assert.assertTrue(DriverFactory.getDriver().findElement(By.tagName("body"))
//				.getText()
//				.contains("Campo de Treinamento"));
		
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
//				.getText());
		
	}	
	
	@Test
	public void testJavaScript() {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
//		js.executeScript("alert('Testando js via selenium')");
//		js.executeAsyncScript("accept()");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = DriverFactory.getDriver().findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	@Test
	public void deveClicarBotaoTabela() {
		dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
		Assert.assertEquals("Maria", dsl.alertaObterTexto());
	}
}
