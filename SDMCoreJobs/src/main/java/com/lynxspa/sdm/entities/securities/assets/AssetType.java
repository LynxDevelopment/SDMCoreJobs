package com.lynxspa.sdm.entities.securities.assets;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lynxspa.sdm.entities.FiltrableAdapter;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.InsertAuditor;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.InsertableAdapter;


@Entity
@Table(name="TB_SDM_ASSETTYPES")
public class AssetType implements InsertableAdapter, FiltrableAdapter{

	private static final long serialVersionUID = 4334796837806950912L;
	private String id=null;
	private String name=null;
	private InsertAuditor auditor=null;
	
	public AssetType() {
	}
	
	public AssetType(String _user, String _id, String _name) {
		super();
		this.id = _id;
		this.name = _name;
		this.auditor=new InsertAuditor(_user);
	}

	@Id
	@Column(name="ID", length=4, nullable=false, updatable=false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="NAME", length=64, nullable=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
	
		return ToStringBuilder.reflectionToString(this);
	}
	

	@Embedded
	public InsertAuditor getAuditor() {
		return this.auditor;
	}
	public void setAuditor(InsertAuditor _auditor) {
		this.auditor=_auditor;
	}


}
