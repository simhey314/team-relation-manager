<%-- Copyright 2018 Simon Heyden

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="de">

<head>
<%-- Meta Daten --%>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Team Pflege</title>

<%-- bootstrap --%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<%-- custom --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>

<body>
	<div class="container bg-light">
		<h1 class="bg-info text-center text-white mb-0 pb-4">Team Pflege</h1>
		<h2 class="bg-info text-center text-white pb-4 mb-4">Mitarbeiter/in</h2>
		<h3 class="mb-2">Suche</h3>
		<div class="row">
			<div class="col-md-4">
				<form:form action="search" method="POST">
					<div class="form-group">
						<div class="input-group">
							<input type="text" name="searchValue" class="form-control"
								placeholder="Vor- oder Nachnamen" value="${searchValue}">
							<div class="input-group-append">
								<button class="btn btn-info" type="submit">&rarr;</button>
							</div>
						</div>
						<small class="form-text text-muted">Leere Eingabe zeigt
							alle an.</small>
					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<a href="create" class="btn btn-info mb-4">Neue/r Mitarbeiter/in</a>
			</div>
		</div>
		<div class="row">
			<div class="col table-responsive-lg">
				<c:choose>
					<c:when test="${empty employees}">
						<p>
							<c:choose>
								<c:when test="${empty message}">Es sind keine Mitarbeiter/in vorhanden.</c:when>
								<c:otherwise>${message}</c:otherwise>
							</c:choose>
						</p>
					</c:when>
					<c:otherwise>
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
							<tbody>
								<c:forEach var="employeeEntity" items="${employees}">
									<c:url var="detailLink" value="detail">
										<c:param name="id" value="${employeeEntity.id}" />
									</c:url>
									<tr>
										<td>${employeeEntity.firstName}</td>
										<td>${employeeEntity.lastName}</td>
										<td>${employeeEntity.email}</td>
										<td>${employeeEntity.team.name}</td>
										<td><a href="${detailLink}" class="btn btn-info">Bearbeiten</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="row pb-2">
			<div class="col">
				<a href="${pageContext.request.contextPath}/team/list"
					class="btn btn-info">zur Teamübersicht</a>
			</div>
		</div>
	</div>


	<%-- jquery --%>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<%-- Popper --%>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<%-- bootstrap --%>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
</body>

</html>
