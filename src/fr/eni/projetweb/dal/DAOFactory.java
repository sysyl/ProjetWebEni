package fr.eni.projetweb.dal;

import fr.eni.projetweb.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.projetweb.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.projetweb.dal.jdbc.UtilisateurDAOJdbcImpl;
import fr.eni.projetweb.dal.jdbc.VenteDAOJdbcImpl;

public class DAOFactory {

	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}
	
	public static VenteDAO getVenteDAO() {
		return new VenteDAOJdbcImpl();
	}
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}
}
