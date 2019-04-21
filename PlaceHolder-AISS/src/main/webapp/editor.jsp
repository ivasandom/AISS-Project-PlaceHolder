<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editor</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
</head>
<body>
<div id="container">


<c:forEach items="${repositoryTrees}" var="tree">
<div style="display:none" data-path="<c:out value="${tree.path}" />" data-type="<c:out value="${tree.type}" />"
	data-name="<c:out value="${tree.name}" />" data-size="<c:out value="${tree.size}" />" class="tree"></div>
</c:forEach>



</div>

 <div id="jstree_demo_div"></div>

<script src="/js/editor.main.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
<script>
$('#jstree_demo_div').jstree({
	'core': {
		'data': fileExplorer
	}
});
</script>
</body>
</html>