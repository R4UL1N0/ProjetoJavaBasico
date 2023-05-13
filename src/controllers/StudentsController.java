package controllers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import entities.Student;
import services.StudentsService;

public class StudentsController {
    

   static public void showApprovedList() {
        var approved = StudentsService.getApproved();
        for (Student s: approved) {
            System.out.printf("%d - %s - Média: %.2f\n", s.getCode(), s.getName(), StudentsService.mediaStudent(s));
        }

   }

   static public void showFlunkedList() {
        var flunked = StudentsService.getFlunked();
        
        for (Student s : flunked) {
            float media = StudentsService.mediaStudent(s);
            float missed = 7 - media;
            System.out.printf("%d - %s - Média: %.2f (Faltou: %.2f)\n", s.getCode(), s.getName(), media, missed );
        }
   }

   static public void showNota10() {
        var students = StudentsService.getApproved();
        Boolean hasOne = false;

        for (Student s : students) {
            if (StudentsService.mediaStudent(s) == 10) {
                hasOne = true;
                System.out.printf("%d - %s", s.getCode(), s.getName());
            }
        }

        if (!hasOne) {
            System.out.println("Ninguém tirou 10...");
        }
   }

   private static ArrayList<Student> lowestScoreStudents() {
        var students = StudentsService.getFlunked();

        ArrayList<Student> lowestStudents = new ArrayList<>();
        float lowestScore = 999;

        for (Student s: students) {
            var media = StudentsService.mediaStudent(s);
            if (media < lowestScore) {
                lowestStudents.clear();
                lowestStudents.add(s);
                lowestScore = media;
            } else if (media == lowestScore && !lowestStudents.isEmpty()) {
                lowestStudents.add(s);
            }
        }

        return lowestStudents;
   }

   public static void showLowestOnes() {
        var lowestList = lowestScoreStudents();

        if (lowestList.size() > 1) {
            System.out.println("THE WORST ONES:");
        } else {
            System.out.println("THE WORST ONE:");
        }

        for (Student s: lowestList) {
            System.out.printf("%d - %s : Nota = %.2f\n", s.getCode(), s.getName(), StudentsService.mediaStudent(s));
        }
   }

   private static List<Student> sortDescending() {
    List<Student> students = StudentsService.getAllStudents();

    Student[] sorted = new Student[students.size()];

    for (var i = 0; i < students.size(); i++) {
        sorted[i] = students.get(i);
    }

    for (var i = 0; i < sorted.length; i++) {
        for (var j = i + 1; j < sorted.length; j++) {
            var s1 = sorted[i];
            var media1 = StudentsService.mediaStudent(sorted[i]);
            var s2 = sorted[j];
            var media2 = StudentsService.mediaStudent(sorted[j]);

            if (media1 < media2) {
                sorted[i] = s2;
                sorted[j] = s1;
            }
           
        }
    }
    
    return Arrays.asList(sorted);
    
   }

   public static void showDescending() {
    var descendingList = sortDescending();
    for (Student s: descendingList) {
        System.out.printf("%d - %s - Média: %.2f\n", s.getCode(), s.getName(), StudentsService.mediaStudent(s));
    }
   }

   private static ArrayList<Float> bestThreeScores() {
        ArrayList<Float> bestThree = new ArrayList<>();

        List<Student> students = StudentsService.getAllStudents();

        while (bestThree.size() != 3) {
            float bestScore = 0;

            for (Student s: students) {
                float scoresList []= {s.getTestOne(), s.getTestTwo(), s.getTestThree()};
                for (float score: scoresList) {
                    if (!bestThree.contains(score) && score > bestScore)  {
                        bestScore = score;
                    }
                } 
            }

            bestThree.add(bestScore);
        }

        return bestThree;
   }

   public static void showBestThree() {
        List<Student> students = StudentsService.getAllStudents();
        ArrayList<Float> bestThreeScores = bestThreeScores();

        HashMap<Integer, ArrayList<String>> hashBest = new HashMap<>();

        hashBest.put(1, new ArrayList<>());
        hashBest.put(2, new ArrayList<>());
        hashBest.put(3, new ArrayList<>());


        for (Student s: students) {
            float scoresList []= {s.getTestOne(), s.getTestTwo(), s.getTestThree()};
            
            for (float score: scoresList) {
                if (score == bestThreeScores.get(0)) {
                    hashBest.get(1).add(s.getName());
                } else if (score == bestThreeScores.get(1)) {
                    hashBest.get(2).add(s.getName());
                } else if (score == bestThreeScores.get(2)){
                    hashBest.get(3).add(s.getName());
                }
            }
        }

        for (var i = 0; i < bestThreeScores.size(); i++) {
            System.out.print((i + 1) + "º ");
            for (String s: hashBest.get(i + 1)) {
                
                System.out.printf("- %s : Nota = %.2f", s, bestThreeScores.get(i));
                System.out.println("");
            }
        }

   }

   private static ArrayList<Float> worstThreeScores() {
        ArrayList<Float> worstThree = new ArrayList<>();

        List<Student> students = StudentsService.getAllStudents();

        while (worstThree.size() != 3) {
            float worstScore = 999;

            for (Student s: students) {
                float scoresList []= {s.getTestOne(), s.getTestTwo(), s.getTestThree()};
                for (float score: scoresList) {
                    if (!worstThree.contains(score) && score < worstScore)  {
                        worstScore = score;
                    }
                } 
            }

            worstThree.add(worstScore);
        }

        return worstThree;
    }

    public static void showWorstThree() {
        List<Student> students = StudentsService.getAllStudents();
        ArrayList<Float> worstThreeScores = worstThreeScores();

        HashMap<Integer, ArrayList<String>> hashWorst = new HashMap<>();

        hashWorst.put(1, new ArrayList<>());
        hashWorst.put(2, new ArrayList<>());
        hashWorst.put(3, new ArrayList<>());


        for (Student s: students) {
            float scoresList []= {s.getTestOne(), s.getTestTwo(), s.getTestThree()};
            
            for (float score: scoresList) {
                if (score == worstThreeScores.get(0)) {
                    hashWorst.get(1).add(s.getName());
                } else if (score == worstThreeScores.get(1)) {
                    hashWorst.get(2).add(s.getName());
                } else if (score == worstThreeScores.get(2)){
                    hashWorst.get(3).add(s.getName());
                }
            }
        }

        for (var i = 0; i < worstThreeScores.size(); i++) {
            System.out.print((i + 1) + "º ");
            for (String s: hashWorst.get(i + 1)) {
                
                System.out.printf("- %s : Nota = %.2f", s, worstThreeScores.get(i));
                System.out.println("");
            }
        }

   }


}
