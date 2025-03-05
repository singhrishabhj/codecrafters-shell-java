import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        // Print initial prompt
        System.out.print("$ ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // Read user input

        while (true) {
            // Exit condition when input is "exit 0"
            if (input.equals("exit 0")) {
                break;
            }

            // Handle echo command to print arguments after "echo"
            if (input.startsWith("echo")) {
                System.out.println(input.substring(5)); // Print everything after "echo "
            } 
            // Handle type command to check if a command is built-in or executable
            else if (input.startsWith("type")) {
                String command = input.substring(5); // Extract the command name
                
                // Check if the command is a shell built-in
                if (command.equals("type") || command.equals("echo") || command.equals("exit")) {
                    System.out.println(command + " is a shell builtin");
                } else {
                    // Get the PATH environment variable
                    String path = System.getenv("PATH");
                    String[] dirs = path.split(":"); // Split PATH into directories
                    boolean found = false;
                    
                    // Loop through each directory in PATH
                    for (String dir : dirs) {
                        File file = new File(dir, command); // Create file object for the command in the directory
                        
                        // Check if the file exists and is executable
                        if (file.exists() && file.canExecute()) {
                            System.out.println(command + " is " + file.getAbsolutePath());
                            found = true;
                            break;
                        }
                    }
                    // Print not found if the command is not found in any directory
                    if (!found) {
                        System.out.println(command + ": not found");
                    }
                }
            } 
            // Handle external programs execution with arguments
            else {
                String[] parts = input.split(" "); // Split input into command and arguments
                String command = parts[0]; // First element is the command name
                
                // Check if command exists in PATH before executing
                boolean found = false;
                String path = System.getenv("PATH");
                String[] dirs = path.split(":");
                for (String dir : dirs) {
                    File file = new File(dir, command);
                    if (file.exists() && file.canExecute()) {
                        found = true;
                        break;
                    }
                }
                
                if (found) {
                    try {
                        ProcessBuilder pb = new ProcessBuilder(parts);
                        pb.inheritIO(); // Inherit input/output streams to display command output directly
                        Process process = pb.start(); // Start the external program
                        process.waitFor(); // Wait for the process to finish
                    } catch (IOException | InterruptedException e) {
                        System.out.println(command + ": execution failed");
                    }
                } else {
                    System.out.println(command + ": command not found");
                }
            }
            // Print prompt again for next command
            System.out.print("$ ");
            input = scanner.nextLine(); // Read next input
        }
    }
} 

