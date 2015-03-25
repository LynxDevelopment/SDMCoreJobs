package com.lynxspa.sdm.jobs.bloomberg.load.securities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.lynxspa.sdm.entities.job.SDMJobField;
import com.lynxspa.sdm.entities.job.SDMJobType;
import com.lynxspa.sdm.entities.job.SDMStaticRow;
import com.lynxspa.sdm.entities.job.SDMValue;

public interface ISDMSourceProcessor {

	public List<SDMJobField> getFields(ApplicationContext applicationContext);

	public SDMJobType getJobType();

	public boolean hasMoreRows() throws FileNotFoundException;

	public SDMStaticRow getNextRow() throws FileNotFoundException;

	public List<SDMValue> getValues(SDMStaticRow row,
			ApplicationContext applicationContext);

	public void setFile(File file);

	public File getFile();

	// TODO: check if State is necessary
	//public void setState(State state);

}
