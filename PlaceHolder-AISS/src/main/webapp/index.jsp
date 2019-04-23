<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<head>
    <meta name="google-site-verification" content="_NmfhoumLzs-Z5B1TMC85vXgfkkzMEjuwNivJBGcPqQ" />
</head>
<title>PlaceHolder</title>
<style>
body {
	text-align: center;
	padding: 150px;
}
h1 {
	font-size: 50px;
}
body {
	font: 20px Helvetica, sans-serif;
	color: #333;
}
article {
	display: block;
	text-align: left;
	width: 650px;
	margin: 0 auto;
}
a {
	color: #dc8100;
	text-decoration: none;
}
a:hover {
	color: #333;
	text-decoration: none;
}
ul {
	display: block;
	list-style-type: decimal;
	margin-top: 1em;
	margin-bottom: 1em;
	margin-left: 0;
	margin-right: 0;
	padding-left: 40px;
}
.btn-github {
	background: #333;
	padding:15px;
	border-radius:5px;
	color:#fff;
}
.btn-github:hover {
	color:#ddd;
}
.btn-trello {
	background: #000080;
	padding:15px;
	border-radius:5px;
	color:#fff;
}
.btn-trello:hover {
	color:#000080;
}
</style>

<h1>PlaceHolder</h1>


<a class="btn-github" href="/login?provider=GitLab">Sign in with GitLab</a>
<a class="btn-github" href="/login?provider=GitHub">Sign in with GitHub</a>
<a class="btn-trello" href="/login?provider=Trello">Sign in with Trello</a>
<a class="btn-trello" href="/login?provider=Todoist">Sign in with Todoist</a>

<h2>Repositorios Github</h2>
<ul>
	<c:forEach items="${repositories}" var="repository">
		<li>
			<a href="<c:out value='${repository.svnUrl}' />" target="_blank">
				<c:out value='${repository.fullName}' />
			</a>
			-
			<a href="/editor?owner=<c:out value='${repository.owner.login}' />&repo=<c:out value='${repository.name}' />">
				Abrir en editor online
			</a>
		</li>
	</c:forEach>
</ul>



<h2>Proyectos Todoist</h2>
<ul>
	<c:forEach items="${projects}" var="project">
		<li>
			<a href="<c:out value='${project.svnUrl}' />" target="_blank">
				<c:out value='${project.fullName}' />
			</a>
		</li>
	</c:forEach>
</ul>
    <h2> Tokens de OAuth </h2>
        <ul>
			<li><strong>GitLab:</strong> <c:out value='${sessionScope["GitLab-token"]}' /> </li>
			<li><strong>GitHub:</strong> <c:out value='${sessionScope["GitHub-token"]}' /> </li>
			<li><strong>Todoist:</strong> <c:out value='${sessionScope["Todoist-token"]}' /> </li>
		</ul>
  </body>
</html>
