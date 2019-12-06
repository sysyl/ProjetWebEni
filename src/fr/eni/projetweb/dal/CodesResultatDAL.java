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
	 * Une erreur non g�r�e est survenue lors de l'enrgistrement des informations.
	 */
	public static final int INSERT_OBJET_ECHEC = 10002;
	/**
	 * Une erreur non g�r�e est survenue lors de le l'affichage de la liste d'articles.
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
	 * Erreur lors de la récupération de l'article
	 */
	public static final int REGLE_ARTICLE_ERREUR = 10008;
	/**
	 * Erreur lors de la récupération du retrait selon id
	 */
	public static final int REGLE_RETRAIT_ERREUR = 10009;
	
	
	
		
}
