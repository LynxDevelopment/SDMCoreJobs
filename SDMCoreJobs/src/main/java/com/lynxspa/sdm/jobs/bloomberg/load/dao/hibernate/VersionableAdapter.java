package com.lynspa.sdm.jobs.bloomberg.load.dao.hibernate;

import java.io.Serializable;

public interface VersionableAdapter extends Serializable,Cloneable{

	public long getId();
	public void setId(long _id);
	public int getVersion();
	public void setVersion(int version);
	public VersionAuditor getAuditor();
	public void setAuditor(VersionAuditor _auditor);
	public Object clone() throws CloneNotSupportedException;
}
