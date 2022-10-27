package br.mg.vhugo.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.mg.vhugo.core.*;


public class TesteAjax {

//	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void Inicializa() {
//		driver = new ChromeDriver();
//		driver.manage().window().setSize(new Dimension(1200, 765));
		DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=6622av");
		dsl = new DSL(DriverFactory.getDriver());
	}
	
	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void testeAjax() {
		dsl.escrever("j_idt343:name", "Teste");
		dsl.clicarBotao("j_idt343:j_idt347");
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 30);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt343:display"), "Teste"));
		Assert.assertEquals("Teste", dsl.obterTexto("j_idt343:display"));
	}
}
