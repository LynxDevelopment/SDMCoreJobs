package com.lynspa.sdm.entities.securities.assets.provider;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.lynxspa.entities.UpdatableAdapter;
import com.lynxspa.entities.UpdateAuditor;
import com.lynxspa.sdm.interfaces.FiltrableAdapter;

@Entity
@Table(name="TB_SDM_PROVIDERS") 
public class Provider implements UpdatableAdapter,FiltrableAdapter{
	
	private static final long serialVersionUID = 2543656143129423678L;
	private Long id=null;
	private String code=null;
	private String name=null;
	private String nature=null; // Indicate if nature is static or dynamic
	boolean isDefaultProvider;
	private int version=0;
	private UpdateAuditor auditor=null;
	
	public Provider(){
		super();
	}

	public Provider(String _code,String _name,String _nature,String _user,boolean _isDefaultProvider){
		super();
		this.code=_code;
		this.name=_name;
		this.nature=_nature;
		this.isDefaultProvider=_isDefaultProvider;
		this.auditor=new UpdateAuditor(_user);
	}
	
	@Id
	@SequenceGenerator(name="SEQ_TB_SDM_PROVIDER_GEN", sequenceName="SEQ_TB_SDM_PROVIDER_GEN", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ_TB_SDM_PROVIDER_GEN")
	@Column(name="ID", nullable=false, updatable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name="CODE", length=30, nullable=false,unique=true)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="NAME", length=128, nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="NATURE", length=1, nullable=false)
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	@Version
	@Column(name="VERSION", nullable=false)
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	@Embedded
	public UpdateAuditor getAuditor() {
		return auditor;
	}
	public void setAuditor(UpdateAuditor auditor) {
		this.auditor = auditor;
	}
	@Column(name="ISDEFAULTPROVIDER", nullable=false)
	public boolean isDefaultProvider() {
		return isDefaultProvider;
	}

	public void setDefaultProvider(boolean isDefaultProvider) {
		this.isDefaultProvider = isDefaultProvider;
	}
	
}
