package fr.eni.projetweb.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetweb.bll.ArticleManager;
import fr.eni.projetweb.bll.EnchereManager;
import fr.eni.projetweb.bll.MethodesUtiles;
import fr.eni.projetweb.bll.UtilisateursManager;
import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.bo.Enchere;
import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Servlet implementation class ServletEnchereTerminee
 */
@WebServlet("/ServletEnchereTerminee")
public class ServletEnchereTerminee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnchereManager enchereManager;
	private ArticleManager articleManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEnchereTerminee() {
        super();
        enchereManager = EnchereManager.getInstance();
        articleManager = ArticleManager.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet servletEnchere terminee");
		String noArticle = (String) request.getAttribute("noArticle");
		request.setAttribute("noArticle", noArticle);
			doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// si l'enchere terminee et on a clique sur 'Resultats'
		int idArticle = 0;
		String noArticleString = null;
		if(request.getParameter("noArticle") != null) {
			noArticleString = request.getParameter("noArticle");
			idArticle = Integer.valueOf(noArticleString);	
		}
		else if(request.getAttribute("noArticle") != null) {
			noArticleString = (String) request.getAttribute("noArticle");
			idArticle = Integer.valueOf(noArticleString);	
		}
			
		int idUtilisateur = 0;
		if(request.getSession().getAttribute("idUtilisateur") != null ) {
			idUtilisateur = (int)request.getSession().getAttribute("idUtilisateur");	
		}
		
		UtilisateursManager utilisateurManager = UtilisateursManager.getInstance();
		Utilisateur utilisateur = utilisateurManager.afficherUtilisateur(idUtilisateur);
			
		try {
			Article article = articleManager.getArticleById(idArticle);
			List<Enchere> listeEncheres = enchereManager.getEncheresByIdArticle(idArticle);
			
			Enchere meilleureEnchere = null;
			int montant = 0;
			int montantMax = 0;
			for(Enchere e : listeEncheres) {	
				System.out.println("sevlet enchere montant : " + e.getMontantEnchere()+", nom user : " + e.getUtilisateur().getNom());
				montant = e.getMontantEnchere();
				if(montant > montantMax) {
					montantMax = montant;
					meilleureEnchere = e;
				}
			}
			Timestamp ts = article.getFinEncheres();
	        String strDate = MethodesUtiles.getStringDateFromTimestamp(ts);
	        request.setAttribute("listeEncheres", listeEncheres);
	        request.setAttribute("meilleureEnchere",  meilleureEnchere);
			request.setAttribute("article", article);
			request.setAttribute("date_fin", strDate);
			request.setAttribute("utilisateur", utilisateur);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/enchereTerminee.jsp");
			rd.forward(request, response);
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());	
			RequestDispatcher rd = request.getRequestDispatcher("/ServletAfficherVente");
			rd.forward(request, response);	
			
		}
		
		

	}

}
