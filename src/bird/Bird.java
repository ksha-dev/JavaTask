package bird;

import utility.LoggerUtility;

abstract class Bird {
	public abstract void fly();

	public void speak() {
		LoggerUtility.DEFAULT_LOGGER.info("Bird speaks");
	}
}