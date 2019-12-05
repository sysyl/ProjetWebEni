package fr.eni.projetweb.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;

import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.bo.Categorie;
import fr.eni.projetweb.bo.Retrait;
import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.dal.CodesResultatDAL;
import fr.eni.projetweb.dal.VenteDAO;
import fr.eni.projetweb.exceptions.BusinessException;

public class VenteDAOJdbcImpl implements VenteDAO{

	

	
	public static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article, description_article, "
								+ "debut_encheres, fin_encheres, prix_initial, prix_vente, no_utilisateur, "
								+ "no_categorie, no_retrait, chemin_img) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static final String INSERT_RETRAIT = "INSERT INTO RETRAITS(rue, code_postal, ville) VALUES(?, ?, ?);";
	
	@Override
	public void insertArticle(Article article) throws BusinessException {
		if(article==null){
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
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
				
				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				if(rs.next()){
					article.getRetrait().setIdRetrait(rs.getInt(1));
				}
				rs.close();
				pstmt.close();	
			
				// insertion article
				pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, article.getNomArticle());
				pstmt.setString(2, article.getDescriptionArticle());
				
				Timestamp ts1 = article.getDebutEncheres();
				Timestamp ts2 = article.getFinEncheres();
				pstmt.setTimestamp(3, ts1); 
				pstmt.setTimestamp(4, ts2);	
				
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
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
		
		
	}

}
