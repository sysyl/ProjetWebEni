/**
 * 
 */
package fr.eni.projetweb.bll;

import java.util.List;

import fr.eni.projetweb.dal.CategorieDAO;
import fr.eni.projetweb.dal.CategorieDAOJdbcImpl;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Classe en charge de 
 * @version ProjetWebENI - V1.0
 * @author jpietras2019
 * @date 4 déc. 2019 - 14:16:08
 *
 */
public class CategorieManager {
	
	private CategorieDAO categorieDAO = new CategorieDAOJdbcImpl();
	
	public List<String> getCategories() {
		List<String> listCategories = null;
		try {
			listCategories = categorieDAO.selectCategories();
		} catch (BusinessException e) {
			// TODO : ajouter code erreur
			e.printStackTrace();
		}
		return listCategories;
		
		
	}

}
