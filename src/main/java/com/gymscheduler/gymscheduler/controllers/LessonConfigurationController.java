package com.gymscheduler.gymscheduler.controllers;

import com.gymscheduler.gymscheduler.models.LessonConfiguration;
import com.gymscheduler.gymscheduler.services.LessonConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class LessonConfigurationController {

    @Autowired
    LessonConfigurationService lessonConfigurationService;

    @GetMapping("/lessonConfiguration")
    Iterable<LessonConfiguration> read() {return lessonConfigurationService.findAll();}

    @GetMapping("/lessonConfiguration/{id}")
    Optional<LessonConfiguration> findById(@PathVariable Integer id) {return lessonConfigurationService.findById(id);}

    @PostMapping("/lessonConfiguration")
    LessonConfiguration create(@Valid @RequestBody LessonConfiguration lessonConfiguration) {return lessonConfigurationService.save(lessonConfiguration);}

    @DeleteMapping("/lessonConfiguration/{id}")
    ResponseEntity<LessonConfiguration> delete(@PathVariable Integer id) {
        lessonConfigurationService.deleteById(id);

        return new ResponseEntity("Lesson Configuration " + id + " has been deleted.", HttpStatus.OK);
    }
}
