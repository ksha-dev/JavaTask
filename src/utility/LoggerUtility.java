package utility;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import exceptions.CustomException;

public class LoggerUtility {

	private static final String DEFAULT_LOGGER_NAME = "ThreadTaskLogger";

	public static final String DEFAULT_FILE_PATH = System.getProperty("user.dir");
	public static final String DEFAULT_SEPARATOR = File.separator;
	public static final String DEFAULT_LOGGER_PATH = DEFAULT_FILE_PATH + DEFAULT_SEPARATOR + "logs";
	public static final String DEFAULT_LOG_FILE_EXTENSION = ".log";

	public static final Logger DEFAULT_LOGGER = Logger.getLogger(DEFAULT_LOGGER_NAME);

	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
		try {
			checkPath(DEFAULT_LOGGER_PATH);
			FileHandler fileHander = new FileHandler(
					DEFAULT_LOGGER_PATH + DEFAULT_SEPARATOR + DEFAULT_LOGGER_NAME + DEFAULT_LOG_FILE_EXTENSION);
			DEFAULT_LOGGER.addHandler(fileHander);
			fileHander.setFormatter(new SimpleFormatter());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Logger getNewLogger(String className) throws CustomException {
		Utility.validateObject(className);
		return Logger.getLogger(className);
	}

	public static void addFileHandler(Logger logger, String fileName, String path) throws CustomException {
		Utility.validateObject(fileName);
		try {
			checkPath(path);
			FileHandler fileHander = new FileHandler(path + DEFAULT_SEPARATOR + fileName + DEFAULT_LOG_FILE_EXTENSION);
			logger.addHandler(fileHander);
			fileHander.setFormatter(new SimpleFormatter());
		} catch (Exception e) {
			DEFAULT_LOGGER.info(e.getMessage());
		}
	}

	public static void checkPath(String path) throws CustomException {
		Utility.validateObject(path);
		if (!path.trim().isEmpty()) {
			File pathDir = new File(path);
			if (!pathDir.exists()) {
				pathDir.mkdir();
			}
		}
	}

	public static void logSevere(Logger logger, Exception e) {
		try {
			Utility.validateObject(logger);
			Utility.validateObject(e);
			logger.log(Level.SEVERE, e.getMessage(), e);
		} catch (Exception exp) {
			DEFAULT_LOGGER.info(exp.getMessage());
		}
	}
}
