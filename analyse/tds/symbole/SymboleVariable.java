package yal.analyse.tds.symbole;

import yal.analyse.tds.TDS;

public class SymboleVariable extends Symbole {

	public SymboleVariable(String type) {
		super(type);
		deplacement = - TDS.getInstance().tailleZoneDesVariables();
		espace = 4;
	}
	
	public boolean pourVariable() {
		return true;
	}
	
}
