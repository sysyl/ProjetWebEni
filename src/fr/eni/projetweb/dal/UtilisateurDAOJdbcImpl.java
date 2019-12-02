package fr.eni.projetweb.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

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
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Utilisateurs WHERE pseudo=? AND mot_de_passe=?");

			stmt.setString(1, pseudo);
			stmt.setString(2, mot_de_passe);

			return stmt.executeQuery().next();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

}
