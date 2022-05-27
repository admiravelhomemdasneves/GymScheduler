package com.gymscheduler.gymscheduler.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
//@Table(schema = "GymScheduler", name = "GS_STUDENT")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStudent;

    @Size(max=20, message="First name can't have more than 20 characters")
    @NotBlank(message = "First name cannot be blank.")
    @JsonProperty("first-name")
    private String firstName;

    @Size(max=20, message="Last name can't have more than 20 characters")
    @NotBlank(message = "Last name cannot be blank.")
    @JsonProperty("last-name")
    private String lastName;

    @Size(min = 9, max = 9, message="Phone number can only have 9 characters")
    @NotBlank(message = "Phone number cannot be blank.")
    @JsonProperty("phone-number")
    private String phoneNumber;

    @JsonIgnore
    @JsonProperty("address")
    private String address;

    @JsonIgnore
    @Email
    @JsonProperty("email")
    private String email;
}
