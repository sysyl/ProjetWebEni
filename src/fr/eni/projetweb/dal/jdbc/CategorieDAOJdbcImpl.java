/**
 * 
 */
package fr.eni.projetweb.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetweb.bo.Categorie;
import fr.eni.projetweb.dal.CategorieDAO;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Classe en charge de 
 * @version ProjetWebENI - V1.0
 * @author jpietras2019
 * @date 4 déc. 2019 - 12:30:05
 *
 */
public class CategorieDAOJdbcImpl implements CategorieDAO {
	
	public static final String SELECT_ALL = "SELECT * FROM CATEGORIES";
	public static final String SELECT_CATEGORIE = "SELECT * FROM CATEGORIES WHERE id=?";

	
	
	public List<Categorie> selectAll() throws BusinessException{
		List<Categorie> categories = new ArrayList<>();
		try (
				Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_ALL);
			) {
			ResultSet rs = stmt.executeQuery();	
			while(rs.next()) {
				categories.add(new Categorie(rs.getInt("no_categorie"), rs.getString("libelle")));
			}	
		} catch (Exception e) {
			// TODO: gerer erreurs DAl
			throw new BusinessException();
		}
		return categories;
	}



	@Override
	public Categorie selectCategorie(int id) throws BusinessException {
		Categorie categorie = null;
		try (
				Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_CATEGORIE);
			) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();	
			while(rs.next()) {
				categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
			}	
		} catch (Exception e) {
			// TODO: gerer erreurs DAl
			throw new BusinessException();
		}
		return categorie;
	}
}
