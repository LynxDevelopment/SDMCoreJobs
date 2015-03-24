package com.lynxspa.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component("loaderJob")
public class FileLoaderJob {

	@Autowired
	private ApplicationContext applicationContext;
	private String inputDirectory = "C:/Temp/input";
	private String outputDirectory = "C:/Temp/done";

	public void run() {

		System.out.println("Hola...");
	}

}
