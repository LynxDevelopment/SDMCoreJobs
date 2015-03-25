package com.lynxspa.sdm.jobs.bloomberg.load.securities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.context.ApplicationContext;

import com.lynxspa.sdm.exception.SDMBasicErrorDictionary;
import com.lynxspa.sdm.exception.SDMException;

public class ImportBBGSecuritiesJob {

	public String importBloombergSecurities(
			ApplicationContext applicationContext, File _file, String _user,
			String _locale) throws SDMException {

		String reply = null;
		boolean error = false;
		File fileTemp = null;

		try {

			SDMJobProcessor process = new SDMJobProcessor();
			fileTemp = new File(_file.getAbsolutePath() + ".tmp");
			_file.renameTo(fileTemp);
			process.process(applicationContext, fileTemp, _user, _locale);

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
			if (error) {
				fileTemp.renameTo(new File(fileTemp.getAbsolutePath()
						+ ".error"));
			} else {
				System.out.println("Renombrando fichero "
						+ fileTemp.getAbsolutePath());
				fileTemp.renameTo(new File(fileTemp.getAbsolutePath() + ".done"));
			}
		}
		return reply;
	}
}
