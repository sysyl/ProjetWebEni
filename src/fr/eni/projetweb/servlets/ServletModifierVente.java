package fr.eni.projetweb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetweb.bll.ArticleManager;
import fr.eni.projetweb.bll.CategorieManager;
import fr.eni.projetweb.bll.MethodesUtiles;
import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.bo.Categorie;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Servlet implementation class ServletModifierVente
 */
@WebServlet("/modifierVente")
public class ServletModifierVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategorieManager categoriesManager;
	private ArticleManager articleManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModifierVente() {
        super();
        categoriesManager = CategorieManager.getInstance();
    	articleManager = ArticleManager.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// si clic sur le bouton 'modifier' une vente
		List<Integer> listeCodesErreur=new ArrayList<>();	
		System.out.println("MODIFIER VENTE");
		String idArticleStr = null;
		int idArticle = 0;
		ServletContext context=getServletContext();  
		if(request.getParameter("idArticle") != null) {
			idArticleStr = request.getParameter("idArticle");
			idArticle = Integer.valueOf(idArticleStr);
		}
		else {
			if(request.getAttribute("idArticle") != null) {
				try {
					idArticle = (int) request.getAttribute("idArticle"); 
				}catch(ClassCastException e) {
					idArticleStr = (String) request.getAttribute("idArticle"); 
					idArticle = Integer.valueOf(idArticleStr);
				}	
			}
			else if(context.getAttribute("idArticle") != null) {
				idArticle=(int)context.getAttribute("idArticle");  
				context.removeAttribute("idArticle");
				System.out.println("jai recupere du conxtext " + idArticle);
			}
			
			if(request.getAttribute("listeCodesErreur") != null) {
				listeCodesErreur = (List<Integer>) request.getAttribute("listeCodesErreur");
				request.setAttribute("listeCodesErreur", listeCodesErreur);	
			}else if(context.getAttribute("listeCodesErreur") != null) {
				listeCodesErreur = (List<Integer>)  context.getAttribute("listeCodesErreur");  
				context.removeAttribute("listeCodesErreur");
				System.out.println("jai recupere du conxtext " + listeCodesErreur.get(0));
			}	
		}

		Article article = null;
		
		try {
			List<Categorie> categories = categoriesManager.getAllCategories();
			article = articleManager.getArticleById(idArticle);
			System.out.println("id article recuprer  " + idArticle + " :  " + article.getNoArticle() + " " + article.getNomArticle());
			String dateDebut = MethodesUtiles.getStringDateFromTimestampForFormInputDefaultValue(article.getDebutEncheres());
			String dateFin = MethodesUtiles.getStringDateFromTimestampForFormInputDefaultValue(article.getFinEncheres());
			request.setAttribute("dateDebut", dateDebut);
			request.setAttribute("dateFin", dateFin);
			request.setAttribute("article", article);
			request.setAttribute("categories", categories);
			request.setAttribute("listeCodesErreur", listeCodesErreur);	
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifierVente.jsp");
			rd.forward(request, response);
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			//TODO
			System.out.println("problemos");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/.jsp");
			rd.forward(request, response);
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
