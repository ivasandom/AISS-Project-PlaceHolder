package aiss.model.resource;


import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.trello.Board;
import aiss.model.trello.Card;
import aiss.model.trello.List;

public class TrelloResource {
	private final String access_token;
	private final String key;
	public TrelloResource(String access_token, String key) {
        this.access_token = access_token;
        this.key =key;
    }
	public Collection<Board> getAllBoards(String id) {

		ClientResource cr = null;
		Board[] boards = null;
		try {
			cr = new ClientResource("https://api.trello.com/1/boards"+"/"+id+ "?key="+key+"&token=" + access_token);
			boards = cr.get(Board[].class);

		} catch (ResourceException re) {
			System.err.println("Error when retrieving the collections of boards: " + cr.getResponse().getStatus());
		}

		return Arrays.asList(boards);
	}

	public Board getBoard(String id) {

		ClientResource cr = null;
		Board board = null;
		try {
			cr = new ClientResource("https://api.trello.com/1/boards" + "/" + id+ "?key="+key+"&token=" + access_token);
			board = cr.get(Board.class);

		} catch (ResourceException re) {
			System.err.println("Error when retrieving the board: " + cr.getResponse().getStatus());
		}

		return board;

	}
	
	public Board getBoardCards(String id) {

		ClientResource cr = null;
		Board board = null;
		try {
			cr = new ClientResource("https://api.trello.com/1/boards" +"/" +id+"/cards"+ "?key="+key+"&token=" + access_token);
			board = cr.get(Board.class);

		} catch (ResourceException re) {
			System.err.println("Error when retrieving the board: " + cr.getResponse().getStatus());
		}

		return board;

	}
	
	public Board getBoardLists(String id) {

		ClientResource cr = null;
		Board board = null;
		try {
			cr = new ClientResource("https://api.trello.com/1/boards" +"/" +id+"/lists"+ "?key="+key+"&token=" + access_token);
			board = cr.get(Board.class);

		} catch (ResourceException re) {
			System.err.println("Error when retrieving the board: " + cr.getResponse().getStatus());
		}

		return board;

	}
	
	public List getList(String id) {

		ClientResource cr = null;
		List list = null;
		try {
			cr = new ClientResource("https://api.trello.com/1/lists" + "/" + id+ "?key="+key+"&token=" + access_token);
			list = cr.get(List.class);

		} catch (ResourceException re) {
			System.err.println("Error when retrieving the board: " + cr.getResponse().getStatus());
		}

		return list;

	}
	
	public List getListCards(String id) {

		ClientResource cr = null;
		List list = null;
		try {
			cr = new ClientResource("https://api.trello.com/1/lists" + "/" +id+"/cards"+ "?key="+key+"&token=" + access_token);
			list = cr.get(List.class);

		} catch (ResourceException re) {
			System.err.println("Error when retrieving the board: " + cr.getResponse().getStatus());
		}

		return list;

	}
	public Card getCard(String id) {

		ClientResource cr = null;
		Card card = null;
		try {
			cr = new ClientResource("https://api.trello.com/1/cards" + "/" + id+ "?key="+key+"&token=" + access_token);
			card = cr.get(Card.class);

		} catch (ResourceException re) {
			System.err.println("Error when retrieving the board: " + cr.getResponse().getStatus());
		}

		return card;

	}
	
	public Board addBoard(String name) {
		ClientResource cr = null;
		Board resultBoard = null;
		try {
			cr = new ClientResource("https://api.trello.com/1/boards"+ "?key="+key+"&token=" + access_token);
			cr.setEntityBuffering(true);
			resultBoard = cr.post(name, Board.class);
		} catch (ResourceException re) {
			System.err.println("Error when adding the board: " + name +" " + cr.getResponse().getStatus());
		}
		return resultBoard;
	}


	public boolean addList(String name, String idBoard) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource("https://api.trello.com/1/lists" +"/" +idBoard +"/"+name+ "?key="+key+"&token=" + access_token);
			cr.setEntityBuffering(true);
			cr.post(" ");
		} catch (ResourceException re) {
			System.err.println("Error when adding the list: " + name +" " + cr.getResponse().getStatus());
			success = false;
		}
		return success;
	}

	public boolean addCard(String name, String idList) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource("https://api.trello.com/1/cards" +"/" +idList+"/"+name+ "?key="+key+"&token=" + access_token);
			cr.setEntityBuffering(true);
			cr.post(" ");
		} catch (ResourceException re) {
			System.err.println("Error when adding the card: " + name +" " + cr.getResponse().getStatus());
			success = false;
		}
		return success;
	}

	public boolean deleteBoard(String id) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource("https://api.trello.com/1/boards" +"/" +id+ "?key="+key+"&token=" + access_token);
			cr.setEntityBuffering(true);
			cr.delete();
		}catch (ResourceException re) {
			System.err.println("Error when deleting the board: " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}
		return success;
	}

	public boolean deleteCard(String id) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource("https://api.trello.com/1/cards" +"/" +id+ "?key="+key+"&token=" + access_token);
			cr.setEntityBuffering(true);
			cr.delete();
		}catch (ResourceException re) {
			System.err.println("Error when deleting the card: " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}
		return success;
	}

	public boolean closeList(String id) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource("https://api.trello.com/1/lists"+"/"+id+"/closed"+ "?key="+key+"&token=" + access_token);
			cr.setEntityBuffering(true);
			cr.put(" ");
		}catch (ResourceException re) {
			System.err.println("Error when deleting the list: " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}
		return success;
	}

	public boolean updateBoard(String id) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource("https://api.trello.com/1/boards"+"/"+id+ "?key="+key+"&token=" + access_token);
			cr.setEntityBuffering(true);
			cr.put(id);
		} catch (ResourceException re) {
			System.err.println("Error when updating the playlist: " +id +" " + cr.getResponse().getStatus());
			success = false;
		}

		return success;
	}

	public boolean updateCard(String id) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource("https://api.trello.com/1/cards"+"/"+id+ "?key="+key+"&token=" + access_token);
			cr.setEntityBuffering(true);
			cr.put(id);
		} catch (ResourceException re) {
			System.err.println("Error when updating the card: " +id +" " + cr.getResponse().getStatus());
			success = false;
		}

		return success;
	}

	public boolean updateList(String id) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource("https://api.trello.com/1/lists"+"/"+id+ "?key="+key+"&token=" + access_token);
			cr.setEntityBuffering(true);
			cr.put(id);
		} catch (ResourceException re) {
			System.err.println("Error when updating the list: " +id +" " + cr.getResponse().getStatus());
			success = false;
		}

		return success;
	}


}