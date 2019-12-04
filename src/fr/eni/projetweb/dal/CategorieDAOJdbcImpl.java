/**
 * 
 */
package fr.eni.projetweb.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Classe en charge de 
 * @version ProjetWebENI - V1.0
 * @author jpietras2019
 * @date 4 déc. 2019 - 12:30:05
 *
 */
public class CategorieDAOJdbcImpl implements CategorieDAO {
	
	public static final String SELECT_CATEGORIES = "SELECT libelle FROM CATEGORIES";

	
	
	public List<String> selectCategories() throws BusinessException{
		List<String> categories = new ArrayList<>();
		try (
				Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_CATEGORIES);
			) {
			ResultSet rs = stmt.executeQuery();	
			while(rs.next()) {
				categories.add(rs.getString("libelle"));
			}	
		} catch (Exception e) {
			// TODO: gerer erreurs DAl
			throw new BusinessException();
		}
		return categories;
	}
}
