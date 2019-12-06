<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetweb.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nouvelle vente</title>
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
	
	.boutonUpload {
	
		display : inline;
		margin-left: 20px;
		
		padding: 5px 30px 5px 30px;
		border-radius: 12px;
		
	  	
	  
	  	
	}
	.wrap-input100{
		margin-bottom: 10px;
	}
	
	textarea {
	  width: 100%;
	  height: 100px !important;
	  padding: 12px 20px;
	  box-sizing: border-box;
	  resize: vertical; /* on peut aggrandir textearea que verticalement */
	}
	
	fieldset {
   
    border:  1px solid #ccc;
    
    padding:  12px;

}
</style>
<body>   <!-- col-xs-12 .col-sm-6 col-md-6 .col-lg-8 -->
		
<div class="limiter">				
	<div class="container-login100">
		 <div class="container" style="background-color: #fff"> 
		  <div class=" p-l-85 p-r-85 p-t-55 p-b-55"> 
						
				<div class="row">
					<div class="col-sm-12">
						<h1>
							<span class="login100-form-title">Nouvelle vente</span>
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
				
				
				<div class="row">
					<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 ">photo</div>
					<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9 ">
					
					<form class="form" action="<%=request.getContextPath()%>/ServletEnregistrementVente" method="post">
					
						<div class="row">
							<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
								<label for="nom_article"><span class="txt1 p-b-11">Article* :</span></label>
								<div class="wrap-input100 ">
									<input class="input100" type="text" name="nom_article" id="nom_article" required autofocus>
									<span class="focus-input100"></span>
								</div>
							</div>
						</div>
						
						
						
						<div class="row">
								<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
								<label for="description"><span class="txt1 p-b-11">Description* :</span></label>
									<div class="wrap-input100 ">
									<textarea class="input100"  name="description" id="description">
										
									</textarea>
									<span class="focus-input100"></span>
								</div>
							</div>
						</div>
						
						
						
					<div class="row" style="margin: 10px 0 10px 0">
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5" >
							<span class="txt1 p-b-11"> Catégorie* :</span> 
							<span class="btn-show-pass"></span> 
							<select name="categorie"
								onChange="combo(this, 'theinput')" style="margin-left : 15px; margin-bottom : 25px ;width: 150px;">
								<c:if test="${!empty listCategories}">
									<c:forEach var="c" items="${listCategories}">
										<option value="${c.noCategorie}" >${c.libelle}</option>
									</c:forEach>
								</c:if>
							</select> <span class="focus-input100"></span>
						</div>	
					
					
						
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" style="display: flex">
								<label for="mise_prix"><span class="txt1 p-b-11" style="margin-right: 10px">Mise à prix* :</span></label>
								<div class="wrap-input100  col-xs-7 col-sm-7 col-md-7 col-lg-7" style="height: 37px">
									<input type="number" class="input100"  name="mise_prix" id="mise_prix" min="0" max="10000" step="1" value="150">
									<span class="focus-input100"></span>
								</div>
							</div>
					</div>
					
					
					<div class="row">	
					<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10" style="margin-bottom: 25px; text-align: center;">
							<a class="login100-form-btn boutonUpload" href="<%=request.getContextPath()%>/ServletAccueil"> 
										Uploader une photo</a>
						
					</div>
					</div>
					
					
						
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<label for="date_debut"><span class="txt1 p-b-11">Début de l'enchère* :</span></label>
							<div class="wrap-input100  col-xs-7 col-sm-7 col-md-7 col-lg-7">
								<input class="input100" type="datetime-local" name="date_debut" id="date_debut" required>
								<span class="focus-input100"></span>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<label for="date_fin"><span class="txt1 p-b-11">Fin de l'enchère* :</span></label>
							<div class="wrap-input100 col-xs-7 col-sm-7 col-md-7 col-lg-7">
								<input class="input100" type="datetime-local" name="date_fin" id="date_fin" required>
								<span class="focus-input100"></span>
							</div>
						</div>
					</div>	
					
					
					<div class="row">
						<div class="col-xs-11 col-sm-11 col-md-11 col-lg-11">
							
								 <fieldset style="margin: 10px 0 25px 0">
								 <legend class="txt1 p-b-11" style="font-size: 16px">
								 Retrait
								 </legend>
								 	<div class="row">
										<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
											<label for="rue"><span class="txt1 p-b-11">Rue* :</span></label>
												<div class="wrap-input100 ">
													<input class="input100" type="text" name="rue" id="rue" required 
														  	value="${utilisateur.rue}">
													<span class="focus-input100"></span>
												</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
											<label for="code_postal"><span class="txt1 p-b-11">Code postal* :</span></label>
												<div class="wrap-input100 ">
													<input class="input100" type="text" name="code_postal" id="code_postal" required 
														  	value="${utilisateur.codePostal}">
													<span class="focus-input100"></span>
												</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
											<label for="ville"><span class="txt1 p-b-11">Ville* :</span></label>
												<div class="wrap-input100 ">
													<input  class="input100" type="text" name="ville" id="ville" required 
														  	value="${utilisateur.ville}">
													<span class="focus-input100"></span>
												</div>
										</div>
									</div>
						</fieldset>
						</div>
					</div>
					
					<div class="row" >	
						<div class="flex-sb-m w-full p-b-48">
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"  >
								<input type="submit" class="login100-form-btn" value="Enregistrer" style="margin: auto">
							</div>		
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">			
							<div class="row">
								<a class="login100-form-btn bouton" href="<%=request.getContextPath()%>/ServletAccueilConnecte"> 
								Annuler
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
	

</div>


	</div>
<body>



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