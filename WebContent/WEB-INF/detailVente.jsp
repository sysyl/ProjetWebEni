<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetweb.messages.LecteurMessage"%>
<%@ page import="fr.eni.projetweb.bo.Article"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<title>Détail vente</title>

<style>
.input100 {
	height: 40px;
}

.wrap-login100 {
	width: 700px;
}

.container-login100 {
	align-items: flex-start;
}

.row {
	height: 30px;
}

.credits {
	margin-top: 30px;
	margin-bottom: 20px;
}

.boutons {
	margin-top: 40px;
}

input[type="submit"] {
	min-width: 0px;
	height: 40px;
	width: 120px;
	margin-left: 20px;
}

a.login100-form-btn {
	min-width: 0px;
	width: 120px;
	margin-left: 0px;
	height: 40px;
}

.bouton {
	display: flex;
	flex-direction: row;
	justify-content: center;
	text-align: center;
}

.erreurs {
	margin-top: 20px;
	margin-bottom: 50px;
	
}
</style>


</head>
<body>

<div class="topnav">
	
		<%
			if (session.getAttribute("idUtilisateur") != null) {
		%>
			<a class="active" href="<%=request.getContextPath()%>/ServletAccueilConnecte">ENI Enchères</a> 
			<a href="<%=request.getContextPath()%>/ServletAccueilConnecte">Accueil</a>
			<a href="<%=request.getContextPath()%>/ServletAfficherProfilVendeur?id=<%=session.getAttribute("idUtilisateur")%>">Mon profil</a>
		<%
			} else {
		%>
			<a class="active" href="<%=request.getContextPath()%>/ServletAccueil">ENI Enchères</a> 
			<a href="<%=request.getContextPath()%>/ServletAccueil">Accueil</a>
		<%
			}
		%>
	</div>

<div class="limiter">
	<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">

				<div class="row p-b-55" style="margin-bottom: 20px;">
					<span class="login100-form-title"> Détail vente </span>
					<hr>
				</div>
				
			
				<% 
				Article art = (Article)  request.getAttribute("article");
				if(art.getPathImg() != null){
					%>
				
				<div class="bouton">
					<img style="border-radius: 30px" src="${article.pathImg}"
						alt="image" height=200 border="0">
				</div>
				<%} %>

				<div class="row">
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<span class="txt1 p-b-11">Nom article : </span>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">${article.nomArticle}</div>
				</div>

				<div class="row p-b-65">
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<span class="txt1 p-b-11">Description : </span>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
					<textarea cols="35" rows="3" disabled style="background-color: white; resize: none" ><%=art.getDescriptionArticle().trim() %></textarea>
					
					</div>
				</div>

				<div class="row" >
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<span class="txt1 p-b-11">Catégorie : </span>
					</div>

					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">${article.categorie.libelle}</div>
				</div>

				<div class="row">
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<span class="txt1 p-b-11">Meilleure offre : </span>
					</div>

					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">${article.prixVente}</div>
				</div>

				<div class="row">
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<span class="txt1 p-b-11">Mise à prix : </span>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">${article.prixInitial}</div>
				</div>

				<div class="row">
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<span class="txt1 p-b-11">Fin de l'enchère : </span>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">${date_fin}</div>
				</div>

				<div class="row">
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<span class="txt1 p-b-11">Vendeur</span>
					</div>

					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<a href="<%=request.getContextPath()%>/ServletAfficherProfilVendeur?id=${article.utilisateur.noUtilisateur}" />
						${article.utilisateur.pseudo}</a>
						</div>
				</div>

				<div class="row">
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<span class="txt1 p-b-11">Retrait </span>
					</div>

					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">${article.retrait.rue}</div>
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5"></div>
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">${article.retrait.codePostal}
						${article.retrait.ville}</div>

				</div>

				<%

					Date dateAujourdhui = new Date();
					// si enchere terminee
					if (art.getFinEncheres().before(dateAujourdhui) || art.getFinEncheres().equals(dateAujourdhui)) {
				%>
				<div class="row boutons">
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<form class="form" action="<%=request.getContextPath()%>/ServletEnchereTerminee" method="post">
							<input type="hidden" name="noArticle" value="<%=art.getNoArticle()%>"> <input type="submit"
								class="login100-form-btn" value="Resultats" style="margin: auto">
						</form>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 bouton"
						style="margin-bottom: 25px; text-align: center;">

						<%
							if (session.getAttribute("idUtilisateur") != null) {
						%>
						<a class="login100-form-btn bouton"
							href="<%=request.getContextPath()%>/ServletAccueilConnecte">
							Retour</a>
						<%
							} else {
						%>
						<a class="login100-form-btn bouton"
							href="<%=request.getContextPath()%>/ServletAccueil"> Retour</a>
						<%
							}
						%>

					</div>
				</div>

				<%
					}
					// si enchere pas terminee
					else {
						if (session.getAttribute("idUtilisateur") != null) {
							int idUtilisateur = (int) session.getAttribute("idUtilisateur");
							if ((idUtilisateur == art.getUtilisateur().getNoUtilisateur())
									&& art.getDebutEncheres().after(dateAujourdhui)) {
				%>
				<div class="row boutons">
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 ahref bouton">
						<div class="row">
							<a class="login100-form-btn bouton"
								href="<%=request.getContextPath()%>/modifierVente?idArticle=${article.noArticle}">Modifier</a>
						</div>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 bouton">
						<a class="login100-form-btn bouton"
							href="<%=request.getContextPath()%>/ServletAccueilConnecte">
							Retour</a>
					</div>
				</div>
				<%
					} else if ((idUtilisateur == art.getUtilisateur().getNoUtilisateur())
									&& (art.getDebutEncheres().before(dateAujourdhui)
											|| art.getDebutEncheres().equals(dateAujourdhui))) {
				%>
				<div class="row boutons">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 bouton">
						<a class="login100-form-btn bouton"
							href="<%=request.getContextPath()%>/ServletAccueilConnecte">
							Retour</a>
					</div>
				</div>
				<%
					} else if ((idUtilisateur != art.getUtilisateur().getNoUtilisateur())
									&& (art.getDebutEncheres().before(dateAujourdhui)
											|| art.getDebutEncheres().equals(dateAujourdhui))
									&& art.getFinEncheres().after(dateAujourdhui)
									|| art.getFinEncheres().equals(dateAujourdhui)) {
				%>
				<form class="form"
					action="<%=request.getContextPath()%>/ServletEncherir?noArticle=${article.noArticle}"
					method="post">
					<div class="row credits">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
							<span class="txt1 p-b-11">Mes crédits disponibles : </span>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">${utilisateur.credit}</div>
					</div>


					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
							<span class="txt1 p-b-11">Ma proposition : </span>
						</div>

						<div class="wrap-input100  col-xs-6 col-sm-6 col-md-6 col-lg-6"
							style="height: 37px">
							<input type="number" class="input100" name="mise_prix"
								id="mise_prix" min="0" max="10000" step="1" value="100">
							<span class="focus-input100"></span>
						</div>
					</div>




					<div class="row boutons">

						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<input type="submit" class="login100-form-btn" value="Enchérir"
								style="margin: auto">
						</div>

						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 bouton"
							style="margin-bottom: 25px; text-align: center;">

							<%
								if (session.getAttribute("idUtilisateur") != null) {
							%>
							<a class="login100-form-btn bouton"
								href="<%=request.getContextPath()%>/ServletAccueilConnecte">
								Retour</a>
							<%
								} else {
							%>
							<a class="login100-form-btn bouton"
								href="<%=request.getContextPath()%>/ServletAccueil"> Retour</a>
							<%
								}
							%>

						</div>
					</div>
					
				</form>


				<%
					} else if (idUtilisateur != art.getUtilisateur().getNoUtilisateur()
									&& art.getFinEncheres().before(dateAujourdhui)) {
				%>
				<div class="row boutons">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 bouton">
						<a class="login100-form-btn bouton"
							href="<%=request.getContextPath()%>/ServletAccueilConnecte">
							Retour</a>
					</div>
				</div>
				<%
					} else if (idUtilisateur != art.getUtilisateur().getNoUtilisateur()
									&& art.getDebutEncheres().after(dateAujourdhui)) {
				%>
				<div class="row boutons">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 bouton">
						<a class="login100-form-btn bouton"
							href="<%=request.getContextPath()%>/ServletAccueilConnecte">
							Retour</a>
					</div>
				</div>
				<%
					}

						} else {
				%>
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="row bouton" style="margin-top: 40px;">
						<a class="login100-form-btn bouton"
							href="<%=request.getContextPath()%>/ServletAccueil"> Retour</a>
					</div>
				</div>
				<%
					}
					}
				%>

				<c:if test="${!empty listeCodesErreur}">
					<div class="row erreurs p-b-55 p-t-20">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 " >
							<div class="alert alert-danger" role="alert">	
									<strong>Erreur!</strong>
									<ul>
										<c:forEach var="code" items="${listeCodesErreur}">
											<li>${LecteurMessage.getMessageErreur(code)}</li>
										</c:forEach>
									</ul>
							</div>
						</div>
					</div>
				</c:if>

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