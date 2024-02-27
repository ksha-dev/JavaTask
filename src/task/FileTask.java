package task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import constants.ExceptionMessage;
import exceptions.CustomException;
import utility.Utility;

public class FileTask {

	public static String DEFAULT_FILE_PATH = System.getProperty("user.dir");
	public static String DEFAULT_SEPARATOR = File.separator;

	public static void checkPath(String path) throws CustomException {
		Utility.validateObject(path);
		if (!path.trim().isEmpty()) {
			File pathDir = new File(path);
			if (!pathDir.exists()) {
				pathDir.mkdir();
			}
		}
	}

	public String createFileAndStoreData(String path, String fileName, String... stringsToStore)
			throws CustomException {
		Utility.validateObject(stringsToStore);
		int length = stringsToStore.length;
		if (length == 0) {
			throw new CustomException(ExceptionMessage.EMPTY_ARRAY);
		}
		Utility.validateObject(fileName);
		checkPath(path);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + DEFAULT_SEPARATOR + fileName))) {
			writer.write(stringsToStore[0]);
			for (int i = 1; i < length; i++) {
				writer.write("\n");
				writer.write(stringsToStore[i]);
			}
		} catch (IOException e) {
			throw new CustomException(ExceptionMessage.FILE_EXCEPTION + e.getMessage());
		}
		return path;
	}

	public String createFileAndStoreData(String fileName, String... stringsToStore) throws CustomException {
		return createFileAndStoreData(DEFAULT_FILE_PATH, fileName, stringsToStore);
	}

	public <K, V> String storeProperties(String path, String fileName, Properties properties)
			throws CustomException {
		Utility.validateObject(properties);
		if (properties.isEmpty()) {
			throw new CustomException(ExceptionMessage.EMPTY_ENTITY);
		}
		Utility.validateObject(fileName);
		checkPath(path);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + DEFAULT_SEPARATOR + fileName))) {
			properties.store(writer, null);
		} catch (IOException e) {
			throw new CustomException(e.getMessage());
		}
		return path;
	}

	public <K, V> String storeProperties(String fileName, Properties properties) throws CustomException {
		return storeProperties(DEFAULT_FILE_PATH, fileName, properties);
	}

	public Properties getPropertiesFromFile(String path, String fileName) throws CustomException {
		Utility.validateObject(path);
		Properties returnProps = new Properties();
		try (BufferedReader reader = new BufferedReader(new FileReader(path + DEFAULT_SEPARATOR + fileName))) {
			returnProps.load(reader);
		} catch (IOException e) {
			throw new CustomException(e.getMessage());
		}
		return returnProps;
	}

	public Properties getPropertiesFromFile(String fileName) throws CustomException {
		return getPropertiesFromFile(DEFAULT_FILE_PATH, fileName);
	}

}
