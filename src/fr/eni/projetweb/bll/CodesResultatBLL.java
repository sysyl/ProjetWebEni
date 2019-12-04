package fr.eni.projetweb.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	/**
	 * Echec le pseudo de l'utilisateur est obligatoire et ne doit pas d�passer 30 caract�res ! 
	 */
	public static final int REGLE_PSEUDO_NOM_ERREUR=20000;
	
	/**
	 * Echec le nom de l'utilisateur est obligatoire et ne doit pas d�passer 30 caract�res ! 
	 */
	public static final int REGLE_NOM_UTILISATEUR_NOM_ERREUR = 20001;
	/**
	 * Echec le pr�nom de l'utilisateur est obligatoire et ne doit pas d�passer 30 caract�res ! 
	 */
	public static final int REGLE_PRENOM_UTILISATEUR_NOM_ERREUR = 20002;
	/**
	 * Echec l'email est obligatoire et ne doit pas d�passer 30 caract�res ! 
	 */
	public static final int REGLE_EMAIL_NOM_ERREUR = 20003;
	/**
	 * Echec le nom de la rue est obligatoire et ne doit pas d�passer 30 caract�res ! 
	 */
	public static final int REGLE_RUE_NOM_ERREUR = 20004;
	
	/**
	 * Echec le code postal doit correspondre � une suite de 5 chiffres (ex.: 44000) ! 
	 */
	public static final int REGLE_CODE_POSTAL_NOM_ERREUR = 20005;
	
	/**
	 * Echec le nom de la ville est obligatoire et ne doit pas d�passer 30 caract�res ! 
	 */
	public static final int REGLE_NOM_VILLE_NOM_ERREUR = 20006;
	/**
	 * Echec le mot de passe est vide
	 */
	public static final int REGLE_MOT_DE_PASSE_NOM_ERREUR = 20007;
	/**
	 * Echec la confirmation de mot de passe est vide
	 */
	public static final int REGLE_CONFIRMATION_MOT_DE_PASSE_NOM_ERREUR = 20008;
	/**
	 * Echec les deux mots de passe doivent etre identiques
	 */
	public static final int REGLE_MOTS_DE_PASSE_IDENTIQUES_NOM_ERREUR = 20009;
	/**
	 * Echec le champ pseudo acc�pte uniquement les caract�res alphanum�riques ! Les caract�res sp�ciaux ne sont pas autoris�s!
	 */
	public static final int REGLE_PSEUDO_ALPHANUMERIQUE_ERREUR = 20011;
	/**
	 * Echec le pseudo choisi existe deja
	 */
	public static final int REGLE_PSEUDO_DUPLICATION_ERREUR = 20012;
	/**
	 * Echec l'email choisi existe deja
	 */
	public static final int REGLE_EMAIL_DUPLICATION_ERREUR = 20013;
	/**
	 * Erreur survenu lors de la récupération des données dans la base de données!
	 * (erreur select)
	 */
	public static final int REGLE_RECUPERATION_BDD_ERREUR = 20010;
	

	
}
