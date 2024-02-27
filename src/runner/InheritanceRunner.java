package runner;

import cars.Swift;
import cars.SCross;
import cars.XUV;
import exceptions.CustomException;
import cars.Car;

import java.util.logging.Level;
import java.util.logging.Logger;

import bird.Duck;
import bird.ParrotMod;
import utility.InputUtil;
import utility.LoggerUtility;

public class InheritanceRunner {

	private static Logger logger = null;
	static {
		try {
			logger = LoggerUtility.getNewLogger(InheritanceRunner.class.getName());
			logger.setLevel(Level.INFO);
			LoggerUtility.addFileHandler(logger, logger.getName(), LoggerUtility.DEFAULT_LOGGER_PATH);
		} catch (Exception e) {
			LoggerUtility.DEFAULT_LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public static void main(String[] args) {

		logger.info("Inheritance Runner");
		logger.info("The following are the list of operations");

		boolean isProgramActive = true;
		int numberOfQuestions = 10;

		while (isProgramActive) {
			logger.info("\n2. Create a swift Object. Set and get its values"
					+ "\n3. Create a Scross Object. Set and get its values including superclass methods"
					+ "\n4 & 5. Create a method in runner with Car as the argument. Pass subclasses to it"
					+ "\n6. Create a method in runner with Swift as the argument. Pass Swift and Car object to it"
					+ "\n7. Create a maintenance method in car class. Override it in SCross class. Pass various objects and observer"
					+ "\n8. Overload the Car constructor which accepts a string and prints it. Implement it in XUV constructor"
					+ "\n9. Create an abstract class BirdAbstract. Create its object"
					+ "\n10. Create a bird class and duck class. Implement duck in runner" + "\n\nTo exit, enter 0"
					+ "\n==============================================");

			int choice = -1;

			do {
				try {
					System.out.print("Enter your choice (0 to " + numberOfQuestions + "): ");
					choice = InputUtil.getIntInput();
				} catch (Exception e) {
					logger.info(e.getMessage());
				}
			} while (choice < 0 || choice > numberOfQuestions);

			try {
				switch (choice) {
				case 0:
					isProgramActive = false;
					logger.info("Thankyou for using the program");
					InputUtil.closeScanner();
					break;

				case 2:
					question2();
					break;
				case 3:
					question3();
					break;
				case 4:
				case 5:
					question4();
					break;
				case 6:
					question6();
					break;
				case 7:
					question7();
					break;
				case 8:
					question8();
					break;
				case 9:
					question9();
					break;
				case 10:
					question10();
					break;

				default:
					logger.info("The choice is invalid");
				}
			} catch (CustomException e) {
				logger.severe(e.getMessage());
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
			logger.info("==============================================");
		}
	}

	public static void question2() throws CustomException {
		Swift newSwift = new Swift();
		System.out.print("Enter the number of seats of Swift : ");
		newSwift.setSeats(InputUtil.getIntInput());
		System.out.print("Enter the number of airbags in Swift : ");
		newSwift.setAirbags(InputUtil.getIntInput());
		System.out.print("Enter the model of your Swift car : ");
		newSwift.setModel(InputUtil.getStringInput());
		System.out.print("Enter the color of your Swift car : ");
		newSwift.setColor(InputUtil.getStringInput());

		logger.info("\nAttributes of Swift Object : ");
		logger.info("-----------------------------------------------");
		logger.info("Seats : " + newSwift.getSeats());
		logger.info("Airbags : " + newSwift.getAirbags());
		logger.info("Model : " + newSwift.getModel());
		logger.info("Color : " + newSwift.getColor());
	}

	public static void question3() throws CustomException {
		SCross scross = new SCross();
		System.out.print("Enter the year of making of your car :");
		scross.setYearOfMaking(InputUtil.getIntInput());
		System.out.print("Enter the engine number of the car : ");
		scross.setEngineNumber(InputUtil.getStringInput());
		System.out.print("Enter the type of your car : ");
		scross.setType(InputUtil.getStringInput());
		System.out.print("Enter the number of airbags in your car : ");
		scross.setAirbags(InputUtil.getIntInput());
		System.out.print("Enter the number of seats : ");
		scross.setSeats(InputUtil.getIntInput());
		System.out.print("Enter the model of the car : ");
		scross.setModel(InputUtil.getStringInput());
		System.out.print("Enter the color of the car : ");
		scross.setColor(InputUtil.getStringInput());

		logger.info("\nAttributes of SCross Object : ");
		logger.info("-----------------------------------------------");
		logger.info("Year of making : " + scross.getYearOfMaking());
		logger.info("Engine Number : " + scross.getEngineNumber());
		logger.info("Car Type : " + scross.getType());
		logger.info("\nSeats : " + scross.getSeats());
		logger.info("Airbags : " + scross.getAirbags());
		logger.info("Model : " + scross.getModel());
		logger.info("Color : " + scross.getColor());
	}

	public static void question4() {
		Swift swift = new Swift();
		acceptCar(swift);
		SCross scross = new SCross();
		acceptCar(scross);
		XUV xuv = new XUV();
		acceptCar(xuv);
	}

	public static void acceptCar(Car car) {
		logger.info(car.getClass().toString());
		if (car instanceof Swift) {
			logger.info("Hatch");
		} else if (car instanceof SCross) {
			logger.info("Sedan");
		}
		if (car instanceof XUV) {
			logger.info("SUV");
		}
	}

	public static void question6() {
		Swift swift = new Swift();
		acceptSwift(swift);

		// Car car = new Car();
		// acceptSwift(car);

		// XUV xuv = new XUV();
		// acceptSwift(xuv);
		// SCross scross = new SCross();
		// acceptSwift(scross);
	}

	public static void acceptSwift(Swift swift) {
	}

	public static void question7() {
		SCross scross = new SCross();
		scross.maintenance();

		Car scrossCar = new SCross();
		scrossCar.maintenance();

		Car newCar = new Car();
		newCar.maintenance();

		Swift swift = new Swift();
		swift.maintenance();
	}

	public static void question8() {
		XUV xuv = new XUV();
		logger.info(xuv.toString());
	}

	public static void question9() {
		// BirdAbstract bird = new BirdAbstract();
		ParrotMod parrot = new ParrotMod();

		parrot.fly();
		parrot.speak();
	}

	public static void question10() {
		Duck duck = new Duck();
		duck.fly();
		duck.speak();
	}
}