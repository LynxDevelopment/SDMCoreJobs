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

import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdatableAdapter;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdateAuditor;

@Entity
@Table(name="TB_SDM_JOBS")
/*@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Table(appliesTo="TB_SDM_JOBS",indexes={
		@Index(name="ID_JOB", columnNames="ID"),
		@Index(name="IDX_JOBTYPE", columnNames="FK_JOBTYPE")
*/
@org.hibernate.annotations.Table(appliesTo="TB_SDM_JOBS")

public class SDMJob implements UpdatableAdapter{
	
	private static final long serialVersionUID = 2613624285639410058L;
	
	private long id = 0l;
	private int version=0;
	private String file = null;
	private int numRecords = 0;
	private int numSuccess = 0;
	private int numFailed = 0;
	private UpdateAuditor auditor = null;
	private SDMJobType jobType = null;
	
	public SDMJob (){
		super();
		this.auditor=new UpdateAuditor();
	}
	public SDMJob (String _user){
		super();
		this.auditor=new UpdateAuditor(_user);
	}
	
	@Id
	@SequenceGenerator(name="SDMJOBS_GEN", sequenceName="SDMJOBS_GEN", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="SDMJOBS_GEN")
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
	
	@Column(name="FILE_PATH", length=100, nullable=true)
	public String getFile() {
		return file;
	}
	
	public void setFile(String file) {
		this.file = file;
	}

	@Column(name="NRECORDS", nullable=true)
	public int getNumRecords() {
		return numRecords;
	}
	
	public void setNumRecords(int numRecords) {
		this.numRecords = numRecords;
	}

	@Column(name="NSUCCESS", nullable=true)
	public int getNumSuccess() {
		return numSuccess;
	}
	
	public void setNumSuccess(int numSuccess) {
		this.numSuccess = numSuccess;
	}
	
	@Column(name="NFAILED", nullable=true)
	public int getNumFailed() {
		return numFailed;
	}
	
	public void setNumFailed(int numFailed) {
		this.numFailed = numFailed;
	}
	
	@Embedded
	public UpdateAuditor getAuditor() {
		return auditor;
	}
	public void setAuditor(UpdateAuditor auditor) {
		this.auditor = auditor;
	}
	
	@ManyToOne(targetEntity = SDMJobType.class,fetch=FetchType.EAGER)
	@ForeignKey(name="FK_JOB_JOBTYPE")
	@JoinColumn(name="FKJOBTYPE", nullable=true)
	public SDMJobType getJobType() {
		return jobType;
	}
	
	public void setJobType(SDMJobType jobType) {
		this.jobType = jobType;
	}
}
