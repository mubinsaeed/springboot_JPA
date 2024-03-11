package com.example.demo;

import com.example.demo.entities.Student;
import com.example.demo.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student maria = new Student("maria","shah","maria@gmail.com",23);
            Student ali = new Student("ali","shah","ali@edu.com",13);
            System.out.println("All the records saved");
           studentRepository.saveAll(List.of(maria,ali));
            System.out.println("The count in db is ");
            System.out.println(studentRepository.count());
            studentRepository.findById(1L).ifPresentOrElse(System.out::println,()->{
                System.out.println("student not found");
            });
            studentRepository.findById(3L).ifPresentOrElse(System.out::println,()->{
                System.out.println("student not found");
            });
            System.out.println("the list of the students are as follows: ");
            List<Student> students = studentRepository.findAll();
            students.forEach(System.out::println);
            studentRepository.deleteById(1L);
            System.out.println("Count in the db after deletion");
            System.out.println(studentRepository.count());
        };
    }

}
