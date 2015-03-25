package com.lynxspa.sdm.jobs.bloomberg.load.securities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lynxspa.sdm.entities.job.SDMJob;
import com.lynxspa.sdm.entities.job.SDMStaticRow;
import com.lynxspa.sdm.entities.job.SDMValue;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.SDMJobDAO;
import com.lynxspa.sdm.jobs.bloomberg.load.dao.hibernate.UpdateAuditor;
import com.lynxspa.sdm.jobs.util.SDMUtils;

public class SDMJobProcessor {

	static Logger logger = Logger.getLogger(SDMJobProcessor.class.getName());

	private ISDMSourceProcessor processor;
	private SDMJob job;
	private int saveInDBEvery;
	private ApplicationContext applicationContext;
	private int numRows;
	private int correctRows;
	private int failedRows;
	private String user;

	public void process(ApplicationContext applicationContext, File file,
			String _user, String _locale) throws FileNotFoundException {

		numRows = 0;
		correctRows = 0;
		failedRows = 0;
		saveInDBEvery = 100;
		user = _user;
		this.applicationContext = applicationContext;

		HashMap<String, String> jobData = new HashMap<String, String>();
		jobData.put("name", "BBGSecurities");
		jobData.put("user", _user);

		job = new SDMJob();
		if (file.exists()) {
			job.setFile(file.getAbsolutePath());
		}
		String path = file.getAbsolutePath().substring(0,
				file.getAbsolutePath().lastIndexOf('\\'));
		System.out.println("Path:" + path);
		job.setJobType(SDMUtils.getJobType(applicationContext, jobData, path));
		job.setAuditor(new UpdateAuditor(_user));

		processor = new SDMBBGProcessor(file, SDMUtils.getState(_session,
				StaticStatesSTATICMESSAGEFlow.PRSD.getId()), user);
		processStart();
	}

	public boolean processStart() throws FileNotFoundException {
		logger.debug("START PROCESS");

		boolean result = true;
		SDMJobDAO jobDao = new SDMJobDAO();

		try {
			jobDao.persist(job, statelessSession);

			if (saveInDBEvery == 0) {
				saveInDBEvery = 1;
			}

			saveRowsAndValues();
			updateJob();
		} catch (Exception e) {
			System.out.println("Error " + e);
		} finally {
			finalizeProcess();
		}

		return result;
	}

	private void finalizeProcess() {
		try {
			System.out.println("Cerrando Sesiones");
			if (!statelessSession.connection().isClosed())
				statelessSession.close();
			if (session.isOpen())
				session.close();
			System.out.println("Cerradas");
		} catch (Exception e) {
			System.err.println("Error cerrando sesiones" + e.getMessage());
			e.printStackTrace();
		}
	}

	private void saveRowsAndValues() throws FileNotFoundException {
		int count = 0;
		List<SDMValue> values = new ArrayList<SDMValue>();
		long processingTime = 0l;
		long processingTimeCommit = 0l;

		if (processor.getFile().exists()) {
			processingTime = System.currentTimeMillis();
			try {
				HibernateUtils.beguinTransaction(statelessSession);

				while (processor.hasMoreRows()) {
					values = new ArrayList<SDMValue>();
					SDMStaticRow row = processor.getNextRow();
					try {
						row.setJob(job);
						HibernateUtils.customSave(statelessSession, row, user);

						values.addAll(processor
								.getValues(row, statelessSession));
						if (values.size() > 0) {
							for (SDMValue value : values) {
								HibernateUtils.customSave(statelessSession,
										value, user);
							}
						}
						if (count == saveInDBEvery) {
							processingTimeCommit = processingTimeCommit
									- System.currentTimeMillis();
							System.out.println("Tiempo en insertar 100"
									+ processingTimeCommit);
							HibernateUtils.commitTransaction(statelessSession);
							HibernateUtils.beguinTransaction(statelessSession);
							count = 0;
							processingTimeCommit = System.currentTimeMillis();
						}
						correctRows++;
					} catch (Exception e) {
						System.err.println(e.getMessage());
						failedRows++;
					}
					HibernateUtils.commitTransaction(statelessSession);
					numRows++;
					count++;
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			processingTime = System.currentTimeMillis() - processingTime;
			System.out.println("Tiempo en guardar rows y values: "
					+ processingTime + " para " + numRows + " y " + failedRows
					+ " erroneos");
		} else {
			// TODO Servicio de log para guardar en tabla TB_LOGS
			throw new FileNotFoundException(
					"El archivo de carga no existe para el job: " + job.getId());
		}
	}

	private void updateJob() {

		job.setNumFailed(failedRows);
		job.setNumRecords(numRows);
		job.setNumSuccess(correctRows);
		SDMJobDAO jobDao = new SDMJobDAO();
		jobDao.update(job, statelessSession);

	}
}
