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
	//the command not found will print
        System.out.println(input + ": command not found");

	//then again the "$" printed , that command is not correct reneter it 
        System.out.print("$ ");

	//again ask for another input
	input = scanner.nextLine();
	
	
	}
    }
}
