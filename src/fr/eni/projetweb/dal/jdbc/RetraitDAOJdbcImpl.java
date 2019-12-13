package fr.eni.projetweb.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.projetweb.bo.Retrait;
import fr.eni.projetweb.dal.CodesResultatDAL;
import fr.eni.projetweb.dal.DAOFactory;
import fr.eni.projetweb.dal.RetraitDAO;
import fr.eni.projetweb.dal.UtilisateurDAO;
import fr.eni.projetweb.exceptions.BusinessException;

public class RetraitDAOJdbcImpl implements RetraitDAO {
	
	
	
	public static final String UPDATE_RETRAIT_STATUT = "UPDATE RETRAITS SET statut=1 WHERE no_retrait=?";
	private static final String UPDATE_RETRAIT = "UPDATE RETRAITS SET rue=?, code_postal=?, ville=? WHERE no_retrait=?";
	public static String SELECT_RETRAIT_BY_ID="SELECT * FROM RETRAITS WHERE no_retrait=?";
	
	
	
	/**
	 * Des que celui qui a remporte l'enchere clique sur le bouton 'Retrait effectue'
	 * la colonne statut recoit une valeur 'true' 
	 * 
	 * En meme temps, le compte du vendeur est credite pour le montant
	 * de la plus grande enchere
	 */
	@Override
	public void updateRetraitStatut(int idArticle, int noVendeur, int credit) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()){
			try	{
				cnx.setAutoCommit(false);
				
				// update de la colonne statut - > true
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_RETRAIT_STATUT);
				pstmt.setInt(1, idArticle);
				pstmt.executeUpdate();
				
				// compte vendeur credite
				UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
				utilisateurDAO.updateCreditUtilisateur(credit, noVendeur);
						
				cnx.commit();	
			}
			catch(Exception e){
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.RETRAIT_STATUT_ERREUR);
			throw businessException;
		}	
	}
	
	/**
	 *  retourne une instance de Retrait selon no_retrait passe en parametre
	 */
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
	public void updateRetrait(Retrait retrait) throws BusinessException {
		if(retrait==null){
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
	
		try(Connection cnx = ConnectionProvider.getConnection()){
			try	{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_RETRAIT); 				
				pstmt.setString(1, retrait.getRue());
				pstmt.setInt(2, retrait.getCodePostal());
				pstmt.setString(3, retrait.getVille());
				pstmt.setInt(4, retrait.getIdRetrait());
				pstmt.executeUpdate();
				cnx.commit();	
			}
			catch(Exception e){
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_RETRAIT_ERREUR);
			throw businessException;
		}
			
	}
	
}
