package com.gymscheduler.gymscheduler.services;

import com.gymscheduler.gymscheduler.models.Professor;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorService extends CrudRepository<Professor, Integer> {
    Iterable<Professor> findByPhoneNumber(String phoneNumber);
}
