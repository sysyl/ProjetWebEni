/**
 * 
 */
package fr.eni.projetweb.dal;

import java.util.List;

import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Classe en charge de 
 * @version ProjetWebENI - V1.0
 * @author slanger2019
 * @date 5 d�c. 2019 - 14:39:10
 *
 */
public interface ArticleDAO {

	List<Article> selectArticle(Article article) throws BusinessException;
	
	void insertArticle(Article article) throws BusinessException;
	
	Article selectArticleById(int numeroArticle) throws BusinessException;
	
	void updateRetraitStatut(int idArticle) throws BusinessException;

	/**
	 * M�thode en charge de
	 * @return
	 * @throws BusinessException
	 */
	List<Article> selectAllArticle() throws BusinessException;
		
}
