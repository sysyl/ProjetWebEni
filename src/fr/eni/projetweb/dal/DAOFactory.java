package fr.eni.projetweb.dal;

import fr.eni.projetweb.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.projetweb.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.projetweb.dal.jdbc.UtilisateurDAOJdbcImpl;

public class DAOFactory {

	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}
}
