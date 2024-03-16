package com.example.demo.entities;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.sound.midi.Sequence;

import java.util.ArrayList;
import java.util.List;

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

    @OneToOne(mappedBy = "student",orphanRemoval = true)
    private StudentIdCard studentIdCard;

    @OneToMany(mappedBy = "student",orphanRemoval = true, cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    //from above, we can map and insert book without book repository
    private List<Book> books = new ArrayList<>();
    public Student() {
    }

    //for making both tables persistent note not making Repo for book table
    public void addBooks(Book book){
        if (!this.books.contains(book)){
            this.books.add(book);
            book.setStudent(this);
        }
    }
    public void removeBook(Book book){
        if (this.books.contains(book)){
            this.books.remove(book);
            book.setStudent(null);
        }
    }

    //only used the below method for lazy fetch
    //By default for 1v1 the fetch method is EAGER
    //For rest type it is lazy
    public List<Book> getBooksAll(){
        return this.books;
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
