<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="fr.eni.projetweb.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="fr.eni.projetweb.bo.Categorie"%>
<%@page import="fr.eni.projetweb.bo.Article"%>
<%@page import="fr.eni.projetweb.bo.Utilisateur"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.projetweb.bll.MethodesUtiles"%>

<!DOCTYPE html>
<html>
<head>
<title>ENI ENCHERES</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
</head>
<style>
.center {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}
</style>
<body>

	<div class="topnav">
		<a class="active" href="#">ENI Enchères - Bonjour <%=request.getSession().getAttribute("pseudo")%></a>
		<a href="<%=request.getContextPath()%>/ServletAccueilConnecte">Accueil</a>
		<a href="<%=request.getContextPath()%>/ServletVendre">Vendre un
			article</a> <a href="<%=request.getContextPath()%>/ServletProfil">Mon
			profil</a> <a href="<%=request.getContextPath()%>/ServletAccueil">Déconnexion</a>
	</div>

	<div class="container-home100">

		<span style="text-align: center;" class="login100-form-title p-b-32">
			Liste des enchères </span>

		<div class="row">
			<div class="col-md-12">


				<%
					List<Categorie> listeCategorie = (List<Categorie>) session.getAttribute("listeCategorie");
					List<Article> listeArticle = (List<Article>) session.getAttribute("listeArticle");
				%>

				<div class="container-fluid">
					<div class="row">
						<%
							int i = 0;
							for (Article article : listeArticle) {
						%>


						<div class="col-md-2">
							<div class="card">
								<div class="card-body">
									<div class="center">
										<%
											if (article.getPathImg() != null) {
										%>
										<img style="border-radius: 30px" src=<%=article.getPathImg()%>
											height=150 width=150 border="0">
										<%
											} else {
										%>
										<img style="border-radius: 30px"
											src="images/img_manquante.png" height=150 width=150
											border="0">
										<%
											}
										%>
									</div>
									<div class="row center">
										<h5 class="card-title" style=""><%=article.getNomArticle()%></h5>
									</div>
									<p class="card-text">
										Prix :
										<%=article.getPrixVente()%>
										point(s)
									</p>
									<p class="card-text">
										Fin de l'enchère : <br><%=MethodesUtiles.getStringDateFromTimestamp(article.getFinEncheres())%></p>
									<p class="card-text">
										Vendeur : <a
											href="<%=request.getContextPath()%>/ServletAfficherProfilVendeur?id=<%=article.getUtilisateur().getNoUtilisateur()%>"
											name="<%=article.getUtilisateur().getPseudo()%>"><%=article.getUtilisateur().getPseudo()%></a>
									</p>
									<a
										href="<%=request.getContextPath()%>/ServletAfficherVente?id=<%=article.getNoArticle()%>"
										class="btn btn-success">Voir l'enchère</a>
								</div>
							</div>
						</div>

						<%
							i++;
							}
						%>
					</div>
				</div>
			</div>

		</div>

	</div>

	<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/countdowntime/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>