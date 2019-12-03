/**
 * 
 */
package fr.eni.projetweb.bo;

import java.util.Date;

/**
 * Classe en charge de 
 * @version Projet1 - V1.0
 * @author jpietras2019
 * @date 2 déc. 2019 - 14:28:43
 *
 */
public class Article {

		private int noArticle;
		private String nomArticle;
		private String descriptionArticle;
		private Date dateDebutEncheres;
		private Date dateFinEncheres;
		private int prixInitial;
		private int prixVente;
		private Utilisateur utilisateur;
		private Categorie categorie;
		
		/**
		 * Constructeur.
		 */
		public Article() {
			// TODO Auto-generated constructor stub
		}
		
		public Article(String nomArticle, String descriptionArticle, Date dateDebutEncheres, Date dateFinEncheres, Utilisateur utilisateur, Categorie categorie) {
			setNomArticle(nomArticle);
			setDescriptionArticle(descriptionArticle);
			setDateDebutEncheres(dateDebutEncheres);
			setDateFinEncheres(dateFinEncheres);
			setUtilisateur(utilisateur);
			setCategorie(categorie);
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
		 * Getter pour categorie.
		 * @return the categorie
		 */
		public Categorie getCategorie() {
			return categorie;
		}

		/**
		 * Setter pour categorie.
		 * @param categorie the categorie to set
		 */
		public void setCategorie(Categorie categorie) {
			this.categorie = categorie;
		}

		/**
		 * Getter pour noArticle.
		 * @return the noArticle
		 */
		public int getNoArticle() {
			return noArticle;
		}
		/**
		 * Setter pour noArticle.
		 * @param noArticle the noArticle to set
		 */
		public void setNoArticle(int noArticle) {
			this.noArticle = noArticle;
		}
		/**
		 * Getter pour nomArticle.
		 * @return the nomArticle
		 */
		public String getNomArticle() {
			return nomArticle;
		}
		/**
		 * Setter pour nomArticle.
		 * @param nomArticle the nomArticle to set
		 */
		public void setNomArticle(String nomArticle) {
			this.nomArticle = nomArticle;
		}
		/**
		 * Getter pour descriptionArticle.
		 * @return the descriptionArticle
		 */
		public String getDescriptionArticle() {
			return descriptionArticle;
		}
		/**
		 * Setter pour descriptionArticle.
		 * @param descriptionArticle the descriptionArticle to set
		 */
		public void setDescriptionArticle(String descriptionArticle) {
			this.descriptionArticle = descriptionArticle;
		}
		/**
		 * Getter pour dateDebutEncheres.
		 * @return the dateDebutEncheres
		 */
		public Date getDateDebutEncheres() {
			return dateDebutEncheres;
		}
		/**
		 * Setter pour dateDebutEncheres.
		 * @param dateDebutEncheres the dateDebutEncheres to set
		 */
		public void setDateDebutEncheres(Date dateDebutEncheres) {
			this.dateDebutEncheres = dateDebutEncheres;
		}
		/**
		 * Getter pour dateFinEncheres.
		 * @return the dateFinEncheres
		 */
		public Date getDateFinEncheres() {
			return dateFinEncheres;
		}
		/**
		 * Setter pour dateFinEncheres.
		 * @param dateFinEncheres the dateFinEncheres to set
		 */
		public void setDateFinEncheres(Date dateFinEncheres) {
			this.dateFinEncheres = dateFinEncheres;
		}
		/**
		 * Getter pour prixInitial.
		 * @return the prixInitial
		 */
		public int getPrixInitial() {
			return prixInitial;
		}
		/**
		 * Setter pour prixInitial.
		 * @param prixInitial the prixInitial to set
		 */
		public void setPrixInitial(int prixInitial) {
			this.prixInitial = prixInitial;
		}
		/**
		 * Getter pour prixVente.
		 * @return the prixVente
		 */
		public int getPrixVente() {
			return prixVente;
		}
		/**
		 * Setter pour prixVente.
		 * @param prixVente the prixVente to set
		 */
		public void setPrixVente(int prixVente) {
			this.prixVente = prixVente;
		}
		
		
		
}
