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
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>

<c:if test="${not empty sessionScope['Todoist-token']}">
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">New project</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="/projects/create" method="post">
      <div class="modal-body">
        
          <div class="form-group">
            <label for="project-name" class="col-form-label">Project name:</label>
            <input type="text" class="form-control" id="project-name" name="name">
          </div>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Create</button>
      </div>
      </form>
    </div>
  </div>
</div>
</c:if>

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
      					<a class="dropdown-item" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Create new project</a>
        			</div>
      			</li>
      			</c:if>
      			
			</ul>
		</div>
	</nav>

	<main role="main">
		
		<div class="jumbotron">
			<div class="container">
  			<h1 class="display-4 mt-5">Bienvenido a Placeholder!</h1>
  			<p class="lead">Placeholder es una herramienta web de gestión de proyectos de desarrollo software. Podrás gestionar y controlar las horas que dedicas a cada tarea, así como editar los repositorios sin necesidad de tener un editor instalado.</p>
  			<hr class="my-4">
  			<p class="lead">
    			<a class="btn btn-primary btn-lg" href="#" role="button">Ver más</a>
  			</p>
  			</div>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<h2>Todoist</h2>
					<p>Gestiona tus proyectos y tareas gracias a la plataforma <a href="https://todoist.com/?lang=es">Todoist</a></p>
					<p><a class="btn btn-danger" href="/login?provider=Todoist" role="button">Sign in with Todoist</a></p>
				</div>
				<div class="col-md-3">
					<h2>Github</h2>
					<p>Edita tus repositorios de GitHub y mucho más.</p>
					<p><a class="btn btn-dark" href="/login?provider=GitHub" role="button">Sign in with GitHub</a></p>
				</div>
				<div class="col-md-3">
					<h2>Gitlab</h2>
					<p>Edita tus repositorios de GitLab de gitlab.com o tu propio servidor.</p>
					<p><a class="btn btn-warning" href="/login?provider=GitLab" role="button">Sign in with GitLab</a></p>
				</div>
				<div class="col-md-3">
					<h2>Bitbucket</h2>
					<p>Edita tus repositorios de Bitbucket, la solución git de Atlassian.</p>
					<p><a class="btn btn-primary" href="/login?provider=Bitbucket" role="button">Sign in with Bitbucket</a></p>
				</div>
			</div>
			<hr>
		</div>

	</main>

	<footer class="container">
		<p>Grupo Placeholder - Arquitectura e Integración de Sistemas Software</p>
		<a class='api' href='/docs/index.html'> Ver documentación de la API</a>
	</footer>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>