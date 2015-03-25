package com.lynxspa.sdm.entities.securities.assets.message;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

import com.lynxspa.sdm.installer.adapter.AssetMessageTypeFieldsDictAdapter;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdatableAdapter;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdateAuditor;
@Entity
@Table(name="TB_SDM_MESSAGEFIELDCONFIGS")
public class AssetMessageFieldConfig implements UpdatableAdapter {

	private static final long serialVersionUID = 801623175004853727L;

	
	private AssetMessageFieldConfigId id=null;
	private String fieldName=null; 
	private int fieldLength=0; 
	private String displayGroup=null; 
	private int displayRow=0; 
	private int displayColumn=0; 
	private String description=null;	
	//private String provider=null;
	//private String type="FOO";
	private boolean hidden=false;
	private int version=0;
	private UpdateAuditor auditor=null;
	
	public AssetMessageFieldConfig(){
		super();
		this.auditor=new UpdateAuditor();
	}
	public AssetMessageFieldConfig(String _user,AssetMessageType _type,String _path){
		super();
		this.id=new AssetMessageFieldConfigId(_path,_type);
		this.auditor=new UpdateAuditor(_user);
	}
	public AssetMessageFieldConfig(String _user,AssetMessageType _type,AssetMessageTypeFieldsDictAdapter _messageFieldConfig){
		super();
		this.id=new AssetMessageFieldConfigId(_messageFieldConfig.getPath()+":"+_type.getId().getAssetType().getId()+":"+_messageFieldConfig.getFieldName(),_type);
		this.fieldName=_messageFieldConfig.getFieldName();
		this.fieldLength=_messageFieldConfig.getFieldLength();
		this.displayGroup=_messageFieldConfig.getDisplayGroup();
		this.displayRow=_messageFieldConfig.getDisplayRow();
		this.displayColumn=_messageFieldConfig.getDisplayColumn();
		this.description=_messageFieldConfig.getDescription();
		this.hidden=_messageFieldConfig.getHidden();
		this.auditor=new UpdateAuditor(_user);	
		//this.provider=_messageFieldConfig.getProvider();
		//this.type=_messageFieldConfig.getType();
	}
	
	@EmbeddedId
	public AssetMessageFieldConfigId getId() {
		return id;
	}
	public void setId(AssetMessageFieldConfigId id) {
		this.id = id;
	}
	
	@Column(name="FIELDNAME", length=64, nullable=false)
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Column(name="FIELDLENGTH", nullable=true)
	public int getFieldLength() {
		return fieldLength;
	}
	public void setFieldLength(int fieldlength) {
		this.fieldLength = fieldlength;
	}

	@Column(name="DISPLAYGROUP", length=64, nullable=true)
	public String getDisplayGroup() {
		return displayGroup;
	}
	public void setDisplayGroup(String displayGroup) {
		this.displayGroup = displayGroup;
	}

	@Column(name="DISPLAYROW", nullable=false)
	public int getDisplayRow() {
		return displayRow;
	}
	public void setDisplayRow(int displayRow) {
		this.displayRow = displayRow;
	}

	@Column(name="DISPLAYCOLUMN", nullable=false)
	public int getDisplayColumn() {
		return displayColumn;
	}
	public void setDisplayColumn(int displayColumn) {
		this.displayColumn = displayColumn;
	}

	@Column(name="DESCRIPTION", length=128, nullable=true)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="HIDDEN", nullable=false)
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
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
	public UpdateAuditor getAuditor() {
		return this.auditor;
	}
	public void setAuditor(UpdateAuditor _auditor) {
		this.auditor=_auditor;
	}

}
