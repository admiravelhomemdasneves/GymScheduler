package com.gymscheduler.gymscheduler.controllers;

import com.gymscheduler.gymscheduler.models.Activity;
import com.gymscheduler.gymscheduler.services.ActivityService;
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
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @GetMapping("/activity")
    Iterable<Activity> read() { return activityService.findAll(); }

    @GetMapping("/activity/{id}")
    Optional<Activity> findById(@PathVariable Integer id) { return activityService.findById(id); }

    @PostMapping("/activity")
    Activity create(@Valid @RequestBody Activity activity) { return activityService.save(activity); }

    @PutMapping("/activity")
    ResponseEntity<Activity> update(@Valid @RequestBody Activity activity) {
        if (activityService.findById(activity.getIdActivity()).isPresent())
            return new ResponseEntity(activityService.save(activity), HttpStatus.OK);
        else
            return new ResponseEntity(activity, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/activity/{id}")
    ResponseEntity<Activity> delete(@PathVariable Integer id) {
        activityService.deleteById(id);

        return new ResponseEntity(activityService.findById(id), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());

        return fieldErrorMessages;
    }
}