package com.lynspa.sdm.entities.securities.assets;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;

import com.lynspa.sdm.entities.securities.assets.adapter.AssetAdapter;
import com.lynspa.sdm.entities.securities.assets.adapter.AssetDetailAdapter;
import com.lynspa.sdm.entities.securities.assets.provider.Provider;
import com.lynspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdatableAdapter;
import com.lynspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdateAuditor;

@Entity
@Table(name="TB_SDM_ASSETS") 
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ASSETCLASS", discriminatorType=DiscriminatorType.STRING, length=32)
public class Asset implements UpdatableAdapter,OperableAdapter,AssetAdapter {

	private static final long serialVersionUID = -2679770394205108773L;
	
	private long id=0l;
	private AssetType assetType=null;
	private Provider provider=null;
	private AssetDetailAdapter assetDetail=null;
	private int version=0;
	private UpdateAuditor auditor=null;
	private OperationStatus operationStatus=null;
	
	public Asset(){
		this(null);
	}
	public Asset(String _user){
		super();
		this.auditor=new UpdateAuditor(_user);
		this.operationStatus=new OperationStatus();
	}
	
	
	@Id
	@SequenceGenerator(name="ASSET_GEN", sequenceName="ASSET_GEN", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="ASSET_GEN")
	@Column(name="ID", nullable=false, updatable=false)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne(targetEntity = AssetType.class,fetch=FetchType.EAGER)
	@ForeignKey(name="FK_ASSET_ASSETTYPE")
	@JoinColumn(name="FKASSETTYPE", nullable=false, updatable=false)
	@Index(name="IX_ASSET_FKASSETTYPE")
	public AssetType getAssetType() {
		return assetType;
	}
	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}
			
	@Version
	@Column(name="VERSION", nullable=false)
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	@Embedded
	public OperationStatus getOperationStatus() {
		return operationStatus;
	}
	public void setOperationStatus(OperationStatus operationStatus) {
		this.operationStatus = operationStatus;
	}
	
	@Embedded
	public UpdateAuditor getAuditor() {
		return auditor;
	}
	public void setAuditor(UpdateAuditor auditor) {
		this.auditor = auditor;
	}
	
	
	@ManyToOne(targetEntity = Provider.class,fetch=FetchType.EAGER)
	@ForeignKey(name="FK_ASSET_PROVIDER")
	@JoinColumn(name="FKASSETPROVIDER",nullable=true,updatable=false)
	@Index(name="IX_FK_ASSET_PROVIDER")
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider _provider) {
		this.provider = _provider;
	}
		
	@ManyToOne(targetEntity = AssetDetails.class,fetch=FetchType.EAGER)
	@ForeignKey(name="FK_ASSET_ASSETDETAIL")
	@JoinColumn(name="FKASSETDETAIL", nullable=true, updatable=false)
	@Index(name="IX_FK_ASSET_ASSETDETAIL")
	public AssetDetailAdapter getAssetDetail() {
		return assetDetail;
	}
	public void setAssetDetail(AssetDetailAdapter _assetDetail) {
		this.assetDetail = _assetDetail;
	}
		
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}