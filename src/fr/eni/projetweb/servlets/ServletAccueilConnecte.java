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
@WebServlet("/ServletAccueilConnecte")
public class ServletAccueilConnecte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager articleManager;
	private UtilisateursManager utilisateurManager;
	private CategorieManager categoriesManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAccueilConnecte() {
        super();
        articleManager = ArticleManager.getInstance();
        utilisateurManager = UtilisateursManager.getInstance();
        categoriesManager = CategorieManager.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//recuperation de la liste de categories pour la liste deroulante
		List<Categorie> listCategories = categoriesManager.getAllCategories();
		request.setAttribute("listCategories", listCategories);
		
		
		List<Article> listeArticle = articleManager.getAllArticle();
		List<String> listePseudoUtilisateurs = new ArrayList<String>();
		Utilisateur utilisateur = null;
		
		for (int i = 0; i < listeArticle.size(); i++) {
			int noUtilisateur = listeArticle.get(i).getNoUtilisateur();
			utilisateur = utilisateurManager.afficherUtilisateur(noUtilisateur);
			listePseudoUtilisateurs.add(utilisateur.getPseudo());
		} 
		
//		HttpSession session = request.getSession(true);
//		
//		session.setAttribute("listeArticle", listeArticle);
//		session.setAttribute("listePseudo", listePseudoUtilisateurs);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueilConnecte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
//		String search = request.getParameter("search");
//		String category = request.getParameter("thelist");
//		String achats = request.getParameter("achat"); 
//		String ventes = request.getParameter("vente");		
				
//		System.out.println(search);
//		System.out.println(category);
//		System.out.println(achats);
//		System.out.println(ventes);
//		
		doGet(request, response);
	}

}
