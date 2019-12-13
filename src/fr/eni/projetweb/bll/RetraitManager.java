
package fr.eni.projetweb.bll;

import fr.eni.projetweb.dal.ArticleDAO;
import fr.eni.projetweb.dal.DAOFactory;
import fr.eni.projetweb.dal.RetraitDAO;
import fr.eni.projetweb.dal.UtilisateurDAO;
import fr.eni.projetweb.exceptions.BusinessException;


public class RetraitManager {

	private static RetraitManager instance;
	private ArticleDAO articleDAO;
	private RetraitDAO retraitDAO;
	private UtilisateurDAO utilisateurDAO;

	private RetraitManager() {
		articleDAO = DAOFactory.getArticleDAO();
		retraitDAO = DAOFactory.getRetraitDAO();
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public static RetraitManager getInstance() {
		if (instance == null) {
			instance = new RetraitManager();
		}
		return instance;
	}

	/**
	 * methode en charge d'appeler la couche DAL pour pouvoir changer le statut
	 * de retrait à true et de declencher la methode qui credite le compte 
	 * du vendeur
	 */
	public void changerStatutRetrait(int idArticle, int noVendeur, int prixVente) throws BusinessException {	
		
		//je recupere le credit actuel du vendeur
		int creditUtilisateur = utilisateurDAO.selectUserById(noVendeur).getCredit();
		// j'y ajoute le montant du prix de la vente
		creditUtilisateur += prixVente;
		//je fait un update du statut de retrait qui va d'occuper aussi de l'update de credit d'utilisateur
		retraitDAO.updateRetraitStatut(idArticle, noVendeur, creditUtilisateur);
	}
	
	
}
