<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Editor</title>
    <link rel="stylesheet" href="/css/jstree.css" />
    <link rel="stylesheet" href="/css/editor.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.1/jquery.min.js"></script>
</head>

<body>

    <div id="editor"></div>
    <div id="sidebar">
        <div id="fileTree" style="margin-top:20px;">

        </div>
        <div id="split-bar"></div>
    </div>
    <div id="menu">
        <img src="http://tentube.weebly.com/uploads/6/4/4/0/64406749/4455140.png?458" style="width: 40px;height:40px;padding:20px 10px;">
    </div>

	<div id="container">
		<c:forEach items="${repositoryTrees}" var="tree">
		<div class="tree" style="display:none" data-path="<c:out value="${tree.path}" />" data-type="<c:out value="${tree.type}" />"
			data-name="<c:out value="${tree.name}" />" data-size="<c:out value="${tree.size}" />" data-url="<c:out value="${tree.url}" />" ></div>
		</c:forEach>
	</div>

    <script src="/js/tabs.js"></script>
    <script src="/js/editor.main.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>

    <script>
        jQuery(function ($) {
            var sidebar = $("#fileTree");
            sidebar.jstree({
                'core': {
                    'data': fileExplorer
                }
            })
        });
    </script>
    
       <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.29.2/sweetalert2.all.js"></script>
    <script>
 
 
 Swal.fire({
  title: '¡Bienvenido!',
  text: 'El editor de repositorios está en desarrollo. De momento sólo puedes visualizar archivos en el editor.',
  type: 'info',
  confirmButtonText: 'De acuerdo'
})
 </script>
    
    
    <script src="https://unpkg.com/monaco-editor@0.8.3/min/vs/loader.js"></script>
    <script src="/js/monaco.js"></script>
    

    
</body>

</html>