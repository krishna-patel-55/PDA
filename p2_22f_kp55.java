// Krishna Patel (kp55)
// Project 2 PDA cs341005
// 11/3/22
import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
public class p2_22f_kp55 {
	private static File inputFile;
	private static Scanner scan;
	public static void main(String args[]) throws FileNotFoundException {
		System.out.println("Project 2 for CS 341");
		System.out.println("Section number:  005");
		System.out.println("Semester: Fall 2022");
		System.out.println("Written by: Krishna Patel, kp55");
		System.out.println("Instructor: Marvin Nakayama, marvin@njit.edu");
		System.out.println();
		inputFile = new File(args[0]); //takes in input file as arg
		scan = new Scanner(inputFile); // to read file
		prompt(); //begin asking user for input	
	}
	private static void prompt() {
		//fresh stack before each string
		Stack<Character> stack = new Stack<>(); 
		System.out.print("Do you want to enter a string? (y/n): ");
		String confirm = scan.nextLine();
		System.out.println(confirm);
		if(confirm.contentEquals("y")) {
			System.out.print("Enter a string over Σ: ");
			String input = scan.nextLine();
			System.out.println(input);
			System.out.println("Starting at state q0");
			//always begins at start state and pass in the string, starting index, and empty stack
			int result = startState(input,0,stack);
			//returns the last state the character reaches either from accepting the string or crashing 
			if(result == -1) {
				System.out.println("Crashed at q7");
			}
			else if(result == 7) {
				System.out.println("Accepted at state q7\n\n");
			}
			else {
				System.out.println("Crashed at q" + result + "\n\n");
			}
			prompt();
		}
		else if(confirm.contentEquals("n")){
			System.out.print("----------------------\n");
		}
	}
	//using ascii for character comparisonss
	private static int startState(String str, int i, Stack<Character> stack) { 
		//State q0(Starting state)
		char read = str.charAt(i);
		// if delimiter '%' is the first character, pops nothing, pushes the delimiter to the stack
		if(read == 37) {
			System.out.println(read + ", ε -> " +stack.push(read));
			//Move on to next state (q1) passing the str, index to next char and updated stack
			return stateOne(str, i+1, stack);
		}
		//otherwise no delimiter and crash, return this state
		return 0;	
	}
	private static int stateOne(String str, int i, Stack<Character> stack) {
		//State q1
		System.out.println("At state q1");
		char read = str.charAt(i);
		//if left parenthesis then pop nothing, push it to stack and go to itself(q1)
		if(read == 40) {
			System.out.println(read + ", ε -> " +stack.push(read));
			return stateOne(str, i+1, stack);
		}
		//if dot then pop nothing push nothing to stack and go to q2
		else if(read == 46) {
			System.out.println(read + ", ε -> ε");
			return stateTwo(str, i+1, stack);
		}
		//if digit then pop nothing push nothing to stack and go to q4
		else if(read >= 48 && read <= 57) {
			System.out.println(read + ", ε -> ε");
			return stateFour(str, i+1, stack);
		}
		//otherwise crashed and return state
		else {
			return 1;
		}
	}
	private static int stateTwo(String str, int i, Stack<Character> stack) {
		//State q2
		System.out.println("At state q2");
		char read = str.charAt(i);
		//if digit then pop nothing push nothing to stack and go to q3
		if(read >= 48 && read <= 57) {
			System.out.println(read + ", ε -> ε");
			return stateThree(str, i+1, stack);
		}
		//otherwise crashed and return state
		else {
			return 2;
		}
	}
	private static int stateThree(String str, int i, Stack<Character> stack) {
		//State q3
		System.out.println("At state q3");
		char read = str.charAt(i);
		//if operator then pop nothing push nothing to stack and go to q1
		if(read == 42 || read == 43 || read == 45 || read == 47) {
			System.out.println(read + ", ε -> ε");
			return stateOne(str, i+1, stack);
		}
		//if digit then pop nothing push nothing to stack and go to q3
		else if(read >= 48 && read <= 57) {
			System.out.println(read + ", ε -> ε");
			return stateThree(str, i+1, stack);
		}
		//read right parenthesis, pop the left parenthesis, push nothing, move to q6
		else if(read == 41) {
			//if what is popped is not the ( then reject string
			char popped = stack.pop();
			if(popped != 40) return 3;
			System.out.println(read + ", " + popped +" -> ε");
			return stateSix(str, i+1, stack);
		}
		//read %, pop the leading %, push nothing accept string by moving to q7
		else if(read == 37) {
			//if what is popped is not the matching % then reject string
			char popped = stack.pop();
			if(popped != 37) return 3;
			System.out.println(read + ", " + popped +" -> ε");
			return acceptingState(str, i+1, stack);
		}
		//otherwise crashed and return state
		else {
			return 3;
		}
	}
	private static int stateFour(String str, int i, Stack<Character> stack) {
		//State q4
		System.out.println("At state q4");
		char read = str.charAt(i);
		//if digit then pop nothing push nothing to stack and go to q4
		if(read >= 48 && read <= 57) {
			System.out.println(read + ", ε -> ε");
			return stateFour(str, i+1, stack);
		}
		//if dot then pop nothing push nothing to stack and go to q5
		else if(read == 46) {
			System.out.println(read + ", ε -> ε");
			return stateFive(str, i+1, stack);
		}
		//otherwise crashed and return state
		else {
			return 4;
		}
	}
	private static int stateFive(String str, int i, Stack<Character> stack) {
		//State q5
		System.out.println("At state q5");
		char read = str.charAt(i);
		//if operator then pop nothing push nothing to stack and go to q1
		if(read == 42 || read == 43 || read == 45 || read == 47) {
			System.out.println(read + ", ε -> ε");
			return stateOne(str, i+1, stack);
		}
		//if digit then pop nothing push nothing to stack and go to q5
		else if(read >= 48 && read <= 57) {
			System.out.println(read + ", ε -> ε");
			return stateFive(str, i+1, stack);
		}
		//read right parenthesis, pop the left parenthesis, push nothing, move to q6
		else if(read == 41) {
			//if what is popped is not the ( then reject string
			char popped = stack.pop();
			if(popped != 40) return 5;
			System.out.println(read + ", " + popped +" -> ε");
			return stateSix(str, i+1, stack);
		}
		//read %, pop the leading %, push nothing accept string by moving to q7
		else if(read == 37) {
			//if what is popped is not the matching % then reject string
			char popped = stack.pop(); 
			if(popped != 37) return 5;
			System.out.println(read + ", " + popped +" -> ε");
			return acceptingState(str, i+1, stack);
		}
		//otherwise crashed and return state
		else {
			return 5;
		}
	}
	private static int stateSix(String str, int i, Stack<Character> stack) {
		//State q6
		System.out.println("At state q6");
		char read = str.charAt(i);
		//if operator then pop nothing push nothing to stack and go to q1
		if(read == 42 || read == 43 || read == 45 || read == 47) {
			System.out.print(read + ", ε -> ε ");
			System.out.println();
			return stateOne(str, i+1, stack);
		}
		//read right parenthesis, pop the left parenthesis, push nothing, move to q6
		else if(read == 41) {
			//if what is popped is not the ( then reject string
			char popped = stack.pop();
			if(popped != 40) return 5;
			System.out.println(read + ", " + popped +" -> ε");
			return stateSix(str, i+1, stack);
		}
		//read %, pop the leading %, push nothing accept string by moving to q7
		else if(read == 37) {
			//if what is popped is not the matching % then reject string
			char popped = stack.pop();
			if(popped != 37) return 6;
			System.out.println(read + ", " + popped +" -> ε");
			return acceptingState(str, i+1, stack);
		}
		//otherwise crashed and return state
		else {
			return 6;
		}
	}
	private static int acceptingState(String str, int i, Stack<Character> stack) {
		//State q7(accepting state)
		System.out.println("At state q7");
		//return and accept string when each character has been read through and the stack has been emptied
		if(i == str.length() && stack.isEmpty())
			return 7;
		//otherwise all characters has not been read and not contained within % delimiters
		else {
			return -1;
		}
	}
}