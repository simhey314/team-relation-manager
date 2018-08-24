package com.heyden.teamrelationmanager.rest;

import com.heyden.teamrelationmanager.error.EntityErrorResponse;

public class EntitySuccesResponse extends EntityErrorResponse {
	
	private Object entity;
	
	public EntitySuccesResponse() {
	}

	public EntitySuccesResponse(Object entity, String message, int status, long timeStamp) {
		super(message, status, timeStamp);
		this.entity = entity;
	}

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}

	@Override
	public String toString() {
		return String.format("EntitySuccesResponse [entity=%s, getMessage()=%s, getStatus()=%s, getTimeStamp()=%s]",
				entity, getMessage(), getStatus(), getTimeStamp());
	}
	
	
}
