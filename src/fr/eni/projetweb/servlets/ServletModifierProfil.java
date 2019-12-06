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
 * Servlet implementation class ServletModifierProfil
 */
@WebServlet(urlPatterns= {
				"/ServletModifierProfil",
				"/supprimer"
})

public class ServletModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModifierProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getServletPath().equals("/ServletModifierProfil"))
		{	
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifierProfil.jsp");
			rd.forward(request, response);
		} 	
		else if(request.getServletPath().equals("/supprimer")){
			System.out.println("je suis dans la servlet supprimer");
			int idUtilisateur = (int) request.getSession().getAttribute("idUtilisateur"); 
			System.out.println("id util " +idUtilisateur);
			UtilisateursManager manager = UtilisateursManager.getInstance();
			try {
				manager.supprimerUtilisateur(idUtilisateur);
				response.sendRedirect("/ProjetWebENI/ServletAccueil");
//				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ServletProfil");
//				rd.forward(request, response);
			//	ServletProfil
			} catch (BusinessException e) {
//				System.out.println("erreur");
//				for(int l : listeCodesErreur) {
//					System.out.println(l);
//				}
				System.out.println(e.getListeCodesErreur());
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				response.sendRedirect("/ProjetWebENI/ServletModifierProfil");
//				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ServletModifierProfil");
//				rd.forward(request, response);
			}
			
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Integer> listeCodesErreur=new ArrayList<>();	
		String pseudo = request.getParameter("pseudo").trim();
		String nom = request.getParameter("nom").trim();
		String prenom = request.getParameter("prenom").trim();
		String email = request.getParameter("email").trim();
		String telephone = request.getParameter("telephone").trim();
		String rue = request.getParameter("rue").trim();
		String codePostalS = request.getParameter("codepostal").trim();
		String ville = request.getParameter("ville").trim();
		String motDePasseActuel = request.getParameter("currentpassword").trim();
		String motDePasse = request.getParameter("password").trim();
		String confirmation = request.getParameter("confirmation").trim();
		int idUtilisateur = (int) request.getSession().getAttribute("idUtilisateur"); 
		UtilisateursManager manager = UtilisateursManager.getInstance();
		
		System.out.println("id utlisatuer passe " + idUtilisateur);
		try {
			manager.modifierUtilisateur(idUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostalS, ville, motDePasseActuel, motDePasse, confirmation);
	
//			RequestDispatcher rd = request.getRequestDispatcher("/ProjetWebENI/ServletProfil");
//			rd.forward(request, response);
			response.sendRedirect("/ProjetWebENI/ServletProfil");

			
		} catch (BusinessException e) {
			System.out.println("erreur");
			for(int l : listeCodesErreur) {
				System.out.println(l);
			}
			System.out.println(e.getListeCodesErreur());
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifierProfil.jsp");
			rd.forward(request, response);
			//e.printStackTrace();
		}
	
		
	}

}
