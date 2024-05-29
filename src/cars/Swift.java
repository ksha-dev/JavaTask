package cars;

import exceptions.CustomException;
import utility.LoggerUtility;
import utility.Utility;

public class Swift extends Car {

	private final static int maxSeats = 5;
	private final static int maxAirbags = 6;



	private int seats;
	private int airbags;
	private String model;
	private String color;

	public Swift() {
		LoggerUtility.DEFAULT_LOGGER.info("Swift object created");
	}

	public void setSeats(int seats) throws CustomException {
		Utility.validateRange(seats, maxSeats, "The maximum seats in this car is : " + maxSeats);
		this.seats = seats;
	}

	public void setAirbags(int airbags) throws CustomException {
		Utility.validateRange(airbags, maxAirbags, "The maximum airbags in this car is : " + maxAirbags);
		this.airbags = airbags;
	}

	public void setModel(String model) throws CustomException {
		Utility.validateObject(model);
		this.model = model;
	}

	public void setColor(String color) throws CustomException {
		Utility.validateObject(color);
		this.color = color;
	}

	public int getSeats() {
		return seats;
	}

	public int getAirbags() {
		return airbags;
	}

	public String getModel() {
		return model;
	}

	public String getColor() {
		return color;
	}
}