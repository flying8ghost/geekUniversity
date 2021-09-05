package org.geekbang.week05.c02;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Data
@Component
public class School1 {

    @Autowired
    private Klass1 klass1;

    @Autowired
    private Student1 student1100;

    public void ding() {
        System.out.println("Klass have " + this.klass1.getStudent1s().size() + " students and one is " + this.student1100);

    }

}
