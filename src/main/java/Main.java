import java.util.Scanner;
import java.io.File; // Missing import for File class

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage
        //System.out.print("$ ");
        System.out.print("$ ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (true) { // If the condition is true

            // Checks if the input is exit to break the loop
            if (input.equals("exit 0")) {
                //  System.out.println("Program exited"); // we can change it further
                break; // Exit 
            }

            /**
             * 1. input.startsWith("echo "): This checks if the command starts with the echo keyword.
             * 2.System.out.println(input.substring(5)): This prints everything after echo (i.e., starting from the 6th character onward). The substring(5) method removes the first 5 characters (echo ) from the input.
             * --input.substring(5) extracts the portion of the string input starting from index 5 (i.e., the 6th character) to the end of the string, effectively removing the first 5 characters.
             * 3.Command Handling: If the input is not exit or echo, it prints command not found.
             */
            if (input.startsWith("echo")) {
                // Print everything after 'echo '
                System.out.println(input.substring(5));
            }
            // To check if the line starts with 'type' 
            else if (input.startsWith("type")) {
                String command = input.substring(5); // To take everything after 'type' as input
                // Check if the command is a builtin
                if (command.equals("type") || command.equals("echo") || command.equals("exit")) {
                    System.out.println(command + " is a shell builtin");
                } else {
                    // Get PATH environment variable and split it into directories
                    String path = System.getenv("PATH");
                    String[] dirs = path.split(":");

                    // Flag to check if the command is found
                    boolean found = false;

                    // Loop through each directory in PATH
                    for (String dir : dirs) {
                        File file = new File(dir, command); // Create a File object for the command in the current directory
                        
                        // Check if the file exists and is executable
                        if (file.exists() && file.canExecute()) {
                            System.out.println(command + " is " + file.getAbsolutePath());
                            found = true;
                            break;
                        }
                    }

                    // If the command is not found in any directory
                    if (!found) {
                        System.out.println(command + ": not found");
                    }
                }
            } else {
                // If the command is not recognized (neither echo nor type), print command not found
                System.out.println(input + ": command not found");
            }

            // Then again the "$" printed, that command is not correct, reenter it 
            System.out.print("$ ");

            // Again ask for another input
            input = scanner.nextLine();         
        }
    }
}
