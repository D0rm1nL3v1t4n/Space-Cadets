package Challenge3;

public class BareBonesObject {
	private String name;
	private int value;
	
	public BareBonesObject(String name) {
		this.name = name;
		this.value = 0;
	}
	
	/*
	 * getting method for the value of the variable
	 * @return	returns the integer of the variable's value
	 */
	public int getValue() {
		return value;
	}
	
	/*
	 * getting method for the name of the variable
	 * @return	returns the variable name
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * sets the value of the Bare Bones variable to
	 */
	public void clearOperation() {
		value = 0;
	}
	
	/*
	 * increase operation for the Bare Bones variable, increasing its value by 1
	 */
	public void increaseOperation() {
		value += 1;
	}
	
	/*
	 * overloaded, increase operation for the Bare Bones variable
	 * @param	increaseVal		the value that the variable's value will be increased by
	 */
	public void increaseOperation(int increaseVal) {
		value += increaseVal;
	}
	
	/*
	 * decrease operation for the Bare Bones variable, decreasing its value by 1
	 */
	public void decreaseOperation() {
		value -= 1;
	}
	
	/*
	 * overloaded, decrease operation for the Bare Bones variable
	 * @param	decreaseVal		the value that the variable's value will be decreased by
	 */
	public void decreaseOperation(int decreaseVal) {
		value -= decreaseVal;
	}
	
	
	/*
	 * changes the value of the Bare Bones variable based
	 * 	carries out the operation for
	 * 			X = Y * 4		where X and Y are Bare Bones variables
	 * 
	 * @param	startingValue	the value that the Bare Bones variable starts at
	 * @param	operation		the operand for the operation that will happen on the variable
	 * @param	operationValue	the value for the operation
	 */
	public void setOperation(int startingValue, String operation, int operationValue) {
		switch (operation) {
			case "+":
				value = startingValue + operationValue;
				break;
			case "-":
				value = startingValue - operationValue;
				break;
			case "*":
				value = startingValue * operationValue;
				break;
			case "/":
				value = startingValue / operationValue;
				break;
		}
	}

	/*
	 * overloading used on the method if the operation is of the form
	 * 			X = X * 4		where X is a Bare Bones variable
	 */
	public void setOperation(String operation, int operationValue) {
		switch (operation) {
			case "+":
				value += operationValue;
				break;
			case "-":
				value -= operationValue;
				break;
			case "*":
				value *= operationValue;
				break;
			case "/":
				value /= operationValue;
				break;
		}
	}
	
	/*
	 * sets the new value for the Bare Bones variable
	 * @param	newValue	the new value for the variable to be set to
	 */
	public void setOperation(int newValue) {
		value = newValue;
	}
	
	
	/*
	 * checks if the value of the Bare Bones variable is equal to a specified other value
	 * @param	n	the values being compared to
	 * @return 		boolean return value for whether n is equal to or not equal to the value of the variable
	 */
	public boolean isEqualToN(int n) {
		if (value == n)
			return true;
		return false;
	}
	
}
