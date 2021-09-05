package org.geekbang.week05.c02;


import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class Student1 {

    private int id;
    private String name;

    public void print() {
        System.out.println("Student name is: " + this.name);

    }


}
