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
import aiss.trello.Board;
import aiss.trello.List;


public class AddListController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(AddListController.class.getName());

	public AddListController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;

		log.log(Level.INFO, "Procesando AddListController.");

		// Obtenemos el usuario y la clave decodificados
		String usuario = new String(Base64.decodeBase64(request.getParameter("usuario").getBytes()));
		String clave = new String(Base64.decodeBase64(request.getParameter("clave").getBytes()));

		// Obtenemos los parámetros necesarios
		String query = request.getParameter("searchQuery");
		String name = request.getParameter("name");
		String idBoard = request.getParameter("idBoard");

		// Creamos la lista con los valores anteriores
		List datos = new List(name, idBoard);

		// Creamos el recurso de lista con el usuario y la clave
		TrelloResource autentificacionTrello = new TrelloResource(usuario, clave);

		// Añadimos la lista comprobando si ya está añadida y en caso de error capturamos la excepción
		try {
			log.log(Level.INFO, "Añadiendo tablero.");
			boolean added = autentificacionTrello.addList(name, idBoard);
			Collection<List> trelloResults = autentificacionTrello.getListSearch(query);
			request.setAttribute("informacion", trelloResults);
			if(added == true) {
				request.setAttribute("added", trelloResults);
				log.log(Level.FINE, "Lista añadida correctamente.");
			}
			else {
				request.setAttribute("noAdded", trelloResults);
				log.log(Level.SEVERE, "No se ha podido añadir la lista correctamente.");
			}
			rd = request.getRequestDispatcher("/listaTareas.jsp");
		} catch (Exception e) {
			log.log(Level.WARNING, "Error al añadir la lita.");
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doGet(request, response);
	}

}