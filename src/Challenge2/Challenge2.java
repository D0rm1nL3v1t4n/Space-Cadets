package Challenge2;

/*
 *  FIRST VERSION OF CHALLENGE 2
 *  --> See Challenge2v2 for final version
 */

import java.io.*;
import java.util.*;

public class Challenge2 {

	public static final String PATH = "C:\\Java Eclipse Workspace\\Space Cadets\\src\\Challenge2\\";
	public static final String FILE_NAME = "Challenge2TestData2.txt";
	
	public static int loopVariableIndex;
	public static boolean storeLines = false;
	
	public static List<BareBonesObject> myVariables = new ArrayList<BareBonesObject>();
	public static List<String> loopData = new ArrayList<String>();
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(PATH + FILE_NAME));
		readData(reader);
	}
	
	public static void readData(BufferedReader reader) throws IOException {
		String line;
		
		while ((line = reader.readLine()) != null) {
			line = line.trim().replace(";", "");
			
			if (line.equals("end")) {
				line = reader.readLine();
				storeLines = false;
				while (myVariables.get(loopVariableIndex).value != 0) {
					for (String loopLine : loopData) {
						run(loopLine);
					}
				}
			}
			else {
				run(line);
			}
		}
		
		reader.close();
	}
	
	
	public static void run(String line) {
		// splitLine[0] : command to be executed --- splitLine[1] variable to execute command on
		String[] splitLine = line.split(" ");
		
		if (storeLines) {
			loopData.add(line);
		}
		/////////////////////////////////////////////////////////////////////////////
		// check whether the variable has already been found and created 
		int index = isVariableDefined(splitLine[1]);
		if (index == -1) {
			myVariables.add(new BareBonesObject(splitLine[1].toString()));
			index = myVariables.size() - 1;
		}
		BareBonesObject myBareBonesVariable = myVariables.get(index);
		/////////////////////////////////////////////////////////////////////////////
		
		
		/////////////////////////////////////////////////////////////////////////////
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
			loopVariableIndex = index;
			storeLines = true;
			break;
		}
		/////////////////////////////////////////////////////////////////////////////
		
		outputData();
	}
	
	
	
	
	
	
	
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
	
	public static void outputData() {
		String data = "";
		for (BareBonesObject var : myVariables) {
			data += (var.name + " = " + var.value + " | ");
		}
		System.out.println(data + "\n**************************************");
	}
	
}
