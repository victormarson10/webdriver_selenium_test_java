package br.mg.vhugo.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;

import br.mg.vhugo.core.*;

public class TesteAlert {
	
	private DSL dsl;
//	private WebDriver driver;
	
	@Before
	public void Inicializa() {
//		driver = new ChromeDriver();
//		DriverFactory.getDriver().manage().window().setSize(new Dimension(1200, 765));
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(DriverFactory.getDriver());
	}
	
	@After
	public void finaliza() {
		DriverFactory.killDriver();;
	}


	@Test
	public void deveInteragirComAlertSimples() {
		dsl.clicarBotao("alert");
//		Alert alert = DriverFactory.getDriver().switchTo().alert();
//		String texto = alert.getText();
//		Assert.assertEquals("Alert Simples", texto);
//		alert.accept();
		String texto = dsl.alertaObterTextoEAceita();
		
		dsl.escrever("elementosForm:nome", texto);
	
	}
	
	@Test
	public void deveInteragirComAlertMultiploConfirm() {
		dsl.clicarBotao("confirm");
		Alert alert = DriverFactory.getDriver().switchTo().alert(); 
		String texto = alert.getText();
		
		Assert.assertEquals("Confirm Simples", texto);
		alert.accept();
		
		String texto2 = alert.getText();
		Assert.assertEquals("Confirmado", texto2);
		alert.accept();
	
	}
	
	@Test
	public void deveInteragirComAlertMultiploNegando() {
		dsl.clicarBotao("confirm");
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		String texto = alert.getText();
		
		Assert.assertEquals("Confirm Simples", texto);
		alert.dismiss();
		
		String texto2 = alert.getText();
		Assert.assertEquals("Negado", texto2);
		alert.accept();
	
	}
	
	@Test
	public void deveInteragirComPrompt() {
		dsl.clicarBotao("prompt");
		Alert alerta = DriverFactory.getDriver().switchTo().alert();
		
		Assert.assertEquals("Digite um numero", alerta.getText());
		String textoCaixa = "12";
		String questao = "?";
		alerta.sendKeys(textoCaixa);
		alerta.accept();
		
		Assert.assertEquals("Era " + textoCaixa+questao, alerta.getText());
		alerta.accept();
		
		Assert.assertEquals(":D", alerta.getText());
		alerta.accept();
		
	}

}
