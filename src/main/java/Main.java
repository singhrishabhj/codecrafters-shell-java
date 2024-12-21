import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage
        //System.out.print("$ ");
	      System.out.print("$ ");
	
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
	
	while(true){ //if the condition is true

                //checks if the input is exit to break the loop
                if(input.equals("exit 0")){
                      //  System.out.println("Program exited"); // we can change it further
                        break;//exit 
                }

                /**
                 * 1. input.startsWith("echo "): This checks if the command starts with the echo keyword.
                 * 2.System.out.println(input.substring(5)): This prints everything after echo (i.e., starting from the 6th character onward). The substring(5) method removes the first 5 characters (echo ) from the input.
                 * --input.substring(5) extracts the portion of the string input starting from index 5 (i.e., the 6th character) to the end of the string, effectively removing the first 5 characters.
                 * 3.Command Handling: If the input is not exit or echo, it prints command not found.
 */
                if(input.startsWith("echo")){
                        System.out.println(input.substring(5));
                  }else{
	          //the command not found will print
                  System.out.println(input + ": command not found");
                  }
        //then again the "$" printed , that command is not correct reneter it 
        System.out.print("$ ");

	//again ask for another input
	input = scanner.nextLine();
	
	
	}
    }
}
