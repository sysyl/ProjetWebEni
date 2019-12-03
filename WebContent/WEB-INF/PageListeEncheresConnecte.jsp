<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste des enchères</title>
</head>
<body>

	<h1>ENI - Enchères</h1>
	<div>
		<a href="<%request.getContextPath();%>/connexion">Enchères</a> 
		<a href="<%request.getContextPath();%>/connexion">Vendre un article</a>
		<a href="<%request.getContextPath();%>/connexion">Mon profil</a> 
		<a href="<%request.getContextPath();%>/connexion">Déconnexion</a>
	</div>

	<div class="input-group md-form form-sm form-1 pl-0">
		<div class="input-group-prepend">
			<span class="input-group-text purple lighten-3" id="basic-text1"><i
				class="fas fa-search text-white" aria-hidden="true"></i></span>
		</div>
		<input class="form-control my-0 py-1" type="text"
			placeholder="Le nom de l'article contient" aria-label="Search">

		<a href="" class="bouton">Rechercher</a>
	</div>

	<label for="select-categories">Catégories :</label>
	<select name="thelist" onChange="combo(this, 'theinput')">
		<option>Toutes</option>
		<option>Ameublement</option>
		<option>Informatique</option>
		<option>Sport & Loisirs</option>
		<option>Vêtement</option>
	</select>

	<div>
		<div>
			<input type="radio" id="achats" name="achats/ventes" value="achats"
				checked> <label for="achats">Achats</label> <input
				type="checkbox" id="enchereouverte" name="enchereouverte"
				value="enchereouverte"> <label for="enchereouverte">enchères
				ouvertes</label> <input type="checkbox" id="enchereencours"
				name="enchereencours" value="enchereencours"> <label
				for="enchereencours">mes enchères en cours</label> <input
				type="checkbox" id="enchereremporte" name="enchereremporte"
				value="enchereremporte"> <label for="enchereremporte">mes
				enchères remportées</label>
		</div>
		<div>
			<input type="radio" id="ventes" name="achats/ventes" value="ventes">
			<label for="ventes">Mes ventes</label> <input type="checkbox"
				id="venteencours" name="venteencours" value="venteencours">
			<label for="venteencours">mes ventes en cours</label> <input
				type="checkbox" id="ventenondebute" name="ventenondebute"
				value="ventenondebute"> <label for="ventenondebute">ventes
				non débutées</label> <input type="checkbox" id="ventetermine"
				name="ventetermine" value="ventetermine"> <label
				for="ventetermine">ventes terminées</label>

		</div>

	</div>

</body>
</html>