package com.lynxspa.sdm.dictionary.flow.state;

import com.lynxspa.sdm.entities.application.flow.adapter.StatesDictAdapter;


public enum StaticStatesSTATICMESSAGEFlow implements StatesDictAdapter{

	PRSD("PRSD","message parsed","message has been parsed",true,false),
	PNFA("PNFA","message with pre-normalization incidence","pre-normalization has failed",false,false),
	MSDS("MSDS","discarted message","pre-normalization discarted message",false,false),
	PNRM("PNRM","message pre-normalized","message has been pre-normalized",false,false),
	NNRM("NNRM","message no normalizable","message no normalizable",false,false),
	NRFA("NRFA","message normalization fail","message normalization has failed",false,false),
	PENF("PENF","previous event not found","previous event can't be found",false,false),
	DISC("DISC","discard","normalization discarted message",false,false),
	NORM("NORM","message normalized","message has been normalized",false,true),
	MTCH("MTCH","MATCHED","message has been agruped",false,false);
	
	
	private String id=null;
	private String name=null;
	private String description=null;
	private boolean initial=false;
	private boolean end=false;
	
	
	StaticStatesSTATICMESSAGEFlow(String _id,String _name,String _description,boolean _initial,boolean _end){
		this.id=_id;
		this.name=_name;
		this.description=_description;
		this.initial=_initial;
		this.end=_end;
	}

	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public boolean isInitial() {
		return initial;
	}
	public boolean isEnd() {
		return end;
	}
}
