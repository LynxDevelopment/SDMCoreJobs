package com.lynxspa.sdm.jobs.normalization;

import org.springframework.context.ApplicationContext;

import com.lynxspa.sdm.exception.SDMException;

public class NormalizerJob {

	public String normalize(ApplicationContext applicationContext,
			String _user, String _locale) {
		NormalizeValuesAdapter normalizeProcess = null;
		String reply = "";
		SDMConfigManager manager = null;

		try {
			manager = SDMConfigManager.getInstance();
			normalizeProcess = (NormalizeValuesAdapter) manager.getProcessor(
					applicationContext,
					CAConfiguration.NORMALIZESTATICPROCESSOR);

			normalizeProcess.normalize(applicationContext, _user, _locale);

		} catch (SDMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {

		}

		return reply;
	}
}
