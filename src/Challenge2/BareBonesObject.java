package Challenge2;

public class BareBonesObject {
	String name;
	int value;
	public BareBonesObject(String name) {
		this.name = name;
		this.value = 0;
	}
	
	public void clearOperation() {
		value = 0;
	}
	
	public void increaseOperation() {
		value += 1;
	}
	
	public void decreaseOperation() {
		value -= 1;
	}
	
	public boolean isZero() {
		if (value == 0)
			return true;
		return false;
	}
}
