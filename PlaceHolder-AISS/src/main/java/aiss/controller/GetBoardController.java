package aiss.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import aiss.model.resource.TrelloResource;
import aiss.model.trello.Board;


public class GetBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GetBoardController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String accessToken = (String) req.getSession().getAttribute("Trello-token");
		String key = (String) req.getSession().getAttribute("Trello-key");
		TrelloResource trelloResource =new TrelloResource(accessToken, key);
		String id = req.getParameter("id");
		if (accessToken != null) {
			
			Collection<Board> boards = trelloResource.getAllBoards(id);
			
//			System.out.println(currentUser.getName());
//			log.info(currentUser.getLogin());
			req.setAttribute("boards", boards);
			
		}
		
		
 		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req,resp);
	}
}