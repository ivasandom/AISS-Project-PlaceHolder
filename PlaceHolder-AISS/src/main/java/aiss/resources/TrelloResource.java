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
			cr.post(name, Board.class);
		} catch (ResourceException re) {
			System.err.println("Error when adding the board: " + name +" " + cr.getResponse().getStatus());
			success = false;
		}
		return success;
	}

	
	public boolean addList(String name, String idBoard) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(base_url +"/" +name);
			cr.setEntityBuffering(true);
			cr.post(name, List.class);
		} catch (ResourceException re) {
			System.err.println("Error when adding the list: " + name +" " + cr.getResponse().getStatus());
			success = false;
		}
		return success;
	}

	public Collection<List> getListSearch(String query) {
		Collection<List> col = new ArrayList<List>();
		List res = null;
		ClientResource cr = null;
		try {
			cr = new ClientResource(base_url);
			if(List.class.getName().contains(query)) {
			res = cr.get(List.class);
			col.add(res);
			}}catch (ResourceException re) {
		System.err.println("Error when searching the query: " + query +" " + cr.getResponse().getStatus());

	}return col;
	}

	public boolean addCard(String name, String idList) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(base_url +"/" +name);
			cr.setEntityBuffering(true);
			cr.post(name, Card.class);
		} catch (ResourceException re) {
			System.err.println("Error when adding the card: " + name +" " + cr.getResponse().getStatus());
			success = false;
		}
		return success;
	}

	public Collection<Card> getCardSearch(String query) {
		Collection<Card> col = new ArrayList<Card>();
		Card res = null;
		ClientResource cr = null;
		try {
			cr = new ClientResource(base_url);
			if(Card.class.getName().contains(query)) {
			res = cr.get(Card.class);
			col.add(res);
				}}catch (ResourceException re) {
		System.err.println("Error when searching the query: " + query +" " + cr.getResponse().getStatus());

	}return col;
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
			cr.put(name);
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
			cr.put(name);
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
			cr.put(name);
		} catch (ResourceException re) {
			System.err.println("Error when updating the list: " +name +" " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}
////Está mal supongo
	public Collection<Board> getBoardSearch(String query) {
		Collection<Board> col = new ArrayList<Board>();
		Board res = null;
		ClientResource cr = null;
		try {
			cr = new ClientResource(base_url);
			if(Board.class.getName().contains(query)) {
			res = cr.get(Board.class);
			col.add(res);
			}}catch (ResourceException re) {
		System.err.println("Error when searching the query: " + query +" " + cr.getResponse().getStatus());

	}return col;
	}
	

	
	

}
