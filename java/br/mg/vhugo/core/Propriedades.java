package br.mg.vhugo.core;

public class Propriedades {

	//é importante que após a execução de cada teste o browser seja fechado para que não incorra em sujeira nos outros testes, 
	//com false isso sempre irá ocorrer
	public static boolean FECHAR_BROWSER = true;
	
	public static Browsers browser = Browsers.FIREFOX;
	
	public enum Browsers {
		CHROME,
		FIREFOX
	}
}
