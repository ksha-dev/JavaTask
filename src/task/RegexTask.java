package task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import constants.ExceptionMessage;
import exceptions.CustomException;
import utility.Utility;

public class RegexTask {

	public static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9._+-]+@[a-zA-Z0-9.-]+\\.[a-zA-z]{2,}+$";
	public static final String MOBILE_NUMBER_VALIDATION_REGEX = "^[7-9]\\d{9}$";
	public static final String HTML_REGEX_GROUP = "<[^<]+>";
	public static final String PASSWORD_VALIDATOR_REGEX = "^((?=[^\\d])(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!_+-@^#$%&]).{8,20})$";


	public boolean regexMatcher(String matcherRegex, String originalString) throws CustomException {
		Utility.validateObject(matcherRegex);
		Utility.validateObject(originalString);
		return Pattern.matches(matcherRegex, originalString);
	}

	public boolean regexFinder(String finderRegex, String originalString) throws CustomException {
		Utility.validateObject(originalString);
		Utility.validateObject(finderRegex);
		return Pattern.compile(finderRegex).matcher(originalString).find();
	}

	public boolean isValidMobileNumber(String mobileNumber) throws CustomException {
		return regexMatcher(MOBILE_NUMBER_VALIDATION_REGEX, mobileNumber);
	}

	public boolean isOnlyAlphaNumeric(String string) throws CustomException {
		return regexMatcher("\\w+", string); // \w is equivalent to [a-zA-Z0-9]
	}

	public boolean isGivenContainsMatching(String givenString, String matchingString) throws CustomException {
		return regexFinder(matchingString, givenString);
	}

	public boolean isGivenStartsWithMatching(String givenString, String matchingString) throws CustomException {
		return isGivenContainsMatching(givenString, "^" + matchingString);
	}

	public boolean isGivenEndsWithMatching(String givenString, String matchingString) throws CustomException {
		return isGivenContainsMatching(givenString, matchingString + "$");
	}

	public boolean isExactMatch(String givenString, String matcherString) throws CustomException {
		return isGivenContainsMatching(givenString, "^" + matcherString + "$");
	}

	public boolean isValidEmail(String email) throws CustomException {
		return regexMatcher(EMAIL_VALIDATION_REGEX, email);
	}

	public boolean isInListOfStringCaseInsensitives(List<String> listOfStrings, String matcherString)
			throws CustomException {
		Utility.validateCollection(listOfStrings);
		Utility.validateObject(matcherString);
		int length = listOfStrings.size();
		if (length == 0) {
			throw new CustomException(ExceptionMessage.EMPTY_ENTITY);
		}
		String checkString = String.join("", listOfStrings);
		return regexFinder(matcherString, checkString);
	}

	public boolean hasListContainsStringsOfLength(List<String> listOfStrings, int startRange, int endRange)
			throws CustomException {
		Utility.validateCollection(listOfStrings);
		Utility.validateRange(startRange, endRange);
		int length = listOfStrings.size();
		if (length == 0) {
			throw new CustomException(ExceptionMessage.EMPTY_ENTITY);
		}
		String regex = "^.{" + startRange + "," + endRange + "}$";
		for(String string : listOfStrings) {
			if(!regexMatcher(regex, string)) {
				return false;
			}
		}
		return true;
	}

	public Map<String, List<Integer>> getMatchingMap(List<String> list1, List<String> list2) throws CustomException {
		Utility.validateCollection(list1);
		Utility.validateCollection(list2);
		Map<String, List<Integer>> map = new HashMap<>();
		int length = list1.size();
		for (String matcher : list2) {
			for (int i = 0; i < length; i++) {
				if (regexMatcher(matcher, list1.get(i))) {
					map.putIfAbsent(matcher, new ArrayList<Integer>());
					if (!map.get(matcher).contains(i)) {
						map.get(matcher).add(i);
					}
				}
			}
		}
		return map;
	}

	public List<String> getHTMLSnippets(String htmlString) throws CustomException {
		Matcher matcher = Pattern.compile(HTML_REGEX_GROUP).matcher(htmlString);
		List<String> snippets = new ArrayList<>();
		while (matcher.find()) {
			snippets.add(matcher.group());
		}
		return snippets;
	}

	public boolean isPasswordStrong(String password) throws CustomException {
		Utility.validateObject(password);
		return regexFinder(PASSWORD_VALIDATOR_REGEX, password);
	}


}
