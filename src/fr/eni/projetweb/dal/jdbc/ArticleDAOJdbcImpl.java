/**
 * 
 */
package fr.eni.projetweb.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetweb.bo.Article;
import fr.eni.projetweb.bo.Categorie;
import fr.eni.projetweb.bo.Retrait;
import fr.eni.projetweb.bo.Utilisateur;
import fr.eni.projetweb.dal.ArticleDAO;
import fr.eni.projetweb.dal.CategorieDAO;
import fr.eni.projetweb.dal.CodesResultatDAL;
import fr.eni.projetweb.dal.DAOFactory;
import fr.eni.projetweb.dal.RetraitDAO;
import fr.eni.projetweb.dal.UtilisateurDAO;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Classe en charge de
 * 
 * @version ProjetWebENI - V1.0
 * @author slanger2019
 * @date 5 d�c. 2019 - 14:37:15
 *
 */
public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS ORDER BY fin_encheres ASC";
	public static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article, description_article, "
			+ "debut_encheres, fin_encheres, prix_initial, prix_vente, no_utilisateur, "
			+ "no_categorie, no_retrait, chemin_img) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String SELECT_ARTICLE_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article=?";
	public static final String INSERT_RETRAIT = "INSERT INTO RETRAITS(rue, code_postal, ville, statut) VALUES(?, ?, ?, ?);";
	public static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?";
	public static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET nom_article=?, description_article=?, debut_encheres=?, "
			+ "	fin_encheres=?, prix_initial=?, prix_vente=?, "
			+ " no_categorie=?, no_retrait=?, chemin_img=? WHERE no_article=?";
	public static final String UPDATE_PRIX_VENTE_ARTICLE = "UPDATE ARTICLES_VENDUS SET prix_vente=? WHERE no_article=?";
	public static final String SELECT_ARTICLES_BY_USER_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur=?";

	@Override
	public List<Article> selectArticle(Article article) throws BusinessException {
		List<Article> listeArticle = new ArrayList<Article>();
		Utilisateur user = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getInt("prix_vente"), rs.getTimestamp("fin_encheres"), rs.getInt("no_utilisateur")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
			throw businessException;
		}
		return listeArticle;
	}

	@Override
	public List<Article> selectAllArticle() throws BusinessException {
		List<Article> listeArticle = new ArrayList<>();
		CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_ALL);) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
						rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
						utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
						categorieDAO.selectCategorie(rs.getInt("no_categorie")),
						retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));
			}
		} catch (Exception e) {
			throw new BusinessException();
		}
		return listeArticle;
	}

	@Override
	public Article selectArticleById(int numeroArticle) throws BusinessException {
		Article article = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE_BY_ID);
			pstmt.setInt(1, numeroArticle);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				int noArticle = rs.getInt("no_article");
				String nomArticle = rs.getString("nom_article");
				String description = rs.getString("description_article");
				int prixInitial = rs.getInt("prix_initial");
				int prixVente = rs.getInt("prix_vente");
				int noUtilisateur = rs.getInt("no_utilisateur");
				int noCategorie = rs.getInt("no_categorie");
				int noRetrait = rs.getInt("no_retrait");
				Timestamp debutEnch = rs.getTimestamp("debut_encheres");
				Timestamp finEnch = rs.getTimestamp("fin_encheres");
				String cheminImg = rs.getString("chemin_img");

				UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
				Utilisateur utilisateur = utilisateurDAO.selectUserById(noUtilisateur);

				RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
				Retrait retrait = retraitDAO.selectRetraitById(noRetrait);

				CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
				Categorie categorie = categorieDAO.selectCategorie(noCategorie);

				article = new Article(noArticle, nomArticle, description, debutEnch, finEnch, prixInitial, prixVente,
						utilisateur, categorie, retrait, cheminImg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.REGLE_ARTICLE_ERREUR);
			throw businessException;
		}
		return article;
	}

	@Override
	public void insertArticle(Article article) throws BusinessException {
		if (article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;

				// insertion retrait avant article pour recupere sa cle primaire
				pstmt = cnx.prepareStatement(INSERT_RETRAIT, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, article.getRetrait().getRue());
				pstmt.setInt(2, article.getRetrait().getCodePostal());
				pstmt.setString(3, article.getRetrait().getVille());
				pstmt.setBoolean(4, false);

				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					article.getRetrait().setIdRetrait(rs.getInt(1));
				}
				rs.close();
				pstmt.close();

				pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, article.getNomArticle());
				pstmt.setString(2, article.getDescriptionArticle());

				pstmt.setTimestamp(3, article.getDebutEncheres());
				pstmt.setTimestamp(4, article.getFinEncheres());

				pstmt.setInt(5, article.getPrixInitial());
				pstmt.setInt(6, article.getPrixVente());
				pstmt.setInt(7, article.getUtilisateur().getNoUtilisateur());
				pstmt.setInt(8, article.getCategorie().getNoCategorie());
				pstmt.setInt(9, article.getRetrait().getIdRetrait());

				if (article.getPathImg() == null || article.getPathImg().trim().length() == 0)
					pstmt.setNull(10, Types.VARCHAR);
				else
					pstmt.setString(10, article.getPathImg().trim());

				pstmt.executeUpdate();

				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					article.setNoArticle(rs.getInt(1));
				}
				rs.close();
				pstmt.close();

				cnx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			if (e.getMessage().contains("datetime2")) {
				businessException.ajouterErreur(CodesResultatDAL.DATE_BDD_ERREUR);
				throw businessException;
			} else if (e.getMessage().contains("Les données de chaîne ou binaires seront tronquées")) {
				businessException.ajouterErreur(CodesResultatDAL.DEPASSEMENT_VARCHAR_LIMIT);
				throw businessException;
			}
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}

	@Override
	public void deleteArticleById(int idArticle) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				System.out.println("je usi dans le delete article by id");
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);
				pstmt.setInt(1, idArticle);
				pstmt.executeUpdate();
				cnx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_ARTICLE_ECHEC);
			throw businessException;
		}

	}

	@Override
	public void updateArticleById(Article article) throws BusinessException {
		if (article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ARTICLE);

				Retrait retrait = new Retrait(article.getRetrait().getIdRetrait(), article.getRetrait().getRue(),
						article.getRetrait().getCodePostal(), article.getRetrait().getVille(), false);

				RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
				retraitDAO.updateRetrait(retrait);

				pstmt.setString(1, article.getNomArticle());
				pstmt.setString(2, article.getDescriptionArticle());
				pstmt.setTimestamp(3, article.getDebutEncheres());
				pstmt.setTimestamp(4, article.getFinEncheres());
				pstmt.setInt(5, article.getPrixInitial());
				pstmt.setInt(6, article.getPrixVente());
				pstmt.setInt(7, article.getCategorie().getNoCategorie());
				pstmt.setInt(8, article.getRetrait().getIdRetrait());
				pstmt.setString(9, article.getPathImg());
				pstmt.setInt(10, article.getNoArticle());
				pstmt.executeUpdate();
				cnx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_ERREUR);
			throw businessException;
		}

	}

	@Override
	public List<Article> SelectArticleByTimeStamp(Timestamp date) throws BusinessException {
		List<Article> listeArticle = new ArrayList<Article>();
		CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(
					"SELECT * FROM ARTICLES_VENDUS WHERE (debut_encheres <= ?) AND (fin_encheres >= ?)");
			pstmt.setTimestamp(1, date);
			pstmt.setTimestamp(2, date);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
						rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
						utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
						categorieDAO.selectCategorie(rs.getInt("no_categorie")),
						retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		return listeArticle;
	}

	public List<Article> SelectArticleFilter(Timestamp dateDebut, Timestamp dateFin, String contenu,
			Categorie categorie) throws BusinessException {

		List<Article> listeArticle = new ArrayList<Article>();
		CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			if (dateDebut != null && dateFin != null && categorie != null) {// WHERE sur tous les
																			// filtres
				PreparedStatement pstmt = cnx.prepareStatement(
						"SELECT * FROM ARTICLES_VENDUS WHERE (debut_encheres < ?) AND (fin_encheres > ?) AND (no_categorie = ?) AND (nom_article LIKE ?) ORDER BY fin_encheres ASC");
				pstmt.setTimestamp(1, dateDebut);
				pstmt.setTimestamp(2, dateFin);
				pstmt.setInt(3, categorie.getNoCategorie());
				pstmt.setString(4, "%" + contenu + "%");
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
							rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
							categorieDAO.selectCategorie(rs.getInt("no_categorie")),
							retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));

				}
			} else if (dateDebut == null && dateFin == null && categorie == null) {// Aucun filtres
				PreparedStatement pstmt = cnx
						.prepareStatement("SELECT * FROM ARTICLES_VENDUS ORDER BY fin_encheres ASC");
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
							rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
							categorieDAO.selectCategorie(rs.getInt("no_categorie")),
							retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));
				}
			} else if (dateDebut == null && dateFin == null) { // Si dates null, filtre que sur
																// categorie et nom
				PreparedStatement pstmt = cnx.prepareStatement(
						"SELECT * FROM ARTICLES_VENDUS WHERE (no_categorie = ?) AND (nom_article LIKE ?) ORDER BY fin_encheres ASC");
				pstmt.setInt(1, categorie.getNoCategorie());
				pstmt.setString(2, "%" + contenu + "%");
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
							rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
							categorieDAO.selectCategorie(rs.getInt("no_categorie")),
							retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));

				}

			} else { // Si categorie null, filtre dates et nom
				PreparedStatement pstmt = cnx.prepareStatement(
						"SELECT * FROM ARTICLES_VENDUS WHERE (debut_encheres < ?) AND (fin_encheres > ?) AND (nom_article LIKE ?) ORDER BY fin_encheres ASC");
				pstmt.setTimestamp(1, dateDebut);
				pstmt.setTimestamp(2, dateFin);
				pstmt.setString(3, "%" + contenu + "%");
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
							rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
							categorieDAO.selectCategorie(rs.getInt("no_categorie")),
							retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		return listeArticle;
	}

	public List<Article> SelectArticleFilterAllCategory(Timestamp dateDebut, Timestamp dateFin, String contenu)
			throws BusinessException {

		List<Article> listeArticle = new ArrayList<Article>();
		CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			if (dateDebut != null && dateFin != null) {

				PreparedStatement pstmt = cnx.prepareStatement(
						"SELECT * FROM ARTICLES_VENDUS WHERE (debut_encheres < ?) AND (fin_encheres > ?) AND (nom_article LIKE ?) ORDER BY fin_encheres ASC");
				pstmt.setTimestamp(1, dateDebut);
				pstmt.setTimestamp(2, dateFin);
				pstmt.setString(3, "%" + contenu + "%");
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
							rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
							categorieDAO.selectCategorie(rs.getInt("no_categorie")),
							retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));
				}
			} else {
				PreparedStatement pstmt = cnx.prepareStatement(
						"SELECT * FROM ARTICLES_VENDUS WHERE (nom_article LIKE ?) ORDER BY fin_encheres ASC");
				pstmt.setString(1, "%" + contenu + "%");
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
							rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
							categorieDAO.selectCategorie(rs.getInt("no_categorie")),
							retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		return listeArticle;
	}

	@Override
	public List<Article> getArticleFilterBuy(Utilisateur utilisateur, String contenu, Categorie categorie,
			boolean encheresOuvertes, boolean mesEnchereEnCours, boolean mesEncheresRemportees)
			throws BusinessException {
		List<Article> listeArticle = new ArrayList<Article>();
		Timestamp dateDuJour = Timestamp.valueOf(LocalDateTime.now());
		System.out.println(dateDuJour);

		CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();

		if (!encheresOuvertes && !mesEnchereEnCours && !mesEncheresRemportees) {
			System.out.println("ArticleAchatsJDBC : rien de coch� ");
			return listeArticle;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			if (encheresOuvertes) {
				PreparedStatement pstmt = cnx.prepareStatement(
						"SELECT * FROM ARTICLES_VENDUS WHERE (no_utilisateur <> ?) AND (nom_article LIKE ?) AND "
								+ "(debut_encheres < ?) AND (fin_encheres > ?) AND (no_categorie = ?) ORDER BY fin_encheres ASC");
				pstmt.setInt(1, utilisateur.getNoUtilisateur());
				pstmt.setString(2, "%" + contenu + "%");
				pstmt.setTimestamp(3, dateDuJour);
				pstmt.setTimestamp(4, dateDuJour);
				pstmt.setInt(5, categorie.getNoCategorie());
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
							rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
							categorieDAO.selectCategorie(rs.getInt("no_categorie")),
							retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));
				}
			}

			if (mesEnchereEnCours && !encheresOuvertes) {
				PreparedStatement pstmt = cnx.prepareStatement(
						"SELECT * FROM ARTICLES_VENDUS INNER JOIN ENCHERES ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
								+ "WHERE (ARTICLES_VENDUS.nom_article LIKE ?) "
								+ "AND (ARTICLES_VENDUS.debut_encheres < ?) AND (ARTICLES_VENDUS.fin_encheres > ?) "
								+ "AND (ENCHERES.no_utilisateur = ?) AND (ARTICLES_VENDUS.no_categorie = ?) ORDER BY fin_encheres ASC");
				pstmt.setString(1, "%" + contenu + "%");
				pstmt.setTimestamp(2, dateDuJour);
				pstmt.setTimestamp(3, dateDuJour);
				pstmt.setInt(4, utilisateur.getNoUtilisateur());
				pstmt.setInt(5, categorie.getNoCategorie());

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
							rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
							categorieDAO.selectCategorie(rs.getInt("no_categorie")),
							retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));
				}
			}

			if (mesEncheresRemportees) {
				PreparedStatement pstmt = cnx.prepareStatement(
						"SELECT * FROM ARTICLES_VENDUS INNER JOIN ENCHERES ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
								+ "WHERE (ARTICLES_VENDUS.nom_article LIKE ?) "
								+ "AND (ARTICLES_VENDUS.fin_encheres < ?) " + "AND (ENCHERES.no_utilisateur = ?)"
								+ "AND (ENCHERES.montant_enchere = ARTICLES_VENDUS.prix_vente) AND (ARTICLES_VENDUS.no_categorie = ?) ORDER BY fin_encheres ASC");

				pstmt.setString(1, "%" + contenu + "%");
				pstmt.setTimestamp(2, dateDuJour);
				pstmt.setInt(3, utilisateur.getNoUtilisateur());
				pstmt.setInt(4, categorie.getNoCategorie());

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
							rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
							categorieDAO.selectCategorie(rs.getInt("no_categorie")),
							retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		}

		return listeArticle;
	}

	@Override
	public List<Article> getArticleFilterBuyAllCategory(Utilisateur utilisateur, String contenu,
			boolean encheresOuvertes, boolean mesEnchereEnCours, boolean mesEncheresRemportees)
			throws BusinessException {
		List<Article> listeArticle = new ArrayList<Article>();
		Timestamp dateDuJour = Timestamp.valueOf(LocalDateTime.now());
		System.out.println(dateDuJour);

		CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();

		// si aucune case de coch�e, on retourne une liste vide
		if (!encheresOuvertes && !mesEnchereEnCours && !mesEncheresRemportees) {
			System.out.println("ArticleAchatsJDBC, toutes cat�gories : rien de coch� ");
			return listeArticle;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			if (encheresOuvertes) {
				PreparedStatement pstmt = cnx.prepareStatement(
						"SELECT * FROM ARTICLES_VENDUS WHERE (no_utilisateur <> ?) AND (nom_article LIKE ?) AND "
								+ "(debut_encheres < ?) AND (fin_encheres > ?) ORDER BY fin_encheres ASC");
				pstmt.setInt(1, utilisateur.getNoUtilisateur());
				pstmt.setString(2, "%" + contenu + "%");
				pstmt.setTimestamp(3, dateDuJour);
				pstmt.setTimestamp(4, dateDuJour);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
							rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
							categorieDAO.selectCategorie(rs.getInt("no_categorie")),
							retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));
				}
			}

			if (mesEnchereEnCours && !encheresOuvertes) {
				PreparedStatement pstmt = cnx.prepareStatement(
						"SELECT * FROM ARTICLES_VENDUS INNER JOIN ENCHERES ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
								+ "WHERE (ARTICLES_VENDUS.nom_article LIKE ?) "
								+ "AND (ARTICLES_VENDUS.debut_encheres < ?) AND (ARTICLES_VENDUS.fin_encheres > ?) "
								+ "AND (ENCHERES.no_utilisateur = ?) ORDER BY fin_encheres ASC");
				pstmt.setString(1, "%" + contenu + "%");
				pstmt.setTimestamp(2, dateDuJour);
				pstmt.setTimestamp(3, dateDuJour);
				pstmt.setInt(4, utilisateur.getNoUtilisateur());

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
							rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
							categorieDAO.selectCategorie(rs.getInt("no_categorie")),
							retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));
				}
			}

			if (mesEncheresRemportees) {
				PreparedStatement pstmt = cnx.prepareStatement(
						"SELECT * FROM ARTICLES_VENDUS INNER JOIN ENCHERES ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
								+ "WHERE (ARTICLES_VENDUS.nom_article LIKE ?) "
								+ "AND (ARTICLES_VENDUS.fin_encheres < ?) " + "AND (ENCHERES.no_utilisateur = ?)"
								+ "AND (ENCHERES.montant_enchere = ARTICLES_VENDUS.prix_vente) ORDER BY fin_encheres ASC");

				pstmt.setString(1, "%" + contenu + "%");
				pstmt.setTimestamp(2, dateDuJour);
				pstmt.setInt(3, utilisateur.getNoUtilisateur());

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
							rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
							categorieDAO.selectCategorie(rs.getInt("no_categorie")),
							retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		}

		return listeArticle;
	}

	@Override
	public List<Article> SelectArticleFilterSell(Utilisateur utilisateur, String contenu, Categorie categorie,
			boolean ventesEnCours, boolean ventesNonDebute, boolean ventesTerminees) throws BusinessException {

		List<Article> listeArticle = new ArrayList<Article>();
		Timestamp dateDuJour = Timestamp.valueOf(LocalDateTime.now());
		System.out.println(dateDuJour);

		if (!ventesEnCours && !ventesNonDebute && !ventesTerminees) {
			System.out.println("ArticleVentesJDBC : rien de coch� ");
			return listeArticle;
		}

		CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			if (ventesEnCours) {
				PreparedStatement pstmt = cnx.prepareStatement(
						"SELECT * FROM ARTICLES_VENDUS WHERE (debut_encheres < ?) AND (fin_encheres > ?) AND (no_utilisateur = ?) AND (nom_article LIKE ?) AND (no_categorie = ?) ORDER BY fin_encheres ASC");
				pstmt.setTimestamp(1, dateDuJour);
				pstmt.setTimestamp(2, dateDuJour);
				pstmt.setInt(3, utilisateur.getNoUtilisateur());
				pstmt.setString(4, "%" + contenu + "%");
				pstmt.setInt(5, categorie.getNoCategorie());
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
							rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
							categorieDAO.selectCategorie(rs.getInt("no_categorie")),
							retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));
				}
			}

			if (ventesNonDebute) {
				PreparedStatement pstmt = cnx.prepareStatement(
						"SELECT * FROM ARTICLES_VENDUS WHERE (debut_encheres > ?) AND (no_utilisateur = ?) AND (nom_article LIKE ?) AND (no_categorie = ?) ORDER BY fin_encheres ASC");
				pstmt.setTimestamp(1, dateDuJour);
				pstmt.setInt(2, utilisateur.getNoUtilisateur());
				pstmt.setString(3, "%" + contenu + "%");
				pstmt.setInt(4, categorie.getNoCategorie());
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
							rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
							categorieDAO.selectCategorie(rs.getInt("no_categorie")),
							retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));
				}

			}

			if (ventesTerminees) {

				PreparedStatement pstmt = cnx.prepareStatement(
						"SELECT * FROM ARTICLES_VENDUS WHERE (fin_encheres < ?) AND (no_utilisateur = ?) AND (nom_article LIKE ?) AND (no_categorie = ?) ORDER BY fin_encheres ASC");
				pstmt.setTimestamp(1, dateDuJour);
				pstmt.setInt(2, utilisateur.getNoUtilisateur());
				pstmt.setString(3, "%" + contenu + "%");
				pstmt.setInt(4, categorie.getNoCategorie());
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
							rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
							categorieDAO.selectCategorie(rs.getInt("no_categorie")),
							retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));
				}

			}

			// if (ventesEnCours && ventesNonDebute) {
			// PreparedStatement pstmt = cnx.prepareStatement(
			// "SELECT * FROM ARTICLES_VENDUS WHERE ((debut_encheres < ?) AND (fin_encheres
			// > ?) OR (debut_encheres > ?)) AND (no_utilisateur = ?) AND (nom_article LIKE
			// ?) AND (no_categorie = ?) ORDER BY fin_encheres ASC");
			// pstmt.setTimestamp(1, dateDuJour);
			// pstmt.setTimestamp(2, dateDuJour);
			// pstmt.setTimestamp(3, dateDuJour);
			// pstmt.setInt(4, utilisateur.getNoUtilisateur());
			// pstmt.setString(5, "%" + contenu + "%");
			// pstmt.setInt(6, categorie.getNoCategorie());
			// ResultSet rs = pstmt.executeQuery();
			//
			// while (rs.next()) {
			// listeArticle.add(new Article(rs.getInt("no_article"),
			// rs.getString("nom_article"),
			// rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
			// rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"),
			// rs.getInt("prix_vente"),
			// utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
			// categorieDAO.selectCategorie(rs.getInt("no_categorie")),
			// retraitDAO.selectRetraitById(rs.getInt("no_article")),
			// rs.getString("chemin_img")));
			// }
			// }
			//
			// if (ventesEnCours && ventesTerminees) {
			// PreparedStatement pstmt = cnx.prepareStatement(
			// "SELECT * FROM ARTICLES_VENDUS WHERE ((debut_encheres < ?) AND (fin_encheres
			// > ?) OR (fin_encheres < ?)) AND (no_utilisateur = ?) AND (nom_article LIKE ?)
			// AND (no_categorie = ?) ORDER BY fin_encheres ASC");
			// pstmt.setTimestamp(1, dateDuJour);
			// pstmt.setTimestamp(2, dateDuJour);
			// pstmt.setTimestamp(3, dateDuJour);
			// pstmt.setInt(4, utilisateur.getNoUtilisateur());
			// pstmt.setString(5, "%" + contenu + "%");
			// pstmt.setInt(6, categorie.getNoCategorie());
			// ResultSet rs = pstmt.executeQuery();
			//
			// while (rs.next()) {
			// listeArticle.add(new Article(rs.getInt("no_article"),
			// rs.getString("nom_article"),
			// rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
			// rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"),
			// rs.getInt("prix_vente"),
			// utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
			// categorieDAO.selectCategorie(rs.getInt("no_categorie")),
			// retraitDAO.selectRetraitById(rs.getInt("no_article")),
			// rs.getString("chemin_img")));
			// }
			// }
			//
			// if (ventesNonDebute && ventesTerminees) {
			// PreparedStatement pstmt = cnx.prepareStatement(
			// "SELECT * FROM ARTICLES_VENDUS WHERE ((debut_encheres > ?) OR (fin_encheres <
			// ?)) AND (no_utilisateur = ?) AND (nom_article LIKE ?) AND (no_categorie = ?)
			// ORDER BY fin_encheres ASC");
			// pstmt.setTimestamp(1, dateDuJour);
			// pstmt.setTimestamp(2, dateDuJour);
			// pstmt.setInt(3, utilisateur.getNoUtilisateur());
			// pstmt.setString(4, "%" + contenu + "%");
			// pstmt.setInt(5, categorie.getNoCategorie());
			// ResultSet rs = pstmt.executeQuery();
			//
			// while (rs.next()) {
			// listeArticle.add(new Article(rs.getInt("no_article"),
			// rs.getString("nom_article"),
			// rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
			// rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"),
			// rs.getInt("prix_vente"),
			// utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
			// categorieDAO.selectCategorie(rs.getInt("no_categorie")),
			// retraitDAO.selectRetraitById(rs.getInt("no_article")),
			// rs.getString("chemin_img")));
			// }
			// }

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		}

		return listeArticle;
	}

	@Override
	public List<Article> SelectArticleFilterSellAllCategory(Utilisateur utilisateur, String contenu,
			boolean ventesEnCours, boolean ventesNonDebute, boolean ventesTerminees) throws BusinessException {

		List<Article> listeArticle = new ArrayList<Article>();
		Timestamp dateDuJour = Timestamp.valueOf(LocalDateTime.now());
		System.out.println(dateDuJour);

		if (!ventesEnCours && !ventesNonDebute && !ventesTerminees) {
			System.out.println("ArticleVentesJDBC : rien de coch� ");
			return listeArticle;
		}

		CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			if (ventesEnCours) {
				PreparedStatement pstmt = cnx.prepareStatement(
						"SELECT * FROM ARTICLES_VENDUS WHERE (debut_encheres < ?) AND (fin_encheres > ?) AND (no_utilisateur = ?) AND (nom_article LIKE ?) ORDER BY fin_encheres ASC");
				pstmt.setTimestamp(1, dateDuJour);
				pstmt.setTimestamp(2, dateDuJour);
				pstmt.setInt(3, utilisateur.getNoUtilisateur());
				pstmt.setString(4, "%" + contenu + "%");
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
							rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
							categorieDAO.selectCategorie(rs.getInt("no_categorie")),
							retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));
				}
			}

			if (ventesNonDebute) {
				PreparedStatement pstmt = cnx.prepareStatement(
						"SELECT * FROM ARTICLES_VENDUS WHERE (debut_encheres > ?) AND (no_utilisateur = ?) AND (nom_article LIKE ?) ORDER BY fin_encheres ASC");
				pstmt.setTimestamp(1, dateDuJour);
				pstmt.setInt(2, utilisateur.getNoUtilisateur());
				pstmt.setString(3, "%" + contenu + "%");
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
							rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
							categorieDAO.selectCategorie(rs.getInt("no_categorie")),
							retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));
				}

			}

			if (ventesTerminees) {

				PreparedStatement pstmt = cnx.prepareStatement(
						"SELECT * FROM ARTICLES_VENDUS WHERE (fin_encheres < ?) AND (no_utilisateur = ?) AND (nom_article LIKE ?) ORDER BY fin_encheres ASC");
				pstmt.setTimestamp(1, dateDuJour);
				
				pstmt.setInt(2, utilisateur.getNoUtilisateur());
				pstmt.setString(3, "%" + contenu + "%");
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					listeArticle.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
							rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
							categorieDAO.selectCategorie(rs.getInt("no_categorie")),
							retraitDAO.selectRetraitById(rs.getInt("no_article")), rs.getString("chemin_img")));
				}

			}
			//
			// if (ventesEnCours && ventesNonDebute) {
			// PreparedStatement pstmt = cnx.prepareStatement(
			// "SELECT * FROM ARTICLES_VENDUS WHERE ((debut_encheres < ?) AND (fin_encheres
			// > ?) OR (debut_encheres > ?)) AND (no_utilisateur = ?) AND (nom_article LIKE
			// ?) ORDER BY fin_encheres ASC");
			// pstmt.setTimestamp(1, dateDuJour);
			// pstmt.setTimestamp(2, dateDuJour);
			// pstmt.setTimestamp(3, dateDuJour);
			// pstmt.setInt(4, utilisateur.getNoUtilisateur());
			// pstmt.setString(5, "%" + contenu + "%");
			// ResultSet rs = pstmt.executeQuery();
			//
			// while (rs.next()) {
			// listeArticle.add(new Article(rs.getInt("no_article"),
			// rs.getString("nom_article"),
			// rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
			// rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"),
			// rs.getInt("prix_vente"),
			// utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
			// categorieDAO.selectCategorie(rs.getInt("no_categorie")),
			// retraitDAO.selectRetraitById(rs.getInt("no_article")),
			// rs.getString("chemin_img")));
			// }
			// }
			//
			// if (ventesEnCours && ventesTerminees) {
			// PreparedStatement pstmt = cnx.prepareStatement(
			// "SELECT * FROM ARTICLES_VENDUS WHERE ((debut_encheres < ?) AND (fin_encheres
			// > ?) OR (fin_encheres < ?)) AND (no_utilisateur = ?) AND (nom_article LIKE ?)
			// ORDER BY fin_encheres ASC");
			// pstmt.setTimestamp(1, dateDuJour);
			// pstmt.setTimestamp(2, dateDuJour);
			// pstmt.setTimestamp(3, dateDuJour);
			// pstmt.setInt(4, utilisateur.getNoUtilisateur());
			// pstmt.setString(5, "%" + contenu + "%");
			// ResultSet rs = pstmt.executeQuery();
			//
			// while (rs.next()) {
			// listeArticle.add(new Article(rs.getInt("no_article"),
			// rs.getString("nom_article"),
			// rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
			// rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"),
			// rs.getInt("prix_vente"),
			// utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
			// categorieDAO.selectCategorie(rs.getInt("no_categorie")),
			// retraitDAO.selectRetraitById(rs.getInt("no_article")),
			// rs.getString("chemin_img")));
			// }
			// }
			//
			// if (ventesNonDebute && ventesTerminees) {
			// PreparedStatement pstmt = cnx.prepareStatement(
			// "SELECT * FROM ARTICLES_VENDUS WHERE ((debut_encheres > ?) OR (fin_encheres <
			// ?)) AND (no_utilisateur = ?) AND (nom_article LIKE ?) ORDER BY fin_encheres
			// ASC");
			// pstmt.setTimestamp(1, dateDuJour);
			// pstmt.setTimestamp(2, dateDuJour);
			// pstmt.setInt(3, utilisateur.getNoUtilisateur());
			// pstmt.setString(4, "%" + contenu + "%");
			// ResultSet rs = pstmt.executeQuery();
			//
			// while (rs.next()) {
			// listeArticle.add(new Article(rs.getInt("no_article"),
			// rs.getString("nom_article"),
			// rs.getString("description_article"), rs.getTimestamp("debut_encheres"),
			// rs.getTimestamp("fin_encheres"), rs.getInt("prix_initial"),
			// rs.getInt("prix_vente"),
			// utilisateurDAO.selectUserById(rs.getInt("no_utilisateur")),
			// categorieDAO.selectCategorie(rs.getInt("no_categorie")),
			// retraitDAO.selectRetraitById(rs.getInt("no_article")),
			// rs.getString("chemin_img")));
			// }
			// }

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		}

		return listeArticle;
	}

	@Override
	public void updatePrixVenteArticleByIdArticle(int prixVente, int idArticle) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_PRIX_VENTE_ARTICLE);
				pstmt.setInt(1, prixVente);
				pstmt.setInt(2, idArticle);
				pstmt.executeUpdate();
				cnx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_ERREUR);
			throw businessException;
		}
	}

	@Override
	public List<Article> selectArticlesByUserId(int idUser) throws BusinessException {

		List<Article> listeArticle = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLES_BY_USER_ID);
			pstmt.setInt(1, idUser);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				int noArticle = rs.getInt("no_article");
				String nomArticle = rs.getString("nom_article");
				String description = rs.getString("description_article");
				int prixInitial = rs.getInt("prix_initial");
				int prixVente = rs.getInt("prix_vente");
				int noUtilisateur = rs.getInt("no_utilisateur");
				int noCategorie = rs.getInt("no_categorie");
				int noRetrait = rs.getInt("no_retrait");
				Timestamp debutEnch = rs.getTimestamp("debut_encheres");
				Timestamp finEnch = rs.getTimestamp("fin_encheres");
				String cheminImg = rs.getString("chemin_img");

				UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
				Utilisateur utilisateur = utilisateurDAO.selectUserById(noUtilisateur);

				RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
				Retrait retrait = retraitDAO.selectRetraitById(noRetrait);

				CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
				Categorie categorie = categorieDAO.selectCategorie(noCategorie);

				listeArticle.add(new Article(noArticle, nomArticle, description, debutEnch, finEnch, prixInitial,
						prixVente, utilisateur, categorie, retrait, cheminImg));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
			throw businessException;
		}
		return listeArticle;
	}

}
