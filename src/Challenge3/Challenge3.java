package Challenge3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Challenge3 {

	// the path for the test data
	public static final String PATH = "C:\\Java Eclipse Workspace\\Space Cadets\\src\\Challenge3\\";
	// the name of the test data file
	public static final String FILE_NAME = "Challenge3TestData4.txt";
	
	// stack for the link numbers of the beginning of while loops
	public static Stack<Integer> lineStack = new Stack<Integer>();
	// stack for the boolean value of whether lines in an if statement need to be executed
	public static Stack<Boolean> ifStack = new Stack<Boolean>();
	// list for all the lines in the file
	public static List<String> fileLines;
	// list for all the variables in the Bare Bones program
	public static List<BareBonesObject> myVariables = new ArrayList<BareBonesObject>();
	
	// the previous line run in the program when a while loop returns to the start
	public static int previousLine;
	// the previous output for all the variables and their values
	public static String previousData = "";
	
	/*
	 * starts the interpreter
	 */
	public static void main(String[] args) throws IOException {
		fileLines = Files.readAllLines(Paths.get(PATH + FILE_NAME));
		fileLines.add(null);
		int counter = 0;
		readData(counter);
	}
	
	/*
	 * carries out the interpreting of the Bare Bones program	
	 * @param	counter		the counter for the line number that will be read in the program
	 */
	public static void readData(int counter) throws IOException {
		String line;
		// loops until the interpreter reaches the end of the file
		while ((line = fileLines.get(counter)) != null) {
			// removes all whitespace and the end of line character ';' from the line read
			line = line.trim().replace(";", "");
			
			// checks if the end command has been reached at the end of a while loop
			if (line.equals("end while")) {
				previousLine = counter;
				if (lineStack.size() > 0)
					counter = lineStack.peek();
				else {
					counter++;
				}
			}
			// checks if the interpreter should interpret the line that has been read
			else if (shouldLineRun(line)) {
				if (line.equals("end if")) {
					ifStack.pop();
					counter++;
					line = fileLines.get(counter).trim().replace(";", "");
				}
				// splits the line based on whitespace
				String[] splitLine = line.split(" ");
				
				// check whether the variable has already been defined by the interpreter
				int index = isVariableDefined(splitLine[1]);
				if (splitLine[1].equals("do"))
					index = 0;
				if (index == -1) {
					// creates a new object for the variable found
					myVariables.add(new BareBonesObject(splitLine[1].toString()));
					index = myVariables.size() - 1;
				}
				BareBonesObject myBareBonesVariable = myVariables.get(index);
				
				// switch statement - allowing the interpreter to work out what to do
				switch (splitLine[0]) {
				case "clear":
					myBareBonesVariable.clearOperation();
					break;
				case "incr":
					if (splitLine.length == 2)
						myBareBonesVariable.increaseOperation();
					else
						myBareBonesVariable.increaseOperation(Integer.parseInt(splitLine[3]));
					break;
				case "decr":
					if (splitLine.length == 2)
						myBareBonesVariable.decreaseOperation();
					else
						myBareBonesVariable.decreaseOperation(Integer.parseInt(splitLine[3]));
					break;
				case "set":
					int varAIndex = isVariableDefined(splitLine[3]);
					int varBIndex = -1;
					int operationValue = 0;
					
					if (splitLine.length == 4) {
						if (varAIndex == -1) {
							myBareBonesVariable.setOperation(Integer.parseInt(splitLine[3]));
						}
						else {	// is a variable
							myBareBonesVariable.setOperation(myVariables.get(varAIndex).getValue());
						}
					}
					else if (splitLine.length == 6) {
						varBIndex = isVariableDefined(splitLine[5]);
						if (varBIndex == -1)
							operationValue = Integer.parseInt(splitLine[5]);
						else
							operationValue = myVariables.get(varBIndex).getValue();
						
						if (splitLine[1].equals(splitLine[3])) {
							myBareBonesVariable.setOperation(splitLine[4], operationValue);
						}
						else {
							myBareBonesVariable.setOperation(myVariables.get(varAIndex).getValue(), splitLine[4], Integer.parseInt(splitLine[5]));
						}
					}
					break;
				case "while":
					
					if (lineStack.size() > 0) {
						if (lineStack.peek() != counter)
							lineStack.push(counter);	// adds the line for the while loop to the stack
					}
					else {
						lineStack.push(counter);		// adds the line for the while loop to the stack
					}
										
					if (myVariables.get(index).isEqualToN(Integer.parseInt(splitLine[3]))) {
						// condition is true ==> skip lines until end
						lineStack.pop();		// removes the line for the while loop to the stack
						counter = previousLine;	
					}
					else {
						if (lineStack.peek() == counter) {
							readData(counter + 1);
						}
						if (lineStack.size() > 0)
							counter = lineStack.peek();	// gets the last element (top) in the stack
						else
							return;
					}
					break;
				
				case "if":
					ifStack.push(false);
					if (myBareBonesVariable.isEqualToN(Integer.parseInt(splitLine[3]))) {
						// execute the first part of the if statement	
						toggleTopStackValue(true);
					}
					break;
					
				case "else":
					if (ifStack.peek()) {
						toggleTopStackValue(false);
					}
					else {
						toggleTopStackValue(true);
					}
					break;
				}
				counter++;
			}
			else {
				counter++;
			}
			outputData();
		}
		return;
	}
	
		
	/*
	 * checks if a variable already exists and has been defined by the interpreter
	 * 
	 * @param	varaibleName	
	 * @return	a boolean value for whether the variable is already defined
	 */
	public static int isVariableDefined(String variableName) {
		int counter = 0;
		for (BareBonesObject var : myVariables) {
			if (variableName.equals(var.getName())) {
				return counter;
			}
			counter++;
		}
		return -1;
	}
	
	/*
	 * prints all the variables and their values after each operation that has an effect on one of the values
	 */
	public static void outputData() {
		String data = "";
		for (BareBonesObject var : myVariables) {
			data += var.getName() + " = " + var.getValue() + " | ";
		}
		if (!data.equals(previousData)) {
			System.out.println(data.substring(0, data.length() - 3));
			previousData = data;
		}
	}
	
	/*
	 * toggles the top value in the stack between true --> false or false --> true
	 */
	public static void toggleTopStackValue(boolean value) {
		ifStack.pop();
		ifStack.push(value);
	}
	
	/*
	 * checks if the current line should be read and interpreted
	 * 
	 * @return a boolean value if the line should be read or not
	 */
	public static boolean shouldLineRun(String line) {
		if (line.equals("end if") || line.equals("else do"))
			return true;
		if (ifStack.size() > 0) {
			if (!ifStack.peek()) {
				return false;
			}
		}
		return true;
	}
}
