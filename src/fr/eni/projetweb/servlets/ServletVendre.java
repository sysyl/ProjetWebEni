package fr.eni.projetweb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetweb.bll.ArticleManager;
import fr.eni.projetweb.bll.CategorieManager;
import fr.eni.projetweb.bll.UtilisateursManager;
import fr.eni.projetweb.bo.Categorie;
import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet(urlPatterns = {
		"/ServletVendre",
		"/supprimerVente",
		"/ServletEnregistrementModifications"
})

public class ServletVendre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CategorieManager categoriesManager;
	private UtilisateursManager utilisateurManager;
	private ArticleManager articleManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVendre() {
    	 super();
    	categoriesManager = CategorieManager.getInstance();
    	utilisateurManager = UtilisateursManager.getInstance();
    	articleManager = ArticleManager.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Integer> listeCodesErreur=new ArrayList<>();	
		System.out.println("je pass dans la servlet vendre quand mmeee");
	
		// pour aller a la jsp nouvelle vente
		if(request.getServletPath().equals("/ServletVendre")) {	
			System.out.println("je pass dans la servlet vendre quand mmeee 2");
			
			if(request.getAttribute("listeCodesErreur") != null) {
				listeCodesErreur =  (List<Integer>) request.getAttribute("listeCodesErreur");
				request.setAttribute("listeCodesErreur", listeCodesErreur);	
			}
			
			//je recupere l'utilisateur pour pouvoir afficher 
			//par defaut son adresse en tant qu'adresse de retrait.
			int idUtilisateur = (int) request.getSession().getAttribute("idUtilisateur"); 
			System.out.println("id utilisateur dans la serv vendre " + idUtilisateur);
			Utilisateur utilisateur = utilisateurManager.afficherUtilisateur(idUtilisateur);
			request.setAttribute("utilisateur", utilisateur);
			
			//recuperation de la liste de categories pour la liste deroulante
			List<Categorie> listCategories = categoriesManager.getAllCategories();
			request.setAttribute("listCategories", listCategories);
				
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
			rd.forward(request, response);
		
		}
		else if(request.getServletPath().equals("/supprimerVente")) {
			String idArticleStr = request.getParameter("idArticle");
			int idArticle = Integer.valueOf(idArticleStr);
			System.out.println(idArticle + " sera supprime");
			try {
				articleManager.supprimerVente(idArticle);
				response.sendRedirect("/ProjetWebENI/ServletAccueilConnecte");
			} catch (BusinessException e) { 
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				response.sendRedirect("/ProjetWebENI/modifierVente");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/ServletEnregistrementModifications")) {	
			
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
			
		// traitement des modifications rentrees dans le formulaire par l'utilisateur
		
		List<Integer> listeCodesErreur=new ArrayList<>();	
		ArticleManager manager = ArticleManager.getInstance();
		
		String idArticleStr = request.getParameter("idArticle");
		System.out.println("idArticleString de la servlet vendre" + idArticleStr);
		String idRetraitStr = request.getParameter("idRetrait");
	
		String nomArticle = request.getParameter("nom_article").trim();
		String description = request.getParameter("description").trim();
		int categorieId = Integer.valueOf(request.getParameter("categorie").trim());
		String prix = request.getParameter("mise_prix").trim();
		String dateDebut = request.getParameter("date_debut").trim();
		String dateFin = request.getParameter("date_fin").trim();
		String rue = request.getParameter("rue").trim();
		String codePostal = request.getParameter("code_postal").trim();
		String ville = request.getParameter("ville").trim();		
		int idArticle = Integer.valueOf(idArticleStr);
		int idUtilisateur = (int) request.getSession().getAttribute("idUtilisateur"); 
		Utilisateur utilisateur = utilisateurManager.afficherUtilisateur(idUtilisateur);
		try {
			articleManager.modifierVente(idArticle, nomArticle, description, dateDebut, dateFin, prix, rue, categorieId, codePostal, ville, utilisateur, idRetraitStr);
			request.setAttribute("id", idArticle);
			RequestDispatcher rd = request.getRequestDispatcher("/ServletAfficherVente");
			rd.forward(request, response);
			
		} catch (BusinessException e) {
			ServletContext context=getServletContext();  
			context.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			context.setAttribute("idArticle", idArticle);
			response.sendRedirect("/ProjetWebENI/modifierVente");
		}
		
	}
}

}
