package com.gymscheduler.gymscheduler.services;

import com.gymscheduler.gymscheduler.models.LessonConfiguration;
import org.springframework.data.repository.CrudRepository;

public interface LessonConfigurationService extends CrudRepository<LessonConfiguration, Integer> {
}
