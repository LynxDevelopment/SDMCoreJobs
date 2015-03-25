package com.lynxspa.sdm.entities.securities.assets.message;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;


@Embeddable
public class AssetMessageFieldConfigId implements Serializable {

	private static final long serialVersionUID = -4421614319901668245L;
	private String path=null;
	private AssetMessageType type=null;
	
	public AssetMessageFieldConfigId(){
		this(null,null);
	}
	public AssetMessageFieldConfigId(String _path,AssetMessageType _type){
		super();
		this.path=_path;
		this.type=_type;
	}
	
	@Column(name="PATH", length=128, nullable=false, updatable=false)
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@ManyToOne(targetEntity = AssetMessageType.class,fetch=FetchType.EAGER)
	@ForeignKey(name="FK_FIELD_MESSAGETYPE")
	@JoinColumns({
		@JoinColumn(name="FKPROVIDER", referencedColumnName="FKPROVIDER", nullable=false, updatable=false),
		@JoinColumn(name="FKASSETTYPE", referencedColumnName="FKASSETTYPE", nullable=false, updatable=false)
	})
	public AssetMessageType getType() {
		return type;
	}
	public void setType(AssetMessageType type) {
		this.type = type;
	}
}
