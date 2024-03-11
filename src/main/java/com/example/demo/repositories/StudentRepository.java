package com.example.demo.repositories;

import com.example.demo.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {

    //FOR MORE Queries refer to JPQL https://docs.oracle.com/cd/E11035_01/kodo41/full/html/ejb3_langref.html
    Optional<Student> findStudentByEmail(String email);
     List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqual(String name, Integer age);
    //Can also write own query using @Query(select s from student s where s.email = ?1)
    //here from student refer student is the entity name set at the Student and ?1 refers the position number of send parameter argument(1,2,3)etc.
    //we even can use native query used in the sql as follows:
    //@Query(value = 'select * from student s where s.email = ?1',nativeQuery = true)

    // Only issue with native query is that it is specific to sql so if we switch db to mongo or other it wont work
    //Best practice is to use JPQuery.

    //can use param instead of ? and ?L
    @Query("select s from student s where s.lastname = :last and s.id = :id")
    Optional <Student> findByLastNameAndId(@Param("last") String name, @Param("id")Long id);

    //For update and delete we cannot directly use @Query as it expects something to be mapped so for that we use as follows
   @Transactional
    @Modifying
    @Query("Delete from student s where s.id = :id")
    int deleteRecord(@Param("id")Long id);
}
