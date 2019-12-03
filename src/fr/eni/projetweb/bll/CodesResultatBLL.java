package fr.eni.projetweb.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	/**
	 * Echec le pseudo de l'utilisateur ne respecte pas les règles définies
	 */
	public static final int REGLE_PSEUDO_NOM_ERREUR=20000;
	/**
	 * Echec le nom de l'utilisateur ne respecte pas les règles définies
	 */
	public static final int REGLE_NOM_UTILISATEUR_NOM_ERREUR = 20001;
	/**
	 * Echec le prénom de l'utilisateur ne respecte pas les règles définies
	 */
	public static final int REGLE_PRENOM_UTILISATEUR_NOM_ERREUR = 20002;
	/**
	 * Echec l'email ne respecte pas les règles définies
	 */
	public static final int REGLE_EMAIL_NOM_ERREUR = 20003;
	/**
	 * Echec le nom de la rue ne respecte pas les règles définies
	 */
	public static final int REGLE_RUE_NOM_ERREUR = 20004;
	/**
	 * Echec le code postal ne respecte pas les règles définies
	 */
	public static final int REGLE_CODE_POSTAL_NOM_ERREUR = 20005;
	/**
	 * Echec le nom de la ville ne respecte pas les règles définies
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
	 * Echec les deux mots de passe doivent être identiques
	 */
	public static final int REGLE_MOTS_DE_PASSE_IDENTIQUES_NOM_ERREUR = 20009;
	/**
	 * Echec le code postal doit correspondre à une suite de chiffres
	 */
	public static final int REGLE_CODE_POSTAL_FORMAT_ERREUR = 20009;
	
	/**
	 * Echec le code postal ne peut pas être null
	 */
	public static final int REGLE_CODE_POSTAL_NULL = 20010;
	/**
	 * Echec le champ pseudo accèpte uniquement les caractères alphanumériques
	 */
	public static final int REGLE_PSEUDO_ALPHANUMERIQUE_ERREUR = 20011;
	/**
	 * Echec le pseudo choisi existe déjà
	 */
	public static final int REGLE_PSEUDO_DUPLICATION_ERREUR = 20012;
	/**
	 * Echec l'email choisi existe déjà
	 */
	public static final int REGLE_EMAIL_DUPLICATION_ERREUR = 20013;
	

	
}
