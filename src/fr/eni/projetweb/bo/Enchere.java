/**
 * 
 */
package fr.eni.projetweb.bo;

import java.util.Date;

/**
 * Classe en charge de 
 * @version Projet 1 - V1.0
 * @author euznansk2019
 * @date 2 déc. 2019 - 14:33:50
 *
 */
public class Enchere {
	
	private Utilisateur utilisateur;
	private Date dateEnchere;
	private int montantEnchere;
	
	public Enchere() {
		
	}

	/**
	 * Constructeur.
	 * @param utilisateur
	 * @param dateEnchere
	 * @param montantEnchere
	 */
	public Enchere(Utilisateur utilisateur, Date dateEnchere, int montantEnchere) {
		super();
		this.utilisateur = utilisateur;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	/**
	 * Getter pour utilisateur.
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/**
	 * Setter pour utilisateur.
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	/**
	 * Getter pour dateEnchere.
	 * @return the dateEnchere
	 */
	public Date getDateEnchere() {
		return dateEnchere;
	}

	/**
	 * Setter pour dateEnchere.
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	/**
	 * Getter pour montantEnchere.
	 * @return the montantEnchere
	 */
	public int getMontantEnchere() {
		return montantEnchere;
	}

	/**
	 * Setter pour montantEnchere.
	 * @param montantEnchere the montantEnchere to set
	 */
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Enchere [utilisateur=" + utilisateur + ", dateEnchere=" + dateEnchere + ", montantEnchere="
				+ montantEnchere + "]";
	}

	
	

}
