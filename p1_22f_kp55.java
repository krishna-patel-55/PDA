// Krishna Patel
// kp55
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
public class p1_22f_kp55 {
	private static File outputFile;
	private static File inputFile;
    private static  PrintWriter output;
    private static Scanner scan;
	public static void main(String args[]) throws IOException {
		
		inputFile = new File("prog1.txt");
		outputFile = new File("kp55P1_Output.txt");
		outputFile.createNewFile();
		output = new PrintWriter(outputFile.getName());
		scan = new Scanner(inputFile);
		
		output.println("Project 1 for CS 341");
		output.println("Section number: 005");
		output.println("Semester: Fall 2022");
		output.println("Written by: Krishna Patel, kp55");
		output.println("Instructor: Marvin Nakayama, marvin@njit.edu");
		output.println();
		System.out.println("Σ = {a-z, 0-9, ., @}\n");
		
		prompt();
		
		output.close();
		
	}
	private static void prompt() {
			
			System.out.println("Do you want to enter a string? (y/n): ");
			String confirm = scan.nextLine();
			
			if(confirm.contentEquals("y")) {
				
				System.out.print("Enter a string over Σ: ");
				String input = scan.nextLine();
				System.out.println("\n");
				
				output.print("Input string: \"" + input + "\"\n");
				output.print("Starting at state X0\n");
				
				Boolean result = startState(input,0);
				if(result) {
					output.print("Accepted\n\n");
				}
				else {
					output.print("Rejected\n\n");
				}
				
				prompt();
			}
			else if(confirm.contentEquals("n")){
				System.out.print("----------------------\n");
			}
	}
	private static Boolean startState(String symbol, int i) { 
		
		if(i == symbol.length())
			return false;
		
		if(i != 0) {
			output.print("symbol \'" + symbol.charAt(i-1) + "\' at state X0");
			output.println();
		}
		
		if(symbol.charAt(i) >= 97 && symbol.charAt(i) <= 122) {
			return stateOne(symbol, i+1);
		}
		else {
			return false;
		}
		
	}
	private static Boolean stateOne(String symbol, int i) {
		
		output.print("symbol \'" + symbol.charAt(i-1) + "\' at state X1");
		output.println();
		
		if(i  == symbol.length())
			return false;
		
		if( (symbol.charAt(i) >= 97 && symbol.charAt(i) <= 122) || (symbol.charAt(i) >= 48 && symbol.charAt(i) <= 57)) {
			return stateOne(symbol, i+1);
		}
		else if(symbol.charAt(i) == 46){
			return startState(symbol, i+1);
		}
		else if(symbol.charAt(i) == 64) {
			return stateTwo(symbol, i+1);
		}
		else {
			return false;
		}
	}
	private static Boolean stateTwo(String symbol, int i) {
		
		output.print("symbol \'" + symbol.charAt(i-1) + "\' at state X2");
		output.println();
		
		if(i == symbol.length())
			return false;
		
		if( (symbol.charAt(i) >= 97 && symbol.charAt(i) <= 122)) {
			return stateThree(symbol, i+1);
		}
		else {
			return false;
		}
	}
	private static Boolean stateThree(String symbol, int i) {
		
		output.print("symbol \'" + symbol.charAt(i-1) + "\' at state X3");
		output.println();
		
		if(i == symbol.length())
			return false;
		
		if( (symbol.charAt(i) >= 97 && symbol.charAt(i) <= 122) || (symbol.charAt(i) >= 48 && symbol.charAt(i) <= 57)) {
			return stateThree(symbol, i+1);
		}
		else if(symbol.charAt(i) == 46){
			return stateFour(symbol, i+1);
		}
		else {
			return false;
		}
	}
	private static Boolean stateFour(String symbol, int i) {
		
		output.print("symbol \'" + symbol.charAt(i-1) + "\' at state X4");
		output.println();
		
		if(i == symbol.length())
			return false;
		
		if( (symbol.charAt(i) >= 97 && symbol.charAt(i) <= 122)) {
			
			if(symbol.charAt(i) == 99){
				return stateFive(symbol, i+1);
			}
			else 
				return stateThree(symbol, i+1);
		}
		else {
			return false;
		}
	}
	private static Boolean stateFive(String symbol, int i) {
		
		output.print("symbol \'" + symbol.charAt(i-1) + "\' at state X5");
		output.println();
		
		if(i == symbol.length())
			return false;
		
		if( (symbol.charAt(i) >= 97 && symbol.charAt(i) <= 122)) {
			
			if(symbol.charAt(i) == 111){
				return stateSix(symbol, i+1);
			}
			else 
				return stateThree(symbol, i+1);
		}
		else if( (symbol.charAt(i) >= 48 && symbol.charAt(i) <= 57) ) {
			return stateThree(symbol, i+1);
		}
		else {
			return false;
		}
	}
	private static Boolean stateSix(String symbol, int i) {
		
		output.print("symbol \'" + symbol.charAt(i-1) + "\' at state X6");
		output.println();
		
		if(i == symbol.length())
			return false;
		
		if( (symbol.charAt(i) >= 97 && symbol.charAt(i) <= 122)) {
			
			if(symbol.charAt(i) == 109){
				return acceptingState(symbol, i+1);
			}
			else 
				return stateThree(symbol, i+1);
		}
		else if( (symbol.charAt(i) >= 48 && symbol.charAt(i) <= 57) ) {
			return stateThree(symbol, i+1);
		}
		else {
			return false;
		}
	}
	private static Boolean acceptingState(String symbol, int i) {
		
		output.print("symbol \'" + symbol.charAt(i-1) + "\' at state X7");
		output.println();
		
		if(i == symbol.length())
			return true;
		
		if( (symbol.charAt(i) >= 97 && symbol.charAt(i) <= 122) || (symbol.charAt(i) >= 48 && symbol.charAt(i) <= 57)) {
			return stateThree(symbol, i+1);
		}
		else if(symbol.charAt(i) == 46) {
			return stateFour(symbol, i+1);
		}
		else {
			return false;
		}
	}
}
