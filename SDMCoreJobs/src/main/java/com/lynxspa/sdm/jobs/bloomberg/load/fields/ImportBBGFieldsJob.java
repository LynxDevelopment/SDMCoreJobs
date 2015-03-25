package com.lynxspa.sdm.jobs.bloomberg.load.fields;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.context.ApplicationContext;

import com.lynxspa.sdm.exception.SDMBasicErrorDictionary;
import com.lynxspa.sdm.exception.SDMException;

public class ImportBBGFieldsJob {

	public String importBloombergFields(ApplicationContext applicationContext,
			File _file, String _user, String _locale) throws SDMException {

		String reply = null;
		boolean error = false;
		File fileTemp = null;
		try {

			LoadFieldsBBG process = new LoadFieldsBBG();
			fileTemp = new File(_file.getAbsolutePath() + ".tmp");
			_file.renameTo(fileTemp);
			process.loadFieldsFromFile(fileTemp, applicationContext, _user);

		} catch (FileNotFoundException e) {
			error = true;
			throw new SDMException(SDMBasicErrorDictionary.FILENOTEXIST, e);
		} catch (IOException e) {
			error = true;
			throw new SDMException(SDMBasicErrorDictionary.FILECANTREAD, e);
		} catch (Exception e) {
			error = true;
			throw new SDMException(SDMBasicErrorDictionary.UNEXPECTED_ERROR, e);
		} finally {
			if (error)
				fileTemp.renameTo(new File(fileTemp.getAbsolutePath()
						+ ".error"));
			else
				fileTemp.renameTo(new File(fileTemp.getAbsolutePath() + ".done"));
		}
		return reply;
	}
}
