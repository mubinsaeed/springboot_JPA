package com.example.demo;

import com.example.demo.entities.Book;
import com.example.demo.entities.Student;
import com.example.demo.entities.StudentIdCard;
import com.example.demo.repositories.StudentCardRepository;
import com.example.demo.repositories.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, StudentCardRepository studentCardRepository){
        return args -> {
            Faker faker = new Faker();
            String fname = faker.name().firstName();
            String lname = faker.name().lastName();
            String email = String.format("%s.%s@gmail.com",fname,lname);
            Student recStudent = new Student(fname,lname,email,faker.number().numberBetween(17,30));
            //  StudentIdCard studentIdCard = new StudentIdCard(recStudent,"12345678");
           // studentCardRepository.save(studentIdCard);
            recStudent.addBooks(new Book("First book", LocalDateTime.now().minusDays(3)));
            recStudent.addBooks(new Book("Second book", LocalDateTime.now().minusDays(4)));
            recStudent.addBooks(new Book("Third book", LocalDateTime.now().minusDays(1)));
            studentRepository.save(recStudent);

        };
    }

    private void pagingRequest(StudentRepository studentRepository) {
        //further add a break point and explorer the page expression
        PageRequest pageRequest = PageRequest.of(0,5,Sort.by("firstname").ascending());
        Page<Student> page = studentRepository.findAll(pageRequest);
        System.out.println(page);
    }

    private  void sortAndFilter(StudentRepository studentRepository) {
        Sort sort = Sort.by("firstname").ascending().and(Sort.by("age").descending());
        studentRepository.findAll(sort).forEach(student-> System.out.println(student.getFirstname()+" "+student.getAge()));
    }

    private void generateRandomStudent(StudentRepository studentRepository) {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            String fname = faker.name().firstName();
            String lname = faker.name().lastName();
            String email = String.format("%s.%s@gmail.com",fname,lname);
            Student recStudent = new Student(fname,lname,email,faker.number().numberBetween(17,30));
            studentRepository.save(recStudent);
        }
    }

}
