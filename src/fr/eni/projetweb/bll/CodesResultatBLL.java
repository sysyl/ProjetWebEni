package fr.eni.projetweb.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	/**
	 * Echec le pseudo de l'utilisateur est obligatoire et ne doit pas dï¿½passer 30 caractï¿½res ! 
	 */
	public static final int REGLE_PSEUDO_NOM_ERREUR=20000;
	
	/**
	 * Echec le nom de l'utilisateur est obligatoire et ne doit pas dï¿½passer 30 caractï¿½res ! 
	 */
	public static final int REGLE_NOM_UTILISATEUR_NOM_ERREUR = 20001;
	/**
	 * Echec le prï¿½nom de l'utilisateur est obligatoire et ne doit pas dï¿½passer 30 caractï¿½res ! 
	 */
	public static final int REGLE_PRENOM_UTILISATEUR_NOM_ERREUR = 20002;
	/**
	 * Echec l'email est obligatoire et ne doit pas dï¿½passer 30 caractï¿½res ! 
	 */
	public static final int REGLE_EMAIL_NOM_ERREUR = 20003;
	/**
	 * Echec le nom de la rue est obligatoire et ne doit pas dï¿½passer 30 caractï¿½res ! 
	 */
	public static final int REGLE_RUE_NOM_ERREUR = 20004;
	
	/**
	 * Echec le code postal doit correspondre ï¿½ une suite de 5 chiffres (ex.: 44000) ! 
	 */
	public static final int REGLE_CODE_POSTAL_NOM_ERREUR = 20005;
	
	/**
	 * Echec le nom de la ville est obligatoire et ne doit pas dï¿½passer 30 caractï¿½res ! 
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
	 * Echec le champ pseudo accï¿½pte uniquement les caractï¿½res alphanumï¿½riques ! Les caractï¿½res spï¿½ciaux ne sont pas autorisï¿½s!
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
	 * Erreur survenu lors de la rÃ©cupÃ©ration des donnÃ©es dans la base de donnÃ©es!
	 * (erreur select)
	 */
	public static final int REGLE_RECUPERATION_BDD_ERREUR = 20010;
	/*
	 * Les données saisies ne peuvent pas être enregistrées dans la base de données.
	 */
	public static final int ERREUR_INSERTION = 20014;
	/*
	 * Echec le champ est vide -> contraite NOT NULL dans la bdd
	 */
	public static final int REGLE_CHAMP_VIDE_ERREUR = 20015;
	
}
