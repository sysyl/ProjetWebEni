/**
 * 
 */
package fr.eni.projetweb.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.dal.CodesResultatDAL;
import fr.eni.projetweb.dal.jdbc.ConnectionProvider;
import fr.eni.projetweb.dal.ArticleDAO;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Classe en charge de 
 * @version ProjetWebENI - V1.0
 * @author slanger2019
 * @date 5 déc. 2019 - 14:37:15
 *
 */
public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	private static final String SELECT_ALL_ACHATS = "SELECT nom_article, prix_vente, fin_encheres, no_utilisateur FROM ARTICLES_VENDUS";
	
	/**
	 * {@inheritDoc}
	 * @see fr.eni.projetweb.dal.ArticleDAO#selectEncheres(fr.eni.projetweb.bo.Article)
	 */
	@Override
	public List<Article> selectArticle(Article article) throws BusinessException {
		List<Article> listeArticle = new ArrayList<Article>();
		Utilisateur user = new Utilisateur();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ACHATS);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
			{
				listeArticle.add(new Article(rs.getString("nom_article"), rs.getInt("prix_vente"),rs.getTimestamp("fin_encheres"), rs.getInt("no_utilisateur")));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
			throw businessException;
		}
		return listeArticle;
	}

}
