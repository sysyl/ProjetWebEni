package fr.eni.projetweb.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetweb.bll.ArticleManager;
import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Servlet implementation class ServletAfficherVente
 */
@WebServlet("/ServletAfficherVente")
public class ServletAfficherVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAfficherVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idArticle = 1; //request.getParameter("nom_param");
		
		ArticleManager articleManager = ArticleManager.getInstance();
		try {
			Article article = articleManager.getArticleById(idArticle);
			
			Timestamp ts = article.getFinEncheres();
            String strDate = articleManager.getStringDateFromTimestamp(ts);
          
			request.setAttribute("article", article);
			request.setAttribute("date_fin", strDate);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailVente.jsp");
			rd.forward(request, response);
		} catch (BusinessException e) {
			// TODO recuperation codes erreur
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
