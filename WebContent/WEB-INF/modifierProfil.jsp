<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<body>   <!-- col-xs-12 .col-sm-6 col-md-6 .col-lg-8 -->
		
<div class="limiter">				
	<div class="container-login100">
		<div class="container bootstrap snippet" style="background-color: #fff">
		  <div class="p-l-85 p-r-85 p-t-55 p-b-55"> 
						
				<div class="row">
					<div class="col-sm-12">
						<h1>
							<span class="login100-form-title">Modifier mon Profil </span>
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
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label for="pseudo"><span class="txt1 p-b-11">Pseudo :</span></label>
							<div class="wrap-input100 validate-input m-b-36" data-validate="Pseudo est requis">
								<input class="input100" type="text" name="pseudo" id="pseudo" required 
									  	value="<%=request.getParameter("pseudo")!=null?request.getParameter("nom"):""%>">
								<span class="focus-input100"></span>
							</div>
						</div>
						
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label for="nom"><span class="txt1 p-b-11">Nom :</span></label>
							<div class="wrap-input100 validate-input m-b-36" data-validate="Nom est requis">
								<input type="text" class=" input100 form-control" name="nom" id="nom"  required 
										value="<%=request.getParameter("nom")!=null?request.getParameter("nom"):""%>"> 
								<span class="focus-input100"></span>
							</div>
						</div>
					</div>
					
					
					<div class="row">	
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label for="prenom"><span class="txt1 p-b-11">Prénom :</span></label>
							<div class="wrap-input100 validate-input m-b-36" data-validate="Prenom est requis">
								<input class="input100" type="text" name="prenom" id="prenom"  required
								value="<%=request.getParameter("prenom")!=null?request.getParameter("prenom"):""%>">
								<span class="focus-input100"></span>
							</div>
						</div>
				
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label for="email"><span class="txt1 p-b-11">E-mail :</span></label>
							<div class="wrap-input100 validate-input m-b-36" data-validate="Email est requis">
								<input class="input100" type="email" name="email" id="email"  required
								value="<%=request.getParameter("email")!=null?request.getParameter("email"):""%>">
								<span class="focus-input100"></span>
							</div>
						</div>
					</div>
					
					
					<div class="row">	
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label for="telephone"><span class="txt1 p-b-11"> Téléphone :</span></label>
							<div class="wrap-input100 validate-input m-b-36">
								<input class="input100" type="tel" name="telephone" id="telephone"
								value="<%=request.getParameter("telephone")!=null?request.getParameter("telephone"):""%>">
								<span class="focus-input100"></span>
							</div>
						</div>
					
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label for="rue"><span class="txt1 p-b-11"> Rue :</span></label>
							<div class="wrap-input100 validate-input m-b-36" data-validate="Le nom de la rue est requis">
								<input class="input100" type="text" name="rue" id="rue" required
										value="<%=request.getParameter("rue")!=null?request.getParameter("rue"):""%>">
								<span class="focus-input100"></span>
							</div>
						</div>
					</div>
					
					
					<div class="row">	
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label for="codepostal"><span class="txt1 p-b-11">Code postal :</span></label>
								<div class="wrap-input100 validate-input m-b-36"
									data-validate="Code postal est requis">
									<input class="input100" type="text" name="codepostal" id="codepostal" required
											value="<%=request.getParameter("codepostal")!=null?request.getParameter("codepostal"):""%>">
									<span class="focus-input100"></span>
								</div>
							</div>
		
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label for="ville"><span class="txt1 p-b-11">Ville : </span></label>
								<div class="wrap-input100 validate-input m-b-36" data-validate="Le nom de la ville est requis" >
									<input class="input100" type="text" name="ville" id="ville" required
										value="<%=request.getParameter("ville")!=null?request.getParameter("ville"):""%>">
									<span class="focus-input100"></span>
								</div>
						</div>
					</div>
					
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label for="currentpassword"><span class="txt1 p-b-11">Mot de passe actuel :</span></label>
								<div class="wrap-input100 validate-input m-b-36" data-validate="Le mot de passe est requis">
									<input class="input100" type="password" name="currentpassword" id="currentpassword" required>
									<span class="focus-input100"></span>
								</div>
						</div>
					
					
					<div class="row">	
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label for="password"><span class="txt1 p-b-11">Nouveau mot de passe :</span></label>
								<div class="wrap-input100 validate-input m-b-36" data-validate="Le mot de passe est requis">
									<input class="input100" type="password" name="password" id="password" required>
									<span class="focus-input100"></span>
								</div>
						</div>

						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label for="confirmation"><span class="txt1 p-b-11"> Confirmation :</span></label>
							<div class="wrap-input100 validate-input m-b-36" data-validate="Confirmez le mot de passe">
								<input class="input100" type="password" name="confirmation" id="confirmation"  required>
								<span class="focus-input100"></span>
							</div>
						</div>	
					</div>
					
					<div class="row" >	
						<div class="flex-sb-m w-full p-b-48" >
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<input type="submit" class="login100-form-btn bouton" value="Enregistrer">
							</div>		
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">			
							<div class="row">
								<a class="login100-form-btn bouton" href="<%=request.getContextPath()%>/supprimer"> 
								Supprimer mon compte
								</a>
							</div>
						</div>
						</div>		
					</div>						  
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