package com.example.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Book")
@Table(name = "student_book")
public class Book {
    @Id
    @SequenceGenerator(name = "book_sequence", allocationSize = 1, sequenceName = "book_sequence")
    @GeneratedValue(strategy = SEQUENCE, generator = "book_sequence")
    @Column(name = "book_id",updatable = false,nullable = false)
    private Long id;

    @Column(name = "book_name",length = 30,nullable = false)
    private String bookName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private Student student;

    @Column(name = "createdAt",nullable = false)
    private LocalDateTime createdAt;
    public Book(String bookName, Student student, LocalDateTime createdAt) {
        this.bookName = bookName;
        this.student = student;
        this.createdAt = createdAt;
    }

    public Book(String bookName, LocalDateTime createdAt) {
        this.bookName = bookName;
        this.createdAt = createdAt;
    }
}
