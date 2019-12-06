package fr.eni.projetweb.dal.jdbc;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.dal.CodesResultatDAL;
import fr.eni.projetweb.dal.UtilisateurDAO;
import fr.eni.projetweb.exceptions.BusinessException;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	public static final String INSERT_NEW_USER = "INSERT INTO UTILISATEURS "
			+ "(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String SELECT_PSEUDOS = "SELECT pseudo FROM UTILISATEURS";
	public static final String SELECT_EMAILS = "SELECT email FROM UTILISATEURS";
	public static final String SELECT_ALL_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo=?";
	public static final String SELECT_ALL_BY_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur=?";
	public static final String UPDATE_USER = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE no_utilisateur=?";
	public static final String DELETE_USER = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?";
	public static final String SELECT_PSEUDOS_EXCEPT_USER_ID = "SELECT pseudo FROM UTILISATEURS WHERE no_utilisateur<>?";

	public static final String SELECT_EMAILS_EXCEPT_USER_ID = "SELECT email FROM UTILISATEURS WHERE no_utilisateur<>?";
	
	
	static {
		try {
			DriverManager.deregisterDriver(new SQLServerDriver());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean verifierUtilisateur(String pseudo, String mot_de_passe) {
		try (Connection con = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM Utilisateurs WHERE pseudo=? AND mot_de_passe=?");

			stmt.setString(1, pseudo);
			stmt.setString(2, mot_de_passe);

			return stmt.executeQuery().next();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	public Utilisateur login(Utilisateur user) {

		// preparing some objects for connection
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		String pseudo = user.getPseudo();
		String mdp = user.getMot_de_passe();

		String searchQuery = "select * from Utilisateurs where pseudo='" + pseudo + "' AND mot_de_passe='" + mdp + "'";

		// "System.out.println" prints in the console; Normally used to trace the
		// process
		System.out.println("Utilisateur pseudo : " + pseudo);
		System.out.println("Utilisateur mot de passe :" + mdp);
		System.out.println("Query: " + searchQuery);

		try {
			// connect to DB
			con = ConnectionProvider.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			// if user does not exist set the isValid variable to false
			if (!more) {
				System.out.println("Ce compte n'existe pas, merci de vous enregistrer avant ");
				user.setValid(false);
			}

			// if user exists set the isValid variable to true
			else if (more) {
				String firstName = rs.getString("prenom");
				String lastName = rs.getString("nom");	

				System.out.println("Bienvenu " + firstName);
				user.setPrenom(firstName);
				user.setNom(lastName);
				user.setNoUtilisateur(rs.getInt("no_utilisateur"));
				user.setValid(true);
			}
		}

		catch (Exception ex) {
			System.out.println("Echec connexion: Exception :" + ex);
		}

		// some exception handling
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}

			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
				}

				con = null;
			}
		}

		return user;

	}

	@Override
	public void insertNewUser(Utilisateur utilisateur) throws BusinessException{
		try (Connection con = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(INSERT_NEW_USER, PreparedStatement.RETURN_GENERATED_KEYS);
		
			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
		
			if(utilisateur.getTelephone().trim().length() == 0)
				stmt.setNull(5, Types.VARCHAR);
			else
				stmt.setString(5, utilisateur.getTelephone());

			stmt.setString(6, utilisateur.getRue());
			stmt.setInt(7, utilisateur.getCodePostal());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, utilisateur.getMot_de_passe());
			stmt.setInt(10, utilisateur.getCredit());
			stmt.setBoolean(11, utilisateur.isAdministrateur());
			
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			
			while(rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}

			rs.close();
		} catch (Exception e) {
			// TODO: gerer erreurs DAl
			throw new BusinessException();
		}

		
	}
	
	
	
	
	public List<String> selectPseudos(String pseudo) throws BusinessException {
		List<String> pseudos = new ArrayList<>();
		try (
				Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_PSEUDOS);
			) {
			ResultSet rs = stmt.executeQuery();	
			while(rs.next()) {
				pseudos.add(rs.getString("pseudo"));
			}	
		} catch (Exception e) {
			// TODO: gerer erreurs DAl
			throw new BusinessException();
		}
		return pseudos;
	}
	
	public List<String> selectEmails(String email) throws BusinessException {
		List<String> emails = new ArrayList<>();
		try (
				Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_EMAILS);
			) {
			ResultSet rs = stmt.executeQuery();	
			while(rs.next()) {
				emails.add(rs.getString("email"));
			}	
		} catch (Exception e) {
			// TODO: gerer erreurs DAl
			throw new BusinessException();
		}
		return emails;
	}

	@Override
	public Utilisateur selectUserByPseudo(String pseudo) throws BusinessException {
		Utilisateur utilisateur = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_BY_PSEUDO);
			pstmt.setString(1, pseudo);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue") , rs.getInt("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.REGLE_UTILISATEUR_ERREUR);
			throw businessException;
		}
		return utilisateur;
	}
	
	@Override
	public Utilisateur selectUserById(int noUtilisateur) throws BusinessException {
		Utilisateur utilisateur = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_BY_ID);
			pstmt.setInt(1, noUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue") , rs.getInt("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.REGLE_UTILISATEUR_ERREUR);
			throw businessException;
		}
		return utilisateur;
	}
	// UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE pseudo=?"
	@Override
	public void updateUser(Utilisateur utilisateur) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_USER);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setInt(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMot_de_passe());
			pstmt.setInt(10, utilisateur.getNoUtilisateur());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_USER_ERREUR);
			throw businessException;
		}
	}



/**
	 * {@inheritDoc}
	 * 
	 * @see fr.eni.projetweb.dal.UtilisateurDAO#selectPseudosExceptUserId(java.lang.String)
	 */
	@Override
	public List<String> selectPseudosExceptUserId(int numeroUtilisateur) throws BusinessException {
		System.out.println("dal selecto pseudo sauf " + numeroUtilisateur);
		List<String> pseudos = new ArrayList<>();
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_PSEUDOS_EXCEPT_USER_ID);) {
			stmt.setInt(1, numeroUtilisateur);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				pseudos.add(rs.getString("pseudo"));
			}
		} catch (Exception e) {
			// TODO: gérer erreurs DAL
			throw new BusinessException();
		}
		return pseudos;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see fr.eni.projetweb.dal.UtilisateurDAO#selectEmailsExceptUserId(java.lang.String)
	 */
	@Override
	public List<String> selectEmailsExceptUserId(int numeroUtilisateur) throws BusinessException {
		List<String> emails = new ArrayList<>();
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_EMAILS_EXCEPT_USER_ID);) {
			stmt.setInt(1, numeroUtilisateur);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				emails.add(rs.getString("email"));
			}
		} catch (Exception e) {
			// TODO: gerer erreurs DAl
			throw new BusinessException();
		}
		return emails;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @see fr.eni.projetweb.dal.UtilisateurDAO#deleteUser(fr.eni.projetweb.bo.Utilisateur)
	 */
	@Override
	public void deleteUser(int numeroUtilisateur) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_USER);
			pstmt.setInt(1, numeroUtilisateur);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_USER_ERREUR);
			throw businessException;
		}

	}
}
