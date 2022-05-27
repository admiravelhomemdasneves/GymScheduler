package com.gymscheduler.gymscheduler.controllers;

import com.gymscheduler.gymscheduler.models.Program;
import com.gymscheduler.gymscheduler.services.LessonConfigurationService;
import com.gymscheduler.gymscheduler.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ProgramController {

    @Autowired
    ProgramService programService;

    @Autowired
    LessonConfigurationService lessonConfigurationService;

    @GetMapping("/program")
    Iterable<Program> read() {return programService.findAll();}

    @GetMapping("/program/{id}")
    Optional<Program> findById(@PathVariable Integer id) {return programService.findById(id);}

    @PostMapping("/program")
    Program create(@Valid @RequestBody Program program) {
        if (program.getLessonConfigurations() != null) {
            program.getLessonConfigurations().stream().forEach(lc -> lc.setConfigurationProgram(program));
            program.getLessonConfigurations().stream().forEach(lc -> lessonConfigurationService.save(lc));

            return programService.save(program);
        }
        else
        {
            return program;
        }
    }

    @PutMapping("/program")
    ResponseEntity<Program> update(@Valid @RequestBody Program program) {
        if (programService.findById(program.getIdProgram()).isPresent()) {
            program.setDateCreation(programService.findById(program.getIdProgram()).get().getDateCreation());
            return new ResponseEntity(programService.save(program), HttpStatus.OK);
        }
        else
            return new ResponseEntity(program, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/program/{id}")
    ResponseEntity<Program> delete(@PathVariable Integer id) {
        programService.deleteById(id);

        return new ResponseEntity("Program " + id + " has been deleted.", HttpStatus.OK);
    }
}
