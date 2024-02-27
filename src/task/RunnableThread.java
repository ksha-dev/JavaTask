package task;

import java.util.logging.Level;

import exceptions.CustomException;
import runner.ThreadRunner;
import utility.Utility;

public class RunnableThread implements Runnable {
	
	public static final int DEFAULT_SLEEP_TIME = 1000;
	public static final int DEFAULT_SELECT_METHOD = 0;

	private long sleepTime;
	private int selectMethod;
	private volatile boolean runThread = true;

	public RunnableThread() {
		this.sleepTime = DEFAULT_SLEEP_TIME;
		this.selectMethod = DEFAULT_SELECT_METHOD;
	}

	public RunnableThread(long sleepTime) throws CustomException {
		Utility.validateRange(0, sleepTime);
		this.sleepTime = sleepTime;
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

	private void runDefault() throws CustomException {
		Thread currentThread = Thread.currentThread();
		Utility.printThreadDetails(currentThread, ThreadRunner.logger);
		ThreadRunner.logger.info("Going to sleep : " + currentThread.getName());
		try {
			Thread.sleep(sleepTime);
		} catch (Exception e) {}
		ThreadRunner.logger.info("After sleeping : " + currentThread.getName());
	}

	private void run1() throws CustomException {
		Thread currentThread = Thread.currentThread();
		Utility.printThreadDetails(currentThread, ThreadRunner.logger);
		long numberOfRuns = 0;
		while (runThread) {
			numberOfRuns++;
			try {
				Thread.sleep(sleepTime);
			} catch (Exception e) {}
		}
		ThreadRunner.logger.info(currentThread.getName() + " has run : " + numberOfRuns + " many number of times");
	}

	public void stopLoop() {
		runThread = false;
	}

	public void setMethod(int method) throws CustomException {
		Utility.validateRange(0, method);
		this.selectMethod = method;
	}
}
