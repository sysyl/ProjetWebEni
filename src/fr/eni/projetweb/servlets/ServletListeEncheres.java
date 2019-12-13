package fr.eni.projetweb.servlets;

import java.io.IOException;
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
import fr.eni.projetweb.bll.UtilisateursManager;
import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.bo.Categorie;
import fr.eni.projetweb.bo.Utilisateur;

/**
 * Servlet implementation class ServletListeEncheres
 */
@WebServlet("/ServletListeEncheres")
public class ServletListeEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager articleManager;
	private UtilisateursManager utilisateurManager;
	private CategorieManager categorieManager;
	private List<Article> listeArticle = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListeEncheres() {
		super();
		articleManager = ArticleManager.getInstance();
		utilisateurManager = UtilisateursManager.getInstance();
		categorieManager = CategorieManager.getInstance();
		// TODO Auto-generated constructor stub
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
		List<Article> listeArticle = articleManager.getAllArticle();

		session.setAttribute("listeCategorie", listeCategorie);
		session.setAttribute("listeArticle", listeArticle);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/listeEncheres.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
