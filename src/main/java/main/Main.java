/*
    class Main

    First it gets user's input data from System.in verifes the data to make sure there is no problem
    with the input data. Then, it asks for user's commands and gets them via System.in.
    Finally, it processes each command, and outputs the results to System.out
* */
package main;

import util.Grade;
import util.Gradebook;
import util.Student;

import java.util.Objects;
import java.util.Scanner;

import static util.Grade.gradeIsValid;
import static util.Student.PIDIsValid;

public class Main {
    // values used during input handling phase
    private static int totalInputHandlingFields = 4;
    private static String inputFieldsDelimiter = " ";
    private static String inputHandlingStateTerminator = "DONE";
    private static String commandHandlingStateTerminator = "quit";

    // enum to keep track of program states
    private enum PROGSTATES { Initial, InputHandling, InputHandlingInvalid, CommandHandling, CommandHandlingInvalid, Quit };
    private static PROGSTATES progState;

    private static Gradebook gradebook;

    public static void prompt() {
        switch(progState) {
            case Initial:
                System.out.println("Welcome to my grade book!");
                System.out.println("Please enter the information of the first student using the following format:");
                System.out.println("“firstName lastName PID grade”.");
                System.out.println("Press Enter when you are done");
                progState = PROGSTATES.InputHandling;
                break;
            case InputHandling:
                System.out.println("Please enter the information of the next student using the same format.\n" +
                        "If there is no more students, please enter the keyword \"DONE\".\n" +
                        "Press Enter when you are done.");
                break;
            case InputHandlingInvalid:
                System.out.println("Try again");
                progState = PROGSTATES.InputHandling;
                break;
            case CommandHandling:
                System.out.println("Please enter a new command");
                break;
            case CommandHandlingInvalid:
                System.out.println("Try again");
                progState = PROGSTATES.CommandHandling;
                break;
            case Quit:
                System.out.println("Exiting program.");
                break;
        }
    }

    /*
        User must enter information in the following format:
        FirstName LastName PID Grade

        FirstName must (1) start with a capital letter, (2) have alphabetical characters only,
        (3) not contain any spaces.

        LastName has similar requirements as FirstName. but it may also contain a single
        dot character too.

        PID must be only 7 digits with no leading 0.

        Grade must be from 0 to 100.
     */
    public static void inputHandler() {
        prompt();

        while(progState == PROGSTATES.InputHandling || progState == PROGSTATES.InputHandlingInvalid) {
            String userInput = new Scanner(System.in).nextLine();
            String[] inputStrings = userInput.split(inputFieldsDelimiter);

            if(userInput.equals(inputHandlingStateTerminator)) {
                progState = PROGSTATES.CommandHandling;
                continue;
            }
            else if(inputStrings.length != totalInputHandlingFields) {
                progState = PROGSTATES.InputHandlingInvalid;
            }
            else if(progState == PROGSTATES.InputHandling) {
                String potentialFirstName = inputStrings[0];
                String potentialLastName = inputStrings[1];
                String potentialPID = inputStrings[2];
                String potentialGrade = inputStrings[3];
                Student student = new Student(potentialFirstName, potentialLastName, potentialPID, potentialGrade);
                if(student.isValid())
                    gradebook.addStudent(student);
                else
                    progState = PROGSTATES.InputHandlingInvalid;
            }

            prompt();
        }
    }

    /*
    * This function runs after the input handling phase of the program.
    * */
    public static void commandHandler() {
        while(progState == PROGSTATES.CommandHandling || progState == PROGSTATES.CommandHandlingInvalid) {
            prompt();

            String userInput = new Scanner(System.in).nextLine();
            String[] inputStrings = userInput.split(inputFieldsDelimiter);

            if(userInput.equals(commandHandlingStateTerminator)) {
                progState = PROGSTATES.Quit;
            }
            else if(inputStrings.length < 2 || inputStrings.length > 3) {
                progState = PROGSTATES.InputHandlingInvalid;
            }
            else if(progState == PROGSTATES.CommandHandling) {
                if(inputStrings.length == 2 && inputStrings[0].equals("min") && inputStrings[1].equals("score")) {
                    // print minimum score out of all students
                    System.out.println(gradebook.getMinimumGrade().getScore());
                }
                else if(inputStrings.length == 2 && inputStrings[0].equals("min") && inputStrings[1].equals("letter")) {
                    // print minimum letter grade out of all students
                    System.out.println(gradebook.getMinimumGrade().getLetterGrade());
                }
                else if(inputStrings.length == 2 && inputStrings[0].equals("max") && inputStrings[1].equals("score")) {
                    // print maximum score of all students
                    System.out.println(gradebook.getMaximumGrade().getScore());
                }
                else if(inputStrings.length == 2 && inputStrings[0].equals("max") && inputStrings[1].equals("letter")) {
                    // print maximum letter grade of all students
                    System.out.println(gradebook.getMaximumGrade().getLetterGrade());
                }
                else if(inputStrings.length == 2 && inputStrings[0].equals("letter") && PIDIsValid(inputStrings[1])) {
                    // find and print letter grade of student with corresponding PID
                    System.out.println(gradebook.getStudent(Integer.parseInt(inputStrings[1])).getGrade().getLetterGrade());
                }
                else if(inputStrings.length == 2 && Objects.equals(inputStrings[0], "name") && PIDIsValid(inputStrings[1])) {
                    // find and print the full name of the student with corresponding PID
                    Student student = gradebook.getStudent(Integer.parseInt(inputStrings[1]));
                    if(student != null)
                        System.out.println(student.getFirstName() + " " + student.getLastName());
                }
                else if(inputStrings.length == 2 && inputStrings[0].equals("average") && inputStrings[1].equals("score")) {
                    // calculate and print average score out of all students
                    System.out.println(gradebook.calculateAvg());
                }
                else if(inputStrings.length == 2 && inputStrings[0].equals("average") && inputStrings[1].equals("letter")) {
                    // calculate and print the letter grade of average score out of all students
                    System.out.println(Grade.convertScoreToLetter((int) gradebook.calculateAvg()));
                }
                else if(inputStrings.length == 2 && inputStrings[0].equals("median") && inputStrings[1].equals("score")) {
                    // calculate and print median score out of all students
                    System.out.println(gradebook.calculateMedian());
                }
                else if(inputStrings.length == 2 && inputStrings[0].equals("median") && inputStrings[1].equals("letter")) {
                    // calculate and print the letter grade for the median score out of all students
                    System.out.println(Grade.convertScoreToLetter((int)gradebook.calculateMedian()));
                }
                else if(inputStrings.length == 2 && inputStrings[0].equals("tab") && inputStrings[1].equals("scores")) {
                    // print all gradebook info in a table with representative column headings (grade scores only)
                    System.out.printf("First Name\tLast Name\tPID\tScore\n");
                    gradebook.printAllStudents();
                }
                else if(inputStrings.length == 2 && inputStrings[0].equals("tab") && inputStrings[1].equals("letters")) {
                    // print all gradebook infor in a table with representative headings (letter grades only)
                    System.out.printf("First Name\tLast Name\tPID\tLetter Grade\n");
                    gradebook.printAllStudentLetterGrades();
                }
                else if(inputStrings.length == 3 && inputStrings[0].equals("change") && PIDIsValid(inputStrings[1])) {
                    // find and update the grade for a student
                    Student student = gradebook.getStudent(Integer.parseInt(inputStrings[1]));
                    if(student != null && gradeIsValid(inputStrings[2]))
                        student.setGrade(Integer.parseInt(inputStrings[2]));
                }
            }
            else {
                progState = PROGSTATES.CommandHandlingInvalid;
            }
        }

    }

    public static void main(String[] args) {
        gradebook = new Gradebook();
        progState = PROGSTATES.Initial;
        inputHandler();
        commandHandler();
    }

}
