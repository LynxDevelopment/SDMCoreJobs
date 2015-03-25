package com.lynxspa.sdm.entities.job;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;

import com.lynxspa.sdm.entities.application.flow.operation.OperationStatus;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdatableAdapter;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdateAuditor;

@Entity
@Table(name="TB_SDM_ROWS")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DISC_TYPE")
@DiscriminatorValue(value="normal")
public class SDMRow implements UpdatableAdapter{
	
	private static final long serialVersionUID = 2613624285639410058L;
	
	private long id = 0l;
	private int record = 0;
	private int version=0;
	private String value = null;
	private SDMJob job = null;
	private UpdateAuditor auditor = null;
	private List<SDMValue> sdmValues=new ArrayList<SDMValue>();
	private OperationStatus operationStatus=null;
	
	public SDMRow(){
		super();
		this.auditor=new UpdateAuditor();
	}
	
	public SDMRow(String _user){
		super();
		this.auditor=new UpdateAuditor(_user);
		this.operationStatus=new OperationStatus();
	}
	
	@Id
	@SequenceGenerator(name="SDMROW_GEN", sequenceName="SDMROW_GEN", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="SDMROW_GEN")
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
	
	
	@Column(name="VALUE", length=1000, nullable=true)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	@Column(name="RECORD", nullable=true)
	public int getRecord() {
		return record;
	}
	public  void setRecord(int l) {
		this.record = l;
	}
		
	@ManyToOne(targetEntity = SDMJob.class,fetch=FetchType.EAGER)
	@ForeignKey(name="FK_ROW_JOB")
	@JoinColumn(name="FKJOB", nullable=true)
	public SDMJob getJob() {
		return job;
	}

	public void setJob(SDMJob job) {
		this.job = job;
	}

	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="row", fetch=FetchType.LAZY)
	public List<SDMValue> getSdmValues() {
		return sdmValues;
	}
	public void setSdmValues(List<SDMValue> sdmValues) {
		this.sdmValues = sdmValues;
	}

	@Embedded
	public UpdateAuditor getAuditor() {
		return auditor;
	}
	
	public void setAuditor(UpdateAuditor auditor) {
		this.auditor = auditor;
	}
	
	@Embedded
	public OperationStatus getOperationStatus() {
		return operationStatus;
	}
	public void setOperationStatus(OperationStatus operationStatus) {
		this.operationStatus = operationStatus;
	}

}
