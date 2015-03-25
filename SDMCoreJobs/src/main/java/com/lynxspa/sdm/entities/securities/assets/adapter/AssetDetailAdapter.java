package com.lynspa.sdm.entities.securities.assets.adapter;

import java.util.HashMap;

import com.lynspa.sdm.exception.SDMException;

public interface AssetDetailAdapter {
	
	public long getId();
	public void setId(long id);
	

	public HashMap<String,Object> getDynamicTable();
	public void setDynamicTable(HashMap<String,Object> _dynamicTable);
	
	public Object get(String _field);
	public void set(String _field,Object _value) throws SDMException;

//	public Map<String,? extends CAEventDetailExtendedAdapter> getExtensions();
//	public void setExtensions(Map<String,? extends CAEventDetailExtendedAdapter> extensions);
//	public void addExtension(CAEventDetailExtendedAdapter _extension);

	public int getVersion();
	public void setVersion(int version);
	
//	public VersionAuditor getAuditor();
//	public void setAuditor(VersionAuditor auditor);

}
