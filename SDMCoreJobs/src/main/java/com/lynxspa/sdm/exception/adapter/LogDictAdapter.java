package com.lynxspa.sdm.exception.adapter;

import com.lynxspa.sdm.exception.LogLevel;


public interface LogDictAdapter {

	public String getMessageKey();
	public String getDefaultMessage();
	public LogLevel getLevel();
}
