package fr.eni.projetweb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletVente
 */
@WebServlet("/ServletVente")
public class ServletVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVente() {
        super();
        // TODO Auto-generated constructor stub
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
		String categorie = request.getParameter("categorie");
		String prix = request.getParameter("mise_prix");
		String dateDebut = request.getParameter("date_debut");
		String dateFin = request.getParameter("date_fin");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		
		System.out.println("article : " + nomArticle);
		System.out.println("description : " + description);
		System.out.println("categorie : " + categorie);
		System.out.println("prix : " + prix);
		System.out.println("dateDebut : " + dateDebut);
		System.out.println("dateFin : " + dateFin);
		System.out.println("rue : " + rue);
		System.out.println("codePostal : " + codePostal);
		System.out.println("ville : " + ville);
		
		
		
	}

}
