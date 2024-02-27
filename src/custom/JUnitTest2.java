package custom;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import exceptions.CustomException;
import task.RegexTask;

public class JUnitTest2 {

	@Test
	public void test() throws CustomException{
		try {
			Assertions.assertEquals(true, new RegexTask().isValidMobileNumber("9876543210"));
		} catch (CustomException e) {
			throw new CustomException();
		}
	}

}
