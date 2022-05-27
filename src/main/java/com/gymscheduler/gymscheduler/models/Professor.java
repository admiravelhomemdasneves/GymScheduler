package com.gymscheduler.gymscheduler.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
//@Table(schema = "GymScheduler", name = "GS_PROFESSOR")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProfessor;

    @Size(max=20, message="First name can't have more than 20 characters")
    @NotBlank(message = "First name cannot be blank.")
    @JsonProperty("firstName")
    private String firstName;

    @Size(max=20, message="Last name can't have more than 20 characters")
    @NotBlank(message = "Last name cannot be blank.")
    @JsonProperty("lastName")
    private String lastName;

    @Size(min = 9, max = 9, message="Phone number can only have 9 characters")
    @NotBlank(message = "Phone number cannot be blank.")
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonIgnore
    @JsonProperty("address")
    private String address;

    @JsonIgnore
    @Email
    @JsonProperty("email")
    private String email;

    @JsonIgnore
    @ManyToMany(mappedBy = "programProfessors")
    private List<Program> programs = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "lessonProfessors")
    private List<LessonSchedule> lessonSchedules = new ArrayList<>();
}
