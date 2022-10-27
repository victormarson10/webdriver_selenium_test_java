package br.mg.vhugo.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.mg.vhugo.core.*;


public class TestePrime {
	
	private DSL dsl;
	
	@Before
	public void Inicializa() {
		dsl = new DSL(DriverFactory.getDriver());
	}
	
	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}

	@Test
	public void deveInteragirComRadioPrime() {
		DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=3d9f1");
		dsl.clicarRadio(By.xpath("//*[@id=\"j_idt344:console:0\"]/../..//span")); 
		Assert.assertTrue(dsl.isRadioMarcado("j_idt344:console:0"));
		dsl.clicarRadio(By.xpath("//label[.='Option2']/..//span")); 
		Assert.assertTrue(dsl.isRadioMarcado("j_idt344:console:1"));
		// //*[@id="j_idt344:console:0"] -> Google
		//*[@id="j_idt344:console:0"]/../..//span
		//*[@id="j_idt344:console"]/tbody/tr/td[2]/label -> Google
		//label[.='Option2']/..//span
	}
	
	@Test
	public void deveInteragirComSelectPrime() {
		DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=4b0ae");
////		dsl.clicarRadio(By.xpath("//*[@id='j_idt343:option_input']/../..//span")); //xPath gerado manual
//		dsl.clicarRadio(By.xpath("//*[@id='j_idt343:option]/div[3]/span")); //xPath gerado Google
//		dsl.clicarRadio(By.xpath("//*[@id='j_idt343:option_items']//li[.='Option1']"));
		dsl.selecionarComboPrime("j_idt343:option", "Option2");
//		Assert.assertEquals("Option1", dsl.obterTexto("j_idt343:option_label"));
		Assert.assertEquals("Option2", dsl.obterTexto(By.xpath("//*[@id=\"j_idt343:option_label\"]"))); //xPath gerado via Google Chrome
		
		//*[@id='j_idt343:option_items']//li[.='Option1']
		//*[@id='j_idt343:option_input']/../..//span
		//*[@id="j_idt343:option"]/div[3]/span ->Google
		//*[@id="j_idt343:option"]/div[3]/ -> FireBug
	}
	
}
