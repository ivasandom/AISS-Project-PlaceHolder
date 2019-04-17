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

public class AddBoardController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(AddBoardController.class.getName());

	public AddBoardController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;

		log.log(Level.INFO, "Procesando AddBoardController.");

		// Obtenemos el usuario y la clave decodificados
		String usuario = new String(Base64.decodeBase64(request.getParameter("usuario").getBytes()));
		
		String clave = new String(Base64.decodeBase64(request.getParameter("clave").getBytes()));

		// Obtenemos los parámetros necesarios
		String query = request.getParameter("searchQuery");
		String name = request.getParameter("name");

		// Creamos el tablero con los valores anteriores
		Board datos = new Board(name);

		// Creamos el recurso de tablero con el usuario y la clave
		TrelloResource autentificacionTrello = new TrelloResource(usuario, clave);

		// Añadimos el tablero comprobando si ya está añadido y en caso de error capturamos la excepción
		try {
			log.log(Level.INFO, "Añadiendo tablero.");
			boolean added = autentificacionTrello.addBoard(name, datos);
			Collection<Board> trelloResults = autentificacionTrello.getBoardSearch(query);
			request.setAttribute("informacion", trelloResults);
			if(added == true) {
				request.setAttribute("added", trelloResults);
				log.log(Level.FINE, "Tablero añadido correctamente.");
			}
			else {
				request.setAttribute("noAdded", trelloResults);
				log.log(Level.SEVERE, "No se ha podido añadir el tablero correctamente.");
			}
			rd = request.getRequestDispatcher("/listaTareas.jsp");
		} catch (Exception e) {
			log.log(Level.WARNING, "Error al añadir el tablero.");
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doGet(request, response);
	}

}
