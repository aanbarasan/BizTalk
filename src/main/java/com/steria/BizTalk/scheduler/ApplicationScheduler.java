package com.steria.BizTalk.scheduler;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationScheduler {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("quartzScheduler.xml");
		System.out.println("App finished");
	}
}
