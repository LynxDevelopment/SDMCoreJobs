package com.lynspa.sdm.jobs.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lynspa.sdm.entities.job.SDMValue;
import com.lynspa.sdm.jobs.bloomberg.load.dao.SDMValueDAO;

public class SaveRowThread extends Thread {

	static Logger logger = Logger.getLogger(SaveRowThread.class.getName());
	private List<SDMValue> values;
	private ApplicationContext applicationContext;

	public SaveRowThread(List<SDMValue> values,
			ApplicationContext applicationContext) {
		this.values = values;
		this.applicationContext = applicationContext;
	}

	public void run() {

		// logger.debug("Ready to save " + values.size() + " values");
		List<List<SDMValue>> rows = sortRows();
		// logger.debug("Sorted Rows");
		Iterator<List<SDMValue>> itRows = rows.iterator();

		while (itRows.hasNext()) {
			// SDMValueDAO valueDao = new SDMValueDAO(this.session);
			SDMValueDAO valueDao = (SDMValueDAO) applicationContext
					.getBean("valueDAO");

			List<SDMValue> row = itRows.next();
			if (row.size() > 0) {
				valueDao.insert(row.get(0));
			}
		}

		logger.debug("Thread [" + this.getId() + "] ha finalizado");
	}

	private List<List<SDMValue>> sortRows() {
		// Sort values per Rows
		List<List<SDMValue>> out = new ArrayList<List<SDMValue>>();

		Iterator<SDMValue> it = values.iterator();
		while (it.hasNext()) {
			SDMValue valueToSort = it.next();
			boolean found = false;
			Iterator<List<SDMValue>> itRows = out.iterator();
			while (!found && itRows.hasNext()) {
				List<SDMValue> rowValues = itRows.next();
				if (rowValues.get(0) != null
						&& rowValues.get(0).getRow().getId() == valueToSort
								.getRow().getId()) {
					found = true;
					rowValues.add(valueToSort);
				}
			}
			if (!found) {
				List<SDMValue> newRow = new ArrayList<SDMValue>();
				newRow.add(valueToSort);
				out.add(newRow);
			}
		}

		return out;
	}
}
