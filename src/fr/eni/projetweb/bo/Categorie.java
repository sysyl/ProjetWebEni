/**
 * 
 */
package fr.eni.projetweb.bo;

/**
 * Classe en charge de 
 * @version Projet1 - V1.0
 * @author jpietras2019
 * @date 2 déc. 2019 - 14:34:20
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
	 * @param noCategorie
	 * @param libelle
	 */
	public Categorie(String libelle) {
		setLibelle(libelle);
	}

	/**
	 * Getter pour noCategorie.
	 * @return the noCategorie
	 */
	public int getNoCategorie() {
		return noCategorie;
	}
	/**
	 * Setter pour noCategorie.
	 * @param noCategorie the noCategorie to set
	 */
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	/**
	 * Getter pour libelle.
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * Setter pour libelle.
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	
}
