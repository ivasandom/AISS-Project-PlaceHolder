// Crear una nueva tarea AJAX
		var formularioTarea = $("#ajax-add-task");
		var listaTareas = $("#lista-tareas");
		$(formularioTarea).submit(function(event){
			event.preventDefault();
			var datosFormulario = $(formularioTarea).serialize();
			$.ajax({
				url: "/tasks/create",
				type: "POST",
				data: datosFormulario,
				success: function(response){
					listaTareas.append("<li data-id='" + response.id + "'>" + response.content + "</li>");
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
		
		
		
		$(".delete-task-confirm").on("click", function(){
			Swal.fire({
				  title: '¿Estás seguro que quieres borrar la tarea?',
				  text: "Se borrará la tarea.",
				  type: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Eliminar',
				  cancelButtonText: 'Cancelar'
				}).then((result) => {
				  if (result.value) {
				    location.href = $(this).attr("data-url");
				  }
				})
		})
		
		
		$('.update-task-confirm').on('show.bs.modal', function (event) {
  			var button = $(event.relatedTarget) // Button that triggered the modal
		})