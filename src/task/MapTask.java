package task;

import java.util.Map;

import exceptions.CustomException;
import utility.LoggerUtility;
import utility.Utility;

public class MapTask {

	private static Class<?> hashMapClass = null;

	static {
		try {
			hashMapClass = Class.forName("java.util.HashMap");
		} catch (Exception e) {
			LoggerUtility.DEFAULT_LOGGER.severe(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public <K, V> Map<K, V> createMap() throws Exception {
		return (Map<K, V>) hashMapClass.getDeclaredConstructor().newInstance();
	}

	public <K, V> int getSize(Map<K, V> map) throws CustomException {
		Utility.validateObject(map);
		return map.size();
	}

	public <K, V> void addElement(Map<K, V> map, K key, V value) throws CustomException {
		Utility.validateObject(map);
		map.put(key, value);
	}

	public <K, V> boolean doesMapContainsKey(Map<K, V> map, K key) throws CustomException {
		Utility.validateObject(map);
		return map.containsKey(key);
	}

	public <K, V> boolean doesMapContainsValue(Map<K, V> map, V value) throws CustomException {
		Utility.validateObject(map);
		return map.containsValue(value);
	}

	public <K, V> V getValueOfKey(Map<K, V> map, K key) throws CustomException {
		Utility.validateObject(map);
		return map.get(key);
	}

	public <K, V> V getValueOfKey(Map<K, V> map, K key, V defaultValue) throws CustomException {
		Utility.validateObject(map);
		return map.getOrDefault(key, defaultValue);
	}

	public <K, V> V modifyKeyValues(Map<K, V> map, K existingkey, V replaceValue) throws CustomException {
		Utility.validateObject(map);
		return map.replace(existingkey, replaceValue);
	}

	public <K, V> boolean modifyKeyValues(Map<K, V> map, K existingkey, V originalValueToCheck, V replaceValue)
			throws CustomException {
		Utility.validateObject(map);
		return map.replace(existingkey, originalValueToCheck, replaceValue);
	}

	public <K, V> V removeKeyFromMap(Map<K, V> map, K key) throws CustomException {
		Utility.validateObject(map);
		return map.remove(key);
	}

	public <K, V> boolean removeKeyFromMap(Map<K, V> map, K key, V value) throws CustomException {
		Utility.validateObject(map);
		return map.remove(key, value);
	}

	public <K, V> void addFromAnotherMap(Map<K, V> map1, Map<K, V> map2) throws CustomException {
		Utility.validateObject(map1);
		Utility.validateObject(map2);
		map1.putAll(map2);
	}

	public <K, V> void clearMap(Map<K, V> map) throws CustomException {
		Utility.validateObject(map);
		map.clear();
	}
}
