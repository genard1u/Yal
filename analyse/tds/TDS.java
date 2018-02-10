package yal.analyse.tds;

import java.util.HashMap;

import yal.analyse.tds.entree.Entree;
import yal.analyse.tds.symbole.Symbole;
import yal.exceptions.AnalyseSyntaxiqueException;

public class TDS {

	private static TDS ourInstance = new TDS();
	
	private HashMap<Entree, Symbole> table;
	
	
	private TDS() {
		table = new HashMap<Entree, Symbole>();
	}
	
	public static TDS getInstance() {
		return ourInstance;
	}
	
	public void ajouter(Entree e, Symbole s) {
		if (table.containsKey(e)) {
			throw new AnalyseSyntaxiqueException("redéclaration de `" + e.getIdf() + "`");
		}
		
		table.put(e, s);
	}
	
	public Symbole identifier(Entree e) {
		Symbole s;
		
		if (table.containsKey(e)) {
		  
		}
		
		return table.get(e);
	}

	public int nbVariables() {
		return table.size();
	}
	
	public int tailleZoneDesVariables() {
		return table.size() * 4;
    }
	
}
