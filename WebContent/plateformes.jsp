<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="lang" value="fr" scope="page" />
<%@ include file="header.jsp"%>

<div class="container">
	<div class="card border-dark">
		<div class="card-header h3">Liste des Jeux</div>
		<div class="card-body">
			
					${query }
		
		</div>
	</div>

	<%@ include file="footer.jsp"%>