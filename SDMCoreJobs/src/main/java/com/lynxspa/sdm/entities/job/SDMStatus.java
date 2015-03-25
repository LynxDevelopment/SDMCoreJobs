package com.lynxspa.sdm.entities.job;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TB_SDM_STATUS")
//@org.hibernate.annotations.Table(appliesTo="TB_SDM_STATUS",indexes={@Index(name="ID_STATUS", columnNames="ID"),})
@org.hibernate.annotations.Table(appliesTo="TB_SDM_STATUS")

public class SDMStatus {
	
	private long id = 0l;
	private String name = null;
	private String description = null;
	private String cod = null;
	
	@Id
	@SequenceGenerator(name="SDMSTATUS_GEN", sequenceName="SDMSTATUS_GEN", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="SDMSTATUS_GEN")
	@Column(name="ID", nullable=false, updatable=false)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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
	
	@Column(name="CODE", length=50, nullable=true)
	public String getCode() {
		return cod;
	}
	
	public void setCode(String cod) {
		this.cod = cod;
	}
}
