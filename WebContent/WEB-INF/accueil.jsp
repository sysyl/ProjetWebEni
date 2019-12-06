<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="fr.eni.projetweb.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</head>

<body>

<div class="topnav">
  <a class="active" href="#">ENI Enchères</a>
  <a href="<%=request.getContextPath()%>/connexion">S'inscrire - Se connecter</a> 
</div>

	<div class="container-home100">

		<span style="text-align: center;" class="login100-form-title p-b-32">
			Liste des enchères </span>

		<div class="row">
			<div class="col-md-6">
			<div class="wrap-home100 p-l-85 p-r-85 p-t-55 p-b-55">
			
				<form method="post" action="/ProjetWebENI/ServletAccueilConnecte"
						class="login100-form validate-form flex-sb flex-w">
						<span class="login100-form-title p-b-32"> Filtres : </span>
						<div class="wrap-input100 validate-input m-b-36">
							<i class="fa fa-search" aria-hidden="true"></i> <input
								maxlength="50" type="search" name="search"
								placeholder="Nom de l'article">
						</div>

						<div class="flex-sb-m w-full p-b-48">
							<p>Catégorie :</p>
							<span class="btn-show-pass"></span> <select name="categorie"
								onChange="combo(this, 'theinput')"
								style="margin-left: 15px; margin-bottom: 25px; width: 150px;">
								<option value="toutes">Toutes</option>
								<c:if test="${!empty listCategories}">
									<c:forEach var="c" items="${listCategories}">
										<option value="${c.noCategorie}">${c.libelle}</option>
									</c:forEach>
								</c:if>
							</select> <span class="focus-input100"></span>
						</div>

					<div class="container-login100-form-btn">
							<button type="submit" class="btn btn-success">Rechercher</button>
						</div>

				</form>
			</div>
		</div>
		
		<div class="col-md-6">


				<%
					List<Article> listeArticle = (List<Article>) session.getAttribute("listeArticle");
					List<String> listePseudoUtilisateurs = (List<String>) session.getAttribute("listePseudo");
				%>

				<div class="container-fluid">
					<div class="row">
						<%
							int i = 0;
							for (Article article : listeArticle) {
						%>


						<div class="col-md-4">
							<div class="card">
								<div class="card-body">
									<h5 class="card-title"><%=article.getNomArticle()%></h5>
									<p class="card-text">
										Prix:<%=article.getPrixVente()%></p>
									<p class="card-text">
										Fin de l'enchère:<%=article.getFinEncheres()%></p>
									<p class="card-text">
										Vendeur:<a
											href="<%=request.getContextPath()%>/ServletAfficheUser?id=<%=listeArticle.get(i).getNoUtilisateur()%>"
											name="<%=listePseudoUtilisateurs.get(i)%>"><%=listePseudoUtilisateurs.get(i)%></a>
									</p>
									<a href="#" class="btn btn-success">Voir l'enchère</a>
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