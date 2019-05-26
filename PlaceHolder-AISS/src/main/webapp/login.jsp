<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="google-site-verification" content="_NmfhoumLzs-Z5B1TMC85vXgfkkzMEjuwNivJBGcPqQ" />
	<title>Iniciar sesión</title>

	<!-- Bootstrap cdn -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>

<style>
	html,
	body {
		height: 100%;
		text-align: center;
	}

	.service-logo {
		height: 30px;
		width: 30px;
		margin-bottom: 10px;
	}

	.btn-harvest {
		color: white;
		background-color: #f66622;
		border-color: #f66622;
		margin-bottom: 40px;
	}

	.btn-todoist {
		color: white;
		background-color: #e44331;
		border-color: #e44331;
		margin-bottom: 40px;
	}

	.navbar-brand {
		margin: 0 auto;
	}
</style>

<body data-gr-c-s-loaded="true" cz-shortcut-listen="true">

	<nav class="navbar navbar-expand-md navbar-dark bg-dark">
		<a class="navbar-brand" href="/" style="letter-spacing:5px;">PLACEHOLDER</a>
	</nav>

	<main role="main">

		<div class="jumbotron"
			style="background:linear-gradient(rgba(255,255,255,.5), rgba(255,255,255,.8)), url(&quot;https://previews.123rf.com/images/karenr/karenr1506/karenr150600007/40831894-blue-and-white-striped-gingham-tile-pattern-repeat-background-that-is-seamless-and-repeats.jpg&quot;)">
			<div class="container">
				<h2 class="mb-">Por favor, inicie sesión en los <br>siguientes servicios para continuar</h2>
			</div>
		</div>

		<div class="container py-5">
			<div class="row" style="max-width:850px;margin:0 auto;">
				<div class="col-md-6">
					<img class="service-logo"
						src="https://www.getharvest.com/assets/press/harvest-logo-icon-77a6f855102e2f85a7fbe070575f293346a643c371a49ceff341d2814e270468.png">

					<h2>Harvest</h2>
					<p>Harvest es una herramienta de seguimiento de tiempo basada en la web desarrollada y lanzada por
						Iridesco.</p>
					<c:if test="${not empty sessionScope['Harvest-token']}">
						<p><a class="btn btn-harvest btn-lg container-fluid disabled" href="#" role="button">✓ You are
								authenticated</a></p>
					</c:if>
					<c:if test="${empty sessionScope['Harvest-token']}">
						<p><a class="btn btn-harvest btn-lg container-fluid" href="/login?provider=Harvest"
								role="button">Sign in with Harvest</a></p>
					</c:if>

				</div>
				<div class="col-md-6">
					<img class="service-logo" src="https://businessoptimizer.org/wp-content/uploads/Todoist-Logo-1.png">
					<h2>Todoist</h2>
					<p>Todoist es una aplicación simple, pero poderosa, de listas de tareas que ayuda a las personas a organizarse.</p>
					<c:if test="${not empty sessionScope['Todoist-token']}">
						<p><a class="btn btn-danger btn-lg container-fluid disabled" href="#" role="button">✓ You are
								authenticated</a></p>
					</c:if>
					<c:if test="${empty sessionScope['Todoist-token']}">
						<p><a class="btn btn-danger btn-lg container-fluid" href="/login?provider=Todoist"
								role="button">Sign in with Todoist</a></p>
					</c:if>
				</div>

			</div>
			<hr>
		</div>
	</main>
	
</body>
</html>