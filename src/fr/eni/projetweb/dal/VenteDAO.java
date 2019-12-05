package fr.eni.projetweb.dal;

import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.bo.Retrait;
import fr.eni.projetweb.exceptions.BusinessException;

public interface VenteDAO {

	public void insertArticle(Article article) throws BusinessException;
}
