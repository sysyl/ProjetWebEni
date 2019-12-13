package fr.eni.projetweb.dal;

import java.util.List;

import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.bo.Enchere;
import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.exceptions.BusinessException;

public interface EnchereDAO {
	
	
	void insertEnchere(Enchere enchere) throws BusinessException;
//	void updateEnchere(Enchere enchere) throws BusinessException;
	public List<Enchere> selectAllEncheres() throws BusinessException;
	
	void insertEnchereetUpdate(Enchere enchere, Enchere plusGrandEnchere) throws BusinessException;
	
	List<Enchere> selectEncheresByIdArticle(int idArticle) throws BusinessException;
	
	

}
