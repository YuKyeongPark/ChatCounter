package edu.handong.csee.java.ChatCounter;

/**
 * This class is Message.
 * This class has constructors.
 * Overloading occurs
 * 
 * @author YukyeongPark
 *
 */

public class Message {
	private String id;
	private String message;
	private String datetime;
	private String fullDateTime;
	private FromWhere fromWhere = FromWhere.CSV;
	private enum FromWhere{CSV,TXT};

	
	public Message(String datetime, String id, String message, FromWhere fromeWhere) {
		this.datetime = datetime;
		this.fullDateTime = "";
		this.id = id;
		this.message = message;
		this.fromWhere = fromWhere;
	}
	
	public Message(String datetime, String fullDateTime, String id, String message, FromWhere fromeWhere) {
		this.datetime = datetime;
		this.fullDateTime = fullDateTime;
		this.id = id;
		this.message = message;
		this.fromWhere = fromWhere;
		
	}
	
	public Message(String dateTime, String id, String strMessage) {
		
	}

	public String getId() {
		return id;
	}
	
	public FromWhere getFromWhere() {
		return fromWhere;
	}
}
