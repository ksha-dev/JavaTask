package bird;

import utility.LoggerUtility;

public abstract class BirdAbstract {
	public void fly() {
		LoggerUtility.DEFAULT_LOGGER.info("Bird is flying");
	}

	public void speak() {
		LoggerUtility.DEFAULT_LOGGER.info("Bird is speaking");
	}
}