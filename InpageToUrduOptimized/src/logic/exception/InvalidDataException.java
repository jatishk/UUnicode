package logic.exception;

public class InvalidDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDataException() {
		// passing the message to Super(Exception class) will set Exception
		// message to below user defined message
		super("No Data or Invalid Data to Convert.");
	}
}
