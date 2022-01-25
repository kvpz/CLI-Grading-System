/*
    class Main

    First it gets user's input data from System.in verifes the data to make sure there is no problem
    with the input data. Then, it asks for user's commands and gets them via System.in.
    Finally, it processes each command, and outputs the results to System.out
* */
package main;

import util.Student;

import java.util.Scanner;

public class Main {
    // Program state tracking variables
    private static Boolean validInputState = false;
    private static Boolean inputCompleteState = false;
    private static Boolean cmdHandlineCompleteState = false;
    private static Boolean validCommandState = false;

    public static void inputIntroPrompt() {
        System.out.println("Welcome to my grade book!");
        System.out.println("Please enter the information of the first student using the following format:");
        System.out.println("“firstName lastName PID grade”.");
        System.out.println("Press Enter when you are done");
    }

    public static void followupPrompt() {
        System.out.println("Please enter the information of the next student using the same format.\n" +
                "If there is no more students, please enter the keyword \"DONE\".\n" +
                "Press Enter when you are done.");
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
    public static String getUserInput() {
        Scanner stdin = new Scanner(System.in);

        return stdin.nextLine();
    }

    public static void parseUserInput(String input) {


        // TODO: verify input meets requirements
    }

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

    private static Boolean PIDIsValid(String pid) {
        Boolean validPID = true;

        // Verify PID does not start with 0
        if(pid.charAt(0) == '0')
            validPID = false;

        // Verify PID has 7 digits
        if(pid.length() != 7)
            validPID = false;

        return validPID;
    }

    private static Boolean gradeIsValid(String grade) {
        Boolean validGrade = true;

        // Verify Grade does not start with a 0
        if(grade.charAt(0) == '0')
            validGrade = false;

        // Verify Grade is greater than 0 and no greater than 100
        if(Integer.parseInt(grade) > 100 || Integer.parseInt(grade) < 0)
            validGrade = false;

        return validGrade;
    }

    public static Boolean isInputValid(String[] input) {
        if(input.length != 4) {
            return false;
        }

        String potentialFirstName = input[0];
        String potentialLastName = input[1];
        String potentialPID = input[2];
        String potentialGrade = input[3];

        // Verify first and last name start with Uppercase letter
        return firstNameIsValid(potentialFirstName) && lastNameIsValid(potentialLastName) &&
                PIDIsValid(potentialPID) && gradeIsValid(potentialGrade);
    }

    private static Student createNewStudent(String[] studentInfo) {
        //Student student = new Student(studentInfo[0], studentInfo[1], studentInfo[2], studentInfo[3]);
        Student student = new Student();

        return student;
    }

    public static void inputHandler() {
        String inputHandlerTerminator = "DONE";
        String[] inputStrings;
        inputIntroPrompt();

        while(!inputCompleteState) {
            if(validInputState)
                followupPrompt();

            String userInput = getUserInput();

            if(userInput.equals(inputHandlerTerminator)) {
                inputCompleteState = true;
                continue;
            }

            inputStrings = userInput.split(" ");

            validInputState = isInputValid(inputStrings);

            if(validInputState) {
                createNewStudent(inputStrings);
            }

            if(!validInputState)
                System.out.println("Try again");
        }
    }

    public static void commandHandler() {
        System.out.println("Please enter a new command");

        while(!cmdHandlineCompleteState) {
            // TODO: command handling
        }
    }

    public static void main(String[] args) {
        inputHandler();
        commandHandler();
    }

}
