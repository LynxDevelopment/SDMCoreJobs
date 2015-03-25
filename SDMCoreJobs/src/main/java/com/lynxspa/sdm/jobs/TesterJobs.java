package com.lynspa.sdm.jobs;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.context.ApplicationContext;

import com.lynspa.sdm.jobs.normalization.BeanShellStaticDataNormalizeProcessor;

public class TesterJobs {
	private static SessionFactory sessionFactory;

	// private final static Logger logger = Logger.getLogger(TesterJobs.class);

	public static void main(String[] args) {

		// BasicConfigurator.configure();

		// logger.debug("Debug tester");

		// logger.log(Level.DEBUG, "debug severe");

		// PropertyConfigurator.configure("log4j-configuration.xml");

		// System.out.println("logger: "+ logger.getLevel() + " " +
		// logger.getName() + " " + logger.isDebugEnabled());

		File hibernateFile = new File(
				"E:/workspace_sdm/SDM/SDMEntities/src/hibernate.cfg.xml");
		sessionFactory = new AnnotationConfiguration().configure(hibernateFile)
				.buildSessionFactory();

		
		private ApplicationContext applicationContext;

		// Probando normalizaciones
		BeanShellStaticDataNormalizeProcessor normalizer = new BeanShellStaticDataNormalizeProcessor();
		try {
			normalizer.normalize(applicationContext, "TEST_USER", "ES");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
