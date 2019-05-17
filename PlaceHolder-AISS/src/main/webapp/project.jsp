<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


		<div class="jumbotron">
			<div class="container">
				<h1 class="display-4 mt-5">Proyecto: ${project.name}
					<button class="btn btn-danger btn-sm" id="delete-confirm"
						data-url="/projects/delete?id=${project.id}">Eliminar proyecto</button>
					<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#updateProject"
						data-url="/projects/update?id=${project.id}" data-whatever="@getbootstrap">Actualizar proyecto</h1>

			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-9">
					<h2>Tareas</h2>

					<ul id="lista-tareas">
						<c:forEach items="${projectTasks}" var="task">
							<li>${task.content}</li>
							<button type="button" class="btn btn-danger btn-sm delete-task-confirm"
								data-url="/tasks/delete?id=${task.id}">Eliminar tarea</button></h1>
							<button type="button" class="btn btn-success btn-sm update-task-confirm" data-toggle="modal"
								data-target="#update-task-modal" data-whatever="@getbootstrap">Actualizar tarea</button>
						</c:forEach>
					</ul>
					<form id="ajax-add-task" method="POST" action="/tasks/create">
						<div class="input-group mb-3">
							<input type="hidden" name="projectId" value="${project.id}">
							<input type="text" name="content" class="form-control" placeholder="Título tarea">
							<div class="input-group-append">
								<button class="btn btn-primary" type="submit">Añadir</button>
							</div>
						</div>
					</form>
				</div>
				<div class="col-md-3">
					<h1>Project configuration</h1>
					<ul>
						<li>Todoist: ${projectConfig.todoistProjectId}</li>
						<li>GitHub: ${projectConfig.gitlabRepository}</li>
						<li>GitLab: ${projectConfig.githubRepository}</li>
						<li>BitBucket: ${projectConfig.bitbucketRepository}</li>
					</ul>
				</div>
			</div>
			<hr>
		</div>

	</main>

	<footer class="container">
		<p>Grupo Placeholder - Arquitectura e Integración de Sistemas Software</p>
		<a class='api' href='/docs/index.html'> Ver documentación de la API</a>
	</footer>

	<div class="modal fade" id="update-task-modal" tabindex="-1" role="dialog" aria-labelledby="updateTaskModal"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Actualizar tarea</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form method="get" action="/tasks/update">
						<input type="hidden" id="task-id" name="id">
						<div class="form-group">
							<label for="task-name" class="col-form-label">Nombre:</label>
							<input type="text" class="form-control" name="name" id="task-name">
						</div>

					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Actualizar</button>
				</div>
			</div>
		</div>
	</div>



	<div class="modal fade" id="updateProject" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Nuevo nombre para el proyecto:</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">Nombre:</label>
							<input type="text" class="form-control" id="recipient-name">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
					<button type="button" class="btn btn-primary">Actualizar</button>
				</div>
			</div>
		</div>
	</div>

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