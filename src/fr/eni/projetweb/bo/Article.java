/**
 * 
 */
package fr.eni.projetweb.bo;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
		private Timestamp debutEncheres;
		private Timestamp finEncheres;
		private int prixInitial;
		private int prixVente;
		private Utilisateur utilisateur;
		private int noUtilisateur;
		private Categorie categorie;
		private Retrait retrait;
		private String pathImg;

		/**
		 * Constructeur.
		 */
		public Article(String nomArticle,Integer prixVente, Timestamp finEncheres, Integer noUtilisateur) {
			this.nomArticle = nomArticle;
			this.prixVente = prixVente;
			this.finEncheres = finEncheres;
			this.noUtilisateur = noUtilisateur;
			}
		
		public Article() {
			
		}
		
		public Article(String nomArticle, String descriptionArticle, Timestamp debutEncheres, Timestamp finEncheres, int prixInitial, 
				int prixVente, Utilisateur utilisateur, Categorie categorie, Retrait retrait,String pathImg ) {
			setNomArticle(nomArticle);
			setDescriptionArticle(descriptionArticle);
			setDebutEncheres(debutEncheres);
			setFinEncheres(finEncheres);
			setUtilisateur(utilisateur);
			setCategorie(categorie);
			setRetrait(retrait);
			setPathImg(pathImg);
		}
		
		public Article(String nomArticle, String descriptionArticle, Timestamp debutEncheres, Timestamp finEncheres, Utilisateur utilisateur, Categorie categorie, Retrait retrait,String pathImg ) {
			setNomArticle(nomArticle);
			setDescriptionArticle(descriptionArticle);
			setDebutEncheres(debutEncheres);
			setFinEncheres(finEncheres);
			setUtilisateur(utilisateur);
			setCategorie(categorie);
			setRetrait(retrait);
			setPathImg(pathImg);
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
		 * Getter pour noUtilisateur.
		 * @return the noUtilisateur
		 */
		public int getNoUtilisateur() {
			return noUtilisateur;
		}

		/**
		 * Setter pour noUtilisateur.
		 * @param noUtilisateur the noUtilisateur to set
		 */
		public void setNoUtilisateur(int noUtilisateur) {
			this.noUtilisateur = noUtilisateur;
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
		public Timestamp getDebutEncheres() {
			return debutEncheres;
		}
		/**
		 * Setter pour dateDebutEncheres.
		 * @param dateDebutEncheres the dateDebutEncheres to set
		 */
		public void setDebutEncheres(Timestamp debutEncheres) {
			this.debutEncheres = debutEncheres;
		}
		/**
		 * Getter pour dateFinEncheres.
		 * @return the dateFinEncheres
		 */
		public Timestamp getFinEncheres() {
			return finEncheres;
		}
		/**
		 * Setter pour dateFinEncheres.
		 * @param dateFinEncheres the dateFinEncheres to set
		 */
		public void setFinEncheres(Timestamp finEncheres) {
			this.finEncheres = finEncheres;
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

		/**
		 * Getter pour retrait.
		 * @return the retrait
		 */
		public Retrait getRetrait() {
			return retrait;
		}

		/**
		 * Setter pour retrait.
		 * @param retrait the retrait to set
		 */
		public void setRetrait(Retrait retrait) {
			this.retrait = retrait;
		}

		/**
		 * Getter pour pathImg.
		 * @return the pathImg
		 */
		public String getPathImg() {
			return pathImg;
		}

		/**
		 * Setter pour pathImg.
		 * @param pathImg the pathImg to set
		 */
		public void setPathImg(String pathImg) {
			this.pathImg = pathImg;
		}
		
		
		
}
