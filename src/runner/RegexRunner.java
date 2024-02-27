package runner;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import task.RegexTask;
import utility.InputUtil;
import utility.LoggerUtility;

public class RegexRunner {

	public static Logger logger = null;
	static {
		try {
			logger = LoggerUtility.getNewLogger(RegexRunner.class.getName());
			logger.setLevel(Level.INFO);
			LoggerUtility.addFileHandler(logger, logger.getName(), LoggerUtility.DEFAULT_FILE_PATH);
		} catch (Exception e) {
			LoggerUtility.DEFAULT_LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public static void main(String... args) {
		RegexTask task = new RegexTask();
		boolean isProgramActive = true;
		int numberOfQuestions = 20;

		logger.info("Regular Expressions Runner Program");
		while (isProgramActive) {

			logger.info("\n1 - Accept a string and validate it for a mobile number"
					+ "\n2 - Accept a string and it should only contain alphanumeric characters"
					+ "\n3 - Accept two strings. Check if it starts, ends, contains and is an extact match"
					+ "\n4 - Get a list of Strings. Check for matcher with case insensitive"
					+ "\n5 - Get an email address and validate it"
					+ "\n6 - Get a list of strings and check if all strings are of a given range of length"
					+ "\n7 - Get two lists. Check if all the strings in list 2 matches ones in list 1"
					+ "\n8 - Get a raw HTML code. Print only the tags" + "\n9 - Enter a password. Validate it"
					+ "\nTo exit, enter 0" + "\n\n-----------------------------------------------------------------");

			int choice = -1;
			do {
				try {
					logger.info("Enter your choice (0 to " + numberOfQuestions + "): ");
					choice = InputUtil.getIntInput();
				} catch (Exception e) {
					logger.info(e.getMessage());
				}
			} while (choice < 0 || choice > numberOfQuestions);
			logger.info("Choice " + choice + " selected!");

			try {
				switch (choice) {
				case 0:
					isProgramActive = false;
					logger.info("Thank you for using the program");
					InputUtil.closeScanner();
					break;

				case 1:
					logger.info("Enter a mobile number : ");
					String mobileNumber = InputUtil.getStringInput();
					if (task.isValidMobileNumber(mobileNumber)) {
						logger.info("The entered mobile number is valid.");
					} else {
						logger.info("Invalid mobile number");
					}
					break;

				case 2:
					logger.info("Enter a string : ");
					String alphaNumericString = InputUtil.getStringInput();
					if (task.isOnlyAlphaNumeric(alphaNumericString)) {
						logger.info("The given string is an alphanumeric string");
					} else {
						logger.info("The string is not a pure alphanumeric string");
					}
					break;

				case 3:
					logger.info("Enter a string to check : ");
					String originalString = InputUtil.getStringInput();
					logger.info("Enter a matcher string : ");
					String matcherString = InputUtil.getStringInput();
					logger.info("Starts with Matching string : "
							+ task.isGivenStartsWithMatching(originalString, matcherString)
							+ "\nEnds with Matching string : "
							+ task.isGivenEndsWithMatching(originalString, matcherString)
							+ "\nContains Matcher string : "
							+ task.isGivenContainsMatching(originalString, matcherString)
							+ "\nExactly same as Matcher string : " + task.isExactMatch(originalString, matcherString));
					break;

				case 4:
					logger.info("Enter a list of String : ");
					List<String> listOfStrings4 = new ArrayList<>();
					ArrayListRunner.addMultipleStringsToList(listOfStrings4);
					logger.info("Enter a matcher string : ");
					String matcherString4 = InputUtil.getStringInput();
					logger.info("List Contains case insensitive match for the string : "
							+ task.isInListOfStringCaseInsensitives(listOfStrings4, matcherString4));
					break;

				case 5:
					logger.info("Enter an Email : ");
					String email = InputUtil.getStringInput();
					if (task.isValidEmail(email)) {
						logger.info("The given email address is valid");
					} else {
						logger.info("The given email address is invalid");
					}
					break;

				case 6:
					logger.info("List of Strings : ");
					List<String> listOfStrings6 = new ArrayList<>();
					ArrayListRunner.addMultipleStringsToList(listOfStrings6);
					logger.info("Enter range start to check string length : ");
					int startRange = InputUtil.getIntInput();
					logger.info("Enter range end to check string length : ");
					int endRange = InputUtil.getIntInput();
					logger.info("The list contains strings of the given range of length : "
							+ task.hasListContainsStringsOfLength(listOfStrings6, startRange, endRange));
					break;

				case 7:
					logger.info("List 1 : ");
					List<String> list1 = new ArrayList<>();
					ArrayListRunner.addMultipleStringsToList(list1);
					logger.info("List 2 : Matcher Strings");
					List<String> list2 = new ArrayList<>();
					ArrayListRunner.addMultipleStringsToList(list2);
					logger.info("Return Map : " + task.getMatchingMap(list1, list2));
					break;
				case 8:
					logger.info("Enter a HTML Code Snippet ");
					String htmlSnippet = InputUtil.getStringInput();
					List<String> snippets = task.getHTMLSnippets(htmlSnippet);
					snippets.forEach((s) -> logger.info(s));
					break;

				case 9:
					logger.info("Enter your password : ");
					String password = InputUtil.getStringInput();
					if (task.isPasswordStrong(password)) {
						logger.info("Your password is strong");
					} else {
						logger.info("Your password is weak. It must contain the following: " + "\nStart with a letter"
								+ "\nContain mixed cases" + "\nContain at least one number"
								+ "\nContain at least one special character"
								+ "\nMust be within 8 to 20 characters long");
					}
					break;

				default:
					logger.info("The choice is invalid");
					break;
				}

			} catch (Exception e) {
				logger.info(e.getMessage());
			}
			logger.info("=================================================================");

		}
	}
}