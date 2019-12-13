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
 * Servlet implementation class ServletListeEncheres
 */
@WebServlet("/ServletAccueilConnecte")
public class ServletAccueilConnecte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager articleManager;
	private UtilisateursManager utilisateurManager;
	private CategorieManager categorieManager;
	private List<Article> listeArticleConnecte = null;

	private boolean enchereOuverte = false;
	private boolean enchereEnCours = false;
	private boolean enchereRemporte = false;

	//
	private boolean venteEncours = false;
	private boolean ventePascommence = false;
	private boolean venteTermine = false;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAccueilConnecte() {
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

		// recuperation de la liste de categories pour la liste deroulante
		List<Categorie> listeCategorie = categorieManager.getAllCategories();
		request.setAttribute("listeCategorie", listeCategorie);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueilConnecte.jsp");
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

		String filter = request.getParameter("search");
		String category = request.getParameter("categorie");
		System.out.println(category);
		String choixAchat = request.getParameter("achats");
		String choixVente = request.getParameter("ventes");

		System.out.println("Achats : " + choixAchat + " || Ventes : " + choixVente);

		if (!("-1").equals(category)) {
			categorie = categorieManager.getCategorie(Integer.valueOf(category));
		}

		Utilisateur utilisateur = utilisateurManager.afficherUtilisateur((int) session.getAttribute("idUtilisateur"));

		System.out.println("filter :" + filter);

		try {
			if (("achats").equals(choixAchat)) {

				String enchereOuverte = request.getParameter("enchereouverte");
				String enchereEnCours = request.getParameter("enchereencours");
				String enchereRemporte = request.getParameter("enchereremporte");

				if (enchereOuverte != null) {
					this.enchereOuverte = true;
					System.out.println(enchereOuverte);

				}

				if (enchereEnCours != null) {
					this.enchereEnCours = true;
					System.out.println(enchereEnCours);

				}

				if (enchereRemporte != null) {
					this.enchereRemporte = true;
					System.out.println(enchereRemporte);

				}
				if (category.equals("-1")) {
					this.listeArticleConnecte = articleManager.articleFiltreAchatToutesCategorie(utilisateur, filter,
							this.enchereOuverte, this.enchereEnCours, this.enchereRemporte);
					System.out.println("aucun filtres achats");

				} else { // si filtre tout les champs
					listeArticleConnecte = articleManager.articleFiltreAchat(utilisateur, filter, categorie,
							this.enchereOuverte, this.enchereEnCours, this.enchereRemporte);
					System.out.println("filtre tout achats");
				}

			}
			if (("ventes").equals(choixVente)) {

				String venteEnCours = request.getParameter("venteencours");
				String venteNonDebute = request.getParameter("ventenondebute");
				String venteTermine = request.getParameter("ventetermine");

				if (venteEnCours != null) {
					this.venteEncours = true;
					System.out.println(venteEnCours);

				}

				if (venteNonDebute != null) {
					this.ventePascommence = true;
					System.out.println(venteNonDebute);

				}

				if (venteTermine != null) {
					this.venteTermine = true;
					System.out.println(venteTermine);

				}
				if (category.equals("-1")) {
					this.listeArticleConnecte = articleManager.articleFiltreVenteToutesCategorie(utilisateur, filter,
							this.venteEncours, this.ventePascommence, this.venteTermine);
					System.out.println("aucun filtres ventes");

				} else { // si filtre tout les champs
					listeArticleConnecte = articleManager.articleFiltreVente(utilisateur, filter, categorie,
							this.venteEncours, this.ventePascommence, this.venteTermine);
					System.out.println("filtre tout ventes");
				}
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		session.setAttribute("listeArticleConnecte", listeArticleConnecte);
		System.out.println(listeArticleConnecte.toString());

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueilConnecte.jsp");
		rd.forward(request, response);

	}
}
