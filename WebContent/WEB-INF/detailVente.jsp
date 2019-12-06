<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetweb.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	.input100{
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


</head>
<body>
<h1>Détail vente</h1>
<p>Nom article : ${article.nomArticle}</p>
<p>Description : ${article.descriptionArticle}</p>
<p>Catégorie : ${article.categorie.libelle}</p>
<p>Meilleure offre : ${article.prixVente}</p>
<p>Mise à prix : ${article.prixInitial}</p>

<p>Fin de l'enchère : ${date_fin}</p>
<p>Retrait ${article.retrait.rue}</p>
<p> ${article.retrait.codePostal} ${article.retrait.ville}</p>
<p>Vendeur </p>
<a href="<%=request.getContextPath()%>/ServletAfficherProfilVendeur?param=${article.utilisateur.noUtilisateur}"/> ${article.utilisateur.pseudo}</a>
	

<form class="form" action="<%=request.getContextPath()%>/ServletCreationCompte" method="post">

<div class="row">
			
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" style="display: flex">
								<label for="mise_prix"><span class="txt1 p-b-11" style="margin-right: 10px">Ma proposition :</span></label>
								<div class="wrap-input100  col-xs-7 col-sm-7 col-md-7 col-lg-7" style="height: 37px">
									<input type="number" class="input100"  name="mise_prix" id="mise_prix" min="0" max="10000" step="1" value="100">
									<span class="focus-input100"></span>
								</div>
							</div>
				<input type="submit" class="login100-form-btn" value="Enchérir" style="margin: auto">
</div>
					</form>
</body>
</html>