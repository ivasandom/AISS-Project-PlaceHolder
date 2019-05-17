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
	
	<jsp:include page="header.jsp"></jsp:include>

	<main role="main">
		
		<div class="jumbotron">
			<div class="container">
  			<h1 class="display-4 mt-5">Bienvenido a Placeholder!</h1>
  			<p class="lead">Placeholder es una herramienta web de gestión de proyectos de desarrollo software. Podrás gestionar los proyectos y tareas que tienes asignados a los repositorios, así como editar los repositorios sin necesidad de tener un editor instalado.</p>
  			<hr class="my-4">
  			<p class="lead">
    			<a class="btn btn-primary btn-lg" href="https://github.com/ivasandom/AISS-Project-PlaceHolder" role="button">Ver codigo</a>
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