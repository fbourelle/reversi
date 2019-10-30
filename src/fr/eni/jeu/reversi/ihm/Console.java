package fr.eni.jeu.reversi.ihm;

import java.util.Scanner;

import fr.eni.jeu.reversi.model.Coup;
import fr.eni.jeu.reversi.model.Pion;
import fr.eni.jeu.reversi.model.Player;

public class Console implements InterfaceIhm {
	
	private String[] puces = {"-", "☺", "☻"};

	@Override
	public void afficherPlateau(Pion[][] plateau) {
		StringBuilder plateauToString = new StringBuilder();
		
		// chiffres du haut
		plateauToString.append(" ");
		for (int i =0; i < plateau[0].length; i++) {
			plateauToString.append((i) +" ");
		}
		
//		retour à la ligne
		plateauToString.append("\n");
		
		for (int i = 0; i < plateau[0].length; i++) {
			plateauToString.append((i) + " ");
			for (int j = 0; j < plateau[0].length; j++) {
				plateauToString.append("["+j+" "+puces[plateau[j][i].ordinal()]+" "+i+"]");
				//plateauToString.append(plateau[i][j].name()+" ");
//				plateauToString.append(puces[plateau[i][j].ordinal()]);
			}
			plateauToString.append("\n\n");
		}
		
		System.out.println(plateauToString.toString());
		
	}

	@Override
	public void afficherScore(Pion p1, Pion p2, Pion libre) {
		StringBuilder afficherScoreToString = new StringBuilder();
		afficherScoreToString.append("Pion ");
		afficherScoreToString.append(p1.name());
		afficherScoreToString.append(" : ");
		afficherScoreToString.append(p1.getNombre()+"\n");
		afficherScoreToString.append("Pion ");
		afficherScoreToString.append(p2.name());
		afficherScoreToString.append(" : ");
		afficherScoreToString.append(p2.getNombre()+"\n");
		afficherScoreToString.append(libre.name());
		afficherScoreToString.append(" : ");
		afficherScoreToString.append(libre.getNombre()+"\n");
		
		int total =  p1.getNombre() + p2.getNombre() + libre.getNombre();
		if (total != 64 || libre.getNombre() < 0) {
			afficherScoreToString.append("!!!!!!!!!!!!!!!!!!!!!!!!!!dfsd gfsdgsfdg!!!!g!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ATTENTION > " + total +"\n");
		}
		System.out.println(afficherScoreToString.toString());
	}
	
	@Override
	public void afficherFin(Player player) {
		System.out.println("Le joueur " + player.getName() +" ("+ player.getP().name() + ")" +" ne peuvent plus jouer");
		
	}

	@Override
	public void afficherImpossible(Pion p, Coup c) {
		System.out.println("Impossible au Pion " + p.name() + " de jouer à " + c.toString());
	}

	@Override
	//public int[] essaiUtilisateur(Pion p) {
	public Coup essaiUtilisateur(Pion p) {
		System.out.println("Au tour du pion "+p.name()+" de jouer");
		Coup newCoup = new Coup();
		//int[] colonneLigne = new int[2];
		Scanner scan = new Scanner(System.in);
		System.out.println("X (colonne) : ");
		//colonneLigne[0] =  scan.nextInt();
		newCoup.setX(scan.nextInt());
//		colonneLigne[0] =  scan.nextInt()-1;
		scan.nextLine();
		System.out.println("Y (ligne) : ");
		//colonneLigne[1] =  scan.nextInt();
		newCoup.setY(scan.nextInt());
//		colonneLigne[1] =  scan.nextInt()-1;
		scan.nextLine();
		//return colonneLigne;
		return newCoup;
	}

	@Override
	public void afficherPose(Coup coup) {
		System.out.println("résultat : " + coup.toString());
	}

	/* (non-Javadoc)
	 * @see fr.eni.jeu.reversi.ihm.InterfaceIhm#afficherVainqueur(fr.eni.jeu.reversi.model.Player[])
	 */
	@Override
	public void afficherVainqueur(Player[] players) {
		if (players[0].getP().getNombre() > players[1].getP().getNombre()) {
			System.out.println(players[0].getName() + " a gagné");
			System.out.println(players[1].getName() + " a perdu");
		} else {
			System.out.println(players[1].getName() + " a gagné");
			System.out.println(players[0].getName() + " a perdu");
		}
		
	}
	
	

}
