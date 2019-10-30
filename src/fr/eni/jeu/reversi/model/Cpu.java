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
public class Cpu extends Player {
	private final int variableCpu = 4;
	private List<Coup> listeCoups = new ArrayList<>();

	/**
	 * 
	 */
	public Cpu() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param p
	 * @param name
	 * @param isActive
	 */
	public Cpu(Pion p, String name, boolean isActive) {
		super(p, name, isActive);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param listeCoup
	 */
	public Cpu(List<Coup> listeCoups) {
		super();
		this.listeCoups = listeCoups;
	}

	/**
	 * @return the listeCoup
	 */
	public List<Coup> getListeCoups() {
		return listeCoups;
	}

	/**
	 * @param listeCoup
	 *            the listeCoup to set
	 */
	public void setListeCoup(List<Coup> listeCoups) {
		this.listeCoups = listeCoups;
	}

	/**
	 * calcule le coup de l'ordinateur Choisi le coup qui aura le plus de pion gagné
	 * ajout d'une variable de hasard
	 * 
	 * @return
	 */
	public Coup getBestCoup() {
		Coup coupCpu = new Coup();
		if (!this.listeCoups.isEmpty()) {
			if (this.listeCoups.size() > 1) {

				int nbMax = this.listeCoups.get(0).getPionsGagnes();
				coupCpu = this.listeCoups.get(0);
				
				for (int i = 1; i < this.listeCoups.size(); i++) {
					if (nbMax < this.listeCoups.get(i).getPionsGagnes()) {
						coupCpu = this.listeCoups.get(i);
						nbMax = coupCpu.getPionsGagnes();
					}
				}
				System.out.println("Cpu va jouer " + coupCpu.toString() + ", nbMax = " + nbMax);

				// Random rand = new Random();
				// int nb = rand.nextInt(12);
				// System.out.println("nb%variableCpu = " + nb + " = " + nb % variableCpu);
				// if (nb % variableCpu == 50) {
				// nb = rand.nextInt(this.listeCoups.size() - 1);
				// coupCpu = this.listeCoups.get(nb);
				// System.out.println("Cpu va jouer " + coupCpu.toString() + "nb dans la liste
				// des coups = " + nb);
				// } else {
				// int nbMax = 0;
				// for (int i = 0; i < this.listeCoups.size(); i++) {
				// if (nbMax < this.listeCoups.get(i).getPionsGagnes()) {
				// coupCpu = this.listeCoups.get(i);
				// nbMax = coupCpu.getPionsGagnes();
				// }
				// }
				// System.out.println("Cpu va jouer " + coupCpu.toString() + ", nbMax = " +
				// nbMax);
				// }
			} else {
				coupCpu = this.listeCoups.get(0);
			}
		}

		return coupCpu;
	}

}
