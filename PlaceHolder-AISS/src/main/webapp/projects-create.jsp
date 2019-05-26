<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="google-site-verification" content="_NmfhoumLzs-Z5B1TMC85vXgfkkzMEjuwNivJBGcPqQ" />
	<title>Placeholder - Inicio</title>

	<!-- Bootstrap cdn -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>

<body>

	<jsp:include page="header.jsp"></jsp:include>

	<main role="main">

		<div class="container mt-5">

			<h1>Create project</h1>

			<form action="/projects/create" method="POST">

				<h3>Basic Information</h3>

				<div class="form-group">
					<label id="client-id">Client</label>
					<select id="client-id" name="client_id" class="form-control">
						<c:forEach items="${clients}" var="client">
							<option value="${client.id}">${client.name}</option>
						</c:forEach>
					</select>
				</div>

				<div class="form-group">
					<label id="client-id">Project Name</label>
					<input type="text" class="form-control" name="name" id="project-name" placeholder="Facebook">
				</div>


				<div class="btn-group-toggle mb-3" data-toggle="buttons">
					<label class="btn btn-secondary">
						<input type="checkbox" autocomplete="off" name="is_billable"> Is billable
					</label>
				</div>

				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label id="bill-by">Bill by</label>
							<select id="bill-by" name="bill_by" class="form-control">
								<option value="Project">Project</option>
								<option value="Tasks">Tasks</option>
								<option value="People">People</option>
								<option value="none">none</option>
							</select>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label id="budget-by">Budget by</label>
							<select id="budget-by" name="budget_by" class="form-control">
								<option value="project">Hours Per Project</option>
								<option value="project_cost">Total Project Fees</option>
								<option value="task">Hours Per Task</option>
								<option value="task_fees">Fees Per Task</option>
								<option value="person">Hours Per Person</option>
								<option value="none">No Budget</option>
							</select>
						</div>
					</div>
				</div>

				<h3>Todoist project configuration</h3>
				<div class="form-group">
					<label for="project-todoist-config">Todoist Project</label>
					<select name="todoistProject" id="project-todoist-config" class="form-control">
						<option value="new_project">Create project automatically</option>
						<option disabled>────────────────────</option>
						<c:forEach items="${todoistProjects}" var="project">
							<option value="${project.id}">${project.name}</option>
						</c:forEach>
					</select>
					<small class="form-text text-muted">Enlaza con un proyecto nuevo o con uno existente.</small>
				</div>

				<h3>Enlazar repositorios al proyecto</h3>

				<div class="form-group">
					<label for="repo">Seleccionar repositorio</label>
					<div class="alert alert-info">Lo sentimos, debes estar logueado en <strong>GitHub, GitLab o
							Bitbucket</strong> para enlazar un repositorio.</div>
				</div>

				<div class="row">

					<div class="col-md-4">
						<div class="form-group">
							<label for="">Github Repository</label>
							<input type="text" name="githubRepository" placeholder="e.g. /username/repository-name"
								class="form-control">
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="">GitLab Repository</label>
							<input type="text" name="gitlabRepository" placeholder="e.g. /username/repository-name"
								class="form-control">
						</div>
					</div>
				</div>

				<button type="submit" class="btn btn-primary">Create project <strong
						id="projectNameButton"></strong></button>

			</form>
		</div>

	</main>

	<footer class="container mt-5">
		<p>Grupo Placeholder - Arquitectura e Integración de Sistemas Software</p>
		<a class='api' href='/docs/index.html'> Ver documentación de la API</a>
	</footer>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous">
	</script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous">
	</script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous">
	</script>

	<script>
		$("#project-name").on("input", function () {
			$("#projectNameButton").text($(this).val());
		})
	</script>

</body>

</html>