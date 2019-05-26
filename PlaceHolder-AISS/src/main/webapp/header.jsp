<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="isAuthenticated" value="${not empty sessionScope['Harvest-token'] and not empty sessionScope['Todoist-token']}"></c:set>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
	<a class="navbar-brand" href="/" style="letter-spacing:5px;">PLACEHOLDER</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
		aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
				<a class="nav-link" href="/">Inicio <span class="sr-only">(current)</span></a>
			</li>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">
					Repositorios
				</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="/login?provider=GitLab">Conectar con GitLab - ${sessionScope["GitLab-token"]}</a>
					<a class="dropdown-item" href="/login?provider=GitHub">Conectar con GitHub - ${sessionScope["GitHub-token"]}</a>
				</div>
			</li>
			<c:if test="${isAuthenticated}">
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Mis proyectos
					</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<c:forEach items="${myProjects}" var="project">
							<a class="dropdown-item" href="/projects?id=${project.id}">
								${project.name}
							</a>
						</c:forEach>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="/projects/create">Create new project</a>
					</div>
				</li>
			</c:if>
		</ul>
		<ul class="navbar-nav">
			<c:if test="${isAuthenticated}">
				<li class="nav-item dropdown">
					<a href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
						class="nav-link dropdown-toggle" id="navbarDropdown">
						<img src="${profile.avatarUrl}" width="25px" height="25" style="border-radius:5px;">
						<strong class="ml-2">${profile.fullName}</strong>
					</a>

					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a href="/logout" class="dropdown-item">Logout</a>
					</div>
				</li>
			</c:if>
			<c:if test="${not isAuthenticated}">
				<li class="nav-item">
					<a href="/login" class="btn btn-primary" style="font-weight:500;font-size:0.9em;">ENTRAR CON HARVEST & TODOIST</a>
				</li>
			</c:if>
		</ul>

		</ul>
	</div>
</nav>