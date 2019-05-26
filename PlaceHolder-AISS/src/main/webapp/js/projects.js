		// Crear una nueva tarea AJAX
		var formularioTarea = $("#ajax-add-task");
		var obj = $("#taskAssignment-other");
		var listaTareas = $(".subTask-other");
		// Crear una tarea
		$(formularioTarea).submit(function (event) {
			event.preventDefault();
			var datosFormulario = $(formularioTarea).serialize();
			$.ajax({
				url: "/tasks/create",
				type: "POST",
				data: datosFormulario,
				success: function (response) {

					var subTask = {
						id: response.id,
						name: JSON.parse(response.content).task_name,
						parentId: JSON.parse(response.content).task_parent,
					}

					subtasks.push(subTask);
					addSubTask(subTask);
					updateSubTaskCount(JSON.parse(response.content).task_parent)


					obj.collapse("show");

				},
				error: function (response) {
					alert("Error creando tarea");
				}
			});
		})

		// Confirmar eliminación proyecto
		var confirmar = $("#delete-confirm");
		$(confirmar).click(function () {
			Swal.fire({
				title: '¿Estás seguro que quieres borrar el proyecto?',
				text: "Se borrarán todas las tareas y configuraciones.",
				type: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: 'Eliminar',
				cancelButtonText: 'Cancelar'
			}).then((result) => {
				if (result.value) {
					location.href = confirmar.attr("data-url");
				}
			})
		})


		// https://stackoverflow.com/questions/1359018/how-do-i-attach-events-to-dynamic-html-elements-with-jquery
		$("body").on("click", "button.delete-task", function (event) {
			var taskId = $(this).data("id");
			deleteSubTask(taskId);
		})

		$('body').on('click', "button.update-task", function (event) {
			var taskId = $(this).data("id");
			var newContent = $("#taskInput-" + taskId).val();
			var data = {
				"taskId": taskId,
				"content": newContent
			};
			$.ajax({
				url: "/tasks/update",
				method: "POST",
				data: data,
				success: function (response) {
					alert("Tarea actualizada");
				},
				error: function (response) {
					alert("Error actualizando tarea");
				}
			})
		})


		function addSubTask(subTask) {
			if (subTask.parentId) {
				$("#taskAssignment-" + subTask.parentId).after("<tr class='table-info collapse subTask-" + subTask.parentId + "' id='task-" + subTask.id + "'>" +
					"<td><input type='text' class='form-control' value='" + subTask.name + "' id='taskInput-" + subTask.id + "' style='border:0; background:transparent;'></td>" +
					"<td>-</td>" +
					"<td><div class='btn-group'><button class='btn btn-danger btn-sm delete-task' data-id='" + subTask.id + "'>Eliminar</button>" +
					"<button class='btn btn-primary btn-sm update-task-form' data-id='" + subTask.id + "'>Actualizar</button>" +
					"</div></td>");
			} else {
				$("#taskAssignment-other").after("<tr class='table-info collapse subTask-other' id='task-" + subTask.id + "'>" +
					"<td><input type='text' class='form-control' value='" + subTask.name + "' id='taskInput-" + subTask.id + "' style='border:0; background:transparent;'></td>" +
					"<td>-</td>" +
					"<td><div class='btn-group'><button class='btn btn-danger btn-sm delete-task' data-id='" + subTask.id + "'>Eliminar</button>" +
					"<button class='btn btn-primary btn-sm update-task-form' data-id='" + subTask.id + "'>Actualizar</button>" +
					"</div></td>");
			}
		}

		function deleteSubTask(subTaskId) {
			var subTask = subtasks.find(x => x.id == subTaskId);
			var deleteUrl = "/tasks/delete?id=" + subTask.id;

			$.ajax({
				url: deleteUrl,
				success: function (response) {
					delete subtasks[subtasks.indexOf(subTask)];
					var trow = $("#task-" + subTask.id);
					trow.remove();
					updateSubTaskCount(subTask.parentId);
				},
				error: function (response) {
					alert("Error eliminando la tarea");
				}
			})


		}

		function updateSubTaskCount(parentTaskId) {

			if (parentTaskId) {
				$("#task-count-" + parentTaskId).html(subtasks.filter(x => x.parentId == parentTaskId).length);
			} else {
				$("#task-count-other").html(subtasks.filter(x => !x.parentId).length);
			}
		}