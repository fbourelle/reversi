package fr.eni.jeu.reversi.logical;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.jeu.reversi.ihm.Console;
import fr.eni.jeu.reversi.model.Coup;
import fr.eni.jeu.reversi.model.Cpu;
import fr.eni.jeu.reversi.model.Pion;
import fr.eni.jeu.reversi.model.Player;

/**
 * 
 * Classe en charge de la logique du jeu
 * 
 * @author fbourell2018
 * @version TP_Reversi - V1.0
 * @date 4 févr. 2019 - 13:42:22
 *
 */
public class PlateauDeReversi {

	public final int LARGEUR = 8;
	public final int LONGUEUR = 8;
	public Console ihm;
	public Pion[][] tableauPlateau = new Pion[LARGEUR][LONGUEUR];
	public static int nbCoups = 0;
	public boolean[] joueursEtats = { true, true };
	public boolean lecturePossible = true;
	public Player[] players = new Player[2];

	private Logger logReversi = LoggerFactory.getLogger("fr.eni.jeu.reversi.logical.PlateauDeReversi");

	/**
	 * 
	 * Constructeur.
	 * 
	 * @param ihm
	 */
	public PlateauDeReversi(Console ihm) {
		this.ihm = ihm;
		init();
	}

	/**
	 * Initialise le plateau de Reversi
	 */
	public void init() {
		for (int i = 0; i < LARGEUR; i++) {
			for (int j = 0; j < LONGUEUR; j++) {
				tableauPlateau[j][i] = Pion.LIBRE;
			}
		}
		tableauPlateau[3][3] = Pion.BLANC;
		tableauPlateau[3][4] = Pion.NOIR;
		tableauPlateau[4][3] = Pion.NOIR;
		tableauPlateau[4][4] = Pion.BLANC;
	}

	/**
	 * Crée 2 joueurs : 1 noir et 1 blanc, Lance le jeu
	 */
	public void jouer() {
		Pion joueur1 = Pion.NOIR;
		Pion joueur2 = Pion.BLANC; // CPU
		Pion caseLibre = Pion.LIBRE;

		Player player1 = new Player(Pion.NOIR, "Mario", true);
		Player player2 = new Player(Pion.BLANC, "Luigi", true);
		Cpu cpu = new Cpu(Pion.BLANC, "Deep blue", true);

		players[0] = player1;
		players[1] = cpu; // on joue contre l'ordi

		// int coup[] = new int[2];

		// do
		// tant que les blancs ou les noirs peuvent jouer
		// while (joueursEtats[0] == true || joueursEtats[1] == true) {
		while (players[0].isActive() || players[1].isActive()) {
			int peutJouer = 0;

			if (nbCoups % 2 == 0) {

				if (peutJouer(players[0], 0)) {
					players[0].setActive(true);
					while (peutJouer == 0) {
						afficher();
						// coup = ihm.essaiUtilisateur(joueur1);
						Coup coupJoue = new Coup();
						coupJoue = ihm.essaiUtilisateur(players[0].getP());

						System.out.println(coupJoue.toString());

						coupJoue.setPionsGagnes(tester(players[0].getP(), coupJoue, false));
						// peutJouer = tester(joueur1, coupJoue, false);

						if (coupJoue.getPionsGagnes() > 0) {
							peutJouer = coupJoue.getPionsGagnes();
							poser(players[0], coupJoue);
						} else {
							ihm.afficherImpossible(players[0].getP(), coupJoue);
						}
					}
				}
				nbCoups++;
			} else { // Le joueur 2

				if (peutJouer(players[1], 1)) {
					players[1].setActive(true);
					while (peutJouer == 0) {
						
						afficher();
						if ((players[1] instanceof Cpu)) { // si c'est un CPU
							System.out.println("Le CPU va poser");

							if (((Cpu) players[1]).getListeCoups().size() > 0) {
								poser(players[1], ((Cpu) players[1]).getBestCoup());
							}
							
							peutJouer = 1;

						} else {
							// coup = ihm.essaiUtilisateur(joueur2);
							Coup coupJoue = new Coup();
							coupJoue = ihm.essaiUtilisateur(players[1].getP());

							System.out.println(coupJoue.toString());

							peutJouer = tester(players[1].getP(), coupJoue, false);

							if (peutJouer > 0) {
								poser(players[1], coupJoue);
							} else {
								ihm.afficherImpossible(players[1].getP(), coupJoue);
							}
						}
					}
				}

				nbCoups++;
			}

			// afficher();
			ihm.afficherScore(players[0].getP(), players[1].getP(), caseLibre);

		}
		;
		// while (joueursEtats[0] != false && joueursEtats[1] != false);

		System.out.println("Fin de la partie");
		afficher();
		ihm.afficherScore(players[0].getP(), players[1].getP(), caseLibre);
		ihm.afficherVainqueur(players);
	}

	/**
	 * Demande à l'IHM d'afficher le plateau
	 */
	public void afficher() {
		ihm.afficherPlateau(tableauPlateau);
	}

	/**
	 * le joueur Pion
	 * 
	 * @param p
	 *            = le joueur qui joue
	 * @param x
	 *            = l'emplacement X
	 * @param y
	 *            = l'emplacement Y
	 */
	public void poser(Player player, Coup coup) {
		coup.setPionsGagnes(tester(player.getP(), coup, true));
		player.getP().gagne(player.getP(), coup);
		ihm.afficherPose(coup);
	}

	/**
	 * Teste si le Pion a au moins 1 emplacement où il peut se poser
	 * 
	 * @param p
	 *            = le joueur
	 * @param index
	 *            = l'index du tableau joueursEtats : 0 = joueur1, 1 = joueur2
	 * @return
	 */
	public boolean peutJouer(Player player, int index) {
		boolean isCpu = false;
		if (player instanceof Cpu) {
			((Cpu) player).getListeCoups().clear(); // on vide la liste des coups du CPU
			isCpu = true;
		}
		int test = 0;
		int indexBoucle = 0;
		logReversi.debug("Debut peut Jouer : " + player.getP().name());
		Coup coupTest = new Coup();
		// tant que la valeur est égale à 0 on continue de parcourir le tableau
		while (indexBoucle < LARGEUR && test == 0) {
			for (int i = 0; i < LARGEUR; i++) {
				coupTest.setY(i);
				for (int j = 0; j < LONGUEUR; j++) {
					// logReversi.debug("On va tester ce point : " + j + ", " + i);
					coupTest.setX(j);
					if (tableauPlateau[j][i].equals(Pion.LIBRE)) {
						test = tester(player.getP(), coupTest, false);
						// logReversi.debug("On a terminé de tester ce point, résultat : " + test);
						// ça sort de la boucle si au moins on trouve une case où poser le pion

						if (test != 0) {
							System.out.println(coupTest.toString() + ", un coup qui rapporte : " + test);
							if (isCpu) {
								Coup coup4Cpu = new Coup(coupTest.getX(), coupTest.getY(), test);
								((Cpu) player).getListeCoups().add(coup4Cpu);
								System.out.println("isCpu");
							} else {
								
								break;
							}
						}

					} else {
						// logReversi.debug("Ce point n'est pas libre !!!!!!!!!!!!!!!!!!!!!!!!!!!! : " +
						// j + ", " + i);
					}
					indexBoucle++;
				}

				if (test != 0 && !isCpu) {
					System.out.println("???????????? " + test);
					break;
				}		
			}
		}

		if (isCpu) {
			System.out.println(((Cpu)player).getListeCoups().toString());
			if (((Cpu)player).getListeCoups().isEmpty()) {
				player.setActive(false);
				ihm.afficherFin(player);
			}
		} else {
			if (test == 0) {
				//joueursEtats[index] = false;
				player.setActive(false);
				ihm.afficherFin(player);
			}
		}

		// return joueursEtats[index];
		return player.isActive;
	}

	/**
	 * Teste chaque direction
	 * 
	 * @param p
	 * @param x
	 * @param y
	 * @param modifier
	 *            = si on teste ou si on pose pour de vrai
	 * @return
	 */
	public int tester(Pion p, Coup coup, boolean modifier) {
		System.out.println("tester " + coup.toString());
		int test = 0;
		// si on choisit une case qui n'est pas libre
		if (!tableauPlateau[coup.getX()][coup.getY()].equals(Pion.LIBRE)) {
			test = 0;
		} else {
			// on tourne autour de la place visée
			// gauche
			if (coup.getX() != 0) {
				int nbTest = direction(p, coup, -1, 0, false);
				if (nbTest > 0) System.out.println("Gauche / nbTest = " + nbTest);
				test += nbTest;
				if (nbTest > 0 && modifier == true) {
					direction(p, coup, -1, 0, modifier);
				}
			}
			// haut gauche
			if (coup.getX() != 0 && coup.getY() != 0) {
				int nbTest = direction(p, coup, -1, -1, false);
				if (nbTest > 0) System.out.println("Haut Gauche / nbTest = " + nbTest);
				test += nbTest;
				if (nbTest > 0 && modifier == true) {
					direction(p, coup, -1, -1, modifier);
				}
			}
			// haut
			if (coup.getY() != 0) {
				int nbTest = direction(p, coup, 0, -1, false);
				if (nbTest > 0) System.out.println("Haut / nbTest = " + nbTest);
				test += nbTest;
				if (nbTest > 0 && modifier == true) {
					direction(p, coup, 0, -1, modifier);
				}
			}
			// haut droite
			if (coup.getX() != LARGEUR - 1 && coup.getY() != 0) {
				int nbTest = direction(p, coup, +1, -1, false);
				if (nbTest > 0) System.out.println("Haut Droite / nbTest = " + nbTest);
				test += nbTest;
				if (nbTest > 0 && modifier == true) {
					direction(p, coup, +1, -1, modifier);
				}
			}
			// droite
			if (coup.getX() != LARGEUR - 1) {
				int nbTest = direction(p, coup, +1, 0, false);
				if (nbTest > 0) System.out.println("Droite / nbTest = " + nbTest);
				test += nbTest;
				if (nbTest > 0 && modifier == true) {
					direction(p, coup, +1, 0, modifier);
				}
			}
			// bas droite
			if (coup.getX() != LARGEUR - 1 && coup.getY() != LONGUEUR - 1) {
				int nbTest = direction(p, coup, +1, +1, false);
				if (nbTest > 0) System.out.println("Bas Droite / nbTest = " + nbTest);
				test += nbTest;
				if (nbTest > 0 && modifier == true) {
					direction(p, coup, +1, +1, modifier);
				}
			}
			// bas
			if (coup.getY() != LONGUEUR - 1) {
				int nbTest = direction(p, coup, 0, +1, false);
				if (nbTest > 0) System.out.println("Bas / nbTest = " + nbTest);
				test += nbTest;
				if (nbTest > 0 && modifier == true) {
					direction(p, coup, 0, +1, modifier);
				}
			}
			// bas gauche
			if (coup.getX() != 0 && coup.getY() != LONGUEUR - 1) {
				int nbTest = direction(p, coup, -1, +1, false);
				if (nbTest > 0) System.out.println("Bas Gauche / nbTest = " + nbTest);
				test += nbTest;
				if (nbTest > 0 && modifier == true) {
					direction(p, coup, -1, +1, modifier);
				}
			}
		}

		return test;
	}

	/**
	 * Teste 1 direction
	 * 
	 * @param p
	 *            = le pion qui joue
	 * @param x
	 *            = le point X à partir duquel on va partir
	 * @param y
	 *            = le point Y à partir duquel on va partir
	 * @param x_direction
	 *            = la direction en ligne (0, +1, -1)
	 * @param y_direction
	 *            = la direction en colonne (0, +1, -1)
	 * @param modifier
	 *            = si on va modifier les pions > l'emplacement a été testé
	 * @return un chiffre, si 0 alors le Pion ne peut pas se poser ici
	 */
	private int direction(Pion p, Coup coup, int x_direction, int y_direction, boolean modifier) {

		lecturePossible = true;
		int test = 0;

		// la coordonnée x sur laquelle on va itérer
		int x = coup.getX();
		// la coordonnée y sur laquelle on va itérer
		int y = coup.getY();
		// la case sur laquelle on pose change
		if (modifier) {
			tableauPlateau[coup.getX()][coup.getY()] = p;
		}

		if (coup.getX() < LARGEUR) {
			if (coup.getX() == LARGEUR - 1 && x_direction == 1) {
				lecturePossible = false;
			} else {
				x += x_direction;
			}
		}
		if (coup.getY() < LONGUEUR) {
			if (coup.getY() == LONGUEUR - 1 && y_direction == 1) {
				// System.out.println("pas possible");
				lecturePossible = false;
			} else {
				y += y_direction;
			}
		}

		// on vérifie d'abord si la case à côté est un pion opposé
		if (!tableauPlateau[x][y].equals(Pion.LIBRE) && !tableauPlateau[x][y].equals(p) && lecturePossible) {
			// si pion opposé = ok pour le 1er pion
			if (tableauPlateau[x][y].equals(p.autrePion(p))) {
				test++;			
				// System.out.println(" / test 1 = " + test);
				if (modifier) {
					tableauPlateau[x][y] = p;
					int nbPions = tableauPlateau[x][y].getNombre();
					tableauPlateau[x][y].setNombre(nbPions++);
				}
			}
			// si pion opposé = ok pour le 1er pion, on le modifie

			// on boucle tant qu'on arrive sur un pion différent et que l'on n'arrive pas au
			// bord du cadre du jeu (0, 0, LONGUEUR, LARGEUR)

			if (modifier) {

				x = testDirection(x, y, x_direction, y_direction)[0];
				y = testDirection(x, y, x_direction, y_direction)[1];

				while (x >= 0 && y >= 0 && x <= LARGEUR - 1 && y <= LONGUEUR - 1
						&& tableauPlateau[x][y].equals(p.autrePion(p)) && lecturePossible == true) {

					tableauPlateau[x][y] = p;
					int nbPions = tableauPlateau[x][y].getNombre();
					tableauPlateau[x][y].setNombre(nbPions++);

					x = testDirection(x, y, x_direction, y_direction)[0];
					y = testDirection(x, y, x_direction, y_direction)[1];
				}

			} else {

				x = testDirection(x, y, x_direction, y_direction)[0];
				y = testDirection(x, y, x_direction, y_direction)[1];

				while (x >= 0 && y >= 0 && x <= LARGEUR - 1 && y <= LONGUEUR - 1
						&& tableauPlateau[x][y].equals(p.autrePion(p)) && lecturePossible == true) {

					if (tableauPlateau[x][y].equals(p.autrePion(p))) {
						test++;
						// System.out.println(" / test 2 = " + test);
					}
					
					x = testDirection(x, y, x_direction, y_direction)[0];
					y = testDirection(x, y, x_direction, y_direction)[1];
				}
			}

			// si à la fin de la boucle le pion n'est pas identique au sien
			if (!tableauPlateau[x][y].equals(p)) {
				test = 0;
				// System.out.println(" /test 3 = " + test + " " + tableauPlateau[x][y].name());
			}
		}
		return test;
	}

	private int[] testDirection(int x, int y, int x_direction, int y_direction) {

		int xY[] = { x, y };


		if ((x == 0 && x_direction == -1) || (x == LARGEUR - 1 && x_direction == 1)) {
			lecturePossible = false;
		} else {
			x += x_direction;
			xY[0] = x;
		}

		if ((y == 0 && y_direction == -1) || (y == LONGUEUR - 1 && y_direction == 1)) {
			lecturePossible = false;
		} else {
			y += y_direction;
			xY[1] = y;
		}

		return xY;
	}
}
