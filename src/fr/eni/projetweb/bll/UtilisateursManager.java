package fr.eni.projetweb.bll;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.dal.DAOFactory;
import fr.eni.projetweb.dal.UtilisateurDAO;

import fr.eni.projetweb.exceptions.BusinessException;

public class UtilisateursManager {

	private static UtilisateursManager instance;
	private UtilisateurDAO daoUtilisateur;

	private UtilisateursManager() {
		daoUtilisateur = DAOFactory.getUtilisateurDAO();
	}

	public static UtilisateursManager getInstance() {
		if (instance == null) {
			instance = new UtilisateursManager();
		}
		return instance;
	}

	public boolean verifierUtilisateur(String pseudo, String mot_de_passe) {
		return daoUtilisateur.verifierUtilisateur(pseudo, mot_de_passe);

	}

	/**
	 * Methode en charge d'inserer un nouveau compte utilisateur dans
	 * la bdd apres la verification de contraintes
	 * @param pseudo, nom, prenom, email, telephone, rue, 
	 * @param codePostalS, ville, motDePasse confirmation
	 * @throws BusinessException
	 */
	public void ajouterUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostalS, String ville, String motDePasse, String confirmation) throws BusinessException {

		BusinessException businessException = new BusinessException();
		Utilisateur utilisateur = null;
		MethodesUtiles.verifierChampVide(nom, businessException);
		MethodesUtiles.verifierChampVide( prenom, businessException);
		verifierPseudo(pseudo, businessException);
		verifierEmail(email, businessException);	
		verifierNumTelephone(telephone, businessException);
		MethodesUtiles.verifierLimiteChaine30(pseudo, businessException);
		MethodesUtiles.verifierLimiteChaine30(email, businessException);
		MethodesUtiles.verifierLimiteChaine30(nom, businessException);
		MethodesUtiles.verifierLimiteChaine30(prenom, businessException);
		MethodesUtiles.verifierLimiteChaine30(rue, businessException);
		MethodesUtiles.verifierLimiteChaine30(ville, businessException);
		
		int codePostal = MethodesUtiles.verifierAdresse(rue, ville, codePostalS, businessException);
		String motDePasseHashe = MethodesUtiles.validerMotDePasse(motDePasse, confirmation, businessException);

		if (!(businessException.hasErreurs())) {
			utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasseHashe, 100, false); 
			daoUtilisateur.insertNewUser(utilisateur);
				
		} else {
			throw businessException;
		}
	}
	
	/**
	 * M�thode en charge de retourne une instance
	 * d'utilisateur de la bdd selon son id
	 * @param pseudo
	 * @return
	 */
	// TODO
	public Utilisateur afficherUtilisateur(int idUtilisateur) {
		Utilisateur utilisateur = null;
		try {
			utilisateur = daoUtilisateur.selectUserById(idUtilisateur);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}

	/*
	 * Methode en charge de verifier si le numero de telephone 
	 * renseigne par utilisateur (s'il l'a renseigne -> champs vides autorises)
	 * est compose de chiffres uniquement et a une longeur min et max definis
	 */
	private void verifierNumTelephone(String telephone, BusinessException businessException) {
		String  pattern = "^[0-9]{10,13}$"; 
		
		if( telephone.trim().length() != 0 && telephone != null  && (!telephone.matches(pattern))){
			
			if(!telephone.matches(pattern)) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_TELEPHONE_CHIFFRES_ERREUR);
			}
			else if(telephone.length() < 10) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_TELEPHONE_TROPCOURT_ERREUR);	
			}
			else if(telephone.length() > 13) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_TELEPHONE_TROPLONG_ERREUR);	
			}
				
		}
	}

	/**
	 * M�thode en charge de modifier les donn�es utilisateur
	 * @param pseudo, nom, prenom, email, telephone
	 * @param rue, codePostal, ville, motDePasse, confirmation
	 * @throws BusinessException 
	 */
	public void modifierUtilisateur(int idUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostalS, String ville, String motDePasseActuel, String motDePasse, String confirmation) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		MethodesUtiles.verifierChampVide(nom, businessException);
		MethodesUtiles.verifierChampVide( prenom, businessException);
		verifierPseudo(idUtilisateur, pseudo, businessException);
		verifierEmail(idUtilisateur, email, businessException);	
		verifierNumTelephone(telephone, businessException);
		MethodesUtiles.verifierLimiteChaine30(pseudo, businessException);
		MethodesUtiles.verifierLimiteChaine30(email, businessException);
		MethodesUtiles.verifierLimiteChaine30(nom, businessException);
		MethodesUtiles.verifierLimiteChaine30(prenom, businessException);
		MethodesUtiles.verifierLimiteChaine30(rue, businessException);
		MethodesUtiles.verifierLimiteChaine30(ville, businessException);
		
		int codePostal = MethodesUtiles.verifierAdresse(rue, ville, codePostalS, businessException);
		String motDePasseHashe = MethodesUtiles.validerMotDePasse(motDePasse, confirmation, businessException);

		verifierMotDePasseActuel(idUtilisateur, motDePasseActuel, businessException);
		
		if (!(businessException.hasErreurs())) {
			Utilisateur utilisateur = new Utilisateur(idUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasseHashe, 100, false); 
			try {
				daoUtilisateur.updateUser(utilisateur);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
				
		} else {
			throw businessException;
		}
		
	}


	/**
	 * M�thode en charge de
	 * @param motDePasseActuel
	 * @param businessException
	 */
	private void verifierMotDePasseActuel(int idUtilisateur, String motDePasseActuelS, BusinessException businessException) {
		// hasher le mdp actuel
		motDePasseActuelS = MethodesUtiles.hasherMotDePasse(motDePasseActuelS);
		try {
			String motDePasseAComparer = daoUtilisateur.selectUserById(idUtilisateur).getMot_de_passe();
		
			MethodesUtiles.validerMotDePasse(motDePasseActuelS, motDePasseAComparer, businessException);
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void supprimerUtilisateur(int idUtilisateur) throws BusinessException{
		// select de tous les articles d'utilisateur pour voir si on peut supprimer
		// -> si vide -> OK
		// - > si encheres pas commencees -> OK
		// - > si en cours => PAS bon
		// ->  si terminees mais retrait false -> pas bon
		BusinessException businessException = new BusinessException();
		ArticleManager articleManager = ArticleManager.getInstance();
		List<Article> listeArticles = articleManager.getListArticlesByUserId(idUtilisateur);
		boolean testPossibiliteSuppression = true;
		Timestamp dateAujourdhui = new Timestamp(new Date().getTime());
		for(Article a : listeArticles) {
			if(		// test s'il y a des cas ou je peux pas supprimer le compte :
					(a.getDebutEncheres().before(dateAujourdhui) || a.getDebutEncheres().equals(dateAujourdhui)) && //si enchere en cours
					(a.getFinEncheres().after(dateAujourdhui) || a.getFinEncheres().equals(dateAujourdhui)) ||
					(a.getFinEncheres().before(dateAujourdhui) && a.getRetrait().isStatut() == false) //enchere terminee mais retrait statut false
			  ) {
						testPossibiliteSuppression = false;
			}
		}
		
		if(listeArticles.size() == 0 || testPossibiliteSuppression == true) {	
				daoUtilisateur.deleteUser(idUtilisateur);
		}
		else {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_SUPPRESSION_UTILISATEUR_ERREUR);
			throw businessException;
		}




}

	
	/**
	 * Methode en charge de verifier si le pseudo renseigne
	 * n'existe pas deja dans la bdd.
	 * Methode utilisee au moment de la creation du compte utilisateur
	 * @param pseudo
	 * @param businessException 
	 */
	public static void verifierPseudo(String pseudo, BusinessException businessException) {
		
		UtilisateurDAO daoUtilisateur = DAOFactory.getUtilisateurDAO();
		// pour verifier si pseudo est une chaine alphanumerique
		String  pattern = "^[a-zA-Z0-9áàâäçéèêëîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ]*$";  	

		if (pseudo == null || pseudo.trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_NOM_ERREUR);		
		}
		else if(!pseudo.matches(pattern)){
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_ALPHANUMERIQUE_ERREUR);		
		}
		
		try {
			List<String> pseudos = daoUtilisateur.selectPseudos(pseudo);
			
			if(pseudos.contains(pseudo)) {
				System.out.println("tous les pseudos sauf celui d'utilisateur");
				businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_DUPLICATION_ERREUR);
			}
		} catch (BusinessException e) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_RECUPERATION_BDD_ERREUR);
		}
	
	}
	/*
	 * Surcharge de la methode precedente : elle verifier egalement si le pseudo
	 * d'utilisateur est unique mais elle est utilisee uniquement par la
	 * methode qui modifie les donnees d'utilisateur
	 * @param idUser, pseudo
	 * @param businessException 
	 */
	public static void verifierPseudo(int idUser, String pseudo, BusinessException businessException) {
			
			UtilisateurDAO daoUtilisateur = DAOFactory.getUtilisateurDAO();
			// pour verifier si pseudo est une chaine alphanumerique
			String  pattern = "^[a-zA-Z0-9]*$";  	
	
			if (pseudo == null || pseudo.trim().length() == 0) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_NOM_ERREUR);		
			}
			else if(!pseudo.matches(pattern)){
				businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_ALPHANUMERIQUE_ERREUR);		
			}
			
			try {
				List<String> pseudos = daoUtilisateur.selectPseudosExceptUserId(idUser);
				
				if(pseudos.contains(pseudo)) {
					System.out.println("tous les pseudos sauf celui d'utilisateur");
					businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_DUPLICATION_ERREUR);
				}
			} catch (BusinessException e) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_RECUPERATION_BDD_ERREUR);
			}
		
		}
	
	
	/**
	 * Methode en charge de verifier si l'email renseigne n'existe pas deja dans la bdd.
	 * Utilisee au moment de la modification du compte
	 * @param idUser, email
	 * @param businessException 
	 */
	public static void verifierEmail(int idUser, String email, BusinessException businessException) {
		UtilisateurDAO daoUtilisateur = DAOFactory.getUtilisateurDAO();
		
		if (email == null || email.trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_EMAIL_NOM_ERREUR);		
		}
		try {
			List<String> emails = daoUtilisateur.selectEmailsExceptUserId(idUser);
			
			if(emails.contains(email)) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_EMAIL_DUPLICATION_ERREUR);
			}
			
		} catch (BusinessException e) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_RECUPERATION_BDD_ERREUR);
		}
	}
	/**
	 * Methode en charge de verifier si l'email renseigne n'existe pas deja dans la bdd
	 * Surcharge de la methode precedente, utilisee au moment de la creation du compte utilisateur
	 * @param idUser, email
	 * @param businessException 
	 */
	public static void verifierEmail(String email, BusinessException businessException) {
		UtilisateurDAO daoUtilisateur = DAOFactory.getUtilisateurDAO();
		
		if (email == null || email.trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_EMAIL_NOM_ERREUR);		
		}
		try {
			List<String> emails = daoUtilisateur.selectEmails(email);
			
			if(emails.contains(email)) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_EMAIL_DUPLICATION_ERREUR);
			}
			
		} catch (BusinessException e) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_RECUPERATION_BDD_ERREUR);
		}
	}

}
