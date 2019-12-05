/**
 * 
 */
package fr.eni.projetweb.dal;

import java.util.List;

import fr.eni.projetweb.bo.Categorie;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Classe en charge de 
 * @version ProjetWebENI - V1.0
 * @author jpietras2019
 * @date 4 déc. 2019 - 14:13:54
 *
 */
public interface CategorieDAO {
	public List<Categorie> selectAll() throws BusinessException;
	
	public Categorie selectCategorie(int id) throws BusinessException;
}
