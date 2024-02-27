package task;

import java.util.regex.PatternSyntaxException;

import constants.ExceptionMessage;
import utility.Utility;
import exceptions.CustomException;

public class StringMethods {

	private int getStringLengthWithEmptyCheck(String inputString) throws CustomException {
		int stringLength = Utility.findStringLength(inputString);
		if (stringLength == 0) {
			throw new CustomException(ExceptionMessage.EMPTY_STRING);
		}
		return stringLength;
	}

	public char[] convertStringtoCharArray(String inputString) throws CustomException {
		Utility.validateObject(inputString);
		return inputString.toCharArray();
	}

	public char findLastNthCharcter(String inputString, int nValue) throws CustomException {
		int stringLength = getStringLengthWithEmptyCheck(inputString);
		Utility.validateRange(stringLength - nValue, stringLength);
		return inputString.charAt(stringLength - nValue);
	}

	public int findOccuranceOfCharacter(String inputString, char characterToCount) throws CustomException {
		int count = 0;
		char[] charArray = convertStringtoCharArray(inputString);
		for (char i : charArray) {
			if (i == characterToCount) {
				count++;
			}
		}
		return count;
	}

	public int findGreatestPosition(String inputString, char inputCharacter) throws CustomException {
		getStringLengthWithEmptyCheck(inputString);
		return inputString.lastIndexOf(inputCharacter);
	}

	public String getLastNCharacters(String inputString, int nValue) throws CustomException {
		int stringLength = getStringLengthWithEmptyCheck(inputString);
		Utility.validateRange(stringLength - nValue, stringLength);
		return inputString.substring((stringLength - nValue), stringLength);
	}

	public String getFirstNCharacters(String inputString, int nValue) throws CustomException {
		int stringLength = getStringLengthWithEmptyCheck(inputString);
		Utility.validateRange(nValue, stringLength);
		return inputString.substring(0, nValue);
	}

	public String replaceStringBeginingWithPhrase(String inputString, String replacePhrase)
			throws CustomException {
		int replaceCharactersCount = getStringLengthWithEmptyCheck(replacePhrase);
		int stringLength = Utility.findStringLength(inputString);
		if (replaceCharactersCount > stringLength) {
			throw new CustomException(ExceptionMessage.PHRASE_EXCEEDS_STRING);
		}
		try {
			return inputString.replaceFirst(getFirstNCharacters(inputString, replaceCharactersCount), replacePhrase);
		} catch (PatternSyntaxException e) {
			throw new CustomException(ExceptionMessage.PATTERN_MISMATCH);
		}
	}

	public String replaceSubstringWithPhrase(String inputString, String subString, String replacePhrase)
			throws CustomException {
		int replaceCharactersCount = getStringLengthWithEmptyCheck(replacePhrase);
		int stringLength = Utility.findStringLength(inputString);
		if (replaceCharactersCount > stringLength) {
			throw new CustomException(ExceptionMessage.PHRASE_EXCEEDS_STRING);
		}
		try {
			return inputString.replaceAll(subString, replacePhrase);
		} catch (PatternSyntaxException e) {
			throw new CustomException(ExceptionMessage.PATTERN_MISMATCH);
		}
	}

	public boolean checkStartingPhrase(String inputString, String phrase) throws CustomException {
		if (Utility.findStringLength(phrase) > Utility.findStringLength(inputString)) {
			throw new CustomException(ExceptionMessage.PHRASE_EXCEEDS_STRING);
		}
		try {
			return inputString.startsWith(phrase);
		} catch (PatternSyntaxException e) {
			throw new CustomException(ExceptionMessage.PATTERN_MISMATCH);
		}
	}

	public boolean checkEndingPhrase(String inputString, String phrase) throws CustomException {
		if (Utility.findStringLength(phrase) > Utility.findStringLength(inputString)) {
			throw new CustomException(ExceptionMessage.PHRASE_EXCEEDS_STRING);
		}
		try {
			return inputString.endsWith(phrase);
		} catch (PatternSyntaxException e) {
			throw new CustomException(ExceptionMessage.PATTERN_MISMATCH);
		}
	}

	public String convertToUpperCase(String inputString) throws CustomException {
		Utility.validateObject(inputString);
		return inputString.toUpperCase();
	}

	public String convertToLowerCase(String inputString) throws CustomException {
		Utility.validateObject(inputString);
		return inputString.toLowerCase();
	}

	public String reverseString(String inputString) throws CustomException {
		int stringLength = getStringLengthWithEmptyCheck(inputString);
		int midValue = stringLength / 2;
		stringLength--; // to consider this variable as the last index
		char[] reverseArray = convertStringtoCharArray(inputString);
		for (int i = 0; i < midValue; i++) {
			char tempChar = reverseArray[i];
			reverseArray[i] = reverseArray[stringLength - i];
			reverseArray[stringLength - i] = tempChar;
		}
		String reversedString = new String(reverseArray);
		return reversedString;
	}

	public String[] splitStringWithPhrase(String inputString, String phrase) throws CustomException {
		int stringLength = getStringLengthWithEmptyCheck(inputString);
		int phraseLength = getStringLengthWithEmptyCheck(phrase);
		if (phraseLength > stringLength) {
			throw new CustomException(ExceptionMessage.PHRASE_EXCEEDS_STRING);
		}
		return inputString.split(phrase);
	}

	public String joinStringWithPhrase(String[] strings, String phrase) throws CustomException {
		Utility.validateObject(phrase);
		Utility.validateObject(strings);
		if (strings.length == 0) {
			throw new CustomException(ExceptionMessage.EMPTY_ARRAY);
		}
		for (String eachString : strings) {
			Utility.validateObject(eachString);
		}
		return String.join(phrase, strings);
	}

	public boolean checkEquality(String firstString, String secondString, boolean ignoreCase)
			throws CustomException {
		Utility.validateObject(firstString);
		Utility.validateObject(secondString);
		if (ignoreCase) {
			return firstString.equalsIgnoreCase(secondString);
		} else {
			return firstString.equals(secondString);
		}
	}

	public String removeSpacesBeforeAndAfter(String inputString) throws CustomException {
		Utility.validateObject(inputString);
		return inputString.trim();
	}
}