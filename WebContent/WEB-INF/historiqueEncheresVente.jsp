<%@page import="fr.eni.projetweb.bll.MethodesUtiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fr.eni.projetweb.bo.Article"%>
<%@ page import="fr.eni.projetweb.bo.Enchere"%>
<%@ page import="java.util.List"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Historique d'enchères</title>
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
<style>
.wrap-login100 {
	width: 700px;
}

.container-login100 {
	align-items: flex-start;
}
.liste{
	display: flex;
	flex-direction: row;
	text-align: center;
}
.bouton {
	display: flex;
	flex-direction: row;
	justify-content: center;
	text-align: center;
}
a.login100-form-btn {
	min-width: 0px;
	width: 120px;
	margin-left: 0px;
	height: 40px;
}

</style>
</head>
<body>
	<% List<Enchere> listeEncheres = (List<Enchere>) request.getAttribute("listeEncheres");
		Article article = (Article)  request.getAttribute("article");
	%>

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
					<span class="login100-form-title"> Historique des enchères </span>
					<hr>
				</div>
				<hr>
				<div class="row">
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<span class="txt1 p-b-11">Nom article : </span>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">${article.nomArticle}</div>
				</div>

				<div class="row">
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<span class="txt1 p-b-11">Meilleure offre : </span>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">${article.prixVente} point(s)</div>
				</div>

				<div class="row">
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<span class="txt1 p-b-11">Fin de l'enchère : </span>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"><%= MethodesUtiles.getStringDateFromTimestamp(article.getFinEncheres()) %></div>
				</div>
				<hr>

					<div class="row p-b-55 p-t-20">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >
						
						<div class="row liste p-b-10">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"> </div>
						<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3"><span class="txt1 p-b-11">Enchérisseurs</span></div>
						<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3"><span class="txt1 p-b-11">Montant</span></div>	
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5"><span class="txt1 p-b-11">Date</span></div>	
						</div>	
						
						
						
				<%int i=0; %>
				
				<% if(listeEncheres != null) {
						for(Enchere e : listeEncheres){
						%>
						<% i++; %>
						<div class="row liste p-b-10">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"><%=i%>. </div>
						<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3"><%=e.getUtilisateur().getPseudo() %></div>
						<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3"><%=e.getMontantEnchere() %> points</div>	
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5"><%= MethodesUtiles.getStringDateFromTimestamp(e.getDateEnchere()) %></div>	
						</div>		
						<%	
						}}
					%>
					
						</div>
					</div>
				
				<div class="row boutons">

						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 bouton"
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