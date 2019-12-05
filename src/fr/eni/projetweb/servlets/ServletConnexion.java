package fr.eni.projetweb.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetweb.bll.MethodesUtiles;
import fr.eni.projetweb.bll.UtilisateursManager;
import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.dal.DAOFactory;
import fr.eni.projetweb.dal.UtilisateurDAO;
import fr.eni.projetweb.dal.jdbc.UtilisateurDAOJdbcImpl;

/**
 * Servlet implementation class ServletListe
 */
@WebServlet("/connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletConnexion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageConnexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Utilisateur user = new Utilisateur();
			UtilisateurDAO userJDBC = DAOFactory.getUtilisateurDAO();
			//	UtilisateurDAOJdbcImpl userJDBC = new UtilisateurDAOJdbcImpl();

			UtilisateursManager manager = new UtilisateursManager();
			String mdp = request.getParameter("mot_de_passe");
			String mdpHashe = MethodesUtiles.hasherMotDePasse(mdp);

			user.setPseudo(request.getParameter("pseudo"));
			user.setMot_de_passe(mdpHashe);
			
			user = userJDBC.login(user);

			if (user.isValid()) {

				HttpSession session = request.getSession(true);
				session.setAttribute("idUtilisateur", user.getNoUtilisateur());
				session.setAttribute("pseudo", user.getPseudo());
				
				System.out.println("Connexion r�ussie");
				response.sendRedirect("/ProjetWebENI/ServletAccueilConnecte");

			}

			else {
				System.out.println("Echec connexion");
				response.sendRedirect("/WEB-INF/connexion");
			}
		}

		catch (Throwable theException) {
			theException.printStackTrace();
			System.out.println(theException);
		}

	}

}
