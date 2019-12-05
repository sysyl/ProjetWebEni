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
 * @date 5 déc. 2019 - 14:39:10
 *
 */
public interface ArticleDAO {

	public List<Article> selectArticle(Article article) throws BusinessException;
		
}
