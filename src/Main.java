import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.print("$ "); //prompt to display by default at the start of each input line
        Scanner scanner = new Scanner(System.in); // helper to take the input

        // 1. Take input from the user via command Line
        String input = scanner.nextLine();
        // 2. Parse the command from the input text
        String Command = input.split(" ")[0];
        // 3. Call the respective functionality to execute the command
            // a. fetch the required parameters from the input text
            // b. execute the command
            // c. return the output of the command to main function
        // 4. Display the output received from the subFunction on the console
        // If the command is not supported Display Invalid Command
        if(Command.equals("echo"))
            System.out.println(input.substring(5));
        else
            System.out.println("Invalid Command");
    }
}