/**
 * 
 */
package fr.eni.jeu.reversi.model;

/**
 * @author frederic.bourelle
 *
 */
public class Plateau implements Cloneable {
	
	private int longueur;
	private int largeur;
	private Pion[][] tableauPlateau;
	private boolean lecturePossible = true;
	
	/**
	 * constructeur vide
	 */
	public Plateau()  {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param longueur
	 * @param largeur
	 */
	public Plateau(int longueur, int largeur) {
		this.longueur = longueur;
		this.largeur = largeur;
		this.tableauPlateau = new Pion[longueur][largeur];
	}
	
	public Plateau(int longueur, int largeur, Pion[][] tableauPlateau) {
		this.longueur = longueur;
		this.largeur = largeur;
		this.tableauPlateau = tableauPlateau;
	}
	
	/**
	 * methode clone pour cloner le tableau
	 */
	@Override
	public Object clone() {
		Object o = null;
		
		try {
			o = super.clone();
		} catch(CloneNotSupportedException cnse) {
			cnse.printStackTrace(System.err);
		}
		
		return o;
	}
	
	/**
	 * Methode en charge de poser un pion sur une case
	 * @param coup
	 * @return
	 */
	public int poser(Coup coup, Player player) {
		int resultat = tester(player.getP(), coup, true);
		return resultat;
	}
	
	/**
	 * 
	 * @param p
	 * @param coup
	 * @param modifier
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
			if (coup.getX() != this.largeur - 1 && coup.getY() != 0) {
				int nbTest = direction(p, coup, +1, -1, false);
				if (nbTest > 0) System.out.println("Haut Droite / nbTest = " + nbTest);
				test += nbTest;
				if (nbTest > 0 && modifier == true) {
					direction(p, coup, +1, -1, modifier);
				}
			}
			// droite
			if (coup.getX() != this.largeur - 1) {
				int nbTest = direction(p, coup, +1, 0, false);
				if (nbTest > 0) System.out.println("Droite / nbTest = " + nbTest);
				test += nbTest;
				if (nbTest > 0 && modifier == true) {
					direction(p, coup, +1, 0, modifier);
				}
			}
			// bas droite
			if (coup.getX() != this.largeur - 1 && coup.getY() != this.longueur - 1) {
				int nbTest = direction(p, coup, +1, +1, false);
				if (nbTest > 0) System.out.println("Bas Droite / nbTest = " + nbTest);
				test += nbTest;
				if (nbTest > 0 && modifier == true) {
					direction(p, coup, +1, +1, modifier);
				}
			}
			// bas
			if (coup.getY() != this.longueur - 1) {
				int nbTest = direction(p, coup, 0, +1, false);
				if (nbTest > 0) System.out.println("Bas / nbTest = " + nbTest);
				test += nbTest;
				if (nbTest > 0 && modifier == true) {
					direction(p, coup, 0, +1, modifier);
				}
			}
			// bas gauche
			if (coup.getX() != 0 && coup.getY() != this.longueur - 1) {
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
	
	private int direction(Pion p, Coup coup, int x_direction, int y_direction, boolean modifier) {

		lecturePossible = true;
		int test = 0;

		// la coordonnée x sur laquelle on va itérer
		int x = coup.getX();
		// la coordonnée y sur laquelle on va itérer
		int y = coup.getY();
		// la case sur laquelle on pose change
		if (modifier) {
			this.tableauPlateau[coup.getX()][coup.getY()] = p;
		}

		if (coup.getX() < this.largeur) {
			if (coup.getX() == this.largeur - 1 && x_direction == 1) {
				lecturePossible = false;
			} else {
				x += x_direction;
			}
		}
		if (coup.getY() < this.longueur) {
			if (coup.getY() == this.longueur - 1 && y_direction == 1) {
				// System.out.println("pas possible");
				lecturePossible = false;
			} else {
				y += y_direction;
			}
		}

		// on vérifie d'abord si la case à côté est un pion opposé
		if (!this.tableauPlateau[x][y].equals(Pion.LIBRE) && !this.tableauPlateau[x][y].equals(p) && lecturePossible) {
			// si pion opposé = ok pour le 1er pion
			if (this.tableauPlateau[x][y].equals(p.autrePion(p))) {
				test++;			
				// System.out.println(" / test 1 = " + test);
				if (modifier) {
					this.tableauPlateau[x][y] = p;
					int nbPions = this.tableauPlateau[x][y].getNombre();
					this.tableauPlateau[x][y].setNombre(nbPions++);
				}
			}
			// si pion opposé = ok pour le 1er pion, on le modifie

			// on boucle tant qu'on arrive sur un pion différent et que l'on n'arrive pas au
			// bord du cadre du jeu (0, 0, LONGUEUR, LARGEUR)

			if (modifier) {

				x = testDirection(x, y, x_direction, y_direction)[0];
				y = testDirection(x, y, x_direction, y_direction)[1];

				while (x >= 0 && y >= 0 && x <= this.largeur - 1 && y <= this.longueur - 1
						&& this.tableauPlateau[x][y].equals(p.autrePion(p)) && lecturePossible == true) {

					this.tableauPlateau[x][y] = p;
					int nbPions = this.tableauPlateau[x][y].getNombre();
					this.tableauPlateau[x][y].setNombre(nbPions++);

					x = testDirection(x, y, x_direction, y_direction)[0];
					y = testDirection(x, y, x_direction, y_direction)[1];
				}

			} else {

				x = testDirection(x, y, x_direction, y_direction)[0];
				y = testDirection(x, y, x_direction, y_direction)[1];

				while (x >= 0 && y >= 0 && x <= this.largeur - 1 && y <= this.longueur - 1
						&& this.tableauPlateau[x][y].equals(p.autrePion(p)) && lecturePossible == true) {

					if (this.tableauPlateau[x][y].equals(p.autrePion(p))) {
						test++;
						// System.out.println(" / test 2 = " + test);
					}
					
					x = testDirection(x, y, x_direction, y_direction)[0];
					y = testDirection(x, y, x_direction, y_direction)[1];
				}
			}

			// si à la fin de la boucle le pion n'est pas identique au sien
			if (!this.tableauPlateau[x][y].equals(p)) {
				test = 0;
				// System.out.println(" /test 3 = " + test + " " + tableauPlateau[x][y].name());
			}
		}
		return test;
	}

	private int[] testDirection(int x, int y, int x_direction, int y_direction) {

		int xY[] = { x, y };


		if ((x == 0 && x_direction == -1) || (x == this.largeur - 1 && x_direction == 1)) {
			lecturePossible = false;
		} else {
			x += x_direction;
			xY[0] = x;
		}

		if ((y == 0 && y_direction == -1) || (y == this.longueur - 1 && y_direction == 1)) {
			lecturePossible = false;
		} else {
			y += y_direction;
			xY[1] = y;
		}

		return xY;
	}
	
	

}
