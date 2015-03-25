package com.lynxspa.sdm.exception;


public enum LogLevel {

	ERROR(0,"error"),
	WARNING(1,"warning"),
	AUDIT(2,"audit information"),
	INFO(3,"information"),
	DEBUG(4,"debug");
	
	
	private int level;
	private String name;

	
	LogLevel(final int _level,final String _name){
		this.level=_level;
		this.name=_name;
	}


	public int getLevel() {
		return level;
	}

	public String getName() {
		return name;
	}
}
