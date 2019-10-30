package fr.eni.jeu.reversi.model;

/**
 * 
 * Classe en charge de construire des pions.
 * @author fbourell2018
 * @version TP_Reversi - V1.0
 * @date 22 févr. 2019 - 08:48:25
 *
 */
public enum Pion {
	LIBRE (60),
	BLANC (2),
	NOIR (2);
	
	private int nombre;
	
	/**
	 * 
	 * Constructeur de Pion.
	 * @param nombre
	 */
	private Pion(int nombre) {
		this.nombre = nombre;
	};
	
//	/**
//	 * 
//	 * Méthode en charge
//	 * @return
//	 */
//	private int nombre() { return nombre;}
	
	/**
	 * 
	 * Méthode en charge de donner le pion opposé à celui du joueur
	 * @param p
	 * @return
	 */
	public Pion autrePion(Pion p) {
//		System.out.println("Le pion qui joue : " + p);
		Pion pInverse = null;
		if (p.equals(BLANC)) {
			pInverse = Pion.valueOf("NOIR");	
		}
		if (p.equals(NOIR)) {
			pInverse = Pion.valueOf("BLANC");
		}
		
		return pInverse;
	}
	
	/**
	 * 
	 * Méthode en charge d'itérer le nombre de pions lorsqu'un pion est posé
	 * Le pion posé gagne en nombre et le pion opposé perd en nombre
	 * @param p
	 * @param nbGagnes
	 */
	public void gagne(Pion p, Coup coup) {
		System.out.println("gagne()");
		p.nombre += coup.getPionsGagnes()+1;
		autrePion(p).nombre -= coup.getPionsGagnes();
		Pion.LIBRE.nombre --;
	}

	/**
	 * 
	 * Méthode en charge de retourner la quantité de pions posés
	 * @return
	 */
	public int getNombre() {
		return nombre;
	}

	/**
	 * 
	 * Méthode en charge de setter la quantité de pions posés
	 * @param nombre
	 */
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	
}
