/**
 * 
 */
package fr.eni.jeu.reversi.model;

/**
 * @author frederic.bourelle
 * le coup que fait 1 joueur
 *
 */
public class Coup {
	/**
	 * coordonnée x du coup joué
	 */
	private int x;
	
	/**
	 * Coordonnée y du coup joué
	 */
	private int y;
	
	/**
	 * Nombre de pions gagnés (hors pion posé)
	 */
	private int pionsGagnes;
	
	
	/**
	 * Constructeur vide
	 */
	public Coup() {
		super();
	}

	/**
	 * @param x > coordonnée x du coup joué
	 * @param y	> coordonnée y du coup joué
	 */
	public Coup(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @param x > coordonnée x du coup joué
	 * @param y	> coordonnée y du coup joué
	 */
	public Coup(int x, int y, int pionsGagnes) {
		super();
		this.x = x;
		this.y = y;
		this.pionsGagnes = pionsGagnes;
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the pionsGagnes
	 */
	public int getPionsGagnes() {
		return pionsGagnes;
	}
	/**
	 * @param pionsGagnes the pionsGagnes to set
	 */
	public void setPionsGagnes(int pionsGagnes) {
		this.pionsGagnes = pionsGagnes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coup Joué [x=" + x + ", y=" + y + ", pions gagnés =" + pionsGagnes +"]";
	}
	
}
