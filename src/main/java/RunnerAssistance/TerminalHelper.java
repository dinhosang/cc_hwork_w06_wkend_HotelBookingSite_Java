package RunnerAssistance;

import java.util.Scanner;

public class TerminalHelper {

    private static Scanner scanner = new Scanner(System.in);

    public static void flushMacScreen(){
        // H means move the caret to the top of screen
        // 2J means clear entire screen (through newlines)
        // Uses Ansi escape codes
        // Apparently does not work on Windows?
        System.out.print("\033[H\033[2J");
    }

    public static String getInput(){
        String userResponse = scanner.nextLine().toLowerCase().trim();
        checkQuit(userResponse);
        return userResponse;
    }

    public static void checkQuit(String response){
        if (response.contains("quit") || response.equals("q")) {
            flushMacScreen();
            System.out.println(String.format("Thank-you for visiting"));
            System.exit(0);
        }
    }

    public static boolean stringIsEmpty(String string){
        if(string.isEmpty()){
            return true;
        }
        return false;
    }

    public static boolean stringIsAlpha(String string){
        return string.matches("[a-zA-Z]+");
    }

    public static boolean stringIsNumeric(String string){
        return string.matches("[0-9]+");
    }

    public static boolean stringIsAlphaNumeric(String string){
        return string.matches("[a-zA-Z0-9]+");
    }

}
