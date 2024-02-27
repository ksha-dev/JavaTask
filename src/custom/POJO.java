package custom;

import exceptions.CustomException;
import utility.Utility;

public class POJO {
	private String string;
	private int integer;
	
	//Constructor
	public POJO() {}
	
	public POJO(String string, int integer) throws CustomException {
		setString(string);
		setInteger(integer);
	}
	
	//setters
	public void setString(String string) throws CustomException {
		Utility.validateObject(string);
		this.string = string;
	}
	
	public void setInteger(int integer) {
		this.integer = integer;
	}
	
	//getters
	public String getString() {
		return string;
	}
	
	public int getInteger() {
		return integer;
	}
	
	@Override
	public String toString() {
		return "String : "+getString()+", Integer : "+getInteger();
	}
}
