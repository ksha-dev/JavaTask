package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import task.RegexTask;

class RegexTestCases {

	@Test
	void test() {
		RegexTask task = new RegexTask();

		try {
//			assertEquals(true, task.regexMatcher("^Hello\\w*$", "HelloThere"));
			String[] strings = { "Hello", "Hi", "HelloThere", "HiThere" };
			String[] matcheres = { "^Hello\\w*$", "^Hi\\w*$", "\\w*There$" };
			for (String matcher : matcheres) {
				for (String string : strings) {
//					assertEquals(false, task.regexMatcher(matcher, string));
					System.out.println(string + " " + matcher + " : " + task.regexMatcher(matcher, string));
				}
			}
		} catch (Exception e) {
		}
	}

}
