package runner;

import java.lang.reflect.Constructor;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import bird.Duck;
import custom.POJO;
import constants.RainbowColors;
import custom.SingletonClass;
import exceptions.CustomException;
import task.FileTask;
import utility.LoggerUtility;
import utility.InputUtil;
import utility.Utility;

public class BasicProgrammingRunner {
	private static FileTask task = new FileTask();
	private static Logger logger = null;

	static {
		try {
			logger = LoggerUtility.getNewLogger(BasicProgrammingRunner.class.getName());
			logger.setLevel(Level.INFO);
			LoggerUtility.addFileHandler(logger, logger.getName(), LoggerUtility.DEFAULT_LOGGER_PATH);
		} catch (CustomException e) {
			LoggerUtility.DEFAULT_LOGGER.severe(e.getMessage());
		}
	}

	public static void main(String... args) {
		boolean isProgramActive = true;
		int numberOfQuestions = 12;
		logger.info("Basic Programming Runner Program");
		while (isProgramActive) {

			logger.info("1 - Create a File \"sample.txt\" and store three lines in it"
					+ "\n2 - Create a File \"myprops.txt\" and store Properties with a minimum of 5 keys and values"
					+ "\n3 - Read the properties from the \"myprops.txt\" and print the keys and values"
					+ "\n4 - Perform 1, 2 and 3 in /home/myDir/ path programmatically"
					+ "\n5 - In a class, write an additional constructor with string input. When object is printed, the string should print automatically"
					+ "\n6 - Write a class POJO, having a string and integer. Get the values through constructor. When object is printed, the string and integer should print automatically"
					+ "\n7 - Create a POJO class object. Set the values through setters. Print the object"
					+ "\n8 - Create and perform operations in POJO class, without importing the class"
					+ "\n9 - Create an Enum with rainbow colors. Print the enum with values"
					+ "\n10 - Create a singleton class object. Create anothoer object and compare both"
					+ "\n11 - Date Time task" + "\nTo exit, enter 0"
					+ "\n\n-----------------------------------------------------------------");

			int choice = -1;
			do {
				try {
					
					logger.info("Enter your choice (0 to " + numberOfQuestions + "): ");
					choice = InputUtil.getIntInput();
					if (choice < 0 || choice > numberOfQuestions) {
						logger.warning("Invalid Choice entered.");
					}
				} catch (Exception e) {
					logger.severe(e.getMessage());
				}
			} while (choice < 0 || choice > numberOfQuestions);
			logger.info("Option " + choice + " selected.");

			try {
				switch (choice) {
				case 0:
					isProgramActive = false;
					logger.info("Thank you for using the program");
					InputUtil.closeScanner();
					break;

				case 1:
					question1(null, "sample.txt");
					break;

				case 2:
					question2(null, "myprops.txt");
					break;

				case 3:
					question3(null, "myprops.txt");
					break;

				case 4:
					String path = FileTask.DEFAULT_FILE_PATH + FileTask.DEFAULT_SEPARATOR + "myDir";
					question1(path, "sample.txt");
					question2(path, "myprops.txt");
					question3(path, "myprops.txt");
					break;

				case 5:
					logger.info("Creating a Duck Object");
					logger.info("Enter a string : ");
					String string5 = InputUtil.getStringInput();
					Duck duck = new Duck(string5);
					logger.info("Pring the Duck object : ");
					logger.info(duck.toString());
					break;

				case 6:
					logger.info("Creating a POJO object : ");
					logger.info("Enter a string : ");
					String string6 = InputUtil.getStringInput();
					logger.info("Enter an integer : ");
					int integer6 = InputUtil.getIntInput();
					POJO pojo6 = new POJO(string6, integer6);
					logger.info("Printing POJO Object : ");
					logger.info(pojo6.toString());
					break;

				case 7:
					logger.info("Creating a POJO object : ");
					POJO pojo7 = new POJO();
					logger.info("Enter a string : ");
					String string7 = InputUtil.getStringInput();
					pojo7.setString(string7);
					logger.info("Enter an integer : ");
					int integer7 = InputUtil.getIntInput();
					pojo7.setInteger(integer7);
					logger.info("Printing POJO Object : ");
					logger.info(pojo7.toString());
					break;

				case 8:
					ReflectionRunner.reflectionRunner();
					break;

				case 9:
					RainbowColors.listColors();
					break;

				case 10:
					SingletonClass singleton1 = SingletonClass.getInstance();
					logger.info("Object 1 : " + singleton1);
					SingletonClass singleton2 = SingletonClass.getInstance();
					logger.info("Object 2 : " + singleton2);
					@SuppressWarnings("rawtypes")
					Constructor singleton3 = SingletonClass.class.getDeclaredConstructor();
					singleton3.setAccessible(true);
					logger.info("Object 3 : " + singleton3.newInstance());
//					SingletonClass singleton4 = SingletonClass.class.newInstance();
//					logger.info(singleton4.toString());
					break;

				case 11:
					DateTimeRunner.dateTimeRunner();
					break;

				default:
					logger.warning("The choice is invalid");
					break;
				}
			} catch (CustomException e) {
				logger.severe(e.getMessage());
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
			logger.info("=================================================================");

		}
	}

	private static void question3(String path, String fileName) throws CustomException {
		logger.info("Reading properties from the file : ");
		Properties props;
		if (Utility.isNull(path)) {
			props = task.getPropertiesFromFile(fileName);
		} else {
			props = task.getPropertiesFromFile(path, fileName);
		}
		logger.info("Elements in the Properties : ");
		props.forEach((k, v) -> logger.info(k + " : " + v));
	}

	private static void question2(String path, String fileName) throws CustomException {
		logger.info("Storing properties to a file : ");
		Properties props = new Properties();
		logger.info("Enter the number of elements : ");
		int numberOfElements = InputUtil.getIntInput();
		for (int i = 1; i <= numberOfElements; i++) {
			logger.info("Enter key " + i + " : ");
			String key = InputUtil.getStringInput();
			logger.info("Enter value " + i + " : ");
			String value = InputUtil.getStringInput();
			props.setProperty(key, value);
		}
		String storedPath = null;
		if (Utility.isNull(path)) {
			storedPath = task.storeProperties(fileName, props);
		} else {
			storedPath = task.storeProperties(path, fileName, props);
		}

		logger.info("The properties have been stored successfully. Path : " + storedPath + ", File name : " + fileName);

	}

	private static void question1(String path, String fileName) throws CustomException {
		logger.info("Storing string values to a file : ");
		logger.info("Enter the number of Strings : ");
		int numberOfStrings1 = InputUtil.getIntInput();
		String[] stringsToStore1 = InputUtil.getMultipleLineStrings(numberOfStrings1);
		String storedPath = null;
		if (Utility.isNull(path)) {
			storedPath = task.createFileAndStoreData(fileName, stringsToStore1);
		} else {
			storedPath = task.createFileAndStoreData(path, fileName, stringsToStore1);
		}

		logger.info("The strings have been stored successfully. Path : " + storedPath + ", File name : " + fileName);

	}
}