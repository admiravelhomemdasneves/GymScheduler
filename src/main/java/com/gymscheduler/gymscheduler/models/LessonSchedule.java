package com.gymscheduler.gymscheduler.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class LessonSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLessonSchedule;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("lesson-date")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateLesson = new Date();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @JsonProperty("begin-time")
    @NotNull
    private LocalTime timeBegin;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @JsonProperty("end-time")
    @NotNull
    private LocalTime timeEnd;

    @JsonProperty("lesson-program")
    @OneToOne
    @JoinColumn(name = "ID_PROGRAM", referencedColumnName = "idProgram")
    private Program lessonProgram;

    @JsonProperty("lesson-professors")
    @ManyToMany
    @JoinTable(
            name = "GS_LESSON_SCH_PROFESSORS",
            joinColumns = @JoinColumn(name = "LESSON_SCH_ID"),
            inverseJoinColumns = @JoinColumn(name = "PROFESSOR_ID", referencedColumnName = "idProfessor")
    )
    private List<Professor> lessonProfessors = new ArrayList<>();
}
