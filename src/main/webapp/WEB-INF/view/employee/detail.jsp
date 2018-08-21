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
		<h2 class="bg-info text-center text-white pb-4 mb-4">Mitarbeiter/in
			Detailansicht</h2>
		<form:form action="save" modelAttribute="employee" method="POST">
			<c:url var="deleteLink" value="delete">
				<c:param name="id" value="${employee.id}" />
			</c:url>
			<div class="form-row">
				<div class="col-6 form-group">
					<form:label path="firstname">Vorname (*)</form:label>
					<form:input type="text" class="form-control"
						cssErrorClass="form-control is-invalid" path="firstname" required />
					<form:errors cssClass="invalid-feedback" />
				</div>
				<div class="col-6 form-group">
					<form:label path="lastname">Nachname (*)</form:label>
					<form:input type="text" class="form-control"
						cssErrorClass="form-control is-invalid" path="lastname" required />
					<form:errors cssClass="invalid-feedback" />
				</div>
			</div>
			<div class="form-row">
				<div class="col-6 form-group">
					<form:label path="email">Email</form:label>
					<form:input type="text" class="form-control"
						cssErrorClass="form-control is-invalid" path="email" />
					<form:errors cssClass="invalid-feedback" />
				</div>
				<div class="col-6 form-group">
					<form:label path="team">Team</form:label>
					<form:select path="team" class="form-control">
						<form:option value="0" label="Team w�hlen" />
						<form:options items="${teams}" itemLabel="name" itemValue="id" />
					</form:select>
				</div>
			</div>
			<div class="form-row pb-4">
				<div class="col">
					<small>(*) Pflichtfeld</small>
				</div>
				<div class="col-auto ml-auto">
					<button type="submit" class="btn btn-info">Speichern</button>
					<a class="btn btn-info" href="${deleteLink}"
						onclick="if (!(confirm('Wollen Sie diesen Mitarbeiter l�schen?'))) return false">L�schen</a>
					<a href="list" class="btn btn-info">Abbrechen</a>
				</div>
			</div>
		</form:form>

	</div>

	<!-- jquery -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<!-- Popper -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<!-- bootstrap -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
	<script src="resources/js/custom.js"></script>
</body>

</html>
