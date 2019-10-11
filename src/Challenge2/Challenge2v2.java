package Challenge2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Challenge2v2 {

	public static final String PATH = "C:\\Java Eclipse Workspace\\Space Cadets\\src\\Challenge2\\";
	public static final String FILE_NAME = "Challenge2TestData2.txt";
	
	// storing the 
	public static List<Integer> lineStack = new ArrayList<Integer>();
	public static List<String> fileLines;
	public static List<BareBonesObject> myVariables = new ArrayList<BareBonesObject>();
	
	public static int counter = 0;
	public static int previousLine;
	public static String previousData = "";
	
	public static void main(String[] args) throws IOException {
		fileLines = Files.readAllLines(Paths.get(PATH + FILE_NAME));
		readData(counter);
	}
	
	public static void readData(int counter) throws IOException {
		String line;
		while ((line = fileLines.get(counter)) != null) {
			line = line.trim().replace(";", "");

			if (counter + 1 == fileLines.size())
				fileLines.add(null);
			
			// checks if the end command has been reached at the end of a while loop
			if (line.equals("end")) {
				previousLine = counter;
				if (lineStack.size() > 0)
					counter = lineStack.get(lineStack.size() - 1);
				else {
					counter++;
				}
			}
			else {
				// splitLine[0] : command to be executed --- splitLine[1] variable to execute command on
				String[] splitLine = line.split(" ");
				
				// check whether the variable has already been found and created 
				int index = isVariableDefined(splitLine[1]);
				if (index == -1) {
					myVariables.add(new BareBonesObject(splitLine[1].toString()));
					index = myVariables.size() - 1;
				}
				BareBonesObject myBareBonesVariable = myVariables.get(index);
				
				switch (splitLine[0]) {
				case "clear":
					myBareBonesVariable.clearOperation();
					break;
				case "incr":
					myBareBonesVariable.increaseOperation();
					break;
				case "decr":
					myBareBonesVariable.decreaseOperation();
					break;
				case "while":
					loopOperation(index);
					break;
				}
				counter++;
			}
			outputData();
		}
		return;
	}
	
		
	// checks if a variable already exists and has been defined by the interpreter
		// returns true if the variable already exists
		// returns false if the variable does not already exist
	public static int isVariableDefined(String variableName) {
		int counter = 0;
		for (BareBonesObject var : myVariables) {
			if (variableName.equals(var.name)) {
				return counter;
			}
			counter++;
		}
		return -1;
	}
	
	// prints all the variables and their values after each operation that has an effect on one of the values
	public static void outputData() {
		String data = "";
		for (BareBonesObject var : myVariables) {
			data += var.name + " = " + var.value + " | ";
		}
		if (!data.equals(previousData)) {
			System.out.println(data.substring(0, data.length() - 3));
			previousData = data;
		}
	}
	
	public static void loopOperation(int index) throws IOException {
		if (lineStack.size() > 0) {
			if (lineStack.get(lineStack.size() - 1) != counter)
				lineStack.add(counter);	// adds the line for the while loop to the stack
		}
		else {
			lineStack.add(counter);		// adds the line for the while loop to the stack
		}
							
		if (myVariables.get(index).isZero()) {
			// condition is true ==> skip lines until end
			lineStack.remove(lineStack.size() - 1);		// removes the line for the while loop to the stack
			counter = previousLine;	
		}
		else {
			if (lineStack.get(lineStack.size() - 1) == counter) {
				readData(counter + 1);
			}
			if (lineStack.size() > 0)
				counter = lineStack.get(lineStack.size() - 1);	// gets the last element (top) in the stack
			else
				return;
		}
	}
	
}
