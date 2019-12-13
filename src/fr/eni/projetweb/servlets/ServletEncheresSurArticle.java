package fr.eni.projetweb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetweb.bll.ArticleManager;
import fr.eni.projetweb.bll.EnchereManager;
import fr.eni.projetweb.bll.MethodesUtiles;
import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.bo.Enchere;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Servlet implementation class ServletEncheresSurArticle
 */
@WebServlet("/ServletEncheresSurArticle")
public class ServletEncheresSurArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnchereManager enchereManager;
	private ArticleManager articleManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEncheresSurArticle() {
        super();
        enchereManager = EnchereManager.getInstance();
        articleManager = ArticleManager.getInstance();
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
			String noArticleString = request.getParameter("noArticle");
			int noArticle = 0;
			
			try {
				noArticle = MethodesUtiles.parseStringToInt(noArticleString);
				List<Enchere> listeEncheres = enchereManager.getEncheresByIdArticle(noArticle);
				Article article = articleManager.getArticleById(noArticle);
				request.setAttribute("listeEncheres", listeEncheres);
				request.setAttribute("article", article);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/historiqueEncheresVente.jsp");
				rd.forward(request, response);
				
			} catch (BusinessException e) {
				// TODO
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				request.setAttribute("noArticle", noArticle);
				RequestDispatcher rd = request.getRequestDispatcher("/ServletEnchereTerminee");
				rd.forward(request, response);
			}
			
	}

}
