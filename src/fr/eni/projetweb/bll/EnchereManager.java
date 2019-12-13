package fr.eni.projetweb.bll;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.bo.Enchere;
import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.dal.CodesResultatDAL;
import fr.eni.projetweb.dal.DAOFactory;
import fr.eni.projetweb.dal.EnchereDAO;
import fr.eni.projetweb.exceptions.BusinessException;

public class EnchereManager {
	
	private static EnchereManager instance;
	private EnchereDAO enchereDAO;
	private UtilisateursManager utilisateurManager;
	private ArticleManager articleManager;

	private EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
		utilisateurManager = UtilisateursManager.getInstance();
		articleManager = ArticleManager.getInstance();
	}

	public static EnchereManager getInstance() {
		if (instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}

	
	
	public List<Enchere> getEncheresByIdArticle(int idArticle) throws BusinessException{
		List<Enchere> listeEncheresUnArticle = enchereDAO.selectEncheresByIdArticle(idArticle);	
		return listeEncheresUnArticle;
	}
	
	public void ajouterEnchere(int idUtilisateur, Timestamp dateEnchere, String idArticleString, String valeur) throws BusinessException {
		List<Integer> listeCodesErreur=new ArrayList<>();	
		BusinessException businessException = new BusinessException();
		int montantEnchere = MethodesUtiles.parseStringToInt(valeur);
		int idArticle = MethodesUtiles.parseStringToInt(idArticleString); // TODO : erreur
		Article article = articleManager.getArticleById(idArticle);
		Utilisateur utilisateur = utilisateurManager.afficherUtilisateur(idUtilisateur);
	
		int creditUtilisateur = utilisateur.getCredit();
		int nouveauCredit = creditUtilisateur - montantEnchere;
		System.out.println("nouveau credit = " + nouveauCredit);
		
		if(nouveauCredit < 0) {
			System.out.println("erreur credit");
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREDIT_NEGATIF_ERREUR);
		}
		else {
		
			List<Enchere> listeEncheres = enchereDAO.selectAllEncheres();
	
			boolean plusDuneEnchere = false;
			int plusGrandMontant = 0;
			int montant = 0;
			Enchere plusGrandEnchere = null;
			for(Enchere e : listeEncheres) {
				
				// je regard il y a deja d'autres encheres sur cet article
				if(e.getArticle().getNoArticle() == article.getNoArticle() ) {
					plusDuneEnchere = true; // true s'il y en a une
						
					if(plusDuneEnchere == true) { 
						montant = e.getMontantEnchere(); // je recupere les montants d'encheres sur cet article
						
						if(montant > plusGrandMontant) {
							plusGrandMontant = montant; // le plus grand montant pour l'enchere de cette article pour savoir avec quoi comparer
							plusGrandEnchere = e; //je recupere l'instance d'enchere precedente
							System.out.println("plus grande montant pour cet art " + plusGrandMontant + " + nom. util " + plusGrandEnchere.getUtilisateur().getNom());
						}
					}
				}
			}
			
			System.out.println("plus dune enchere : " + plusDuneEnchere);

				if(montantEnchere < plusGrandMontant) {
					System.out.println("montant enchere inferieur"); // ca le reconnait
					businessException.ajouterErreur(CodesResultatBLL.ENCHERE_INFERIEURE_ERREUR);
				}
				if(montantEnchere == plusGrandMontant) {
				System.out.println("le meme montant, pas possible d'encherir");		
				businessException.ajouterErreur(CodesResultatBLL.MEME_MONTANT_ENCHERE_ERREUR);
				}
				if(montantEnchere < article.getPrixInitial() ) {
					System.out.println("proposition inferieure au prix initial");		
					businessException.ajouterErreur(CodesResultatBLL.PROPOSITION_MONTANT_ENCHERE_ERREUR);
				}
				
				
				utilisateur.setCredit(nouveauCredit);
				article.setPrixVente(montantEnchere);
				Enchere enchere = new Enchere(utilisateur, dateEnchere, article, montantEnchere);
				if (!(businessException.hasErreurs()) && plusDuneEnchere == false) {
					System.out.println("methode 1");
					enchereDAO.insertEnchere(enchere);
					System.out.println("manager encher test 3");
				} 
				else if (!(businessException.hasErreurs()) && plusDuneEnchere == true) {
					System.out.println("im y a une autre grande encheres methode 2");
					enchereDAO.insertEnchereetUpdate(enchere, plusGrandEnchere);
					
				}
				else if(businessException.hasErreurs()) {
					throw businessException;

				}

			}
			if(businessException.hasErreurs()) {
				System.out.println("les erreurs : " );
				throw businessException;
			}
			
		
		

		
	}
}
