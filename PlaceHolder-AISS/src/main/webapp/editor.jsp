<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editor</title>
</head>
<body>
<div id="container">

<c:forEach items="${repositoryTrees}" var="tree">
	<p><c:out value="${tree.path}" /></p>
</c:forEach>



</div>

</body>
</html>