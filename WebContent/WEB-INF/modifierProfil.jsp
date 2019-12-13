<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="fr.eni.projetweb.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modification du Profil</title>
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
	.input100{
		height: 30px;
	}
	
	.bouton {
		display: flex;
	  	flex-direction: column;
	  	justify-content: center;
	  	text-align: center;
	  	margin: auto;
	  	margin-left: 20px;
	}
	.boutons{
		margin-top: 50px;
	}	
		
	.wrap-login100{
		margin-top: 20px;
		width: 992px;
	}
	.wrap-input100{
		margin-bottom: 15px;
	}
</style>
<body>   <!-- col-xs-12 .col-sm-6 col-md-6 .col-lg-8 -->

<div class="topnav">
  		<a class="active" href="<%=request.getContextPath()%>/ServletAccueilConnecte">ENI Enchères</a>
  		<a href="<%=request.getContextPath()%>/ServletAccueilConnecte">Accueil</a>
	</div>

<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
						
						
				<div class="row">
					<div class="col-sm-12">
						<h1>
							<span class="login100-form-title">Modifier mon profil </span>
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
			
				<form class="form" action="<%=request.getContextPath()%>/ServletModifierProfil" method="post">
				
					<div class="row">
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
							<label for="pseudo"><span class="txt1 p-b-11">Pseudo :</span></label>
							<div class="wrap-input100">
								<input class="input100" type="text" name="pseudo" id="pseudo" required 
									 value="${user.pseudo}">
								<span class="focus-input100"></span>
							</div>
						</div>
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
							<label for="nom"><span class="txt1 p-b-11">Nom :</span></label>
							<div class="wrap-input100">
								<input type="text" class=" input100 form-control" name="nom" id="nom"  required 
										 value="${user.nom}"> 
								<span class="focus-input100"></span>
							</div>
						</div>
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
					</div>
					
					
					<div class="row">	
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
							<label for="prenom"><span class="txt1 p-b-11">Prénom :</span></label>
							<div class="wrap-input100">
								<input class="input100" type="text" name="prenom" id="prenom"  required
								 value="${user.prenom}">
								<span class="focus-input100"></span>
							</div>
						</div>
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
				
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
							<label for="email"><span class="txt1 p-b-11">E-mail :</span></label>
							<div class="wrap-input100">
								<input class="input100" type="email" name="email" id="email"  required
								 value="${user.email}">
								<span class="focus-input100"></span>
							</div>
						</div>
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
					</div>
					
					
					<div class="row">	
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
							<label for="telephone"><span class="txt1 p-b-11"> Téléphone (format: 0708985421) :</span></label>
							<div class="wrap-input100">
								<input class="input100" type="tel" name="telephone" id="telephone"
								value="${user.telephone}">
								<span class="focus-input100"></span>
							</div>
						</div>
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
					
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
							<label for="rue"><span class="txt1 p-b-11"> Rue :</span></label>
							<div class="wrap-input100 ">
								<input class="input100" type="text" name="rue" id="rue" required
										value="${user.rue}">
								<span class="focus-input100"></span>
							</div>
						</div>
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
					</div>
					
					
					<div class="row">	
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
							<label for="codepostal"><span class="txt1 p-b-11">Code postal (format : 44000) :</span></label>
								<div class="wrap-input100">
									<input class="input100" type="text" name="codepostal" id="codepostal" required
											 value="${user.codePostal}">
									<span class="focus-input100"></span>
								</div>
							</div>
							<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
		
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
							<label for="ville"><span class="txt1 p-b-11">Ville : </span></label>
								<div class="wrap-input100">
									<input class="input100" type="text" name="ville" id="ville" required
										 value="${user.ville}">
									<span class="focus-input100"></span>
								</div>
						</div>
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
					</div>
					
					
					
					<div class="row">
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
							<label for="currentpassword"><span class="txt1 p-b-11">Mot de passe actuel :</span></label>
								<div class="wrap-input100">
									<input class="input100" type="password" name="currentpassword" id="currentpassword" required>
									<span class="focus-input100"></span>
								</div>
						</div>
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"></div>
					
					</div>
					
					
					<div class="row">	
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
							<label for="password"><span class="txt1 p-b-11">Nouveau mot de passe :</span></label>
								<div class="wrap-input100 validate-input m-b-36" data-validate="Le mot de passe est requis">
									<input class="input100" type="password" name="password" id="password" required>
									<span class="focus-input100"></span>
								</div>
						</div>
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>

						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
							<label for="confirmation"><span class="txt1 p-b-11"> Confirmation :</span></label>
							<div class="wrap-input100 validate-input m-b-36" data-validate="Confirmez le mot de passe">
								<input class="input100" type="password" name="confirmation" id="confirmation"  required>
								<span class="focus-input100"></span>
							</div>
						</div>
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>	
					</div>
					
					<div class="row boutons" >	
						<div class="flex-sb-m w-full p-b-48" >
						<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"></div>
							<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
								<input type="submit" class="login100-form-btn bouton" value="Enregistrer">
							</div>	
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>	
						<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">			
							<div class="row">
								<a class="login100-form-btn bouton" href="<%=request.getContextPath()%>/supprimer"> 
								Supprimer
								</a>
							</div>
						</div>
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>	
						<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">			
							<div class="row">
								<a class="login100-form-btn bouton" href="<%=request.getContextPath()%>/ServletProfil"> 
								Retour
								</a>
							</div>
						</div>
						<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"></div>
						
						
						</div>	
							
					</div>					  
				</form>
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