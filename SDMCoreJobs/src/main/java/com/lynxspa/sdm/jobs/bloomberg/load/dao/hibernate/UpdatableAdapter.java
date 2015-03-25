package com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate;
import java.io.Serializable;


public interface UpdatableAdapter extends Serializable{

	public int getVersion();
	public void setVersion(int version);
	public UpdateAuditor getAuditor();
	public void setAuditor(UpdateAuditor _auditor);
}
