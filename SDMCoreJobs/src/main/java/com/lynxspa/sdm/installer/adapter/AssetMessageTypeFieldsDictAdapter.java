package com.lynxspa.sdm.installer.adapter;

public interface AssetMessageTypeFieldsDictAdapter{
	public String getPath();
	public String getFieldName();
	public int getFieldLength();
	public String getDisplayGroup();
	public int getDisplayRow();
	public int getDisplayColumn();
	public String getDescription();
	public String getProvider();
	public String getType();
	public boolean getHidden();
}
