package constants;

public enum ExceptionMessage {
	DEFAULT("The input that you have specified is invalid"), NULL_OBJECT("A null value was obtained."),
	EMPTY_STRING("The string that was obtained is empty. This method cannot operate on empty strings"),
	NO_CHARACTER("A character input is required, but no character was obtained."),
	OUT_OF_INDEX("The position of execution is outside the range of the string length."),
	PHRASE_EXCEEDS_STRING("The length of the given string is less than the phrase to be replaced."),
	RANGE_EXCEEDS_STRING("The length of the given string is less than the desired length."),
	PATTERN_MISMATCH("The phrase that is used is not a valid one."),
	EMPTY_ARRAY("No strings were obtained. The array of strings is empty"),
	INTEGER_NOT_FOUND("An integer value is expected, but an integer was not given."),
	LONG_NOT_FOUND("A long value is expected, but a long was not given."),
	DECIMAL_NOT_FOUND("A decimal value is expected, but an decimal number was not given."),
	LACK_OF_STRINGS("The desired number of strings were not obtained"),
	NEGATIVE_INTEGER("A positive integer is required."), EMPTY_ENTITY("The entity is empty."),
	FILE_EXCEPTION("A File exception occured."), CURRENT_DIRECTORY_NOT_FOUND("Failed to obtain current directory"),
	NO_HTML_TAGS_FOUND("No HTML tags were found."),
	CONNECTION_NOT_ESTABLISHED("The server connection is not established yet. Please connect and try again."),
	INVALID_MOBILE_NUMBER ("The mobile number is invalid. It should start with 7,8 or 9 and contain 10 digits."),
	INVALID_EMAIL_ADDRESS ("The email address is invalid. Please enter correct email address."),
	REQUIRES_NATURAL_NUMBER ("The number cannot be less than 1");

	private String message;

	private ExceptionMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}

}
