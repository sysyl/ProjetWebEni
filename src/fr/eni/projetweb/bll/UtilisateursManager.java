package fr.eni.projetweb.bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
		verifierChamps(pseudo, nom, prenom, email, rue, ville, businessException);
		verifierPseudoUnique(pseudo, businessException);
		verifierEmailUnique(email, businessException);
		String motDePasseHashe = validerMotDePasse(motDePasse, confirmation, businessException);
		int codePostal = this.verifierCodePostal(codePostalS, businessException);
		
		if (!(businessException.hasErreurs())) {
			Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasseHashe, 100, false); 
			daoUtilisateur.insertNewUser(utilisateur);
				
		} else {
			throw businessException;
		}

	}
	
	/**
	 * Methode en charge de verifier si 
	 * l'email renseigne n'existe pas deja dans la bdd
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
			businessException.ajouterErreur(CodesResultatBLL.REGLE_RECUPERATION_BDD_ERREUR);
		}
		
	}

	/**
	 * Methode en charge de verifier si le pseudo renseigne
	 * n'existe pas deja dans la bdd
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
			businessException.ajouterErreur(CodesResultatBLL.REGLE_RECUPERATION_BDD_ERREUR);
		}
		
	}

	/**
	 * Methode en charge de verifier si le code postal est compose de 5 chiffres
	 * et de le transformer de String en int
	 * @param String codePostalS
	 * @return int codePostal 
	 */
	private int verifierCodePostal(String codePostalS, BusinessException businessException) {
		//accepte uniquement code postal compose de 5 chiffres
		String  pattern = "^[0-9]{5}$";  	
		int codePostal=0;
		
		if(!codePostalS.trim().matches(pattern)){
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CODE_POSTAL_NOM_ERREUR);		
		}else {
			try {
				codePostal = Integer.parseInt(codePostalS);
		
			}catch(NumberFormatException e){
				businessException.ajouterErreur(CodesResultatBLL.REGLE_CODE_POSTAL_NOM_ERREUR);		
			}
		}
		
		return codePostal;
	}

	/**
	 * Methode en charge de verifier si les champs renseignes sont vides
	 * et de confirmer que le pseudo est une chaine alphanumerique
	 * @param pseudo, nom, prenom, email, rue, codePostal, ville
	 * @param businessException
	 */
	private void verifierChamps(String pseudo, String nom, String prenom, String email, String rue,
		String ville, BusinessException businessException) {
		
		// pour verifier si pseudo est une chaine alphanumerique
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
	 * Methode en charge de 
	 * - verifier si le mot de passe a ete renseigne
	 * - si le mot de passe et sa confirmation sont equivalents
	 * - si tout va bien -> appel methode qui hashe le mdp
	 * 	 pour l'inserer dans la bdd
	 * 
	 * @param motDePasse
	 * @param confirmation
	 * @param businessException
	 */
	private String validerMotDePasse(String motDePasse, String confirmation, BusinessException businessException) {
		
		String generatedPassword = null;
		
		if (motDePasse == null || motDePasse.trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_MOT_DE_PASSE_NOM_ERREUR);		
		}
		
		else if (!motDePasse.equals(confirmation)) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_MOTS_DE_PASSE_IDENTIQUES_NOM_ERREUR);
		}
		else {
			generatedPassword = hasherMotDePasse(motDePasse);
		}
		return generatedPassword;
	}
	
	
	/**
	 * methode qui hash le mot de passe passe en parametre
	 * 
	 * @param passwordToHash
	 * @return String mot de passe hashe
	 */
	public String hasherMotDePasse(String passwordToHash) {
	
		String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return generatedPassword;
	}

}
