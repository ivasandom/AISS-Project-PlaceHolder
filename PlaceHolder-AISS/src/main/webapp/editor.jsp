<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Editor - ${project.name}</title>
    <link rel="stylesheet" href="/css/jstree.css" />
    <link rel="stylesheet" href="/css/editor.css">
    <!-- Bootstrap cdn -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">

</head>


<body>


    <div id="editor"></div>
    <div id="sidebar">
        <div id="fileTree" style="margin-top:20px;">

        </div>
        <div id="split-bar"></div>
    </div>
    <div id="menu" style="padding:10px;">
        	<button class="btn btn-secondary" data-toggle="modal" data-target="#selectRepositoryModal" style="border-radius:50%;"><i class="fas fa-cog"></i></button>

    </div>

    <div id="container">
        <c:forEach items="${repositoryTrees}" var="tree">
            <div class="tree" style="display:none" data-path="<c:out value=" ${tree.path}" />" data-type="
            <c:out value="${tree.type}" />"
            data-name="
            <c:out value="${tree.name}" />" data-size="
            <c:out value="${tree.size}" />" data-url="
            <c:out value="${tree.url}" />" >
    </div>
    </c:forEach>
    </div>

    <div id="task-git-manager">
        <h1>Seleccionar tarea</h1>
        <p>Selecciona la tarea en la que quieres trabajar</p>
        <hr>

        <div class="form-group">
            <label for="task-select">Tarea</label>
            <select class="form-control" id="task-select">
                <option value="hola">El workbook</option>
            </select>
        </div>
        <div class="form-group">
            <label for="task-select">Más detalles..</label>
            <select class="form-control" id="task-select">
                <option value="hola">Ver videos de vegetta</option>
            </select>
        </div>
		<hr>
		<div class="row mt-4">
			<div class="col-md-4">
				<button class="btn btn-secondary container-fluid">TIMER</button>
			</div>
			<div class="col-md-8">
				<button class="btn btn-primary container-fluid">COMMIT AND PUSH</button>
			</div>
		</div>
		
        
    </div>

    <div class="modal fade" id="selectRepositoryModal" tabindex="-1" role="dialog" aria-labelledby="selectRepositoryModalLabel"
        aria-hidden="true" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Cargar repositorio</h5>
                    
                </div>
                <div class="modal-body">
                	
                    <form method="POST" id="formulario-repositorios" action="/editor/repository/tree">
                    	<input type="hidden" name="projectId" value="${project.id}">
	                    <div class="form-group">
	                    	<label for="recipient-name" class="col-form-label">Repositorios enlazados a <a href="/projects?id=${project.id}" target="_blank">${project.name}</a>:</label>
	                    	<select class="form-control" name="repositoryHost" id="repository-host">
                                <c:if test="${not empty projectConfig.githubRepository}">
                                    <option value="GitHub">Github: ${projectConfig.githubRepository}</option>
                                </c:if>
                                <c:if test="${not empty projectConfig.gitlabRepository}">
                                    <option value="GitLab">Gitlab: ${projectConfig.gitlabRepository}</option>
                                </c:if>
                                <c:if test="${not empty projectConfig.bitbucketRepository}">
                                    <option value="Bitbucket">Bitbucket: ${projectConfig.bitbucketRepository}</option>
                                </c:if>
	                        </select>	                           
	                        </div>
	                    <button type="submit" class="btn btn-primary container-fluid" id="boton-seleccionar-repo">Seleccionar</button>
                    </form>

                </div>
               
            </div>
        </div>
    </div>

    <script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous">
    </script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous">
    </script>

    <script src="/js/tabs.js"></script>

    <script src="/js/editor.main.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>

    <script>
        $(document).ready(function () {
            var sidebar = $("#fileTree");
			// cargar modal seleccionar repositorio
            $("#selectRepositoryModal").modal("show");
			
			$("#formulario-repositorios").on("submit", function(event){
				event.preventDefault();
				$("#boton-seleccionar-repo").html("<img src='https://loading.io/assets/img/css/spinner.svg' height='22.5px' width='22.5px'> Loading...");
				$.ajax({
					url: "/editor/repository/tree",
					data: $("#formulario-repositorios").serialize(),
					success: function(response){
						$("#fileTree").replaceWith("<div id='fileTree' style='margin-top:20px;'></div>");
						loadRepositoryTree(response.tree);
						$('#selectRepositoryModal').modal("hide");
						$("#boton-seleccionar-repo").html("Seleccionar");
					},
					error: function (xhr, ajaxOptions, thrownError){
					    switch (xhr.status) {
					        case 401:
					             alert("Inicia sesión e intenta de nuevo");
					             var win = window.open('http://localhost:8090/login?provider=' + $('#repository-host').val(), '_blank');
								 if (win) {
								    //Browser has allowed it to be opened
								    win.focus();
								 }
					    }
					}
				})
			})
        })
    </script>

    <script>
        $(document).ready(function () {
            // COMBINACION TECLAS
            // https://stackoverflow.com/questions/4604057/jquery-keypress-ctrlc-or-some-combo-like-that
            $(document).on('keydown', function (event) {
                if ((event.metaKey || event.ctrlKey) && (String.fromCharCode(event.which)
                    .toLowerCase() ===
                        'm')) {
                    var taskGitManager = $("#task-git-manager");
                    if (taskGitManager.hasClass("show-sidebar")) {
                    	taskGitManager.removeClass("show-sidebar");
                        $("#editor").removeClass("show-sidebar");
                    } else {
                    	taskGitManager.addClass("show-sidebar");
                        $("#editor").addClass("show-sidebar");
                    }
                }
            });
        })
    </script>



    <script src="https://unpkg.com/monaco-editor@0.8.3/min/vs/loader.js"></script>
    <script src="/js/monaco.js"></script> -->



</body>

</html>