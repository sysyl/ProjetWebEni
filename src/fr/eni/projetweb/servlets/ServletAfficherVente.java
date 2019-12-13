package fr.eni.projetweb.servlets;
//2
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
import fr.eni.projetweb.bll.MethodesUtiles;
import fr.eni.projetweb.bll.UtilisateursManager;
import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Servlet implementation class ServletAfficherVente
 */
@WebServlet("/ServletAfficherVente")
public class ServletAfficherVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAfficherVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("afficher vente");
		List<Integer> listeCodesErreur = null;
		String idArticleString = null;
		int idArticle = 0;
		if(request.getParameter("id") != null) {
			idArticleString = request.getParameter("id");
			idArticle = Integer.valueOf(idArticleString);	
		}
		else {
			if(request.getAttribute("id") != null) {
				try {
					idArticle = (int) request.getAttribute("id"); // pour passer par les modifcations vente
				}catch(ClassCastException e) {
					idArticleString = (String) request.getAttribute("id"); // pour passer de la servlet encherir si erreur
					idArticle = Integer.valueOf(idArticleString);
				}				
			}
			
			if(request.getAttribute("listeCodesErreur") != null) {
				listeCodesErreur =  (List<Integer>) request.getAttribute("listeCodesErreur");
				request.setAttribute("listeCodesErreur", listeCodesErreur);	
			}		
		}
		
		int idUtilisateur = 0;
		if(request.getSession().getAttribute("idUtilisateur") != null ) {
			idUtilisateur = (int)request.getSession().getAttribute("idUtilisateur");	
		}
		
		UtilisateursManager utilisateurManager = UtilisateursManager.getInstance();
		Utilisateur utilisateur = utilisateurManager.afficherUtilisateur(idUtilisateur);
		
		ArticleManager articleManager = ArticleManager.getInstance();
		try {
			Article article = articleManager.getArticleById(idArticle);
			
			Timestamp ts = article.getFinEncheres();
            String strDate = MethodesUtiles.getStringDateFromTimestamp(ts);
          
			request.setAttribute("article", article);
			request.setAttribute("date_fin", strDate);
			request.setAttribute("utilisateur", utilisateur);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailVente.jsp");
			rd.forward(request, response);
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			
			if(request.getSession().getAttribute("idUtilisateur") != null ) {
				idUtilisateur = (int)request.getSession().getAttribute("idUtilisateur");	
				RequestDispatcher rd = request.getRequestDispatcher("/ServletAccueilConnecte");
				rd.forward(request, response);
			}
			else {			
				RequestDispatcher rd = request.getRequestDispatcher("/ServletAccueil");
				rd.forward(request, response);	
			}
		
			
			
		}
		

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
