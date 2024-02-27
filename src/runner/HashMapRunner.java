package runner;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import cars.Car;
import exceptions.CustomException;
import utility.LoggerUtility;
import utility.InputUtil;
import utility.Utility;

@SuppressWarnings("unchecked")
public class HashMapRunner {

	// creating log instance
	private static Logger logger = null;
	static {
		try {
			logger = LoggerUtility.getNewLogger(HashMapRunner.class.getName());
			logger.setLevel(Level.INFO);
			LoggerUtility.addFileHandler(logger, logger.getName(), LoggerUtility.DEFAULT_LOGGER_PATH);
		} catch (Exception e) {
			LoggerUtility.DEFAULT_LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	// creating object using reflection and getting methods map
	private static Object task = null;
	private static Map<String, Method> method = null;
	static {
		try {
			Class<?> listTaskClass = Class.forName("task.MapTask");
			task = listTaskClass.getDeclaredConstructor().newInstance();

			Method[] availableMethods = listTaskClass.getMethods();
			for (Method availableMethod : availableMethods) {
				if(availableMethod.getName().equals("createMap") && availableMethod.getParameterCount()==0) {
					method = (Map<String, Method>) availableMethod.invoke(task);
				}
			}
			for (Method availableMethod : availableMethods) {
				method.put(availableMethod.getName() + availableMethod.getParameterCount(), availableMethod);
			}
			method.forEach((k, v)-> System.out.println(k+v));
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);

		}
	}

	public static void main(String[] args) {

		boolean isProgramActive = true;
		int numberOfQuestions = 20;

		logger.info("HashMap Task Runner Programm");
		logger.info("The following are the list of operations : ");
		while (isProgramActive) {

			logger.info("1 - Create a HashMap. Print the map and its size"
					+ "\n2 - Create a HashMap. Add 3 pairs of string keys and string values"
					+ "\n3 - Create a HashMap. Add 3 pairs of integer keys and integer values"
					+ "\n4 - Create a HashMap. Add 3 pairs of string keys and integer values"
					+ "\n5 - Create a HashMap. Add 3 pairs of string keys and custom object values"
					+ "\n6 - Create a HashMap. Add 3 pairs of string key and values, with one key containing null value"
					+ "\n7 - Create a HashMap. Add 3 pairs of string key and values, with one key being null and a non-null value"
					+ "\n8 - Create a HashMap with Strings. Check if a key is present in it"
					+ "\n9 - Create a HashMap with Strings. Check if a value is present in it"
					+ "\n10 - Create a HashMap with Strings. Replace the values of all the keys"
					+ "\n11 & 12 - Create a HashMap with Strings. Check and print a value for a key"
					+ "\n13 - Create a HashMap with Strings. Check and print a value for a key. If absent, print zoho"
					+ "\n14 - Remove a key from the a HashMap"
					+ "\n15 - Remove a key only if the value matches the given value"
					+ "\n16 - Replace a value of a key in the HashMap"
					+ "\n17 - Replace a value of a key only if the old value matches the given value"
					+ "\n18 - Transfer all the elements from one map to another"
					+ "\n19 - Iterate over a Hashmap and print all the keys and values"
					+ "\n20 - Remove all the elements in the map" + "\nTo exit, enter 0"
					+ "\n\n-----------------------------------------------------------------");

			int choice = -1;
			do {
				try {
					logger.info("Enter your choice (0 to " + numberOfQuestions + "): ");
					choice = InputUtil.getIntInput();
				} catch (Exception e) {
					logger.info(e.getMessage());
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
					Map<Object, Object> map1 = (Map<Object, Object>) method.get("createMap0").invoke(task);
					printMapAndLength(map1);
					break;

				case 2:
					Map<String, String> map2 = (Map<String, String>) method.get("createMap0").invoke(task);
					getStringElementsIntoMap(map2);
					printMapAndLength(map2);
					break;

				case 3:
					Map<Integer, Integer> map3 = (Map<Integer, Integer>) method.get("createMap0").invoke(task);
					logger.info("Enter the number of elements : ");
					int numberOfElements3 = InputUtil.getIntInput();
					for (int i = 1; i <= numberOfElements3; i++) {
						Integer key = null;
						do {
							logger.info("Enter key " + i + " : ");
							try {
								key = InputUtil.getIntInput();
							} catch (Exception e) {
								logger.info(e.getMessage());
							}
						} while (Utility.isNull(key));

						Integer value = null;
						do {
							logger.info("Enter value " + i + " : ");
							try {
								value = InputUtil.getIntInput();
							} catch (Exception e) {
								logger.info(e.getMessage());
							}
						} while (Utility.isNull(value));
						method.get("addElement3").invoke(task, map3, key, value);
					}
					printMapAndLength(map3);
					break;

				case 4:
					Map<String, Integer> map4 = (Map<String, Integer>) method.get("createMap0").invoke(task);
					logger.info("Enter the number of elements : ");
					int numberOfElements4 = InputUtil.getIntInput();
					for (int i = 1; i <= numberOfElements4; i++) {
						logger.info("Enter key " + i + " : ");
						String key = InputUtil.getStringInput();
						Integer value = null;
						do {
							logger.info("Enter value " + i + " : ");
							try {
								value = InputUtil.getIntInput();
							} catch (Exception e) {
								logger.info(e.getMessage());
							}
						} while (Utility.isNull(value));
						method.get("addElement3").invoke(task, map4, key, value);
					}
					printMapAndLength(map4);
					break;

				case 5:
					Map<String, Car> map5 = (Map<String, Car>) method.get("createMap0").invoke(task);
					logger.info("Enter the number of elements : ");
					int numberOfElements5 = InputUtil.getIntInput();
					for (int i = 1; i <= numberOfElements5; i++) {
						logger.info("Enter key " + i + " : ");
						String key = InputUtil.getStringInput();
						Car value = new Car();
						method.get("addElement3").invoke(task, map5, key, value);
					}
					printMapAndLength(map5);
					break;

				case 6:
					Map<String, String> map6 = (Map<String, String>) method.get("createMap0").invoke(task);
					getStringElementsIntoMap(map6);
					method.get("addElement3").invoke(task, map6, "key", null);
					printMapAndLength(map6);
					break;

				case 7:
					Map<String, String> map7 = (Map<String, String>) method.get("createMap0").invoke(task);
					getStringElementsIntoMap(map7);
					method.get("addElement3").invoke(map7, null, "value");
					printMapAndLength(map7);
					break;

				case 8:
					Map<String, String> map8 = (Map<String, String>) method.get("createMap0").invoke(task);
					getStringElementsIntoMap(map8);
					String keyCheck = null;
					do {
						logger.info("Enter a key to check for existance (or) hit enter to exit this operation : ");
						keyCheck = InputUtil.getStringInput();
						if (keyCheck.isEmpty()) {
							break;
						}
						boolean check = (boolean) method.get("doesMapContainsKey2").invoke(task, map8, keyCheck);
						if (check) {
							logger.info("The map contains the given key");
						} else {
							logger.info("The given key is not found in the Map");
						}
					} while (Utility.isNull(keyCheck) || !keyCheck.isEmpty());
					break;

				case 9:
					Map<String, String> map9 = (Map<String, String>) method.get("createMap0").invoke(task);
					getStringElementsIntoMap(map9);
					String valueCheck = null;
					do {
						logger.info("Enter a key to check for existance (or) hit enter to exit this operation : ");
						valueCheck = InputUtil.getStringInput();
						if (valueCheck.isEmpty()) {
							break;
						}
						boolean check = (boolean) method.get("doesMapContainsValue2").invoke(task, map9, null);
						if (check) {
							logger.info("The map contains the given value");
						} else {
							logger.info("The given value is not found in the Map");
						}
					} while (Utility.isNull(valueCheck) || !valueCheck.isEmpty());
					break;

				case 10:
					Map<String, String> map10 = (Map<String, String>) method.get("createMap0").invoke(task);
					getStringElementsIntoMap(map10);
					logger.info("Origianl Map");
					printMapAndLength(map10);
					map10.forEach((key, value) -> {
						try {
							logger.info("Enter new value for the key - " + key + " : ");
							method.get("modifyKeyValues3").invoke(task, map10, key, InputUtil.getStringInput());
						} catch (Exception e) {
							logger.severe(e.getMessage());
						}
					});
					logger.info("Final Map");
					printMapAndLength(map10);
					break;

				case 11:
				case 12:
					Map<String, String> map11 = (Map<String, String>) method.get("createMap0").invoke(task);
					getStringElementsIntoMap(map11);
					printMapAndLength(map11);
					String key11Check = null;
					do {
						logger.info("Enter a key to get the value (or) hit enter to exit this operation : ");
						key11Check = InputUtil.getStringInput();
						if (!Utility.isNull(key11Check) && key11Check.isEmpty()) {
							break;
						}
						String valueObtained = (String) method.get("getValueOfKey2").invoke(task, map11, key11Check);
						if (Utility.isNull(valueObtained)) {
							logger.info("Zoho");
						} else {
							logger.info("The map contains the given key. Its value is : " + valueObtained);
						}
					} while (Utility.isNull(key11Check) || !key11Check.isEmpty());
					break;

				case 13:
					Map<String, String> map13 = (Map<String, String>) method.get("createMap0").invoke(task);
					getStringElementsIntoMap(map13);
					printMapAndLength(map13);
					String key13Check = null;
					do {
						logger.info("Enter a key to get the value (or) hit enter to exit this operation : ");
						key13Check = InputUtil.getStringInput();
						if (!Utility.isNull(key13Check) && key13Check.isEmpty()) {
							break;
						}
						String valueObtained = (String) method.get("getValueOfKey3").invoke(map13, key13Check, "Zoho");
						logger.info(valueObtained);
					} while (Utility.isNull(key13Check) || !key13Check.isEmpty());
					break;

				case 14:
					Map<String, String> map14 = (Map<String, String>) method.get("createMap0").invoke(task);
					getStringElementsIntoMap(map14);
					printMapAndLength(map14);
					logger.info("Enter a key string to delete it from the map : ");
					String keyToDelete = InputUtil.getStringInput();
					method.get("removeKeyFromMap2").invoke(task, map14, keyToDelete);
					printMapAndLength(map14);
					break;

				case 15:
					Map<String, String> map15 = (Map<String, String>) method.get("createMap0").invoke(task);
					getStringElementsIntoMap(map15);
					printMapAndLength(map15);
					logger.info("Enter a key string to delete it from the map : ");
					String key15ToDelete = InputUtil.getStringInput();
					logger.info("Enter its value to check before deleting : ");
					String valueToCheck = InputUtil.getStringInput();
					boolean isDeleted = (boolean) method.get("removeKeyFromMap3").invoke(task, map15, key15ToDelete,
							valueToCheck);
					if (isDeleted) {
						logger.info("The given key and value is successfully removed from the map");
					} else {
						System.out
								.println("The given value does not match the present value or the key might be absent");
					}
					printMapAndLength(map15);
					break;

				case 16:
					Map<String, String> map16 = (Map<String, String>) method.get("createMap0").invoke(task);
					getStringElementsIntoMap(map16);
					printMapAndLength(map16);
					logger.info("Enter a key string to replace the value : ");
					String key16ToReplace = InputUtil.getStringInput();
					logger.info("Enter the replace value : ");
					String value16ToReplace = InputUtil.getStringInput();
					String checkForReplaceMent = (String) method.get("modifyKeyValues3").invoke(task, map16,
							key16ToReplace, value16ToReplace);
					if (Utility.isNull(checkForReplaceMent)) {
						logger.info("The key is be absent.");
					} else {
						logger.info("The new value is successfully replaced in the map");
					}
					printMapAndLength(map16);
					break;

				case 17:
					Map<String, String> map17 = (Map<String, String>) method.get("createMap0").invoke(task);
					getStringElementsIntoMap(map17);
					printMapAndLength(map17);
					logger.info("Enter a key string to replace the value : ");
					String key17ToReplace = InputUtil.getStringInput();
					logger.info("Enter its value to check before replacing : ");
					String value17ToCheck = InputUtil.getStringInput();
					logger.info("Enter the replace value : ");
					String value17ToReplace = InputUtil.getStringInput();
					boolean isReplaced = (boolean) method.get("modifyKeyValues4").invoke(task, map17, key17ToReplace,
							value17ToCheck, value17ToReplace);
					if (isReplaced) {
						logger.info("The new value is successfully replaced in the map");
					} else {
						logger.info("The old value does not match the present value or the key might be absent");
					}
					printMapAndLength(map17);
					break;

				case 18:
					Map<String, String> map18a = (Map<String, String>) method.get("createMap0").invoke(task);
					getStringElementsIntoMap(map18a);
					logger.info("First HashMap");
					printMapAndLength(map18a);
					Map<String, String> map18b = (Map<String, String>) method.get("createMap0").invoke(task);
					getStringElementsIntoMap(map18b);
					logger.info("Second HashMap");
					method.get("addFromAnotherMap2").invoke(task, map18a, map18b);
					logger.info("First Map after copying values from second map : ");
					printMapAndLength(map18a);
					break;

				case 19:
					Map<String, String> map19 = (Map<String, String>) method.get("createMap0").invoke(task);
					getStringElementsIntoMap(map19);
					logger.info("Printing the map : \n<Key> : <Value>");
					map19.forEach((key, value) -> logger.info(key + " : " + value));
					break;

				case 20:
					Map<String, String> map20 = (Map<String, String>) method.get("createMap0").invoke(task);
					getStringElementsIntoMap(map20);
					printMapAndLength(map20);
					logger.info("Map after removing all the elements : ");
					method.get("clearMap1").invoke(task, map20);
					printMapAndLength(map20);
					break;

				default:
					logger.info("The choice is invalid");
					break;
				}
			} catch (CustomException e) {
				logger.info(e.getMessage());
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
			logger.info("=================================================================");
		}
	}

	public static <K, V> void printMapAndLength(Map<K, V> map) throws Exception {
		logger.info("Map : " + map);
		logger.info("Size of Map : " + method.get("getSize1").invoke(task, map));
	}

	public static void getStringElementsIntoMap(Map<String, String> map) throws Exception {
		logger.info("Enter the number of elements : ");
		int numberOfElements = InputUtil.getIntInput();
		for (int i = 1; i <= numberOfElements; i++) {
			logger.info("Enter key " + i + " : ");
			String key = InputUtil.getStringInput();
			logger.info("Enter value " + i + " : ");
			String value = InputUtil.getStringInput();
			method.get("addElement3").invoke(task, map, key, value);
			logger.info("------------------------------");
		}
	}
}
