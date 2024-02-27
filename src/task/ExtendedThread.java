package task;

import java.util.logging.Level;

import exceptions.CustomException;
import runner.ThreadRunner;
import utility.Utility;

public class ExtendedThread extends Thread {

	public static final int DEFAULT_SLEEP_TIME = 1000;
	public static final int DEFAULT_SELECT_METHOD = 0;

	private long sleepTime;
	private int selectMethod;
	private volatile boolean runThread = true;

	public ExtendedThread() {
		this.sleepTime = DEFAULT_SLEEP_TIME;
		this.selectMethod = DEFAULT_SELECT_METHOD;
	}

	public ExtendedThread(String name, long sleepTime) throws CustomException {
		Utility.validateObject(name);
		Utility.validateRange(0, sleepTime);
		this.sleepTime = sleepTime;
		this.selectMethod = DEFAULT_SELECT_METHOD;
		this.setName(name);
	}

	public void run() {
		try {
			switch (selectMethod) {

			case 1:
				run1();
				break;
			default:
				runDefault();
				break;
			}
		} catch (Exception e) {
			ThreadRunner.logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private void runDefault() throws CustomException, InterruptedException {
		Utility.printThreadDetails(this, ThreadRunner.logger);
		ThreadRunner.logger.info("Going to sleep : " + getName());
		try {
			sleep(sleepTime);
		} catch (Exception e) {
		}
		ThreadRunner.logger.info("After sleeping : " + getName());
	}

	private void run1() throws CustomException {
		Utility.printThreadDetails(this, ThreadRunner.logger);
		long numberOfRuns = 0;
		while (runThread) {
			numberOfRuns++;
			try {
				sleep(sleepTime);
			} catch (Exception e) {
			}
		}
		ThreadRunner.logger.info(this.getName() + " has run : " + numberOfRuns + " many number of times");
	}

	public void stopLoop() {
		this.runThread = false;
	}

	public void setSelectMethod(int method) throws CustomException {
		Utility.validateRange(0, method);
		this.selectMethod = method;
	}
}
