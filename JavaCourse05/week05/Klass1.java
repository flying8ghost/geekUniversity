package org.geekbang.week05.c02;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class Klass1 {

    @Autowired
    private List<Student1> student1s = new ArrayList<>();
    
    public void dong(){
        System.out.println(this.getStudent1s());
    }
    
}
