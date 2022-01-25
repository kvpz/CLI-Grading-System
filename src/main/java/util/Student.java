/*
    Class Student

    Keeps track of students info: First name (String), last name
    (String), 7-digit ID (int), and grade (Grade).
 */
package util;

public class Student {
    private String firstName;
    private String lastName;
    private int pid;
    private Grade grade;

    // Constructor

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
}
