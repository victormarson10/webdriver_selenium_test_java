package br.mg.vhugo.core;

public class BasePage {

		protected DSL dsl;
	
		public BasePage() {
		dsl = new DSL(DriverFactory.getDriver());
	}
}
