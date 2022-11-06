<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="lang" value="fr" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bibliotèque page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</head>
<body>

	<nav
		class="navbar navbar-expand navbar-dark navbar-inverse text-light text-uppercase bg-dark">
		<div class="container-fluid">
			<a href="#" class="navbar-brand"> <!-- Logo Image --> <img
				src="resources/logo.png"
				width="50" alt="" class="d-inline-block align-middle mr-2"> <!-- Logo Text -->
				<span class="text-uppercase font-weight-bold">GamesMarket </span>
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
				aria-controls="navbarNavDropdown" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<c:if test="${ sessionScope['username'] != null}">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="index.do">Liste des jeux</a></li>
						<li class="nav-item"><a class="nav-link" href="addGame.do">Ajouter
								un jeux</a></li>
						<li class="nav-item"><a class="nav-link"
							href="plateformes.do">Plateformes </a></li>
						<li class="nav-item"><a class="nav-link" href="liste.do">Liste
								des jeux par XSLT </a></li>
					</ul>
					<ul class="navbar-nav ml-auto">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> ${sessionScope['username'] } </a>

							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="logout.do">Se déconnecter</a>
							</div></li>
					</ul>
				</c:if>


			</div>
		</div>
	</nav>
	<br>
	<br>