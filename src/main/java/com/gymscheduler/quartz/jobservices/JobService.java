package com.gymscheduler.quartz.jobservices;

import com.gymscheduler.quartz.info.TimerInfo;
import com.gymscheduler.quartz.jobs.HelloWorldJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gymscheduler.quartz.timerservices.SchedulerService;

import java.util.List;

@Service
public class JobService {
    private final SchedulerService scheduler;

    @Autowired
    public JobService(final SchedulerService scheduler) {
        this.scheduler = scheduler;
    }

    public void runHelloWorldJob() {
        final TimerInfo info = new TimerInfo();
        info.setTotalFireCount(5);
        info.setRemainingFireCount(info.getTotalFireCount());
        info.setRepeatIntervalMs(2000);
        info.setInitialOffsetMs(1000);
        info.setCallbackData("My callback data!");

        scheduler.schedule(HelloWorldJob.class, info);
    }

    public List<TimerInfo> getAllRunningTimers() {
        return scheduler.getAllRunningTimers();
    }

    public TimerInfo getRunningTimer(String timerId){
        return scheduler.getRunningTimer(timerId);
    }

    public Boolean deleteTimer (final String timerId) {
        return scheduler.deleteTimer(timerId);
    }
}
