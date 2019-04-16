package aiss;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import aiss.resources.GitHubResource;

public class Index extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Index.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		RequestDispatcher rd = null;
		log.log(Level.FINE, "Processing GET request");

		String usuarioCodificado = "PlaceHolder";
		String claveCodificada = "p14c3h01d3r";
		String usuario = req.getParameter("usuario");
		String clave = req.getParameter("clave");
		String sesion = req.getParameter("sesion");
		
	/*	if (usuario != null || clave != null) {
			log.log(Level.INFO, "Verificando credenciales.");
			GitHubResource autentificacionGitHub = new GitHubResource(usuario, clave);
			boolean verificacionResult = autentificacionGitHub.verificarCredenciales(usuario);
			if (verificacionResult == false) {
				log.log(Level.WARNING, "Error al verificar las credenciales, el usuario y/o clave no son correctos.");
				rd = req.getRequestDispatcher("/error.jsp");
				rd.forward(req, resp);
			}
			log.log(Level.FINE, "Credenciales verificadas con éxito.");
			usuarioCodificado = new String(Base64.encodeBase64(usuario.getBytes()));
			claveCodificada = new String(Base64.encodeBase64(clave.getBytes()));
		} else {
			usuarioCodificado = new String(Base64.encodeBase64(usuarioCodificado.getBytes()));
			claveCodificada = new String(Base64.encodeBase64(claveCodificada.getBytes()));
		}
		
		*/
		
		
		
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		if (sesion != null) {
			out.println("<span>Logeado como <b>" + usuario + ".</b></span> ");
			out.println("<span><a href='index'>Cerrar sesión</a></span>");
		} else {
			out.println("<span>Si desea logear en GitHub, haga click <a href='loginGitHub.jsp'>aquí</a></span>");
		}
	}
}

