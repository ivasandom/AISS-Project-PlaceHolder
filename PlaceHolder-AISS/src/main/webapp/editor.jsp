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

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
        integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">

    <style>
        #tabs-container {
            background: #151515;
        }

        #tabs-container>ul {
            padding: 0;
        }

        #tabs-container>ul li {
            display: inline-block;
            background-color: #444;
            height: 40px;
            line-height: 40px;
            padding: 0 10px;
            margin-right: 3px;

        }

        #tabs-container>ul li:hover {
            background-color: #222;
            cursor: pointer;
        }

        #tabs-container>ul li a {
            font-family: Arial;
            font-size: 14px;
            color: #eee;
        }

        #tabs-container>ul li a:hover {
            text-decoration: none;
        }


        #git-changed-files {
            display: none;
        }


        #changed-files {
            padding: 0;
            list-style: none;
        }

        #changed-files>li {
            height: 30px;
            line-height: 30px;
            padding: 0 15px;
            margin-right: 3px;

        }

        #changed-files li:hover {
            background-color: #222;
            cursor: pointer;
        }

        #changed-files li a {
            font-family: Arial;
            font-size: 14px;
            color: #fff;
        }

        #changed-files li a:hover {
            text-decoration: none;
        }
    </style>
</head>


<body>


    <div id="editor">
        <div id="tabs-container">
            <ul id="tabs">

            </ul>
        </div>
        <div id="diffEditor" style="position:relative;width:100%;height:100%;"></div>
    </div>

    <div id="sidebar">
        <div id="fileTree" style="margin-top:20px;">

        </div>

        <div id="git-changed-files" style="padding:20px;">
            <h3 style="color:#fff;">Changed files</h3>
            <ul id="changed-files">
            </ul>
        </div>
        <div id="split-bar"></div>
    </div>

    <div id="menu" style="padding:10px;">
        <button class="btn btn-secondary" data-toggle="modal" data-target="#selectRepositoryModal"
            style="border-radius:50%;"><i class="fas fa-cog"></i></button>
    </div>


    <div id="task-git-manager">
        <h1>Guardar cambios</h1>
        <p>Guarda cambios en el repositorio y añade una entrada de tiempo del trabajo realizado.</p>
        <hr>
        
       

        <form id="commit-form" method="POST">
        
        	 <h3>Task & Time Entry</h3>
        	 
            <div class="form-group">
                <label for="task-select">Tarea</label>
                <select class="form-control" id="task-select">
                    <c:forEach items="${todoistTasks}" var="todoistTask">
                        <option value="${todoistTask.id}">
                            <c:if test="${empty todoistTask.config.taskName}">
                                ${todoistTask.content}
                            </c:if>
                            <c:if test="${not empty todoistTask.config.taskName}">
                                ${todoistTask.config.taskName}
                            </c:if>
                        </option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="row">
            	<div class="col-md-6">
            		<label for="started-time">Started time</label>
            		<input type="time" class="form-control" placeholder="8:00am" id="started-time">
            	</div>
            	<div class="col-md-6">
            		<label for="ended-time">Ended time</label>
            		<input type="time" class="form-control" placeholder="10:00am" id="ended-time">
            	</div>
            </div>
            <hr>
            
            <h3>Commit details</h3>
            
            <div class="form-group">
            	<label for="commit-message">Message</label>
            	<textarea class="form-control" id="commit-message" name="commitMessage" placeholder="Write description message..."></textarea>
            </div>
            
            <hr>
            

            <button type="submit" class="btn btn-primary container-fluid">TIME & COMMIT CHANGES</button>

            </div>

        </form>


    </div>

    <div class="modal fade" id="selectRepositoryModal" tabindex="-1" role="dialog"
        aria-labelledby="selectRepositoryModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Cargar repositorio</h5>

                </div>
                <div class="modal-body">

                    <form method="POST" id="formulario-repositorios" action="/editor/repository/tree">
                        <input type="hidden" name="projectId" value="${project.id}">
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">Repositorios enlazados a <a
                                    href="/projects?id=${project.id}" target="_blank">${project.name}</a>:</label>
                            <select class="form-control" name="repositoryHost" id="repository-host">
                                <c:if test="${not empty projectConfig.githubRepository}">
                                    <option value="GitHub">Github: ${projectConfig.githubRepository}</option>
                                </c:if>
                                <c:if test="${not empty projectConfig.gitlabRepository}">
                                    <option value="GitLab">Gitlab: ${projectConfig.gitlabRepository}</option>
                                </c:if>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary container-fluid"
                            id="boton-seleccionar-repo">Seleccionar</button>
                    </form>

                </div>

            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
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
        var trees;
        var codeEditor;
        var baseTree;

        $(document).ready(function () {

            $("#tabs").on("click", 'li', function (event) {
                EditorBlob.load($(this).data("bloburl"));
            });

            var sidebar = $("#fileTree");
            // cargar modal seleccionar repositorio
            $("#selectRepositoryModal").modal("show");

            $("#formulario-repositorios").on("submit", function (event) {
                event.preventDefault();
                $("#boton-seleccionar-repo").html(
                    "<img src='https://loading.io/assets/img/css/spinner.svg' height='22.5px' width='22.5px'> Loading..."
                );
                $.ajax({
                    url: "/editor/repository/tree",
                    data: $("#formulario-repositorios").serialize(),
                    success: function (response) {
                        $("#fileTree").replaceWith(
                            "<div id='fileTree' style='margin-top:20px;'></div>");

                        window.codeEditor = new CodeEditor();
                        
        				window.githost = $("#repository-host").val();
        				
        				var repo;
        				if (window.githost == "GitHub") {
        					repo = "${projectConfig.githubRepository}";
        					window.githostAccessToken = "${sessionScope['GitHub-token']}";
        				} else if (window.githost == "GitLab") {
        					repo = "${projectConfig.gitlabRepository}";
        					window.githostAccessToken = "${sessionScope['GitLab-token']}";
        				}
  
        				window.repositoryOwner = repo.split("/")[1];
        				window.repositoryName = repo.split("/")[2];

                        window.baseTree = response.sha;
                        window.trees = Tree.getTrees(response.tree);

                        $('#selectRepositoryModal').modal("hide");
                        $("#boton-seleccionar-repo").html("Seleccionar");

                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        switch (xhr.status) {
                            case 401:
                                alert("Inicia sesión e intenta de nuevo");
                                var win = window.open(
                                    'http://localhost:8090/login?provider=' + $(
                                        '#repository-host').val(), '_blank');
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
            // COMBINACION TECLAS ctrl + m
            // https://stackoverflow.com/questions/4604057/jquery-keypress-ctrlc-or-some-combo-like-that
            $(document).on('keydown', function (event) {
                if ((event.metaKey || event.ctrlKey) && (String.fromCharCode(event.which)
                        .toLowerCase() ===
                        'm')) {
                    var taskGitManager = $("#task-git-manager");
                    if (taskGitManager.hasClass("show-sidebar")) {
                        taskGitManager.removeClass("show-sidebar");
                        $("#editor").removeClass("show-sidebar");
                        $("#git-changed-files").css("display", "none");
                        $("#fileTree").css("display", "block");
                    } else {
                        taskGitManager.addClass("show-sidebar");
                        $("#editor").addClass("show-sidebar");
                        var changedFiles = codeEditor.getChangedFiles();
                        $("#changed-files").html("");
                        for (var url in changedFiles) {
                            $("#changed-files").append("<li onclick='codeEditor.showDiffEditor(\"" +
                                url + "\")'><a>" + changedFiles[url].text + "</a></li>")
                        }

                        $("#git-changed-files").css("display", "block");
                        $("#fileTree").css("display", "none");
                    }
                }
            });
        })
    </script>
    
    <script>
    	$("#commit-form").on("submit", function(event) {
    		event.preventDefault();
    		
    		$.ajax({
    			method: "POST",
    			data: JSON.stringify({
    				githost: window.githost,
    				owner: window.repositoryOwner,
    				repo: window.repositoryName,
    				startedTime: $("#started-time").val(),
    				endedTime: $("#ended-time").val(),
    				commitMessage: $("#commit-message").val(),
    				taskId: $("#task-select").val(),
    				projectId: "${project.id}",
    				tree: getJSONCommitChanges(),
    			}),
    			dataType: 'json',
    			contentType: "application/json",
    			url: "/editor/commit",
    			success: function(data){
    				
    				var win = window.open(data.commitUrl, '_blank');
                        if (win) {
                            //Browser has allowed it to be opened
                            win.focus();
                        }
    				alert("Time entry & commit added successfully.");
    			},
    			error: function(data){
    				alert("Error. Please try later.");
    			}
    		})
    	})
    </script>



    <script src="https://unpkg.com/monaco-editor@0.8.3/min/vs/loader.js"></script>
    <script src="/js/monaco.js"></script> -->



</body>

</html>