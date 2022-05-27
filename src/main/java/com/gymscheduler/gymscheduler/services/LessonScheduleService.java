package com.gymscheduler.gymscheduler.services;

import com.gymscheduler.gymscheduler.models.LessonSchedule;
import org.springframework.data.repository.CrudRepository;

public interface LessonScheduleService extends CrudRepository<LessonSchedule, Integer> {
}
