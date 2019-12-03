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

import fr.eni.projetweb.bll.UtilisateursManager;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Servlet implementation class ServletCreationCompte
 */
@WebServlet("/ServletCreationCompte")
public class ServletCreationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCreationCompte() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("doGet -- creation compte/annuler");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/creationCompte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Integer> listeCodesErreur=new ArrayList<>();
			
		
		
		//System.out.println("doPost -- form sumit");
			String pseudo = request.getParameter("pseudo").trim();
			String nom = request.getParameter("nom").trim();
			String prenom = request.getParameter("prenom").trim();
			String email = request.getParameter("email").trim();
			String telephone = request.getParameter("telephone").trim();
			String rue = request.getParameter("rue").trim();
			String codePostal = request.getParameter("codepostal").trim();
			String ville = request.getParameter("ville").trim();
			String motDePasse = request.getParameter("password").trim();
			String confirmation = request.getParameter("confirmation").trim();
			
			System.out.println("pseudo " + pseudo);
			System.out.println("nom " + nom);
			System.out.println("email " + email);
			System.out.println("codePostal " + codePostal);
			UtilisateursManager manager = new UtilisateursManager();
			try {
				manager.ajouterUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, confirmation);
			} catch (BusinessException e) {
				System.out.println("erreur");
				for(int l : listeCodesErreur) {
					System.out.println(l);
				}
				System.out.println(e.getListeCodesErreur());
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/creationCompte.jsp");
				rd.forward(request, response);
				//e.printStackTrace();
			}
		
		
			
	}

}
