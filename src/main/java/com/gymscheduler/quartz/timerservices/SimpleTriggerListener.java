package com.gymscheduler.quartz.timerservices;

import com.gymscheduler.quartz.info.TimerInfo;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

public class SimpleTriggerListener implements TriggerListener {
    private final SchedulerService schedulerService;

    public SimpleTriggerListener(SchedulerService schedulerService){
        this.schedulerService = schedulerService;
    }

    @Override
    public String getName() {
        return SimpleTriggerListener.class.getSimpleName();
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
        final String timerId = trigger.getKey().getName();

        final JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        final TimerInfo info = (TimerInfo) jobDataMap.get(timerId);

        if (!info.isRunForever()) {
            int remainingFireCount = info.getRemainingFireCount();
            if (remainingFireCount == 0) {
                return;
            }

            info.setRemainingFireCount(remainingFireCount - 1);
        }

        schedulerService.updateTimer(timerId, info);
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {

    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {

    }
}