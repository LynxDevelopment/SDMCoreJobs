package com.lynxspa.sdm.entities.application.flow;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;

import com.lynxspa.sdm.entities.application.Application;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.InsertAuditor;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.InsertableAdapter;


@Entity
@Table(name = "TB_FLOWS")
@org.hibernate.annotations.Table(appliesTo="TB_FLOWS",indexes={
		@Index(name="IDX_STATE_FKAPPLICATION",columnNames="FKAPPLICATION")
})
public final class Flow implements InsertableAdapter{

	private static final long serialVersionUID = -3297383954443035144L;
	
	
	private String id=null;
	private String name=null;
	private String description=null;
	private Application application=null;
	private InsertAuditor auditor=null;

	
	public Flow (){
		this(null,null,null,null,null);
	}
	public Flow (String _id){
		this(_id,null,null,null,null);
	}
	public Flow (String _id,String _name,Application _application,String _user){
		this(_id,_name,null,_application,_user);
	}
	public Flow (String _id,String _name,String _description,Application _application,String _user){
		super();
		this.id=_id;
		this.name=_name;
		this.description=_description;
		this.application=_application;
		this.auditor=new InsertAuditor(_user);
	}

	
	@Id
	@Column(name="ID", length=16, nullable=false, updatable=false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
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

	@ManyToOne(targetEntity = Application.class,fetch=FetchType.EAGER)
	@ForeignKey(name="FK_FLOW_APPLICATION")
	@JoinColumn(name="FKAPPLICATION", nullable=false)
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	
	@Embedded
	public InsertAuditor getAuditor() {
		return auditor;
	}
	public void setAuditor(InsertAuditor auditor) {
		this.auditor = auditor;
	}
	
	@Override
	public boolean equals(Object _toCompare) {
		
		boolean reply=false;
		Flow newFlow=null;
		
		if(_toCompare instanceof Flow){
			newFlow=(Flow)_toCompare;
			if((newFlow!=null)&&(this.getId()!=null)&&(this.getId().equals(newFlow.getId()))){
				reply=true;
			}
		}
		
		return reply;
	}
	@Override
	public int hashCode() {
		
		return this.getId().hashCode();
	}
}
