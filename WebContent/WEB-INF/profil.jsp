<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

.bouton {
	display: flex;
	flex-direction: column;
	justify-content: center;
	text-align: center;
	margin: auto;
}
</style>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="container bootstrap snippet"
				style="background-color: #fff">
				<div class="p-l-85 p-r-85 p-t-55 p-b-55">

					<div class="row">
						<div class="col-sm-12">
							<h1>
								<span class="login100-form-title">Mon Profil </span>
							</h1>
						</div>
					</div>
					<hr>

					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<label for="pseudo"><span class="txt1 p-b-11">Pseudo
								: ${user.pseudo}</span></label>

					</div>

					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<label for="nom"><span class="txt1 p-b-11">Nom :
								${user.nom}</span></label>

					</div>

					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<label for="prenom"><span class="txt1 p-b-11">Prénom
								: ${user.prenom}</span></label>

					</div>

					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<label for="email"><span class="txt1 p-b-11">E-mail
								: ${user.email}</span></label>

					</div>

					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<label for="telephone"><span class="txt1 p-b-11">
								Téléphone : ${user.telephone}</span></label>

					</div>

					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<label for="rue"><span class="txt1 p-b-11"> Rue :
								${user.rue}</span></label>

					</div>

					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<label for="codepostal"><span class="txt1 p-b-11">Code
								postal : ${user.codePostal}</span></label>

					</div>

					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<label for="ville"><span class="txt1 p-b-11">Ville
								: ${user.ville}</span></label>

					</div>
					
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<label for="ville"><span class="txt1 p-b-11">Crédits
								: ${user.credit}</span></label>

					</div>

					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<div class="row">
							<a class="login100-form-btn bouton"
								href="<%=request.getContextPath()%>/ServletModifierProfil">
								Modifier </a>
						</div>
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