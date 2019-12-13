package fr.eni.projetweb.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetweb.bll.ArticleManager;
import fr.eni.projetweb.bll.CategorieManager;
import fr.eni.projetweb.bll.MethodesUtiles;
import fr.eni.projetweb.bll.UtilisateursManager;
import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.bo.Categorie;
import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/ServletAccueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager articleManager;
	private UtilisateursManager utilisateurManager;
	private CategorieManager categorieManager;
	private List<Article> listeArticlePasConnecte = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAccueil() {
		super();
		articleManager = ArticleManager.getInstance();
		utilisateurManager = UtilisateursManager.getInstance();
		categorieManager = CategorieManager.getInstance();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// recuperation de la liste de categories pour la liste deroulante
		List<Categorie> listeCategorie = categorieManager.getAllCategories();
		session.setAttribute("listeCategorie", listeCategorie);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Categorie categorie = null;

		String dateDebut = request.getParameter("date_debut");
		String dateFin = request.getParameter("date_fin");
		String filter = request.getParameter("search");
		String category = request.getParameter("categorie");
		System.out.println(category);

		if (!("-1").equals(category)) {
			categorie = categorieManager.getCategorie(Integer.valueOf(category));
		}
//		System.out.println(categorie.toString());

		Timestamp dateDebutT = null;
		Timestamp dateFinT = null;

		System.out.println("dateDebut :" + dateDebut);
		System.out.println("dateFin :" + dateFin);
		System.out.println("filter :" + filter);

		try {
			if ((dateDebut.trim().length() == 0 || dateFin.trim().length() == 0) && category.equals("-1")) {
				listeArticlePasConnecte = articleManager.articlesFiltreToutesCategorie(dateDebutT, dateFinT, filter);
				System.out.println("aucun filtres");

			} else if (dateDebut.trim().length() == 0 || dateFin.trim().length() == 0) { // dates null
				listeArticlePasConnecte = articleManager.articlesFiltre(dateDebutT, dateFinT, filter, categorie);
				System.out.println("filtre tout sauf date");

			} else if (category.equals("-1")) {
				listeArticlePasConnecte = articleManager.articlesFiltreToutesCategorie(
						MethodesUtiles.getTimestampFromString(dateDebut),
						MethodesUtiles.getTimestampFromString(dateFin), filter);
				System.out.println("filtre tout sauf categorie");

			} else { // si filtre tout les champs
				listeArticlePasConnecte = articleManager.articlesFiltre(
						MethodesUtiles.getTimestampFromString(dateDebut),
						MethodesUtiles.getTimestampFromString(dateFin), filter, categorie);
				System.out.println("filtre tout");
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		session.setAttribute("listeArticlePasConnecte", listeArticlePasConnecte);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);

	}

}
