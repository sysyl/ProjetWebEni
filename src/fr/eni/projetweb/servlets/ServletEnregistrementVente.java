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
import fr.eni.projetweb.bll.ArticleManager;
import fr.eni.projetweb.bo.Categorie;
import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Servlet implementation class ServletVente
 */
@WebServlet("/ServletEnregistrementVente")
public class ServletEnregistrementVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UtilisateursManager utilisateurManager;
	private CategorieManager managerCategorie;
	private ArticleManager managerVente;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEnregistrementVente() {
        super();
        managerCategorie = CategorieManager.getInstance();
        utilisateurManager = UtilisateursManager.getInstance();
        managerVente = ArticleManager.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String nomArticle = request.getParameter("nom_article").trim();
		String description = request.getParameter("description").trim();
		int categorieId = Integer.valueOf(request.getParameter("categorie").trim());
		String prix = request.getParameter("mise_prix").trim();
		String dateDebut = request.getParameter("date_debut").trim();
		String dateFin = request.getParameter("date_fin").trim();
		String rue = request.getParameter("rue").trim();
		String codePostal = request.getParameter("code_postal").trim();
		String ville = request.getParameter("ville").trim();
		String lienImage = request.getParameter("lien_image").trim();
		
		int idUtilisateur = (int) request.getSession().getAttribute("idUtilisateur"); 
		Utilisateur utilisateur = utilisateurManager.afficherUtilisateur(idUtilisateur);
		Categorie categorie = managerCategorie.getCategorie(categorieId);
	
		try {
			managerVente.ajouterVente(nomArticle, description, prix, dateDebut, dateFin, rue, codePostal, ville, utilisateur, categorie, lienImage);
			response.sendRedirect("ServletAccueilConnecte");
		} catch (BusinessException e) {
			List<Categorie> listCategories = managerCategorie.getAllCategories();
			request.setAttribute("listCategories", listCategories);
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			request.setAttribute("utilisateur", utilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
			rd.forward(request, response);	
		}
	}
}
