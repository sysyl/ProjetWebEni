/**
 * 
 */
package fr.eni.projetweb.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.bo.Categorie;
import fr.eni.projetweb.bo.Retrait;
import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.dal.CodesResultatDAL;
import fr.eni.projetweb.dal.DAOFactory;
import fr.eni.projetweb.dal.UtilisateurDAO;
import fr.eni.projetweb.dal.jdbc.ConnectionProvider;
import fr.eni.projetweb.dal.ArticleDAO;
import fr.eni.projetweb.dal.CategorieDAO;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Classe en charge de 
 * @version ProjetWebENI - V1.0
 * @author slanger2019
 * @date 5 d�c. 2019 - 14:37:15
 *
 */
public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	private static final String SELECT_ALL_ACHATS = "SELECT nom_article, prix_vente, fin_encheres, no_utilisateur FROM ARTICLES_VENDUS";
	public static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article, description_article, "
			+ "debut_encheres, fin_encheres, prix_initial, prix_vente, no_utilisateur, "
			+ "no_categorie, no_retrait, chemin_img) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String SELECT_ARTICLE_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article=?";

	public static final String INSERT_RETRAIT = "INSERT INTO RETRAITS(rue, code_postal, ville, statut) VALUES(?, ?, ?, ?);";
	public static final String UPDATE_RETRAIT_STATUT = "UPDATE RETRAITS SET statut=1 WHERE no_retrait=?";
	
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

	


	@Override
	public void insertArticle(Article article) throws BusinessException {
		if(article==null){
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		System.out.println("dal insert article prix inital" + article.getPrixInitial() );
		System.out.println("dal insert article prix vente" + article.getPrixVente() );
		try(Connection cnx = ConnectionProvider.getConnection()){
			try	{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				
				//insertion retrait avant article pour recupere sa cle primaire
				pstmt = cnx.prepareStatement(INSERT_RETRAIT, PreparedStatement.RETURN_GENERATED_KEYS); 
				pstmt.setString(1, article.getRetrait().getRue());
				pstmt.setInt(2,article.getRetrait().getCodePostal());
				pstmt.setString(3, article.getRetrait().getVille());
				pstmt.setBoolean(4, false);
				
				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				if(rs.next()){
				article.getRetrait().setIdRetrait(rs.getInt(1));
				}
				rs.close();
				pstmt.close();	
				
				pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, article.getNomArticle());
				pstmt.setString(2, article.getDescriptionArticle());
				
				pstmt.setTimestamp(3, article.getDebutEncheres()); 
				pstmt.setTimestamp(4, article.getFinEncheres());	
				
				pstmt.setInt(5, article.getPrixInitial());
				pstmt.setInt(6, article.getPrixVente());
				pstmt.setInt(7, article.getUtilisateur().getNoUtilisateur());
				pstmt.setInt(8, article.getCategorie().getNoCategorie());
				pstmt.setInt(9, article.getRetrait().getIdRetrait());
				
				if(article.getPathImg() == null || article.getPathImg().trim().length() == 0)
					pstmt.setNull(10, Types.VARCHAR);
				else
					pstmt.setString(10, article.getPathImg().trim());
				
				pstmt.executeUpdate();
				
				rs = pstmt.getGeneratedKeys();
				if(rs.next()){
					article.setNoArticle(rs.getInt(1));
				}
				rs.close();
				pstmt.close();
				
				cnx.commit();
		}
			catch(Exception e){
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}
	
	public static String SELECT_RETRAIT_BY_ID="SELECT * FROM RETRAITS WHERE no_retrait=?";
	
	public Retrait selectRetraitById(int idRetrait) throws BusinessException {
		Retrait retrait = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_RETRAIT_BY_ID);
			pstmt.setInt(1, idRetrait);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				retrait = new Retrait(rs.getInt("no_retrait"), rs.getString("rue"), rs.getInt("code_postal"),rs.getString("ville"), rs.getBoolean("statut"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.REGLE_RETRAIT_ERREUR);
			throw businessException;
		}
			
		return retrait;
	}

	@Override
	public Article selectArticleById(int numeroArticle) throws BusinessException {
		Article article = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE_BY_ID);
			pstmt.setInt(1, numeroArticle);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				int noArticle = rs.getInt("no_article");
				String nomArticle = rs.getString("nom_article");
				String description = rs.getString("description_article");
				int prixInitial = rs.getInt("prix_initial");
				int prixVente = rs.getInt("prix_vente");
				int noUtilisateur = rs.getInt("no_utilisateur");
				int noCategorie = rs.getInt("no_categorie");
				int noRetrait = rs.getInt("no_retrait");
				Timestamp debutEnch = rs.getTimestamp("debut_encheres");
				Timestamp finEnch = rs.getTimestamp("fin_encheres");
				String cheminImg = rs.getString("chemin_img");
				
				UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
				Utilisateur utilisateur = utilisateurDAO.selectUserById(noUtilisateur);
				
				Retrait retrait = selectRetraitById(noRetrait);
				
				CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
				Categorie categorie = categorieDAO.selectCategorie(noCategorie);
				
				article = new Article(noArticle, nomArticle, description, debutEnch, finEnch, prixInitial, prixVente, utilisateur, categorie, retrait, cheminImg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.REGLE_ARTICLE_ERREUR);
			throw businessException;
		}
		return article;
	}



	@Override
	public void updateRetraitStatut(int idArticle) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_RETRAIT_STATUT);
			pstmt.setInt(1, idArticle);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
		//	businessException.ajouterErreur(CodesResultatDAL.REGLE_ARTICLE_ERREUR);
			throw businessException;
		}
		
	}


	/**
	 * {@inheritDoc}
	 * @see fr.eni.projetweb.dal.ArticleDAO#selectAllArticle()
	 */
	@Override
	public List<Article> selectAllArticle() throws BusinessException {
		List<Article> listeArticle = new ArrayList<>();
		try (
				Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_ALL_ACHATS);
			) {
			ResultSet rs = stmt.executeQuery();	
			while(rs.next()) {
				listeArticle.add(new Article(rs.getString("nom_article"), rs.getInt("prix_vente"), rs.getTimestamp("fin_encheres"), rs.getInt("no_utilisateur")));
			}	
		} catch (Exception e) {
			// TODO: gerer erreurs DAl
			throw new BusinessException();
		}
		return listeArticle;
	}

	
	
	
	
}
