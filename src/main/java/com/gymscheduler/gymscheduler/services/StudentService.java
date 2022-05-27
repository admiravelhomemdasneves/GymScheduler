package com.gymscheduler.gymscheduler.services;

import com.gymscheduler.gymscheduler.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentService extends CrudRepository<Student, Integer> {
    Iterable<Student> findByPhoneNumber(String phoneNumber);
}
