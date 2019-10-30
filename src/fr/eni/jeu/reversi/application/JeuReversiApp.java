package fr.eni.jeu.reversi.application;

import fr.eni.jeu.reversi.ihm.Console;
import fr.eni.jeu.reversi.logical.PlateauDeReversi;

public class JeuReversiApp {

	public static void main(String[] args) {
		Console ihm = new Console();
		
		PlateauDeReversi reversi = new PlateauDeReversi(ihm);
		
		reversi.afficher();
		reversi.jouer();

	}

}
