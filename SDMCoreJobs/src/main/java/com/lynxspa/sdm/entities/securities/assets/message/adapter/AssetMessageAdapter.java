package com.lynxspa.sdm.entities.securities.assets.message.adapter;

import java.util.Date;
import java.util.Map;

import com.lynxspa.sdm.entities.securities.assets.AssetType;
import com.lynxspa.sdm.entities.securities.assets.provider.Provider;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdateAuditor;

public interface AssetMessageAdapter {
	
	public long getId();
	public void setId(long id);
	
	public int getVersion();
	public void setVersion(int version);

	public long getNormalizedAsset();
	public void setNormalizedAsset(long normalizedAsset);
	public String getOriginId();
	public void setOriginId(String originId);
	public String getOriginName();
	public void setOriginName(String originName);
	public String getOriginType();
	public void setOriginType(String originType);
	public int getOriginPosition();
	public void setOriginPosition(int originPosition);
	public String getMessageId();
	public void setMessageId(String messageId);
	public String getAssetType();
	public void setAssetType(String assetType);
	public String getSender();
	public void setSender(String sender);
	public boolean isExtension();
	public void setExtension(boolean extension);
	public String getReceiver();
	public void setReceiver(String receiver);
	public String getSecurityType();
	public void setSecurityType(String securityType);
	public String getSecurity();
	public void setSecurity(String security);
	public String getSecurityName();
	public void setSecurityName(String securityName);
	public Date getSenderTimestamp();
	public void setSenderTimestamp(Date senderTimestamp);
	public String getOriginalMessage();
	public void setOriginalMessage(String originalMessage);
	public AssetType getNormalizedAssetType();
	public void setNormalizedAssetType(AssetType normalizedAssetType);
//	public SPVirtualSecurity getNormalizedSecurity();
//	public void setNormalizedSecurity(SPVirtualSecurity normalizedSecurity);
	public Provider getProvider();
	public void setProvider(Provider provider);
	public boolean isMainMarket();
	public void setMainMarket(boolean mainMarket);
	public boolean isInCustomerPortfolio();
	public void setInCustomerPortfolio(boolean inCustomerPortfolio);

	public OperationStatus getOperationStatus();
	public void setOperationStatus(OperationStatus operationStatus);
	public UpdateAuditor getAuditor();
	public void setAuditor(UpdateAuditor _auditor);

	public Map<String,? extends AssetMessageFieldAdapter> getFields();
	public void setFields(Map<String,? extends AssetMessageFieldAdapter> fields);
	public void addField(AssetMessageFieldAdapter field);
	public void addField(String _path,String _value);
	public String getField(String _path);

}
