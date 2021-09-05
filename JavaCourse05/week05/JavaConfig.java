package org.geekbang.week05.c02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 自动装配bean
 */
@Configuration
public class JavaConfig {

    @Bean
    public Student2 student100() {
        Student2 student = new Student2();
        student.setId(100);
        student.setName("student100");
        return student;
    }

    @Bean
    public Student2 student200() {
        Student2 student = new Student2();
        student.setId(200);
        student.setName("student200");
        return student;
    }

    @Bean
    public Klass2 klass2(@Autowired List<Student2> student2List) {
        Klass2 klass2 = new Klass2();
        klass2.setStudents(student2List);
        return klass2;
    }

    @Bean
    public School2 school2(@Autowired Student2 student100, @Autowired Klass2 klass2) {
        School2 school2 = new School2();
        school2.setKlass2(klass2);
        school2.setStudent2(student100);

        return school2;
    }
}
