package com.ds3.proyecto1;

import java.util.List;

public class Student implements IteratorFields{

    private String name;
    private String identification;

    public Student(){
        this("root","0");
    }
    public Student(String name, String id){
        this.name = name;
        this.identification = id;
    }

    @Override
    public List<String> listFields(){
        Student thisClass = null;
        try {
            thisClass = (Student) this.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return this.streamFields(thisClass);
    }
}
