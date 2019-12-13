<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="fr.eni.projetweb.messages.LecteurMessage"%>
<%@ page import="fr.eni.projetweb.bo.Article"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vente - modification</title>
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
.divUpload {
	display: flex;
	justify-content: center;
	align-items: center;
}

textarea {
	padding: 12px 20px;
	box-sizing: border-box;
	resize: vertical !important;
	/* on peut aggrandir textearea que verticalement */
	height: 100px !important;
}

fieldset {
	border: 1px solid #ccc;
	padding: 12px;
}

.input100 {
	height: 30px;
}

.boutons {
	margin: 30px 10px 10px 50px;
}

.wrap-login100 {
	margin-top: 20px;
	width: 992px;
}

.wrap-input100 {
	margin-bottom: 15px;
}

.img {
	padding-top: 20px;
	display: flex;
	align-items: center;
	margin-bottom: 30px;
}

input {
	width: 100%;
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

.wrap-login100 {
	margin-top: 5px;
}

.categorie {
	margin-top: 10px;
}

.ahref {
	display: flex;
	flex-direction: row;
	justify-content: center;
}
</style>
</head>
<body>




	<div class="topnav">
		<a class="active"
			href="<%=request.getContextPath()%>/ServletAccueilConnecte">ENI
			Enchères</a> <a
			href="<%=request.getContextPath()%>/ServletAccueilConnecte">Accueil</a>
	</div>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85" style="padding-top: 20px;">

				<div class="row">
					<div class="col-sm-12">
						<h1>
							<span class="login100-form-title">Modification des informations sur l'article</span>
						</h1>
					</div>
				</div>
				<hr>

				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
						<c:if test="${!empty listeCodesErreur}">
							<div class="alert alert-danger" role="alert">
								<strong>Erreur!</strong>
								<ul>
									<c:forEach var="code" items="${listeCodesErreur}">
										<li>${LecteurMessage.getMessageErreur(code)}</li>
									</c:forEach>
								</ul>
							</div>
						</c:if>
					</div>
				</div>

				<!--<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>  -->
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

					<form class="form"
						action="<%=request.getContextPath()%>/ServletEnregistrementModifications"
						method="post">
						<input type="hidden" name="idArticle" value="${article.noArticle}">
						<input type="hidden" name="idRetrait"
							value="${article.retrait.idRetrait}">

						<div class="row">
							<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"></div>
							<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
								<label for="nom_article"><span class="txt1 p-b-11">Nom
										d'article * :</span></label>
								<div class="wrap-input100 ">
									<input class="input100" type="text" name="nom_article"
										id="nom_article" required value="${article.nomArticle}">
									<span class="focus-input100"></span>
								</div>
							</div>
						</div>


						<div class="row">
							<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"></div>
							<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
								<label for="description"><span class="txt1 p-b-11">Description
										* :</span></label>
								<div class="wrap-input100 ">
									<textarea class="input100" name="description" id="description">${article.descriptionArticle}</textarea>
									<span class="focus-input100"></span>
								</div>
							</div>
						</div>


						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<div class="row categorie">
									<span class="txt1 p-b-11">Catégorie * :</span> <span
										class="btn-show-pass"></span> <select name="categorie"
										id="categorie" class="input100"
										onChange="combo(this, 'theinput')"
										style="margin-left: 15px; margin-bottom: 25px; width: 235px; height: 37px;">
										<c:if test="${!empty categories}">
											<c:forEach var="c" items="${categories}">
												<option value="${c.noCategorie}">${c.libelle}</option>
											</c:forEach>
										</c:if>
									</select>
								</div>

								<!--  Methode javascript qui permet de mettre l'element option a selected quand le numero de la categorie
										article choisi correspond a l'iteration en cours -->
								<script>
											function setSelectedIndex(s, i){
												s.options[i-1].selected = true;
											return;
											}
											setSelectedIndex(document.getElementById("categorie"), ${article.categorie.noCategorie});	
										</script>


								<div class="row">
									<label for="mise_prix"><span class="txt1 p-b-11"
										style="margin-right: 10px">Mise à prix * :</span></label>
									<div class="wrap-input100  col-xs-7 col-sm-7 col-md-7 col-lg-7"
										style="height: 37px">
										<input type="number" class="input100" name="mise_prix"
											id="mise_prix" min="0" max="10000" step="1"
											value="${article.prixInitial}"> <span
											class="focus-input100"></span>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
										<label for="date_debut"><span class="txt1 p-b-11">Début
												de l'enchère * :</span></label>
										<div class="wrap-input100">
											<input class="input100" type="datetime-local"
												name="date_debut" id="date_debut" required
												value="${dateDebut}"> <span class="focus-input100"></span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
										<label for="date_fin"><span class="txt1 p-b-11">Fin
												de l'enchère * :</span></label>
										<div class="wrap-input100">
											<input class="input100" type="datetime-local" name="date_fin"
												id="date_fin" required value="${dateFin}"> <span
												class="focus-input100"></span>
										</div>
									</div>
								</div>
								<div class="row">
										<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
											<label for="lien_image"><span class="txt1 p-b-11">URL de l'image :</span></label>
											<div class="wrap-input100">
												<input type="text" class="input100" name="lien_image" id="lien_image" value="${article.pathImg}">
												<span class="focus-input100"></span>
											</div>
										</div>
								</div>
								
								


							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">



								<fieldset class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<legend style="width: 20%;">Retrait</legend>
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<label for="rue"><span class="txt1 p-b-11">Rue
													* :</span></label>
											<div class="wrap-input100 ">
												<input class="" type="text" name="rue" id="rue" required
													value="${article.retrait.rue}"> <span
													class="focus-input100"></span>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<label for="code_postal"><span class="txt1 p-b-11">Code postal (format : 44000) * :</span></label>
											<div class="wrap-input100 ">
												<input class="" type="text" name="code_postal"
													id="code_postal" required
													value="${article.retrait.codePostal}"> <span
													class="focus-input100"></span>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<label for="ville"><span class="txt1 p-b-11">Ville
													* :</span></label>
											<div class="wrap-input100 ">
												<input class="" type="text" name="ville" id="ville" required
													value="${article.retrait.ville}"> <span
													class="focus-input100"></span>
											</div>
										</div>
									</div>
								</fieldset>


							</div>

						</div>



						<div class="row boutons">
							<div class="flex-sb-m w-full p-b-48">
								<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
									<input type="submit" class="login100-form-btn"
										value="Enregistrer" style="margin: auto; padding: 0px;">
								</div>

								<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 ahref">
									<div class="row">
										<a class="login100-form-btn bouton"
											href="<%=request.getContextPath()%>/supprimerVente?idArticle=${article.noArticle}">
											Supprimer </a>
									</div>
								</div>

								<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 ahref">
									<div class="row">
										<a class="login100-form-btn bouton"
											href="<%=request.getContextPath()%>/ServletAccueilConnecte">
											Annuler </a>
									</div>
								</div>
							</div>
						</div>
						<!--  style="vertical-align: middle;" -->




					</form>
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