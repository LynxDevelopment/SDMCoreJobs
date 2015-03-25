package com.lynspa.sdm.exception;

public interface SDMExceptionMessage {

	public SDMExceptionType getType();

	public String getMessageKey();

	public String getDefaultMessage();
}
