package com.gymscheduler.gymscheduler.util;

import com.gymscheduler.gymscheduler.models.LessonConfiguration;
import com.gymscheduler.gymscheduler.models.LessonSchedule;
import com.gymscheduler.gymscheduler.models.Program;
import com.gymscheduler.gymscheduler.services.LessonConfigurationService;
import com.gymscheduler.gymscheduler.services.LessonScheduleService;
import com.gymscheduler.gymscheduler.services.ProgramService;
import com.gymscheduler.quartz.jobs.LessonScheduleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class SchedulerActions {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerActions.class);

    @Autowired
    ProgramService programService;
    @Autowired
    LessonConfigurationService lessonConfigurationService;
    @Autowired
    LessonScheduleService lessonScheduleService;

    public int weekDayIndexByDate (Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public List<LessonSchedule> createFutureLessons (Program program) {
        List<LessonSchedule> lessonSchedules = new ArrayList<>();
        int totalDaysToSchedule = 30;

        List<LessonConfiguration> configurations = program.getLessonConfigurations();

        Date today = Calendar.getInstance().getTime();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(today);

        for (int day=0; day<=totalDaysToSchedule; day++) {
            for (int configurationCount=0; configurationCount<=configurations.size()-1; configurationCount++) {
                LessonConfiguration configuration = configurations.get(configurationCount);

                if (Integer.parseInt(configuration.getWeekDay()) == calendar.get(Calendar.DAY_OF_WEEK)) {
                    Date date = calendar.getTime();

                    if (checkScheduleAvailability(date, configuration)) {
                        LessonSchedule lessonSchedule = new LessonSchedule();

                        lessonSchedule.setDateLesson(date);
                        lessonSchedule.setLessonProfessors(program.getProgramProfessors());
                        lessonSchedule.setLessonProgram(program);
                        lessonSchedule.setTimeBegin(configuration.getTimeBegin());
                        lessonSchedule.setTimeEnd(configuration.getTimeEnd());

                        lessonSchedules.add(lessonSchedule);
                    }
                    else
                    {
                        logger.error("There's no availability on date " + date.toString() + " to create schedule for id configuration: " + configuration.getIdLessonConfiguration());
                    };
                }
            }

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return lessonSchedules;
    }

    public Boolean checkScheduleAvailability (Date date, LessonConfiguration configuration) {
        return true;
    }
}
