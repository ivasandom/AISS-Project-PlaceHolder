package aiss.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import aiss.resources.TrelloResource;
import aiss.trello.List;


public class UpdateListController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(UpdateListController.class.getName());

	public UpdateListController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;

		log.log(Level.INFO, "Procesando UpdateListController.");

		// Obtenemos el usuario y la clave decodificados
		String usuario = new String(Base64.decodeBase64(request.getParameter("usuario").getBytes()));
		String clave = new String(Base64.decodeBase64(request.getParameter("clave").getBytes()));

		// Obtenemos los parámetros necesarios
		String query = request.getParameter("searchQuery");
		String id = request.getParameter("idList");
		String name = request.getParameter("name");

		// Creamos la lista con los valores anteriores
		List datos = new List(id, name);

		// Creamos el recurso de lista con el usuario y la clave
		TrelloResource autentificacionTrello = new TrelloResource(usuario, clave);

		// Actualizamos la lista y en caso de error capturamos la excepción
		try {
			log.log(Level.INFO, "Actualizando tablero.");
			boolean updated = autentificacionTrello.updateList(id, name);
			Collection<List> trelloResults = autentificacionTrello.getListSearch(query);
			request.setAttribute("informacion", trelloResults);
			if(updated == true) {
				request.setAttribute("updated", trelloResults);
				log.log(Level.FINE, "Lista actualizado correctamente.");
			}
			else {
				request.setAttribute("noUpdated", trelloResults);
				log.log(Level.SEVERE, "No se ha podido actualizar la lista correctamente.");
			}
			rd = request.getRequestDispatcher("/listaTareas.jsp");
		} catch (Exception e) {
			log.log(Level.WARNING, "Error al actualizar la lista.");
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doGet(request, response);
	}

}