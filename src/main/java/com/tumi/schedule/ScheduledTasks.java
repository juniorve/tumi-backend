package com.tumi.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tumi.service.VisionPersonaQuecServiceImpl;

@Component
public class ScheduledTasks {

	@Autowired
	VisionPersonaQuecServiceImpl visionPersonaQuecServiceImpl;	

	//"0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30, 10:00 and 10:30 every day.
	@Scheduled(cron="0 0 1 * * *")
	public void reportCurrentTime() {
		visionPersonaQuecServiceImpl.tareaRegistrarAPI();
	}
}