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

import aiss.bitbucket.BitbucketRepository;
import aiss.resources.BitbucketResource;


public class GetBitbucketRepositoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(GetBitbucketRepositoryController.class.getName());

	public GetBitbucketRepositoryController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;

		log.log(Level.INFO, "Procesando GetBitbucketRepositoryController.");

		// Obtenemos el usuario y la clave decodificados
		String usuario = new String(Base64.decodeBase64(request.getParameter("usuario").getBytes()));
		String clave = new String(Base64.decodeBase64(request.getParameter("clave").getBytes()));
		
		// Inicializamos el recurso de Bitbucket en null
		BitbucketResource autentificacionBitbucket = null;
		
		// En el caso de haberse logeado comprobamos que sean correctos el usuario y la clave. También creamos el recurso de GitHub con dicho usuario y clave
		if (usuario != null || clave != null) {
			log.log(Level.INFO, "Verificando credenciales.");
			autentificacionBitbucket = new BitbucketResource(usuario, clave);
			boolean verificacionResult = autentificacionBitbucket.verificarCredenciales(usuario);
			if (verificacionResult == false) {
				log.log(Level.WARNING, "Error al verificar las credenciales, el usuario y/o clave no son correctos.");
				rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
			log.log(Level.FINE, "Credenciales verificadas con éxito.");
		}

		// Obtenemos el parámetro de búsqueda del repositorio
		String query = request.getParameter("searchQuery");
		
		// Obtenemos los resultados de Bitbucket según la búsqueda realizada
		log.log(Level.INFO, "Buscando para Bitbucket el repositorio " + query);
		Collection<BitbucketRepository> bitbucketRepositoryResults = autentificacionBitbucket.getRepository(query);
		
		// Comprobamos los resultados y en caso no obtenerlos se redirigirá a la página de error
		if (bitbucketRepositoryResults != null) {
			request.setAttribute("Nombre", bitbucketRepositoryResults);
			rd = request.getRequestDispatcher("/listaTareas.jsp");
			log.log(Level.FINE, "Búsqueda de repositorio reaiazada y procesada correctamente.");
		} else {
			log.log(Level.WARNING, "Error al realizar la búsqueda");
			rd = request.getRequestDispatcher("/error.jsp");
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doGet(request, response);
	}
}