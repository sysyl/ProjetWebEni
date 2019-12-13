<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="fr.eni.projetweb.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profil</title>
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
.input100 {
	height: 40px;
}
.bouton{
		display : inline;
		margin-left: 20px;
		padding: 13px 33px 13px 33px;
		border-radius: 25px;
	}
.boutons{
	margin-top: 50px;
}

.row{
	margin-bottom: 10px;
}
.wrap-login100{
	width : 700px;
}
.container-login100{
	align-items: flex-start;
	}
	

</style>
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

				
					<span class="login100-form-title"> Mon Profil </span> 
					<hr>
				
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<span class="txt1 p-b-11">Pseudo :</span></div>
						<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">${user.pseudo}</div>
					</div>
					
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<span class="txt1 p-b-11">Nom :</span></div>
						<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">${user.nom}</div>
					</div>
					
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<span class="txt1 p-b-11">Prénom :</span></div>

						<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">${user.prenom}</div>
					</div>		
					
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<span class="txt1 p-b-11">Prénom :</span></div>

						<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">${user.prenom}</div>
					</div>					
					
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<span class="txt1 p-b-11">E-mail :</span></div>
						<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">${user.email}</div>
					</div>	
					
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<span class="txt1 p-b-11">Téléphone :</span></div>
						<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">${user.telephone}</div>
					</div>
					
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<span class="txt1 p-b-11">Rue :</span></div>

						<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">${user.rue}</div>
					</div>
					
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<span class="txt1 p-b-11">Code postal :</span></div>

						<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">${user.codePostal}</div>
					</div>
					
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<span class="txt1 p-b-11">Ville :</span></div>
						<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">${user.ville}</div>
					</div>

					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<span class="txt1 p-b-11">Crédits :</span></div>
						<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">${user.credit}</div>
					</div>				

					
					<div class="row boutons" >
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" style="margin-bottom: 25px; text-align: center;">
							<a class="login100-form-btn bouton" href="<%=request.getContextPath()%>/ServletModifierProfil"> 
										Modifier</a>
						</div>
					
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" style="margin-bottom: 25px; text-align: center;">
							<a class="login100-form-btn bouton" href="<%=request.getContextPath()%>/ServletAccueilConnecte"> 
										Retour</a>	
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