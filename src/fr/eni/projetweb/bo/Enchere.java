/**
 * 
 */
package fr.eni.projetweb.bo;

import java.sql.Timestamp;

/**
 * Classe en charge de 
 * @version Projet 1 - V1.0
 * @author euznansk2019
 * @date 2 d�c. 2019 - 14:33:50
 *
 */
public class Enchere implements Comparable<Enchere>{
	
	private Utilisateur utilisateur;
	private Timestamp dateEnchere;
	private Article article;
	private int montantEnchere;
	
	public Enchere() {
		
	}

	/**
	 * Constructeur.
	 * @param utilisateur
	 * @param dateEnchere
	 * @param montantEnchere
	 */
	public Enchere(Utilisateur utilisateur, Timestamp dateEnchere, Article article, int montantEnchere) {
		super();
		this.utilisateur = utilisateur;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.article = article;
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
	public Timestamp getDateEnchere() {
		return dateEnchere;
	}

	/**
	 * Setter pour dateEnchere.
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(Timestamp dateEnchere) {
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
	 * Getter pour article.
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * Setter pour article.
	 * @param article the article to set
	 */
	public void setArticle(Article article) {
		this.article = article;
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

	/**
	 * {@inheritDoc}
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Enchere e) {
		
		
		
		  if(this.getDateEnchere().after(e.getDateEnchere())) {
			  return 1; 
		  }
		  if(this.getDateEnchere().after(e.getDateEnchere())) {
			  return -1;
		  }
		  else                  
			  return 0;
		
	}

	
	

}
