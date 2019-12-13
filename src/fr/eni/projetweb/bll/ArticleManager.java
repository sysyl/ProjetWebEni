package fr.eni.projetweb.bll;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

import org.omg.PortableServer.IdAssignmentPolicy;

import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.bo.Categorie;
import fr.eni.projetweb.bo.Retrait;
import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.dal.ArticleDAO;
import fr.eni.projetweb.dal.CategorieDAO;
import fr.eni.projetweb.dal.CodesResultatDAL;
import fr.eni.projetweb.dal.DAOFactory;
import fr.eni.projetweb.exceptions.BusinessException;

public class ArticleManager {

	private static ArticleManager instance;
	private ArticleDAO articleDAO;
	private CategorieDAO categorieDAO;

	private ArticleManager() {
		articleDAO = DAOFactory.getArticleDAO();
		categorieDAO = DAOFactory.getCategorieDAO();
	}

	public static ArticleManager getInstance() {
		if (instance == null) {
			instance = new ArticleManager();
		}
		return instance;
	}

	public Article getArticleById(int idArticle) throws BusinessException {
		Article article = articleDAO.selectArticleById(idArticle);
		return article;

	}
	
	/**
	 * Méthode en charge de verifier si le nom d'article renseigne ne depasse pas les
	 * limites fixes par la bdd -> 30
	 * @param nomArticle
	 * @param businessException
	 */
	private void verifierNomArticle(String nomArticle, BusinessException businessException) {
		if(nomArticle.length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.NOM_ARTICLE_TROP_LONG);		
		}
		
	}

	/**
	 * Méthode en charge de verifier si la description d'article renseignee ne depasse pas les
	 * limites fixes par la bdd -> 300
	 * @param description
	 * @param businessException
	 */
	private void verifierDescription(String description, BusinessException businessException) {
		if(description.length() > 300) {
			businessException.ajouterErreur(CodesResultatBLL.DESCRIPTION_TROP_LONGUE);		
		}

		
	}

	public void ajouterVente(String nomArticle, String description, String prixStr, String dateDebutStr,
			String dateFinStr, String rue, String codePostalStr, String ville, Utilisateur utilisateur,
			Categorie categorie, String lienImage) throws BusinessException {

		BusinessException businessException = new BusinessException();
		int prix = MethodesUtiles.parseStringToInt(prixStr);

		int codePostal = MethodesUtiles.verifierAdresse(rue, ville, codePostalStr, businessException);
		MethodesUtiles.verifierChampVide(nomArticle, businessException);
		MethodesUtiles.verifierChampVide(description, businessException);
		MethodesUtiles.verifierLienImage(lienImage, businessException);
		verifierNomArticle(nomArticle, businessException);
		verifierDescription(description, businessException);
		Timestamp dateDebut = MethodesUtiles.getTimestampFromString(dateDebutStr);
		Timestamp dateFin = MethodesUtiles.getTimestampFromString(dateFinStr);

		MethodesUtiles.validerDatesEnchere(dateDebut, dateFin, businessException);

		Retrait retrait = new Retrait(rue, codePostal, ville, false);
		Article article = new Article(nomArticle, description, dateDebut, dateFin, prix, prix, utilisateur, categorie,
				retrait, lienImage);

		if (!(businessException.hasErreurs())) {
			articleDAO.insertArticle(article);
		} else {
			throw businessException;
		}

	}

	public List<Article> getAllArticle() {
		List<Article> listeArticle = null;
		try {
			listeArticle = articleDAO.selectAllArticle();
		} catch (BusinessException e) {
			// TODO : ajouter code erreur
			e.printStackTrace();
		}
		return listeArticle;
	}

	public List<Article> getListArticlesByUserId(int idUser) {
		List<Article> listeArticles = null;
		try {
			listeArticles = articleDAO.selectArticlesByUserId(idUser);
		} catch (BusinessException e) {
			// TODO : ajouter code erreur
			e.printStackTrace();
		}
		return listeArticles;
	}

	public void modifierVente(int idArticle, String nomArticle, String description, String dateDebutStr,
			String dateFinStr, String prixStr, String rue, int categorieId, String codePostalStr, String ville,
			Utilisateur utilisateur, String idRetraitStr) throws BusinessException {

		BusinessException businessException = new BusinessException();
		String pathImg = null;
		int idRetrait = MethodesUtiles.parseStringToInt(idRetraitStr);
		int prix = MethodesUtiles.parseStringToInt(prixStr);
		int codePostal = MethodesUtiles.verifierAdresse(rue, ville, codePostalStr, businessException);
		MethodesUtiles.verifierChampVide(nomArticle, businessException);
		MethodesUtiles.verifierChampVide(description, businessException);
		verifierDescription(description, businessException);
		Timestamp dateDebut = MethodesUtiles.getTimestampFromString(dateDebutStr);
		Timestamp dateFin = MethodesUtiles.getTimestampFromString(dateFinStr);

		MethodesUtiles.validerDatesEnchere(dateDebut, dateFin, businessException);

		Categorie categorie = categorieDAO.selectCategorie(categorieId);

		Retrait retrait = new Retrait(rue, codePostal, ville, false);

		Article article = new Article(idArticle, nomArticle, description, dateDebut, dateFin, prix, prix, utilisateur,
				categorie, retrait, pathImg);
		article.getRetrait().setIdRetrait(idRetrait);
		;
		if (!(businessException.hasErreurs())) {
			articleDAO.updateArticleById(article);
		} else {
			throw businessException;
		}

	}

	public void supprimerVente(int idArticle) throws BusinessException {

		articleDAO.deleteArticleById(idArticle);

	}

	public List<Article> articleParDate(Timestamp date) throws BusinessException {
		try {
			return articleDAO.SelectArticleByTimeStamp(date);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
	}

	public List<Article> articlesFiltre(Timestamp dateDebut, Timestamp dateFin, String contenu, Categorie categorie)
			throws BusinessException {
		try {
			return articleDAO.SelectArticleFilter(dateDebut, dateFin, contenu, categorie);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
	}

	public List<Article> articlesFiltreToutesCategorie(Timestamp dateDebut, Timestamp dateFin, String contenu)
			throws BusinessException {
		try {
			return articleDAO.SelectArticleFilterAllCategory(dateDebut, dateFin, contenu);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
	}

	public List<Article> articleFiltreVente(Utilisateur utilisateur, String contenu, Categorie categorie,
			boolean ventesEnCours, boolean ventesNonDebute, boolean ventesTerminees) throws BusinessException {
		try {
			return articleDAO.SelectArticleFilterSell(utilisateur, contenu, categorie, ventesEnCours, ventesNonDebute,
					ventesTerminees);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
	}
	
	public List<Article> articleFiltreVenteToutesCategorie(Utilisateur utilisateur, String contenu,
			boolean ventesEnCours, boolean ventesNonDebute, boolean ventesTerminees) throws BusinessException {
		try {
			return articleDAO.SelectArticleFilterSellAllCategory(utilisateur, contenu, ventesEnCours, ventesNonDebute,
					ventesTerminees);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
	}

	public List<Article> articleFiltreAchat(Utilisateur utilisateur, String contenu, Categorie categorie,
			boolean encheresOuvertes, boolean mesEnchereEnCours, boolean mesEncheresRemportees)
			throws BusinessException {
		return articleDAO.getArticleFilterBuy(utilisateur, contenu, categorie, encheresOuvertes, mesEnchereEnCours,
				mesEncheresRemportees);

	}

	public List<Article> articleFiltreAchatToutesCategorie(Utilisateur utilisateur, String contenu,
			boolean encheresOuvertes, boolean mesEnchereEnCours, boolean mesEncheresRemportees)
			throws BusinessException {
		return articleDAO.getArticleFilterBuyAllCategory(utilisateur, contenu, encheresOuvertes, mesEnchereEnCours,
				mesEncheresRemportees);
	}
}
