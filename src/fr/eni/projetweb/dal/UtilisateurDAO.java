package fr.eni.projetweb.dal;

import fr.eni.projetweb.bo.Utilisateur;

public interface UtilisateurDAO {
	boolean verifierUtilisateur(String pseudo, String mot_de_passe);
	Utilisateur login(Utilisateur user);
}
