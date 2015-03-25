package com.lynxspa.sdm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.mapping.Array;
import org.quartz.CronTrigger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

	@Autowired
	ApplicationContext applicationContext;
	Scheduler schedulerBean;

	private static final Logger logger = LoggerFactory
			.getLogger(AdminController.class);

	@RequestMapping(value = "/dashboard.do", method = RequestMethod.GET)
	public String dashboardController(Locale locale, Model model)
			throws SchedulerException {

		schedulerBean = (StdScheduler) applicationContext.getBean("quartzBean");
		model = setStardardModalVariables(model);

		return "dashboard";
	}

	@RequestMapping(value = "/scheduler.do", method = RequestMethod.GET)
	public String schedulerController(Locale locale, Model model)
			throws SchedulerException {

		schedulerBean = (StdScheduler) applicationContext.getBean("quartzBean");

		List<List<String>> out = new ArrayList<List<String>>();

		for (String groupName : schedulerBean.getJobGroupNames()) {
			for (JobKey jobKey : schedulerBean.getJobKeys(GroupMatcher
					.jobGroupEquals(groupName))) {
				List<String> values = new ArrayList<String>();
				values.add(jobKey.getName());
				values.add(jobKey.getGroup());
				for (Trigger trig : (List<Trigger>) schedulerBean
						.getTriggersOfJob(jobKey)) {
					if (trig instanceof CronTrigger) {
						values.add(((CronTrigger) trig).getCronExpression());
						values.add(trig.getNextFireTime().toString());
					}

					out.add(values);
				}
			}

		}
		
		model.addAttribute("jobs", out);
		model = setStardardModalVariables(model);

		return "scheduler";
	}

	@RequestMapping(value = "/schedplay.do", method = RequestMethod.GET)
	public String startSchedulerController(Locale locale, Model model)
			throws SchedulerException {

		schedulerBean = (StdScheduler) applicationContext.getBean("quartzBean");
		schedulerBean.start();
		model = setStardardModalVariables(model);

		return "scheduler";
	}

	@RequestMapping(value = "/schedstop.do", method = RequestMethod.GET)
	public String stopSchedulerController(Locale locale, Model model)
			throws SchedulerException {

		schedulerBean = (StdScheduler) applicationContext.getBean("quartzBean");
		schedulerBean.shutdown();
		model = setStardardModalVariables(model);

		return "scheduler";
	}

	@RequestMapping(value = "/schedpause.do", method = RequestMethod.GET)
	public String pauseSchedulerController(Locale locale, Model model)
			throws SchedulerException {

		schedulerBean = (StdScheduler) applicationContext.getBean("quartzBean");
		schedulerBean.standby();
		model = setStardardModalVariables(model);

		return "scheduler";
	}

	private Model setStardardModalVariables(Model model)
			throws SchedulerException {

		model.addAttribute("isStarted", schedulerBean.isStarted());
		model.addAttribute("isInStandBy", schedulerBean.isInStandbyMode());
		model.addAttribute("isStopped", schedulerBean.isShutdown());

		return model;
	}
}
