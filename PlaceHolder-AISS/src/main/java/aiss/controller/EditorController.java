package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import aiss.model.gitHub.Repository;
import aiss.model.gitHub.RepositoryTree;
import aiss.model.gitHub.User;
import aiss.model.resource.GitHubResource;

public class EditorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(HomeController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String accessToken = (String) req.getSession().getAttribute("GitHub-token");
		String owner = req.getParameter("owner");
		String repo = req.getParameter("repo");
		
		if (owner != null && repo != null && accessToken != null) {
			RepositoryTree repositoryTree = GitHubResource.getRepositoryTree(owner, repo, accessToken);
			req.setAttribute("repositoryTrees", repositoryTree.getTree());
			req.getRequestDispatcher("editor.jsp").forward(req, resp);
		}
		
		
//		
// 		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}