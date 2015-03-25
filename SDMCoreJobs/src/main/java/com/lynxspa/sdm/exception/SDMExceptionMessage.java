package com.lynxspa.sdm.exception;

public interface SDMExceptionMessage {

	public SDMExceptionType getType();

	public String getMessageKey();

	public String getDefaultMessage();
}
