package runner;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import utility.InputUtil;
import utility.LoggerUtility;
import utility.Utility;
import task.StringMethods;
import exceptions.CustomException;

public class StringTaskRunner {

	private static Logger logger = null;
	static {
		try {
			logger = LoggerUtility.getNewLogger(StringTaskRunner.class.getName());
			logger.setLevel(Level.INFO);
			LoggerUtility.addFileHandler(logger, logger.getName(), LoggerUtility.DEFAULT_LOGGER_PATH);
		} catch (Exception e) {
			LoggerUtility.DEFAULT_LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public static void main(String[] args) {

		StringMethods stringMethod = new StringMethods();
		logger.info("String Task Runner Program");
		logger.info("The following are the list of operations that can be done in this program.\n");

		boolean isProgramActive = true;
		while (isProgramActive) {
			logger.info("1 : Find the length of a string" + "\n2 : Convert string to character array"
					+ "\n3 : Find the penultimate character of a string"
					+ "\n4 : Find the number of occurances of a character in a string"
					+ "\n5 : Find the greatest position of the given character"
					+ "\n6 : Get the last 5 characters of a string" + "\n7 : Get the first 3 characters of a string"
					+ "\n8 : Replace first 3 characters of a string with \"XYZ\""
					+ "\n9 : Check if string starts with \"ent\"" + "\n10 : Check if string ends with \"le\""
					+ "\n11 : Convert String to Uppercase" + "\n12 : Convert String to Lowercase"
					+ "\n13 : Reverse the String" + "\n14 : Accept Multiple Strings in a single line"
					+ "\n15 : Accept Multiple Strings in a single line and concat each without spaces"
					+ "\n16 : Accept Multiple Strings in a single line and convert it into an array of strings"
					+ "\n17 : Accept Multiple Strings and convert it into a single string with hypens"
					+ "\n18 : Accept Two Strings and Check for Equality with Case Sensitivity"
					+ "\n19 : Accept Two Strings and Check for Equality without Case sensitivity"
					+ "\n20 : Remove unnecessary spaces at start and end of a string\n" + "\nTo exit, enter 0"
					+ "\n------------------------------------------------------------");
			int choice = -1;
			do {
				try {
					logger.info("Enter your choice (must be an integer within 0 to 20) : ");
					choice = InputUtil.getIntInput();
					if (choice > 20 || choice < 0) {
						throw new CustomException("Out of range");
					}
				} catch (CustomException e) {
					logger.info(e.getMessage());
				}
			} while (choice < 0 || choice > 20);
			try {
				switch (choice) {
				case 0:
					logger.info("Thank you for using the program.");
					isProgramActive = false;
					break;
				case 1:
					logger.info("Enter a string : ");
					logger.info("Length of String is : " + (Utility.findStringLength(InputUtil.getStringInput())));
					break;

				case 2:
					logger.info("Enter a string : ");
					char[] convertedArray = stringMethod.convertStringtoCharArray(InputUtil.getStringInput());
					logger.info(Arrays.toString(convertedArray));
					break;

				case 3:
					logger.info("Enter a string : ");
					logger.info("Penultimate Character : "
							+ stringMethod.findLastNthCharcter(InputUtil.getStringInput(), 1));
					break;

				case 4:
					logger.info("Enter the string : ");
					String inputString4 = InputUtil.getStringInput();
					logger.info("Enter character to find occurance : ");
					logger.info("Count : "
							+ stringMethod.findOccuranceOfCharacter(inputString4, InputUtil.getCharInput()));
					break;

				case 5:
					logger.info("Enter a string : ");
					String inputString5 = InputUtil.getStringInput();
					logger.info("Enter a character to find the greatest position : ");
					logger.info("Index : "
							+ (stringMethod.findGreatestPosition(inputString5, InputUtil.getCharInput())));
					break;

				case 6:
					logger.info("Enter a string with a minimum of 5 characters : ");
					logger.info(stringMethod.getLastNCharacters(InputUtil.getStringInput(), 5));
					// " ", 10));
					break;

				case 7:
					logger.info("Enter a string with a minimum of 3 characters : ");
					logger.info(stringMethod.getFirstNCharacters(InputUtil.getStringInput(), 3));
					break;

				case 8:
					logger.info("Enter a string with a minimum of 3 characters : ");
					logger.info(stringMethod.replaceStringBeginingWithPhrase(InputUtil.getStringInput(), "XYZ"));
					break;

				case 9:
					logger.info("Enter a string with a minimum of 3 characters : ");
					logger.info("Answer : " + stringMethod.checkStartingPhrase(InputUtil.getStringInput(), "ent"));
					break;

				case 10:
					logger.info("Enter a string with a minimum of 2 characters : ");
					logger.info("Answer : " + stringMethod.checkEndingPhrase(InputUtil.getStringInput(), "le"));
					break;

				case 11:
					logger.info("Enter a string : ");
					logger.info(stringMethod.convertToUpperCase(InputUtil.getStringInput()));
					break;

				case 12:
					logger.info("Enter a string : ");
					logger.info(stringMethod.convertToLowerCase(InputUtil.getStringInput()));
					break;

				case 13:
					logger.info("Enter a string : ");
					logger.info(stringMethod.reverseString(InputUtil.getStringInput()));
					break;

				case 14:
					logger.info("Enter a string : ");
					String[] strings14 = stringMethod.splitStringWithPhrase(InputUtil.getStringInput(), " ");
					for (String i : strings14) {
						logger.info(i);
					}
					break;

				case 15:
					logger.info("Enter a string : ");
					logger.info(stringMethod.replaceSubstringWithPhrase(InputUtil.getStringInput(), " ", ""));
					break;

				case 16:
					logger.info("Enter a string : ");
					String[] strings16 = stringMethod.splitStringWithPhrase(InputUtil.getStringInput(), " ");
					logger.info(Arrays.toString(strings16));
					break;

				case 17:
					logger.info("Enter the number of strings : ");
					int numberOfStrings = InputUtil.getIntInput();
					logger.info(stringMethod.joinStringWithPhrase(InputUtil.getMultipleLineStrings(numberOfStrings),
							"-"));
					break;

				case 18:
					logger.info("Enter a string : ");
					String firstInputString18 = InputUtil.getStringInput();
					logger.info("Enter another string : ");
					String secondInputString18 = InputUtil.getStringInput();
					logger.info(
							"Answer : " + stringMethod.checkEquality(firstInputString18, secondInputString18, false));
					break;

				case 19:
					logger.info("Enter a string : ");
					String firstInputString19 = InputUtil.getStringInput();
					logger.info("Enter another string : ");
					String secondInputString19 = InputUtil.getStringInput();
					logger.info(
							"Answer : " + stringMethod.checkEquality(firstInputString19, secondInputString19, true));
					break;

				case 20:
					logger.info("Enter a string : ");
					logger.info(stringMethod.removeSpacesBeforeAndAfter(InputUtil.getStringInput()));
					break;

				default:
					logger.info("The entered choice is not valid");
				}
			} catch (CustomException e) {
				logger.severe(e.getMessage());
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
			logger.info("------------------------------------------------------------");
		}
	}
}