package org.geekbang.week05.c02;

import lombok.Data;


@Data
public class School2 {

    private Klass2 klass2;

    private Student2 student2;

    public void ding() {
        System.out.println("Klass have " + this.klass2.getStudents().size() + " students and one is " + this.student2);

    }

}
