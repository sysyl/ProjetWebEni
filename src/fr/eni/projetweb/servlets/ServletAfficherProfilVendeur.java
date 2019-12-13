package fr.eni.projetweb.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetweb.bll.UtilisateursManager;
import fr.eni.projetweb.bo.Utilisateur;

/**
 * Servlet implementation class ServletAfficherProfilVendeur
 */
@WebServlet("/ServletAfficherProfilVendeur")
public class ServletAfficherProfilVendeur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAfficherProfilVendeur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		int idUtilisateur = Integer.valueOf(id);
		Utilisateur user = new Utilisateur();
		UtilisateursManager UserManager = UtilisateursManager.getInstance();
		
		user = UserManager.afficherUtilisateur(idUtilisateur);
		
		request.setAttribute("user", user);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/afficherProfilVendeur.jsp");
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
