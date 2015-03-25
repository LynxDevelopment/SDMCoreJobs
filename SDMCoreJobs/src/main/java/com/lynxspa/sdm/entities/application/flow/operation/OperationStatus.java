package com.lynxspa.sdm.entities.application.flow.operation;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.lynxspa.sdm.entities.application.flow.State;


@Embeddable
public final class OperationStatus implements Cloneable,Serializable{
	
	private static final long serialVersionUID = -7122336164118651053L;

	
	private State state=null;
	private boolean ended=false;
	private boolean transitionMessageError=false;
	private String transitionMessageKey=null;
	private String transitionMessageParameters=null;
	
	
	public OperationStatus (){
		this(null,false);
	} 
	public OperationStatus (final State _state){
		this(_state,false);
	} 
	public OperationStatus (final State _state,final boolean _ended){
		super();
		this.state=_state;
		this.ended=_ended;
		this.transitionMessageError=false;
		this.transitionMessageKey=null;
		this.transitionMessageParameters=null;
	} 
	
	
	@ManyToOne(targetEntity = State.class,fetch=FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name="FKSTATEFLOW", referencedColumnName="FKFLOW", nullable=false),
			@JoinColumn(name="FKSTATE", referencedColumnName="CODE", nullable=false)
	})
	public State getState() {
		return state;
	}
	public void setState(final State state) {
		this.state = state;
	}
	
	@Column(name="ISENDED", nullable=false)
	public boolean isEnded() {
		return ended;
	}
	public void setEnded(final boolean ended) {
		this.ended = ended;
	}

	@Column(name="ISTRANSMESSERROR", nullable=false)
	public boolean isTransitionMessageError() {
		return transitionMessageError;
	}
	public void setTransitionMessageError(final boolean comesWithError) {
		this.transitionMessageError = comesWithError;
	}

	@Column(name="TRANSMESSKEY", length=128, nullable=true)
	public String getTransitionMessageKey() {
		return transitionMessageKey;
	}
	public void setTransitionMessageKey(final String transitionMessageKey) {
		this.transitionMessageKey = transitionMessageKey;
	}

	@Column(name="TRANSMESSPARM", length=256, nullable=true)
	public String getTransitionMessageParameters() {
		return transitionMessageParameters;
	}
	public void setTransitionMessageParameters(final String transitionMessageParameters) {
		this.transitionMessageParameters = transitionMessageParameters;
	}

	@Transient
	public String[] getMessageParameters(){
		
		String[] reply=null;
		
		if(this.transitionMessageParameters!=null){
			reply=this.transitionMessageParameters.split("\\|");
		}
		
		return reply;
	}
	
	@Transient
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
