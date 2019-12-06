package fr.eni.projetweb.bll;

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
	 * Methode en charge d'inserer un nouveau compte utilisateur dans la bdd apres
	 * la verification de contraintes
	 * 
	 * @param pseudo,
	 *                         nom, prenom, email, telephone, rue,
	 * @param codePostalS,
	 *                         ville, motDePasse confirmation
	 * @throws BusinessException
	 */
	public void ajouterUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostalS, String ville, String motDePasse, String confirmation) throws BusinessException {

		BusinessException businessException = new BusinessException();

		verificationsInfosUtilisateur(nom, prenom, pseudo, email, businessException);

		int codePostal = MethodesUtiles.verifierAdresse(rue, ville, codePostalS, businessException);
		String motDePasseHashe = MethodesUtiles.validerMotDePasse(motDePasse, confirmation, businessException);

		if (!(businessException.hasErreurs())) {
			Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
					motDePasseHashe, 100, false);
			daoUtilisateur.insertNewUser(utilisateur);

		} else {
			throw businessException;
		}
	}

	/**
	 * Méthode en charge de verifier les parametres recus pour permettre ou
	 * empecher l'intanciation d'utilisateur
	 * 
	 * @param nom,
	 *                 prenom, pseudo, email, businessException
	 */
	private void verificationsInfosUtilisateur(String nom, String prenom, String pseudo, String email,
			BusinessException businessException) {

		MethodesUtiles.verifierChampVide(nom, businessException);
		MethodesUtiles.verifierChampVide(prenom, businessException);
		MethodesUtiles.verifierPseudo(pseudo, businessException);
		MethodesUtiles.verifierEmail(email, businessException);
	}

	/**
	 * Méthode en charge de retourne une instance d'utilisateur de la bdd selon son
	 * id
	 * 
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

	/**
	 * Méthode en charge de modifier les données utilisateur
	 * 
	 * @param pseudo,
	 *                    nom, prenom, email, telephone
	 * @param rue,
	 *                    codePostal, ville, motDePasse, confirmation
	 * @throws BusinessException
	 */
	public void modifierUtilisateur(int idUtilisateur, String pseudo, String nom, String prenom, String email,
			String telephone, String rue, String codePostalS, String ville, String motDePasseActuel, String motDePasse,
			String confirmation) throws BusinessException {

		BusinessException businessException = new BusinessException();
		verificationsInfosUtilisateur(nom, prenom, pseudo, email, businessException);
		int codePostal = MethodesUtiles.verifierAdresse(rue, ville, codePostalS, businessException);
		String motDePasseHashe = MethodesUtiles.validerMotDePasse(motDePasse, confirmation, businessException);

		verifierMotDePasseActuel(idUtilisateur, motDePasseActuel, businessException);

		if (!(businessException.hasErreurs())) {
			Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
					motDePasseHashe, 100, false);
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
	 * Méthode en charge de
	 * 
	 * @param motDePasseActuel
	 * @param businessException
	 */
	private void verifierMotDePasseActuel(int idUtilisateur, String motDePasseActuelS,
			BusinessException businessException) {
		// hasher le mdp actuel
		// motDePasseActuelS = hasherMotDePasse(motDePasseActuelS);
		// String motDePasseActuel = daoUtilisateur.selectUser(pseudo)
		// if motDePasseActuel = utilisateur.motDePasse

	}

	public void supprimerUtilisateur(int idUtilisateur) throws BusinessException {

		daoUtilisateur.deleteUser(idUtilisateur);
	}

}
