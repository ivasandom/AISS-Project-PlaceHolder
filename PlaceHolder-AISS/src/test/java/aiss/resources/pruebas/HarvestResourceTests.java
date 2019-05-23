package aiss.resources.pruebas;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

import com.google.api.client.json.Json;
import com.google.appengine.repackaged.com.google.gson.JsonObject;

import aiss.model.harvest.Project;
import aiss.model.harvest.Projects;
import aiss.model.harvest.TaskAssignment;
import aiss.model.resource.HarvestResource;
import jdk.nashorn.internal.runtime.regexp.joni.Config;

public class HarvestResourceTests {
	
	private static Long dy6SybqvNhJH81BGjq8fBicH;
	HarvestResource hv = new HarvestResource("1970829.at.HSZoO2csj4ldClEdqUI2rkrhBwf9ibcxn_KdBgeIQVdOIn-lLuKSuPUS1aqmrKu2Avh4etAAwf0GgMtSZt06DA");
	/* GUÍA ASSERTS
	assertTrue("This will succeed.", true);
	assertTrue("This will fail!", false);

	assertFalse("This will succeed.", false);
	assertFalse("This will fail!", true);*/
	@Test
	public void testGetHarvestProjects() {
		
		Projects proyectos = hv.getMyProjects();
		assertNotNull("La carga de proyectos devolvió null", proyectos);
		System.out.println("Búsqueda testGetHarvestProjects() con éxito");
	}
	
	@Test
	public void testCreateProjectHarvest() {
		//ARREGLAR
		Project proyecto = hv.createProject(dy6SybqvNhJH81BGjq8fBicH, "Test Crear Proyecto", false, "Project", "Hours Per Project");
		assertNotNull("La creación del proyecto devolvió null", proyecto);
		System.out.println("Creación testCreateProjectHarvest() completado con éxito");
	}
	
	@Test
	public void testDeleteProjectHarvest() {
		
		Boolean borraProyecto = hv.deleteProject("21216199");
		if(borraProyecto==true) {
			System.out.println("testDeleteProjectHarvest() completado con éxito");
		}
		assertTrue("El proyecto no se eliminó correctamente", borraProyecto);
	}
	
	//UPDATE PROJECT
	
	@Test
	public void testUpdateProject() {
		Boolean updateProject = hv.updateProjectConfiguration(dy6SybqvNhJH81BGjq8fBicH, new JSONObject());
		if (updateProject==true) {
			System.out.println("UpdateProject completado con exito");
		}
		assertTrue("El proyecto no se actualizo correctamente", updateProject);
	}
		
	@Test
	public void testGetProjectTasksHarvest() {
		List<TaskAssignment> tareas = hv.getProjectTasks("21216212");
		assertNotNull("La carga de tareas devolvió null", tareas);
		System.out.println("Búsqueda testGetProjectTasksHarvest() con éxito");
	}
}
