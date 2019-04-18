package aiss.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.trello.Board;
import aiss.trello.Card;
import aiss.trello.List;

public class TrelloResource {

	private static final Logger log = Logger.getLogger(TrelloResource.class.getName());
	
	private final String usuario;
	private final String clave;
	private final String base_url = "https://developers.trello.com/";

	public TrelloResource(String usuario, String clave) {
		this.usuario = usuario;
		this.clave = clave;
	}

	public boolean addBoard(String name, Board datos) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(base_url +"/" +name);
			cr.setEntityBuffering(true);
			cr.post(datos, Board.class);
		} catch (ResourceException re) {
			System.err.println("Error when adding the board: " + name +" " + cr.getResponse().getStatus());
			success = false;
		}
		return success;
	}

//	public String getBoardSearch(String query) {
//		ClientResource cr = null;
//		String search = null;
//		try {
//			cr = new ClientResource(base_url);
//			search = cr.getQueryValue(query);
//	}catch (ResourceException re) {
//		System.err.println("Error when searching the query: " + query +" " + cr.getResponse().getStatus());
//
//	}return search;
//	}
	
	public boolean addList(String name, String idBoard) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(base_url +"/" +name);
			cr.setEntityBuffering(true);
			cr.post(idBoard, List.class);
		} catch (ResourceException re) {
			System.err.println("Error when adding the list: " + name +" " + cr.getResponse().getStatus());
			success = false;
		}
		return success;
	}

	public Collection<List> getListSearch(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addCard(String name, String idList) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(base_url +"/" +name);
			cr.setEntityBuffering(true);
			cr.post(idList, Card.class);
		} catch (ResourceException re) {
			System.err.println("Error when adding the card: " + name +" " + cr.getResponse().getStatus());
			success = false;
		}
		return success;
	}

	public Collection<Card> getCardSearch(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteBoard(String id, String usuario) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(base_url +"/" +id);
			cr.setEntityBuffering(true);
			cr.delete();
		}catch (ResourceException re) {
				System.err.println("Error when deleting the board: " + cr.getResponse().getStatus());
				success = false;
				throw re;
			}return success;
		}

	public boolean deleteCard(String id, String usuario) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(base_url +"/" +id);
			cr.setEntityBuffering(true);
			cr.delete();
		}catch (ResourceException re) {
				System.err.println("Error when deleting the card: " + cr.getResponse().getStatus());
				success = false;
				throw re;
			}return success;
	}

	public boolean closeList(String id, String usuario) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(base_url +"/" +id);
			cr.setEntityBuffering(true);
			cr.delete();
		}catch (ResourceException re) {
				System.err.println("Error when deleting the list: " + cr.getResponse().getStatus());
				success = false;
				throw re;
			}return success;
	}

	public boolean updateBoard(String id, String name) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(base_url +"/" +id);
			cr.setEntityBuffering(true);
			cr.put(id);
		} catch (ResourceException re) {
			System.err.println("Error when updating the playlist: " +name +" " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}

	public boolean updateCard(String id, String name) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(base_url +"/" +id);
			cr.setEntityBuffering(true);
			cr.put(id);
		} catch (ResourceException re) {
			System.err.println("Error when updating the card: " +name +" " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}

	public boolean updateList(String id, String name) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(base_url +"/" +id);
			cr.setEntityBuffering(true);
			cr.put(id);
		} catch (ResourceException re) {
			System.err.println("Error when updating the list: " +name +" " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}
//	public Collection<Board> getBoardSearch(String query) {
//		
//	}

	
	

}
