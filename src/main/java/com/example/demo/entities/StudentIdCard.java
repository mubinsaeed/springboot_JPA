package com.example.demo.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "StudentIdCard")
@Table(name = "student_id_card",
        uniqueConstraints = {@UniqueConstraint(name = "student_card_id_unique",columnNames = "card_number")}

)
@Getter
@Setter
@NoArgsConstructor
public class StudentIdCard {
    @Id
    @SequenceGenerator(name = "id", allocationSize = 1, sequenceName = "id")
    @GeneratedValue(strategy = SEQUENCE, generator = "id")
    @Column(name = "card_id",updatable = false)
    private Long   id;


    //foreign key implementation
    //default fetch type is EAGER for 1 to 1 that means it load both joining class
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey =
    @ForeignKey(name = "Fk_student_card_student" //used for naming the constraint of fk
    ))

    private Student student; //foreign key

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    public StudentIdCard( String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public StudentIdCard(Student student, String cardNumber) {
        this.student = student;
        this.cardNumber = cardNumber;
    }
}
