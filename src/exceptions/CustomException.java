package exceptions;

import constants.ExceptionMessage;

@SuppressWarnings("serial")
public class CustomException extends Exception {


	public CustomException(String message) {
		super(message);
	}
	
	public CustomException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage.toString());
	}

	public CustomException() {
		super(ExceptionMessage.DEFAULT.toString());
	}

	public CustomException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public CustomException(ExceptionMessage message, Throwable throwable) {
		super(message.toString(), throwable);
	}

	public CustomException(Throwable throwable) {
		super(ExceptionMessage.DEFAULT.toString(), throwable);
	}
}