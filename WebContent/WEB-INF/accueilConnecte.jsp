<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

	<div class="limiter">
		<div class="container-home100">
			<div class="flex-sb-m w-full p-b-48">
				<span style="text-align: left" class="login100-form-title p-b-32">
					ENI Enchères</span> <a href="<%request.getContextPath();%>/connexion">Enchères</a>
				<a href="<%request.getContextPath();%>/connexion">Vendre un
					article</a> <a href="<%request.getContextPath();%>/connexion">Mon
					profil</a> <a href="<%request.getContextPath();%>/connexion">Déconnexion</a>
			</div>
			<div class="flex-sb-m w-full p-b-48">
				<span style="text-align: center" class="login100-form-title p-b-32">
					Liste des enchères </span>
			</div>
			<div class="wrap-home100 p-l-85 p-r-85 p-t-55 p-b-55">

				<form method="post" action="/ProjetWebENI/ServletAccueil"
					class="login100-form validate-form flex-sb flex-w">
					<span class="login100-form-title p-b-32"> Filtres : </span>
					<div class="wrap-input100 validate-input m-b-36">
						<i class="fa fa-search" aria-hidden="true"></i> <input
							maxlength="50" type="search" name="search"
							placeholder="Nom de l'article">

					</div>

					<div class="flex-sb-m w-full p-b-48">
						<span class="txt1 p-b-11"> Catégorie :</span> <span
							class="btn-show-pass"></span> <select name="thelist"
							onChange="combo(this, 'theinput')">
							<option value="toutes">Toutes</option>
							<option value="ameublement">Ameublement</option>
							<option value="informatique">Informatique</option>
							<option value="sportLoisirs">Sport et Loisirs</option>
							<option value="vetement">Vêtement</option>
						</select> <span class="focus-input100"></span>

					</div>


					<div class="container-login100-form-btn">
						<div class="flex-sb-m w-full p-b-48">

							<input type="radio" id="achats" name="achats/ventes"
								value="achats" checked> <label for="achats">Achats</label>

							<input type="radio" id="ventes" name="achats/ventes"
								value="ventes"> <label for="ventes">Mes ventes</label>
						</div>
						<fieldset>
							<div class="flex-sb-m w-full p-b-48">
								<input type="checkbox" id="enchereouverte" name="enchereouverte"
									value="enchereouverte"> <label for="enchereouverte">Enchères
									ouvertes</label>
								<input type="checkbox" id="enchereencours" name="enchereencours"
									value="enchereencours"> <label for="enchereencours">Mes
									enchères en cours</label>
								<input type="checkbox" id="enchereremporte"
									name="enchereremporte" value="enchereremporte"> <label
									for="enchereremporte">Mes enchères remportées</label>
							</div>
						</fieldset>

					</div>
					<!-- 					
						
							 <label for="enchereouverte">enchères
								ouvertes</label> <input type="checkbox" id="enchereencours"
								name="enchereencours" value="enchereencours">
								 <label
								for="enchereencours">mes enchères en cours</label>
								
								 <input type="checkbox" id="enchereremporte" name="enchereremporte"
								value="enchereremporte"> <label for="enchereremporte">mes
								enchères remportées</label>
						</div>
						<div class="container-login100-form-btn">
							<input type="radio" id="ventes" name="achats/ventes"
								value="ventes"> <label for="ventes">Mes ventes</label> <input
								type="checkbox" id="venteencours" name="venteencours"
								value="venteencours"> <label for="venteencours">mes
								ventes en cours</label> <input type="checkbox" id="ventenondebute"
								name="ventenondebute" value="ventenondebute"> <label
								for="ventenondebute">ventes non débutées</label> <input
								type="checkbox" id="ventetermine" name="ventetermine"
								value="ventetermine"> <label for="ventetermine">ventes
								terminées</label>

						</div>

					</div> -->

					<div class="container-login100-form-btn">
						<button type="submit" class="login100-form-btn">Rechercher</button>
					</div>

				</form>
			</div>
		</div>
	</div>


	<div id="dropDownSelect1"></div>

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