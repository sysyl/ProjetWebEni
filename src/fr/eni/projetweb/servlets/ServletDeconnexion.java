package fr.eni.projetweb.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletDeconnexion
 */
@WebServlet("/ServletDeconnexion")
public class ServletDeconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletDeconnexion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		try {
			session.removeAttribute("idUtilisateur");
			session.removeAttribute("pseudo");
			session.removeAttribute("listeCategorie");
			session.removeAttribute("listeArticle");
			session.removeAttribute("listeArticlePasConnecte");
			session.removeAttribute("listeArticleConnecte");
			session.removeAttribute("listePseudo");
			session.invalidate();
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			cookie.setMaxAge(0);
			cookie.setValue(null);
			cookie.setPath(request.getRequestURI());
			response.addCookie(cookie);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/ServletAccueil");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
