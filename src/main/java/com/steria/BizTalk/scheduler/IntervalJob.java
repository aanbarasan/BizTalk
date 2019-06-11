package com.steria.BizTalk.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.steria.BizTalk.service.TripReadService;

@Component
public class IntervalJob implements Job {

	@Value("${processed.folder.path}")
	private String processedDirPath;

	private String UnprocessedDirPath;

	@Autowired
	TripReadService tripReadObj;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		if (tripReadObj == null) {
			try {
				ApplicationContext applicationContext = (ApplicationContext) context.getScheduler().getContext()
						.get("applicationContext");
				tripReadObj = applicationContext.getBean(TripReadService.class);
				processedDirPath = applicationContext.getEnvironment().getProperty("processed.folder.path");
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}

		tripReadObj.readFiles(processedDirPath, "process");
		tripReadObj.readFiles(UnprocessedDirPath, "unprocess");

	}

}
