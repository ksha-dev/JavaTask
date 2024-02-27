package bird;

import exceptions.CustomException;
import utility.LoggerUtility;
import utility.Utility;

public class Duck extends Bird {
	private String string;
	
	public Duck() {}
	
	public Duck(String string) throws CustomException {
		Utility.validateObject(string);
		this.string = string;
	}
	
	public void fly() {
		LoggerUtility.DEFAULT_LOGGER.info("Duck flys");
	}
	
	@Override
	public String toString() {
		return string;
	}
}