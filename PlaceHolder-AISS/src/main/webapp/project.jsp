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
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>

<body>
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		<a class="navbar-brand" href="#">Placeholder</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
			aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarsExampleDefault">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" target="_blank" href="https://github.com/ivasandom/AISS-Project-PlaceHolder/">Github repository</a>
				</li>
				<li class="nav-item dropdown">
        			<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          				Tokens oauth
        			</a>
        			<div class="dropdown-menu" aria-labelledby="navbarDropdown">
          				<a class="dropdown-item" href="/login?provider=GitLab">GitLab - ${sessionScope["GitLab-token"]}</a>
          				<a class="dropdown-item" href="/login?provider=GitHub">GitHub - ${sessionScope["GitHub-token"]}</a>
          				<a class="dropdown-item" href="/login?provider=Bitbucket">Bitbucket - ${sessionScope["Bitbucket-token"]}</a>
          				<div class="dropdown-divider"></div>
          				<a class="dropdown-item" href="/login?provider=Todoist">Todoist - ${sessionScope["Todoist-token"]}</a>
        			</div>
      			</li>
      			<c:if test="${not empty sessionScope['GitHub-token']}">
      			<li class="nav-item dropdown">
        			<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          				Mis repositorios
        			</a>
        			<div class="dropdown-menu" aria-labelledby="navbarDropdown">
        				<c:forEach items="${repositories}" var="repository">
							<a class="dropdown-item" href="/editor?owner=${repository.owner.login}&repo=${repository.name}">
      							${repository.fullName}
      						</a>
      					</c:forEach>
        			</div>
      			</li>
      			</c:if>
      			
      			<c:if test="${not empty sessionScope['Todoist-token']}">
      			<li class="nav-item dropdown">
        			<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          				Mis proyectos
        			</a>
        			<div class="dropdown-menu" aria-labelledby="navbarDropdown">
        				<c:forEach items="${myProjects}" var="project">
							<a class="dropdown-item" href="/projects?id=${project.id}">
      							${project.name}
      						</a>
      					</c:forEach>
      					<div class="dropdown-divider"></div>
      					<a class="dropdown-item" href="#">Create new project</a>
        			</div>
      			</li>
      			</c:if>
      			
			</ul>
		</div>
	</nav>

	<main role="main mt-5 pt-5">
		
			
		<div class="jumbotron">
			<div class="container">
  			<h1 class="display-4 mt-5">Proyecto: ${project.name}
  			<button class="btn btn-danger btn-sm" id="delete-confirm" data-url="/projects/delete?id=${project.id}">Eliminar proyecto</button></h1>
  			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<h2>Tareas</h2>
	
					<ul id="lista-tareas">
						<c:forEach items="${projectTasks}" var="task">
							<li>${task.content}</li>
							<button class="btn btn-danger btn-sm" data-url="/tasks/delete?id=${task.id}">Eliminar tarea</button></h1>
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
				<div class="col-md-6"></div>
			</div>
			<hr>
		</div>

	</main>

	<footer class="container">
		<p>Grupo Placeholder - Arquitectura e Integración de Sistemas Software</p>
		<a class='api' href='/docs/index.html'> Ver documentación de la API</a>
	</footer>
	
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.29.2/sweetalert2.all.js"></script>
	
	<script>
		// Crear una nueva tarea AJAX
		var formularioTarea = $("#ajax-add-task");
		var listaTareas = $("#lista-tareas");
		$(formularioTarea).submit(function(event){
			event.preventDefault();
			var datosFormulario = $(formularioTarea).serialize();
			$.ajax({
				url: "/tasks/create",
				type: "POST",
				data: datosFormulario,
				success: function(response){
					listaTareas.append("<li data-id='" + response.id + "'>" + response.content + "</li>");
				},
				error: function(response){
					alert("Error creando tarea");
				}
			});
		})
		
		// Confirmar eliminación proyecto
		var confirmar = $("#delete-confirm");
		$(confirmar).click(function(){
			Swal.fire({
				  title: '¿Estás seguro que quieres borrar el proyecto?',
				  text: "Se borrarán todas las tareas y configuraciones.",
				  type: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Eliminar',
				  cancelButtonText: 'Cancelar'
				}).then((result) => {
				  if (result.value) {
				    location.href = confirmar.attr("data-url");
				  }
				})
		})
	</script>
</body>
</html>