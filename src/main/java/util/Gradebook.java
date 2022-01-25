/*
    class Gradebook

    Keeps a list of students info in an ArrayList.
 */
package util;

import java.sql.Array;
import java.util.*;

public class Gradebook {
    private ArrayList<Student> listOfStudents;

    // Constructor
    public Gradebook() {
        listOfStudents = new ArrayList<Student>();
    }

    public void addStudent(Student student) {
        listOfStudents.add(student);
    }

    public double calculateAvg() {
        double sum = 0;
        for(Student s: listOfStudents)
            sum += s.getGrade().getScore();
        return sum / listOfStudents.size();
    }
    public float calculateMedian() {
        int i = 0, n = listOfStudents.size();
        int[] scores = new int[n];
        for(Student s: listOfStudents)
            scores[i++] = s.getGrade().getScore();
        Arrays.sort(scores);
        if (n % 2 == 0)
            return (scores[n / 2] + scores[n / 2 - 1]) / 2.0f;
        else
            return scores[n / 2];
    }
    public void printAllStudents() {
        for(Student s: listOfStudents)
            System.out.printf("%s\t%s\t%d\t%d\n", s.getFirstName(), s.getLastName(), s.getPid(), s.getGrade().getScore());
    }

    public void printAllStudentLetterGrades() {
        for(Student s: listOfStudents)
            System.out.printf("%s\t%s\t%d\t%d\n", s.getFirstName(), s.getLastName(), s.getPid(), s.getGrade().getLetterGrade());
    }

    public Grade getMinimumGrade() {
        Grade minimumGrade = new Grade(100);

        for(Student s : listOfStudents) {
            if(s.getGrade().getScore() < minimumGrade.getScore()) {
                minimumGrade = s.getGrade();
            }
        }

        return minimumGrade;
    }

    public Grade getMaximumGrade() {
        Grade maximumGrade = new Grade(0);

        for(Student s : listOfStudents) {
            if(s.getGrade().getScore() < maximumGrade.getScore()) {
                maximumGrade = s.getGrade();
            }
        }

        return maximumGrade;
    }

    public Student getStudent(int pid) {
        Student student = null;
        for(Student s : listOfStudents) {
            if(s.getPid() == pid) {
                student = s;
                break;
            }
        }

        return student;
    }
}
