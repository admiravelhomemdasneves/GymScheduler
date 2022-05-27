package com.gymscheduler.gymscheduler.controllers;

import com.gymscheduler.gymscheduler.models.Professor;
import com.gymscheduler.gymscheduler.services.ProfessorService;
import com.gymscheduler.gymscheduler.util.FieldErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @GetMapping("/professor")
    Iterable<Professor> read() {return professorService.findAll();}

    @GetMapping("/professor/{id}")
    Optional<Professor> findById(@PathVariable Integer id) {
        return professorService.findById(id);
    }

    @GetMapping("/professor/search")
    Iterable<Professor> findByPhoneNumber(@PathVariable String phoneNumber){
        return professorService.findByPhoneNumber(phoneNumber);
    }

    @PostMapping("/professor")
    Professor create(@Valid @RequestBody Professor professor){
        return professorService.save(professor);
    }

    @PutMapping("/professor")
    ResponseEntity<Professor> update(@Valid @RequestBody Professor professor) {
        if (professorService.findById(professor.getIdProfessor()).isPresent())
            return new ResponseEntity(professorService.save(professor), HttpStatus.OK);
        else
            return new ResponseEntity(professor, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/professor/{id}")
    ResponseEntity<Professor> delete(@PathVariable Integer id) {
        professorService.deleteById(id);

        return new ResponseEntity(professorService.findById(id), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());

        return fieldErrorMessages;
    }
}
