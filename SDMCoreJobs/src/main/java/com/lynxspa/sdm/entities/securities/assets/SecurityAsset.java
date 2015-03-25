package com.lynspa.sdm.entities.securities.assets;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

import com.lynxspa.entities.securities.markets.SPMarket;

@Entity
@DiscriminatorValue("security")
public class SecurityAsset extends Asset {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4484294103862825854L;
	private String isin;
	private String name;
	private String cusip=null;
	private String sedol=null;
	private SPMarket market;
	private String country= null;
	private Long securityMessageId;
	private String concatField;
	private long groupId = 0l;
	private int percentageGrouping = 0;
	private boolean completed = false;
	
	public SecurityAsset (){
		super();
	}
	
	public SecurityAsset (String _user){
		super(_user);
	}
	@Column(name="ISIN", length=12, nullable=true)
	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}
	
	@ManyToOne(targetEntity = SPMarket.class,fetch=FetchType.EAGER)
	@ForeignKey(name="FK_SECURITYASSET_MARKET")
	@JoinColumn(name="FKMARKET", nullable=true)
	public SPMarket getMarket() {
		return market;
	}
	public void setMarket(SPMarket _market) {
		this.market = _market;
	}
	
	
	@Column(name="COUNTRY", length=2)
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Column(name="CUSIP", length=9, nullable=true)
	public String getCusip() {
		return cusip;
	}
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	@Column(name="SEDOL", length=7, nullable=true)
	public String getSedol() {
		return sedol;
	}
	public void setSedol(String sedol) {
		this.sedol = sedol;
	}

	@Column(name="NAME", length=64, nullable=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="SECURITYMESSAGE_ID")
	public Long getSecurityMessageId() {
		return securityMessageId;
	}
	public void setSecurityMessageId(Long securityMessageId) {
		this.securityMessageId = securityMessageId;
	}
	
	@Column(name="CONCATFIELD")
	public String getConcatField() {
		return concatField;
	}

	public void setConcatField(String concatField) {
		this.concatField = concatField;
	}
	
	@Column(name="GROUP_ID", columnDefinition = "long default 0")
	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	@Column(name="PERCENTAGE_GROUPING", columnDefinition = "long default 0")
	public int getPercentageGrouping() {
		return percentageGrouping;
	}

	public void setPercentageGrouping(int percentageGrouping) {
		this.percentageGrouping = percentageGrouping;
	}
	
	@Column(name="ISCOMPLETED", nullable=false)
	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
}
