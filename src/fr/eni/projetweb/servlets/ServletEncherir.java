package fr.eni.projetweb.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.bind.v2.model.core.ID;

import fr.eni.projetweb.bll.ArticleManager;
import fr.eni.projetweb.bll.EnchereManager;
import fr.eni.projetweb.bll.MethodesUtiles;
import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Servlet implementation class ServletEncherir
 */
@WebServlet(urlPatterns = {"/ServletEncherir"}
		)
public class ServletEncherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private EnchereManager enchereManager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEncherir() {
        super();
       enchereManager = EnchereManager.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
					
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getServletPath().equals("/ServletEncherir")) {	
			List<Integer> listeCodesErreur=new ArrayList<>();			
			String valeur = request.getParameter("mise_prix");
			String id = request.getParameter("noArticle");
			Timestamp dateEnchere = new Timestamp(new Date().getTime());
			int idUtilisateur = (int) request.getSession().getAttribute("idUtilisateur"); 
					
			try {
				enchereManager.ajouterEnchere(idUtilisateur, dateEnchere, id, valeur);
				response.sendRedirect("/ProjetWebENI/ServletAccueilConnecte");
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("/ServletAfficherVente");
				request.setAttribute("id", id);
				rd.forward(request, response);
			}
		}
	}

	
}
