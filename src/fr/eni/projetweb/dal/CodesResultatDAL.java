/**
 * 
 */
package fr.eni.projetweb.dal;

/**
 * Classe en charge de 
 * @version ProjetWebENI - V1.0
 * @author euznansk2019
 * @date 4 d�c. 2019 - 13:58:49
 *
 */
public class CodesResultatDAL {
	/**
	 * Echec lors de l'affichage des donn�es de l'utilisateur
	 */
	public static final int REGLE_UTILISATEUR_ERREUR=10000;
	/**
	 * Echec lors de l'insertion d'objet -> objet null
	 */
	public static final int INSERT_OBJET_NULL = 10001;
	/**
	 * Une erreur non geree est survenue lors de l'enrgistrement des informations.
	 */
	public static final int INSERT_OBJET_ECHEC = 10002;
	/**
	 * Une erreur non geree est survenue lors de le l'affichage de la liste d'articles.
	 */
	public static final int LECTURE_ARTICLE_ECHEC=10003;
	/**
	 * Une erreur est survenue lors de la mise a jour des informations.
	 */
	public static final int UPDATE_USER_ERREUR = 10004;
	/**
	 * Erreur survenue lors de la suppression du compte utilisateur.
	 */
	public static final int DELETE_USER_ERREUR = 10005;
	/*
	 * Erreur lors de la r�cup�ration des donn�es de la base de donn�es.
	 */
	public static final int SELECT_ERREUR = 10006;
	
	/*
	 * Erreur lors de la connexion : mdp ou login incorrect
	 */
	public static final int UTILISATEUR_VALIDATION_ERREUR = 10007;
	/*
	 * Erreur lors de la recuperation de l'article
	 */
	public static final int REGLE_ARTICLE_ERREUR = 10008;
	/**
	 * Erreur lors de la recuperation du retrait selon id
	 */
	public static final int REGLE_RETRAIT_ERREUR = 10009;
	
	/*
	 * Erreur lors de la tentative de parse de date
	 */
	public static final int PARSE_DATE_ERREUR = 10010;
	/*
	 *Erreur lors de l'insertion de la date (classe Article) dans la bdd 
	 */
	public static final int DATE_BDD_ERREUR = 10011;
	
	/*
	 * Echec lors de l'update du statut retrait a : retire
	 */
	public static final int RETRAIT_STATUT_ERREUR = 10012;
	/**
	 * L'utilisateur a essaye de se connecter avec un mot de passe ou login invalide
	 */
	public static final int REGLE_IDENTIFIANTS_INCORRECTS_ERREUR = 10013;
	/*
	 * Erreur lors de la suppression d'article
	 */
	public static final int DELETE_ARTICLE_ECHEC = 10014;
	
	/**
	 * Echec lors de l'update d'utilisateur
	 */
	public static final int UPDATE_ARTICLE_ERREUR = 10015;
	
	/**
	 * Echec lors de la mise a jour de l'adresse du retrait
	 */
	public static final int UPDATE_RETRAIT_ERREUR = 10016;
	
	/*
	 * erreur lors de l'insertion des donnees dans la base de donnees
	 * - > limite varchar depassee
	 */
	public static final int DEPASSEMENT_VARCHAR_LIMIT = 10017;
	
	
	
	
	
		
}
