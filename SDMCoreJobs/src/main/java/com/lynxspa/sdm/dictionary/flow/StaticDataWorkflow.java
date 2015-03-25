package com.lynxspa.sdm.dictionary.flow;

import com.lynxspa.sdm.entities.application.flow.adapter.FlowsDictAdapter;


public enum StaticDataWorkflow implements FlowsDictAdapter{

	STATICMESSAGE("STATICMESSAGE","static message","static messages flow according"),
	STATICCOLLECTED("STATICCOLLECTED","static collected","collected statics flow according to provider"),
	STATICGROUP("STATICGROUP","static group","group statics flow");
	
	
	private String id=null;
	private String name=null;
	private String description=null;

	
	StaticDataWorkflow(String _id,String _name,String _description){
		this.id=_id;
		this.name=_name;
		this.description=_description;
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
}
