package fr.eni.projetweb.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetweb.bll.ArticleManager;
import fr.eni.projetweb.bll.UtilisateursManager;
import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.bo.Enchere;
import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.dal.ArticleDAO;
import fr.eni.projetweb.dal.DAOFactory;
import fr.eni.projetweb.dal.EnchereDAO;
import fr.eni.projetweb.dal.UtilisateurDAO;
import fr.eni.projetweb.exceptions.BusinessException;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	
	public static final String INSERT_ENCHERE = "INSERT INTO ENCHERES(no_article, date_enchere, montant_enchere, no_utilisateur) VALUES(?, ?, ?, ?)";
	public static final String SELECT_ALL_ENCHERES = "SELECT * FROM ENCHERES";
	public static final String SELECT_ENCHERES_BY_ID_ARTICLE = "SELECT * FROM ENCHERES WHERE no_article=? ORDER BY date_enchere DESC";
	
	@Override
	public void insertEnchere(Enchere enchere) throws BusinessException {

		try(Connection cnx = ConnectionProvider.getConnection()){
			try	{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				System.out.println("enchere daO jdbc 1");
				List<Enchere> listEncheres = selectAllEncheres();
				pstmt = cnx.prepareStatement(INSERT_ENCHERE);
				pstmt.setInt(1, enchere.getArticle().getNoArticle());
				pstmt.setTimestamp(2, enchere.getDateEnchere());
				pstmt.setInt(3, enchere.getMontantEnchere());
				pstmt.setInt(4, enchere.getUtilisateur().getNoUtilisateur());
				pstmt.executeUpdate();
				pstmt.close();	
				
				UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
				utilisateurDAO.updateCreditUtilisateur(enchere.getUtilisateur().getCredit(), enchere.getUtilisateur().getNoUtilisateur());
				
				ArticleDAO articleDAO = DAOFactory.getArticleDAO();
				articleDAO.updatePrixVenteArticleByIdArticle(enchere.getMontantEnchere(), enchere.getArticle().getNoArticle());
				
				
				cnx.commit();
				System.out.println("enchere daO jdbc 2 bien commitee");
		} catch (Exception e) {
			e.printStackTrace();
			cnx.rollback();
			throw e;
		}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void insertEnchereetUpdate(Enchere enchere, Enchere plusGrandEnchere) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()){
			try	{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
		
				pstmt = cnx.prepareStatement(INSERT_ENCHERE);
				pstmt.setInt(1, enchere.getArticle().getNoArticle());
				pstmt.setTimestamp(2, enchere.getDateEnchere());
				pstmt.setInt(3, enchere.getMontantEnchere());
				pstmt.setInt(4, enchere.getUtilisateur().getNoUtilisateur());
				pstmt.executeUpdate();
						
				UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
				utilisateurDAO.updateCreditUtilisateur(enchere.getUtilisateur().getCredit(), enchere.getUtilisateur().getNoUtilisateur());
				
				ArticleDAO articleDAO = DAOFactory.getArticleDAO();
				articleDAO.updatePrixVenteArticleByIdArticle(enchere.getMontantEnchere(), enchere.getArticle().getNoArticle());
				
				//restitution d'argent
				int ancienneEnchereMontant = plusGrandEnchere.getMontantEnchere();
				System.out.println("int montant anciennne enchere" + ancienneEnchereMontant);
				int numeroUtilisateurAncienneEnchere = plusGrandEnchere.getUtilisateur().getNoUtilisateur();
				Utilisateur utilisateur = utilisateurDAO.selectUserById(plusGrandEnchere.getUtilisateur().getNoUtilisateur());
				int creditUser = utilisateur.getCredit();
				creditUser += ancienneEnchereMontant;
				utilisateurDAO.updateCreditUtilisateur(creditUser, numeroUtilisateurAncienneEnchere);
				
				pstmt.close();
				cnx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			cnx.rollback();
			throw e;
		}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	public List<Enchere> selectAllEncheres() throws BusinessException {
		
		List<Enchere> listeEncheres = new ArrayList<>();
		try (
				Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_ALL_ENCHERES);
			) {
			ResultSet rs = stmt.executeQuery();	
			while(rs.next()) {
				int noArticle = rs.getInt("no_article");
				Timestamp dateEnchere = rs.getTimestamp("date_enchere");
				int montantEnchere = rs.getInt("montant_enchere");
				int noUtilisateur = rs.getInt("no_utilisateur");
				
				ArticleManager articleManager = ArticleManager.getInstance();
				UtilisateursManager utilisateurManager = UtilisateursManager.getInstance();
				Article article = articleManager.getArticleById(noArticle);
				Utilisateur utilisateur= utilisateurManager.afficherUtilisateur(noUtilisateur);
				listeEncheres.add(new Enchere(utilisateur, dateEnchere, article, montantEnchere));
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		return listeEncheres;
		
	}

	
	@Override
	public List<Enchere> selectEncheresByIdArticle(int idArticle) throws BusinessException {
		List<Enchere> listeEncheres = new ArrayList<>();
		try (
				Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_ENCHERES_BY_ID_ARTICLE);
			) {
			stmt.setInt(1, idArticle);
			ResultSet rs = stmt.executeQuery();	
			while(rs.next()) {
				int noArticle = rs.getInt("no_article");
				Timestamp dateEnchere = rs.getTimestamp("date_enchere");
				int montantEnchere = rs.getInt("montant_enchere");
				int noUtilisateur = rs.getInt("no_utilisateur");
				
				ArticleManager articleManager = ArticleManager.getInstance();
				UtilisateursManager utilisateurManager = UtilisateursManager.getInstance();
				Article article = articleManager.getArticleById(noArticle);
				Utilisateur utilisateur= utilisateurManager.afficherUtilisateur(noUtilisateur);
				listeEncheres.add(new Enchere(utilisateur, dateEnchere, article, montantEnchere));
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		return listeEncheres;
		
	}

}
