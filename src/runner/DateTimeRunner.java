package runner;

import java.time.ZonedDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.CustomException;
import task.DateTimeTask;
import utility.LoggerUtility;
import utility.InputUtil;

public class DateTimeRunner {

	public static Logger logger = null;

	static {
		try {
			logger = LoggerUtility.getNewLogger(DateTimeRunner.class.getName());
			logger.setLevel(Level.INFO);
			LoggerUtility.addFileHandler(logger, logger.getName(), LoggerUtility.DEFAULT_LOGGER_PATH);
		} catch (CustomException e) {
			LoggerUtility.DEFAULT_LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public static void main(String... args) {
		dateTimeRunner();
	}

	public static void dateTimeRunner() {
		boolean isProgramActive = true;
		int numberOfQuestions = 20;
		DateTimeTask task = new DateTimeTask();
		ZonedDateTime dateTime = task.getLocalDateTime();
		logger.info("Date Time Runner Program");
		logger.info("The following are the list of operations : ");
		while (isProgramActive) {

			logger.info("1 - Get current date and time" + "\n2 - Get current milliseconds using system class"
					+ "\n3 - Get date and time of Newyork and london. Get the difference"
					+ "\n4 - Get the weekday of a given datetime" + "\n5 - Get the month of a given datetime"
					+ "\n6 - Get the year of a given datetime" + "\nTo exit, enter 0"
					+ "\n\n-----------------------------------------------------------------");

			int choice = -1;
			do {
				try {
					logger.info("Enter your choice (0 to " + numberOfQuestions + "): ");
					choice = InputUtil.getIntInput();
				} catch (CustomException e) {
					logger.warning(e.getMessage());
				}
			} while (choice < 0 || choice > numberOfQuestions);

			try {
				switch (choice) {
				case 0:
					isProgramActive = false;
					logger.info("Thank you for using the program");
					break;

				case 1:
					logger.info("Current Date and Time : ");
					dateTime = task.getLocalDateTime();
					logger.info(dateTime + "");
					break;

				case 2:
					logger.info("Current Date and Time in Milliseconds : ");
					logger.info(task.getDateTimeInMillis() + "");
					break;

				case 3:
					ZonedDateTime newyork = task.getZoneDateTime("America/New_York");
					ZonedDateTime london = task.getZoneDateTime("Europe/London");
					london.plusDays(10);
					logger.info("Current Date and Time in NewYork : " + newyork);
					logger.info("Current Date and Time in London : " + london);
					logger.info("Date Difference : "+ task.getDateDifference(newyork, london));
					logger.info("Time Difference : "+ task.getTimeDifference(newyork, london));
					logger.info("Offset Difference : "+ task.getOffsetDifference(newyork, london));
					break;

				case 4:
					logger.info("Weekday of a given DateTime : ");
					logger.info(task.getWeekDay(dateTime) + "");
					break;

				case 5:
					logger.info("Month of a given DateTime : ");
					logger.info(task.getMonth(dateTime) + "");
					break;

				case 6:
					logger.info("Year of a given DateTime : ");
					logger.info(task.getYear(dateTime) + "");
					break;

				default:
					logger.info("The choice is invalid");
					break;
				}
			} catch (CustomException e) {
				logger.severe(e.getMessage());
			}
			logger.info("=================================================================");

		}
	}
}
