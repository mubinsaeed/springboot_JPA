package com.example.demo.entities;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.sound.midi.Sequence;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "student")
@Table(name = "student" ,uniqueConstraints = {@UniqueConstraint(name = "student_email_unique",columnNames = "email")})
public class Student {
    @Column(name = "first_name", nullable = false,columnDefinition = "TEXT")
    private String firstname;
    @Column(name = "email", nullable = false,columnDefinition = "TEXT")

    private String email;
    @Id
    @SequenceGenerator(name = "student_id", allocationSize = 1, sequenceName = "student_id")
    @GeneratedValue(strategy = SEQUENCE, generator = "student_id")
    private long   id;
    @Column(name = "age", nullable = false)

    private Integer age;
    @Column(name = "last_name", nullable = false,columnDefinition = "TEXT")

    private String lastname;

    public Student() {
    }

    public Student(String firstname, String lastname,String email,Integer age) {
        this.firstname = firstname;
        this.email = email;

        this.age = age;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
