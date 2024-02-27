package cars;

import exceptions.CustomException;
import utility.Utility;

public class Car {
	private int yearOfMaking;
	private String engineNumber;
	private String type;

	public Car() {
	}

	public Car(String inputString) {
		System.out.println(inputString);
	}

	public void setYearOfMaking(int yearOfMaking) throws CustomException {
		if (yearOfMaking < 1900 || yearOfMaking > 2024) {
			throw new CustomException("Invalid year of Making : " + yearOfMaking);
		}
		this.yearOfMaking = yearOfMaking;
	}

	public void setEngineNumber(String engineNumber) throws CustomException {
		Utility.validateObject(engineNumber);
		this.engineNumber = engineNumber;
	}

	public void setType(String type) throws CustomException {
		Utility.validateObject(type);
		this.type = type;
	}

	public int getYearOfMaking() {
		return yearOfMaking;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public String getType() {
		return type;
	}

	public void maintenance() {
		System.out.println("Car under maintenance");
	}
}