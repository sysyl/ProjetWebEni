package fr.eni.projetweb.dal;

import java.util.List;

import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.exceptions.BusinessException;

public interface UtilisateurDAO {
	
	boolean verifierUtilisateur(String pseudo, String mot_de_passe);
	Utilisateur login(Utilisateur user) throws BusinessException;
	
	void insertNewUser(Utilisateur utilisateur) throws BusinessException;
	
	public List<String> selectPseudos(String pseudo) throws BusinessException;
	public List<String> selectEmails(String email) throws BusinessException;
	
	public Utilisateur selectUserByPseudo(String pseudo) throws BusinessException;
	
	public void updateUser(Utilisateur utilisateur) throws BusinessException;
	
	public Utilisateur selectUserById(int no_utilisateur) throws BusinessException;
	
	public void deleteUser(int numeroUtilisateur) throws BusinessException;

	
	public List<String> selectPseudosExceptUserId(int numeroUtilisateur) throws BusinessException;

	public List<String> selectEmailsExceptUserId(int numeroUtilisateur) throws BusinessException;
	

}
