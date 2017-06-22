package com.lyx.batch;

import org.apache.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class ExceptionListener implements JobExecutionListener  {
	private static Logger logger=Logger.getLogger(ExceptionListener.class);
	@Override
	public void afterJob(JobExecution arg0) {
		logger.info("spring batch afterJob");
		
	}

	@Override
	public void beforeJob(JobExecution arg0) {
		// TODO Auto-generated method stub
		logger.info("spring batch beforeJob");
		
	}

}
