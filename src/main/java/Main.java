import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        // Print initial prompt to indicate shell readiness
        System.out.print("$ ");

        Scanner scanner = new Scanner(System.in); // Create Scanner object to read user input
        String input = scanner.nextLine(); // Read user input

        while (true) {
            // Exit condition when input is "exit 0"
            if (input.equals("exit 0")) {
                // Break the loop to terminate the shell
                break;
            }

            // Handle echo command to print arguments after "echo"
            if (input.startsWith("echo")) {
                // Extract and print everything after the echo command
                System.out.println(input.substring(5).trim());
            } 
            // Handle type command to check if a command is built-in or executable
            else if (input.startsWith("type")) {
                String command = input.substring(5).trim(); // Extract and trim the command name
                
                // Check if the command is a shell built-in
                if (command.equals("type") || command.equals("echo") || command.equals("exit")) {
                    System.out.println(command + " is a shell builtin");
                } else {
                    // Get the PATH environment variable to search for executables
                    String path = System.getenv("PATH");
                    String[] dirs = path.split(":"); // Split PATH into directories
                    boolean found = false;
                    
                    // Loop through each directory in PATH to locate the command
                    for (String dir : dirs) {
                        File file = new File(dir, command); // Create file object for the command in the directory
                        
                        // Check if the file exists and is executable
                        if (file.exists() && file.canExecute()) {
                            System.out.println(command + " is " + file.getAbsolutePath());
                            found = true;
                            break; // Stop searching once the executable is found
                        }
                    }
                    // If the command is not found in any directory
                    if (!found) {
                        System.out.println(command + ": not found");
                    }
                }
            } 
            // Handle external programs execution with arguments
            else {
                String[] parts = input.split(" "); // Split input into command and arguments
                String command = parts[0]; // First element is the command name
                
                boolean found = false;
                String path = System.getenv("PATH");
                String[] dirs = path.split(":");
                File executable = null;
                
                // Search for the executable file in the PATH directories
                for (String dir : dirs) {
                    File file = new File(dir, command);
                    if (file.exists() && file.canExecute()) {
                        found = true;
                        executable = file;
                        break; // Stop searching once found
                    }
                }
                
                if (found) {
                    try {
                        // Create ProcessBuilder to run the external command
                        ProcessBuilder pb = new ProcessBuilder(parts);
                        pb.directory(executable.getParentFile()); // Set directory of executable
                        pb.inheritIO(); // Inherit input/output streams to display command output directly
                        Process process = pb.start(); // Start the external program
                        process.waitFor(); // Wait for the process to finish
                    } catch (IOException | InterruptedException e) {
                        System.out.println(command + ": execution failed");
                    }
                } else {
                    // Print message if command is not found
                    System.out.println(command + ": command not found");
                }
            }
            // Print prompt again for next command input
            System.out.print("$ ");
            input = scanner.nextLine(); // Read next input
        }
    }
}

