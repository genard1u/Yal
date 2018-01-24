package yal.arbre.expression;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public class Moins extends BinaireArithmetique {

    public Moins(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public String operateur() {
        return " - ";
    }

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("# Soustraction\n");
		sb.append("# Calcul de la partie gauche\n");
		sb.append(gauche.toMIPS());
		sb.append("# Empilement de la partie gauche\n");
		sb.append("sw $v0, 0($sp)");
		sb.append("add $sp, $sp, -4");
		sb.append("# Calcul de la partie droite\n");
		sb.append(droite.toMIPS());
		sb.append("# Dépilement de la partie gauche\n");
		sb.append("add $sp, $sp, 4");
		sb.append("lw $t8,($sp)");
		sb.append("# Soustraction entre $v0 et $t8\n");
		sb.append("sub $v0, $t8, $v0");	
		
		return sb.toString();
	}
	
	public int getType() {
	    return ENTIER;
    }
	
}
