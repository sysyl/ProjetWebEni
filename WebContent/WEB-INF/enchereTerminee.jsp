<%@page import="fr.eni.projetweb.bll.RetraitManager"%>
<%@page import="fr.eni.projetweb.bll.MethodesUtiles"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetweb.messages.LecteurMessage" %>
<%@ page import="fr.eni.projetweb.bo.Article" %>
<%@ page import="fr.eni.projetweb.bo.Enchere" %>
<%@ page import="fr.eni.projetweb.bo.Utilisateur" %>
<%@ page import="fr.eni.projetweb.bll.MethodesUtiles" %>

<%@ page import="java.util.List"%>
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
<title>Vente terminée</title>

<style>
	.input100{
		height: 40px;
	}
	
	
	.wrap-login100{
		width : 700px;
	}
	.container-login100{
		align-items: flex-start;
	}
	
	.row{
		height: 30px;
	}

	.credits{
		margin-top: 30px;
		margin-bottom: 20px;
	}

	input[type="submit"]{
		min-width: 0px;
		height: 40px;
		width: 120px;
		
	}
	a.login100-form-btn{
		min-width: 0px;
		width: 120px;
		
		height: 40px;
	}
	
	.bouton {
		display: flex;
	  	flex-direction: row;
	  	justify-content: center;
	  	text-align: center;
	}	
	
	.erreurs{
		margin-top: 20px;
	
	}
	.gagnant{
	font-size: 16px;
	}


</style>


</head>
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

					<div class="row p-b-55">
						<span class="login100-form-title"> Resultats d'enchère </span> 		
						<hr>
					</div>
					
					
					<div class="row" style="margin-bottom: 20px">	
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 bouton" >
						<%
						List<Enchere> listeEncheres = (List<Enchere>) request.getAttribute("listeEncheres");
						Enchere meilleureEnchere = (Enchere) request.getAttribute("meilleureEnchere");
						Article article = (Article)  request.getAttribute("article");
						Utilisateur currentUser = (Utilisateur) request.getAttribute("utilisateur");
						
						// personne n'a fait d'enchere
						if(meilleureEnchere == null){
							if(currentUser != null){
						%>
							<span class="txt1 p-b-11 gagnant"> Personne n'a remporté l'enchère </span>
						<%
						
						} }// qqnn a gagne
						else if(meilleureEnchere != null){
							// si l'utilisateur courant est le gagnant
							if(currentUser != null){
								
								if(meilleureEnchere.getUtilisateur().getNoUtilisateur() == currentUser.getNoUtilisateur()){
									%>
										<span class="txt1 p-b-11 gagnant"> Vous a remporté l'enchère </span>
									<%
									}
									//si utilisateur courant n'est pas le gagnat
									else if(meilleureEnchere.getUtilisateur().getNoUtilisateur() != currentUser.getNoUtilisateur()){
									%>
										<span class="txt1 p-b-11 gagnant"> <%=meilleureEnchere.getUtilisateur().getPseudo() %> a remporté l'enchère </span>
									<%
									}
	
							}
						}
						%>
			
				</div>
				
					</div>
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<span class="txt1 p-b-11">Nom article : </span></div>
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">${article.nomArticle}</div>
					</div>
					
					<div class="row p-b-65">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
							<span class="txt1 p-b-11">Description : </span>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<textarea cols="35" rows="3" disabled style="background-color: white; resize: none" ><%=article.getDescriptionArticle().trim() %></textarea>
					</div>
				</div>
					
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<span class="txt1 p-b-11">Catégorie : </span></div>

						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">${article.categorie.libelle}</div>
					</div>		
					
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<span class="txt1 p-b-11">Meilleure offre : </span></div>

						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">${article.prixVente}
							<%
							
							if(currentUser != null){
								if(article.getUtilisateur().getNoUtilisateur() == currentUser.getNoUtilisateur()){
									if(meilleureEnchere != null){
								%> par <a href="<%=request.getContextPath()%>/ServletAfficherProfilVendeur?id=<%=meilleureEnchere.getUtilisateur().getNoUtilisateur() %>"><%=meilleureEnchere.getUtilisateur().getPseudo() %></a>
									
								<%	
								}}
							}
							%>
						
						
						</div>
					</div>					
					
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<span class="txt1 p-b-11">Mise à prix : </span></div>
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">${article.prixInitial}</div>
					</div>	
					
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<span class="txt1 p-b-11">Fin de l'enchère : </span></div>
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">${date_fin}</div>
					</div>
					
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<span class="txt1 p-b-11">Vendeur : </span></div>

						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<a href="<%=request.getContextPath()%>/ServletAfficherProfilVendeur?id=${article.utilisateur.noUtilisateur}"/> ${article.utilisateur.pseudo}</a>
						</div>
					</div>
					
					
					<%	
					if(meilleureEnchere != null){
						if(currentUser != null){
							if(meilleureEnchere.getUtilisateur().getNoUtilisateur() == currentUser.getNoUtilisateur()){
						%>
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<span class="txt1 p-b-11">E-mail : </span></div>

						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">${article.utilisateur.email}</div>
					</div>
					<%}}}%>
					
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
							<span class="txt1 p-b-11">Retrait </span>
						</div>

						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">${article.retrait.rue}</div>
					</div>
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5"></div>
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">${article.retrait.codePostal} ${article.retrait.ville}</div>
					
					</div>
					
					<div class="row">
						<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
						<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<span class="txt1 p-b-11">Statut retrait : </span></div>

						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"><%=MethodesUtiles.statutRetraitString(article.getRetrait().isStatut()) %></div>
					</div>
					
					
					<div class="row bouton">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 bouton" >
						<%
						if(meilleureEnchere != null){
							if(currentUser != null){
							// si l'utilisateur courant est le vendeur 
							// -> liste d'encheres
							if(article.getUtilisateur().getNoUtilisateur() == currentUser.getNoUtilisateur()){
							%>
						<div class="row m-b-20 m-t-20 m-l-3 m-r-3">
							<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4" >
								<form class="form" action="<%=request.getContextPath()%>/ServletEncheresSurArticle" method="post">
									<input type="hidden" name="noArticle" value="<%=article.getNoArticle()%>">
									<input type="submit" class="login100-form-btn" value="Détails" style="margin: auto">
								</form>
							</div>
						</div>
							<%}}}%>
					
						<% if(meilleureEnchere != null){
							if(currentUser != null){
							// si l'utilisateur courant est le vendeur  :
							//-> retrait (visible que si on y clique la 1ere fois -> update credit vendeur en meme temps)
							// -> si statut retrait est true -> on ne voit plus de bouton
							if(article.getUtilisateur().getNoUtilisateur() == currentUser.getNoUtilisateur()){		
								if(!article.getRetrait().isStatut()){		
								
							%>
							<div class="row m-b-20 m-t-20  m-l-3 m-r-3">
								<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4" >	
									<form class="form" action="<%=request.getContextPath()%>/ServletRetraitEffectue" method="post">
										<input type="hidden" name="noArticle" value="<%=article.getNoArticle()%>">
										<input type="hidden" name="noUtilisateur" value="<%=article.getUtilisateur().getNoUtilisateur()%>">
										<input type="hidden" name="prixVente" value="<%=article.getPrixVente()%>">
										<input type="submit" class="login100-form-btn" value="Retrait" style="margin: auto">
									</form>
								</div>
							</div>
					
					<%}}}} %>
					
						<div class="row  m-t-20 m-b-20 m-t-20 m-l-20 m-r-5 p-l-30">
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 bouton" style="text-align: center;">
	
							<%if (session.getAttribute("idUtilisateur") != null) {
							%>
							<div class="row " >
								<a class="login100-form-btn bouton" href="<%=request.getContextPath()%>/ServletAccueilConnecte">Retour</a>	
							</div>
							<%}
							else{
							%>
							<div class="row ">
								<a class="login100-form-btn bouton" href="<%=request.getContextPath()%>/ServletAccueil">Retour</a>
							</div>			
							<%}%>
						</div>
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