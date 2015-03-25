package com.lynxspa.sdm.entities.application.flow.operation;


public interface OperableAdapter {

	public long getId();
	public void setId(final long _id);
	public OperationStatus getOperationStatus();
	public void setOperationStatus(final OperationStatus _status);
}
