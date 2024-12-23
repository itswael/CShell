import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in); // helper to take the input
        ArrayList<String> commands = new ArrayList<>(Arrays.asList("exit", "echo", "type"));
        String cwd = Path.of("").toAbsolutePath().toString();
        while(true) {
            // 1. Take input from the user via command Line
            System.out.print("$ "); //prompt to display by default at the start of each input line
            String input = scanner.nextLine();
            // 2. Parse the command from the input text
            String Command = input.split(" ")[0];
            // 3. Call the respective functionality to execute the command
            // a. fetch the required parameters from the input text
            // b. execute the command
            // c. return the output of the command to main function
            // 4. Display the output received from the subFunction on the console
            // If the command is not supported Display Invalid Command
            if (Command.equals("echo")) {
                System.out.println(input.substring(5));
            }else if (Command.equals("exit")) {
                System.exit(0);
            }else if(Command.equals("type")){
                String param = input.split(" ")[1];
                if(commands.contains(param)){
                    System.out.println(param+" is CShell builtin");
                }else{
                    String path = getPath(param);
                    if (path != null) {
                        System.out.println(param + " is " + path);
                    } else {
                        System.out.println(param + ": not found");
                    }
                }
            }else if(Command.equals("pwd")){
                System.out.println(cwd);
            }else if (input.startsWith("cd ")) {
                String dir = input.substring(3);
                if (!dir.startsWith("/")) {
                    dir = cwd + "/" + dir;
                }
                if (Files.isDirectory(Path.of(dir))) {
                    //cwd = dir;
                    cwd = Path.of(dir).normalize().toString();
                } else {
                    System.out.printf("cd: %s: No such file or directory%n", dir);
                }
            }else {
                String command = input.split(" ")[0];
                String path = getPath(command);
                if (path == null) {
                    System.out.printf("%s: command not found%n", command);
                } else {
                    String fullPath = path + input.substring(command.length());
                    Process p = Runtime.getRuntime().exec(fullPath.split(" "));
                    p.getInputStream().transferTo(System.out);
                }
            }
        }
    }

    private static String getPath(String parameter) {
        for (String path : System.getenv("PATH").split(":")) {
            Path fullPath = Path.of(path, parameter);
            if (Files.isRegularFile(fullPath)) {
                return fullPath.toString();
            }
        }
        return null;
    }
}