		// Crear una nueva tarea AJAX
		var formularioTarea = $("#ajax-add-task");
		var obj = $("#taskAssignment-other");
		var listaTareas = $(".subTask-other");
		$(formularioTarea).submit(function(event){
			event.preventDefault();
			var datosFormulario = $(formularioTarea).serialize();
			$.ajax({
				url: "/tasks/create",
				type: "POST",
				data: datosFormulario,
				success: function(response){
					
					var trow = (
							"<tr class='table-info collapse show subTask-other' id='task-" + response.id + "'>" +
								"<td>" + response.content + "</td>" +
								"<td></td>" +
								"<td><button class='btn btn-danger btn-sm delete-task' data-id='" + response.id + "'>Eliminar</button></td>" +
							"</tr>");
					
					(listaTareas.length) ? listaTareas.last().after(trow) : obj.after(trow);
					
					
					obj.collapse("show");
					
				},
				error: function(response){
					alert("Error creando tarea");
				}
			});
		})
		
		// Confirmar eliminación proyecto
		var confirmar = $("#delete-confirm");
		$(confirmar).click(function(){
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
		
		
		
		$(".delete-task").on("click", function(event){
			var taskId = $(this).data("id");
			var deleteUrl = "/tasks/delete?id=" + taskId;
			var trow = $("#task-" + taskId);
			$.ajax({
				url: deleteUrl,
				success: function(response){
					trow.remove()
				},
				error: function(response){
					alert("Error eliminando la tarea");
				}
			})
		})
		
		
		$('.update-task-confirm').on('show.bs.modal', function (event) {
  			var button = $(event.relatedTarget) // Button that triggered the modal
		})