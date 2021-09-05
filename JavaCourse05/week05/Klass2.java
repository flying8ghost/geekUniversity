package org.geekbang.week05.c02;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Klass2 {

    private List<Student2> students = new ArrayList<>();
    
    public void dong(){
        System.out.println(this.getStudents());
    }
    
}
