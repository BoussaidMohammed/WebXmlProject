<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="lang" value="fr" scope="page" />
<%@ include file="header.jsp"%>

<div class="container">
	<div class="card border-dark">
		<div class="card-header h3">Liste des Jeux</div>
		<div class="card-body">
			<table class = "table table-bordered table-striped table-hover text-center">
				<tr class = "table table-header bg-dark text-white">
					<td>Identificateur</td>
					<td>Titre</td>
					<td>Prix</td>
					<td>Éditeur</td>
					<td>Platforme</td>
					<td colspan="2">Operation</td>

				</tr>
				<c:forEach items="${games}" var="g">
					<tr>
						<td>${g.id }</td>
						<td>${g.title }</td>
						<td>${g.price }</td>
						<td>${g.publisher }</td>
						<td>${g.plateforme } </td>
						<td><a onclick="return confirm('êtes vous sure:')" class = "btn  btn-sm btn-danger btn-block" href="deleteGame.do?id=${g.id }">Supprimer</a></td>
						<td><a class = "btn btn-sm btn-warning btn-block" href="updateGame.do?id=${g.id }">Modifer</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	</div>

<%@ include file="footer.jsp"%>