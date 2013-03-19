package com.smud.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.smud.service.FightService;

public class TickerJob extends QuartzJobBean {

	private static final int FIGHT_PERIOD = 2;
	
	private FightService fightService;
	
	private long executionCount = 0;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		if (executionCount % FIGHT_PERIOD == 0) {
			fightService.executeRound();
		}
		executionCount++;
	}
	
	public void setFightService(FightService fightService) {
		this.fightService = fightService;
	}

}
