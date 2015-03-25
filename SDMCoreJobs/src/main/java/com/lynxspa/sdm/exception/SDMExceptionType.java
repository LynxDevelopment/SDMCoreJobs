package com.lynxspa.sdm.exception;

public enum SDMExceptionType {

	// Values
	WARNING(1, "warning"), ERROR(1, "error");

	// Structure
	private final int level;
	private final String description;

	SDMExceptionType(final int _level, final String _description) {

		this.level = _level;
		this.description = _description;
	}

	public String getDescription() {
		return description;
	}

	public int getLevel() {
		return level;
	}

}
