package com.lynspa.sdm.entities.securities.assets.adapter;

import com.lynspa.sdm.entities.securities.assets.AssetType;

public interface AssetAdapter {
	
	public long getId();
	public void setId(long id);

	public int getVersion();
	public void setVersion(int version);

	public AssetType getAssetType();
	public void setAssetType(AssetType assetType);

	public OperationStatus getOperationStatus();
	public void setOperationStatus(OperationStatus operationStatus);
	//public VersionAuditor getAuditor();
	//public void setAuditor(VersionAuditor auditor);

}
