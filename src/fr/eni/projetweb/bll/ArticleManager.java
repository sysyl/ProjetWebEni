package fr.eni.projetweb.bll;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.bo.Categorie;
import fr.eni.projetweb.bo.Retrait;
import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.dal.ArticleDAO;
import fr.eni.projetweb.dal.DAOFactory;
import fr.eni.projetweb.exceptions.BusinessException;

public class ArticleManager {

	private static ArticleManager instance;
	private ArticleDAO articleDAO;

	private ArticleManager() {
		articleDAO = DAOFactory.getArticleDAO();
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

	public void ajouterVente(String nomArticle, String description, String prixStr, String dateDebutStr, String dateFinStr, String rue, String codePostalStr, String ville, Utilisateur utilisateur, Categorie categorie) throws BusinessException {

		BusinessException businessException = new BusinessException();
		String pathImg = null;
		int prix = MethodesUtiles.parseStringToInt(prixStr);
	
		System.out.println("ajouter vente bll prix " + prix);
	
		int codePostal = MethodesUtiles.verifierAdresse(rue, ville, codePostalStr, businessException);
		MethodesUtiles.verifierChampVide(nomArticle, businessException);
		MethodesUtiles.verifierChampVide(description, businessException);
		Timestamp dateDebut = getTimestampFromString(dateDebutStr);
		Timestamp dateFin = getTimestampFromString(dateFinStr);
		
		Retrait retrait = new Retrait(rue, codePostal, ville, false);
		Article article = new Article(nomArticle, description, dateDebut, dateFin, prix, prix, utilisateur, categorie, retrait, pathImg);
	//	Article article = new Article(nomArticle, description, dateDebut, dateFin, prix, prix, utilisateur, categorie, retrait, pathImg);
		
		if (!(businessException.hasErreurs())) {
			articleDAO.insertArticle(article);
		} else {
			throw businessException;
		}

		
	}
	
	
	public String getStringDateFromTimestamp(Timestamp ts) {
		Date date1 = new Date(ts.getTime());
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
        String strDate = dateFormat.format(date1);  
     //   System.out.println("Converted String: " + strDate);  
        return strDate;
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

	public Timestamp getTimestampFromString(String dateTimeString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime ldt = LocalDateTime.parse(dateTimeString, formatter);
		Timestamp ts = Timestamp.valueOf(ldt);
		return ts;
	}
}
