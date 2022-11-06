<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<div class="container">
	<div class="card border-dark card-bordered">
		<div class="card-header h3">Ajouter un nouveau jeux</div>
		<div class= "card-body S">
			<form action="saveGame.do" method="post">
				<div class="form-group">
					<label class="form-label">Entrez le titre:</label><input
						type="text" name="title" value="${game.title }"
						class="form-control" />
						<span class = "text-danger">${err.title }</span>
				</div>
				<div class="form-group">
					<label>Entrez le prix:</label><input type="text"
						name="price" value="${game.price }" class="form-control" />
						<span class = "text-danger">${err.price }</span>
				</div>
				<div class="form-group">
					<label>Entrez le nom de l'éditeur:</label><br /> <input type="text"
						name="publisher" value="${game.publisher }" class="form-control" />
						<span class = "text-danger">${err.publisher }</span>
				</div>
				<div class="form-group">
					<label>Entrez le nom la plateforme:</label><br /> <input type="text"
						name="plateforme" value="${game.plateforme }" class="form-control" />
						<span class = "text-danger">${err.plateforme }</span>
				</div>
				<input type="hidden" name="id" value="${game.id }"
					/> <input type="submit" value="Enregister" class="btn btn-primary"/>
			</form>
		</div>
	</div>
</div>


<%@ include file="footer.jsp"%>