package com.lynspa.sdm.entities.job;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.lynspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdatableAdapter;
import com.lynspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdateAuditor;

@Entity
@Table(name="TB_SDM_JOBTYPE")
/*
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Table(appliesTo="TB_SDM_JOBTYPES",indexes={
		@Index(name="ID_JOBTYPE", columnNames="ID"),
})
*/
@org.hibernate.annotations.Table(appliesTo="TB_SDM_JOBTYPE")

public class SDMJobType implements UpdatableAdapter{
	
	private static final long serialVersionUID = 2613624285639410058L;
	
	private long id = 0l;
	private int version=0;
	private String name = null;
	private String description = null;
	private String cronExpression = null;
	private String inputDirectory = null;
	private String commitDirectory = null;
	private String rollbackDirectory = null;
	private String temporalSuffix = null;
	private String commitSuffix = null;
	private String rollbackSuffix = null;
	private String pattern = null;
	private String classExe = null;
	private boolean isAllowMultithreading = false;
	private boolean allOrNothing = false;
	private UpdateAuditor auditor=null;
	private SDMEnterprise enterprise=null;
	private String type = null;
	private Map<String,SDMJobTypeFields> fields = new HashMap <String, SDMJobTypeFields>();
	public SDMJobType (){
		super();
		this.auditor=new UpdateAuditor();
	}
	public SDMJobType (String _user){
		super();
		this.auditor=new UpdateAuditor(_user);
	}
	
	@Id
	@SequenceGenerator(name="SDMJOBTYPES_GEN", sequenceName="SDMJOBTYPES_GEN", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="SDMJOBTYPES_GEN")
	@Column(name="ID", nullable=false, updatable=false)
	public long getId() {
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
	
	@Column(name="CRON_EXPRESSION", length=30, nullable=true)
	public String getCronExpression() {
		return cronExpression;
	}
	
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	
	@Column(name="INPUT_DIRECTORY", length=200, nullable=true)
	public String getInputDirectory() {
		return inputDirectory;
	}
	
	public void setInputDirectory(String inputDirectory) {
		this.inputDirectory = inputDirectory;
	}
	
	@Column(name="COMMIT_DIRECTORY", length=200, nullable=true)
	public String getCommitDirectory() {
		return commitDirectory;
	}
	
	public void setCommitDirectory(String commitDirectory) {
		this.commitDirectory = commitDirectory;
	}
	
	@Column(name="ROLLBACK_DIRECTORY", length=200, nullable=true)
	public String getRollbackDirectory() {
		return rollbackDirectory;
	}
	
	public void setRollbackDirectory(String rollbackDirectory) {
		this.rollbackDirectory = rollbackDirectory;
	}
	
	@Column(name="TEMPORAL_SUFFIX", length=20, nullable=true)
	public String getTemporalSuffix() {
		return temporalSuffix;
	}
	
	public void setTemporalSuffix(String temporalSuffix) {
		this.temporalSuffix = temporalSuffix;
	}
	
	@Column(name="COMMIT_SUFFIX", length=20, nullable=true)
	public String getCommitSuffix() {
		return commitSuffix;
	}
	
	public void setCommitSuffix(String commitSuffix) {
		this.commitSuffix = commitSuffix;
	}
	
	@Column(name="ROLLBACK_SUFFIX", length=20, nullable=true)
	public String getRollbackSuffix() {
		return rollbackSuffix;
	}
	
	public void setRollbackSuffix(String rollbackSuffix) {
		this.rollbackSuffix = rollbackSuffix;
	}
	
	@Column(name="PATTERN", length=30, nullable=true)
	public String getPattern() {
		return pattern;
	}
	
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	@Column(name="CLASSEXE", length=50, nullable=true)
	public String getClassExe() {
		return classExe;
	}
	
	public void setClassExe(String classExe) {
		this.classExe = classExe;
	}
	
	@Column(name="IS_ALLOW_MULTITHREADING", length=1, nullable=true)
	public boolean isAllowMultithreading() {
		return isAllowMultithreading;
	}
	
	public void setAllowMultithreading(boolean isAllowMultithreading) {
		this.isAllowMultithreading = isAllowMultithreading;
	}
	
	@Column(name="ALLORNOTHING", length=1, nullable=true)
	public boolean getAllOrNothing() {
		return allOrNothing;
	}
	public void setAllOrNothing(boolean allon){
		this.allOrNothing = allon;
	}
	
	@Embedded
	public UpdateAuditor getAuditor() {
		return auditor;
	}
	public void setAuditor(UpdateAuditor auditor) {
		this.auditor = auditor;
	}
	
	@OneToOne
	@PrimaryKeyJoinColumn
	public SDMEnterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(SDMEnterprise enterprise) {
		this.enterprise = enterprise;
	}
	
	@Column(name="TYPE", length=32, nullable=true)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@OneToMany(targetEntity = SDMJobTypeFields.class, mappedBy="jobType",fetch=FetchType.LAZY)
	@MapKey(name="value")
	public Map<String,SDMJobTypeFields> getFields() {
		return fields;
	}
	public void setFields(Map<String, SDMJobTypeFields> fields) {
		this.fields = fields;
	}
	
	@Transient
	@SuppressWarnings("unchecked")
	public void addField(AssetMessageFieldAdapter field) {
		((Map<String,SDMJobTypeFields>)this.fields).put(field.getPath(),(SDMJobTypeFields)field);
	}
	@Transient
	@SuppressWarnings("unchecked")
	public void addField(String _fieldName, String _value) {
		SDMJobTypeFields field=new SDMJobTypeFields(this,_fieldName,_value);
		((Map<String,SDMJobTypeFields>)this.fields).put(field.getFieldName(),field);
	}
	@Transient
	public String getField(String _path) {
		
		String reply=null;
		SDMJobTypeFields field=null;
		
		field=this.fields.get(_path);
		if(field!=null)
			reply=field.getValue();
		
		return reply;
	}

}
