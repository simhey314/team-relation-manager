package com.heyden.teamrelationmanager.service;

public interface FieldUniqueness {

	boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException;
}
