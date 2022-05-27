package com.gymscheduler.gymscheduler.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
//@Table(schema = "GymScheduler", name = "GS_ACTIVITY")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idActivity;

    @Size(max=50, message="Activity name can't have more than 50 characters")
    @NotBlank(message = "Activity name cannot be blank.")
    @JsonProperty("activity-name")
    private String activityName;

    @Size(max=255, message="Activity description can't have more than 255 characters")
    @NotBlank(message = "Activity description cannot be blank.")
    @JsonProperty("activity-description")
    private String activityDescription;
}
