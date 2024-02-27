package task;

import utility.Utility;
import exceptions.CustomException;

public class StringBuilderTask {

	public StringBuilder getStringBuilder() {
		return new StringBuilder();
	}

	public StringBuilder getStringBuilder(String createString) throws CustomException {
		Utility.validateObject(createString);
		return new StringBuilder(createString);
	}

	public int getLength(StringBuilder input) throws CustomException {
		Utility.validateObject(input);
		return input.length();
	}

	public void insertString(StringBuilder input, String stringToInsert, int index) throws CustomException {
		Utility.validateObject(stringToInsert);
		Utility.validateRange(index, getLength(input));
		input.insert(index, stringToInsert);
	}

	public void addString(StringBuilder input, String stringToAppend) throws CustomException {
		Utility.validateObject(input);
		Utility.validateObject(stringToAppend);
		input.append(stringToAppend);
	}

	public int getIndexOfString(StringBuilder input, String phrase, int fromIndex) throws CustomException {
		Utility.validateRange(fromIndex, getLength(input));
		Utility.validateObject(phrase);
		return input.indexOf(phrase, fromIndex);
	}

	public int getIndexOfString(StringBuilder input, String phrase) throws CustomException {
		return getIndexOfString(input, phrase, 0);
	}

	public int getLastIndexOfString(StringBuilder input, String phrase) throws CustomException {
		Utility.validateObject(input);
		Utility.validateObject(phrase);
		return input.lastIndexOf(phrase);
	}

	public void deleteString(StringBuilder input, int start, int end) throws CustomException {
		Utility.validateRange(end, getLength(input));
		Utility.validateRange(start, end);
		input.delete(start, end);
	}

	public void replaceCharacters(StringBuilder input, String replacePhrase, int fromIndex, int toIndex)
			throws CustomException {
		Utility.validateRange(toIndex, getLength(input));
		Utility.validateRange(fromIndex, toIndex);
		Utility.validateObject(replacePhrase);
		input.replace(fromIndex, toIndex, replacePhrase);
	}

	public void replaceCharacters(StringBuilder input, String originalPhrase, String replacePhrase, int fromIndex)
			throws CustomException {
		int startIndex = getIndexOfString(input, originalPhrase, fromIndex);
		Utility.validateObject(replacePhrase);
		if (startIndex < 0) {
			return;
		}
		int endIndex = Utility.findStringLength(originalPhrase) + startIndex;
		replaceCharacters(input, replacePhrase, startIndex, endIndex);
		replaceCharacters(input, originalPhrase, replacePhrase, endIndex);
	}

	public void reverseString(StringBuilder input) throws CustomException {
		Utility.validateObject(input);
		input.reverse();
	}

	public void deleteCharacterAtIndex(StringBuilder input, int index) throws CustomException {
		int stringLength = getLength(input);
		Utility.validateRange(index, stringLength);
		input.deleteCharAt(index);
	}
}