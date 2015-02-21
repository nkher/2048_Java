package nkher.Twenty_Forty_Eight_Java;

/* A data structure used as the return type for the shifting logic method in the game container class */
public class ReturnFields {
	
	private int returnValue;
	private boolean isChange;
	
	public ReturnFields() {
		this.isChange = false;
		this.returnValue = Integer.MAX_VALUE;
	}
	
	public int getreturnValue() {
		return returnValue;
	}
	
	public boolean isChange() {
		return isChange == true;
	}
	
	public void setisChange(boolean value) {
		this.isChange = value;
	}
	
	public void setreturnValue(int value) {
		this.returnValue = value;
	}
}
