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
	/*
	 * Les donn�es saisies ne peuvent pas �tre enregistr�es dans la base de donn�es.
	 */
	public static final int ERREUR_INSERTION = 20014;
	/*
	 * Echec le champ est vide -> contraite NOT NULL dans la bdd
	 */
	public static final int REGLE_CHAMP_VIDE_ERREUR = 20015;

	/*
	 * La date de fin d'enchere renseignee est avant son debut...
	 */
	public static final int REGLE_PERIODE_ENCHERE_ERREUR = 20016;
	/*
	 * Le debut et/ou la fin de l'enchere ne peuvent pas avoir lieu avant le moment present
	 */
	public static final int REGLE_DATE_ENCHERE_INVALIDE = 20017;
	/*
	 * Numero de telephone inferieur a 10 chiffres
	 */
	public static final int REGLE_TELEPHONE_TROPCOURT_ERREUR = 20018;
	/*
	 * Numero de telephone superieur a 13 chiffres
	 */
	public static final int REGLE_TELEPHONE_TROPLONG_ERREUR = 20019;
	/*
	 * Numero de telephone contient autres characteres que les chiffres
	 */
	public static final int REGLE_TELEPHONE_CHIFFRES_ERREUR = 20020;
	/*
	 * Echec lors de la finalisation d'enchere : credit utilisateur 
	 * ne peut pas etre negatif
	 */
	public static final int UTILISATEUR_CREDIT_NEGATIF_ERREUR = 20021;
	/*
	 * Pas possbile d'encherir sur un objet pour le meme montant
	 */
	public static final int MEME_MONTANT_ENCHERE_ERREUR = 20022;
	/*
	 * Pas possible d'encherir pour un montant inferieur a la personne precedente
	 */
	public static final int ENCHERE_INFERIEURE_ERREUR = 20023;

	/*
	 * Utilisateur ne peut pas etre supprime car il a des encheres en cours
	 * ou les retraits pas effectues
	 */
	public static final int REGLE_SUPPRESSION_UTILISATEUR_ERREUR = 20024;
	
	/*
	 * l'URL de l'image que le vendeur veut inserer est trop long et depasse
	 * les limites du varchar fixees dans la bdd
	 */
	public static final int LIEN_IMAGE_TROP_LONG = 20025;

	/*
	 * La proposition d'enchere faite par l'acheteur est inferieure 
	 * au prix initial fixe pour l'article
	 */
	public static final int PROPOSITION_MONTANT_ENCHERE_ERREUR = 20026;

	/*
	 * Le text rentre dans le formulaire est plus long que 
	 * limite 30 caracs de la bdd
	 */
	public static final int CHAMP_TEXT_30_TROP_LONG = 20027;
	/**
	 * Description d'article trop longue -> limit 300
	 */
	public static final int DESCRIPTION_TROP_LONGUE = 20028;

	/*
	 * Nom d'article depasse le max de caracteres dans la bdd
	 */
	public static final int NOM_ARTICLE_TROP_LONG = 20029;
	
}
