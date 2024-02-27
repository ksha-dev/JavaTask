package runner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.CustomException;
import utility.InputUtil;
import utility.LoggerUtility;

public class ReflectionRunner {

	public static Logger logger = null;

	static {
		try {
			logger = LoggerUtility.getNewLogger(ReflectionRunner.class.getName());
			logger.setLevel(Level.INFO);
			LoggerUtility.addFileHandler(logger, logger.getName(), LoggerUtility.DEFAULT_LOGGER_PATH);
		} catch (Exception e) {
			LoggerUtility.DEFAULT_LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public static void main(String... args) {
		reflectionRunner();
	}
	
	@SuppressWarnings({ "rawtypes" })
	public static void reflectionRunner() {
		try {
			logger.info("Reflection Task Runner : ");
			logger.info("1. Create Class object without any imports");
			Class pojoClass = Class.forName("custom.POJO");
			logger.info(pojoClass.getName());
			logger.info("------------------");

			Object pojo1 = null;
			Object pojo2 = null;

			Constructor[] availableConstructors = pojoClass.getConstructors();
			for (Constructor constructor : availableConstructors) {
				if (constructor.getParameterCount() == 0) {
					logger.info("2. Invoke the default constructor");
					pojo1 = constructor.newInstance();
					logger.info("------------------");
				}

				if (constructor.getParameterCount() == 2) {
					logger.info("3. Invoke the overloaded constructor");
					logger.info("Enter a string : ");
					String string = InputUtil.getStringInput();
					logger.info("Enter an integer : ");
					int integer = InputUtil.getIntInput();
					pojo2 = constructor.newInstance(string, integer);
					logger.info("------------------");
				}
			}

			Method[] methodsAvaiable = pojoClass.getMethods();

			for (Method method : methodsAvaiable) {
				if (method.getName().equals("setString")) {
					logger.info("5. Invoke any one setter method");
					logger.info("Enter a string for object 1 : ");
					String string = InputUtil.getStringInput();
					method.invoke(pojo1, string);
					logger.info("------------------");
				}

				if (method.getName().equals("getString")) {
					logger.info("4. Invoke any one getter method");
					logger.info("String in Object 2 : " + method.invoke(pojo2));
					logger.info("------------------");
				}

//				if (method.getName().equals("setInteger")) {
//					logger.info("Enter an integer for object 1 : ");
//					int integer = InputUtility.getIntInput();
//					method.invoke(pojo1, integer);
//				}
//				
//				if (method.getName().equals("getInteger")) {
//					logger.info("Integer in Object 2 : " + method.invoke(pojo2));
//				}
			}
			logger.info("Printing the objects :");
			logger.info(pojo1.toString());
			logger.info(pojo2.toString());
		} catch (CustomException e) {
			logger.severe(e.getMessage());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}
}

/*
 * Reflections
 * 
 * method.setAccessible(true); -> gets operation done on any private methods
 * also
 * 
 */
