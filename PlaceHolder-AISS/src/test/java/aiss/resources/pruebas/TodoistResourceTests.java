package aiss.resources.pruebas;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiss.model.resource.TodoistResource;
import aiss.model.todoist.Task;
import aiss.model.todoist.TaskSimple;

public class TodoistResourceTests {
	
	TodoistResource td = new TodoistResource("636a7278f087c14f6643a61517b38616cedba8f8");
	
	@Test
	public void testGetProjectTasksTodoist() {
		TaskSimple[] tareas = td.getActiveTasks("2210842978");
		assertNotNull("La carga de tareas devolvió null", tareas);
		System.out.println("Búsqueda testGetProjectTasks() con éxito");
	}
	
	@Test
	public void testCreateTask() {
		//TODO
	}
	
	
	@Test
	public void testUpdateTask() {
		Boolean updateTarea = td.updateTask("3210017843", "newTask");
		if (updateTarea==true) {
			System.out.println("Test de updateTask completado exitosamente");
		}
		assertTrue("La tarea no se actualizó correctamente", updateTarea);
	}
	
	
	@Test
	public void testDeleteTaskTodoist() {
		Boolean borraTarea = td.deleteTask("3210017843");
		if(borraTarea==true) {
			System.out.println("testDeleteTaskTodoist() completado con éxito");
		}
		assertTrue("La tarea no se eliminó correctamente", borraTarea);
	}
}
