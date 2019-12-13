/**
 * 
 */
package fr.eni.projetweb.dal;

import java.sql.Timestamp;
import java.util.List;

import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.bo.Categorie;
import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Classe en charge de
 * 
 * @version ProjetWebENI - V1.0
 * @author slanger2019
 * @date 5 dï¿½c. 2019 - 14:39:10
 *
 */
public interface ArticleDAO {

	List<Article> selectArticle(Article article) throws BusinessException;

	void insertArticle(Article article) throws BusinessException;

	Article selectArticleById(int numeroArticle) throws BusinessException;

	List<Article> selectAllArticle() throws BusinessException;

	void deleteArticleById(int idArticle) throws BusinessException;

	void updateArticleById(Article article) throws BusinessException;
	
	List<Article> SelectArticleFilterAllCategory(Timestamp dateDebut, Timestamp dateFin, String contenu) throws BusinessException;

	List<Article> SelectArticleFilter(Timestamp dateDebut, Timestamp dateFin, String contenu, Categorie categorie) throws BusinessException;
	
	List<Article> selectArticlesByUserId(int idUser) throws BusinessException;

	/**
	 * Mï¿½thode en charge de
	 * 
	 * @param idUtilisateur
	 * @param contenu
	 * @param categorie
	 * @param encheresOuvertes
	 * @param mesEnchereEnCours
	 * @param mesEncheresRemportees
	 * @return
	 * @throws BusinessException
	 */
	List<Article> getArticleFilterBuy(Utilisateur utilisateur, String contenu, Categorie categorie, boolean encheresOuvertes,
			boolean mesEnchereEnCours, boolean mesEncheresRemportees) throws BusinessException;

	/**
	 * Mï¿½thode en charge de
	 * 
	 * @param idUtilisateur
	 * @param contenu
	 * @param categorie
	 * @param ventesEnCours
	 * @param ventesNonDebute
	 * @param ventesTerminees
	 * @return
	 * @throws BusinessException
	 */
	List<Article> SelectArticleFilterSell(Utilisateur utilisateur, String contenu, Categorie categorie, boolean ventesEnCours,
			boolean ventesNonDebute, boolean ventesTerminees) throws BusinessException;

	/**
	 * Mï¿½thode en charge de
	 * 
	 * @param date
	 * @return
	 * @throws BusinessException
	 */
	List<Article> SelectArticleByTimeStamp(Timestamp date) throws BusinessException;
	
	void updatePrixVenteArticleByIdArticle(int prixVente, int idArticle) throws BusinessException;

	/**
	 * Méthode en charge de
	 * @param utilisateur
	 * @param contenu
	 * @param encheresOuvertes
	 * @param mesEnchereEnCours
	 * @param mesEncheresRemportees
	 * @return
	 * @throws BusinessException 
	 */
	List<Article> getArticleFilterBuyAllCategory(Utilisateur utilisateur, String contenu, boolean encheresOuvertes,
			boolean mesEnchereEnCours, boolean mesEncheresRemportees) throws BusinessException;

	/**
	 * Méthode en charge de
	 * @param utilisateur
	 * @param contenu
	 * @param ventesEnCours
	 * @param ventesNonDebute
	 * @param ventesTerminees
	 * @return
	 * @throws BusinessException
	 */
	List<Article> SelectArticleFilterSellAllCategory(Utilisateur utilisateur, String contenu, boolean ventesEnCours,
			boolean ventesNonDebute, boolean ventesTerminees) throws BusinessException;

}
