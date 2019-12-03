package fr.eni.projetweb.bll;

import java.util.List;

import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.dal.UtilisateurDAO;
import fr.eni.projetweb.dal.UtilisateurDAOJdbcImpl;
import fr.eni.projetweb.exceptions.BusinessException;

public class UtilisateursManager {
	private UtilisateurDAO daoUtilisateur;

	public UtilisateursManager() {
		daoUtilisateur = new UtilisateurDAOJdbcImpl();
	}

	public boolean verifierUtilisateur(String pseudo, String mot_de_passe) {
		return daoUtilisateur.verifierUtilisateur(pseudo, mot_de_passe);

	}
	
	public void ajouterUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostalS, String ville, String motDePasse, String confirmation) throws BusinessException {
	
		System.out.println("je suis dans la bll"); //
		BusinessException businessException = new BusinessException();
		this.verifierChamps(pseudo, nom, prenom, email, rue, ville, businessException);
		this.validerMotDePasse(motDePasse, confirmation, businessException);
		int codePostal = this.verifierCodePostal(codePostalS, businessException);
		verifierPseudoUnique(pseudo, businessException);
		verifierEmailUnique(email, businessException);
		
		if (!(businessException.hasErreurs())) {
	
			Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, 1, ville, motDePasse, 100, false); 
			daoUtilisateur.insertNewUser(utilisateur);
				
		} else {
			throw businessException;
		}

	}
	
	/**
	 * Méthode en charge de
	 * @param email
	 * @param businessException 
	 */
	private void verifierEmailUnique(String email, BusinessException businessException) {
		try {
			List<String> emails = daoUtilisateur.selectEmails(email);
			
			if(emails.contains(email)) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_EMAIL_DUPLICATION_ERREUR);
			}
			
		} catch (BusinessException e) {
			e.getCause();
			//TODO
		}
		
	}

	/**
	 * Méthode en charge de
	 * @param pseudo
	 * @param businessException 
	 */
	private void verifierPseudoUnique(String pseudo, BusinessException businessException) {
		try {
			List<String> pseudos = daoUtilisateur.selectPseudos(pseudo);
			if(pseudos.contains(pseudo)) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_DUPLICATION_ERREUR);
			}
		} catch (BusinessException e) {
			//TODO
			e.getCause();
		}
//		if(pseudo.equals(anObject))
		
	}

	/**
	 * Méthode en charge de
	 * @param codePostalS
	 * @return
	 */
	private int verifierCodePostal(String codePostalS, BusinessException businessException) {
		
		int codePostal=0;
		if (codePostalS == null || codePostalS.trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CODE_POSTAL_NOM_ERREUR);		
		}
		else {
			try {
				codePostal = Integer.parseInt(codePostalS);
				
				if(codePostal == 0) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_CODE_POSTAL_NULL);		
				}
				
			}catch(NumberFormatException e){
				businessException.ajouterErreur(CodesResultatBLL.REGLE_CODE_POSTAL_FORMAT_ERREUR);		
			}
		}
		
		return codePostal;
	}

	/**
	 * Méthode en charge de verifier si les champs renseignes sont vides
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param businessException
	 */
	private void verifierChamps(String pseudo, String nom, String prenom, String email, String rue,
		String ville, BusinessException businessException) {
	
		String  pattern = "^[a-zA-Z0-9]*$";  	
		
		if (pseudo == null || pseudo.trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_NOM_ERREUR);		
		}
		else if(!pseudo.matches(pattern)){
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_ALPHANUMERIQUE_ERREUR);		
		}
		if (nom == null || nom.trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_NOM_UTILISATEUR_NOM_ERREUR);		
		}
		if (prenom == null || prenom.trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PRENOM_UTILISATEUR_NOM_ERREUR);		
		}
		if (email == null || email.trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_EMAIL_NOM_ERREUR);		
		}
		if (rue == null || rue.trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_RUE_NOM_ERREUR);		
		}
	
		if (ville == null || ville.trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_NOM_VILLE_NOM_ERREUR);		
		}
		
	}

	/**
	 * Méthode en charge de
	 * 
	 * @param motDePasse
	 * @param confirmation
	 * @param businessException
	 */
	private void validerMotDePasse(String motDePasse, String confirmation, BusinessException businessException) {

		if (motDePasse == null || motDePasse.trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_MOT_DE_PASSE_NOM_ERREUR);		
		}
		else {
			
			if (!motDePasse.equals(confirmation)) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_MOTS_DE_PASSE_IDENTIQUES_NOM_ERREUR);
			}
			
		}
		
		
		

	}

}
