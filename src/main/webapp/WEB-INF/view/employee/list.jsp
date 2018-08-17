<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="de">

<head>
	<!-- Meta Daten -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>Team Pflege</title>

	<!-- bootstrap -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<!-- custom -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>

<body>
	<div class="container bg-light">
		<h1 class="bg-info text-center text-white mb-0 pb-4">Team Pflege</h1>
		<h2 class="bg-info text-center text-white pb-4 mb-4">Übersicher aller Angestellten</h2>
		<h3 class="mb-2">Suche</h3>
		<div class="row">
			<div class="col-4">
				<form>
					<div class="form-group">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="Vor- oder Nachnamen">
							<div class="input-group-append">
								<button class="btn btn-info" type="submit">&rarr;</button>
							</div>
						</div>
						<small class="form-text text-muted">Leere Eingabe zeigt alle an.</small>
					</div>
				</form>
			</div>
		</div>
		<form>
			<div class="form-row">
				<div class="col form-group">
					<button class="btn btn-info">Neue/n Mitarbeiter/in</button>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<table class="table table-striped">
						<thead class="thead-inverse">
							<tr>
								<th>Vorname</th>
								<th>Nachname</th>
								<th>Email</th>
								<th>Team</th>
								<th>Aktion</th>
							</tr>
						</thead>
						<tbody class="">
							<tr>
								<td>Simon</td>
								<td>Heyden</td>
								<td>email@mail.de</td>
								<td>Java Developer</td>
								<td><button class="btn btn-info">Bearbeiten</button></td>
							</tr>
							<tr>
								<td>Simon</td>
								<td>Heyden</td>
								<td>email@mail.de</td>
								<td>Java Developer</td>
								<td><button class="btn btn-info">Bearbeiten</button></td>
							</tr>
							<tr>
								<td>Simon</td>
								<td>Heyden</td>
								<td>email@mail.de</td>
								<td>Java Developer</td>
								<td><button class="btn btn-info">Bearbeiten</button></td>
							</tr>
							<tr>
								<td>Simon</td>
								<td>Heyden</td>
								<td>email@mail.de</td>
								<td>Java Developer</td>
								<td><button class="btn btn-info">Bearbeiten</button></td>
							</tr>
							<tr>
								<td>Simon</td>
								<td>Heyden</td>
								<td>email@mail.de</td>
								<td>Java Developer</td>
								<td><button class="btn btn-info">Bearbeiten</button></td>
							</tr>
							<tr>
								<td>Simon</td>
								<td>Heyden</td>
								<td>email@mail.de</td>
								<td>Java Developer</td>
								<td><button class="btn btn-info">Bearbeiten</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</form>
		<h3 class="mb-4">Team</h3>
		<form>
			<div class="form-row">
				<div class="col-3 form-group">
					<button class="btn btn-info">Neues Team</button>
				</div>
				<div class="col-3 form-group">
					<select id="team" class="form-control">
						<option>Super Hackers</option>
						<option>Software Helden</option>
						<option>Superhelden</option>
					</select>
					<small>Team auswählen zum Bearbeiten</small>
				</div>
				<div class="col-3 form-group">
					<button class="btn btn-info">Bearbeiten</button>
				</div>
			</div>
		</form>
	</div>


	<!-- jquery -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<!-- Popper -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<!-- bootstrap -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
</body>

</html>
