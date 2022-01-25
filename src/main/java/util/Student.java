/*
    Class Student

    Keeps track of students info: First name (String), last name
    (String), 7-digit ID (int), and grade (Grade).
 */
package util;

import static util.Grade.gradeIsValid;

public class Student {
    private String firstName;
    private String lastName;
    private int pid;
    private Grade grade;

    private static Boolean firstNameIsValid(String name) {
        Boolean validFirstName = true;

        // Verify first name start with Uppercase letter
        if(!Character.isUpperCase(name.charAt(0)))
            return false;

        // Verify first name contains only alphabetical characters
        for(int i = 0; i < name.length(); ++i) {
            if(!Character.isLetter(name.charAt(i)))
                return false;
        }

        return validFirstName;
    }

    private static Boolean lastNameIsValid(String name) {
        Boolean validLastName = true;

        // Verify last name start with Uppercase letter
        if(!Character.isUpperCase(name.charAt(0)))
            validLastName = false;

        // Verify last name contains only alphabetical characters
        for(int i = 0; i < name.length(); ++i) {
            if(!Character.isLetter(name.charAt(i)) && name.charAt(i) != '.')
                validLastName = false;
        }

        // Verify last name contains only one occurrence of a dot character
        if(name.indexOf('.') != name.lastIndexOf('.'))
            validLastName = false;

        return validLastName;
    }

    public static Boolean PIDIsValid(String pid) {
        Boolean validPID = true;

        // Verify PID does not start with 0
        if(pid.charAt(0) == '0')
            validPID = false;

        // Verify PID has 7 digits
        if(pid.length() != 7)
            validPID = false;

        return validPID;
    }

    // Constructor
    public Student(String firstName, String lastName, String pid, String grade) {
            this.firstName = firstNameIsValid(firstName) ? firstName : null;
            this.lastName = lastNameIsValid(lastName) ? lastName : null;
            this.pid = PIDIsValid(pid) ? Integer.parseInt(pid) : null;
            this.grade = gradeIsValid(grade) ? new Grade(Integer.parseInt(grade)) : null;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getPid() {
        return pid;
    }
    public Grade getGrade() {
        return grade;
    }

    // modifiers
    public void setGrade(int score) {

    }

    public Boolean isValid() {
        if(this.firstName == null || this.lastName == null || this.pid == 0 || this.grade.getLetterGrade() == null)
            return false;

        return true;
    }
}
