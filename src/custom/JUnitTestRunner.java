package custom;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class JUnitTestRunner {
	
	public static void main(String[] args) {
//		JUnitTestTask task = new JUnitTestTask();
		Result result =  JUnitCore.runClasses(JUnitTestTask.class);
		System.out.println(result.getFailureCount()); 
		result.getFailures().forEach((e)->System.out.println(e));
	}

}
