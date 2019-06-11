package com.steria.BizTalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = { "com.steria.BizTalk" })
@ImportResource("classpath:quartzScheduler.xml")
public class BizTalkApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BizTalkApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BizTalkApplication.class);
	}
}
