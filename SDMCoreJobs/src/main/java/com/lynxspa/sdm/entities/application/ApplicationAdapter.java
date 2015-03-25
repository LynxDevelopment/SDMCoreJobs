package com.lynxspa.sdm.entities.application;

import java.math.BigDecimal;
import java.util.Date;

public interface ApplicationAdapter{

	public String getId();
	public String getName();
	public String getDescription();
	public BigDecimal getSoftwareVersion();
	public Date getSoftwareDate();
	public BigDecimal getDatabaseVersion();
	public Date getDatabaseDate();
}
