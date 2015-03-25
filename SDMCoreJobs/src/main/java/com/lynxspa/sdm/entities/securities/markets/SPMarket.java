package com.lynxspa.sdm.entities.securities.markets;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Index;

import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdatableAdapter;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdateAuditor;


@Entity
@Table(name="TB_SP_MARKETS")
@org.hibernate.annotations.Table(appliesTo="TB_SP_MARKETS",indexes={
		@Index(name="IDX_MARKET_DELETED",columnNames="ISDELETED"),
		@Index(name="IDX_MARKET_MIC",columnNames="MIC"),
		@Index(name="IDX_MARKET_COUNTRY",columnNames="COUNTRY")
})
public class SPMarket implements UpdatableAdapter{

	private static final long serialVersionUID = 2204375525156562646L;
	
	
	private long id=0l;
	private String mic=null;
	private String ticker=null;
	private String name=null;
	private String country=null;
	private String city=null;
	private String customerId=null;
	private int version=0;
	private UpdateAuditor auditor=null;

	
	public SPMarket (){
		this(null,null,null,null,null,null);
	}
	public SPMarket (String _user,String _mic,String _name){
		this(_user,_mic,_name,null,null,null);
	}
	public SPMarket (String _user,String _mic,String _name,String _country,String _city,String _customerId){
		super();
		this.mic=_mic;
		this.name=_name;
		this.country=_country;
		this.city=_city;
		this.customerId=_customerId;
		this.auditor=new UpdateAuditor(_user);
	}
	
	
	@Id
	@SequenceGenerator(name="SPMARKET_GEN", sequenceName="SPMARKET_GEN", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="SPMARKET_GEN")
	@Column(name="ID", nullable=false, updatable=false)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="MIC", length=4, nullable=false)
	public String getMic() {
		return mic;
	}
	public void setMic(String mic) {
		this.mic = mic;
	}

	@Column(name="TICKER", length=32, nullable=true)
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	@Column(name="NAME", length=128, nullable=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="COUNTRY", length=2, nullable=true)
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Column(name="CITY", length=32, nullable=true)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name="CUSTOMERID", length=64, nullable=true)
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
		return auditor;
	}
	public void setAuditor(UpdateAuditor auditor) {
		this.auditor = auditor;
	}
}
