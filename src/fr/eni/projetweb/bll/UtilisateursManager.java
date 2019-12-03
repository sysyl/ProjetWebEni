package fr.eni.projetweb.bll;

import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.dal.UtilisateurDAO;
import fr.eni.projetweb.dal.UtilisateurDAOJdbcImpl;

public class UtilisateursManager {
	private UtilisateurDAO daoUtilisateur;

	public UtilisateursManager() {
		daoUtilisateur = new UtilisateurDAOJdbcImpl();
	}

	public boolean verifierUtilisateur(String pseudo, String mot_de_passe) {
		return daoUtilisateur.verifierUtilisateur(pseudo, mot_de_passe);

	}
	
	public Utilisateur login(Utilisateur user) {
		return daoUtilisateur.login(user);	
	}

}
