<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="lang" value="fr" scope="page" />
<%@ include file="header.jsp"%>

<div class="container col-6 ">
	<div class="card border-dark">
		<div class="card-header h3">Connexion</div>
		<div class="card-body box shadow">
			<div class="form-group">
				<img src="resources/libraryImage.png" class="rounded img-thumbnail"
					width="610">
			</div>
			<form method="post" action= "" >
				<div class="form-group">
					<label>Entrez l'username:</label><input type="text" name="username"
						value="${username }" class="form-control" /> <span
						class="text-danger">${errUsername }</span>
				</div>
				<div class="form-group">
					<label>Entrez le mot de passe:</label><br /> <input type="password"
						name="pass" value="${pass }" class="form-control" /> <span
						class="text-danger">${errPass }</span>
				</div>
				<div class = "form-group">
					<span
						class="text-danger">${authErr }</span>
				</div>
				<div class="form-group">
					<input type="submit" value="Connexion" class="btn btn-success btn-" />
				</div>
			</form>

		</div>
	</div>
</div>
<%@ include file="footer.jsp"%>