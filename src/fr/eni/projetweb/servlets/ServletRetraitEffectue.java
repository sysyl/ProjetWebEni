package fr.eni.projetweb.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetweb.bll.MethodesUtiles;
import fr.eni.projetweb.bll.RetraitManager;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Servlet implementation class ServletRetraitEffectue
 */
@WebServlet("/ServletRetraitEffectue")
public class ServletRetraitEffectue extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private RetraitManager retraitManager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRetraitEffectue() {
        super();
        retraitManager = RetraitManager.getInstance();
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

		String noArticleRetraitEffectue = request.getParameter("noArticle");
		String noVendeurStr = request.getParameter("noUtilisateur");
		String prixVenteStr = request.getParameter("prixVente");
		System.out.println(noArticleRetraitEffectue);
		System.out.println(noVendeurStr);
		System.out.println(prixVenteStr);
		int noVendeur = MethodesUtiles.parseStringToInt(noVendeurStr);
		int prixVente = MethodesUtiles.parseStringToInt(prixVenteStr);
		int noArticle = MethodesUtiles.parseStringToInt(noArticleRetraitEffectue);
		try {
			retraitManager.changerStatutRetrait(noArticle, noVendeur, prixVente);			
		} catch (BusinessException e) {
			//Erreur lors du changement de statut de retrait à : article retiré. Votre compte n'a pas pu être crédité
			//e.printStackTrace();
		}finally {
			request.setAttribute("noArticle", noArticle);

			RequestDispatcher rd = request.getRequestDispatcher("/ServletEnchereTerminee");
			rd.forward(request, response);
		}
		
		
		
	}

}
