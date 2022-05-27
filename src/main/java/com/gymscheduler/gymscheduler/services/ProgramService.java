package com.gymscheduler.gymscheduler.services;

import com.gymscheduler.gymscheduler.models.Program;
import org.springframework.data.repository.CrudRepository;

public interface ProgramService extends CrudRepository<Program, Integer> {
}
