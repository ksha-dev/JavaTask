package runner;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.CustomException;
import task.ExtendedThread;
import task.RunnableThread;
import utility.InputUtil;
import utility.LoggerUtility;
import utility.Utility;

public class ThreadRunner {

	public static Logger logger = null;
	static {
		try {
			logger = LoggerUtility.getNewLogger(ThreadRunner.class.getName());
			logger.setLevel(Level.INFO);
			LoggerUtility.addFileHandler(logger, logger.getName(), LoggerUtility.DEFAULT_LOGGER_PATH);
		} catch (Exception e) {
			LoggerUtility.DEFAULT_LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private static ThreadMXBean mxbean = ManagementFactory.getThreadMXBean();

	public static void main(String... args) {
		boolean isProgramActive = true;
		int numberOfQuestions = 20;

		logger.info("Runner Program");
		logger.info("The following are the list of operations : ");
		while (isProgramActive) {

			logger.info("1 - Create a ExtendedThread class extending Thread. Create a thread in the main and run it"
					+ "\n2 - Create a RunnableThread class implementing Runnable. Create a thread in main using this class"
					+ "\n3 - Create a ExtendedThread and provide a name and a RunnableThread and set its name. Print its details"
					+ "\n4 - Add sleep in run methods. Call 5 objects of each and observe" + "\nTo exit, enter 0"
					+ "\n6 - Create ExtendedThreads and RunnableThreads. Have a while loop which runs infinitely. Set it to stop with external toggle."
					+ "\n7 - Same as 6, but stop each thread with a small interval in between"
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

				case 1: {
					ExtendedThread thread1 = new ExtendedThread();
					Utility.printThreadDetails(thread1, logger);
					thread1.start();
					putThreadToSleep(100);
					Utility.printThreadDetails(thread1, logger);
					thread1.join();
					Utility.printThreadDetails(thread1, logger);
				}
					break;

				case 2: {
					Thread thread2 = new Thread(new RunnableThread());
					Utility.printThreadDetails(thread2, logger);
					thread2.start();
					putThreadToSleep(100);
					Utility.printThreadDetails(thread2, logger);
					thread2.join();
					Utility.printThreadDetails(thread2, logger);
				}
					break;

				case 3: {
					logger.info("Spawning an Extended Thread");
					ExtendedThread thread3 = new ExtendedThread("ExtendedThread", 1500);
					Utility.printThreadDetails(thread3, logger);
					logger.info("Starting the thread");
					thread3.start();
					putThreadToSleep(1000);
					logger.info("Intermediate state");
					Utility.printThreadDetails(thread3, logger);
					thread3.join();
					logger.info("Thread Completed");
					Utility.printThreadDetails(thread3, logger);

					logger.info("Spawning a Runnable Thread");
					Thread thread4 = new Thread(new RunnableThread(3000));
					thread4.setName("RunnableThread");
					Utility.printThreadDetails(thread4, logger);
					logger.info("Starting the thread");
					thread4.start();
					putThreadToSleep(1000);
					logger.info("Intermediate state");
					Utility.printThreadDetails(thread4, logger);
					thread4.join();
					logger.info("Thread Completed");
					Utility.printThreadDetails(thread4, logger);
				}
					break;

				case 4: {
					logger.info("Spawning five ExtendedThreads");
					for (int i = 0; i < 5; i++) {
						ExtendedThread thread = new ExtendedThread("ExtendedThread" + i, 3000);
						thread.start();
					}
					logger.info("Spawning five RunnableThreads");
					for (int i = 0; i < 5; i++) {
						Thread thread = new Thread(new RunnableThread(4500));
						thread.setName("RunnableThread" + i);
						thread.start();
					}
				}
					break;

				case 6: {
					// Spawning 5 extended threads
					logger.info("Spawning five ExtendedThreads");
					for (int i = 0; i < 5; i++) {
						ExtendedThread thread = new ExtendedThread("ExtendedThread" + i, 1000);
						thread.setSelectMethod(1);
						thread.start();
					}

					// Spawning 5 Runnable Threads
					logger.info("Spawning five RunnableThreads");
					Map<String, RunnableThread> runnables = new HashMap<>();
					for (int i = 0; i < 5; i++) {
						String threadName = "RunnableThread" + i;
						RunnableThread runnable = new RunnableThread(1500);
						runnable.setMethod(1);
						runnables.put(threadName, runnable);

						Thread thread = new Thread(runnable);
						thread.setName(threadName);
						thread.start();
					}

					// Main thread - Sleeping for 10 seconds
					putThreadToSleep(10000);

					// Stopping all the extended threads
					Thread.getAllStackTraces().forEach((thread, trace) -> {
						if (thread instanceof ExtendedThread) {
							((ExtendedThread) thread).stopLoop();
						}
					});
					logger.info("States after 10 seconds : ");
					logCustomThreadStackTrace();

					// Main thread - Sleeping for 10 seconds
					putThreadToSleep(10000);

					// Stopping all runnable threads
					Thread.getAllStackTraces().forEach((thread, trace) -> {
						if (thread.getName().contains("RunnableThread")) {
							runnables.get(thread.getName()).stopLoop();
						}
					});
					logger.info("States after 10 seconds : ");
					logCustomThreadStackTrace();

					putThreadToSleep(2000);
					logger.info("All thread stack");
					logThreadStackTrace();
				}
					break;

				case 7: {
					// Spawning 5 extended threads
					ExtendedThread[] threads = new ExtendedThread[5];
					logger.info("Spawning five ExtendedThreads");
					for (int i = 0; i < 5; i++) {
						ExtendedThread thread = new ExtendedThread("ExtendedThread" + i, 1000);
						threads[i] = thread;
						thread.setSelectMethod(1);
						thread.start();
					}

					// Spawning 5 runnable threads
					logger.info("Spawning five RunnableThreads");
					Map<String, RunnableThread> runnables = new HashMap<>();
					for (int i = 0; i < 5; i++) {
						String threadName = "RunnableThread" + i;
						RunnableThread runnable = new RunnableThread(1500);
						runnable.setMethod(1);
						runnables.put(threadName, runnable);

						Thread thread = new Thread(runnable);
						thread.setName(threadName);
						thread.start();
					}

					// Waiting for 5 seconds
					putThreadToSleep(5000);
					logger.info("States after 5 seconds : ");
					logCustomThreadStackTrace();

					// Stopping extended thread one by one after 5 seconds
					for (int i = 0; i < 5; i++) {
						threads[i].stopLoop();
						putThreadToSleep(5000);
					}
					logger.info("States after Stopping Extended Threads : ");
					logCustomThreadStackTrace();

					// Stopping runnable threads one by one after 5 seconds
					Object[] runnableThreads = Thread.getAllStackTraces().keySet().toArray();
					for (Object object : runnableThreads) {
						Thread thread = (Thread) object;
						if (thread.getName().contains("RunnableThread")) {
							runnables.get(thread.getName()).stopLoop();
							putThreadToSleep(5000);
						}
					}
					logger.info("States after Stopping Runnable Threads : ");
					logCustomThreadStackTrace();

					logger.info("Task Completed");

					logger.info("All Threads Stack : ");
					logThreadStackTrace();
				}
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

	private static void logCustomThreadStackTrace() {
		logger.info("*******************************************************************");
		ThreadInfo[] info = mxbean.dumpAllThreads(true, true);
		for (ThreadInfo i : info) {
			if (i.getThreadName().contains("ExtendedThread") || i.getThreadName().contains("RunnableThread")) {
				logger.info(i.toString());
			}
		}
		logger.info("*******************************************************************");
	}

	private static void logThreadStackTrace() {
		logger.info("*******************************************************************");
		ThreadInfo[] info = mxbean.dumpAllThreads(true, true);
		for (ThreadInfo i : info) {
			logger.info(i.toString());
		}
		logger.info("*******************************************************************");
	}

	public static void putThreadToSleep(long microSeconds) {
		try {
			Thread.sleep(microSeconds);
		} catch (Exception e) {
		}
	}
}