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
import aiss.trello.Card;

public class UpdateCardController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(UpdateCardController.class.getName());

	public UpdateCardController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;

		log.log(Level.INFO, "Procesando UpdateCardController.");

		// Obtenemos el usuario y la clave decodificados
		String usuario = new String(Base64.decodeBase64(request.getParameter("usuario").getBytes()));
		String clave = new String(Base64.decodeBase64(request.getParameter("clave").getBytes()));

		// Obtenemos los parámetros necesarios
		String query = request.getParameter("searchQuery");
		String id = request.getParameter("idCard");
		String name = request.getParameter("name");

		// Creamos la tarea con los valores anteriores
		Card datos = new Card(id, name);

		// Creamos el recurso de tarea con el usuario y la clave
		TrelloResource autentificacionTrello = new TrelloResource(usuario, clave);

		// Actualizamos la tares y en caso de error capturamos la excepción
		try {
			log.log(Level.INFO, "Actualizando tarea.");
			boolean updated = autentificacionTrello.updateCard(id, name);
			Collection<Card> trelloResults = autentificacionTrello.getCardSearch(query);
			request.setAttribute("informacion", trelloResults);
			if(updated == true) {
				request.setAttribute("updated", trelloResults);
				log.log(Level.FINE, "Tarea actualizado correctamente.");
			}
			else {
				request.setAttribute("noUpdated", trelloResults);
				log.log(Level.SEVERE, "No se ha podido actualizar la tarea correctamente.");
			}
			rd = request.getRequestDispatcher("/listaTareas.jsp");
		} catch (Exception e) {
			log.log(Level.WARNING, "Error al actualizar la tarea.");
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doGet(request, response);
	}

}