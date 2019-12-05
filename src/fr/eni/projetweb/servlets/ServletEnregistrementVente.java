package fr.eni.projetweb.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetweb.bll.CategorieManager;
import fr.eni.projetweb.bll.UtilisateursManager;
import fr.eni.projetweb.bll.VenteManager;
import fr.eni.projetweb.bo.Categorie;
import fr.eni.projetweb.bo.Utilisateur;

/**
 * Servlet implementation class ServletVente
 */
@WebServlet("/ServletEnregistrementVente")
public class ServletEnregistrementVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UtilisateursManager utilisateurManager;
	private CategorieManager managerCategorie;
	private VenteManager managerVente;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEnregistrementVente() {
        super();
        managerCategorie = new CategorieManager();
        utilisateurManager = new UtilisateursManager();
        managerVente = new VenteManager();
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
		
		String nomArticle = request.getParameter("nom_article");
		String description = request.getParameter("description");
		int categorieId = Integer.valueOf(request.getParameter("categorie").trim());
		String prix = request.getParameter("mise_prix");
		String dateDebut = request.getParameter("date_debut");
		String dateFin = request.getParameter("date_fin");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		
		
		int idUtilisateur = (int) request.getSession().getAttribute("idUtilisateur"); //TODO : int idUtilisateur -> nom param?
		Utilisateur utilisateur = utilisateurManager.afficherUtilisateur(idUtilisateur);
		Categorie categorie = managerCategorie.getCategorie(categorieId);
	
//		System.out.println("article : " + nomArticle);
//		System.out.println("description : " + description);
//		System.out.println("prix : " + prix);
//		System.out.println("dateDebut : " + dateDebut);
//		System.out.println("dateFin : " + dateFin);
//		System.out.println("rue : " + rue);
//		System.out.println("codePostal : " + codePostal);
//		System.out.println("ville : " + ville);	
		
		managerVente.ajouterVente(nomArticle, description, prix, dateDebut, dateFin, rue, codePostal, ville, utilisateur, categorie);
		
		RequestDispatcher rd = request.getRequestDispatcher("/ServletAccueilConnecte");
		rd.forward(request, response);
		
	}

}
