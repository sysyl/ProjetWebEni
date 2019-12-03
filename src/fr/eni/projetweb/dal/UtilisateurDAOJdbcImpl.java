package fr.eni.projetweb.dal;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

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

}
