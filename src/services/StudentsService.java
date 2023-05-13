package services;

import java.util.ArrayList;
import java.util.List;

import builders.StudentsBuilder;
import entities.Student;

public abstract class StudentsService {

   
    

    static public List<Student> getAllStudents() {
        return StudentsBuilder.getAllStudents();
    }

    static private ArrayList<Float> notas(Student s) {
        ArrayList<Float> notas = new ArrayList<>(); 
        notas.add(s.getTestOne());
        notas.add(s.getTestTwo());
        notas.add(s.getTestThree());

        return notas;
    }

    static public float mediaStudent(Student s) {
        var notas = notas(s);
        float sum = 0;

        for (float score: notas) {
            sum += score;
        }

        return sum / notas.size();
    }

    static private Boolean isApproved(Student s) {

        var media = mediaStudent(s);

        return media >= 7.0;
    }

    static public List<Student> getApproved() {
        var students = getAllStudents();

        return students.stream().filter(s -> isApproved(s)).toList();
    }
    
    static public List<Student> getFlunked() {
        var students = getAllStudents();

        return students.stream().filter(s -> !isApproved(s)).toList();
    }

}
