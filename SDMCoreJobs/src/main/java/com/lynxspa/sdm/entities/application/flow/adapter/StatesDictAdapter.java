package com.lynxspa.sdm.entities.application.flow.adapter;

public interface StatesDictAdapter {
	
	public String getId();
	public String getName();
	public String getDescription();
	public boolean isInitial();
	public boolean isEnd();
}
