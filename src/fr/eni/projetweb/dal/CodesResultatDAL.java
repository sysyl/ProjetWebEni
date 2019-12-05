/**
 * 
 */
package fr.eni.projetweb.dal;

/**
 * Classe en charge de 
 * @version ProjetWebENI - V1.0
 * @author euznansk2019
 * @date 4 déc. 2019 - 13:58:49
 *
 */
public class CodesResultatDAL {
	/**
	 * Echec lors de l'affichage des données de l'utilisateur
	 */
	public static final int REGLE_UTILISATEUR_ERREUR=10000;
	/**
	 * Echec lors de l'insertion d'objet -> objet null
	 */
	public static final int INSERT_OBJET_NULL = 10001;
	/**
	 * Une erreur non gérée est survenue lors de l'enrgistrement des informations.
	 */
	public static final int INSERT_OBJET_ECHEC = 10002;
	
	public static final int LECTURE_ARTICLE_ECHEC=10003;
	/**
	 * Une erreur non gérée est survenue lors de le l'affichage de la liste d'articles.
	 */
	
	/**
	 * Une erreur est survenue lors de la mise a jour des informations.
	 */
	public static final int UPDATE_USER_ERREUR = 10004;
	/**
	 * 
	 */
	public static final int DELETE_USER_ERREUR = 10005;
	
	
	
	
		
}
