package br.mg.vhugo.core;

public class Propriedades {

	//� importante que ap�s a execu��o de cada teste o browser seja fechado para que n�o incorra em sujeira nos outros testes, 
	//com false isso sempre ir� ocorrer
	public static boolean FECHAR_BROWSER = true;
	
	public static Browsers browser = Browsers.FIREFOX;
	
	public enum Browsers {
		CHROME,
		FIREFOX
	}
}
