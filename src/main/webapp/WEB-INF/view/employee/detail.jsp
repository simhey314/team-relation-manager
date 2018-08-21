<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="de">

<head>
<!-- Meta Daten -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Team Pflege</title>

<!-- bootstrap -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<!-- custom -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>

<body>
	<div class="container bg-light">
		<h1 class="bg-info text-center text-white mb-0 pb-4">Team Pflege</h1>
		<h2 class="bg-info text-center text-white pb-4 mb-4">Mitarbeiter/in Detailansicht</h2>
		<form:form action="save" modelAttribute="customer" method="POST">
			<div class="form-row">
				<div class="col-6 form-group">
					<label for="firstname">Vorname (*)</label>
					<input type="text" class="form-control" id="firstname" required>
				</div>
				<div class="col-6 form-group">
					<label for="lastname">Nachname (*)</label>
					<input type="text" class="form-control" id="lastname" required>
				</div>
			</div>
			<div class="form-row">
				<div class="col-6 form-group">
					<label for="email">Email</label>
					<input type="text" class="form-control" id="email">
				</div>
				<div class="col-6 form-group">
					<label for="team">Team</label>
					<select id="team" class="form-control">
						<option>Super Hackers</option>
						<option>Software Helden</option>
						<option>Superhelden</option>
					</select>
				</div>
			</div>
			<div class="form-row pb-4">
				<div class="col"><small>(*) Pflichtfeld</small></div>
				<div class="col-auto ml-auto">
					<button class="btn btn-info">Speichern</button>
					<button class="btn btn-info">L�schen</button>
					<button class="btn btn-info">Abbrechen</button>
				</div>
			</div>
		</form:form>

	</div>

	<!-- jquery -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<!-- Popper -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<!-- bootstrap -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	<script src="resources/js/custom.js"></script>
</body>

</html>
