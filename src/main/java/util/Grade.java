/*
    class Grade

    Keeps a single grade in the form of both score (from 0 to 100) and
    letter grade (A, A-, . . . , F).

    Letter grade to score range mapping:
    A : 100 - 90
    A-: 89.99 - 85
    B+: 84.99 - 80
    B : 79.99 - 75
    B-: 74.99 - 70
    C+: 69.99 - 65
    C : 64.99 - 60 (note the actual syllabus states 64.66...)
    D : 59.99 - 50
    F : 49.99 - 0
 */
package util;

public class Grade {
    private int score;
    private String letterGrade;


    // Constructor


    public int getScore() {
        return score;
    }
    public String getLetterGrade() {
        return letterGrade;
    }
}
