package yal.analyse.tds;

import yal.analyse.tds.entree.Entree;
import yal.analyse.tds.symbole.Symbole;

public class TDS {

	public enum Analyse {Syntaxique, Semantique};
	
	private Analyse analyse;
	
	private static TDS ourInstance = new TDS();
	
	private Arbre blocPrincipal;
	private Arbre blocCourant;
	
	private int numeroRegion;
	private int numeroImbrication;
	
	
	private TDS() {
		prepareAnalyseSyntaxique();
	}
	
	public static TDS getInstance() {
		return ourInstance;
	}
	
	public void prepareAnalyseSyntaxique() {
		analyse = Analyse.Syntaxique;
		blocPrincipal = null;
		blocCourant = null;
		numeroRegion = -1;
		numeroImbrication = -1;
	}
	
	public void prepareAnalyseSemantique() {
		assert blocPrincipal != null;
		
		analyse = Analyse.Semantique;
		numeroRegion = -1;
		numeroImbrication = -1;
	}
    
	public void ajouter(Entree e, Symbole s, int noLigne) {
		assert e != null;
		assert blocCourant != null;
		
		blocCourant.ajouter(e, s, noLigne);
	}
	
	public Symbole identifier(Entree e) {
		assert blocCourant != null;
		
		return blocCourant.identifier(e);
	}

	public void entreeBloc() {
		numeroRegion ++;
		numeroImbrication ++;
		
		switch (analyse) {
		    case Syntaxique:
		    	if (numeroRegion < 0) {
		    		Arbre premier = new Arbre(numeroRegion);
		    		blocPrincipal = premier;
		    		blocCourant = premier;
		    	}
		    	else {
		    		Arbre nouveauBloc = new Arbre(numeroRegion, blocCourant);
		    		blocCourant.ajouterFils(nouveauBloc);
		    		blocCourant = nouveauBloc;
		    	}
		    	
		        break;
		    case Semantique:
		    	if (numeroRegion < 0) {
		    		blocCourant = blocPrincipal;
		    	}
		    	else {
		    		Arbre fils = blocCourant.recupererFils(numeroRegion);
		    		blocCourant = fils;
		    	}
		    	
			    break;
		}
	}
	
	public void sortieBloc() {
		assert blocCourant != null;
		
		Arbre parent = blocCourant.getParent();
		
		blocCourant = parent;
		numeroImbrication--;
	}
	
	public int numeroParent() {
		assert blocCourant != null;
		
		Arbre parent = blocCourant.getParent();
		int numeroRegion = -1;
		
		if (parent != null) {
			numeroRegion = parent.numeroRegion();
		}
		
		return numeroRegion;
	}
	
	public int numeroRegion() {
		assert blocCourant != null;
		
		return blocCourant.numeroRegion();
	}
	
	public int numeroImbrication() {
		return numeroImbrication;
	}
	
	public int nbVariables() {
		assert blocCourant != null;
		
		return blocCourant.nbVariables();
	}
	
	public int tailleZoneDesVariables() {
		assert blocCourant != null;
		
		return blocCourant.tailleZoneDesVariables();
    }
	
	@Override
	public String toString() {
		assert blocCourant != null;
		
		return blocCourant.toString();
	}
	
}
