package com.lynxspa.sdm.entities.application;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdatableAdapter;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdateAuditor;


@Entity
@Table(name = "TB_APPLICATIONS")
public final class Application implements UpdatableAdapter{

	private static final long serialVersionUID = -8581780787125530257L;
	
	
	private String id=null;
	private String name=null;
	private String description=null;
	private BigDecimal softwareVersion=null;
	private Date softwareDate=null;
	private BigDecimal databaseVersion=null;
	private Date databaseDate=null;
	
	private int version=0;
	private UpdateAuditor auditor=null;

	
	public Application(){
		this(null,null,null,null,null,null,null,null);
	}
	public Application(String _user,String _id,String _name){
		this(_user,_id,_name,null,null,null,null,null);
	}
	public Application(String _user,String _id,String _name,String _description){
		this(_user,_id,_name,_description,null,null,null,null);
	}
	public Application(String _user,ApplicationAdapter _adapter){
		this(_user,_adapter.getId(),_adapter.getName(),_adapter.getDescription(),_adapter.getSoftwareVersion(),_adapter.getSoftwareDate(),_adapter.getDatabaseVersion(),_adapter.getDatabaseDate());
	}
	public Application(String _user,String _id,String _name,String _description,BigDecimal _softwareVersion,Date _softwareDate,BigDecimal _databaseVersion,Date _databaseDate){
		super();
		this.id=_id;
		this.name=_name;
		this.description=_description;
		this.softwareVersion=_softwareVersion;
		this.softwareDate=_softwareDate;
		this.databaseVersion=_databaseVersion;
		this.databaseDate=_databaseDate;
		this.auditor=new UpdateAuditor(_user);
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
	
	@Column(name="SOFTWAREVERSION",precision=10,scale=6,nullable=true,updatable=true,insertable=true)
	public BigDecimal getSoftwareVersion() {
		return softwareVersion;
	}
	public void setSoftwareVersion(BigDecimal distributionVersion) {
		this.softwareVersion = distributionVersion;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="SOFTWAREDATE",nullable=true,updatable=true,insertable=true)
	public Date getSoftwareDate() {
		return softwareDate;
	}
	public void setSoftwareDate(Date distributionDate) {
		this.softwareDate = distributionDate;
	}
	
	@Column(name="DATABASEVERSION",precision=10,scale=6,nullable=true,updatable=true,insertable=true)
	public BigDecimal getDatabaseVersion() {
		return databaseVersion;
	}
	public void setDatabaseVersion(BigDecimal databaseVersion) {
		this.databaseVersion = databaseVersion;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATABASEDATE",nullable=true,updatable=true,insertable=true)
	public Date getDatabaseDate() {
		return databaseDate;
	}
	public void setDatabaseDate(Date databaseDate) {
		this.databaseDate = databaseDate;
	}

	
	@Version
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	@Embedded
	public UpdateAuditor getAuditor() {
		return this.auditor;
	}
	public void setAuditor(UpdateAuditor _auditor) {
		this.auditor=_auditor;
	}
	
	
	@Override
	public boolean equals(Object _toCompare) {
		
		boolean reply=false;
		
		if(_toCompare instanceof Application){
			reply=((_toCompare!=null)? this.getId().equals(((Application)_toCompare).getId()) : false);
		}
		
		return reply;
	}
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}
}
