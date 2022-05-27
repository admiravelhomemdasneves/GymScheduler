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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
//@Table(schema = "GymScheduler", name = "GS_PROGRAM")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProgram;

    @NotBlank(message = "Program name can't be blank.")
    @Size(max=50, message="Program name can't have more than 50 characters")
    @JsonProperty("program-name")
    private String programName;

    @JsonProperty("program-professors")
    @ManyToMany
    @JoinTable(
            name = "GS_PROGRAM_PROFESSORS",
            joinColumns = @JoinColumn(name = "PROGRAM_ID"),
            inverseJoinColumns = @JoinColumn(name = "PROFESSOR_ID", referencedColumnName = "idProfessor")
    )
    private List<Professor> programProfessors = new ArrayList<>();

    @JsonProperty("program-activity")
    @OneToOne
    @JoinColumn(name = "ID_ACTIVITY", referencedColumnName = "idActivity")
    private Activity programActivity;

    @JsonProperty("lesson-configurations")
    @OneToMany(mappedBy = "configurationProgram")
    private List<LessonConfiguration> lessonConfigurations;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("begin-date")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateBegin = new Date();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("end-date")
    @Temporal(TemporalType.DATE)
    private Date dateEnd = new Date();

    @JsonIgnore
    @NotNull
    @JoinColumn(name = "DT_CREATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation = new Date(System.currentTimeMillis());

    @JsonIgnore
    @NotNull
    @JoinColumn(name = "DT_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdate = new Date(System.currentTimeMillis());

    @JsonProperty("program-type")
    @NotBlank
    private String flgType;
}
