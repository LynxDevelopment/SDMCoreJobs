package com.lynspa.sdm.entities.job;

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
import org.hibernate.annotations.Index;

import com.lynspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdatableAdapter;
import com.lynspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdateAuditor;

@Entity
@Table(name="TB_SDM_FIELDS")
@org.hibernate.annotations.Table(appliesTo="TB_SDM_FIELDS",indexes={
		@Index(name="IDX_SDMFIELDS_NAME",columnNames="NAME"),
		@Index(name="IDX_SDMFIELDS_JOBTYPE",columnNames="FKJOBTYPE")})
public class SDMJobField implements UpdatableAdapter{
	
private static final long serialVersionUID = 2613624285639410058L;
	
	private long id = 0l;
	private int version=0;
	private String name = null;
	private String description = null;
	private String longDescription = null;
	private SDMJobType jobType = null;
	private String fieldType = null;
	private String mask = null;
	private UpdateAuditor auditor = null;
	private String path=null;
	
	@Id
	@SequenceGenerator(name="SDMFIELDS_GEN", sequenceName="SDMFIELDS_GEN", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="SDMFIELDS_GEN")
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

	@Column(name="DESCRIPTION", length=50, nullable=true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="LONG_DESCRIPTION", length=200, nullable=true)
	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String long_description) {
		this.longDescription = long_description;
	}

	@ManyToOne(targetEntity = SDMJobType.class,fetch=FetchType.EAGER)
	@ForeignKey(name="FK_JOBFIELD_JOBTYPE")
	@JoinColumn(name="FKJOBTYPE", nullable=true)
	public SDMJobType getJobType() {
		return jobType;
	}

	public void setJobType(SDMJobType jobType) {
		this.jobType = jobType;
	}

	@Column(name="FIELDTYPE", length=50, nullable=true)
	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	@Column(name="MASK", length=50, nullable=true)
	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}
	
	@Embedded
	public UpdateAuditor getAuditor() {
		return auditor;
	}
	
	public void setAuditor(UpdateAuditor auditor) {
		this.auditor = auditor;
	}

	@Column(name="PATH", length=128, nullable=false, updatable=false)
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
