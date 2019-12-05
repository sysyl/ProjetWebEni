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
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<!--===============================================================================================-->
</head>

<body>
	<div class="topnav">
		<a class="active" href="#">ENI Enchères - Bonjour <%=request.getSession().getAttribute("pseudo")%></a>
		<a href="<%=request.getContextPath()%>/ServletListeEncheres">Enchères</a>
		<a href="<%=request.getContextPath()%>/ServletVendre">Vendre un
			article</a> <a href="<%=request.getContextPath()%>/ServletProfil">Mon
			profil</a> <a href="<%=request.getContextPath()%>/ServletAccueil">Déconnexion</a>
	</div>

	<div class="limiter">
		<div class="container-home100">
			<div class="flex-sb-m w-full p-b-48">
				<span style="text-align: center" class="login100-form-title p-b-32">
					Liste des enchères </span>
			</div>
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


					<div class="container">
						<div class="flex-sb-m w-full p-b-48">
							<label> <input type="checkbox" name="achats" checked
								value="achats" id="achats"> <span class="label-text"
								onclick="invertCheckbox('input[name=\'ventes\']', false);setAchat()">Achats</span>
							</label> <label> <input type="checkbox" name="ventes"
								value="ventes" id="ventes"> <span class="label-text"
								onclick="invertCheckbox('input[name=\'achats\']', false);setVente()">Mes
									ventes</span>
							</label>
						</div>
						<div class="col-md-6">
							<div class="form-check">
								<label> <input type="checkbox" name="achat"
									value="enchereouverte" id="enchereouverte"> <span
									class="label-text">Enchères ouvertes</span>
								</label>
							</div>
							<div class="form-check">
								<label> <input type="checkbox" name="achat"
									value="enchereencours" id="enchereencours"> <span
									class="label-text">Mes enchères en cours</span>
								</label>
							</div>
							<div class="form-check">
								<label> <input type="checkbox" name="achat"
									value="enchereremporte" id="enchereremporte"> <span
									class="label-text">Mes enchères remportées</span>
								</label>
							</div>

						</div>
						<div class="col-md-6">
							<div class="form-check">
								<label> <input type="checkbox" name="vente"
									value="venteencours" disabled id="venteencours"> <span
									class="label-text">Mes ventes en cours</span>
								</label>
							</div>
							<div class="form-check">
								<label> <input type="checkbox" name="vente"
									value="ventenondebute" disabled id="ventenondebute"> <span
									class="label-text">Ventes non débutées</span>
								</label>
							</div>
							<div class="form-check">
								<label> <input type="checkbox" name="vente"
									value="ventetermine" disabled id="ventetermine"> <span
									class="label-text">Ventes terminées</span>
								</label>
							</div>

						</div>
					</div>

					<div class="container-login100-form-btn">
						<button type="submit" class="login100-form-btn">Rechercher</button>
					</div>

				</form>
			</div>
		</div>
	</div>


	<div id="dropDownSelect1"></div>

	<!--===============================================================================================-->
	<script type="text/javascript">
		function invertCheckbox(tags, value) {
			tags = typeof (tags) == "string" ? $(tags) : tags;
			if (typeof (value) != "undefined") {
				tags.prop("checked", value);
			} else {
				tags.each(function(i, tag) {
					$(tag).prop("checked", !$(tag).is(":checked"));
				});
			}
		}

		function setVente() {
			document.getElementById("enchereouverte").disabled = true;
			document.getElementById("venteencours").disabled = false;
			
			document.getElementById("enchereencours").disabled = true;
			document.getElementById("ventenondebute").disabled = false;
			
			document.getElementById("enchereremporte").disabled = true;
			document.getElementById("ventetermine").disabled = false;

		}

		function setAchat() {
			document.getElementById("venteencours").disabled = true;
			document.getElementById("enchereencours").disabled = false;
			
			document.getElementById("ventenondebute").disabled = true;
			document.getElementById("enchereouverte").disabled = false;
			
			document.getElementById("ventetermine").disabled = true;
			document.getElementById("enchereremporte").disabled = false;
		}
	</script>
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
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</body>
</html>