package com.gymscheduler.gymscheduler.controllers;

import com.gymscheduler.gymscheduler.models.LessonSchedule;
import com.gymscheduler.gymscheduler.models.Program;
import com.gymscheduler.gymscheduler.services.LessonScheduleService;
import com.gymscheduler.gymscheduler.services.ProgramService;
import com.gymscheduler.gymscheduler.util.SchedulerActions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/scheduling")
public class SchedulerActionsController {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerActionsController.class);

    private SchedulerActions schedulerActions = new SchedulerActions();

    @Autowired
    ProgramService programService;
    @Autowired
    LessonScheduleService lessonScheduleService;

    @PostMapping("/byProgram")
    List<LessonSchedule> create(@RequestBody Program program) {
        List<LessonSchedule> lessonSchedules = schedulerActions.createFutureLessons(program);
        lessonSchedules.stream().forEach(lessonSchedule -> lessonScheduleService.save(lessonSchedule));

        return lessonSchedules;
    }
}
