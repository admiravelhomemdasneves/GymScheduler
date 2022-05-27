package com.gymscheduler.quartz.jobs;

import com.gymscheduler.gymscheduler.controllers.ProgramController;
import com.gymscheduler.gymscheduler.models.Program;
import com.gymscheduler.gymscheduler.services.ProgramService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LessonScheduleJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(LessonScheduleJob.class);

    @Autowired
    ProgramService programService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        Integer program_id;

        try {
            program_id = Integer.parseInt(jobDataMap.getString("Program Id"));
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
