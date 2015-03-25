package com.lynxspa.sdm.entities.job;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;

import com.lynxspa.sdm.entities.FiltrableAdapter;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdatableAdapter;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdateAuditor;

@Entity
@Table(name="TB_SDM_ENTERPRISE")
public class SDMEnterprise implements UpdatableAdapter, FiltrableAdapter{
	
	private static final long serialVersionUID = 2613624285639410058L;
	
	private long id = 0l;
	private int version=0;
	private String name = null;
	private String description = null;
	private String bic = null;
	private String address = null;
	private String tlf = null;
	private SDMEnterprise parentEnterprise = null;
	private UpdateAuditor auditor=null;
	
	public SDMEnterprise (){
		super();
		this.auditor=new UpdateAuditor();
	}
	public SDMEnterprise (String _user){
		super();
		this.auditor=new UpdateAuditor(_user);
	}
	
	@Id
	@SequenceGenerator(name="SDMENTERPRISE_GEN", sequenceName="SDMENTERPRISE_GEN", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="SDMENTERPRISE_GEN")
	@Column(name="ID", nullable=false, updatable=false)
	public Long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Version
	@Column(name="VERSION", nullable=false)
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}

	@Column(name="NAME", length=50, nullable=true)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="DESCRIPTION", length=255, nullable=true)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="BIC", length=20, nullable=true)
	public String getBic() {
		return bic;
	}
	
	public void setBic(String bic) {
		this.bic = bic;
	}
	
	@Column(name="ADDRESS", length=255, nullable=true)
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="TELEPHONE", length=12, nullable=true)
	public String getTlf() {
		return tlf;
	}
	
	public void setTlf(String tlf) {
		this.tlf = tlf;
	}
	
	@ManyToOne(targetEntity = SDMEnterprise.class,fetch=FetchType.EAGER)
	@ForeignKey(name="FK_ENT_PARENT")
	@JoinColumn(name="FKENTPARENT", nullable=true)
	public SDMEnterprise getParentEnterprise(){
		return parentEnterprise;
	}
	
	public void setParentEnterprise(SDMEnterprise parentEnterprise){
		this.parentEnterprise = parentEnterprise;
	}
	
	@Embedded
	public UpdateAuditor getAuditor() {
		return auditor;
	}
	
	public void setAuditor(UpdateAuditor auditor) {
		this.auditor = auditor;
	}
	
}
