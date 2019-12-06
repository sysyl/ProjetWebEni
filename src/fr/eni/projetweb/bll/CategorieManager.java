/**
 * 
 */
package fr.eni.projetweb.bll;

import java.util.List;

import fr.eni.projetweb.bo.Categorie;
import fr.eni.projetweb.dal.CategorieDAO;
import fr.eni.projetweb.dal.DAOFactory;
import fr.eni.projetweb.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Classe en charge de 
 * @version ProjetWebENI - V1.0
 * @author jpietras2019
 * @date 4 déc. 2019 - 14:16:08
 *
 */
public class CategorieManager {
	
	private static CategorieManager instance;
	private CategorieDAO categorieDAO;
	
	private CategorieManager() {
		categorieDAO = DAOFactory.getCategorieDAO();
	}
	
	public static CategorieManager getInstance() {
		if(instance == null) {
			instance = new CategorieManager();
		}
		return instance;
	}
	
	public List<Categorie> getAllCategories() {
		List<Categorie> listCategories = null;
		try {
			listCategories = categorieDAO.selectAll();
		} catch (BusinessException e) {
			// TODO : ajouter code erreur
			e.printStackTrace();
		}
		return listCategories;
	}
	
	public Categorie getCategorie(int id) {
		Categorie categorie = null;
		try {
			categorie = categorieDAO.selectCategorie(id);
		} catch (BusinessException e) {
			// TODO : ajouter code erreur
			e.printStackTrace();
		}
		return categorie;
	}

}
