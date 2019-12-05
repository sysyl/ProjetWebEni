package fr.eni.projetweb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetweb.bll.CategorieManager;
import fr.eni.projetweb.bll.UtilisateursManager;
import fr.eni.projetweb.bo.Categorie;
import fr.eni.projetweb.bo.Utilisateur;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet("/ServletVendre")
public class ServletVendre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CategorieManager categoriesManager;
	private UtilisateursManager utilisateurManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVendre() {
    	 super();
    	categoriesManager = new CategorieManager();
    	utilisateurManager = new UtilisateursManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//je recupere l'utilisateur pour pouvoir afficher 
		//par defaut son adresse en tant qu'adresse de retrait.
		int idUtilisateur = (int) request.getSession().getAttribute("idUtilisateur"); //TODO
		Utilisateur utlisateur = utilisateurManager.afficherUtilisateur(idUtilisateur);
		request.setAttribute("utilisateur", utlisateur);
		
		//recuperation de la liste de categories pour la liste deroulante
		List<Categorie> listCategories = categoriesManager.getAllCategories();
		request.setAttribute("listCategories", listCategories);
			
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
