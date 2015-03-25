package com.lynspa.sdm.jobs.normalization;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.lynspa.sdm.entities.job.SDMRow;
import com.lynspa.sdm.processor.NormalizeScriptConfigBean;

public interface NormalizeValuesAdapter {

	public NormalizeValueResultBean test(ApplicationContext applicationContext,
			List<NormalizeScriptConfigBean> _scripts, SDMRow row)
			throws Exception;

	public void normalize(ApplicationContext applicationContext, String _user,
			String _locale) throws Exception;

}
