package com.gymscheduler.gymscheduler.services;

import com.gymscheduler.gymscheduler.models.Activity;
import org.springframework.data.repository.CrudRepository;

public interface ActivityService extends CrudRepository<Activity, Integer> {
}
