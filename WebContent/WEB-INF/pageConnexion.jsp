<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
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
		<a class="active" href="<%=request.getContextPath()%>/ServletAccueil">ENI
			Enchères</a> <a href="<%=request.getContextPath()%>/ServletAccueil">Accueil</a>
	</div>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">

				<form method="post" action="/ProjetWebENI/connexion"
					class="login100-form validate-form flex-sb flex-w">
					<span class="login100-form-title p-b-32"> Se connecter </span> <span
						class="txt1 p-b-11"> Identifiant </span>
					<div class="wrap-input100 validate-input m-b-36"
						data-validate="L'identifiant est requis">
						<input class="input100" type="text" name="pseudo"> <span
							class="focus-input100"></span>
					</div>

					<span class="txt1 p-b-11"> Mot de passe </span>
					<div class="wrap-input100 validate-input m-b-12"
						data-validate="Le mot de passe est requis">
						<span class="btn-show-pass"> <i class="fa fa-eye"></i>
						</span> <input class="input100" type="password" name="mot_de_passe">
						<span class="focus-input100"></span>
					</div>

					<div class="flex-sb-m w-full p-b-48">
						<div class="contact100-form-checkbox">
							<input class="input-checkbox100" id="ckb1" type="checkbox"
								name="remember-me"> <label class="label-checkbox100"
								for="ckb1"> Se souvenir de moi </label>
						</div>

						<div>
							<a href="#" class="txt3"> Mot de passe oublié </a>
						</div>
					</div>

					<div class="flex-sb-m w-full p-b-48">
						<div class="container-login100-form-btn">
							<button type="submit" class="login100-form-btn">Connexion</button>
						</div>
						<div class="container-login100-create-btn">
							<button id="newAccount" class="login100-create-btn">Créer
								un compte</button>
							<script type="text/javascript">
								document.getElementById("newAccount").onclick = function() {
									location.href = "http://localhost:8080/ProjetWebENI/ServletCreationCompte";
								};
							</script>

						</div>
					</div>
					<%
						if (request.getAttribute("loginError") != null) {
					%>
					<div id="alert" class="alert alert-danger" role="alert">
						<strong>Login ou mot de passe erroné !</strong>
					</div>
					<%
						}
					%>
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