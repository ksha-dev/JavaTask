package utility;

import java.util.Collection;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import constants.ExceptionMessage;
import exceptions.CustomException;
import task.RegexTask;

public class Utility {
	public static void validateObject(Object input, String message) throws CustomException {
		if (isNull(message)) {
			throw new CustomException(ExceptionMessage.NULL_OBJECT);
		}
		if (isNull(input)) {
			throw new CustomException(message);
		}
	}

	public static void validateObject(Object input) throws CustomException {
		validateObject(input, ExceptionMessage.NULL_OBJECT.toString());
	}

	public static void validateObject(Object input, ExceptionMessage message) throws CustomException {
		validateObject(input, message.toString());
	}

	public static void validateRange(long startIndex, long endIndex) throws CustomException {
		if (startIndex < 0) {
			throw new CustomException(ExceptionMessage.OUT_OF_INDEX);
		} else if (startIndex > endIndex) {
			throw new CustomException(ExceptionMessage.RANGE_EXCEEDS_STRING);
		}
	}

	public static void validateRange(long startIndex, long endIndex, String message) throws CustomException {
		if (startIndex < 0 || startIndex > endIndex) {
			throw new CustomException(message);
		}
	}

	public static int findStringLength(String inputString) throws CustomException {
		validateObject(inputString);
		return inputString.length();
	}

	public static <T> void validateCollection(Collection<T> collection) throws CustomException {
		validateObject(collection);
		for (T element : collection) {
			Utility.validateObject(element, "Encountered null value inside collection");
		}
	}

	public static boolean isNull(Object object) {
		return object == null;
	}

	public static void printThreadDetails(Thread thread, Logger logger) throws CustomException {
		validateObject(thread);
		validateObject(logger);
		logger.info("Name of Thread : " + thread.getName() + ", Priority : " + thread.getPriority() + ", State : "
				+ thread.getState());
	}

	public static void validateMobileNumber(long mobileNumber) throws CustomException {
		if (!Pattern.compile(RegexTask.MOBILE_NUMBER_VALIDATION_REGEX).matcher(mobileNumber + "").find()) {
			throw new CustomException(ExceptionMessage.INVALID_MOBILE_NUMBER);
		}
	}

	public static void validateEmailAddress(String emailAddress) throws CustomException {
		Utility.validateObject(emailAddress);
		if (!Pattern.compile(RegexTask.EMAIL_VALIDATION_REGEX).matcher(emailAddress).find()) {
			throw new CustomException(ExceptionMessage.INVALID_EMAIL_ADDRESS);
		}
	}
}