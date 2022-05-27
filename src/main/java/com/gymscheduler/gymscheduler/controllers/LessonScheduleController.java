package com.gymscheduler.gymscheduler.controllers;

import com.gymscheduler.gymscheduler.models.LessonSchedule;
import com.gymscheduler.gymscheduler.services.LessonScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class LessonScheduleController {

    @Autowired
    LessonScheduleService lessonScheduleService;

    @GetMapping("/lessonSchedule")
    Iterable<LessonSchedule> read() {return lessonScheduleService.findAll();}

    @GetMapping("/lessonSchedule/{id}")
    Optional<LessonSchedule> findById(@PathVariable Integer id) {
        return lessonScheduleService.findById(id);
    }

    @PostMapping("/lessonSchedule")
    LessonSchedule create(@Valid @RequestBody LessonSchedule lessonSchedule) {return lessonScheduleService.save(lessonSchedule);}

    @PutMapping("/lessonSchedule")
    ResponseEntity<LessonSchedule> update(@Valid @RequestBody LessonSchedule lessonSchedule) {
        if (lessonScheduleService.findById(lessonSchedule.getIdLessonSchedule()).isPresent())
            return new ResponseEntity(lessonScheduleService.save(lessonSchedule), HttpStatus.OK);
        else
            return new ResponseEntity(lessonSchedule, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/lessonSchedule/{id}")
    ResponseEntity<LessonSchedule> delete(@PathVariable Integer id) {
        lessonScheduleService.deleteById(id);

        return new ResponseEntity(lessonScheduleService.findById(id), HttpStatus.OK);
    }
}
