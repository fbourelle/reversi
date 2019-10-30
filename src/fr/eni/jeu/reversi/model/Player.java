/**
 * 
 */
package fr.eni.jeu.reversi.model;

/**
 * @author frederic.bourelle
 *
 */
public class Player {
	private Pion p;
	private String name;
	public boolean isActive;

	/**
	 * 
	 */
	public Player() {
		
	}

	/**
	 * @param p
	 * @param name
	 * @param isActive
	 */
	public Player(Pion p, String name, boolean isActive) {
		super();
		this.p = p;
		this.name = name;
		this.isActive = isActive;
	}

	/**
	 * @return the p
	 */
	public Pion getP() {
		return p;
	}

	/**
	 * @param p the p to set
	 */
	public void setP(Pion p) {
		this.p = p;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [p=" + p + ", name=" + name + ", isActive=" + isActive + "]";
	}

}
