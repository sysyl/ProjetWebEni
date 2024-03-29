/**
 * 
 */
package fr.eni.projetweb.bo;

/**
 * Classe en charge de
 * 
 * @version Projet1 - V1.0
 * @author jpietras2019
 * @date 2 d�c. 2019 - 14:34:20
 *
 */
public class Categorie {
	private int noCategorie;
	private String libelle;

	/**
	 * Constructeur.
	 */
	public Categorie() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructeur.
	 * 
	 * @param libelle
	 */
	public Categorie(String libelle) {
		setLibelle(libelle);
	}

	/**
	 * Constructeur.
	 * 
	 * @param noCategorie
	 * @param libelle
	 */
	public Categorie(int noCategorie, String libelle) {
		this(libelle);
		setNoCategorie(noCategorie);

	}

	/**
	 * Getter pour noCategorie.
	 * 
	 * @return the noCategorie
	 */
	public int getNoCategorie() {
		return noCategorie;
	}

	/**
	 * Setter pour noCategorie.
	 * 
	 * @param noCategorie
	 *                        the noCategorie to set
	 */
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	/**
	 * Getter pour libelle.
	 * 
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Setter pour libelle.
	 * 
	 * @param libelle
	 *                    the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + "]";
	}

}
