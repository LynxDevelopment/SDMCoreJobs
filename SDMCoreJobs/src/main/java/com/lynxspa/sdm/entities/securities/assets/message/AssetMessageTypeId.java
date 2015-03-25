package com.lynxspa.sdm.entities.securities.assets.message;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;

import com.lynxspa.sdm.entities.securities.assets.AssetType;
import com.lynxspa.sdm.entities.securities.assets.provider.Provider;


@Embeddable
public class AssetMessageTypeId implements Serializable {

	private static final long serialVersionUID = -2219969651539106396L;
	
	private Provider provider=null;
	private AssetType assetType=null;
	
	
	public AssetMessageTypeId(){
		this(null,null);
	}
	
	public AssetMessageTypeId(Provider _provider,AssetType _assetType){
		super();
		this.provider=_provider;
		this.assetType=_assetType;
	}
	
	@ManyToOne(targetEntity = Provider.class)
	@ForeignKey(name="FK_MESSAGETYPE_PROVIDER")
	@JoinColumn(name="FKPROVIDER", nullable=false, updatable=false)
	@Index(name="IX_MESSAGETYPE_PROVIDER")
	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	@ManyToOne(targetEntity = AssetType.class)
	@ForeignKey(name="FK_MESSAGETYPE_ASSETTYPE")
	@JoinColumn(name="FKASSETTYPE", nullable=false, updatable=false)
	@Index(name="IX_MESSAGETYPE_ASSETTYPE")
	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}
	
}
