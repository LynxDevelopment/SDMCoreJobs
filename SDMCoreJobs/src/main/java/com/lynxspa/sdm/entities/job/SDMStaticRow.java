package com.lynspa.sdm.entities.job;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="security")
public class SDMStaticRow extends SDMRow {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5530473627707875873L;
	private String securityIsin;
	private String securityTicker;
	private String securityMic;
	private String securityName;
	
	@Column(name="ISIN",length=15)
	public String getSecurityIsin() {
		return securityIsin;
	}
	public void setSecurityIsin(String securityIsin) {
		this.securityIsin = securityIsin;
	}

	@Column(name="TICKER",length=32)
	public String getSecurityTicker() {
		return securityTicker;
	}

	public void setSecurityTicker(String securityTicker) {
		this.securityTicker = securityTicker;
	}

	@Column(name="MIC",length=4)
	public String getSecurityMic() {
		return securityMic;
	}

	public void setSecurityMic(String securityMic) {
		this.securityMic = securityMic;
	}

	@Column(name="NAME",length=128)
	public String getSecurityName() {
		return securityName;
	}

	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}

}
