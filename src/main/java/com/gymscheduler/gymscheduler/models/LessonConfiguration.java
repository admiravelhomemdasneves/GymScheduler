package com.gymscheduler.gymscheduler.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;

@Getter
@Setter
@Entity
//@Table(schema = "GymScheduler", name = "GS_LESSON_CONFIGURATION")
public class LessonConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLessonConfiguration;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PROGRAM_ID", referencedColumnName = "idProgram")
    private Program configurationProgram;

    @JsonProperty("lesson-name")
    private String lessonName;

    @Size(max=1, message="Week Day can't have more than 1 character")
    @JsonProperty("week-day")
    @NotBlank
    private String weekDay;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @JsonProperty("begin-time")
    @NotNull
    private LocalTime timeBegin;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @JsonProperty("end-time")
    @NotNull
    private LocalTime timeEnd;

    /*
    FALTA CONSIDERAR CICLOS DE REPETIÇÃO & SALAS
     */
}
