/**
 * 
 */
package fr.eni.jeu.reversi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author frederic.bourelle
 *
 */
public class SimulationCoup {
	
	private Plateau tableauPlateau;
	private Coup bestCoup;
	private List<Coup> listeCoups = new ArrayList<>();
	private Player playerCourant;

	/**
	 * 
	 */
	public SimulationCoup() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param tableauPlateau
	 * @param listeCoups
	 */
	public SimulationCoup(Plateau tableauPlateau, List<Coup> listeCoups, Player playerCourant) {
		super();
		this.tableauPlateau = tableauPlateau;
		this.listeCoups = listeCoups;
		this.playerCourant = playerCourant;
	}

	/**
	 * @return the tableauPlateau
	 */
	public Plateau getTableauPlateau() {
		return tableauPlateau;
	}

	/**
	 * @param tableauPlateau the tableauPlateau to set
	 */
	public void setTableauPlateau(Plateau tableauPlateau) {
		this.tableauPlateau = tableauPlateau;
	}

	/**
	 * @return the bestCoup
	 */
	public Coup getBestCoup() {
		return simuler();
	}

	/**
	 * @return the listeCoups
	 */
	public List<Coup> getListeCoups() {
		return listeCoups;
	}

	/**
	 * @param listeCoups the listeCoups to set
	 */
	public void setListeCoups(List<Coup> listeCoups) {
		this.listeCoups = listeCoups;
	}
	
	/**
	 * 
	 * @return le meilleur coup
	 */
	private Coup simuler() {
		 Coup coup = new Coup();
		 
		 int test = 0;
		 
		 // on va boucler sur chaque coup de la liste coup
		 	// on pose le coup puis on teste la pose du joueur adverse et on récupère le meilleur coup de celui-ci
		 
		 for (Coup coupTest : this.listeCoups) {
			 Plateau plateauTest = (Plateau) this.tableauPlateau.clone();
			 
			// poser(playerCourant, coupTest);
			 
		 }
		 
		 return coup;
	}

}
