package fr.eni.projetweb.bll;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.bo.Categorie;
import fr.eni.projetweb.bo.Retrait;
import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.dal.DAOFactory;
import fr.eni.projetweb.dal.VenteDAO;
import fr.eni.projetweb.exceptions.BusinessException;

public class VenteManager {

	private VenteDAO venteDAO;
	
	public VenteManager() {
		venteDAO = DAOFactory.getVenteDAO();
	}
	
	public void ajouterVente(String nomArticle, String description, String prixStr, String dateDebutStr, String dateFinStr, String rue, String codePostalStr, String ville, Utilisateur utilisateur, Categorie categorie) {
		// String nomArticle, String descriptionArticle, Date dateDebutEncheres, Date dateFinEncheres, Utilisateur utilisateur, Categorie categorie
		//LocalDateTime dateDebut = null;
		//LocalDateTime dateFin = null;
		Article article = null;
		Retrait retrait = null;
		String pathImg = null;
		int prix = 0;
		int codePostal = 0; // conversion ! ! ! 
		
		Timestamp dateDebut = Timestamp.valueOf(dateDebutStr);
		Timestamp dateFin = Timestamp.valueOf(dateFinStr);
		
		retrait = new Retrait(rue, codePostal, ville);
		
		
		article = new Article(nomArticle, description, dateDebut, dateFin, prix, prix, utilisateur, categorie, retrait, pathImg);
		
		try {
			
			venteDAO.insertArticle(article);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
		public LocalDateTime getDateTimeFromString(String dateTimeString) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			LocalDateTime lt = LocalDateTime.parse(dateTimeString, formatter);
			//System.out.println("datetime en localdate = " + lt.toString());
			return lt;
		}
}
