package fr.eni.projetweb.dal;

import java.util.List;

import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.exceptions.BusinessException;

public interface UtilisateurDAO {
	boolean verifierUtilisateur(String pseudo, String mot_de_passe);
	Utilisateur login(Utilisateur user);
	/**
	 * Méthode en charge de
	 * @param utilisateur
	 * @throws BusinessException 
	 */
	void insertNewUser(Utilisateur utilisateur) throws BusinessException;
	
	public List<String> selectPseudos(String pseudo) throws BusinessException;
	public List<String> selectEmails(String email) throws BusinessException;
	
	public Utilisateur selectUser(String pseudo) throws BusinessException;

}
