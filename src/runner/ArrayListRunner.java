package runner;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import cars.Car;
import cars.SCross;
import cars.Swift;
import cars.XUV;
import exceptions.CustomException;
import utility.LoggerUtility;
import utility.InputUtil;
import utility.Utility;

@SuppressWarnings("unchecked")
public class ArrayListRunner {
	// creating log instance
	private static Logger logger = null;
	static {
		try {
			logger = LoggerUtility.getNewLogger(ArrayListRunner.class.getName());
			logger.setLevel(Level.INFO);
			LoggerUtility.addFileHandler(logger, logger.getName(), LoggerUtility.DEFAULT_LOGGER_PATH);
		} catch (Exception e) {
			LoggerUtility.DEFAULT_LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	//creating object using reflection and getting methods map
	private static Object task = null;
	private static Map<String, Method> methods = new HashMap<String, Method>();
	static {
		try {
			Class<?> listTaskClass = Class.forName("task.ListTask");
			task = listTaskClass.getDeclaredConstructor().newInstance();

			Method[] availableMethods = listTaskClass.getMethods();
			for (Method method : availableMethods) {
				methods.put(method.getName() + method.getParameterCount(), method);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public static <T> void printArrayListAndLength(List<T> list) throws Exception {
		logger.info("ArrayList : " + list);
		logger.info("Length of ArrayList : " + methods.get("getSizeOfList1").invoke(task, list));
	}

	public static void addMultipleStringsToList(List<String> list) throws Exception {
		logger.info("Enter the number of Strings : ");
		int numberOfStrings = InputUtil.getIntInput();
		String[] strings = InputUtil.getMultipleLineStrings(numberOfStrings);
		methods.get("addElementsToList2").invoke(task, list, strings);
	}

	public static void main(String[] args) {
		
		if(Utility.isNull(task)) {
			logger.severe("The class was failed to obtain.");
			System.exit(-1);
		}
		
		boolean isProgramActive = true;
		int numberOfQuestions = 18;

		logger.info("ArrayList Task Runner Programm");
		logger.info("The following are the list of operations : ");
		while (isProgramActive) {
			logger.info("1 - Create an ArrayList and print its size"
					+ "\n2 - Create an ArrayList and add 5 strings to it"
					+ "\n3 - Create an ArrayList and add 5 integers to it"
					+ "\n4 - Create an ArrayList and add custom objects to it"
					+ "\n5 - Create an ArrayList and add 2 strings, 3 integers and custom objects to it"
					+ "\n6 - Create an ArrayList with 5 strings. Get the index of a string in it"
					+ "\n7 - Create an ArrayList with 5 strings. Use iterator to print the strings"
					+ "\n8 - Create an ArrayList and add strings to it. Get the string at an index"
					+ "\n9 - Create an ArrayList with duplicate strings. Find the first and last index of a duplicate string"
					+ "\n10 - Create an ArrayList with strings. Add a string to the 2nd position"
					+ "\n11 - Create an ArrayList with 10 strings. Get another arraylist from index 3 to 8"
					+ "\n12 - Create two ArrayLists. Merge both in a new one without loops"
					+ "\n13 - Create two ArrayLists. Merge both such that the second list elements should be first"
					+ "\n14 - Create an ArrayList with decimals. Remove the fourth element from it"
					+ "\n15 - Create two Arralists. Remove the elements of 1 that are present in 2"
					+ "\n16 - Create two Arraylists. Remove the elements of 1 that are absent in 2"
					+ "\n17 - Create an ArrayList with long type. Remove all the elements from it"
					+ "\n18 - Create an ArrayList with strings. Check if a string is present in it"
					+ "\nTo exit, enter 0" + "\n\n-----------------------------------------------------------------");

			int choice = -1;
			do {
				try {
					logger.info("Enter your choice (0 to " + numberOfQuestions + "): ");
					choice = InputUtil.getIntInput();
					if (choice < 0 || choice > numberOfQuestions) {
						logger.warning("Invalid Choice Entered");
					}
				} catch (Exception e) {
					logger.severe(e.getMessage());
				}
			} while (choice < 0 || choice > numberOfQuestions);

			try {
				switch (choice) {
				case 0:
					isProgramActive = false;
					logger.info("Thank you for using the program");
					InputUtil.closeScanner();
					break;

				case 1:
					List<Integer> list1 = (List<Integer>) methods.get("createList0").invoke(task);
					logger.info("Length of ArrayList : " + methods.get("getSizeOfList1").invoke(task, list1));
					break;

				case 2:
					List<String> list2 = (List<String>) methods.get("createList0").invoke(task);
					addMultipleStringsToList(list2);
					printArrayListAndLength(list2);
					break;

				case 3:
					List<Integer> list3 = (List<Integer>) methods.get("createList0").invoke(task);
					logger.info("Enter the number of Integers : ");
					int numberOfInteger3 = InputUtil.getIntInput();
					int[] integers3 = InputUtil.getMultipleIntInput(numberOfInteger3);
					for (int i : integers3) {
						methods.get("addElementToList2").invoke(task, list3, i);
					}
					printArrayListAndLength(list3);
					break;

				case 4:
					List<Car> list4 = (List<Car>) methods.get("createList0").invoke(task);
					methods.get("addElementToList2").invoke(task, list4, new Car());
					methods.get("addElementToList2").invoke(task, list4, new Swift());
					methods.get("addElementToList2").invoke(task, list4, new SCross());
					methods.get("addElementToList2").invoke(task, list4, new XUV());
					printArrayListAndLength(list4);

				case 5:
					List<Object> list5 = (List<Object>) methods.get("createList0").invoke(task);
					logger.info("Enter the number of integers : ");
					int numberOfIntegers5 = InputUtil.getIntInput();
					int[] integers5 = InputUtil.getMultipleIntInput(numberOfIntegers5);
					for (int i : integers5) {
						methods.get("addElementToList2").invoke(task, list5, i);
					}
					logger.info("Enter the number of Strings : ");
					int numberOfStrings = InputUtil.getIntInput();
					String[] strings5 = InputUtil.getMultipleLineStrings(numberOfStrings);
					methods.get("addElementsToList2").invoke(task, list5, strings5);
					methods.get("addElementToList2").invoke(task, list5, new Swift());
					methods.get("addElementToList2").invoke(task, list5, new XUV());
					printArrayListAndLength(list5);
					break;

				case 6:
					List<String> list6 = (List<String>) methods.get("createList0").invoke(task);
					addMultipleStringsToList(list6);
					boolean isCase6Running = true;
					while (isCase6Running) {
						logger.info("Enter a string to find index (or) press enter to exit : ");
						String checkString = InputUtil.getStringInput();
						if (checkString.isEmpty()) {
							isCase6Running = false;
							break;
						}
						logger.info("Index : " + methods.get("getIndexOfElement2").invoke(task, list6, checkString));
					}
					printArrayListAndLength(list6);
					break;

				case 7:
					List<String> list7 = (List<String>) methods.get("createList0").invoke(task);
					addMultipleStringsToList(list7);
					Iterator<String> listIterator = (Iterator<String>) methods.get("getIterator1").invoke(task, list7);
					logger.info("Printing Elements using Iterator : ");
					for (; listIterator.hasNext(); logger.info(listIterator.next()))
						;
					break;

				case 8:
					List<String> list8 = (List<String>) methods.get("createList0").invoke(task);
					addMultipleStringsToList(list8);
					boolean isCase7Running = true;
					while (isCase7Running) {
						logger.info("Enter an integer to get element (or) enter -1 to exit : ");
						try {
							int index = InputUtil.getIntInput();
							if (index == -1) {
								isCase6Running = false;
								break;
							}
							logger.info("Element at Index " + index + " is : "
									+ methods.get("getElementAtIndex2").invoke(task, list8, index));
						} catch (Exception e) {
							logger.info(e.getMessage());
						}
					}
					printArrayListAndLength(list8);
					break;

				case 9:
					List<String> list9 = (List<String>) methods.get("createList0").invoke(task);
					addMultipleStringsToList(list9);
					boolean isCase9Running = true;
					while (isCase9Running) {
						logger.info("Enter a string to find first and last index (or) press enter to exit : ");
						String checkString = InputUtil.getStringInput();
						if (checkString.isEmpty()) {
							isCase9Running = false;
							break;
						}
						int firstIndex = (int) methods.get("getIndexOfElement2").invoke(task, list9, checkString);
						if (firstIndex == -1) {
							logger.info("String not found in the arraylist.");
							continue;
						}
						int lastIndex = (int) methods.get("getLastIndexOfElement2").invoke(task, list9, checkString);
						if (firstIndex == lastIndex) {
							logger.info("There is only one occurance at index : " + firstIndex);
						} else {
							logger.info("First Index : " + firstIndex + ", Last Index  : " + lastIndex);
						}
					}
					printArrayListAndLength(list9);
					break;

				case 10:
					List<String> list10 = (List<String>) methods.get("createList0").invoke(task);
					addMultipleStringsToList(list10);
					logger.info("Enter a string to add it in the arraylist : ");
					String stringToAdd = InputUtil.getStringInput();
					methods.get("addElementToList2").invoke(task, list10, stringToAdd, 1);
					printArrayListAndLength(list10);
					break;

				case 11:
					List<String> list11 = (List<String>) methods.get("createList0").invoke(task);
					addMultipleStringsToList(list11);
					logger.info("Enter start index of sub array : ");
					int start = InputUtil.getIntInput();
					logger.info("Enter end index of sub array : ");
					int end = InputUtil.getIntInput();
					List<String> subList11 = (List<String>) methods.get("getSubList3").invoke(task, list11, start, end);
					logger.info("Original ArrayList : ");
					printArrayListAndLength(list11);
					logger.info("Sub-ArrayList : ");
					printArrayListAndLength(subList11);
					break;

				case 12:
					List<String> list12a = (List<String>) methods.get("createList0").invoke(task);
					logger.info("First ArrayList : ");
					addMultipleStringsToList(list12a);
					List<String> list12b = (List<String>) methods.get("createList0").invoke(task);
					logger.info("Second ArrayList : ");
					addMultipleStringsToList(list12b);
					methods.get("addElementToList2").invoke(task, list12b, null);
					printArrayListAndLength(list12b);
					List<String> list12c = (List<String>) methods.get("createList0").invoke(task);
					methods.get("addCollectionToList2").invoke(task, list12c, list12a);
					methods.get("addCollectionToList2").invoke(task, list12c, list12b);
					printArrayListAndLength(list12c);
					break;

				case 13:
					List<String> list13a = (List<String>) methods.get("createList0").invoke(task);
					logger.info("First ArrayList : ");
					addMultipleStringsToList(list13a);
					List<String> list13b = (List<String>) methods.get("createList0").invoke(task);
					logger.info("Second ArrayList : ");
					addMultipleStringsToList(list13b);
					List<String> list13c = (List<String>) methods.get("createList0").invoke(task);
					methods.get("addCollectionToList2").invoke(task, list13c, list13a);
					methods.get("addCollectionToList3").invoke(task, list13c, list13b, 0);
					printArrayListAndLength(list13c);
					break;

				case 14:
					List<Float> list14 = (List<Float>) methods.get("createList0").invoke(task);
					logger.info("Enter the number of decimal values : ");
					int numberOfFloats = InputUtil.getIntInput();
					float[] floats14 = InputUtil.getMultipleFloatInput(numberOfFloats);
					for (float f : floats14) {
						methods.get("addElementToList2").invoke(task, list14, f);
					}
					logger.info("Original ArrayList : ");
					printArrayListAndLength(list14);
					logger.info("Enter the index to remove value : ");
					int index14 = InputUtil.getIntInput();
					methods.get("removeElementAtIndex2").invoke(task, list14, index14);
					logger.info("Final ArrayList : ");
					printArrayListAndLength(list14);
					break;

				case 15:
					List<String> list15a = (List<String>) methods.get("createList0").invoke(task);
					addMultipleStringsToList(list15a);
					printArrayListAndLength(list15a);
					logger.info("-----------------------------------------------------------------");
					logger.info("Second ArrayList : ");
					List<String> list15b = (List<String>) methods.get("createList0").invoke(task);
					addMultipleStringsToList(list15b);
					printArrayListAndLength(list15b);
					logger.info("-----------------------------------------------------------------");
					logger.info("First ArrayList after removing common elements : ");
					methods.get("removeCollection2").invoke(task, list15a, list15b);
					printArrayListAndLength(list15a);
					break;

				case 16:
					List<String> list16a = (List<String>) methods.get("createList0").invoke(task);
					addMultipleStringsToList(list16a);
					printArrayListAndLength(list16a);
					logger.info("-----------------------------------------------------------------");
					logger.info("Second ArrayList : ");
					List<String> list16b = (List<String>) methods.get("createList0").invoke(task);
					addMultipleStringsToList(list16b);
					printArrayListAndLength(list16b);
					logger.info("-----------------------------------------------------------------");
					logger.info("First ArrayList after removing unique elements : ");
					methods.get("retainCollect2").invoke(task, list16a, list16b);
					printArrayListAndLength(list16a);
					break;

				case 17:
					List<Long> list17 = (List<Long>) methods.get("createList0").invoke(task);
					logger.info("Enter the number of long values : ");
					int numberOfLong = InputUtil.getIntInput();
					long[] longs = InputUtil.getMultipleLongInput(numberOfLong);
					for (long i : longs) {
						methods.get("addElementToList2").invoke(task, list17, i);
					}
					logger.info("Initial ArrayList : ");
					printArrayListAndLength(list17);
					logger.info("FInal ArrayList : ");
					methods.get("clearList1").invoke(task, list17);
					printArrayListAndLength(list17);
					break;

				case 18:
					List<String> list18 = (List<String>) methods.get("createList0").invoke(task);
					addMultipleStringsToList(list18);
					boolean isCase18Running = true;
					while (isCase18Running) {
						logger.info("Enter a string to find first and last index (or) press enter to exit : ");
						String checkString = InputUtil.getStringInput();
						if (checkString.isEmpty()) {
							isCase18Running = false;
							break;
						}
						int index = (int) methods.get("getIndexOfElement2").invoke(task, list18, checkString);
						if (index == -1) {
							logger.info("String not found in the arraylist.");
							continue;
						} else {
							logger.info("Given string is present in index : " + index);
						}
					}
					printArrayListAndLength(list18);
					break;

				default:
					logger.info("The choice is invalid");
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
}