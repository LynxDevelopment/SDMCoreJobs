package com.lynxspa.sdm.entities.application.flow;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;


@Embeddable
public class StateId implements Serializable{

	private static final long serialVersionUID = 696094467624444817L;

	
	private Flow flow=null;
	private String code=null;

	
	public StateId(){
		this(null,null);
	}
	public StateId(Flow _flow,String _code){
		super();
		this.flow=_flow;
		this.code=_code;
	}
	
	
	@ManyToOne(targetEntity = Flow.class,fetch=FetchType.EAGER)
	@ForeignKey(name="FK_STATE_FLOW")
	@JoinColumn(name="FKFLOW", nullable=false, updatable=false)
	public Flow getFlow() {
		return flow;
	}
	public void setFlow(Flow _flow) {
		this.flow = _flow;
	}
	
	@Column(name="CODE", length=16, nullable=false, updatable=false)
    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		boolean reply=false;
		StateId newState=null;
		
		if(obj instanceof StateId){
			newState=(StateId)obj;
			if((this.getFlow()!=null)&&(this.getCode()!=null)&&(this.getFlow().equals(newState.getFlow()))&&(this.getCode().equals(newState.getCode()))){
				reply=true;
			}
		}
		
		return reply;
	}
	
	@Override
	public int hashCode() {
		
		int reply=0;
		
		reply=(this.getFlow()!=null)? this.getFlow().hashCode() : 1;
		reply*=(this.getCode()!=null)? this.getCode().hashCode() : 1;
		
		return reply;
	}
}
