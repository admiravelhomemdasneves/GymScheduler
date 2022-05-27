package com.gymscheduler.gymscheduler.controllers;

import com.gymscheduler.gymscheduler.models.Student;
import com.gymscheduler.gymscheduler.services.StudentService;
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
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/student")
    Iterable<Student> read() {return studentService.findAll();}

    @GetMapping("/student/{id}")
    Optional<Student> findById(@PathVariable Integer id) {
        return studentService.findById(id);
    }

    @GetMapping("/student/search")
    Iterable<Student> findByPhoneNumber(@PathVariable String phoneNumber){
        return studentService.findByPhoneNumber(phoneNumber);
    }

    @PostMapping("/student")
    Student create(@Valid @RequestBody Student student){
            return studentService.save(student);
    }

    @PutMapping("/student")
    ResponseEntity<Student> update(@Valid @RequestBody Student student) {
        if (studentService.findById(student.getIdStudent()).isPresent())
            return new ResponseEntity(studentService.save(student), HttpStatus.OK);
        else
            return new ResponseEntity(student, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/student/{id}")
    ResponseEntity<Student> delete(@PathVariable Integer id) {
        studentService.deleteById(id);

        return new ResponseEntity(studentService.findById(id), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());

        return fieldErrorMessages;
    }
}
