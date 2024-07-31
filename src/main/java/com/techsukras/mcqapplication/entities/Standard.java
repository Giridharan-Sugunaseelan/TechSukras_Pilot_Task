package com.techsukras.mcqapplication.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Class")
public class Standard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stdId;

    @Column(nullable = false)
    private String stdName;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Subject.class)
    @JoinTable(
            name = "standard_subject_table",
            joinColumns = @JoinColumn(name = "standardId", referencedColumnName = "stdId"),
            inverseJoinColumns = @JoinColumn(name = "subjectId", referencedColumnName = "subId")
            )
    private Set<Subject> subjects = new HashSet<>();

    @OneToMany(mappedBy = "standard",fetch = FetchType.LAZY, targetEntity = Student.class)
    private Set<Student> students = new HashSet<>();
}
