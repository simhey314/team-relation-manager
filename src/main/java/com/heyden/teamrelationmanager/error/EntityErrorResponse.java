package com.heyden.teamrelationmanager.error;

public class EntityErrorResponse {
	
	private String message;
	private int status;
	private long timeStamp;
	
	public EntityErrorResponse() {
	}
	
	public EntityErrorResponse(String message, int status, long timeStamp) {
		super();
		this.message = message;
		this.status = status;
		this.timeStamp = timeStamp;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return String.format("EntityErrorResponse [message=%s, status=%s, timeStamp=%s]", message, status, timeStamp);
	}
}
