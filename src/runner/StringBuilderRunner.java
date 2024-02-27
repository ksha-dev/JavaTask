package runner;

import task.StringBuilderTask;
import utility.InputUtil;
import utility.LoggerUtility;

import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.CustomException;

public class StringBuilderRunner {

	private static Logger logger = null;
	static {
		try {
			logger = LoggerUtility.getNewLogger(StringBuilderRunner.class.getName());
			logger.setLevel(Level.INFO);
			LoggerUtility.addFileHandler(logger, logger.getName(), LoggerUtility.DEFAULT_LOGGER_PATH);
		} catch (Exception e) {
			LoggerUtility.DEFAULT_LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private static StringBuilderTask taskMethods = new StringBuilderTask();

	public static void printStringAndLength(StringBuilder input) throws CustomException {
		logger.info("Final String : " + input);
		logger.info("Length of final string : " + taskMethods.getLength(input));
	}

	public static void printLength(StringBuilder input) throws CustomException {
		logger.info("Initial Length of string : " + taskMethods.getLength(input));
	}

	public static void multipleStringInput(StringBuilder stringBuilder, String delimiter) throws CustomException {
		logger.info("Enter number of strings needed : ");
		int numberOfStrings = InputUtil.getIntInput();
		if (numberOfStrings < 1) {
			throw new CustomException("Enter values from 1 and above");
		}
		logger.info("Enter " + numberOfStrings + " strings : ");
		String[] multipleStrings = InputUtil.getMultipleStrings(numberOfStrings);
		taskMethods.addString(stringBuilder, multipleStrings[0]);
		for (int i = 1; i < numberOfStrings; i++) {
			taskMethods.addString(stringBuilder, delimiter);
			taskMethods.addString(stringBuilder, multipleStrings[i]);
		}
	}

	public static void main(String[] args) {

		boolean isProgramActive = true;

		logger.info("String Builder Task Program - Task 4");
		logger.info("The following are the list of operations :\n");

		while (isProgramActive) {
			logger.info(
					"1 : Create a StringBuilder with no string, get its length. Add a string to it and get its length"
							+ "\n2 : Create a StringBuilder with a string, get its length. Add 4 string to it separated by \'#\' and get its length"
							+ "\n3 : Create a StringBuilder with two strings, get its length. Add a string in between it and get its length"
							+ "\n4 : Create a StringBuilder with two strings, get its length. Remove first string and get its length"
							+ "\n5 : Create a StringBuilder with three strings, get its length. Replace spaces with \'-\' and get its length"
							+ "\n6 : Create a StringBuilder with three strings, get its length. Reverse it and get its length"
							+ "\n7 : Create a StringBuilder with a string of minimum 10 charcters. Delete characters from 6-8, get its length and print it"
							+ "\n8 : Create a StringBuilder with a string of minimum 10 charcters. Replace 6-8th characters with XYZ, get its length and print it"
							+ "\n9 : Create a StringBuilder with three strings, replace the space by # and get its length. Find the first index of #"
							+ "\n10 : Create a StringBuilder with three strings, replace the space by # and get its length. Find the last index of #"
							+ "\n\nTo exit, enter 0" + "\n---------------------------------------------");
			int choice = -1;
			do {
				try {
					logger.info("Enter your choice (must be an integer within 0 to 10) : ");
					choice = InputUtil.getIntInput();
					if (choice > 10 || choice < 0) {
						logger.warning("Invalid Choice Entered");
					}
				} catch (CustomException e) {
					logger.severe(e.getMessage());
				}
			} while (choice < 0 || choice > 10);

			try {
				switch (choice) {
				case 0:
					isProgramActive = false;
					logger.info("Thank you for using the program");
					break;

				case 1:
					StringBuilder stringBuilder1 = taskMethods.getStringBuilder();
					printLength(stringBuilder1);
					logger.info("Enter a string : ");
					taskMethods.addString(stringBuilder1, InputUtil.getStringInput());
					printStringAndLength(stringBuilder1);
					break;

				case 2:
					logger.info("Enter a string : ");
					StringBuilder stringBuilder2 = taskMethods.getStringBuilder(InputUtil.getStringInput());
					taskMethods.addString(stringBuilder2, "#");
					multipleStringInput(stringBuilder2, "#");
					printStringAndLength(stringBuilder2);
					break;

				case 3:
					StringBuilder stringBuilder3 = taskMethods.getStringBuilder();
					multipleStringInput(stringBuilder3, " ");
					printLength(stringBuilder3);
					logger.info("Enter another string : ");
					String inputString3 = InputUtil.getStringInput();
					taskMethods.insertString(stringBuilder3, " " + inputString3,
							taskMethods.getIndexOfString(stringBuilder3, " "));
					printStringAndLength(stringBuilder3);
					break;

				case 4:
					StringBuilder stringBuilder4 = taskMethods.getStringBuilder();
					multipleStringInput(stringBuilder4, " ");
					printLength(stringBuilder4);
					taskMethods.deleteString(stringBuilder4, 0, taskMethods.getIndexOfString(stringBuilder4, " ") + 1);
					printStringAndLength(stringBuilder4);
					break;

				case 5:
					StringBuilder stringBuilder5 = taskMethods.getStringBuilder();
					multipleStringInput(stringBuilder5, " ");
					printLength(stringBuilder5);
					taskMethods.replaceCharacters(stringBuilder5, " ", "---", 0);
					printStringAndLength(stringBuilder5);
					break;

				case 6:
					StringBuilder stringBuilder6 = taskMethods.getStringBuilder();
					multipleStringInput(stringBuilder6, " ");
					printLength(stringBuilder6);
					taskMethods.reverseString(stringBuilder6);
					printStringAndLength(stringBuilder6);
					break;

				case 7:
					logger.info("Enter a string with a minimum of 10 characters : ");
					StringBuilder stringBuilder7 = taskMethods.getStringBuilder(InputUtil.getStringInput(10));
					printLength(stringBuilder7);
					taskMethods.deleteString(stringBuilder7, 6, 9);
					printStringAndLength(stringBuilder7);
					break;

				case 8:
					logger.info("Enter a string with a minimum of 10 characters : ");
					StringBuilder stringBuilder8 = taskMethods.getStringBuilder(InputUtil.getStringInput(10));
					printLength(stringBuilder8);
					taskMethods.replaceCharacters(stringBuilder8, "XYZ", 6, 9);
					printStringAndLength(stringBuilder8);
					break;

				case 9:
					StringBuilder stringBuilder9 = taskMethods.getStringBuilder();
					multipleStringInput(stringBuilder9, "#");
					printStringAndLength(stringBuilder9);
					int index9 = taskMethods.getIndexOfString(stringBuilder9, "#");
					logger.info("Index of First # : " + index9);
					break;

				case 10:
					StringBuilder stringBuilder10 = taskMethods.getStringBuilder();
					multipleStringInput(stringBuilder10, "#	");
					printStringAndLength(stringBuilder10);
					int index10 = taskMethods.getLastIndexOfString(stringBuilder10, "#");
					logger.info("Index of Last # : " + index10);
					break;

				default:
					logger.info("The entered choice is incorrect");
				}

			} catch (CustomException e) {
				logger.severe(e.getMessage());
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
			logger.info("---------------------------------------------\n");
		}
	}
}