package logic.exception;

public class InvalidFileToParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public InvalidFileToParseException() {
		super("Please check the file if is not corrupted");
	}

	
	public InvalidFileToParseException(String string) {
		super(string);
	}

}
