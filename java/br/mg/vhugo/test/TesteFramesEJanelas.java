package br.mg.vhugo.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.mg.vhugo.core.*;

public class TesteFramesEJanelas {
	
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
	public void deveInteragirComFrames() {
//		DriverFactory.getDriver().switchTo().frame("frame1");
		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();
		
		DriverFactory.getDriver().switchTo().defaultContent();
		
		dsl.escrever("elementosForm:nome", msg);
		
		Assert.assertEquals(msg, dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveInteragirComFramesEscondidos() {
		WebElement frame = DriverFactory.getDriver().findElement(By.id("frame2"));      //linha criada para sanar erro de localização (rolando a barra na vertical) e click do botao "Dentro do Frame"
		dsl.executarJs("window.scrollBy(0, arguments[0])", frame.getLocation().y);   //linha criada para sanar erro de localização (rolando a barra na vertical) e click do botao "Dentro do Frame"
		dsl.entrarFrame("frame2");
		
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
	}
	
	@Test
	public void deveInteragirComJanelas() {
		dsl.clicarBotao("buttonPopUpEasy");
		DriverFactory.getDriver().switchTo().window("Popup");
		DriverFactory.getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo!");
		DriverFactory.getDriver().close();
		DriverFactory.getDriver().switchTo().window("");
		DriverFactory.getDriver().findElement(By.tagName("textarea")).sendKeys("e agora?");
		
	}
	
	@Test
	public void deveInteragirComJanelasSemTitulo() {
		dsl.clicarBotao("buttonPopUpHard");
		System.out.println(DriverFactory.getDriver().getWindowHandle());
		System.out.println(DriverFactory.getDriver().getWindowHandles());
		DriverFactory.getDriver().switchTo().window((String)DriverFactory.getDriver().getWindowHandles().toArray()[1]);
		DriverFactory.getDriver().findElement(By.tagName("textarea")).sendKeys("Deu bom!");
		
		DriverFactory.getDriver().switchTo().window((String)DriverFactory.getDriver().getWindowHandles().toArray()[0]);
		DriverFactory.getDriver().findElement(By.tagName("textarea")).sendKeys("E agora?");
	}
}
