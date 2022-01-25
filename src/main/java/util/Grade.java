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

    public static Boolean gradeIsValid(String score) {
        Boolean validGrade = true;

        // Verify Grade does not start with a 0
        if(score.charAt(0) == '0')
            validGrade = false;

        // Verify Grade is greater than 0 and no greater than 100
        if(Integer.parseInt(score) > 100 || Integer.parseInt(score) < 0)
            validGrade = false;

        return validGrade;
    }

    public static Boolean gradeIsValid(int score) {
        if(score > 100 || score < 0)
            return false;

        return true;
    }

    public static String convertScoreToLetter(int score) {
        String letter = "";
        if(score > 89)
            letter = "A";
        else if(score > 84 && score < 90)
            letter = "A-";
        else if(score > 80 && score < 85)
            letter = "B+";
        else if(score > 74 && score < 80)
            letter = "B";
        else if(score > 69 && score < 75)
            letter = "B-";
        else if(score > 64 && score < 70)
            letter = "C+";
        else if(score > 59 && score < 65)
            letter = "C";
        else if (score > 49 && score < 60)
            letter = "D";
        else
            letter = "F";

        return letter;
    }

    // Constructor
    public Grade(int score) {
        if(gradeIsValid(score)) {
            this.score = score;
            letterGrade = convertScoreToLetter(score);
        }
    }

    public int getScore() {
        return score;
    }
    public String getLetterGrade() {
        return letterGrade;
    }
}
