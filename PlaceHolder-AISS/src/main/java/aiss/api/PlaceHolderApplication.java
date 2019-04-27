package aiss.api;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Application;

import aiss.api.resources.ProyectosResource;
import aiss.api.resources.TareasResource;
import aiss.controller.UpdateTaskController;

public class PlaceHolderApplication extends Application{

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UpdateTaskController.class.getName());
	
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	// Loads all resources that are implemented in the application
	// so that they can be found by RESTEasy.
	public PlaceHolderApplication() {

		log.log(Level.INFO, "Procesando PlaceHolder.");

		singletons.add(ProyectosResource.getInstance());
		singletons.add(TareasResource.getInstance());
		log.log(Level.FINE, "PlaceHolder procesado con Exito.");

	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}

