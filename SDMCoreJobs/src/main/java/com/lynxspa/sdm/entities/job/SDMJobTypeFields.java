package com.lynxspa.sdm.entities.job;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;

@Entity
@Table(name="TB_SDM_JOBTYPE_FIELDS")
public class SDMJobTypeFields {
	private long id = 0l;
	private String fieldName = null;
	private String value = null;
	private SDMJobType jobType=null;
	
	public SDMJobTypeFields(){

	}
	
	public SDMJobTypeFields(SDMJobType _jobType, String _fieldName,String _value){
		super();
		setFieldName(_fieldName);
		setValue(_value);
		setJobType(_jobType);
	}
	
	@Id
	@SequenceGenerator(name="SDMJOBTYPESFIELDS_GEN", sequenceName="SDMJOBTYPESFIELDS_GEN", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="SDMJOBTYPESFIELDS_GEN")
	@Column(name="ID", nullable=false, updatable=false)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="FIELDNAME", length=32)
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	@Column(name="VALUE", length=64)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@ManyToOne(targetEntity = SDMJobType.class,fetch=FetchType.EAGER)
	@ForeignKey(name="FK_JOBTYPE")
	@JoinColumn(name="FKJOBTYPE", nullable=true, updatable=false)
	@Index(name="IX_FK_JOBTYPE_FIELD")
	public SDMJobType getJobType() {
		return jobType;
	}
	public void setJobType(SDMJobType jobType) {
		this.jobType = jobType;
	}
	
	
}
