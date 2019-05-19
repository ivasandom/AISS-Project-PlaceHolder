<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<title>Placeholder - ${project.name}</title>

	<!-- Bootstrap cdn -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


</head>

<body>

	<jsp:include page="header.jsp"></jsp:include>

	<main role="main mt-5 pt-5">

	
		<div class="jumbotron mb-0" style='background:linear-gradient(rgba(255,255,255,.5), rgba(255,255,255,.8)), url("https://previews.123rf.com/images/karenr/karenr1506/karenr150600007/40831894-blue-and-white-striped-gingham-tile-pattern-repeat-background-that-is-seamless-and-repeats.jpg")'>
			<div class="container">
				<h1 class="display-4">${project.name}</h1>
				<a class="btn btn-dark btn-sm" href="/editor?project=${project.id}">Abrir en editor</a>
			</div>
		</div>

		<div class="container">

			<ul class="nav nav-tabs mt-4" id="myTab" role="tablist">
				<li class="nav-item">
					<a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home"
						aria-selected="true">Resumen proyecto</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" id="configuration-tab" data-toggle="tab" href="#configuration" role="tab" aria-controls="configuration"
						aria-selected="false">Configuracion</a>
				</li>
			</ul>
			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
					<h2 class="my-4">Tareas</h2>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Task</th>
								<th>Assignments</th>
								<th>Options</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${harvestTasks}" var="taskAssignment">
								<tr data-toggle="collapse" id="taskAssignment-${taskAssignment.id}" data-target=".subTask-${taskAssignment.id}">
									<td>${taskAssignment.task.name}</td>
									<td>0</td>
									<td>-</td>
								</tr>
							</c:forEach>
							
							<tr data-toggle="collapse" id="taskAssignment-other" data-target=".subTask-other">
								<td>Other</td>
								<td>${fn:length(todoistTasks)}</td>
								<td></td>
							</tr>

							<c:forEach items="${todoistTasks}" var="todoistTask">
								<tr class="table-info collapse subTask-other" id="task-${todoistTask.id}">
									<td>${todoistTask.content}</td>
									<td>-</td>
									<td><button class="btn btn-danger btn-sm delete-task" data-id="${todoistTask.id}">Eliminar</button></td>
								</tr>
							</c:forEach>
				
						</tbody>
					</table>
					
					
					<form id="ajax-add-task" method="POST" action="/tasks/create">
						<div class="input-group mb-3">
							<input type="hidden" name="projectId" value="${projectConfig.todoistProjectId}">
							<input type="text" name="content" class="form-control" placeholder="Estudiar aiss...">
							<div class="input-group-append">
								<select class="form-control" name="assignment">
									<option value="">Other</option>
									<c:forEach items="${harvestTasks}" var="taskAssignment">
										<option value="${taskAssignment.task.id}" disabled>${taskAssignment.task.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="input-group-append">
								<button class="btn btn-primary" type="submit">Añadir</button>
							</div>
						</div>
					</form>
				</div>
				<div class="tab-pane fade" id="configuration" role="tabpanel" aria-labelledby="configuration-tab">
					<h2 class="my-4">Configuración</h2>
					
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Service</th>
								<th>Id/Name</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Todoist</td>
								<td>${projectConfig.todoistProjectId}</td>
							</tr>
							<tr>
								<td>GitHub</td>
								<td>${projectConfig.githubRepository}</td>
							</tr>
							<tr>
								<td>GitLab</td>
								<td>${projectConfig.gitlabRepository}</td>
							</tr>
							<tr>
								<td>BitBucket</td>
								<td>${projectConfig.bitbucketRepository}</td>
							</tr>
						</tbody>
					</table>
					
					<button class="btn btn-danger btn-sm" id="delete-confirm"
						data-url="/projects/delete?id=${project.id}">Eliminar</button>
					<a class="btn btn-info btn-sm" href="/projects/update?id=${project.id}">Editar</a>
					
				</div>
			</div>

			
		</div>

	</main>

	<footer class="container my-5">
		<hr>
		<p>Grupo Placeholder - Arquitectura e Integración de Sistemas Software</p>
		<a class='api' href='/docs/index.html'> Ver documentación de la API</a>
	</footer>



	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous">
	</script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous">
	</script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.29.2/sweetalert2.all.js"></script>

	<script src="/js/projects.js"></script>

</body>

</html>