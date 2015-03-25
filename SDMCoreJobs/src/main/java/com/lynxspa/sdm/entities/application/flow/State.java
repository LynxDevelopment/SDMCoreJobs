package com.lynxspa.sdm.entities.application.flow;


import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.InsertAuditor;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.InsertableAdapter;
import com.oracle.jrockit.jfr.Transition;


@Entity
@Table(name = "TB_STATES")
@org.hibernate.annotations.Table(appliesTo="TB_STATES",indexes={
		@Index(name="IDX_STATE_FKFLOW",columnNames="FKFLOW"),
		@Index(name="IDX_STATE_CODE",columnNames="CODE")
})
public final class State implements InsertableAdapter{

	private static final long serialVersionUID = 5833171117112522048L;
	
		
	private StateId id=null;
	private String name=null;
	private String description="";
	private boolean initial=false;
	private boolean end=false;
	private InsertAuditor auditor=null;

	Map<State,Transition> arrivalTransitions=new HashMap<State,Transition>(); 
	Map<State,Transition> departuresTransitions=new HashMap<State,Transition>(); 
	
	
	public State(){
		this(null,null,null,null,false,false,null);
	}
	public State(final StateId _id){
		this(new Flow(_id.getFlow().getId()),_id.getCode(),null,null,false,false,null);
	}
	public State(Flow _flow,String _code,String _name,String _user){
		this(_flow,_code,_name,null,false,false,_user);
	}
	public State(Flow _flow,String _code,String _name,String _description,String _user){
		this(_flow,_code,_name,_description,false,false,_user);
	}
	public State(Flow _flow,String _code,String _name,String _description,boolean _initial,boolean _end,String _user){
		super();
		this.id=new StateId(_flow,_code);
		this.name=_name;
		this.description=_description;
		this.initial=_initial;
		this.end=_end;
		this.auditor=new InsertAuditor(_user);
	}
	
	
	@EmbeddedId
	public StateId getId() {
		return id;
	}
	public void setId(StateId id) {
		this.id = id;
	}

	@Column(name="NAME", length=64, nullable=false)
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name="DESCRIPTION", length=128, nullable=true)
    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="ISINITIAL", nullable=false)
    public boolean isInitial() {
		return initial;
	}
	public void setInitial(boolean initial) {
		this.initial = initial;
	}
	
	@Column(name="ISEND", nullable=false)
	public boolean isEnd() {
		return end;
	}
	public void setEnd(boolean end) {
		this.end = end;
	}

	@OneToMany(targetEntity = Transition.class, mappedBy="endState",fetch=FetchType.LAZY)
	@MapKey(name="initialState")
	public Map<State, Transition> getArrivalTransitions() {
		return arrivalTransitions;
	}
	public void setArrivalTransitions(Map<State, Transition> arrivalTransitions) {
		this.arrivalTransitions = arrivalTransitions;
	}
	
	@OneToMany(targetEntity = Transition.class, mappedBy="initialState",fetch=FetchType.LAZY)
	@MapKey(name="endState")
	public Map<State, Transition> getDeparturesTransitions() {
		return departuresTransitions;
	}
	public void setDeparturesTransitions(
			Map<State, Transition> departuresTransitions) {
		this.departuresTransitions = departuresTransitions;
	}
	
	@Embedded
	public InsertAuditor getAuditor() {
		return auditor;
	}
	public void setAuditor(InsertAuditor auditor) {
		this.auditor = auditor;
	}
}
