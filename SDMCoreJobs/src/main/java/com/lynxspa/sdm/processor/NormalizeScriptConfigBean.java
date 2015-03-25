package com.lynxspa.sdm.processor;

public class NormalizeScriptConfigBean implements Comparable<NormalizeScriptConfigBean>{

	private String tableFieldPath=null;
	private String script=null;
	private boolean extension=false;
	
	public NormalizeScriptConfigBean (String _tableFieldPath,String _script,boolean _extension){
		this.tableFieldPath=_tableFieldPath;
		this.script=_script;
		this.extension=_extension;
	}

	public String getTableFieldPath() {
		return tableFieldPath;
	}
	public void setTableFieldPath(String tableFieldPath) {
		this.tableFieldPath = tableFieldPath;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	public boolean isExtension() {
		return extension;
	}
	public void setExtension(boolean extension) {
		this.extension = extension;
	}

	public int compareTo(NormalizeScriptConfigBean o) {
		
		int reply=0;
		
		if(o.isExtension()==this.isExtension()){
			reply=this.tableFieldPath.compareTo(o.getTableFieldPath());
		}else{
			if(o.isExtension()) reply=-1;
			else reply=+1;
		}
		
		return reply;
	}
}
