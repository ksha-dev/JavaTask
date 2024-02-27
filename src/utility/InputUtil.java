
package utility;

import java.util.Arrays;
import java.util.Scanner;

import constants.ExceptionMessage;

import java.util.InputMismatchException;
import exceptions.CustomException;

public class InputUtil {
	private static Scanner scanner;

	static {
		scanner = new Scanner(System.in);
	}

	public static int getIntInput() throws CustomException {
		try {
			int returnInt = scanner.nextInt();
			return returnInt;
		} catch (InputMismatchException e) {
			throw new CustomException(ExceptionMessage.INTEGER_NOT_FOUND);
		} finally {
			getStringInput();
		}
	}

	public static int[] getMultipleIntInput(int numberOfInt) throws CustomException {
		if (numberOfInt <= 0) {
			throw new CustomException(ExceptionMessage.NEGATIVE_INTEGER);
		}
		int[] returnInt = new int[numberOfInt];
		for (int i = 0; i < numberOfInt; i++) {
			boolean isIntObtained = false;
			while (!isIntObtained) {
				System.out.print((i + 1) + " -> ");
				try {
					returnInt[i] = getIntInput();
					isIntObtained = true;
				} catch (CustomException e) {
					System.out.println(e.getMessage() + " Please try again");
				}
			}
		}
		return returnInt;
	}

	public static float getFloatInput() throws CustomException {
		try {
			float returnInt = scanner.nextFloat();
			return returnInt;
		} catch (InputMismatchException e) {
			throw new CustomException(ExceptionMessage.DECIMAL_NOT_FOUND);
		} finally {
			getStringInput();
		}
	}

	public static float[] getMultipleFloatInput(int numberOfInt) throws CustomException {
		if (numberOfInt <= 0) {
			throw new CustomException(ExceptionMessage.NEGATIVE_INTEGER);
		}
		float[] returnFloat = new float[numberOfInt];
		for (int i = 0; i < numberOfInt; i++) {
			boolean isFloatObtained = false;
			while (!isFloatObtained) {
				System.out.print((i + 1) + " -> ");
				try {
					returnFloat[i] = getFloatInput();
					isFloatObtained = true;
				} catch (CustomException e) {
					System.out.println(e.getMessage() + " Please try again");
				}
			}
		}
		return returnFloat;
	}

	public static long getLongInput() throws CustomException {
		try {
			long returnLong = scanner.nextLong();
			return returnLong;
		} catch (InputMismatchException e) {
			throw new CustomException(ExceptionMessage.LONG_NOT_FOUND);
		} finally {
			getStringInput();
		}
	}

	public static long[] getMultipleLongInput(int numberOfLong) throws CustomException {
		if (numberOfLong <= 0) {
			throw new CustomException(ExceptionMessage.NEGATIVE_INTEGER);
		}
		long[] returnLong = new long[numberOfLong];
		for (int i = 0; i < numberOfLong; i++) {
			boolean isLongObtained = false;
			while (!isLongObtained) {
				System.out.print((i + 1) + " -> ");
				try {
					returnLong[i] = getLongInput();
					isLongObtained = true;
				} catch (CustomException e) {
					System.out.println(e.getMessage() + " Please try again");
				}
			}
		}
		return returnLong;
	}

	public static String getStringInput() {
		return scanner.nextLine();
	}

	public static String getStringInput(int minimumCharacters) throws CustomException {
		String outputString = getStringInput();
		if (outputString.length() < minimumCharacters) {
			throw new CustomException(ExceptionMessage.RANGE_EXCEEDS_STRING);
		}
		return outputString;
	}

	public static char getCharInput() throws CustomException {
		String tempString = scanner.nextLine();
		if (tempString.isEmpty()) {
			throw new CustomException(ExceptionMessage.NO_CHARACTER);
		}
		return tempString.charAt(0);
	}

	public static String[] getMultipleStrings() {
		return getStringInput().split(" ");
	}

	public static String[] getMultipleStrings(int numberOfStrings) throws CustomException {
		String[] obtainStrings = getMultipleStrings();
		if (obtainStrings.length >= numberOfStrings) {
			return Arrays.copyOf(obtainStrings, numberOfStrings);
		} else if (numberOfStrings < 0) {
			throw new CustomException(ExceptionMessage.NEGATIVE_INTEGER);
		} else {
			throw new CustomException(ExceptionMessage.LACK_OF_STRINGS);
		}
	}

	public static String[] getMultipleLineStrings(int numberOfStrings) {
		String[] returnStrings = new String[numberOfStrings];
		for (int i = 0; i < numberOfStrings; i++) {
			System.out.print((i + 1) + " -> ");
			returnStrings[i] = getStringInput();
		}
		return returnStrings;
	}

	public static void closeScanner() {
		try {
			scanner.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}