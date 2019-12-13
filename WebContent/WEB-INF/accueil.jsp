<%@page import="fr.eni.projetweb.bll.MethodesUtiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetweb.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="fr.eni.projetweb.bo.Categorie"%>
<%@page import="fr.eni.projetweb.bo.Article"%>
<%@page import="fr.eni.projetweb.bo.Utilisateur"%>
<%@page import="java.util.List"%>
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
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<!--===============================================================================================-->
<style>
.center {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}
</style>
</head>

<%
	List<Categorie> listeCategorie = (List<Categorie>) session.getAttribute("listeCategorie");
	List<Article> listeArticlePasConnecte = (List<Article>) session.getAttribute("listeArticlePasConnecte");
%>

<body>
	<div class="topnav">
		<a class="active" href="#">ENI Enchères</a> <a
			href="<%=request.getContextPath()%>/connexion">S'inscrire - Se
			connecter</a>
	</div>


	<div class="container-home100">

		<span style="text-align: center;" class="login100-form-title p-b-32">
			Liste des enchères </span>

		<div class="row">
			<div class="col-md-6">
				<div class="wrap-home100 p-l-85 p-r-85 p-t-55 p-b-55">
					<form method="post" action="/ProjetWebENI/ServletAccueil"
						class="login100-form validate-form flex-sb flex-w">
						<span class="login100-form-title p-b-32"> Filtres : </span>
						<div class="row">
							<div class="col-md-12">
								<div class="wrap-input100" style="height: 30px; width: 230px;">
									<i class="fa fa-search" aria-hidden="true"></i> <input
										maxlength="50" type="search" name="search"
										placeholder="Nom de l'article"> <span
										class="focus-input100"></span>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<label for="date_debut" style="margin-top: 25px;"><span
									class="txt1 p-b-11">Début de l'enchère :</span></label>
								<div class="wrap-input100">
									<input class="input100" type="datetime-local" name="date_debut"
										id="date_debut"> <span class="focus-input100"></span>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<label for="date_fin" style="margin-top: 25px;"><span
									class="txt1 p-b-11">Fin de l'enchère :</span></label>
								<div class="wrap-input100">
									<input class="input100" type="datetime-local" name="date_fin"
										id="date_fin"> <span class="focus-input100"></span>
								</div>
							</div>
						</div>


						<div class="flex-sb-m w-full p-b-48">
							<label for="date_debut" style="margin-top: 25px;"><span
								class="txt1 p-b-11">Catégorie :</span></label> <select class="input100"
								name="categorie" onChange="combo(this, 'theinput')"
								style="margin-bottom: 25px; margin-top: 25px; width: 250px;">
								<option value="-1">Toutes</option>
								<c:if test="${!empty listeCategorie}">
									<c:forEach var="c" items="${listeCategorie}">
										<option value="${c.noCategorie}">${c.libelle}</option>
									</c:forEach>
								</c:if>
							</select>
						</div>

						<div class="container-login100-form-btn">
							<button type="submit" style="width: 100px; margin-bottom: 15px;"
								class="btn btn-success">Rechercher</button>
						</div>
					</form>
					<button style="width: 100px;" class="btn btn-warning"
						onClick="javascript:document.location.href='<%=request.getContextPath()%>/ServletDeconnexion'">
						<i class="fa fa-refresh" aria-hidden="true"></i>
					</button>
				</div>
			</div>

			<%
				if (listeArticlePasConnecte != null) {
			%>
			<div class="col-md-6">

				<div class="container-fluid">
					<div class="row">
						<%
							int i = 0;
								for (Article article : listeArticlePasConnecte) {
						%>


						<div class="col-md-4">
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
									<h5 class="card-title"><%=article.getNomArticle()%></h5>
									<p class="card-text">
										Prix:<%=article.getPrixVente()%></p>
									<p class="card-text">
										Fin de l'enchère:<%=MethodesUtiles.getStringDateFromTimestamp(article.getFinEncheres())%></p>
									<p class="card-text">
										Vendeur:<a
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
			<%
				}
			%>
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
	<!--===============================================================================================-->
	<!-- 	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script> -->
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</body>
</html>