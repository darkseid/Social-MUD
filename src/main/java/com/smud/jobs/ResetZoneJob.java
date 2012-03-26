package com.smud.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.smud.model.Zone;

public class ResetZoneJob extends QuartzJobBean {

	private Zone zone;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		zone.reset();
	}
	
	public void setZone(Zone zone) {
		this.zone = zone;
	}

}
