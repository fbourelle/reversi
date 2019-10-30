package fr.eni.jeu.reversi.ihm;

import fr.eni.jeu.reversi.model.Coup;
import fr.eni.jeu.reversi.model.Pion;
import fr.eni.jeu.reversi.model.Player;

public interface InterfaceIhm {
	
	public void afficherScore(Pion p1, Pion p2, Pion libre);

	void afficherPlateau(Pion[][] plateau);
	
	void afficherFin(Player player);
	
	//public void afficherImpossible(Pion p, int x, int y);
	/**
	 * Affiche l'impossibilité au Pion p de poser le Coup c
	 * @param p le joueur qui joue
	 * @param c le coup du joueur
	 */
	public void afficherImpossible(Pion p, Coup c);
	
	public void afficherPose(Coup coup);
	
	//public int[] essaiUtilisateur(Pion p);
	/**
	 * 
	 * @param p l'utilisateur qui joue
	 * @return un Coup
	 */
	public Coup essaiUtilisateur(Pion p);
	
	public void afficherVainqueur(Player[] players);
	
}
