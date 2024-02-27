package custom;

import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;

import exceptions.CustomException;
import task.StringMethods;

public class JUnitTest1 {

	@Test
	public void test() {
		assertThrows(CustomException.class, () -> new StringMethods().reverseString(null));
	}

}
